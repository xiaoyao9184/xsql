package com.xy.xsql.tsql.style.constructor.introducer.queries;

import com.xy.xsql.tsql.model.datatypes.table.ColumnName;
import com.xy.xsql.tsql.model.datatypes.table.TableName;
import com.xy.xsql.tsql.model.elements.expressions.ScalarExpression;
import com.xy.xsql.tsql.style.constructor.builder.queries.b_OUTPUT;
import com.xy.xsql.tsql.style.constructor.builder.queries.select.b_SELECT;

import static com.xy.xsql.tsql.builder.chain.queries.OutputBuilder.c_deleted;
import static com.xy.xsql.tsql.builder.chain.queries.OutputBuilder.c_inserted;

/**
 * Created by xiaoyao9184 on 2018/5/9.
 */
@SuppressWarnings({"WeakerAccess", "unused"})
public interface output {

    static b_OUTPUT OUTPUT(
            b_OUTPUT.b$dml_select_list dml_select_list,
            b_OUTPUT.b_INTO into){
        return new b_OUTPUT(){
            {
                withDmlSelect(dml_select_list.build());
                withTableName(into.build().getOutputTable());
                withColumnName(into.build().getColumnList());
                withTableVariable(into.build().getTableVariable());
            }
        };
    }
    static b_OUTPUT OUTPUT(
            b_OUTPUT.b$dml_select_list dml_select_list,
            b_OUTPUT.b_INTO into,
            b_OUTPUT output){
        return new b_OUTPUT(){
            {
                withDmlSelect(dml_select_list.build());
                withTableName(into.build().getOutputTable());
                withColumnName(into.build().getColumnList());
                withTableVariable(into.build().getTableVariable().getName());
                withOutputDmlSelect(output.build().getOutputDmlSelectList());
            }
        };
    }

    static b_OUTPUT OUTPUT(
            b_OUTPUT.b$dml_select_list dml_select_list){
        return new b_OUTPUT(){
            {
                withOutputDmlSelect(dml_select_list.build());
            }
        };
    }


    static b_OUTPUT.b$dml_select_list DELETED(
            String column_name,
            b_SELECT.b_AS as
    ){
        return new b_OUTPUT.b$dml_select_list(){
            {
                withItem()
                        .withColumnName(c_deleted(column_name))
                        .withColumnAliasIdentifier(as.build());
            }
        };
    }
    static b_OUTPUT.b$dml_select_list DELETED(
            String column_name,
            String column_alias_identifier
    ){
        return new b_OUTPUT.b$dml_select_list(){
            {
                withItem()
                        .withColumnName(c_deleted(column_name))
                        .withColumnAliasIdentifier(column_alias_identifier);
            }
        };
    }
    static b_OUTPUT.b$dml_select_list DELETED(
            String column_name
    ){
        return new b_OUTPUT.b$dml_select_list(){
            {
                withItem()
                        .withColumnName(c_deleted(column_name));
            }
        };
    }
    static b_OUTPUT.b$dml_select_list DELETED(){
        return new b_OUTPUT.b$dml_select_list(){
            {
                withItem()
                        .withColumnName(c_deleted());
            }
        };
    }

    static b_OUTPUT.b$dml_select_list INSERTED(
            String column_name,
            b_SELECT.b_AS as
    ){
        return new b_OUTPUT.b$dml_select_list(){
            {
                withItem()
                        .withColumnName(c_inserted(column_name))
                        .withColumnAliasIdentifier(as.build());
            }
        };
    }
    static b_OUTPUT.b$dml_select_list INSERTED(
            String column_name,
            String column_alias_identifier
    ){
        return new b_OUTPUT.b$dml_select_list(){
            {
                withItem()
                        .withColumnName(c_inserted(column_name))
                        .withColumnAliasIdentifier(column_alias_identifier);
            }
        };
    }
    static b_OUTPUT.b$dml_select_list INSERTED(
            String column_name
    ){
        return new b_OUTPUT.b$dml_select_list(){
            {
                withItem()
                        .withColumnName(c_inserted(column_name));
            }
        };
    }
    static b_OUTPUT.b$dml_select_list INSERTED(){
        return new b_OUTPUT.b$dml_select_list(){
            {
                withItem()
                        .withColumnName(c_inserted());
            }
        };
    }

    static b_OUTPUT.b$dml_select_list $action(){
        return new b_OUTPUT.b$dml_select_list(){
            {
                withItem()
                        .withColumnName().with$action();
            }
        };
    }

    @Deprecated
    static b_OUTPUT.b$dml_select_list $(
            b_OUTPUT.b$column_name column_name,
            b_SELECT.b_AS as
    ){
        return new b_OUTPUT.b$dml_select_list(){
            {
                withItem()
                        .withColumnName(column_name.build())
                        .withColumnAliasIdentifier(as.build());
            }
        };
    }

    @Deprecated
    static b_OUTPUT.b$dml_select_list $(
            b_OUTPUT.b$column_name column_name,
            String column_alias_identifier
    ){
        return new b_OUTPUT.b$dml_select_list(){
            {
                withItem()
                        .withColumnName(column_name.build())
                        .withColumnAliasIdentifier(column_alias_identifier);
            }
        };
    }
    @Deprecated
    static b_OUTPUT.b$dml_select_list $(
            b_OUTPUT.b$column_name column_name
    ){
        return new b_OUTPUT.b$dml_select_list(){
            {
                withItem()
                        .withColumnName(column_name.build());
            }
        };
    }

    static b_OUTPUT.b$dml_select_list $(
            ColumnName column_name,
            b_SELECT.b_AS as
    ){
        return new b_OUTPUT.b$dml_select_list(){
            {
                withItem()
                        .withColumnName()
                            .withColumnName(column_name.getName())
                            .withFromTableName(column_name.getTable().getFullName())
                            .and()
                        .withColumnAliasIdentifier(as.build());
            }
        };
    }
    static b_OUTPUT.b$dml_select_list $(
            ColumnName column_name,
            String column_alias_identifier
    ){
        return new b_OUTPUT.b$dml_select_list(){
            {
                withItem()
                        .withColumnName()
                            .withColumnName(column_name.getName())
                            .withFromTableName(column_name.getTable().getFullName())
                            .and()
                        .withColumnAliasIdentifier(column_alias_identifier);
            }
        };
    }
    static b_OUTPUT.b$dml_select_list $(
            ColumnName column_name
    ){
        return new b_OUTPUT.b$dml_select_list(){
            {
                withItem()
                        .withColumnName()
                            .withColumnName(column_name.getName())
                            .withFromTableName(column_name.getTable().getFullName())
                            .and();
            }
        };
    }
    static b_OUTPUT.b$dml_select_list $(
            ScalarExpression scalar_expression,
            b_SELECT.b_AS as
    ){
        return new b_OUTPUT.b$dml_select_list(){
            {
                withItem()
                        .withScalarExpression(scalar_expression)
                        .withColumnAliasIdentifier(as.build());
            }
        };
    }
    static b_OUTPUT.b$dml_select_list $(
            ScalarExpression scalar_expression,
            String column_alias_identifier
    ){
        return new b_OUTPUT.b$dml_select_list(){
            {
                withItem()
                        .withScalarExpression(scalar_expression)
                        .withColumnAliasIdentifier(column_alias_identifier);
            }
        };
    }
    static b_OUTPUT.b$dml_select_list $(
            ScalarExpression scalar_expression
    ){
        return new b_OUTPUT.b$dml_select_list(){
            {
                withItem()
                        .withScalarExpression(scalar_expression);
            }
        };
    }

    static b_OUTPUT.b_INTO INTO(String table_variable, ColumnName column_name){
        return new b_OUTPUT.b_INTO(){
            {
                withTableVariable(table_variable);
                withColumnName(column_name);
            }
        };
    }
    static b_OUTPUT.b_INTO INTO(TableName output_table, ColumnName column_name){
        return new b_OUTPUT.b_INTO(){
            {
                withTableName(output_table);
                withColumnName(column_name);
            }
        };
    }

    static b_OUTPUT.b_INTO INTO(String table_variable){
        return new b_OUTPUT.b_INTO(){
            {
                withTableVariable(table_variable);
            }
        };
    }
    static b_OUTPUT.b_INTO INTO(TableName output_table){
        return new b_OUTPUT.b_INTO(){
            {
                withTableName(output_table);
            }
        };
    }


//    static OUTPUT.column_name DELETED(){
//        return new OUTPUT.column_name(){
//            {
//                withDeleted();
//                withAll();
//            }
//        };
//    }
//    static OUTPUT.column_name DELETED(String column_name){
//        return new OUTPUT.column_name(){
//            {
//                withDeleted();
//                withColumnName(column_name);
//            }
//        };
//    }
//
//    static OUTPUT.column_name INSERTED(){
//        return new OUTPUT.column_name(){
//            {
//                withInserted();
//                withAll();
//            }
//        };
//    }
//    static OUTPUT.column_name INSERTED(String column_name){
//        return new OUTPUT.column_name(){
//            {
//                withInserted();
//                withColumnName(column_name);
//            }
//        };
//    }
//
//    static OUTPUT.column_name $action(){
//        return new OUTPUT.column_name(){
//            {
//                with$action();
//            }
//        };
//    }
}
