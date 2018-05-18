package com.xy.xsql.hsweb.ezorm.render.support.tsql;

import com.xy.xsql.block.core.BlockManager;
import com.xy.xsql.model.sql.PlaceholderJSql;
import com.xy.xsql.tsql.model.datatypes.table.ColumnName;
import com.xy.xsql.tsql.model.elements.expressions.Expression;
import com.xy.xsql.tsql.model.elements.expressions.UnknownExpression;
import com.xy.xsql.tsql.model.statements.Insert;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.PropertyUtilsBean;
import org.hsweb.ezorm.core.param.InsertParam;
import org.hsweb.ezorm.rdb.executor.BindSQL;
import org.hsweb.ezorm.rdb.executor.SQL;
import org.hsweb.ezorm.rdb.meta.RDBTableMetaData;
import org.hsweb.ezorm.rdb.render.SqlRender;
import org.hsweb.ezorm.rdb.render.support.simple.SimpleSQL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.xy.xsql.jdbc.PlaceholderExpressionFactory.placeholder;
import static com.xy.xsql.tsql.builder.chain.datatypes.table.TableNameFactory.t;
import static com.xy.xsql.tsql.builder.chain.statements.InsertBuilder.INSERT;

public class TsqlInsertSqlRender implements SqlRender<InsertParam> {
    PropertyUtilsBean propertyUtils = BeanUtilsBean.getInstance().getPropertyUtils();
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    protected RDBTableMetaData metaData;
    protected InsertParam param;

    @Override
    public SQL render(RDBTableMetaData metaData, InsertParam param) {
        this.metaData = metaData;
        this.param = param;

        Object data = Objects.requireNonNull(param.getData(), "param can not be null!");
        if (data == null) throw new NullPointerException();
        List<Object> datas;
        if (data instanceof Collection) {
            datas = new ArrayList<>(((Collection) data));
        } else if (data instanceof Object[]) {
            datas = Arrays.asList(((Object[]) data));
        } else{
            datas = Collections.singletonList(data);
        }

        List<PlaceholderJSql> list = datas.stream()
                .map(d -> {
            Map.Entry<List<ColumnName>,List<Object>> cv = getColumns(data);
            List<Object> params = cv.getValue();
            Expression[] values = getValues(cv.getValue().size());

            Insert insert = INSERT()
                    .$(t(metaData.getAlias()))
                    .withColumn(cv.getKey().toArray(new ColumnName[]{}))
                    .withValues()
                    .$(values)
                    .and()
                    .build();

            String sqlString = BlockManager.INSTANCE.print(insert);
            return new PlaceholderJSql().withSql(sqlString).withArgs(params);
        })
        .collect(Collectors.toList());

        SimpleSQL sql = list.stream()
                .findFirst()
                .map(placeholderJSql -> new SimpleSQL(placeholderJSql.getSql(),placeholderJSql.getArgs()))
                .orElseThrow(RuntimeException::new);
        list.remove(0);

        List<BindSQL> bindSQLS = list.stream()
                .map(placeholderJSql -> {
                    SimpleSQL simpleSQL = new SimpleSQL(placeholderJSql.getSql(),placeholderJSql.getArgs());
                    BindSQL bindSQL = new BindSQL();
                    bindSQL.setSql(simpleSQL);
                    return bindSQL;
                })
                .collect(Collectors.toList());

        sql.setBindSQLs(bindSQLS);

        return sql;
    }


    private Map.Entry<List<ColumnName>,List<Object>> getColumns(Object data){
        List<ColumnName> columns = new ArrayList<>();
        List<Object> values = new ArrayList<>();
        metaData.getColumns().forEach(column -> {
            Object value = null;
            String propertyName = null;
            try {
                value = propertyUtils.getProperty(data, propertyName = column.getAlias());
            } catch (Exception e) {
                try {
                    value = propertyUtils.getProperty(data, propertyName = column.getName());
                } catch (Exception ignore) {
                    //ignore
                }
            }
            if (value == null) {
                value = column.getProperty("default-value").getValue();
                if (logger.isInfoEnabled() && value != null)
                    logger.info("{}将使用默认值[default-value]:{}", propertyName, value);
            }
            if (value != null && column.getValueConverter() != null) {
                Object new_value = column.getValueConverter().getData(value);
                if (column.getOptionConverter() != null) {
                    new_value = column.getOptionConverter().converterData(new_value);
                }
                if (value != new_value && !value.equals(new_value)) {
                    if (logger.isDebugEnabled())
                        logger.debug("{} 转换value:{}为:{}", propertyName, value, new_value);
                    value = new_value;
                }
            }
            if (null == value) {
                return;
            }

            columns.add(new ColumnName(column.getName()));
            values.add(value);
        });

        return new AbstractMap.SimpleEntry<>(columns,values);
    }

    private Expression[] getValues(int count){
        UnknownExpression[] values = IntStream.rangeClosed(1,count)
                .mapToObj(i -> placeholder())
                .toArray(UnknownExpression[]::new);
        return values;
    }
}
