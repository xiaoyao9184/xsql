package com.xy.xsql.tsql.style.constructor.introducer.queries.select;

import com.xy.xsql.tsql.model.datatypes.table.Alias;
import com.xy.xsql.tsql.model.datatypes.table.ColumnName;
import com.xy.xsql.tsql.model.datatypes.table.TableName;
import com.xy.xsql.tsql.model.elements.expressions.Expression;
import com.xy.xsql.tsql.style.constructor.builder.queries.b_TOP;
import com.xy.xsql.tsql.style.constructor.builder.queries.k_;
import com.xy.xsql.tsql.style.constructor.builder.queries.select.b_SELECT;

import static com.xy.xsql.tsql.builder.chain.datatypes.table.ColumnNameFactory.c;

/**
 * Created by xiaoyao9184 on 2018/5/9.
 */
@SuppressWarnings({"WeakerAccess", "unused"})
public interface select {

    static b_SELECT SELECT(
            k_.k_ALL all,
            b_TOP top,
            TableName tableName){
        return new b_SELECT(){
            {
                withAll();
                withTop(top.build());
                withSelectItem().withTableAll(tableName);
            }
        };
    }

    static b_SELECT SELECT(
            k_.k_ALL all,
            b_TOP top,
            TableName tableName,
            b_SELECT.k$IDENTITY $identity){
        return new b_SELECT(){
            {
                withAll();
                withTop(top.build());
                withSelectItem().withColumnName(c(tableName,"$IDENTITY"));
            }
        };
    }

    static b_SELECT SELECT(
            k_.k_ALL all,
            b_TOP top,
            TableName tableName,
            b_SELECT.k$ROWGUID $rowguid){
        return new b_SELECT(){
            {
                withAll();
                withTop(top.build());
                withSelectItem().withColumnName(c(tableName,"$ROWGUID"));
            }
        };
    }

    static b_SELECT SELECT(
            k_.k_ALL all,
            b_TOP top,
            ColumnName columnName){
        return new b_SELECT(){
            {
                withAll();
                withTop(top.build());
                withSelectItem()
                        .withColumnName(columnName);
            }
        };
    }

    static b_SELECT SELECT(
            k_.k_ALL all,
            b_TOP top,
            ColumnName columnName,
            String column_alias){
        return new b_SELECT(){
            {
                withAll();
                withTop(top.build());
                withSelectItem()
                        .withColumnName(columnName)
                        .withColumnAlias(column_alias);
            }
        };
    }

    static b_SELECT SELECT(
            k_.k_ALL all,
            b_TOP top,
            ColumnName columnName,
            b_SELECT.b_AS as){
        return new b_SELECT(){
            {
                withAll();
                withTop(top.build());
                withSelectItem()
                        .withColumnName(columnName)
                        .withAs()
                        .withColumnAlias(as.build());
            }
        };
    }

    static b_SELECT SELECT(
            k_.k_ALL all,
            b_TOP top,
            Expression expression){
        return new b_SELECT(){
            {
                withAll();
                withTop(top.build());
                withSelectItem()
                        .withExpression(expression);
            }
        };
    }

    static b_SELECT SELECT(
            k_.k_ALL all,
            b_TOP top,
            Expression expression,
            String column_alias){
        return new b_SELECT(){
            {
                withAll();
                withTop(top.build());
                withSelectItem()
                        .withExpression(expression)
                        .withColumnAlias(column_alias);
            }
        };
    }

    static b_SELECT SELECT(
            k_.k_ALL all,
            b_TOP top,
            Expression expression,
            b_SELECT.b_AS as){
        return new b_SELECT(){
            {
                withAll();
                withTop(top.build());
                withSelectItem()
                        .withExpression(expression)
                        .withAs()
                        .withColumnAlias(as.build());
            }
        };
    }

    static b_SELECT SELECT(
            k_.k_ALL all,
            b_TOP top,
            String column_alias,
            Expression expression){
        return new b_SELECT(){
            {
                withAll();
                withTop(top.build());
                withSelectItem()
                        .withColumnAlias(new Alias<>(column_alias))
                        .withEQ()
                        .withExpression(expression);
            }
        };
    }



    static b_SELECT SELECT(
            k_.k_ALL all,
            b_TOP top){
        return new b_SELECT(){
            {
                withAll();
                withTop(top.build());
                withSelectItem().withAll();
            }
        };
    }

    static b_SELECT SELECT(
            k_.k_ALL all,
            TableName tableName){
        return new b_SELECT(){
            {
                withAll();
                withSelectItem().withTableAll(tableName);
            }
        };
    }

    static b_SELECT SELECT(
            k_.k_ALL all,
            TableName tableName,
            b_SELECT.k$IDENTITY $identity){
        return new b_SELECT(){
            {
                withAll();
                withSelectItem().withColumnName(c(tableName,"$IDENTITY"));
            }
        };
    }

    static b_SELECT SELECT(
            k_.k_ALL all,
            TableName tableName,
            b_SELECT.k$ROWGUID $rowguid){
        return new b_SELECT(){
            {
                withAll();
                withSelectItem().withColumnName(c(tableName,"$ROWGUID"));
            }
        };
    }

    static b_SELECT SELECT(
            k_.k_ALL all,
            ColumnName columnName){
        return new b_SELECT(){
            {
                withAll();
                withSelectItem()
                        .withColumnName(columnName);
            }
        };
    }

    static b_SELECT SELECT(
            k_.k_ALL all,
            ColumnName columnName,
            String column_alias){
        return new b_SELECT(){
            {
                withAll();
                withSelectItem()
                        .withColumnName(columnName)
                        .withColumnAlias(column_alias);
            }
        };
    }

    static b_SELECT SELECT(
            k_.k_ALL all,
            ColumnName columnName,
            b_SELECT.b_AS as){
        return new b_SELECT(){
            {
                withAll();
                withSelectItem()
                        .withColumnName(columnName)
                        .withAs()
                        .withColumnAlias(as.build());
            }
        };
    }

    static b_SELECT SELECT(
            k_.k_ALL all,
            Expression expression){
        return new b_SELECT(){
            {
                withAll();
                withSelectItem()
                        .withExpression(expression);
            }
        };
    }

    static b_SELECT SELECT(
            k_.k_ALL all,
            Expression expression,
            String column_alias){
        return new b_SELECT(){
            {
                withAll();
                withSelectItem()
                        .withExpression(expression)
                        .withColumnAlias(column_alias);
            }
        };
    }

    static b_SELECT SELECT(
            k_.k_ALL all,
            Expression expression,
            b_SELECT.b_AS as){
        return new b_SELECT(){
            {
                withAll();
                withSelectItem()
                        .withExpression(expression)
                        .withAs()
                        .withColumnAlias(as.build());
            }
        };
    }

    static b_SELECT SELECT(
            k_.k_ALL all,
            String column_alias,
            Expression expression){
        return new b_SELECT(){
            {
                withAll();
                withSelectItem()
                        .withColumnAlias(new Alias<>(column_alias))
                        .withEQ()
                        .withExpression(expression);
            }
        };
    }

    static b_SELECT SELECT(
            k_.k_ALL all){
        return new b_SELECT(){
            {
                withAll();
                withSelectItem().withAll();
            }
        };
    }

    //

    static b_SELECT SELECT(
            k_.k_DISTINCT distinct,
            b_TOP top,
            TableName tableName){
        return new b_SELECT(){
            {
                withDistinct();
                withTop(top.build());
                withSelectItem().withTableAll(tableName);
            }
        };
    }

    static b_SELECT SELECT(
            k_.k_DISTINCT distinct,
            b_TOP top,
            TableName tableName,
            b_SELECT.k$IDENTITY $identity){
        return new b_SELECT(){
            {
                withDistinct();
                withTop(top.build());
                withSelectItem().withColumnName(c(tableName,"$IDENTITY"));
            }
        };
    }

    static b_SELECT SELECT(
            k_.k_DISTINCT distinct,
            b_TOP top,
            TableName tableName,
            b_SELECT.k$ROWGUID $rowguid){
        return new b_SELECT(){
            {
                withDistinct();
                withTop(top.build());
                withSelectItem().withColumnName(c(tableName,"$ROWGUID"));
            }
        };
    }

    static b_SELECT SELECT(
            k_.k_DISTINCT distinct,
            b_TOP top,
            ColumnName columnName){
        return new b_SELECT(){
            {
                withDistinct();
                withTop(top.build());
                withSelectItem()
                        .withColumnName(columnName);
            }
        };
    }

    static b_SELECT SELECT(
            k_.k_DISTINCT distinct,
            b_TOP top,
            ColumnName columnName,
            String column_alias){
        return new b_SELECT(){
            {
                withDistinct();
                withTop(top.build());
                withSelectItem()
                        .withColumnName(columnName)
                        .withColumnAlias(column_alias);
            }
        };
    }

    static b_SELECT SELECT(
            k_.k_DISTINCT distinct,
            b_TOP top,
            ColumnName columnName,
            b_SELECT.b_AS as){
        return new b_SELECT(){
            {
                withDistinct();
                withTop(top.build());
                withSelectItem()
                        .withColumnName(columnName)
                        .withAs()
                        .withColumnAlias(as.build());
            }
        };
    }

    static b_SELECT SELECT(
            k_.k_DISTINCT distinct,
            b_TOP top,
            Expression expression){
        return new b_SELECT(){
            {
                withDistinct();
                withTop(top.build());
                withSelectItem()
                        .withExpression(expression);
            }
        };
    }

    static b_SELECT SELECT(
            k_.k_DISTINCT distinct,
            b_TOP top,
            Expression expression,
            String column_alias){
        return new b_SELECT(){
            {
                withDistinct();
                withTop(top.build());
                withSelectItem()
                        .withExpression(expression)
                        .withColumnAlias(column_alias);
            }
        };
    }

    static b_SELECT SELECT(
            k_.k_DISTINCT distinct,
            b_TOP top,
            Expression expression,
            b_SELECT.b_AS as){
        return new b_SELECT(){
            {
                withDistinct();
                withTop(top.build());
                withSelectItem()
                        .withExpression(expression)
                        .withAs()
                        .withColumnAlias(as.build());
            }
        };
    }

    static b_SELECT SELECT(
            k_.k_DISTINCT distinct,
            b_TOP top,
            String column_alias,
            Expression expression){
        return new b_SELECT(){
            {
                withDistinct();
                withTop(top.build());
                withSelectItem()
                        .withColumnAlias(new Alias<>(column_alias))
                        .withEQ()
                        .withExpression(expression);
            }
        };
    }



    static b_SELECT SELECT(
            k_.k_DISTINCT distinct,
            b_TOP top){
        return new b_SELECT(){
            {
                withDistinct();
                withTop(top.build());
                withSelectItem().withAll();
            }
        };
    }

    static b_SELECT SELECT(
            k_.k_DISTINCT distinct,
            TableName tableName){
        return new b_SELECT(){
            {
                withDistinct();
                withSelectItem().withTableAll(tableName);
            }
        };
    }

    static b_SELECT SELECT(
            k_.k_DISTINCT distinct,
            TableName tableName,
            b_SELECT.k$IDENTITY $identity){
        return new b_SELECT(){
            {
                withDistinct();
                withSelectItem().withColumnName(c(tableName,"$IDENTITY"));
            }
        };
    }

    static b_SELECT SELECT(
            k_.k_DISTINCT distinct,
            TableName tableName,
            b_SELECT.k$ROWGUID $rowguid){
        return new b_SELECT(){
            {
                withDistinct();
                withSelectItem().withColumnName(c(tableName,"$ROWGUID"));
            }
        };
    }

    static b_SELECT SELECT(
            k_.k_DISTINCT distinct,
            ColumnName columnName){
        return new b_SELECT(){
            {
                withDistinct();
                withSelectItem()
                        .withColumnName(columnName);
            }
        };
    }

    static b_SELECT SELECT(
            k_.k_DISTINCT distinct,
            ColumnName columnName,
            String column_alias){
        return new b_SELECT(){
            {
                withDistinct();
                withSelectItem()
                        .withColumnName(columnName)
                        .withColumnAlias(column_alias);
            }
        };
    }

    static b_SELECT SELECT(
            k_.k_DISTINCT distinct,
            ColumnName columnName,
            b_SELECT.b_AS as){
        return new b_SELECT(){
            {
                withDistinct();
                withSelectItem()
                        .withColumnName(columnName)
                        .withAs()
                        .withColumnAlias(as.build());
            }
        };
    }

    static b_SELECT SELECT(
            k_.k_DISTINCT distinct,
            Expression expression){
        return new b_SELECT(){
            {
                withDistinct();
                withSelectItem()
                        .withExpression(expression);
            }
        };
    }

    static b_SELECT SELECT(
            k_.k_DISTINCT distinct,
            Expression expression,
            String column_alias){
        return new b_SELECT(){
            {
                withDistinct();
                withSelectItem()
                        .withExpression(expression)
                        .withColumnAlias(column_alias);
            }
        };
    }

    static b_SELECT SELECT(
            k_.k_DISTINCT distinct,
            Expression expression,
            b_SELECT.b_AS as){
        return new b_SELECT(){
            {
                withDistinct();
                withSelectItem()
                        .withExpression(expression)
                        .withAs()
                        .withColumnAlias(as.build());
            }
        };
    }

    static b_SELECT SELECT(
            k_.k_DISTINCT distinct,
            String column_alias,
            Expression expression){
        return new b_SELECT(){
            {
                withDistinct();
                withSelectItem()
                        .withColumnAlias(new Alias<>(column_alias))
                        .withEQ()
                        .withExpression(expression);
            }
        };
    }

    static b_SELECT SELECT(
            k_.k_DISTINCT distinct){
        return new b_SELECT(){
            {
                withDistinct();
                withSelectItem().withAll();
            }
        };
    }

    //

    static b_SELECT SELECT(
            b_TOP top,
            TableName tableName){
        return new b_SELECT(){
            {
                withTop(top.build());
                withSelectItem().withTableAll(tableName);
            }
        };
    }

    static b_SELECT SELECT(
            b_TOP top,
            TableName tableName,
            b_SELECT.k$IDENTITY $identity){
        return new b_SELECT(){
            {
                withTop(top.build());
                withSelectItem().withColumnName(c(tableName,"$IDENTITY"));
            }
        };
    }

    static b_SELECT SELECT(
            b_TOP top,
            TableName tableName,
            b_SELECT.k$ROWGUID $rowguid){
        return new b_SELECT(){
            {
                withTop(top.build());
                withSelectItem().withColumnName(c(tableName,"$ROWGUID"));
            }
        };
    }

    static b_SELECT SELECT(
            b_TOP top,
            ColumnName columnName){
        return new b_SELECT(){
            {
                withTop(top.build());
                withSelectItem()
                        .withColumnName(columnName);
            }
        };
    }

    static b_SELECT SELECT(
            b_TOP top,
            ColumnName columnName,
            String column_alias){
        return new b_SELECT(){
            {
                withTop(top.build());
                withSelectItem()
                        .withColumnName(columnName)
                        .withColumnAlias(column_alias);
            }
        };
    }

    static b_SELECT SELECT(
            b_TOP top,
            ColumnName columnName,
            b_SELECT.b_AS as){
        return new b_SELECT(){
            {
                withTop(top.build());
                withSelectItem()
                        .withColumnName(columnName)
                        .withAs()
                        .withColumnAlias(as.build());
            }
        };
    }

    static b_SELECT SELECT(
            b_TOP top,
            Expression expression){
        return new b_SELECT(){
            {
                withTop(top.build());
                withSelectItem()
                        .withExpression(expression);
            }
        };
    }

    static b_SELECT SELECT(
            b_TOP top,
            Expression expression,
            String column_alias){
        return new b_SELECT(){
            {
                withTop(top.build());
                withSelectItem()
                        .withExpression(expression)
                        .withColumnAlias(column_alias);
            }
        };
    }

    static b_SELECT SELECT(
            b_TOP top,
            Expression expression,
            b_SELECT.b_AS as){
        return new b_SELECT(){
            {
                withTop(top.build());
                withSelectItem()
                        .withExpression(expression)
                        .withAs()
                        .withColumnAlias(as.build());
            }
        };
    }

    static b_SELECT SELECT(
            b_TOP top,
            String column_alias,
            Expression expression){
        return new b_SELECT(){
            {
                withTop(top.build());
                withSelectItem()
                        .withColumnAlias(new Alias<>(column_alias))
                        .withEQ()
                        .withExpression(expression);
            }
        };
    }

    //

    static b_SELECT SELECT(
            b_TOP top){
        return new b_SELECT(){
            {
                withTop(top.build());
                withSelectItem().withAll();
            }
        };
    }

    static b_SELECT SELECT(
            TableName tableName){
        return new b_SELECT(){
            {
                withSelectItem().withTableAll(tableName);
            }
        };
    }

    static b_SELECT SELECT(
            TableName tableName,
            b_SELECT.k$IDENTITY $identity){
        return new b_SELECT(){
            {
                withSelectItem().withColumnName(c(tableName,"$IDENTITY"));
            }
        };
    }

    static b_SELECT SELECT(
            TableName tableName,
            b_SELECT.k$ROWGUID $rowguid){
        return new b_SELECT(){
            {
                withSelectItem().withColumnName(c(tableName,"$ROWGUID"));
            }
        };
    }

    static b_SELECT SELECT(
            ColumnName columnName){
        return new b_SELECT(){
            {
                withSelectItem()
                        .withColumnName(columnName);
            }
        };
    }

    static b_SELECT SELECT(
            ColumnName columnName,
            String column_alias){
        return new b_SELECT(){
            {
                withSelectItem()
                        .withColumnName(columnName)
                        .withColumnAlias(column_alias);
            }
        };
    }

    static b_SELECT SELECT(
            ColumnName columnName,
            b_SELECT.b_AS as){
        return new b_SELECT(){
            {
                withSelectItem()
                        .withColumnName(columnName)
                        .withAs()
                        .withColumnAlias(as.build());
            }
        };
    }

    static b_SELECT SELECT(
            Expression expression){
        return new b_SELECT(){
            {
                withSelectItem()
                        .withExpression(expression);
            }
        };
    }

    static b_SELECT SELECT(
            Expression expression,
            String column_alias){
        return new b_SELECT(){
            {
                withSelectItem()
                        .withExpression(expression)
                        .withColumnAlias(column_alias);
            }
        };
    }

    static b_SELECT SELECT(
            Expression expression,
            b_SELECT.b_AS as){
        return new b_SELECT(){
            {
                withSelectItem()
                        .withExpression(expression)
                        .withAs()
                        .withColumnAlias(as.build());
            }
        };
    }

    static b_SELECT SELECT(
            String column_alias,
            Expression expression){
        return new b_SELECT(){
            {
                withSelectItem()
                        .withColumnAlias(new Alias<>(column_alias))
                        .withEQ()
                        .withExpression(expression);
            }
        };
    }

    static b_SELECT SELECT(){
        return new b_SELECT(){
            {
                withSelectItem().withAll();
            }
        };
    }


    //

    static k_.k_ALL ALL(){
        return null;
    }
    static k_.k_DISTINCT DISTINCT(){
        return null;
    }

    //

    static b_SELECT.b_AS AS(String column_alias){
        return new b_SELECT.b_AS(column_alias);
    }
    static b_SELECT.k$IDENTITY $IDENTITY(){
        return null;
    }
    static b_SELECT.k$ROWGUID $ROWGUID(){
        return null;
    }

    //

    static b_SELECT.b$select_list $(){
        return new b_SELECT.b$select_list(){
            {
                withItem()
                        .withAll();
            }
        };
    }

}
