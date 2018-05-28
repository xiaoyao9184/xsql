package com.xy.xsql.tsql.style.constructor.builder.queries.select;

import com.xy.xsql.core.builder.CodeBuilder;
import com.xy.xsql.tsql.builder.chain.queries.select.SelectBuilder;
import com.xy.xsql.tsql.model.datatypes.table.ColumnName;
import com.xy.xsql.tsql.model.datatypes.table.TableName;
import com.xy.xsql.tsql.model.elements.expressions.Expression;

import static com.xy.xsql.tsql.builder.chain.datatypes.table.ColumnNameFactory.c;

/**
 * Created by xiaoyao9184 on 2015/5/25.
 */
public class b_SELECT extends SelectBuilder<b_SELECT> {

    public b_SELECT() {
        this.in(this);
    }


    /*
    Item
     */

    public b_SELECT $$() {
        return withSelectItem()
                .withAll()
                .and()
                .and();
    }

    public b_SELECT $$(TableName tableName) {
        return withSelectItem()
                .withTableAll(tableName)
                .and()
                .and();
    }

    public b_SELECT $$(TableName tableName, k$IDENTITY $identity) {
        return withSelectItem()
                .withColumnName(c(tableName,"$IDENTITY"))
                .and()
                .and();
    }

    public b_SELECT $$(TableName tableName, k$ROWGUID $rowguid) {
        return withSelectItem()
                .withColumnName(c(tableName,"$ROWGUID"))
                .and()
                .and();
    }

    public b_SELECT $$(ColumnName columnName) {
        return withSelectItem()
                .withColumnName(columnName)
                .and()
                .and();
    }

    public b_SELECT $$(ColumnName columnName, String columnAlias) {
        return withSelectItem()
                .withColumnName(columnName)
                .withAs()
                .withColumnAlias(columnAlias)
                .and()
                .and();
    }

    public b_SELECT $$(ColumnName columnName, b_AS as) {
        return withSelectItem()
                .withColumnName(columnName)
                .withAs()
                .withColumnAlias(as.build())
                .and()
                .and();
    }

    public b_SELECT $$(String columnAlias, Expression expression) {
        return withSelectItem()
                .withColumnAlias(columnAlias)
                .withExpression(expression)
                .withEQ()
                .and()
                .and();
    }

    public b_SELECT $$(Expression expression) {
        return withSelectItem()
                .withExpression(expression)
                .and()
                .and();
    }

    public b_SELECT $$(Expression expression, String columnAlias) {
        return withSelectItem()
                .withExpression(expression)
                .withAs()
                .withColumnAlias(columnAlias)
                .and()
                .and();
    }

    public b_SELECT $$(Expression expression, b_AS as) {
        return withSelectItem()
                .withExpression(expression)
                .withAs()
                .withColumnAlias(as.build())
                .and()
                .and();
    }




    public static class b_AS extends CodeBuilder<String> {
        public b_AS(String s) {
            super(s);
        }
    }

    public static class k$IDENTITY {}

    public static class k$ROWGUID {}


    public static class b$select_list extends SelectListBuilder<b$select_list>{

        public b$select_list() {
            this.in(this);
        }


        /*
        Item
         */

        public b$select_list $$() {
            return withItem()
                    .withAll()
                    .and()
                    .and();
        }

        public b$select_list $$(TableName tableName) {
            return withItem()
                    .withTableAll(tableName)
                    .and()
                    .and();
        }

        public b$select_list $$(TableName tableName, k$IDENTITY $identity) {
            return withItem()
                    .withColumnName(c(tableName,"$IDENTITY"))
                    .and()
                    .and();
        }

        public b$select_list $$(TableName tableName, k$ROWGUID $rowguid) {
            return withItem()
                    .withColumnName(c(tableName,"$ROWGUID"))
                    .and()
                    .and();
        }

        public b$select_list $$(ColumnName columnName) {
            return withItem()
                    .withColumnName(columnName)
                    .and()
                    .and();
        }

        public b$select_list $$(ColumnName columnName, String columnAlias) {
            return withItem()
                    .withColumnName(columnName)
                    .withAs()
                    .withColumnAlias(columnAlias)
                    .and()
                    .and();
        }

        public b$select_list $$(ColumnName columnName, b_AS as) {
            return withItem()
                    .withColumnName(columnName)
                    .withAs()
                    .withColumnAlias(as.build())
                    .and()
                    .and();
        }

        public b$select_list $$(String columnAlias, Expression expression) {
            return withItem()
                    .withColumnAlias(columnAlias)
                    .withExpression(expression)
                    .withEQ()
                    .and()
                    .and();
        }

        public b$select_list $$(Expression expression) {
            return withItem()
                    .withExpression(expression)
                    .and()
                    .and();
        }

        public b$select_list $$(Expression expression, String columnAlias) {
            return withItem()
                    .withExpression(expression)
                    .withAs()
                    .withColumnAlias(columnAlias)
                    .and()
                    .and();
        }

        public b$select_list $$(Expression expression, b_AS as) {
            return withItem()
                    .withExpression(expression)
                    .withAs()
                    .withColumnAlias(as.build())
                    .and()
                    .and();
        }
    }

}
