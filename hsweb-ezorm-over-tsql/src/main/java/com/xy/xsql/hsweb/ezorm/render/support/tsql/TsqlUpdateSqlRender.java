package com.xy.xsql.hsweb.ezorm.render.support.tsql;

import com.xy.xsql.block.core.BlockManager;
import com.xy.xsql.tsql.model.datatypes.table.ColumnName;
import com.xy.xsql.tsql.model.queries.Update;
import org.hsweb.ezorm.core.param.UpdateParam;
import org.hsweb.ezorm.rdb.executor.SQL;
import org.hsweb.ezorm.rdb.meta.RDBColumnMetaData;
import org.hsweb.ezorm.rdb.meta.RDBTableMetaData;
import org.hsweb.ezorm.rdb.render.support.simple.SimpleSQL;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.xy.xsql.jdbc.PlaceholderExpressionFactory.placeholder;
import static com.xy.xsql.tsql.core.element.TableNameFactory.t;
import static com.xy.xsql.tsql.core.statement.dml.UpdateBuilder.UPDATE;

/**
 * Created by xiaoyao9184 on 2017/9/14.
 */
public class TsqlUpdateSqlRender extends CommentSupportRender<UpdateParam> {

    @Override
    public SQL render(RDBTableMetaData metaData, UpdateParam param) {
        this.metaData = metaData;
        this.param = param;

        Map.Entry<List<ColumnName>,List<Object>> cv = getColumns();
        List<Object> params = cv.getValue();
        Update.SetItem[] values = getValues(cv.getKey());

        Update update = UPDATE()
                .$(t(metaData.getName()))
                .$(metaData.getAlias())
                .$Set(values)
                .build();

        String sqlString = BlockManager.INSTANCE.print(update);
        return new SimpleSQL(sqlString,params.toArray());
    }

    private Map.Entry<List<ColumnName>,List<Object>> getColumns(){
        List<OperationColumn> operationColumnList = parseOperationField(metaData,param);
        List<ColumnName> columns = operationColumnList.stream()
                .map(operationColumn -> {
                    ColumnName columnName = new ColumnName();
                    columnName.setName(operationColumn.getRDBColumnMetaData().getName());
                    columnName.setTable(t(operationColumn.getTableName()));
                    return columnName;
                })
                .collect(Collectors.toList());

        List<Object> values = new ArrayList<>();
        operationColumnList.forEach(operationColumn -> {
            RDBColumnMetaData column = operationColumn.getRDBColumnMetaData();
            if (column.getProperty("read-only").isTrue()) return;
            String dataProperty = column.getAlias();
            Object value = null;
            try {
                value = propertyUtils.getProperty(param.getData(), dataProperty);
            } catch (Exception e) {
            }
            if (value == null && !column.getAlias().equals(column.getName())) {
                dataProperty = column.getName();
                try {
                    value = propertyUtils.getProperty(param.getData(), dataProperty);
                } catch (Exception e) {
                }
            }
            if (value == null) {
                if (logger.isInfoEnabled())
                    logger.info("跳过修改列:[{}], 属性[{}]为null!", column.getName(), column.getAlias());
                return;
            }
            if (column.getValueConverter() != null) {
                Object new_value = column.getValueConverter().getData(value);
                if (column.getOptionConverter() != null) {
                    new_value = column.getOptionConverter().converterData(new_value);
                }
                if (value != new_value && !value.equals(new_value)) {
                    // propertyUtils.setProperty(param.getData(), dataProperty, new_value);
                    value = new_value;
                }
            }
            values.add(value);
        });

        return new AbstractMap.SimpleEntry<>(columns,values);
    }

    private Update.ColumnAssignmentSet[] getValues(List<ColumnName> columnNameList){
        Update.ColumnAssignmentSet[] values = columnNameList.stream()
                .map(columnName -> {
                    Update.ColumnAssignmentSet set = new Update.ColumnAssignmentSet();
                    set.setColumnName(columnName);
                    set.setExpression(placeholder());
                    return set;
                })
                .toArray(Update.ColumnAssignmentSet[]::new);
        return values;
    }
}
