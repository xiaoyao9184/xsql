package com.xy.xsql.hsweb.ezorm.render.support.tsql;

import com.xy.xsql.block.core.BlockManager;
import com.xy.xsql.model.param.ListParameterModel;
import com.xy.xsql.tsql.model.datatypes.table.Alias;
import com.xy.xsql.tsql.model.datatypes.table.ColumnName;
import com.xy.xsql.tsql.model.datatypes.table.TableName;
import com.xy.xsql.tsql.model.queries.From;
import com.xy.xsql.tsql.model.queries.Select;
import com.xy.xsql.tsql.model.queries.Where;
import com.xy.xsql.tsql.model.queries.select.OrderBy;
import com.xy.xsql.tsql.model.queries.select.Select.SelectItem;
import com.xy.xsql.util.CheckUtil;
import org.hsweb.commons.StringUtils;
import org.hsweb.ezorm.core.param.QueryParam;
import org.hsweb.ezorm.core.param.Sort;
import org.hsweb.ezorm.rdb.executor.SQL;
import org.hsweb.ezorm.rdb.meta.Correlation;
import org.hsweb.ezorm.rdb.meta.RDBColumnMetaData;
import org.hsweb.ezorm.rdb.meta.RDBTableMetaData;
import org.hsweb.ezorm.rdb.render.support.simple.SimpleSQL;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.xy.xsql.core.handler.list.ListHandler.list;
import static com.xy.xsql.tsql.builder.chain.datatypes.table.ColumnNameFactory.c;
import static com.xy.xsql.tsql.builder.chain.datatypes.table.TableNameFactory.t;
import static com.xy.xsql.tsql.builder.chain.elements.expressions.Expressions.e;
import static com.xy.xsql.tsql.builder.chain.queries.Queries.$Query;
import static com.xy.xsql.tsql.builder.chain.queries.Queries.$Select;

/**
 * Created by zhouhao on 16-5-17.
 */
public class TsqlSelectSqlRender extends CommentSupportRender<QueryParam> {

    @Override
    public SQL render(RDBTableMetaData metaData, QueryParam param) {
        this.metaData = metaData;
        this.param = param;

        if (param.getIncludes().isEmpty() && param.getExcludes().isEmpty()) {
            param.includes("*");
            metaData.getCorrelations().forEach(correlation -> param.includes(correlation.getAlias() + ".*"));
        }
        //解析要查询的字段
        List<OperationColumn> selectField = parseOperationField(metaData, param);
        //解析要查询的表
        List<String> needSelectTable = getNeedSelectTable();

        List<Sort>  sorts           = new ArrayList<>();
        selectField.stream().forEach(field -> {
            needSelectTable.add(field.getTableName());
        });
        param.getSorts().forEach(sort -> {
            RDBColumnMetaData rDBColumnMetaData = metaData.findColumn(sort.getName());
            if (rDBColumnMetaData.getName() == null) return;
            String tableName = getTableAlias(metaData, sort.getName());
            needSelectTable.add(tableName);
            sort.setName(buildColumnName(tableName, rDBColumnMetaData.getName()));
            sorts.add(sort);
        });

        ListParameterModel<From.TableSource> tableSourceListParameterModel = getTableSource(needSelectTable);
        List<Object> params = tableSourceListParameterModel.getParam();
        From.TableSource tableSource = tableSourceListParameterModel.getModel();

        ListParameterModel<Where> whereListParameterModel = getWhere();
        params.addAll(whereListParameterModel.getParam());
        Where where = whereListParameterModel.getModel();

        Select.QuerySpecification query = $Query()
                .withSelectItem(getSelectItems(selectField))
                .withFrom()
                    .withItem(tableSource)
                    .and()
                .withWhere(where)
                .build();

        OrderBy orderBy = getOrderBy(sorts);

        if(param.isPaging()){
            Integer pageIndex = param.getPageIndex();
            Integer pageSize = param.getPageSize();
            Integer rowIndex = pageIndex * pageSize;
            OrderBy.OffsetFetch offsetFetch = new OrderBy.OffsetFetch();
            offsetFetch.setUseRows(true);
            offsetFetch.setIntegerConstant(rowIndex);
            offsetFetch.setUseFetch(true);
            offsetFetch.setFetchIntegerConstant(pageSize);
            offsetFetch.setUseFetchRows(true);
            orderBy.setOffsetFetch(offsetFetch);
            if(CheckUtil.isNullOrEmpty(orderBy.getItems())){
                String uniqueColumn = metaData
                        .getProperty("unique-column", () -> {
                            RDBColumnMetaData column = metaData.findColumn("u_id") == null ? metaData.findColumn("id") : null;
                            if (column != null) return column.getName();
                            return null;
                        })
                        .getValue();
                if (uniqueColumn == null) {
                    throw new NullPointerException("unique column is null,you can set table property:unique-column or add column : id or u_id");
                }
                OrderBy.Item item = new OrderBy.Item();
                item.setOrderByExpression(e(uniqueColumn));
                list(orderBy::getItems, orderBy::setItems).add(item);
            }
        }else if(CheckUtil.isNullOrEmpty(orderBy.getItems())){
            orderBy = null;
        }

        Select select = $Select()
                .withQuery(query)
                .withOrderBy(orderBy)
                .build();

        String sqlString = BlockManager.INSTANCE.print(select);
        return new SimpleSQL(sqlString,params.toArray());
    }

    public SelectItem[] getSelectItems(List<OperationColumn> selectField){
        List<SelectItem> selectItemList = selectField.stream()
                .map(operationColumn -> {
                    RDBColumnMetaData rDBColumnMetaData = operationColumn.getRDBColumnMetaData();
                    String tableName = rDBColumnMetaData.getTableMetaData().getName();
                    Correlation correlation = metaData.getCorrelation(tableName);

                    TableName table = t(operationColumn.getTableName());
                    ColumnName column = c(table,rDBColumnMetaData.getName());
                    SelectItem item = new SelectItem();
                    if (correlation == null) {
                        item.setUseAs(true);
                        item.setColumnName(column);
                        item.setColumnAlias(new Alias<>(rDBColumnMetaData.getAlias()));
                    } else {
                        //关联的另外一张表
                        if (correlation.isOne2one()) {
                            item.setUseAs(true);
                            item.setColumnName(column);
                            item.setColumnAlias(new Alias<>(operationColumn.getTableName() + "." + rDBColumnMetaData.getAlias()));
                        }
                    }
                    return item;
                })
                .collect(Collectors.toList());

        if (selectItemList.isEmpty()){
            SelectItem selectItem = new SelectItem();
            selectItem.setUseAll(true);
            selectItemList.add(selectItem);
        }
        return selectItemList.toArray(new SelectItem[]{});
    }

    public OrderBy getOrderBy(List<Sort> sorts){
        //生成orderby
        return sorts.stream()
                .map(sort -> {
                    OrderBy.Item item = new OrderBy.Item();
                    item.setOrderByExpression(e(sort.getName()));
                    if(sort.getOrder().equals("asc")){
                        item.setUseAsc(true);
                    }else if(sort.getOrder().equals("desc")){
                        item.setUseDesc(true);
                    }
                    return item;
                })
                .collect(
                        () -> {
                            OrderBy orderBy = new OrderBy();
                            orderBy.setItems(new ArrayList<>());
                            return orderBy;
                        },
                        (orderBy, item) -> {
                            list(orderBy::getItems, orderBy::setItems).add(item);
                        },
                        (orderBy, orderBy2) -> {
                            list(orderBy::getItems, orderBy::setItems).addAll(orderBy2.getItems());
                            throw new UnsupportedOperationException("OrderBy not support merge!");
                        }
                );
    }

    private String buildColumnName(String tableName, String columnName) {
        if (columnName.contains(".")) return columnName;
        if (StringUtils.isNullOrEmpty(tableName)) {
            return columnName;
        }
        return StringUtils.concat(tableName, ".", columnName);
    }

}
