package com.xy.xsql.tsql.style.constructor.introducer.statements;

import com.xy.xsql.core.builder.simple.CodeBuilder;
import com.xy.xsql.tsql.model.datatypes.table.ColumnName;
import com.xy.xsql.tsql.model.datatypes.table.TableName;
import com.xy.xsql.tsql.style.constructor.builder.queries.b_OUTPUT;
import com.xy.xsql.tsql.style.constructor.builder.queries.b_TOP;
import com.xy.xsql.tsql.style.constructor.builder.queries.b_VALUES;
import com.xy.xsql.tsql.style.constructor.builder.queries.hints.b$table_hint_limited;
import com.xy.xsql.tsql.style.constructor.builder.statements.b_INSERT;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Created by xiaoyao9184 on 2018/5/9.
 */
@SuppressWarnings({"WeakerAccess", "unused"})
public interface insert {

    static b_INSERT $(
            com.xy.xsql.tsql.style.constructor.builder.queries.b_WITH with_,
            b_INSERT insert){
        insert.withWith(with_.build());
        return insert;
    }
    
    //

    static b_INSERT INSERT(
            b_TOP top,
            b_INSERT.k_INTO into,
            TableName table_or_view_name,
            b$table_hint_limited.b_WITH with,
            b_INSERT.b$column_list column_list,
            b_OUTPUT output,
            b_VALUES values){
        return new b_INSERT(){
            {
                withTop(top.build());
                withInto();
                withTableName(table_or_view_name);
                withTableHint(with.build());
                withColumn(column_list.build());
                withOutput(output.build());
                withValues(values.build());
            }
        };
    }
    static b_INSERT INSERT(
            b_TOP top,
            b_INSERT.k_INTO into,
            TableName table_or_view_name,
            b$table_hint_limited.b_WITH with,
            b_INSERT.b$column_list column_list,
            b_OUTPUT output,
            b_INSERT.k_DEFAULT_VALUES default_values){
        return new b_INSERT(){
            {
                withTop(top.build());
                withInto();
                withTableName(table_or_view_name);
                withTableHint(with.build());
                withColumn(column_list.build());
                withOutput(output.build());
                withDefaultValues();
            }
        };
    }
    static b_INSERT INSERT(
            b_TOP top,
            b_INSERT.k_INTO into,
            TableName table_or_view_name,
            b$table_hint_limited.b_WITH with,
            b_INSERT.b$column_list column_list,
            b_VALUES values){
        return new b_INSERT(){
            {
                withTop(top.build());
                withInto();
                withTableName(table_or_view_name);
                withTableHint(with.build());
                withColumn(column_list.build());
                withValues(values.build());
            }
        };
    }
    static b_INSERT INSERT(
            b_TOP top,
            b_INSERT.k_INTO into,
            TableName table_or_view_name,
            b$table_hint_limited.b_WITH with,
            b_INSERT.b$column_list column_list,
            b_INSERT.k_DEFAULT_VALUES default_values){
        return new b_INSERT(){
            {
                withTop(top.build());
                withInto();
                withTableName(table_or_view_name);
                withTableHint(with.build());
                withColumn(column_list.build());
                withDefaultValues();
            }
        };
    }
    static b_INSERT INSERT(
            b_TOP top,
            b_INSERT.k_INTO into,
            TableName table_or_view_name,
            b$table_hint_limited.b_WITH with,
            b_OUTPUT output,
            b_VALUES values){
        return new b_INSERT(){
            {
                withTop(top.build());
                withInto();
                withTableName(table_or_view_name);
                withTableHint(with.build());
                withOutput(output.build());
                withValues(values.build());
            }
        };
    }
    static b_INSERT INSERT(
            b_TOP top,
            b_INSERT.k_INTO into,
            TableName table_or_view_name,
            b$table_hint_limited.b_WITH with,
            b_OUTPUT output,
            b_INSERT.k_DEFAULT_VALUES default_values){
        return new b_INSERT(){
            {
                withTop(top.build());
                withInto();
                withTableName(table_or_view_name);
                withTableHint(with.build());
                withOutput(output.build());
                withDefaultValues();
            }
        };
    }
    static b_INSERT INSERT(
            b_TOP top,
            b_INSERT.k_INTO into,
            TableName table_or_view_name,
            b$table_hint_limited.b_WITH with,
            b_VALUES values){
        return new b_INSERT(){
            {
                withTop(top.build());
                withInto();
                withTableName(table_or_view_name);
                withTableHint(with.build());
                withValues(values.build());
            }
        };
    }
    static b_INSERT INSERT(
            b_TOP top,
            b_INSERT.k_INTO into,
            TableName table_or_view_name,
            b$table_hint_limited.b_WITH with,
            b_INSERT.k_DEFAULT_VALUES default_values){
        return new b_INSERT(){
            {
                withTop(top.build());
                withInto();
                withTableName(table_or_view_name);
                withTableHint(with.build());
                withDefaultValues();
            }
        };
    }
    static b_INSERT INSERT(
            b_TOP top,
            b_INSERT.k_INTO into,
            TableName table_or_view_name,
            b_INSERT.b$column_list column_list,
            b_OUTPUT output,
            b_VALUES values){
        return new b_INSERT(){
            {
                withTop(top.build());
                withInto();
                withTableName(table_or_view_name);
                withColumn(column_list.build());
                withOutput(output.build());
                withValues(values.build());
            }
        };
    }
    static b_INSERT INSERT(
            b_TOP top,
            b_INSERT.k_INTO into,
            TableName table_or_view_name,
            b_INSERT.b$column_list column_list,
            b_OUTPUT output,
            b_INSERT.k_DEFAULT_VALUES default_values){
        return new b_INSERT(){
            {
                withTop(top.build());
                withInto();
                withTableName(table_or_view_name);
                withColumn(column_list.build());
                withOutput(output.build());
                withDefaultValues();
            }
        };
    }
    static b_INSERT INSERT(
            b_TOP top,
            b_INSERT.k_INTO into,
            TableName table_or_view_name,
            b_INSERT.b$column_list column_list,
            b_VALUES values){
        return new b_INSERT(){
            {
                withTop(top.build());
                withInto();
                withTableName(table_or_view_name);
                withColumn(column_list.build());
                withValues(values.build());
            }
        };
    }
    static b_INSERT INSERT(
            b_TOP top,
            b_INSERT.k_INTO into,
            TableName table_or_view_name,
            b_INSERT.b$column_list column_list,
            b_INSERT.k_DEFAULT_VALUES default_values){
        return new b_INSERT(){
            {
                withTop(top.build());
                withInto();
                withTableName(table_or_view_name);
                withColumn(column_list.build());
                withDefaultValues();
            }
        };
    }
    static b_INSERT INSERT(
            b_TOP top,
            b_INSERT.k_INTO into,
            TableName table_or_view_name,
            b_OUTPUT output,
            b_VALUES values){
        return new b_INSERT(){
            {
                withTop(top.build());
                withInto();
                withTableName(table_or_view_name);
                withOutput(output.build());
                withValues(values.build());
            }
        };
    }
    static b_INSERT INSERT(
            b_TOP top,
            b_INSERT.k_INTO into,
            TableName table_or_view_name,
            b_OUTPUT output,
            b_INSERT.k_DEFAULT_VALUES default_values){
        return new b_INSERT(){
            {
                withTop(top.build());
                withInto();
                withTableName(table_or_view_name);
                withOutput(output.build());
                withDefaultValues();
            }
        };
    }
    static b_INSERT INSERT(
            b_TOP top,
            b_INSERT.k_INTO into,
            TableName table_or_view_name,
            b_VALUES values){
        return new b_INSERT(){
            {
                withTop(top.build());
                withInto();
                withTableName(table_or_view_name);
                withValues(values.build());
            }
        };
    }
    static b_INSERT INSERT(
            b_TOP top,
            b_INSERT.k_INTO into,
            TableName table_or_view_name,
            b_INSERT.k_DEFAULT_VALUES default_values){
        return new b_INSERT(){
            {
                withTop(top.build());
                withInto();
                withTableName(table_or_view_name);
                withDefaultValues();
            }
        };
    }
    static b_INSERT INSERT(
            b_TOP top,
            TableName table_or_view_name,
            b$table_hint_limited.b_WITH with,
            b_INSERT.b$column_list column_list,
            b_OUTPUT output,
            b_VALUES values){
        return new b_INSERT(){
            {
                withTop(top.build());
                withTableName(table_or_view_name);
                withTableHint(with.build());
                withColumn(column_list.build());
                withOutput(output.build());
                withValues(values.build());
            }
        };
    }
    static b_INSERT INSERT(
            b_TOP top,
            TableName table_or_view_name,
            b$table_hint_limited.b_WITH with,
            b_INSERT.b$column_list column_list,
            b_OUTPUT output,
            b_INSERT.k_DEFAULT_VALUES default_values){
        return new b_INSERT(){
            {
                withTop(top.build());
                withTableName(table_or_view_name);
                withTableHint(with.build());
                withColumn(column_list.build());
                withOutput(output.build());
                withDefaultValues();
            }
        };
    }
    static b_INSERT INSERT(
            b_TOP top,
            TableName table_or_view_name,
            b$table_hint_limited.b_WITH with,
            b_INSERT.b$column_list column_list,
            b_VALUES values){
        return new b_INSERT(){
            {
                withTop(top.build());
                withTableName(table_or_view_name);
                withTableHint(with.build());
                withColumn(column_list.build());
                withValues(values.build());
            }
        };
    }
    static b_INSERT INSERT(
            b_TOP top,
            TableName table_or_view_name,
            b$table_hint_limited.b_WITH with,
            b_INSERT.b$column_list column_list,
            b_INSERT.k_DEFAULT_VALUES default_values){
        return new b_INSERT(){
            {
                withTop(top.build());
                withTableName(table_or_view_name);
                withTableHint(with.build());
                withColumn(column_list.build());
                withDefaultValues();
            }
        };
    }
    static b_INSERT INSERT(
            b_TOP top,
            TableName table_or_view_name,
            b$table_hint_limited.b_WITH with,
            b_OUTPUT output,
            b_VALUES values){
        return new b_INSERT(){
            {
                withTop(top.build());
                withTableName(table_or_view_name);
                withTableHint(with.build());
                withOutput(output.build());
                withValues(values.build());
            }
        };
    }
    static b_INSERT INSERT(
            b_TOP top,
            TableName table_or_view_name,
            b$table_hint_limited.b_WITH with,
            b_OUTPUT output,
            b_INSERT.k_DEFAULT_VALUES default_values){
        return new b_INSERT(){
            {
                withTop(top.build());
                withTableName(table_or_view_name);
                withTableHint(with.build());
                withOutput(output.build());
                withDefaultValues();
            }
        };
    }
    static b_INSERT INSERT(
            b_TOP top,
            TableName table_or_view_name,
            b$table_hint_limited.b_WITH with,
            b_VALUES values){
        return new b_INSERT(){
            {
                withTop(top.build());
                withTableName(table_or_view_name);
                withTableHint(with.build());
                withValues(values.build());
            }
        };
    }
    static b_INSERT INSERT(
            b_TOP top,
            TableName table_or_view_name,
            b$table_hint_limited.b_WITH with,
            b_INSERT.k_DEFAULT_VALUES default_values){
        return new b_INSERT(){
            {
                withTop(top.build());
                withTableName(table_or_view_name);
                withTableHint(with.build());
                withDefaultValues();
            }
        };
    }
    static b_INSERT INSERT(
            b_TOP top,
            TableName table_or_view_name,
            b_INSERT.b$column_list column_list,
            b_OUTPUT output,
            b_VALUES values){
        return new b_INSERT(){
            {
                withTop(top.build());
                withTableName(table_or_view_name);
                withColumn(column_list.build());
                withOutput(output.build());
                withValues(values.build());
            }
        };
    }
    static b_INSERT INSERT(
            b_TOP top,
            TableName table_or_view_name,
            b_INSERT.b$column_list column_list,
            b_OUTPUT output,
            b_INSERT.k_DEFAULT_VALUES default_values){
        return new b_INSERT(){
            {
                withTop(top.build());
                withTableName(table_or_view_name);
                withColumn(column_list.build());
                withOutput(output.build());
                withDefaultValues();
            }
        };
    }
    static b_INSERT INSERT(
            b_TOP top,
            TableName table_or_view_name,
            b_INSERT.b$column_list column_list,
            b_VALUES values){
        return new b_INSERT(){
            {
                withTop(top.build());
                withTableName(table_or_view_name);
                withColumn(column_list.build());
                withValues(values.build());
            }
        };
    }
    static b_INSERT INSERT(
            b_TOP top,
            TableName table_or_view_name,
            b_INSERT.b$column_list column_list,
            b_INSERT.k_DEFAULT_VALUES default_values){
        return new b_INSERT(){
            {
                withTop(top.build());
                withTableName(table_or_view_name);
                withColumn(column_list.build());
                withDefaultValues();
            }
        };
    }
    static b_INSERT INSERT(
            b_TOP top,
            TableName table_or_view_name,
            b_OUTPUT output,
            b_VALUES values){
        return new b_INSERT(){
            {
                withTop(top.build());
                withTableName(table_or_view_name);
                withOutput(output.build());
                withValues(values.build());
            }
        };
    }
    static b_INSERT INSERT(
            b_TOP top,
            TableName table_or_view_name,
            b_OUTPUT output,
            b_INSERT.k_DEFAULT_VALUES default_values){
        return new b_INSERT(){
            {
                withTop(top.build());
                withTableName(table_or_view_name);
                withOutput(output.build());
                withDefaultValues();
            }
        };
    }
    static b_INSERT INSERT(
            b_TOP top,
            TableName table_or_view_name,
            b_VALUES values){
        return new b_INSERT(){
            {
                withTop(top.build());
                withTableName(table_or_view_name);
                withValues(values.build());
            }
        };
    }
    static b_INSERT INSERT(
            b_TOP top,
            TableName table_or_view_name,
            b_INSERT.k_DEFAULT_VALUES default_values){
        return new b_INSERT(){
            {
                withTop(top.build());
                withTableName(table_or_view_name);
                withDefaultValues();
            }
        };
    }
    static b_INSERT INSERT(
            b_INSERT.k_INTO into,
            TableName table_or_view_name,
            b$table_hint_limited.b_WITH with,
            b_INSERT.b$column_list column_list,
            b_OUTPUT output,
            b_VALUES values){
        return new b_INSERT(){
            {
                withInto();
                withTableName(table_or_view_name);
                withTableHint(with.build());
                withColumn(column_list.build());
                withOutput(output.build());
                withValues(values.build());
            }
        };
    }
    static b_INSERT INSERT(
            b_INSERT.k_INTO into,
            TableName table_or_view_name,
            b$table_hint_limited.b_WITH with,
            b_INSERT.b$column_list column_list,
            b_OUTPUT output,
            b_INSERT.k_DEFAULT_VALUES default_values){
        return new b_INSERT(){
            {
                withInto();
                withTableName(table_or_view_name);
                withTableHint(with.build());
                withColumn(column_list.build());
                withOutput(output.build());
                withDefaultValues();
            }
        };
    }
    static b_INSERT INSERT(
            b_INSERT.k_INTO into,
            TableName table_or_view_name,
            b$table_hint_limited.b_WITH with,
            b_INSERT.b$column_list column_list,
            b_VALUES values){
        return new b_INSERT(){
            {
                withInto();
                withTableName(table_or_view_name);
                withTableHint(with.build());
                withColumn(column_list.build());
                withValues(values.build());
            }
        };
    }
    static b_INSERT INSERT(
            b_INSERT.k_INTO into,
            TableName table_or_view_name,
            b$table_hint_limited.b_WITH with,
            b_INSERT.b$column_list column_list,
            b_INSERT.k_DEFAULT_VALUES default_values){
        return new b_INSERT(){
            {
                withInto();
                withTableName(table_or_view_name);
                withTableHint(with.build());
                withColumn(column_list.build());
                withDefaultValues();
            }
        };
    }
    static b_INSERT INSERT(
            b_INSERT.k_INTO into,
            TableName table_or_view_name,
            b$table_hint_limited.b_WITH with,
            b_OUTPUT output,
            b_VALUES values){
        return new b_INSERT(){
            {
                withInto();
                withTableName(table_or_view_name);
                withTableHint(with.build());
                withOutput(output.build());
                withValues(values.build());
            }
        };
    }
    static b_INSERT INSERT(
            b_INSERT.k_INTO into,
            TableName table_or_view_name,
            b$table_hint_limited.b_WITH with,
            b_OUTPUT output,
            b_INSERT.k_DEFAULT_VALUES default_values){
        return new b_INSERT(){
            {
                withInto();
                withTableName(table_or_view_name);
                withTableHint(with.build());
                withOutput(output.build());
                withDefaultValues();
            }
        };
    }
    static b_INSERT INSERT(
            b_INSERT.k_INTO into,
            TableName table_or_view_name,
            b$table_hint_limited.b_WITH with,
            b_VALUES values){
        return new b_INSERT(){
            {
                withInto();
                withTableName(table_or_view_name);
                withTableHint(with.build());
                withValues(values.build());
            }
        };
    }
    static b_INSERT INSERT(
            b_INSERT.k_INTO into,
            TableName table_or_view_name,
            b$table_hint_limited.b_WITH with,
            b_INSERT.k_DEFAULT_VALUES default_values){
        return new b_INSERT(){
            {
                withInto();
                withTableName(table_or_view_name);
                withTableHint(with.build());
                withDefaultValues();
            }
        };
    }
    static b_INSERT INSERT(
            b_INSERT.k_INTO into,
            TableName table_or_view_name,
            b_INSERT.b$column_list column_list,
            b_OUTPUT output,
            b_VALUES values){
        return new b_INSERT(){
            {
                withInto();
                withTableName(table_or_view_name);
                withColumn(column_list.build());
                withOutput(output.build());
                withValues(values.build());
            }
        };
    }
    static b_INSERT INSERT(
            b_INSERT.k_INTO into,
            TableName table_or_view_name,
            b_INSERT.b$column_list column_list,
            b_OUTPUT output,
            b_INSERT.k_DEFAULT_VALUES default_values){
        return new b_INSERT(){
            {
                withInto();
                withTableName(table_or_view_name);
                withColumn(column_list.build());
                withOutput(output.build());
                withDefaultValues();
            }
        };
    }
    static b_INSERT INSERT(
            b_INSERT.k_INTO into,
            TableName table_or_view_name,
            b_INSERT.b$column_list column_list,
            b_VALUES values){
        return new b_INSERT(){
            {
                withInto();
                withTableName(table_or_view_name);
                withColumn(column_list.build());
                withValues(values.build());
            }
        };
    }
    static b_INSERT INSERT(
            b_INSERT.k_INTO into,
            TableName table_or_view_name,
            b_INSERT.b$column_list column_list,
            b_INSERT.k_DEFAULT_VALUES default_values){
        return new b_INSERT(){
            {
                withInto();
                withTableName(table_or_view_name);
                withColumn(column_list.build());
                withDefaultValues();
            }
        };
    }
    static b_INSERT INSERT(
            b_INSERT.k_INTO into,
            TableName table_or_view_name,
            b_OUTPUT output,
            b_VALUES values){
        return new b_INSERT(){
            {
                withInto();
                withTableName(table_or_view_name);
                withOutput(output.build());
                withValues(values.build());
            }
        };
    }
    static b_INSERT INSERT(
            b_INSERT.k_INTO into,
            TableName table_or_view_name,
            b_OUTPUT output,
            b_INSERT.k_DEFAULT_VALUES default_values){
        return new b_INSERT(){
            {
                withInto();
                withTableName(table_or_view_name);
                withOutput(output.build());
                withDefaultValues();
            }
        };
    }
    static b_INSERT INSERT(
            b_INSERT.k_INTO into,
            TableName table_or_view_name,
            b_VALUES values){
        return new b_INSERT(){
            {
                withInto();
                withTableName(table_or_view_name);
                withValues(values.build());
            }
        };
    }
    static b_INSERT INSERT(
            b_INSERT.k_INTO into,
            TableName table_or_view_name,
            b_INSERT.k_DEFAULT_VALUES default_values){
        return new b_INSERT(){
            {
                withInto();
                withTableName(table_or_view_name);
                withDefaultValues();
            }
        };
    }
    static b_INSERT INSERT(
            TableName table_or_view_name,
            b$table_hint_limited.b_WITH with,
            b_INSERT.b$column_list column_list,
            b_OUTPUT output,
            b_VALUES values){
        return new b_INSERT(){
            {
                withTableName(table_or_view_name);
                withTableHint(with.build());
                withColumn(column_list.build());
                withOutput(output.build());
                withValues(values.build());
            }
        };
    }
    static b_INSERT INSERT(
            TableName table_or_view_name,
            b$table_hint_limited.b_WITH with,
            b_INSERT.b$column_list column_list,
            b_OUTPUT output,
            b_INSERT.k_DEFAULT_VALUES default_values){
        return new b_INSERT(){
            {
                withTableName(table_or_view_name);
                withTableHint(with.build());
                withColumn(column_list.build());
                withOutput(output.build());
                withDefaultValues();
            }
        };
    }
    static b_INSERT INSERT(
            TableName table_or_view_name,
            b$table_hint_limited.b_WITH with,
            b_INSERT.b$column_list column_list,
            b_VALUES values){
        return new b_INSERT(){
            {
                withTableName(table_or_view_name);
                withTableHint(with.build());
                withColumn(column_list.build());
                withValues(values.build());
            }
        };
    }
    static b_INSERT INSERT(
            TableName table_or_view_name,
            b$table_hint_limited.b_WITH with,
            b_INSERT.b$column_list column_list,
            b_INSERT.k_DEFAULT_VALUES default_values){
        return new b_INSERT(){
            {
                withTableName(table_or_view_name);
                withTableHint(with.build());
                withColumn(column_list.build());
                withDefaultValues();
            }
        };
    }
    static b_INSERT INSERT(
            TableName table_or_view_name,
            b$table_hint_limited.b_WITH with,
            b_OUTPUT output,
            b_VALUES values){
        return new b_INSERT(){
            {
                withTableName(table_or_view_name);
                withTableHint(with.build());
                withOutput(output.build());
                withValues(values.build());
            }
        };
    }
    static b_INSERT INSERT(
            TableName table_or_view_name,
            b$table_hint_limited.b_WITH with,
            b_OUTPUT output,
            b_INSERT.k_DEFAULT_VALUES default_values){
        return new b_INSERT(){
            {
                withTableName(table_or_view_name);
                withTableHint(with.build());
                withOutput(output.build());
                withDefaultValues();
            }
        };
    }
    static b_INSERT INSERT(
            TableName table_or_view_name,
            b$table_hint_limited.b_WITH with,
            b_VALUES values){
        return new b_INSERT(){
            {
                withTableName(table_or_view_name);
                withTableHint(with.build());
                withValues(values.build());
            }
        };
    }
    static b_INSERT INSERT(
            TableName table_or_view_name,
            b$table_hint_limited.b_WITH with,
            b_INSERT.k_DEFAULT_VALUES default_values){
        return new b_INSERT(){
            {
                withTableName(table_or_view_name);
                withTableHint(with.build());
                withDefaultValues();
            }
        };
    }
    static b_INSERT INSERT(
            TableName table_or_view_name,
            b_INSERT.b$column_list column_list,
            b_OUTPUT output,
            b_VALUES values){
        return new b_INSERT(){
            {
                withTableName(table_or_view_name);
                withColumn(column_list.build());
                withOutput(output.build());
                withValues(values.build());
            }
        };
    }
    static b_INSERT INSERT(
            TableName table_or_view_name,
            b_INSERT.b$column_list column_list,
            b_OUTPUT output,
            b_INSERT.k_DEFAULT_VALUES default_values){
        return new b_INSERT(){
            {
                withTableName(table_or_view_name);
                withColumn(column_list.build());
                withOutput(output.build());
                withDefaultValues();
            }
        };
    }
    static b_INSERT INSERT(
            TableName table_or_view_name,
            b_INSERT.b$column_list column_list,
            b_VALUES values){
        return new b_INSERT(){
            {
                withTableName(table_or_view_name);
                withColumn(column_list.build());
                withValues(values.build());
            }
        };
    }
    static b_INSERT INSERT(
            TableName table_or_view_name,
            b_INSERT.b$column_list column_list,
            b_INSERT.k_DEFAULT_VALUES default_values){
        return new b_INSERT(){
            {
                withTableName(table_or_view_name);
                withColumn(column_list.build());
                withDefaultValues();
            }
        };
    }
    static b_INSERT INSERT(
            TableName table_or_view_name,
            b_OUTPUT output,
            b_VALUES values){
        return new b_INSERT(){
            {
                withTableName(table_or_view_name);
                withOutput(output.build());
                withValues(values.build());
            }
        };
    }
    static b_INSERT INSERT(
            TableName table_or_view_name,
            b_OUTPUT output,
            b_INSERT.k_DEFAULT_VALUES default_values){
        return new b_INSERT(){
            {
                withTableName(table_or_view_name);
                withOutput(output.build());
                withDefaultValues();
            }
        };
    }
    static b_INSERT INSERT(
            TableName table_or_view_name,
            b_VALUES values){
        return new b_INSERT(){
            {
                withTableName(table_or_view_name);
                withValues(values.build());
            }
        };
    }
    static b_INSERT INSERT(
            TableName table_or_view_name,
            b_INSERT.k_DEFAULT_VALUES default_values){
        return new b_INSERT(){
            {
                withTableName(table_or_view_name);
                withDefaultValues();
            }
        };
    }

    //

    static b_INSERT.k_INTO INTO(){
        return null;
    }

    //

    static b$table_hint_limited.b_WITH WITH(b$table_hint_limited... table_hint_limited){
        return new b$table_hint_limited.b_WITH(){
            {
                this.target = Arrays.stream(table_hint_limited)
                        .map(CodeBuilder::build)
                        .collect(Collectors.toList());
            }
        };
    }

    //

//    @Deprecated
//    static b_INSERT.b$column_list $(ColumnName columnName){
//        return new b_INSERT.b$column_list(){
//            {
//                this.target.add(columnName);
//            }
//        };
//    }

    static b_INSERT.b$column_list $(ColumnName... columnName){
        return new b_INSERT.b$column_list(){
            {
                this.target.addAll(Arrays.asList(columnName));
            }
        };
    }

    //

    static b_INSERT.k_DEFAULT_VALUES DEFAULT_VALUES(){
        return null;
    }

}
