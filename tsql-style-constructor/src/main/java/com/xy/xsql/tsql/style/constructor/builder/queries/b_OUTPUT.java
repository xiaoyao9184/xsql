package com.xy.xsql.tsql.style.constructor.builder.queries;

import com.xy.xsql.tsql.builder.chain.queries.OutputBuilder;
import com.xy.xsql.tsql.model.datatypes.table.ColumnName;
import com.xy.xsql.tsql.model.elements.expressions.ScalarExpression;
import com.xy.xsql.tsql.style.constructor.builder.queries.select.b_SELECT;

/**
 * Created by xiaoyao9184 on 2015/5/25.
 */
public class b_OUTPUT extends OutputBuilder<b_OUTPUT> {

    public b_OUTPUT() {
        this.in(this);
    }


    /*
    Item
     */

    public b_OUTPUT OUTPUT(b$dml_select_list dml_select_list){
        return withOutputDmlSelect(dml_select_list.build())
                .and();
    }


    public static class b_INTO extends OutputBuilder<b_INTO> {

        public b_INTO() {
            this.in(this);
        }


        /*
        Item
         */

        public b_INTO $$(ColumnName... column_name){
            return withColumnName(column_name)
                    .and();
        }

    }

    public static class b$dml_select_list extends DmlSelectListBuilder<b$dml_select_list> {

        public b$dml_select_list() {
            this.in(this);
        }


        /*
        Item
         */

        public b$dml_select_list DELETED(
                String column_name,
                b_SELECT.b_AS as
        ){
            return withItem()
                    .withColumnName(c_deleted(column_name))
                    .withColumnAliasIdentifier(as.build())
                    .and()
                    .and();
        }
        public b$dml_select_list DELETED(
                String column_name,
                String column_alias_identifier
        ){
            return withItem()
                    .withColumnName(c_deleted(column_name))
                    .withColumnAliasIdentifier(column_alias_identifier)
                    .and()
                    .and();
        }
        public b$dml_select_list DELETED(
                String column_name
        ){
            return withItem()
                    .withColumnName(c_deleted(column_name))
                    .and()
                    .and();
        }
        public b$dml_select_list DELETED(){
            return withItem()
                    .withColumnName(c_deleted())
                    .and()
                    .and();
        }

        public b$dml_select_list INSERTED(
                String column_name,
                b_SELECT.b_AS as
        ){
            return withItem()
                    .withColumnName(c_inserted(column_name))
                    .withColumnAliasIdentifier(as.build())
                    .and()
                    .and();
        }
        public b$dml_select_list INSERTED(
                String column_name,
                String column_alias_identifier
        ){
            return withItem()
                    .withColumnName(c_inserted(column_name))
                    .withColumnAliasIdentifier(column_alias_identifier)
                    .and()
                    .and();
        }
        public b$dml_select_list INSERTED(
                String column_name
        ){
            return withItem()
                    .withColumnName(c_inserted(column_name))
                    .and()
                    .and();
        }
        public b$dml_select_list INSERTED(){
            return withItem()
                    .withColumnName(c_inserted())
                    .and()
                    .and();
        }

        public b$dml_select_list $action(){
            return withItem()
                    .withColumnName()
                        .with$action()
                        .and()
                    .and()
                    .and();
        }

        @Deprecated
        public b$dml_select_list $$(
                b$column_name column_name,
                b_SELECT.b_AS as
        ){
            return withItem()
                    .withColumnName(column_name.build())
                    .withColumnAliasIdentifier(as.build())
                    .and()
                    .and();
        }
        @Deprecated
        public b$dml_select_list $$(
                b$column_name column_name,
                String column_alias_identifier
        ){
            return withItem()
                    .withColumnName(column_name.build())
                    .withColumnAliasIdentifier(column_alias_identifier)
                    .and()
                    .and();
        }
        @Deprecated
        public b$dml_select_list $$(
                b$column_name column_name
        ){
            return withItem()
                    .withColumnName(column_name.build())
                    .and()
                    .and();
        }

        public b$dml_select_list $$(
                ColumnName column_name,
                b_SELECT.b_AS as
        ){
            return withItem()
                    .withColumnName()
                        .withColumnName(column_name.getName())
                        .withFromTableName(column_name.getTable().getFullName())
                        .and()
                    .withColumnAliasIdentifier(as.build())
                    .and()
                .and();
        }
        public b$dml_select_list $$(
                ColumnName column_name,
                String column_alias_identifier
        ){
            return withItem()
                    .withColumnName()
                        .withColumnName(column_name.getName())
                        .withFromTableName(column_name.getTable().getFullName())
                        .and()
                    .withColumnAliasIdentifier(column_alias_identifier)
                    .and()
                .and();
        }
        public b$dml_select_list $$(
                ColumnName column_name
        ){
            return withItem()
                    .withColumnName()
                        .withColumnName(column_name.getName())
                        .withFromTableName(column_name.getTable().getFullName())
                        .and()
                    .and()
                .and();
        }
        public b$dml_select_list $$(
                ScalarExpression scalar_expression,
                b_SELECT.b_AS as
        ){
            return withItem()
                    .withScalarExpression(scalar_expression)
                    .withColumnAliasIdentifier(as.build())
                    .and()
                .and();
        }
        public b$dml_select_list $$(
                ScalarExpression scalar_expression,
                String column_alias_identifier
        ){
            return withItem()
                    .withScalarExpression(scalar_expression)
                    .withColumnAliasIdentifier(column_alias_identifier)
                    .and()
                .and();
        }
        public b$dml_select_list $$(
                ScalarExpression scalar_expression
        ){
            return withItem()
                    .withScalarExpression(scalar_expression)
                    .and()
                .and();
        }
    }

    @Deprecated
    public static class b$dml_select extends DmlSelectBuilder<b$dml_select> {

        public b$dml_select() {
            this.in(this);
        }

    }

    @Deprecated
    public static class b$column_name extends ColumnNameBuilder<b$column_name> {

        public b$column_name() {
            this.in(this);
        }

    }

}
