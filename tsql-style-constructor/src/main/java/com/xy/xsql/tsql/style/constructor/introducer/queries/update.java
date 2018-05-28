package com.xy.xsql.tsql.style.constructor.introducer.queries;

import com.xy.xsql.core.builder.simple.CodeBuilder;
import com.xy.xsql.tsql.model.datatypes.table.ColumnName;
import com.xy.xsql.tsql.model.datatypes.table.TableName;
import com.xy.xsql.tsql.model.elements.expressions.Expression;
import com.xy.xsql.tsql.model.elements.operators.Compound;
import com.xy.xsql.tsql.model.elements.variables.LocalVariable;
import com.xy.xsql.tsql.style.constructor.builder.queries.hints.b$table_hint_limited;
import com.xy.xsql.tsql.style.constructor.builder.queries.*;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Created by xiaoyao9184 on 2018/5/9.
 */
@SuppressWarnings({"WeakerAccess", "unused"})
public interface update {

    static b_UPDATE $(
            b_WITH with_,
            b_UPDATE update){
        update.withWith(with_.build());
        return update;
    }

    //

    static b_UPDATE UPDATE(
            b_TOP top,
            String tableAlias,
            b$table_hint_limited.b_WITH with,
            b_UPDATE.b_SET set,
            b_OUTPUT output,
            b_FROM from,
            b_WHERE where,
            b_OPTION option){
        return new b_UPDATE(){
            {
                withTop(top.build());
                withTableAlias(tableAlias);
                withTableHint(with.build());
                withSetItem(set.build());
                withOutput(output.build());
                withFrom(from.build());
                withWhere(where.build());
                withOption(option.build());
            }
        };
    }
    static b_UPDATE UPDATE(
            b_TOP top,
            String tableAlias,
            b$table_hint_limited.b_WITH with,
            b_UPDATE.b_SET set,
            b_OUTPUT output,
            b_FROM from,
            b_WHERE where){
        return new b_UPDATE(){
            {
                withTop(top.build());
                withTableAlias(tableAlias);
                withTableHint(with.build());
                withSetItem(set.build());
                withOutput(output.build());
                withFrom(from.build());
                withWhere(where.build());
            }
        };
    }
    static b_UPDATE UPDATE(
            b_TOP top,
            String tableAlias,
            b$table_hint_limited.b_WITH with,
            b_UPDATE.b_SET set,
            b_OUTPUT output,
            b_FROM from,
            b_OPTION option){
        return new b_UPDATE(){
            {
                withTop(top.build());
                withTableAlias(tableAlias);
                withTableHint(with.build());
                withSetItem(set.build());
                withOutput(output.build());
                withFrom(from.build());
                withOption(option.build());
            }
        };
    }
    static b_UPDATE UPDATE(
            b_TOP top,
            String tableAlias,
            b$table_hint_limited.b_WITH with,
            b_UPDATE.b_SET set,
            b_OUTPUT output,
            b_FROM from){
        return new b_UPDATE(){
            {
                withTop(top.build());
                withTableAlias(tableAlias);
                withTableHint(with.build());
                withSetItem(set.build());
                withOutput(output.build());
                withFrom(from.build());
            }
        };
    }
    static b_UPDATE UPDATE(
            b_TOP top,
            String tableAlias,
            b$table_hint_limited.b_WITH with,
            b_UPDATE.b_SET set,
            b_OUTPUT output,
            b_WHERE where,
            b_OPTION option){
        return new b_UPDATE(){
            {
                withTop(top.build());
                withTableAlias(tableAlias);
                withTableHint(with.build());
                withSetItem(set.build());
                withOutput(output.build());
                withWhere(where.build());
                withOption(option.build());
            }
        };
    }
    static b_UPDATE UPDATE(
            b_TOP top,
            String tableAlias,
            b$table_hint_limited.b_WITH with,
            b_UPDATE.b_SET set,
            b_OUTPUT output,
            b_WHERE where){
        return new b_UPDATE(){
            {
                withTop(top.build());
                withTableAlias(tableAlias);
                withTableHint(with.build());
                withSetItem(set.build());
                withOutput(output.build());
                withWhere(where.build());
            }
        };
    }
    static b_UPDATE UPDATE(
            b_TOP top,
            String tableAlias,
            b$table_hint_limited.b_WITH with,
            b_UPDATE.b_SET set,
            b_OUTPUT output,
            b_OPTION option){
        return new b_UPDATE(){
            {
                withTop(top.build());
                withTableAlias(tableAlias);
                withTableHint(with.build());
                withSetItem(set.build());
                withOutput(output.build());
                withOption(option.build());
            }
        };
    }
    static b_UPDATE UPDATE(
            b_TOP top,
            String tableAlias,
            b$table_hint_limited.b_WITH with,
            b_UPDATE.b_SET set,
            b_OUTPUT output){
        return new b_UPDATE(){
            {
                withTop(top.build());
                withTableAlias(tableAlias);
                withTableHint(with.build());
                withSetItem(set.build());
                withOutput(output.build());
            }
        };
    }
    static b_UPDATE UPDATE(
            b_TOP top,
            String tableAlias,
            b$table_hint_limited.b_WITH with,
            b_UPDATE.b_SET set,
            b_FROM from,
            b_WHERE where,
            b_OPTION option){
        return new b_UPDATE(){
            {
                withTop(top.build());
                withTableAlias(tableAlias);
                withTableHint(with.build());
                withSetItem(set.build());
                withFrom(from.build());
                withWhere(where.build());
                withOption(option.build());
            }
        };
    }
    static b_UPDATE UPDATE(
            b_TOP top,
            String tableAlias,
            b$table_hint_limited.b_WITH with,
            b_UPDATE.b_SET set,
            b_FROM from,
            b_WHERE where){
        return new b_UPDATE(){
            {
                withTop(top.build());
                withTableAlias(tableAlias);
                withTableHint(with.build());
                withSetItem(set.build());
                withFrom(from.build());
                withWhere(where.build());
            }
        };
    }
    static b_UPDATE UPDATE(
            b_TOP top,
            String tableAlias,
            b$table_hint_limited.b_WITH with,
            b_UPDATE.b_SET set,
            b_FROM from,
            b_OPTION option){
        return new b_UPDATE(){
            {
                withTop(top.build());
                withTableAlias(tableAlias);
                withTableHint(with.build());
                withSetItem(set.build());
                withFrom(from.build());
                withOption(option.build());
            }
        };
    }
    static b_UPDATE UPDATE(
            b_TOP top,
            String tableAlias,
            b$table_hint_limited.b_WITH with,
            b_UPDATE.b_SET set,
            b_FROM from){
        return new b_UPDATE(){
            {
                withTop(top.build());
                withTableAlias(tableAlias);
                withTableHint(with.build());
                withSetItem(set.build());
                withFrom(from.build());
            }
        };
    }
    static b_UPDATE UPDATE(
            b_TOP top,
            String tableAlias,
            b$table_hint_limited.b_WITH with,
            b_UPDATE.b_SET set,
            b_WHERE where,
            b_OPTION option){
        return new b_UPDATE(){
            {
                withTop(top.build());
                withTableAlias(tableAlias);
                withTableHint(with.build());
                withSetItem(set.build());
                withWhere(where.build());
                withOption(option.build());
            }
        };
    }
    static b_UPDATE UPDATE(
            b_TOP top,
            String tableAlias,
            b$table_hint_limited.b_WITH with,
            b_UPDATE.b_SET set,
            b_WHERE where){
        return new b_UPDATE(){
            {
                withTop(top.build());
                withTableAlias(tableAlias);
                withTableHint(with.build());
                withSetItem(set.build());
                withWhere(where.build());
            }
        };
    }
    static b_UPDATE UPDATE(
            b_TOP top,
            String tableAlias,
            b$table_hint_limited.b_WITH with,
            b_UPDATE.b_SET set,
            b_OPTION option){
        return new b_UPDATE(){
            {
                withTop(top.build());
                withTableAlias(tableAlias);
                withTableHint(with.build());
                withSetItem(set.build());
                withOption(option.build());
            }
        };
    }
    static b_UPDATE UPDATE(
            b_TOP top,
            String tableAlias,
            b$table_hint_limited.b_WITH with,
            b_UPDATE.b_SET set){
        return new b_UPDATE(){
            {
                withTop(top.build());
                withTableAlias(tableAlias);
                withTableHint(with.build());
                withSetItem(set.build());
            }
        };
    }
    static b_UPDATE UPDATE(
            b_TOP top,
            String tableAlias,
            b_UPDATE.b_SET set,
            b_OUTPUT output,
            b_FROM from,
            b_WHERE where,
            b_OPTION option){
        return new b_UPDATE(){
            {
                withTop(top.build());
                withTableAlias(tableAlias);
                withSetItem(set.build());
                withOutput(output.build());
                withFrom(from.build());
                withWhere(where.build());
                withOption(option.build());
            }
        };
    }
    static b_UPDATE UPDATE(
            b_TOP top,
            String tableAlias,
            b_UPDATE.b_SET set,
            b_OUTPUT output,
            b_FROM from,
            b_WHERE where){
        return new b_UPDATE(){
            {
                withTop(top.build());
                withTableAlias(tableAlias);
                withSetItem(set.build());
                withOutput(output.build());
                withFrom(from.build());
                withWhere(where.build());
            }
        };
    }
    static b_UPDATE UPDATE(
            b_TOP top,
            String tableAlias,
            b_UPDATE.b_SET set,
            b_OUTPUT output,
            b_FROM from,
            b_OPTION option){
        return new b_UPDATE(){
            {
                withTop(top.build());
                withTableAlias(tableAlias);
                withSetItem(set.build());
                withOutput(output.build());
                withFrom(from.build());
                withOption(option.build());
            }
        };
    }
    static b_UPDATE UPDATE(
            b_TOP top,
            String tableAlias,
            b_UPDATE.b_SET set,
            b_OUTPUT output,
            b_FROM from){
        return new b_UPDATE(){
            {
                withTop(top.build());
                withTableAlias(tableAlias);
                withSetItem(set.build());
                withOutput(output.build());
                withFrom(from.build());
            }
        };
    }
    static b_UPDATE UPDATE(
            b_TOP top,
            String tableAlias,
            b_UPDATE.b_SET set,
            b_OUTPUT output,
            b_WHERE where,
            b_OPTION option){
        return new b_UPDATE(){
            {
                withTop(top.build());
                withTableAlias(tableAlias);
                withSetItem(set.build());
                withOutput(output.build());
                withWhere(where.build());
                withOption(option.build());
            }
        };
    }
    static b_UPDATE UPDATE(
            b_TOP top,
            String tableAlias,
            b_UPDATE.b_SET set,
            b_OUTPUT output,
            b_WHERE where){
        return new b_UPDATE(){
            {
                withTop(top.build());
                withTableAlias(tableAlias);
                withSetItem(set.build());
                withOutput(output.build());
                withWhere(where.build());
            }
        };
    }
    static b_UPDATE UPDATE(
            b_TOP top,
            String tableAlias,
            b_UPDATE.b_SET set,
            b_OUTPUT output,
            b_OPTION option){
        return new b_UPDATE(){
            {
                withTop(top.build());
                withTableAlias(tableAlias);
                withSetItem(set.build());
                withOutput(output.build());
                withOption(option.build());
            }
        };
    }
    static b_UPDATE UPDATE(
            b_TOP top,
            String tableAlias,
            b_UPDATE.b_SET set,
            b_OUTPUT output){
        return new b_UPDATE(){
            {
                withTop(top.build());
                withTableAlias(tableAlias);
                withSetItem(set.build());
                withOutput(output.build());
            }
        };
    }
    static b_UPDATE UPDATE(
            b_TOP top,
            String tableAlias,
            b_UPDATE.b_SET set,
            b_FROM from,
            b_WHERE where,
            b_OPTION option){
        return new b_UPDATE(){
            {
                withTop(top.build());
                withTableAlias(tableAlias);
                withSetItem(set.build());
                withFrom(from.build());
                withWhere(where.build());
                withOption(option.build());
            }
        };
    }
    static b_UPDATE UPDATE(
            b_TOP top,
            String tableAlias,
            b_UPDATE.b_SET set,
            b_FROM from,
            b_WHERE where){
        return new b_UPDATE(){
            {
                withTop(top.build());
                withTableAlias(tableAlias);
                withSetItem(set.build());
                withFrom(from.build());
                withWhere(where.build());
            }
        };
    }
    static b_UPDATE UPDATE(
            b_TOP top,
            String tableAlias,
            b_UPDATE.b_SET set,
            b_FROM from,
            b_OPTION option){
        return new b_UPDATE(){
            {
                withTop(top.build());
                withTableAlias(tableAlias);
                withSetItem(set.build());
                withFrom(from.build());
                withOption(option.build());
            }
        };
    }
    static b_UPDATE UPDATE(
            b_TOP top,
            String tableAlias,
            b_UPDATE.b_SET set,
            b_FROM from){
        return new b_UPDATE(){
            {
                withTop(top.build());
                withTableAlias(tableAlias);
                withSetItem(set.build());
                withFrom(from.build());
            }
        };
    }
    static b_UPDATE UPDATE(
            b_TOP top,
            String tableAlias,
            b_UPDATE.b_SET set,
            b_WHERE where,
            b_OPTION option){
        return new b_UPDATE(){
            {
                withTop(top.build());
                withTableAlias(tableAlias);
                withSetItem(set.build());
                withWhere(where.build());
                withOption(option.build());
            }
        };
    }
    static b_UPDATE UPDATE(
            b_TOP top,
            String tableAlias,
            b_UPDATE.b_SET set,
            b_WHERE where){
        return new b_UPDATE(){
            {
                withTop(top.build());
                withTableAlias(tableAlias);
                withSetItem(set.build());
                withWhere(where.build());
            }
        };
    }
    static b_UPDATE UPDATE(
            b_TOP top,
            String tableAlias,
            b_UPDATE.b_SET set,
            b_OPTION option){
        return new b_UPDATE(){
            {
                withTop(top.build());
                withTableAlias(tableAlias);
                withSetItem(set.build());
                withOption(option.build());
            }
        };
    }
    static b_UPDATE UPDATE(
            b_TOP top,
            String tableAlias,
            b_UPDATE.b_SET set){
        return new b_UPDATE(){
            {
                withTop(top.build());
                withTableAlias(tableAlias);
                withSetItem(set.build());
            }
        };
    }
    static b_UPDATE UPDATE(
            b_TOP top,
            TableName tableName,
            b$table_hint_limited.b_WITH with,
            b_UPDATE.b_SET set,
            b_OUTPUT output,
            b_FROM from,
            b_WHERE where,
            b_OPTION option){
        return new b_UPDATE(){
            {
                withTop(top.build());
                withTableName(tableName);
                withTableHint(with.build());
                withSetItem(set.build());
                withOutput(output.build());
                withFrom(from.build());
                withWhere(where.build());
                withOption(option.build());
            }
        };
    }
    static b_UPDATE UPDATE(
            b_TOP top,
            TableName tableName,
            b$table_hint_limited.b_WITH with,
            b_UPDATE.b_SET set,
            b_OUTPUT output,
            b_FROM from,
            b_WHERE where){
        return new b_UPDATE(){
            {
                withTop(top.build());
                withTableName(tableName);
                withTableHint(with.build());
                withSetItem(set.build());
                withOutput(output.build());
                withFrom(from.build());
                withWhere(where.build());
            }
        };
    }
    static b_UPDATE UPDATE(
            b_TOP top,
            TableName tableName,
            b$table_hint_limited.b_WITH with,
            b_UPDATE.b_SET set,
            b_OUTPUT output,
            b_FROM from,
            b_OPTION option){
        return new b_UPDATE(){
            {
                withTop(top.build());
                withTableName(tableName);
                withTableHint(with.build());
                withSetItem(set.build());
                withOutput(output.build());
                withFrom(from.build());
                withOption(option.build());
            }
        };
    }
    static b_UPDATE UPDATE(
            b_TOP top,
            TableName tableName,
            b$table_hint_limited.b_WITH with,
            b_UPDATE.b_SET set,
            b_OUTPUT output,
            b_FROM from){
        return new b_UPDATE(){
            {
                withTop(top.build());
                withTableName(tableName);
                withTableHint(with.build());
                withSetItem(set.build());
                withOutput(output.build());
                withFrom(from.build());
            }
        };
    }
    static b_UPDATE UPDATE(
            b_TOP top,
            TableName tableName,
            b$table_hint_limited.b_WITH with,
            b_UPDATE.b_SET set,
            b_OUTPUT output,
            b_WHERE where,
            b_OPTION option){
        return new b_UPDATE(){
            {
                withTop(top.build());
                withTableName(tableName);
                withTableHint(with.build());
                withSetItem(set.build());
                withOutput(output.build());
                withWhere(where.build());
                withOption(option.build());
            }
        };
    }
    static b_UPDATE UPDATE(
            b_TOP top,
            TableName tableName,
            b$table_hint_limited.b_WITH with,
            b_UPDATE.b_SET set,
            b_OUTPUT output,
            b_WHERE where){
        return new b_UPDATE(){
            {
                withTop(top.build());
                withTableName(tableName);
                withTableHint(with.build());
                withSetItem(set.build());
                withOutput(output.build());
                withWhere(where.build());
            }
        };
    }
    static b_UPDATE UPDATE(
            b_TOP top,
            TableName tableName,
            b$table_hint_limited.b_WITH with,
            b_UPDATE.b_SET set,
            b_OUTPUT output,
            b_OPTION option){
        return new b_UPDATE(){
            {
                withTop(top.build());
                withTableName(tableName);
                withTableHint(with.build());
                withSetItem(set.build());
                withOutput(output.build());
                withOption(option.build());
            }
        };
    }
    static b_UPDATE UPDATE(
            b_TOP top,
            TableName tableName,
            b$table_hint_limited.b_WITH with,
            b_UPDATE.b_SET set,
            b_OUTPUT output){
        return new b_UPDATE(){
            {
                withTop(top.build());
                withTableName(tableName);
                withTableHint(with.build());
                withSetItem(set.build());
                withOutput(output.build());
            }
        };
    }
    static b_UPDATE UPDATE(
            b_TOP top,
            TableName tableName,
            b$table_hint_limited.b_WITH with,
            b_UPDATE.b_SET set,
            b_FROM from,
            b_WHERE where,
            b_OPTION option){
        return new b_UPDATE(){
            {
                withTop(top.build());
                withTableName(tableName);
                withTableHint(with.build());
                withSetItem(set.build());
                withFrom(from.build());
                withWhere(where.build());
                withOption(option.build());
            }
        };
    }
    static b_UPDATE UPDATE(
            b_TOP top,
            TableName tableName,
            b$table_hint_limited.b_WITH with,
            b_UPDATE.b_SET set,
            b_FROM from,
            b_WHERE where){
        return new b_UPDATE(){
            {
                withTop(top.build());
                withTableName(tableName);
                withTableHint(with.build());
                withSetItem(set.build());
                withFrom(from.build());
                withWhere(where.build());
            }
        };
    }
    static b_UPDATE UPDATE(
            b_TOP top,
            TableName tableName,
            b$table_hint_limited.b_WITH with,
            b_UPDATE.b_SET set,
            b_FROM from,
            b_OPTION option){
        return new b_UPDATE(){
            {
                withTop(top.build());
                withTableName(tableName);
                withTableHint(with.build());
                withSetItem(set.build());
                withFrom(from.build());
                withOption(option.build());
            }
        };
    }
    static b_UPDATE UPDATE(
            b_TOP top,
            TableName tableName,
            b$table_hint_limited.b_WITH with,
            b_UPDATE.b_SET set,
            b_FROM from){
        return new b_UPDATE(){
            {
                withTop(top.build());
                withTableName(tableName);
                withTableHint(with.build());
                withSetItem(set.build());
                withFrom(from.build());
            }
        };
    }
    static b_UPDATE UPDATE(
            b_TOP top,
            TableName tableName,
            b$table_hint_limited.b_WITH with,
            b_UPDATE.b_SET set,
            b_WHERE where,
            b_OPTION option){
        return new b_UPDATE(){
            {
                withTop(top.build());
                withTableName(tableName);
                withTableHint(with.build());
                withSetItem(set.build());
                withWhere(where.build());
                withOption(option.build());
            }
        };
    }
    static b_UPDATE UPDATE(
            b_TOP top,
            TableName tableName,
            b$table_hint_limited.b_WITH with,
            b_UPDATE.b_SET set,
            b_WHERE where){
        return new b_UPDATE(){
            {
                withTop(top.build());
                withTableName(tableName);
                withTableHint(with.build());
                withSetItem(set.build());
                withWhere(where.build());
            }
        };
    }
    static b_UPDATE UPDATE(
            b_TOP top,
            TableName tableName,
            b$table_hint_limited.b_WITH with,
            b_UPDATE.b_SET set,
            b_OPTION option){
        return new b_UPDATE(){
            {
                withTop(top.build());
                withTableName(tableName);
                withTableHint(with.build());
                withSetItem(set.build());
                withOption(option.build());
            }
        };
    }
    static b_UPDATE UPDATE(
            b_TOP top,
            TableName tableName,
            b$table_hint_limited.b_WITH with,
            b_UPDATE.b_SET set){
        return new b_UPDATE(){
            {
                withTop(top.build());
                withTableName(tableName);
                withTableHint(with.build());
                withSetItem(set.build());
            }
        };
    }
    static b_UPDATE UPDATE(
            b_TOP top,
            TableName tableName,
            b_UPDATE.b_SET set,
            b_OUTPUT output,
            b_FROM from,
            b_WHERE where,
            b_OPTION option){
        return new b_UPDATE(){
            {
                withTop(top.build());
                withTableName(tableName);
                withSetItem(set.build());
                withOutput(output.build());
                withFrom(from.build());
                withWhere(where.build());
                withOption(option.build());
            }
        };
    }
    static b_UPDATE UPDATE(
            b_TOP top,
            TableName tableName,
            b_UPDATE.b_SET set,
            b_OUTPUT output,
            b_FROM from,
            b_WHERE where){
        return new b_UPDATE(){
            {
                withTop(top.build());
                withTableName(tableName);
                withSetItem(set.build());
                withOutput(output.build());
                withFrom(from.build());
                withWhere(where.build());
            }
        };
    }
    static b_UPDATE UPDATE(
            b_TOP top,
            TableName tableName,
            b_UPDATE.b_SET set,
            b_OUTPUT output,
            b_FROM from,
            b_OPTION option){
        return new b_UPDATE(){
            {
                withTop(top.build());
                withTableName(tableName);
                withSetItem(set.build());
                withOutput(output.build());
                withFrom(from.build());
                withOption(option.build());
            }
        };
    }
    static b_UPDATE UPDATE(
            b_TOP top,
            TableName tableName,
            b_UPDATE.b_SET set,
            b_OUTPUT output,
            b_FROM from){
        return new b_UPDATE(){
            {
                withTop(top.build());
                withTableName(tableName);
                withSetItem(set.build());
                withOutput(output.build());
                withFrom(from.build());
            }
        };
    }
    static b_UPDATE UPDATE(
            b_TOP top,
            TableName tableName,
            b_UPDATE.b_SET set,
            b_OUTPUT output,
            b_WHERE where,
            b_OPTION option){
        return new b_UPDATE(){
            {
                withTop(top.build());
                withTableName(tableName);
                withSetItem(set.build());
                withOutput(output.build());
                withWhere(where.build());
                withOption(option.build());
            }
        };
    }
    static b_UPDATE UPDATE(
            b_TOP top,
            TableName tableName,
            b_UPDATE.b_SET set,
            b_OUTPUT output,
            b_WHERE where){
        return new b_UPDATE(){
            {
                withTop(top.build());
                withTableName(tableName);
                withSetItem(set.build());
                withOutput(output.build());
                withWhere(where.build());
            }
        };
    }
    static b_UPDATE UPDATE(
            b_TOP top,
            TableName tableName,
            b_UPDATE.b_SET set,
            b_OUTPUT output,
            b_OPTION option){
        return new b_UPDATE(){
            {
                withTop(top.build());
                withTableName(tableName);
                withSetItem(set.build());
                withOutput(output.build());
                withOption(option.build());
            }
        };
    }
    static b_UPDATE UPDATE(
            b_TOP top,
            TableName tableName,
            b_UPDATE.b_SET set,
            b_OUTPUT output){
        return new b_UPDATE(){
            {
                withTop(top.build());
                withTableName(tableName);
                withSetItem(set.build());
                withOutput(output.build());
            }
        };
    }
    static b_UPDATE UPDATE(
            b_TOP top,
            TableName tableName,
            b_UPDATE.b_SET set,
            b_FROM from,
            b_WHERE where,
            b_OPTION option){
        return new b_UPDATE(){
            {
                withTop(top.build());
                withTableName(tableName);
                withSetItem(set.build());
                withFrom(from.build());
                withWhere(where.build());
                withOption(option.build());
            }
        };
    }
    static b_UPDATE UPDATE(
            b_TOP top,
            TableName tableName,
            b_UPDATE.b_SET set,
            b_FROM from,
            b_WHERE where){
        return new b_UPDATE(){
            {
                withTop(top.build());
                withTableName(tableName);
                withSetItem(set.build());
                withFrom(from.build());
                withWhere(where.build());
            }
        };
    }
    static b_UPDATE UPDATE(
            b_TOP top,
            TableName tableName,
            b_UPDATE.b_SET set,
            b_FROM from,
            b_OPTION option){
        return new b_UPDATE(){
            {
                withTop(top.build());
                withTableName(tableName);
                withSetItem(set.build());
                withFrom(from.build());
                withOption(option.build());
            }
        };
    }
    static b_UPDATE UPDATE(
            b_TOP top,
            TableName tableName,
            b_UPDATE.b_SET set,
            b_FROM from){
        return new b_UPDATE(){
            {
                withTop(top.build());
                withTableName(tableName);
                withSetItem(set.build());
                withFrom(from.build());
            }
        };
    }
    static b_UPDATE UPDATE(
            b_TOP top,
            TableName tableName,
            b_UPDATE.b_SET set,
            b_WHERE where,
            b_OPTION option){
        return new b_UPDATE(){
            {
                withTop(top.build());
                withTableName(tableName);
                withSetItem(set.build());
                withWhere(where.build());
                withOption(option.build());
            }
        };
    }
    static b_UPDATE UPDATE(
            b_TOP top,
            TableName tableName,
            b_UPDATE.b_SET set,
            b_WHERE where){
        return new b_UPDATE(){
            {
                withTop(top.build());
                withTableName(tableName);
                withSetItem(set.build());
                withWhere(where.build());
            }
        };
    }
    static b_UPDATE UPDATE(
            b_TOP top,
            TableName tableName,
            b_UPDATE.b_SET set,
            b_OPTION option){
        return new b_UPDATE(){
            {
                withTop(top.build());
                withTableName(tableName);
                withSetItem(set.build());
                withOption(option.build());
            }
        };
    }
    static b_UPDATE UPDATE(
            b_TOP top,
            TableName tableName,
            b_UPDATE.b_SET set){
        return new b_UPDATE(){
            {
                withTop(top.build());
                withTableName(tableName);
                withSetItem(set.build());
            }
        };
    }
    static b_UPDATE UPDATE(
            String tableAlias,
            b$table_hint_limited.b_WITH with,
            b_UPDATE.b_SET set,
            b_OUTPUT output,
            b_FROM from,
            b_WHERE where,
            b_OPTION option){
        return new b_UPDATE(){
            {
                withTableAlias(tableAlias);
                withTableHint(with.build());
                withSetItem(set.build());
                withOutput(output.build());
                withFrom(from.build());
                withWhere(where.build());
                withOption(option.build());
            }
        };
    }
    static b_UPDATE UPDATE(
            String tableAlias,
            b$table_hint_limited.b_WITH with,
            b_UPDATE.b_SET set,
            b_OUTPUT output,
            b_FROM from,
            b_WHERE where){
        return new b_UPDATE(){
            {
                withTableAlias(tableAlias);
                withTableHint(with.build());
                withSetItem(set.build());
                withOutput(output.build());
                withFrom(from.build());
                withWhere(where.build());
            }
        };
    }
    static b_UPDATE UPDATE(
            String tableAlias,
            b$table_hint_limited.b_WITH with,
            b_UPDATE.b_SET set,
            b_OUTPUT output,
            b_FROM from,
            b_OPTION option){
        return new b_UPDATE(){
            {
                withTableAlias(tableAlias);
                withTableHint(with.build());
                withSetItem(set.build());
                withOutput(output.build());
                withFrom(from.build());
                withOption(option.build());
            }
        };
    }
    static b_UPDATE UPDATE(
            String tableAlias,
            b$table_hint_limited.b_WITH with,
            b_UPDATE.b_SET set,
            b_OUTPUT output,
            b_FROM from){
        return new b_UPDATE(){
            {
                withTableAlias(tableAlias);
                withTableHint(with.build());
                withSetItem(set.build());
                withOutput(output.build());
                withFrom(from.build());
            }
        };
    }
    static b_UPDATE UPDATE(
            String tableAlias,
            b$table_hint_limited.b_WITH with,
            b_UPDATE.b_SET set,
            b_OUTPUT output,
            b_WHERE where,
            b_OPTION option){
        return new b_UPDATE(){
            {
                withTableAlias(tableAlias);
                withTableHint(with.build());
                withSetItem(set.build());
                withOutput(output.build());
                withWhere(where.build());
                withOption(option.build());
            }
        };
    }
    static b_UPDATE UPDATE(
            String tableAlias,
            b$table_hint_limited.b_WITH with,
            b_UPDATE.b_SET set,
            b_OUTPUT output,
            b_WHERE where){
        return new b_UPDATE(){
            {
                withTableAlias(tableAlias);
                withTableHint(with.build());
                withSetItem(set.build());
                withOutput(output.build());
                withWhere(where.build());
            }
        };
    }
    static b_UPDATE UPDATE(
            String tableAlias,
            b$table_hint_limited.b_WITH with,
            b_UPDATE.b_SET set,
            b_OUTPUT output,
            b_OPTION option){
        return new b_UPDATE(){
            {
                withTableAlias(tableAlias);
                withTableHint(with.build());
                withSetItem(set.build());
                withOutput(output.build());
                withOption(option.build());
            }
        };
    }
    static b_UPDATE UPDATE(
            String tableAlias,
            b$table_hint_limited.b_WITH with,
            b_UPDATE.b_SET set,
            b_OUTPUT output){
        return new b_UPDATE(){
            {
                withTableAlias(tableAlias);
                withTableHint(with.build());
                withSetItem(set.build());
                withOutput(output.build());
            }
        };
    }
    static b_UPDATE UPDATE(
            String tableAlias,
            b$table_hint_limited.b_WITH with,
            b_UPDATE.b_SET set,
            b_FROM from,
            b_WHERE where,
            b_OPTION option){
        return new b_UPDATE(){
            {
                withTableAlias(tableAlias);
                withTableHint(with.build());
                withSetItem(set.build());
                withFrom(from.build());
                withWhere(where.build());
                withOption(option.build());
            }
        };
    }
    static b_UPDATE UPDATE(
            String tableAlias,
            b$table_hint_limited.b_WITH with,
            b_UPDATE.b_SET set,
            b_FROM from,
            b_WHERE where){
        return new b_UPDATE(){
            {
                withTableAlias(tableAlias);
                withTableHint(with.build());
                withSetItem(set.build());
                withFrom(from.build());
                withWhere(where.build());
            }
        };
    }
    static b_UPDATE UPDATE(
            String tableAlias,
            b$table_hint_limited.b_WITH with,
            b_UPDATE.b_SET set,
            b_FROM from,
            b_OPTION option){
        return new b_UPDATE(){
            {
                withTableAlias(tableAlias);
                withTableHint(with.build());
                withSetItem(set.build());
                withFrom(from.build());
                withOption(option.build());
            }
        };
    }
    static b_UPDATE UPDATE(
            String tableAlias,
            b$table_hint_limited.b_WITH with,
            b_UPDATE.b_SET set,
            b_FROM from){
        return new b_UPDATE(){
            {
                withTableAlias(tableAlias);
                withTableHint(with.build());
                withSetItem(set.build());
                withFrom(from.build());
            }
        };
    }
    static b_UPDATE UPDATE(
            String tableAlias,
            b$table_hint_limited.b_WITH with,
            b_UPDATE.b_SET set,
            b_WHERE where,
            b_OPTION option){
        return new b_UPDATE(){
            {
                withTableAlias(tableAlias);
                withTableHint(with.build());
                withSetItem(set.build());
                withWhere(where.build());
                withOption(option.build());
            }
        };
    }
    static b_UPDATE UPDATE(
            String tableAlias,
            b$table_hint_limited.b_WITH with,
            b_UPDATE.b_SET set,
            b_WHERE where){
        return new b_UPDATE(){
            {
                withTableAlias(tableAlias);
                withTableHint(with.build());
                withSetItem(set.build());
                withWhere(where.build());
            }
        };
    }
    static b_UPDATE UPDATE(
            String tableAlias,
            b$table_hint_limited.b_WITH with,
            b_UPDATE.b_SET set,
            b_OPTION option){
        return new b_UPDATE(){
            {
                withTableAlias(tableAlias);
                withTableHint(with.build());
                withSetItem(set.build());
                withOption(option.build());
            }
        };
    }
    static b_UPDATE UPDATE(
            String tableAlias,
            b$table_hint_limited.b_WITH with,
            b_UPDATE.b_SET set){
        return new b_UPDATE(){
            {
                withTableAlias(tableAlias);
                withTableHint(with.build());
                withSetItem(set.build());
            }
        };
    }
    static b_UPDATE UPDATE(
            String tableAlias,
            b_UPDATE.b_SET set,
            b_OUTPUT output,
            b_FROM from,
            b_WHERE where,
            b_OPTION option){
        return new b_UPDATE(){
            {
                withTableAlias(tableAlias);
                withSetItem(set.build());
                withOutput(output.build());
                withFrom(from.build());
                withWhere(where.build());
                withOption(option.build());
            }
        };
    }
    static b_UPDATE UPDATE(
            String tableAlias,
            b_UPDATE.b_SET set,
            b_OUTPUT output,
            b_FROM from,
            b_WHERE where){
        return new b_UPDATE(){
            {
                withTableAlias(tableAlias);
                withSetItem(set.build());
                withOutput(output.build());
                withFrom(from.build());
                withWhere(where.build());
            }
        };
    }
    static b_UPDATE UPDATE(
            String tableAlias,
            b_UPDATE.b_SET set,
            b_OUTPUT output,
            b_FROM from,
            b_OPTION option){
        return new b_UPDATE(){
            {
                withTableAlias(tableAlias);
                withSetItem(set.build());
                withOutput(output.build());
                withFrom(from.build());
                withOption(option.build());
            }
        };
    }
    static b_UPDATE UPDATE(
            String tableAlias,
            b_UPDATE.b_SET set,
            b_OUTPUT output,
            b_FROM from){
        return new b_UPDATE(){
            {
                withTableAlias(tableAlias);
                withSetItem(set.build());
                withOutput(output.build());
                withFrom(from.build());
            }
        };
    }
    static b_UPDATE UPDATE(
            String tableAlias,
            b_UPDATE.b_SET set,
            b_OUTPUT output,
            b_WHERE where,
            b_OPTION option){
        return new b_UPDATE(){
            {
                withTableAlias(tableAlias);
                withSetItem(set.build());
                withOutput(output.build());
                withWhere(where.build());
                withOption(option.build());
            }
        };
    }
    static b_UPDATE UPDATE(
            String tableAlias,
            b_UPDATE.b_SET set,
            b_OUTPUT output,
            b_WHERE where){
        return new b_UPDATE(){
            {
                withTableAlias(tableAlias);
                withSetItem(set.build());
                withOutput(output.build());
                withWhere(where.build());
            }
        };
    }
    static b_UPDATE UPDATE(
            String tableAlias,
            b_UPDATE.b_SET set,
            b_OUTPUT output,
            b_OPTION option){
        return new b_UPDATE(){
            {
                withTableAlias(tableAlias);
                withSetItem(set.build());
                withOutput(output.build());
                withOption(option.build());
            }
        };
    }
    static b_UPDATE UPDATE(
            String tableAlias,
            b_UPDATE.b_SET set,
            b_OUTPUT output){
        return new b_UPDATE(){
            {
                withTableAlias(tableAlias);
                withSetItem(set.build());
                withOutput(output.build());
            }
        };
    }
    static b_UPDATE UPDATE(
            String tableAlias,
            b_UPDATE.b_SET set,
            b_FROM from,
            b_WHERE where,
            b_OPTION option){
        return new b_UPDATE(){
            {
                withTableAlias(tableAlias);
                withSetItem(set.build());
                withFrom(from.build());
                withWhere(where.build());
                withOption(option.build());
            }
        };
    }
    static b_UPDATE UPDATE(
            String tableAlias,
            b_UPDATE.b_SET set,
            b_FROM from,
            b_WHERE where){
        return new b_UPDATE(){
            {
                withTableAlias(tableAlias);
                withSetItem(set.build());
                withFrom(from.build());
                withWhere(where.build());
            }
        };
    }
    static b_UPDATE UPDATE(
            String tableAlias,
            b_UPDATE.b_SET set,
            b_FROM from,
            b_OPTION option){
        return new b_UPDATE(){
            {
                withTableAlias(tableAlias);
                withSetItem(set.build());
                withFrom(from.build());
                withOption(option.build());
            }
        };
    }
    static b_UPDATE UPDATE(
            String tableAlias,
            b_UPDATE.b_SET set,
            b_FROM from){
        return new b_UPDATE(){
            {
                withTableAlias(tableAlias);
                withSetItem(set.build());
                withFrom(from.build());
            }
        };
    }
    static b_UPDATE UPDATE(
            String tableAlias,
            b_UPDATE.b_SET set,
            b_WHERE where,
            b_OPTION option){
        return new b_UPDATE(){
            {
                withTableAlias(tableAlias);
                withSetItem(set.build());
                withWhere(where.build());
                withOption(option.build());
            }
        };
    }
    static b_UPDATE UPDATE(
            String tableAlias,
            b_UPDATE.b_SET set,
            b_WHERE where){
        return new b_UPDATE(){
            {
                withTableAlias(tableAlias);
                withSetItem(set.build());
                withWhere(where.build());
            }
        };
    }
    static b_UPDATE UPDATE(
            String tableAlias,
            b_UPDATE.b_SET set,
            b_OPTION option){
        return new b_UPDATE(){
            {
                withTableAlias(tableAlias);
                withSetItem(set.build());
                withOption(option.build());
            }
        };
    }
    static b_UPDATE UPDATE(
            String tableAlias,
            b_UPDATE.b_SET set){
        return new b_UPDATE(){
            {
                withTableAlias(tableAlias);
                withSetItem(set.build());
            }
        };
    }
    static b_UPDATE UPDATE(
            TableName tableName,
            b$table_hint_limited.b_WITH with,
            b_UPDATE.b_SET set,
            b_OUTPUT output,
            b_FROM from,
            b_WHERE where,
            b_OPTION option){
        return new b_UPDATE(){
            {
                withTableName(tableName);
                withTableHint(with.build());
                withSetItem(set.build());
                withOutput(output.build());
                withFrom(from.build());
                withWhere(where.build());
                withOption(option.build());
            }
        };
    }
    static b_UPDATE UPDATE(
            TableName tableName,
            b$table_hint_limited.b_WITH with,
            b_UPDATE.b_SET set,
            b_OUTPUT output,
            b_FROM from,
            b_WHERE where){
        return new b_UPDATE(){
            {
                withTableName(tableName);
                withTableHint(with.build());
                withSetItem(set.build());
                withOutput(output.build());
                withFrom(from.build());
                withWhere(where.build());
            }
        };
    }
    static b_UPDATE UPDATE(
            TableName tableName,
            b$table_hint_limited.b_WITH with,
            b_UPDATE.b_SET set,
            b_OUTPUT output,
            b_FROM from,
            b_OPTION option){
        return new b_UPDATE(){
            {
                withTableName(tableName);
                withTableHint(with.build());
                withSetItem(set.build());
                withOutput(output.build());
                withFrom(from.build());
                withOption(option.build());
            }
        };
    }
    static b_UPDATE UPDATE(
            TableName tableName,
            b$table_hint_limited.b_WITH with,
            b_UPDATE.b_SET set,
            b_OUTPUT output,
            b_FROM from){
        return new b_UPDATE(){
            {
                withTableName(tableName);
                withTableHint(with.build());
                withSetItem(set.build());
                withOutput(output.build());
                withFrom(from.build());
            }
        };
    }
    static b_UPDATE UPDATE(
            TableName tableName,
            b$table_hint_limited.b_WITH with,
            b_UPDATE.b_SET set,
            b_OUTPUT output,
            b_WHERE where,
            b_OPTION option){
        return new b_UPDATE(){
            {
                withTableName(tableName);
                withTableHint(with.build());
                withSetItem(set.build());
                withOutput(output.build());
                withWhere(where.build());
                withOption(option.build());
            }
        };
    }
    static b_UPDATE UPDATE(
            TableName tableName,
            b$table_hint_limited.b_WITH with,
            b_UPDATE.b_SET set,
            b_OUTPUT output,
            b_WHERE where){
        return new b_UPDATE(){
            {
                withTableName(tableName);
                withTableHint(with.build());
                withSetItem(set.build());
                withOutput(output.build());
                withWhere(where.build());
            }
        };
    }
    static b_UPDATE UPDATE(
            TableName tableName,
            b$table_hint_limited.b_WITH with,
            b_UPDATE.b_SET set,
            b_OUTPUT output,
            b_OPTION option){
        return new b_UPDATE(){
            {
                withTableName(tableName);
                withTableHint(with.build());
                withSetItem(set.build());
                withOutput(output.build());
                withOption(option.build());
            }
        };
    }
    static b_UPDATE UPDATE(
            TableName tableName,
            b$table_hint_limited.b_WITH with,
            b_UPDATE.b_SET set,
            b_OUTPUT output){
        return new b_UPDATE(){
            {
                withTableName(tableName);
                withTableHint(with.build());
                withSetItem(set.build());
                withOutput(output.build());
            }
        };
    }
    static b_UPDATE UPDATE(
            TableName tableName,
            b$table_hint_limited.b_WITH with,
            b_UPDATE.b_SET set,
            b_FROM from,
            b_WHERE where,
            b_OPTION option){
        return new b_UPDATE(){
            {
                withTableName(tableName);
                withTableHint(with.build());
                withSetItem(set.build());
                withFrom(from.build());
                withWhere(where.build());
                withOption(option.build());
            }
        };
    }
    static b_UPDATE UPDATE(
            TableName tableName,
            b$table_hint_limited.b_WITH with,
            b_UPDATE.b_SET set,
            b_FROM from,
            b_WHERE where){
        return new b_UPDATE(){
            {
                withTableName(tableName);
                withTableHint(with.build());
                withSetItem(set.build());
                withFrom(from.build());
                withWhere(where.build());
            }
        };
    }
    static b_UPDATE UPDATE(
            TableName tableName,
            b$table_hint_limited.b_WITH with,
            b_UPDATE.b_SET set,
            b_FROM from,
            b_OPTION option){
        return new b_UPDATE(){
            {
                withTableName(tableName);
                withTableHint(with.build());
                withSetItem(set.build());
                withFrom(from.build());
                withOption(option.build());
            }
        };
    }
    static b_UPDATE UPDATE(
            TableName tableName,
            b$table_hint_limited.b_WITH with,
            b_UPDATE.b_SET set,
            b_FROM from){
        return new b_UPDATE(){
            {
                withTableName(tableName);
                withTableHint(with.build());
                withSetItem(set.build());
                withFrom(from.build());
            }
        };
    }
    static b_UPDATE UPDATE(
            TableName tableName,
            b$table_hint_limited.b_WITH with,
            b_UPDATE.b_SET set,
            b_WHERE where,
            b_OPTION option){
        return new b_UPDATE(){
            {
                withTableName(tableName);
                withTableHint(with.build());
                withSetItem(set.build());
                withWhere(where.build());
                withOption(option.build());
            }
        };
    }
    static b_UPDATE UPDATE(
            TableName tableName,
            b$table_hint_limited.b_WITH with,
            b_UPDATE.b_SET set,
            b_WHERE where){
        return new b_UPDATE(){
            {
                withTableName(tableName);
                withTableHint(with.build());
                withSetItem(set.build());
                withWhere(where.build());
            }
        };
    }
    static b_UPDATE UPDATE(
            TableName tableName,
            b$table_hint_limited.b_WITH with,
            b_UPDATE.b_SET set,
            b_OPTION option){
        return new b_UPDATE(){
            {
                withTableName(tableName);
                withTableHint(with.build());
                withSetItem(set.build());
                withOption(option.build());
            }
        };
    }
    static b_UPDATE UPDATE(
            TableName tableName,
            b$table_hint_limited.b_WITH with,
            b_UPDATE.b_SET set){
        return new b_UPDATE(){
            {
                withTableName(tableName);
                withTableHint(with.build());
                withSetItem(set.build());
            }
        };
    }
    static b_UPDATE UPDATE(
            TableName tableName,
            b_UPDATE.b_SET set,
            b_OUTPUT output,
            b_FROM from,
            b_WHERE where,
            b_OPTION option){
        return new b_UPDATE(){
            {
                withTableName(tableName);
                withSetItem(set.build());
                withOutput(output.build());
                withFrom(from.build());
                withWhere(where.build());
                withOption(option.build());
            }
        };
    }
    static b_UPDATE UPDATE(
            TableName tableName,
            b_UPDATE.b_SET set,
            b_OUTPUT output,
            b_FROM from,
            b_WHERE where){
        return new b_UPDATE(){
            {
                withTableName(tableName);
                withSetItem(set.build());
                withOutput(output.build());
                withFrom(from.build());
                withWhere(where.build());
            }
        };
    }
    static b_UPDATE UPDATE(
            TableName tableName,
            b_UPDATE.b_SET set,
            b_OUTPUT output,
            b_FROM from,
            b_OPTION option){
        return new b_UPDATE(){
            {
                withTableName(tableName);
                withSetItem(set.build());
                withOutput(output.build());
                withFrom(from.build());
                withOption(option.build());
            }
        };
    }
    static b_UPDATE UPDATE(
            TableName tableName,
            b_UPDATE.b_SET set,
            b_OUTPUT output,
            b_FROM from){
        return new b_UPDATE(){
            {
                withTableName(tableName);
                withSetItem(set.build());
                withOutput(output.build());
                withFrom(from.build());
            }
        };
    }
    static b_UPDATE UPDATE(
            TableName tableName,
            b_UPDATE.b_SET set,
            b_OUTPUT output,
            b_WHERE where,
            b_OPTION option){
        return new b_UPDATE(){
            {
                withTableName(tableName);
                withSetItem(set.build());
                withOutput(output.build());
                withWhere(where.build());
                withOption(option.build());
            }
        };
    }
    static b_UPDATE UPDATE(
            TableName tableName,
            b_UPDATE.b_SET set,
            b_OUTPUT output,
            b_WHERE where){
        return new b_UPDATE(){
            {
                withTableName(tableName);
                withSetItem(set.build());
                withOutput(output.build());
                withWhere(where.build());
            }
        };
    }
    static b_UPDATE UPDATE(
            TableName tableName,
            b_UPDATE.b_SET set,
            b_OUTPUT output,
            b_OPTION option){
        return new b_UPDATE(){
            {
                withTableName(tableName);
                withSetItem(set.build());
                withOutput(output.build());
                withOption(option.build());
            }
        };
    }
    static b_UPDATE UPDATE(
            TableName tableName,
            b_UPDATE.b_SET set,
            b_OUTPUT output){
        return new b_UPDATE(){
            {
                withTableName(tableName);
                withSetItem(set.build());
                withOutput(output.build());
            }
        };
    }
    static b_UPDATE UPDATE(
            TableName tableName,
            b_UPDATE.b_SET set,
            b_FROM from,
            b_WHERE where,
            b_OPTION option){
        return new b_UPDATE(){
            {
                withTableName(tableName);
                withSetItem(set.build());
                withFrom(from.build());
                withWhere(where.build());
                withOption(option.build());
            }
        };
    }
    static b_UPDATE UPDATE(
            TableName tableName,
            b_UPDATE.b_SET set,
            b_FROM from,
            b_WHERE where){
        return new b_UPDATE(){
            {
                withTableName(tableName);
                withSetItem(set.build());
                withFrom(from.build());
                withWhere(where.build());
            }
        };
    }
    static b_UPDATE UPDATE(
            TableName tableName,
            b_UPDATE.b_SET set,
            b_FROM from,
            b_OPTION option){
        return new b_UPDATE(){
            {
                withTableName(tableName);
                withSetItem(set.build());
                withFrom(from.build());
                withOption(option.build());
            }
        };
    }
    static b_UPDATE UPDATE(
            TableName tableName,
            b_UPDATE.b_SET set,
            b_FROM from){
        return new b_UPDATE(){
            {
                withTableName(tableName);
                withSetItem(set.build());
                withFrom(from.build());
            }
        };
    }
    static b_UPDATE UPDATE(
            TableName tableName,
            b_UPDATE.b_SET set,
            b_WHERE where,
            b_OPTION option){
        return new b_UPDATE(){
            {
                withTableName(tableName);
                withSetItem(set.build());
                withWhere(where.build());
                withOption(option.build());
            }
        };
    }
    static b_UPDATE UPDATE(
            TableName tableName,
            b_UPDATE.b_SET set,
            b_WHERE where){
        return new b_UPDATE(){
            {
                withTableName(tableName);
                withSetItem(set.build());
                withWhere(where.build());
            }
        };
    }
    static b_UPDATE UPDATE(
            TableName tableName,
            b_UPDATE.b_SET set,
            b_OPTION option){
        return new b_UPDATE(){
            {
                withTableName(tableName);
                withSetItem(set.build());
                withOption(option.build());
            }
        };
    }
    static b_UPDATE UPDATE(
            TableName tableName,
            b_UPDATE.b_SET set){
        return new b_UPDATE(){
            {
                withTableName(tableName);
                withSetItem(set.build());
            }
        };
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

    static b_UPDATE.b_SET SET(ColumnName column_name, Expression expression){
        return new b_UPDATE.b_SET(){
            {
                withItem()._ColumnAssignment()
                        .withColumnName(column_name)
                        .withExpression(expression);
            }
        };
    }
    static b_UPDATE.b_SET SET(ColumnName column_name, b_UPDATE.k_DEFAULT default_){
        return new b_UPDATE.b_SET(){
            {
                withItem()._ColumnAssignment()
                        .withColumnName(column_name)
                        .withUseDefault(true);
            }
        };
    }
    static b_UPDATE.b_SET SET(ColumnName column_name, b_UPDATE.k_NULL null_){
        return new b_UPDATE.b_SET(){
            {
                withItem()._ColumnAssignment()
                        .withColumnName(column_name)
                        .withUseNull(true);
            }
        };
    }
    static b_UPDATE.b_SET SET(LocalVariable variable, Expression expression){
        return new b_UPDATE.b_SET(){
            {
                withItem()._VariableAssignment()
                        .withVariable(variable)
                        .withExpression(expression);
            }
        };
    }
    static b_UPDATE.b_SET SET(LocalVariable variable, ColumnName column_name, Expression expression){
        return new b_UPDATE.b_SET(){
            {
                withItem()._VariableColumnAssignment()
                        .withVariable(variable)
                        .withColumnName(column_name)
                        .withExpression(expression);
            }
        };
    }
    static b_UPDATE.b_SET SET(ColumnName column_name, Compound compound, Expression expression){
        return new b_UPDATE.b_SET(){
            {
                withItem()._ColumnCompound()
                        .withColumnName(column_name)
                        .withCompound(compound)
                        .withExpression(expression);
            }
        };
    }
    static b_UPDATE.b_SET SET(LocalVariable variable, Compound compound, Expression expression){
        return new b_UPDATE.b_SET(){
            {
                withItem()._VariableCompound()
                        .withVariable(variable)
                        .withCompound(compound)
                        .withExpression(expression);
            }
        };
    }
    static b_UPDATE.b_SET SET(LocalVariable variable, ColumnName column_name, Compound compound, Expression expression){
        return new b_UPDATE.b_SET(){
            {
                withItem()._VariableColumnCompound()
                        .withVariable(variable)
                        .withColumnName(column_name)
                        .withCompound(compound)
                        .withExpression(expression);
            }
        };
    }

    //

    static b_UPDATE.k_DEFAULT DEFAULT(){
        return null;
    }
    static b_UPDATE.k_NULL NULL(){
        return new b_UPDATE.k_NULL();
    }

}
