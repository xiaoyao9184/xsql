package com.xy.xsql.tsql.style.constructor.introducer.statements;

import com.xy.xsql.core.builder.simple.CodeBuilder;
import com.xy.xsql.tsql.model.datatypes.table.TableName;
import com.xy.xsql.tsql.style.constructor.builder.queries.hints.b$table_hint_limited;
import com.xy.xsql.tsql.style.constructor.builder.queries.*;
import com.xy.xsql.tsql.style.constructor.builder.statements.b_DELETE;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Created by xiaoyao9184 on 2018/5/9.
 */
@SuppressWarnings({"WeakerAccess", "unused"})
public interface delete {

    static b_DELETE $(
            b_WITH with_,
            b_DELETE delete){
        delete.withWith(with_.build());
        return delete;
    }
    
    //

    static b_DELETE DELETE(
            b_TOP top,
            b_DELETE.k_FROM from_,
            String table_alias,
            b$table_hint_limited.b_WITH with,
            b_OUTPUT output,
            b_FROM from,
            b_WHERE where,
            b_OPTION option){
        return new b_DELETE(){
            {
                withTop(top.build());
                withTableAlias(table_alias);
                withTableHint(with.build());
                withOutput(output.build());
                withFrom(from.build());
                withWhere(where.build());
                withOption(option.build());
            }
        };
    }
    static b_DELETE DELETE(
            b_TOP top,
            b_DELETE.k_FROM from_,
            String table_alias,
            b$table_hint_limited.b_WITH with,
            b_OUTPUT output,
            b_FROM from,
            b_WHERE where){
        return new b_DELETE(){
            {
                withTop(top.build());
                withFrom(true);
                withTableAlias(table_alias);
                withTableHint(with.build());
                withOutput(output.build());
                withFrom(from.build());
                withWhere(where.build());
            }
        };
    }
    static b_DELETE DELETE(
            b_TOP top,
            b_DELETE.k_FROM from_,
            String table_alias,
            b$table_hint_limited.b_WITH with,
            b_OUTPUT output,
            b_FROM from,
            b_OPTION option){
        return new b_DELETE(){
            {
                withTop(top.build());
                withFrom(true);
                withTableAlias(table_alias);
                withTableHint(with.build());
                withOutput(output.build());
                withFrom(from.build());
                withOption(option.build());
            }
        };
    }
    static b_DELETE DELETE(
            b_TOP top,
            b_DELETE.k_FROM from_,
            String table_alias,
            b$table_hint_limited.b_WITH with,
            b_OUTPUT output,
            b_FROM from){
        return new b_DELETE(){
            {
                withTop(top.build());
                withFrom(true);
                withTableAlias(table_alias);
                withTableHint(with.build());
                withOutput(output.build());
                withFrom(from.build());
            }
        };
    }
    static b_DELETE DELETE(
            b_TOP top,
            b_DELETE.k_FROM from_,
            String table_alias,
            b$table_hint_limited.b_WITH with,
            b_OUTPUT output,
            b_WHERE where,
            b_OPTION option){
        return new b_DELETE(){
            {
                withTop(top.build());
                withFrom(true);
                withTableAlias(table_alias);
                withTableHint(with.build());
                withOutput(output.build());
                withWhere(where.build());
                withOption(option.build());
            }
        };
    }
    static b_DELETE DELETE(
            b_TOP top,
            b_DELETE.k_FROM from_,
            String table_alias,
            b$table_hint_limited.b_WITH with,
            b_OUTPUT output,
            b_WHERE where){
        return new b_DELETE(){
            {
                withTop(top.build());
                withFrom(true);
                withTableAlias(table_alias);
                withTableHint(with.build());
                withOutput(output.build());
                withWhere(where.build());
            }
        };
    }
    static b_DELETE DELETE(
            b_TOP top,
            b_DELETE.k_FROM from_,
            String table_alias,
            b$table_hint_limited.b_WITH with,
            b_OUTPUT output,
            b_OPTION option){
        return new b_DELETE(){
            {
                withTop(top.build());
                withFrom(true);
                withTableAlias(table_alias);
                withTableHint(with.build());
                withOutput(output.build());
                withOption(option.build());
            }
        };
    }
    static b_DELETE DELETE(
            b_TOP top,
            b_DELETE.k_FROM from_,
            String table_alias,
            b$table_hint_limited.b_WITH with,
            b_OUTPUT output){
        return new b_DELETE(){
            {
                withTop(top.build());
                withFrom(true);
                withTableAlias(table_alias);
                withTableHint(with.build());
                withOutput(output.build());
            }
        };
    }
    static b_DELETE DELETE(
            b_TOP top,
            b_DELETE.k_FROM from_,
            String table_alias,
            b$table_hint_limited.b_WITH with,
            b_FROM from,
            b_WHERE where,
            b_OPTION option){
        return new b_DELETE(){
            {
                withTop(top.build());
                withFrom(true);
                withTableAlias(table_alias);
                withTableHint(with.build());
                withFrom(from.build());
                withWhere(where.build());
                withOption(option.build());
            }
        };
    }
    static b_DELETE DELETE(
            b_TOP top,
            b_DELETE.k_FROM from_,
            String table_alias,
            b$table_hint_limited.b_WITH with,
            b_FROM from,
            b_WHERE where){
        return new b_DELETE(){
            {
                withTop(top.build());
                withFrom(true);
                withTableAlias(table_alias);
                withTableHint(with.build());
                withFrom(from.build());
                withWhere(where.build());
            }
        };
    }
    static b_DELETE DELETE(
            b_TOP top,
            b_DELETE.k_FROM from_,
            String table_alias,
            b$table_hint_limited.b_WITH with,
            b_FROM from,
            b_OPTION option){
        return new b_DELETE(){
            {
                withTop(top.build());
                withFrom(true);
                withTableAlias(table_alias);
                withTableHint(with.build());
                withFrom(from.build());
                withOption(option.build());
            }
        };
    }
    static b_DELETE DELETE(
            b_TOP top,
            b_DELETE.k_FROM from_,
            String table_alias,
            b$table_hint_limited.b_WITH with,
            b_FROM from){
        return new b_DELETE(){
            {
                withTop(top.build());
                withFrom(true);
                withTableAlias(table_alias);
                withTableHint(with.build());
                withFrom(from.build());
            }
        };
    }
    static b_DELETE DELETE(
            b_TOP top,
            b_DELETE.k_FROM from_,
            String table_alias,
            b$table_hint_limited.b_WITH with,
            b_WHERE where,
            b_OPTION option){
        return new b_DELETE(){
            {
                withTop(top.build());
                withFrom(true);
                withTableAlias(table_alias);
                withTableHint(with.build());
                withWhere(where.build());
                withOption(option.build());
            }
        };
    }
    static b_DELETE DELETE(
            b_TOP top,
            b_DELETE.k_FROM from_,
            String table_alias,
            b$table_hint_limited.b_WITH with,
            b_WHERE where){
        return new b_DELETE(){
            {
                withTop(top.build());
                withFrom(true);
                withTableAlias(table_alias);
                withTableHint(with.build());
                withWhere(where.build());
            }
        };
    }
    static b_DELETE DELETE(
            b_TOP top,
            b_DELETE.k_FROM from_,
            String table_alias,
            b$table_hint_limited.b_WITH with,
            b_OPTION option){
        return new b_DELETE(){
            {
                withTop(top.build());
                withFrom(true);
                withTableAlias(table_alias);
                withTableHint(with.build());
                withOption(option.build());
            }
        };
    }
    static b_DELETE DELETE(
            b_TOP top,
            b_DELETE.k_FROM from_,
            String table_alias,
            b$table_hint_limited.b_WITH with){
        return new b_DELETE(){
            {
                withTop(top.build());
                withFrom(true);
                withTableAlias(table_alias);
                withTableHint(with.build());
            }
        };
    }

    static b_DELETE DELETE(
            b_TOP top,
            b_DELETE.k_FROM from_,
            String table_alias,
            b_OUTPUT output,
            b_FROM from,
            b_WHERE where,
            b_OPTION option){
        return new b_DELETE(){
            {
                withTop(top.build());
                withTableAlias(table_alias);
                withOutput(output.build());
                withFrom(from.build());
                withWhere(where.build());
                withOption(option.build());
            }
        };
    }
    static b_DELETE DELETE(
            b_TOP top,
            b_DELETE.k_FROM from_,
            String table_alias,
            b_OUTPUT output,
            b_FROM from,
            b_WHERE where){
        return new b_DELETE(){
            {
                withTop(top.build());
                withFrom(true);
                withTableAlias(table_alias);
                withOutput(output.build());
                withFrom(from.build());
                withWhere(where.build());
            }
        };
    }
    static b_DELETE DELETE(
            b_TOP top,
            b_DELETE.k_FROM from_,
            String table_alias,
            b_OUTPUT output,
            b_FROM from,
            b_OPTION option){
        return new b_DELETE(){
            {
                withTop(top.build());
                withFrom(true);
                withTableAlias(table_alias);
                withOutput(output.build());
                withFrom(from.build());
                withOption(option.build());
            }
        };
    }
    static b_DELETE DELETE(
            b_TOP top,
            b_DELETE.k_FROM from_,
            String table_alias,
            b_OUTPUT output,
            b_FROM from){
        return new b_DELETE(){
            {
                withTop(top.build());
                withFrom(true);
                withTableAlias(table_alias);
                withOutput(output.build());
                withFrom(from.build());
            }
        };
    }
    static b_DELETE DELETE(
            b_TOP top,
            b_DELETE.k_FROM from_,
            String table_alias,
            b_OUTPUT output,
            b_WHERE where,
            b_OPTION option){
        return new b_DELETE(){
            {
                withTop(top.build());
                withFrom(true);
                withTableAlias(table_alias);
                withOutput(output.build());
                withWhere(where.build());
                withOption(option.build());
            }
        };
    }
    static b_DELETE DELETE(
            b_TOP top,
            b_DELETE.k_FROM from_,
            String table_alias,
            b_OUTPUT output,
            b_WHERE where){
        return new b_DELETE(){
            {
                withTop(top.build());
                withFrom(true);
                withTableAlias(table_alias);
                withOutput(output.build());
                withWhere(where.build());
            }
        };
    }
    static b_DELETE DELETE(
            b_TOP top,
            b_DELETE.k_FROM from_,
            String table_alias,
            b_OUTPUT output,
            b_OPTION option){
        return new b_DELETE(){
            {
                withTop(top.build());
                withFrom(true);
                withTableAlias(table_alias);
                withOutput(output.build());
                withOption(option.build());
            }
        };
    }
    static b_DELETE DELETE(
            b_TOP top,
            b_DELETE.k_FROM from_,
            String table_alias,
            b_OUTPUT output){
        return new b_DELETE(){
            {
                withTop(top.build());
                withFrom(true);
                withTableAlias(table_alias);
                withOutput(output.build());
            }
        };
    }
    static b_DELETE DELETE(
            b_TOP top,
            b_DELETE.k_FROM from_,
            String table_alias,
            b_FROM from,
            b_WHERE where,
            b_OPTION option){
        return new b_DELETE(){
            {
                withTop(top.build());
                withFrom(true);
                withTableAlias(table_alias);
                withFrom(from.build());
                withWhere(where.build());
                withOption(option.build());
            }
        };
    }
    static b_DELETE DELETE(
            b_TOP top,
            b_DELETE.k_FROM from_,
            String table_alias,
            b_FROM from,
            b_WHERE where){
        return new b_DELETE(){
            {
                withTop(top.build());
                withFrom(true);
                withTableAlias(table_alias);
                withFrom(from.build());
                withWhere(where.build());
            }
        };
    }
    static b_DELETE DELETE(
            b_TOP top,
            b_DELETE.k_FROM from_,
            String table_alias,
            b_FROM from,
            b_OPTION option){
        return new b_DELETE(){
            {
                withTop(top.build());
                withFrom(true);
                withTableAlias(table_alias);
                withFrom(from.build());
                withOption(option.build());
            }
        };
    }
    static b_DELETE DELETE(
            b_TOP top,
            b_DELETE.k_FROM from_,
            String table_alias,
            b_FROM from){
        return new b_DELETE(){
            {
                withTop(top.build());
                withFrom(true);
                withTableAlias(table_alias);
                withFrom(from.build());
            }
        };
    }
    static b_DELETE DELETE(
            b_TOP top,
            b_DELETE.k_FROM from_,
            String table_alias,
            b_WHERE where,
            b_OPTION option){
        return new b_DELETE(){
            {
                withTop(top.build());
                withFrom(true);
                withTableAlias(table_alias);
                withWhere(where.build());
                withOption(option.build());
            }
        };
    }
    static b_DELETE DELETE(
            b_TOP top,
            b_DELETE.k_FROM from_,
            String table_alias,
            b_WHERE where){
        return new b_DELETE(){
            {
                withTop(top.build());
                withFrom(true);
                withTableAlias(table_alias);
                withWhere(where.build());
            }
        };
    }
    static b_DELETE DELETE(
            b_TOP top,
            b_DELETE.k_FROM from_,
            String table_alias,
            b_OPTION option){
        return new b_DELETE(){
            {
                withTop(top.build());
                withFrom(true);
                withTableAlias(table_alias);
                withOption(option.build());
            }
        };
    }
    static b_DELETE DELETE(
            b_TOP top,
            b_DELETE.k_FROM from_,
            String table_alias){
        return new b_DELETE(){
            {
                withTop(top.build());
                withFrom(true);
                withTableAlias(table_alias);
            }
        };
    }
    static b_DELETE DELETE(
            b_TOP top,
            b_DELETE.k_FROM from_,
            TableName table_or_view_name,
            b$table_hint_limited.b_WITH with,
            b_OUTPUT output,
            b_FROM from,
            b_WHERE where,
            b_OPTION option){
        return new b_DELETE(){
            {
                withTop(top.build());
                withTableName(table_or_view_name);
                withTableHint(with.build());
                withOutput(output.build());
                withFrom(from.build());
                withWhere(where.build());
                withOption(option.build());
            }
        };
    }
    static b_DELETE DELETE(
            b_TOP top,
            b_DELETE.k_FROM from_,
            TableName table_or_view_name,
            b$table_hint_limited.b_WITH with,
            b_OUTPUT output,
            b_FROM from,
            b_WHERE where){
        return new b_DELETE(){
            {
                withTop(top.build());
                withFrom(true);
                withTableName(table_or_view_name);
                withTableHint(with.build());
                withOutput(output.build());
                withFrom(from.build());
                withWhere(where.build());
            }
        };
    }
    static b_DELETE DELETE(
            b_TOP top,
            b_DELETE.k_FROM from_,
            TableName table_or_view_name,
            b$table_hint_limited.b_WITH with,
            b_OUTPUT output,
            b_FROM from,
            b_OPTION option){
        return new b_DELETE(){
            {
                withTop(top.build());
                withFrom(true);
                withTableName(table_or_view_name);
                withTableHint(with.build());
                withOutput(output.build());
                withFrom(from.build());
                withOption(option.build());
            }
        };
    }
    static b_DELETE DELETE(
            b_TOP top,
            b_DELETE.k_FROM from_,
            TableName table_or_view_name,
            b$table_hint_limited.b_WITH with,
            b_OUTPUT output,
            b_FROM from){
        return new b_DELETE(){
            {
                withTop(top.build());
                withFrom(true);
                withTableName(table_or_view_name);
                withTableHint(with.build());
                withOutput(output.build());
                withFrom(from.build());
            }
        };
    }
    static b_DELETE DELETE(
            b_TOP top,
            b_DELETE.k_FROM from_,
            TableName table_or_view_name,
            b$table_hint_limited.b_WITH with,
            b_OUTPUT output,
            b_WHERE where,
            b_OPTION option){
        return new b_DELETE(){
            {
                withTop(top.build());
                withFrom(true);
                withTableName(table_or_view_name);
                withTableHint(with.build());
                withOutput(output.build());
                withWhere(where.build());
                withOption(option.build());
            }
        };
    }
    static b_DELETE DELETE(
            b_TOP top,
            b_DELETE.k_FROM from_,
            TableName table_or_view_name,
            b$table_hint_limited.b_WITH with,
            b_OUTPUT output,
            b_WHERE where){
        return new b_DELETE(){
            {
                withTop(top.build());
                withFrom(true);
                withTableName(table_or_view_name);
                withTableHint(with.build());
                withOutput(output.build());
                withWhere(where.build());
            }
        };
    }
    static b_DELETE DELETE(
            b_TOP top,
            b_DELETE.k_FROM from_,
            TableName table_or_view_name,
            b$table_hint_limited.b_WITH with,
            b_OUTPUT output,
            b_OPTION option){
        return new b_DELETE(){
            {
                withTop(top.build());
                withFrom(true);
                withTableName(table_or_view_name);
                withTableHint(with.build());
                withOutput(output.build());
                withOption(option.build());
            }
        };
    }
    static b_DELETE DELETE(
            b_TOP top,
            b_DELETE.k_FROM from_,
            TableName table_or_view_name,
            b$table_hint_limited.b_WITH with,
            b_OUTPUT output){
        return new b_DELETE(){
            {
                withTop(top.build());
                withFrom(true);
                withTableName(table_or_view_name);
                withTableHint(with.build());
                withOutput(output.build());
            }
        };
    }
    static b_DELETE DELETE(
            b_TOP top,
            b_DELETE.k_FROM from_,
            TableName table_or_view_name,
            b$table_hint_limited.b_WITH with,
            b_FROM from,
            b_WHERE where,
            b_OPTION option){
        return new b_DELETE(){
            {
                withTop(top.build());
                withFrom(true);
                withTableName(table_or_view_name);
                withTableHint(with.build());
                withFrom(from.build());
                withWhere(where.build());
                withOption(option.build());
            }
        };
    }
    static b_DELETE DELETE(
            b_TOP top,
            b_DELETE.k_FROM from_,
            TableName table_or_view_name,
            b$table_hint_limited.b_WITH with,
            b_FROM from,
            b_WHERE where){
        return new b_DELETE(){
            {
                withTop(top.build());
                withFrom(true);
                withTableName(table_or_view_name);
                withTableHint(with.build());
                withFrom(from.build());
                withWhere(where.build());
            }
        };
    }
    static b_DELETE DELETE(
            b_TOP top,
            b_DELETE.k_FROM from_,
            TableName table_or_view_name,
            b$table_hint_limited.b_WITH with,
            b_FROM from,
            b_OPTION option){
        return new b_DELETE(){
            {
                withTop(top.build());
                withFrom(true);
                withTableName(table_or_view_name);
                withTableHint(with.build());
                withFrom(from.build());
                withOption(option.build());
            }
        };
    }
    static b_DELETE DELETE(
            b_TOP top,
            b_DELETE.k_FROM from_,
            TableName table_or_view_name,
            b$table_hint_limited.b_WITH with,
            b_FROM from){
        return new b_DELETE(){
            {
                withTop(top.build());
                withFrom(true);
                withTableName(table_or_view_name);
                withTableHint(with.build());
                withFrom(from.build());
            }
        };
    }
    static b_DELETE DELETE(
            b_TOP top,
            b_DELETE.k_FROM from_,
            TableName table_or_view_name,
            b$table_hint_limited.b_WITH with,
            b_WHERE where,
            b_OPTION option){
        return new b_DELETE(){
            {
                withTop(top.build());
                withFrom(true);
                withTableName(table_or_view_name);
                withTableHint(with.build());
                withWhere(where.build());
                withOption(option.build());
            }
        };
    }
    static b_DELETE DELETE(
            b_TOP top,
            b_DELETE.k_FROM from_,
            TableName table_or_view_name,
            b$table_hint_limited.b_WITH with,
            b_WHERE where){
        return new b_DELETE(){
            {
                withTop(top.build());
                withFrom(true);
                withTableName(table_or_view_name);
                withTableHint(with.build());
                withWhere(where.build());
            }
        };
    }
    static b_DELETE DELETE(
            b_TOP top,
            b_DELETE.k_FROM from_,
            TableName table_or_view_name,
            b$table_hint_limited.b_WITH with,
            b_OPTION option){
        return new b_DELETE(){
            {
                withTop(top.build());
                withFrom(true);
                withTableName(table_or_view_name);
                withTableHint(with.build());
                withOption(option.build());
            }
        };
    }
    static b_DELETE DELETE(
            b_TOP top,
            b_DELETE.k_FROM from_,
            TableName table_or_view_name,
            b$table_hint_limited.b_WITH with){
        return new b_DELETE(){
            {
                withTop(top.build());
                withFrom(true);
                withTableName(table_or_view_name);
                withTableHint(with.build());
            }
        };
    }
    static b_DELETE DELETE(
            b_TOP top,
            b_DELETE.k_FROM from_,
            TableName table_or_view_name,
            b_OUTPUT output,
            b_FROM from,
            b_WHERE where,
            b_OPTION option){
        return new b_DELETE(){
            {
                withTop(top.build());
                withTableName(table_or_view_name);
                withOutput(output.build());
                withFrom(from.build());
                withWhere(where.build());
                withOption(option.build());
            }
        };
    }
    static b_DELETE DELETE(
            b_TOP top,
            b_DELETE.k_FROM from_,
            TableName table_or_view_name,
            b_OUTPUT output,
            b_FROM from,
            b_WHERE where){
        return new b_DELETE(){
            {
                withTop(top.build());
                withFrom(true);
                withTableName(table_or_view_name);
                withOutput(output.build());
                withFrom(from.build());
                withWhere(where.build());
            }
        };
    }
    static b_DELETE DELETE(
            b_TOP top,
            b_DELETE.k_FROM from_,
            TableName table_or_view_name,
            b_OUTPUT output,
            b_FROM from,
            b_OPTION option){
        return new b_DELETE(){
            {
                withTop(top.build());
                withFrom(true);
                withTableName(table_or_view_name);
                withOutput(output.build());
                withFrom(from.build());
                withOption(option.build());
            }
        };
    }
    static b_DELETE DELETE(
            b_TOP top,
            b_DELETE.k_FROM from_,
            TableName table_or_view_name,
            b_OUTPUT output,
            b_FROM from){
        return new b_DELETE(){
            {
                withTop(top.build());
                withFrom(true);
                withTableName(table_or_view_name);
                withOutput(output.build());
                withFrom(from.build());
            }
        };
    }
    static b_DELETE DELETE(
            b_TOP top,
            b_DELETE.k_FROM from_,
            TableName table_or_view_name,
            b_OUTPUT output,
            b_WHERE where,
            b_OPTION option){
        return new b_DELETE(){
            {
                withTop(top.build());
                withFrom(true);
                withTableName(table_or_view_name);
                withOutput(output.build());
                withWhere(where.build());
                withOption(option.build());
            }
        };
    }
    static b_DELETE DELETE(
            b_TOP top,
            b_DELETE.k_FROM from_,
            TableName table_or_view_name,
            b_OUTPUT output,
            b_WHERE where){
        return new b_DELETE(){
            {
                withTop(top.build());
                withFrom(true);
                withTableName(table_or_view_name);
                withOutput(output.build());
                withWhere(where.build());
            }
        };
    }
    static b_DELETE DELETE(
            b_TOP top,
            b_DELETE.k_FROM from_,
            TableName table_or_view_name,
            b_OUTPUT output,
            b_OPTION option){
        return new b_DELETE(){
            {
                withTop(top.build());
                withFrom(true);
                withTableName(table_or_view_name);
                withOutput(output.build());
                withOption(option.build());
            }
        };
    }
    static b_DELETE DELETE(
            b_TOP top,
            b_DELETE.k_FROM from_,
            TableName table_or_view_name,
            b_OUTPUT output){
        return new b_DELETE(){
            {
                withTop(top.build());
                withFrom(true);
                withTableName(table_or_view_name);
                withOutput(output.build());
            }
        };
    }
    static b_DELETE DELETE(
            b_TOP top,
            b_DELETE.k_FROM from_,
            TableName table_or_view_name,
            b_FROM from,
            b_WHERE where,
            b_OPTION option){
        return new b_DELETE(){
            {
                withTop(top.build());
                withFrom(true);
                withTableName(table_or_view_name);
                withFrom(from.build());
                withWhere(where.build());
                withOption(option.build());
            }
        };
    }
    static b_DELETE DELETE(
            b_TOP top,
            b_DELETE.k_FROM from_,
            TableName table_or_view_name,
            b_FROM from,
            b_WHERE where){
        return new b_DELETE(){
            {
                withTop(top.build());
                withFrom(true);
                withTableName(table_or_view_name);
                withFrom(from.build());
                withWhere(where.build());
            }
        };
    }
    static b_DELETE DELETE(
            b_TOP top,
            b_DELETE.k_FROM from_,
            TableName table_or_view_name,
            b_FROM from,
            b_OPTION option){
        return new b_DELETE(){
            {
                withTop(top.build());
                withFrom(true);
                withTableName(table_or_view_name);
                withFrom(from.build());
                withOption(option.build());
            }
        };
    }
    static b_DELETE DELETE(
            b_TOP top,
            b_DELETE.k_FROM from_,
            TableName table_or_view_name,
            b_FROM from){
        return new b_DELETE(){
            {
                withTop(top.build());
                withFrom(true);
                withTableName(table_or_view_name);
                withFrom(from.build());
            }
        };
    }
    static b_DELETE DELETE(
            b_TOP top,
            b_DELETE.k_FROM from_,
            TableName table_or_view_name,
            b_WHERE where,
            b_OPTION option){
        return new b_DELETE(){
            {
                withTop(top.build());
                withFrom(true);
                withTableName(table_or_view_name);
                withWhere(where.build());
                withOption(option.build());
            }
        };
    }
    static b_DELETE DELETE(
            b_TOP top,
            b_DELETE.k_FROM from_,
            TableName table_or_view_name,
            b_WHERE where){
        return new b_DELETE(){
            {
                withTop(top.build());
                withFrom(true);
                withTableName(table_or_view_name);
                withWhere(where.build());
            }
        };
    }
    static b_DELETE DELETE(
            b_TOP top,
            b_DELETE.k_FROM from_,
            TableName table_or_view_name,
            b_OPTION option){
        return new b_DELETE(){
            {
                withTop(top.build());
                withFrom(true);
                withTableName(table_or_view_name);
                withOption(option.build());
            }
        };
    }
    static b_DELETE DELETE(
            b_TOP top,
            b_DELETE.k_FROM from_,
            TableName table_or_view_name){
        return new b_DELETE(){
            {
                withTop(top.build());
                withFrom(true);
                withTableName(table_or_view_name);
            }
        };
    }

    static b_DELETE DELETE(
            b_TOP top,
            String table_alias,
            b$table_hint_limited.b_WITH with,
            b_OUTPUT output,
            b_FROM from,
            b_WHERE where,
            b_OPTION option){
        return new b_DELETE(){
            {
                withTop(top.build());
                withTableAlias(table_alias);
                withTableHint(with.build());
                withOutput(output.build());
                withFrom(from.build());
                withWhere(where.build());
                withOption(option.build());
            }
        };
    }
    static b_DELETE DELETE(
            b_TOP top,
            String table_alias,
            b$table_hint_limited.b_WITH with,
            b_OUTPUT output,
            b_FROM from,
            b_WHERE where){
        return new b_DELETE(){
            {
                withTop(top.build());
                withTableAlias(table_alias);
                withTableHint(with.build());
                withOutput(output.build());
                withFrom(from.build());
                withWhere(where.build());
            }
        };
    }
    static b_DELETE DELETE(
            b_TOP top,
            String table_alias,
            b$table_hint_limited.b_WITH with,
            b_OUTPUT output,
            b_FROM from,
            b_OPTION option){
        return new b_DELETE(){
            {
                withTop(top.build());
                withTableAlias(table_alias);
                withTableHint(with.build());
                withOutput(output.build());
                withFrom(from.build());
                withOption(option.build());
            }
        };
    }
    static b_DELETE DELETE(
            b_TOP top,
            String table_alias,
            b$table_hint_limited.b_WITH with,
            b_OUTPUT output,
            b_FROM from){
        return new b_DELETE(){
            {
                withTop(top.build());
                withTableAlias(table_alias);
                withTableHint(with.build());
                withOutput(output.build());
                withFrom(from.build());
            }
        };
    }
    static b_DELETE DELETE(
            b_TOP top,
            String table_alias,
            b$table_hint_limited.b_WITH with,
            b_OUTPUT output,
            b_WHERE where,
            b_OPTION option){
        return new b_DELETE(){
            {
                withTop(top.build());
                withTableAlias(table_alias);
                withTableHint(with.build());
                withOutput(output.build());
                withWhere(where.build());
                withOption(option.build());
            }
        };
    }
    static b_DELETE DELETE(
            b_TOP top,
            String table_alias,
            b$table_hint_limited.b_WITH with,
            b_OUTPUT output,
            b_WHERE where){
        return new b_DELETE(){
            {
                withTop(top.build());
                withTableAlias(table_alias);
                withTableHint(with.build());
                withOutput(output.build());
                withWhere(where.build());
            }
        };
    }
    static b_DELETE DELETE(
            b_TOP top,
            String table_alias,
            b$table_hint_limited.b_WITH with,
            b_OUTPUT output,
            b_OPTION option){
        return new b_DELETE(){
            {
                withTop(top.build());
                withTableAlias(table_alias);
                withTableHint(with.build());
                withOutput(output.build());
                withOption(option.build());
            }
        };
    }
    static b_DELETE DELETE(
            b_TOP top,
            String table_alias,
            b$table_hint_limited.b_WITH with,
            b_OUTPUT output){
        return new b_DELETE(){
            {
                withTop(top.build());
                withTableAlias(table_alias);
                withTableHint(with.build());
                withOutput(output.build());
            }
        };
    }
    static b_DELETE DELETE(
            b_TOP top,
            String table_alias,
            b$table_hint_limited.b_WITH with,
            b_FROM from,
            b_WHERE where,
            b_OPTION option){
        return new b_DELETE(){
            {
                withTop(top.build());
                withTableAlias(table_alias);
                withTableHint(with.build());
                withFrom(from.build());
                withWhere(where.build());
                withOption(option.build());
            }
        };
    }
    static b_DELETE DELETE(
            b_TOP top,
            String table_alias,
            b$table_hint_limited.b_WITH with,
            b_FROM from,
            b_WHERE where){
        return new b_DELETE(){
            {
                withTop(top.build());
                withTableAlias(table_alias);
                withTableHint(with.build());
                withFrom(from.build());
                withWhere(where.build());
            }
        };
    }
    static b_DELETE DELETE(
            b_TOP top,
            String table_alias,
            b$table_hint_limited.b_WITH with,
            b_FROM from,
            b_OPTION option){
        return new b_DELETE(){
            {
                withTop(top.build());
                withTableAlias(table_alias);
                withTableHint(with.build());
                withFrom(from.build());
                withOption(option.build());
            }
        };
    }
    static b_DELETE DELETE(
            b_TOP top,
            String table_alias,
            b$table_hint_limited.b_WITH with,
            b_FROM from){
        return new b_DELETE(){
            {
                withTop(top.build());
                withTableAlias(table_alias);
                withTableHint(with.build());
                withFrom(from.build());
            }
        };
    }
    static b_DELETE DELETE(
            b_TOP top,
            String table_alias,
            b$table_hint_limited.b_WITH with,
            b_WHERE where,
            b_OPTION option){
        return new b_DELETE(){
            {
                withTop(top.build());
                withTableAlias(table_alias);
                withTableHint(with.build());
                withWhere(where.build());
                withOption(option.build());
            }
        };
    }
    static b_DELETE DELETE(
            b_TOP top,
            String table_alias,
            b$table_hint_limited.b_WITH with,
            b_WHERE where){
        return new b_DELETE(){
            {
                withTop(top.build());
                withTableAlias(table_alias);
                withTableHint(with.build());
                withWhere(where.build());
            }
        };
    }
    static b_DELETE DELETE(
            b_TOP top,
            String table_alias,
            b$table_hint_limited.b_WITH with,
            b_OPTION option){
        return new b_DELETE(){
            {
                withTop(top.build());
                withTableAlias(table_alias);
                withTableHint(with.build());
                withOption(option.build());
            }
        };
    }
    static b_DELETE DELETE(
            b_TOP top,
            String table_alias,
            b$table_hint_limited.b_WITH with){
        return new b_DELETE(){
            {
                withTop(top.build());
                withTableAlias(table_alias);
                withTableHint(with.build());
            }
        };
    }

    static b_DELETE DELETE(
            b_TOP top,
            String table_alias,
            b_OUTPUT output,
            b_FROM from,
            b_WHERE where,
            b_OPTION option){
        return new b_DELETE(){
            {
                withTop(top.build());
                withTableAlias(table_alias);
                withOutput(output.build());
                withFrom(from.build());
                withWhere(where.build());
                withOption(option.build());
            }
        };
    }
    static b_DELETE DELETE(
            b_TOP top,
            String table_alias,
            b_OUTPUT output,
            b_FROM from,
            b_WHERE where){
        return new b_DELETE(){
            {
                withTop(top.build());
                withTableAlias(table_alias);
                withOutput(output.build());
                withFrom(from.build());
                withWhere(where.build());
            }
        };
    }
    static b_DELETE DELETE(
            b_TOP top,
            String table_alias,
            b_OUTPUT output,
            b_FROM from,
            b_OPTION option){
        return new b_DELETE(){
            {
                withTop(top.build());
                withTableAlias(table_alias);
                withOutput(output.build());
                withFrom(from.build());
                withOption(option.build());
            }
        };
    }
    static b_DELETE DELETE(
            b_TOP top,
            String table_alias,
            b_OUTPUT output,
            b_FROM from){
        return new b_DELETE(){
            {
                withTop(top.build());
                withTableAlias(table_alias);
                withOutput(output.build());
                withFrom(from.build());
            }
        };
    }
    static b_DELETE DELETE(
            b_TOP top,
            String table_alias,
            b_OUTPUT output,
            b_WHERE where,
            b_OPTION option){
        return new b_DELETE(){
            {
                withTop(top.build());
                withTableAlias(table_alias);
                withOutput(output.build());
                withWhere(where.build());
                withOption(option.build());
            }
        };
    }
    static b_DELETE DELETE(
            b_TOP top,
            String table_alias,
            b_OUTPUT output,
            b_WHERE where){
        return new b_DELETE(){
            {
                withTop(top.build());
                withTableAlias(table_alias);
                withOutput(output.build());
                withWhere(where.build());
            }
        };
    }
    static b_DELETE DELETE(
            b_TOP top,
            String table_alias,
            b_OUTPUT output,
            b_OPTION option){
        return new b_DELETE(){
            {
                withTop(top.build());
                withTableAlias(table_alias);
                withOutput(output.build());
                withOption(option.build());
            }
        };
    }
    static b_DELETE DELETE(
            b_TOP top,
            String table_alias,
            b_OUTPUT output){
        return new b_DELETE(){
            {
                withTop(top.build());
                withTableAlias(table_alias);
                withOutput(output.build());
            }
        };
    }
    static b_DELETE DELETE(
            b_TOP top,
            String table_alias,
            b_FROM from,
            b_WHERE where,
            b_OPTION option){
        return new b_DELETE(){
            {
                withTop(top.build());
                withTableAlias(table_alias);
                withFrom(from.build());
                withWhere(where.build());
                withOption(option.build());
            }
        };
    }
    static b_DELETE DELETE(
            b_TOP top,
            String table_alias,
            b_FROM from,
            b_WHERE where){
        return new b_DELETE(){
            {
                withTop(top.build());
                withTableAlias(table_alias);
                withFrom(from.build());
                withWhere(where.build());
            }
        };
    }
    static b_DELETE DELETE(
            b_TOP top,
            String table_alias,
            b_FROM from,
            b_OPTION option){
        return new b_DELETE(){
            {
                withTop(top.build());
                withTableAlias(table_alias);
                withFrom(from.build());
                withOption(option.build());
            }
        };
    }
    static b_DELETE DELETE(
            b_TOP top,
            String table_alias,
            b_FROM from){
        return new b_DELETE(){
            {
                withTop(top.build());
                withTableAlias(table_alias);
                withFrom(from.build());
            }
        };
    }
    static b_DELETE DELETE(
            b_TOP top,
            String table_alias,
            b_WHERE where,
            b_OPTION option){
        return new b_DELETE(){
            {
                withTop(top.build());
                withTableAlias(table_alias);
                withWhere(where.build());
                withOption(option.build());
            }
        };
    }
    static b_DELETE DELETE(
            b_TOP top,
            String table_alias,
            b_WHERE where){
        return new b_DELETE(){
            {
                withTop(top.build());
                withTableAlias(table_alias);
                withWhere(where.build());
            }
        };
    }
    static b_DELETE DELETE(
            b_TOP top,
            String table_alias,
            b_OPTION option){
        return new b_DELETE(){
            {
                withTop(top.build());
                withTableAlias(table_alias);
                withOption(option.build());
            }
        };
    }
    static b_DELETE DELETE(
            b_TOP top,
            String table_alias){
        return new b_DELETE(){
            {
                withTop(top.build());
                withTableAlias(table_alias);
            }
        };
    }
    static b_DELETE DELETE(
            b_TOP top,
            TableName table_or_view_name,
            b$table_hint_limited.b_WITH with,
            b_OUTPUT output,
            b_FROM from,
            b_WHERE where,
            b_OPTION option){
        return new b_DELETE(){
            {
                withTop(top.build());
                withTableName(table_or_view_name);
                withTableHint(with.build());
                withOutput(output.build());
                withFrom(from.build());
                withWhere(where.build());
                withOption(option.build());
            }
        };
    }
    static b_DELETE DELETE(
            b_TOP top,
            TableName table_or_view_name,
            b$table_hint_limited.b_WITH with,
            b_OUTPUT output,
            b_FROM from,
            b_WHERE where){
        return new b_DELETE(){
            {
                withTop(top.build());
                withTableName(table_or_view_name);
                withTableHint(with.build());
                withOutput(output.build());
                withFrom(from.build());
                withWhere(where.build());
            }
        };
    }
    static b_DELETE DELETE(
            b_TOP top,
            TableName table_or_view_name,
            b$table_hint_limited.b_WITH with,
            b_OUTPUT output,
            b_FROM from,
            b_OPTION option){
        return new b_DELETE(){
            {
                withTop(top.build());
                withTableName(table_or_view_name);
                withTableHint(with.build());
                withOutput(output.build());
                withFrom(from.build());
                withOption(option.build());
            }
        };
    }
    static b_DELETE DELETE(
            b_TOP top,
            TableName table_or_view_name,
            b$table_hint_limited.b_WITH with,
            b_OUTPUT output,
            b_FROM from){
        return new b_DELETE(){
            {
                withTop(top.build());
                withTableName(table_or_view_name);
                withTableHint(with.build());
                withOutput(output.build());
                withFrom(from.build());
            }
        };
    }
    static b_DELETE DELETE(
            b_TOP top,
            TableName table_or_view_name,
            b$table_hint_limited.b_WITH with,
            b_OUTPUT output,
            b_WHERE where,
            b_OPTION option){
        return new b_DELETE(){
            {
                withTop(top.build());
                withTableName(table_or_view_name);
                withTableHint(with.build());
                withOutput(output.build());
                withWhere(where.build());
                withOption(option.build());
            }
        };
    }
    static b_DELETE DELETE(
            b_TOP top,
            TableName table_or_view_name,
            b$table_hint_limited.b_WITH with,
            b_OUTPUT output,
            b_WHERE where){
        return new b_DELETE(){
            {
                withTop(top.build());
                withTableName(table_or_view_name);
                withTableHint(with.build());
                withOutput(output.build());
                withWhere(where.build());
            }
        };
    }
    static b_DELETE DELETE(
            b_TOP top,
            TableName table_or_view_name,
            b$table_hint_limited.b_WITH with,
            b_OUTPUT output,
            b_OPTION option){
        return new b_DELETE(){
            {
                withTop(top.build());
                withTableName(table_or_view_name);
                withTableHint(with.build());
                withOutput(output.build());
                withOption(option.build());
            }
        };
    }
    static b_DELETE DELETE(
            b_TOP top,
            TableName table_or_view_name,
            b$table_hint_limited.b_WITH with,
            b_OUTPUT output){
        return new b_DELETE(){
            {
                withTop(top.build());
                withTableName(table_or_view_name);
                withTableHint(with.build());
                withOutput(output.build());
            }
        };
    }
    static b_DELETE DELETE(
            b_TOP top,
            TableName table_or_view_name,
            b$table_hint_limited.b_WITH with,
            b_FROM from,
            b_WHERE where,
            b_OPTION option){
        return new b_DELETE(){
            {
                withTop(top.build());
                withTableName(table_or_view_name);
                withTableHint(with.build());
                withFrom(from.build());
                withWhere(where.build());
                withOption(option.build());
            }
        };
    }
    static b_DELETE DELETE(
            b_TOP top,
            TableName table_or_view_name,
            b$table_hint_limited.b_WITH with,
            b_FROM from,
            b_WHERE where){
        return new b_DELETE(){
            {
                withTop(top.build());
                withTableName(table_or_view_name);
                withTableHint(with.build());
                withFrom(from.build());
                withWhere(where.build());
            }
        };
    }
    static b_DELETE DELETE(
            b_TOP top,
            TableName table_or_view_name,
            b$table_hint_limited.b_WITH with,
            b_FROM from,
            b_OPTION option){
        return new b_DELETE(){
            {
                withTop(top.build());
                withTableName(table_or_view_name);
                withTableHint(with.build());
                withFrom(from.build());
                withOption(option.build());
            }
        };
    }
    static b_DELETE DELETE(
            b_TOP top,
            TableName table_or_view_name,
            b$table_hint_limited.b_WITH with,
            b_FROM from){
        return new b_DELETE(){
            {
                withTop(top.build());
                withTableName(table_or_view_name);
                withTableHint(with.build());
                withFrom(from.build());
            }
        };
    }
    static b_DELETE DELETE(
            b_TOP top,
            TableName table_or_view_name,
            b$table_hint_limited.b_WITH with,
            b_WHERE where,
            b_OPTION option){
        return new b_DELETE(){
            {
                withTop(top.build());
                withTableName(table_or_view_name);
                withTableHint(with.build());
                withWhere(where.build());
                withOption(option.build());
            }
        };
    }
    static b_DELETE DELETE(
            b_TOP top,
            TableName table_or_view_name,
            b$table_hint_limited.b_WITH with,
            b_WHERE where){
        return new b_DELETE(){
            {
                withTop(top.build());
                withTableName(table_or_view_name);
                withTableHint(with.build());
                withWhere(where.build());
            }
        };
    }
    static b_DELETE DELETE(
            b_TOP top,
            TableName table_or_view_name,
            b$table_hint_limited.b_WITH with,
            b_OPTION option){
        return new b_DELETE(){
            {
                withTop(top.build());
                withTableName(table_or_view_name);
                withTableHint(with.build());
                withOption(option.build());
            }
        };
    }
    static b_DELETE DELETE(
            b_TOP top,
            TableName table_or_view_name,
            b$table_hint_limited.b_WITH with){
        return new b_DELETE(){
            {
                withTop(top.build());
                withTableName(table_or_view_name);
                withTableHint(with.build());
            }
        };
    }
    static b_DELETE DELETE(
            b_TOP top,
            TableName table_or_view_name,
            b_OUTPUT output,
            b_FROM from,
            b_WHERE where,
            b_OPTION option){
        return new b_DELETE(){
            {
                withTop(top.build());
                withTableName(table_or_view_name);
                withOutput(output.build());
                withFrom(from.build());
                withWhere(where.build());
                withOption(option.build());
            }
        };
    }
    static b_DELETE DELETE(
            b_TOP top,
            TableName table_or_view_name,
            b_OUTPUT output,
            b_FROM from,
            b_WHERE where){
        return new b_DELETE(){
            {
                withTop(top.build());
                withTableName(table_or_view_name);
                withOutput(output.build());
                withFrom(from.build());
                withWhere(where.build());
            }
        };
    }
    static b_DELETE DELETE(
            b_TOP top,
            TableName table_or_view_name,
            b_OUTPUT output,
            b_FROM from,
            b_OPTION option){
        return new b_DELETE(){
            {
                withTop(top.build());
                withTableName(table_or_view_name);
                withOutput(output.build());
                withFrom(from.build());
                withOption(option.build());
            }
        };
    }
    static b_DELETE DELETE(
            b_TOP top,
            TableName table_or_view_name,
            b_OUTPUT output,
            b_FROM from){
        return new b_DELETE(){
            {
                withTop(top.build());
                withTableName(table_or_view_name);
                withOutput(output.build());
                withFrom(from.build());
            }
        };
    }
    static b_DELETE DELETE(
            b_TOP top,
            TableName table_or_view_name,
            b_OUTPUT output,
            b_WHERE where,
            b_OPTION option){
        return new b_DELETE(){
            {
                withTop(top.build());
                withTableName(table_or_view_name);
                withOutput(output.build());
                withWhere(where.build());
                withOption(option.build());
            }
        };
    }
    static b_DELETE DELETE(
            b_TOP top,
            TableName table_or_view_name,
            b_OUTPUT output,
            b_WHERE where){
        return new b_DELETE(){
            {
                withTop(top.build());
                withTableName(table_or_view_name);
                withOutput(output.build());
                withWhere(where.build());
            }
        };
    }
    static b_DELETE DELETE(
            b_TOP top,
            TableName table_or_view_name,
            b_OUTPUT output,
            b_OPTION option){
        return new b_DELETE(){
            {
                withTop(top.build());
                withTableName(table_or_view_name);
                withOutput(output.build());
                withOption(option.build());
            }
        };
    }
    static b_DELETE DELETE(
            b_TOP top,
            TableName table_or_view_name,
            b_OUTPUT output){
        return new b_DELETE(){
            {
                withTop(top.build());
                withTableName(table_or_view_name);
                withOutput(output.build());
            }
        };
    }
    static b_DELETE DELETE(
            b_TOP top,
            TableName table_or_view_name,
            b_FROM from,
            b_WHERE where,
            b_OPTION option){
        return new b_DELETE(){
            {
                withTop(top.build());
                withTableName(table_or_view_name);
                withFrom(from.build());
                withWhere(where.build());
                withOption(option.build());
            }
        };
    }
    static b_DELETE DELETE(
            b_TOP top,
            TableName table_or_view_name,
            b_FROM from,
            b_WHERE where){
        return new b_DELETE(){
            {
                withTop(top.build());
                withTableName(table_or_view_name);
                withFrom(from.build());
                withWhere(where.build());
            }
        };
    }
    static b_DELETE DELETE(
            b_TOP top,
            TableName table_or_view_name,
            b_FROM from,
            b_OPTION option){
        return new b_DELETE(){
            {
                withTop(top.build());
                withTableName(table_or_view_name);
                withFrom(from.build());
                withOption(option.build());
            }
        };
    }
    static b_DELETE DELETE(
            b_TOP top,
            TableName table_or_view_name,
            b_FROM from){
        return new b_DELETE(){
            {
                withTop(top.build());
                withTableName(table_or_view_name);
                withFrom(from.build());
            }
        };
    }
    static b_DELETE DELETE(
            b_TOP top,
            TableName table_or_view_name,
            b_WHERE where,
            b_OPTION option){
        return new b_DELETE(){
            {
                withTop(top.build());
                withTableName(table_or_view_name);
                withWhere(where.build());
                withOption(option.build());
            }
        };
    }
    static b_DELETE DELETE(
            b_TOP top,
            TableName table_or_view_name,
            b_WHERE where){
        return new b_DELETE(){
            {
                withTop(top.build());
                withTableName(table_or_view_name);
                withWhere(where.build());
            }
        };
    }
    static b_DELETE DELETE(
            b_TOP top,
            TableName table_or_view_name,
            b_OPTION option){
        return new b_DELETE(){
            {
                withTop(top.build());
                withTableName(table_or_view_name);
                withOption(option.build());
            }
        };
    }
    static b_DELETE DELETE(
            b_TOP top,
            TableName table_or_view_name){
        return new b_DELETE(){
            {
                withTop(top.build());
                withTableName(table_or_view_name);
            }
        };
    }

    static b_DELETE DELETE(
            b_DELETE.k_FROM from_,
            String table_alias,
            b$table_hint_limited.b_WITH with,
            b_OUTPUT output,
            b_FROM from,
            b_WHERE where,
            b_OPTION option){
        return new b_DELETE(){
            {
                withTableAlias(table_alias);
                withTableHint(with.build());
                withOutput(output.build());
                withFrom(from.build());
                withWhere(where.build());
                withOption(option.build());
            }
        };
    }
    static b_DELETE DELETE(
            b_DELETE.k_FROM from_,
            String table_alias,
            b$table_hint_limited.b_WITH with,
            b_OUTPUT output,
            b_FROM from,
            b_WHERE where){
        return new b_DELETE(){
            {
                withFrom(true);
                withTableAlias(table_alias);
                withTableHint(with.build());
                withOutput(output.build());
                withFrom(from.build());
                withWhere(where.build());
            }
        };
    }
    static b_DELETE DELETE(
            b_DELETE.k_FROM from_,
            String table_alias,
            b$table_hint_limited.b_WITH with,
            b_OUTPUT output,
            b_FROM from,
            b_OPTION option){
        return new b_DELETE(){
            {
                withFrom(true);
                withTableAlias(table_alias);
                withTableHint(with.build());
                withOutput(output.build());
                withFrom(from.build());
                withOption(option.build());
            }
        };
    }
    static b_DELETE DELETE(
            b_DELETE.k_FROM from_,
            String table_alias,
            b$table_hint_limited.b_WITH with,
            b_OUTPUT output,
            b_FROM from){
        return new b_DELETE(){
            {
                withFrom(true);
                withTableAlias(table_alias);
                withTableHint(with.build());
                withOutput(output.build());
                withFrom(from.build());
            }
        };
    }
    static b_DELETE DELETE(
            b_DELETE.k_FROM from_,
            String table_alias,
            b$table_hint_limited.b_WITH with,
            b_OUTPUT output,
            b_WHERE where,
            b_OPTION option){
        return new b_DELETE(){
            {
                withFrom(true);
                withTableAlias(table_alias);
                withTableHint(with.build());
                withOutput(output.build());
                withWhere(where.build());
                withOption(option.build());
            }
        };
    }
    static b_DELETE DELETE(
            b_DELETE.k_FROM from_,
            String table_alias,
            b$table_hint_limited.b_WITH with,
            b_OUTPUT output,
            b_WHERE where){
        return new b_DELETE(){
            {
                withFrom(true);
                withTableAlias(table_alias);
                withTableHint(with.build());
                withOutput(output.build());
                withWhere(where.build());
            }
        };
    }
    static b_DELETE DELETE(
            b_DELETE.k_FROM from_,
            String table_alias,
            b$table_hint_limited.b_WITH with,
            b_OUTPUT output,
            b_OPTION option){
        return new b_DELETE(){
            {
                withFrom(true);
                withTableAlias(table_alias);
                withTableHint(with.build());
                withOutput(output.build());
                withOption(option.build());
            }
        };
    }
    static b_DELETE DELETE(
            b_DELETE.k_FROM from_,
            String table_alias,
            b$table_hint_limited.b_WITH with,
            b_OUTPUT output){
        return new b_DELETE(){
            {
                withFrom(true);
                withTableAlias(table_alias);
                withTableHint(with.build());
                withOutput(output.build());
            }
        };
    }
    static b_DELETE DELETE(
            b_DELETE.k_FROM from_,
            String table_alias,
            b$table_hint_limited.b_WITH with,
            b_FROM from,
            b_WHERE where,
            b_OPTION option){
        return new b_DELETE(){
            {
                withFrom(true);
                withTableAlias(table_alias);
                withTableHint(with.build());
                withFrom(from.build());
                withWhere(where.build());
                withOption(option.build());
            }
        };
    }
    static b_DELETE DELETE(
            b_DELETE.k_FROM from_,
            String table_alias,
            b$table_hint_limited.b_WITH with,
            b_FROM from,
            b_WHERE where){
        return new b_DELETE(){
            {
                withFrom(true);
                withTableAlias(table_alias);
                withTableHint(with.build());
                withFrom(from.build());
                withWhere(where.build());
            }
        };
    }
    static b_DELETE DELETE(
            b_DELETE.k_FROM from_,
            String table_alias,
            b$table_hint_limited.b_WITH with,
            b_FROM from,
            b_OPTION option){
        return new b_DELETE(){
            {
                withFrom(true);
                withTableAlias(table_alias);
                withTableHint(with.build());
                withFrom(from.build());
                withOption(option.build());
            }
        };
    }
    static b_DELETE DELETE(
            b_DELETE.k_FROM from_,
            String table_alias,
            b$table_hint_limited.b_WITH with,
            b_FROM from){
        return new b_DELETE(){
            {
                withFrom(true);
                withTableAlias(table_alias);
                withTableHint(with.build());
                withFrom(from.build());
            }
        };
    }
    static b_DELETE DELETE(
            b_DELETE.k_FROM from_,
            String table_alias,
            b$table_hint_limited.b_WITH with,
            b_WHERE where,
            b_OPTION option){
        return new b_DELETE(){
            {
                withFrom(true);
                withTableAlias(table_alias);
                withTableHint(with.build());
                withWhere(where.build());
                withOption(option.build());
            }
        };
    }
    static b_DELETE DELETE(
            b_DELETE.k_FROM from_,
            String table_alias,
            b$table_hint_limited.b_WITH with,
            b_WHERE where){
        return new b_DELETE(){
            {
                withFrom(true);
                withTableAlias(table_alias);
                withTableHint(with.build());
                withWhere(where.build());
            }
        };
    }
    static b_DELETE DELETE(
            b_DELETE.k_FROM from_,
            String table_alias,
            b$table_hint_limited.b_WITH with,
            b_OPTION option){
        return new b_DELETE(){
            {
                withFrom(true);
                withTableAlias(table_alias);
                withTableHint(with.build());
                withOption(option.build());
            }
        };
    }
    static b_DELETE DELETE(
            b_DELETE.k_FROM from_,
            String table_alias,
            b$table_hint_limited.b_WITH with){
        return new b_DELETE(){
            {
                withFrom(true);
                withTableAlias(table_alias);
                withTableHint(with.build());
            }
        };
    }

    static b_DELETE DELETE(
            b_DELETE.k_FROM from_,
            String table_alias,
            b_OUTPUT output,
            b_FROM from,
            b_WHERE where,
            b_OPTION option){
        return new b_DELETE(){
            {
                withTableAlias(table_alias);
                withOutput(output.build());
                withFrom(from.build());
                withWhere(where.build());
                withOption(option.build());
            }
        };
    }
    static b_DELETE DELETE(
            b_DELETE.k_FROM from_,
            String table_alias,
            b_OUTPUT output,
            b_FROM from,
            b_WHERE where){
        return new b_DELETE(){
            {
                withFrom(true);
                withTableAlias(table_alias);
                withOutput(output.build());
                withFrom(from.build());
                withWhere(where.build());
            }
        };
    }
    static b_DELETE DELETE(
            b_DELETE.k_FROM from_,
            String table_alias,
            b_OUTPUT output,
            b_FROM from,
            b_OPTION option){
        return new b_DELETE(){
            {
                withFrom(true);
                withTableAlias(table_alias);
                withOutput(output.build());
                withFrom(from.build());
                withOption(option.build());
            }
        };
    }
    static b_DELETE DELETE(
            b_DELETE.k_FROM from_,
            String table_alias,
            b_OUTPUT output,
            b_FROM from){
        return new b_DELETE(){
            {
                withFrom(true);
                withTableAlias(table_alias);
                withOutput(output.build());
                withFrom(from.build());
            }
        };
    }
    static b_DELETE DELETE(
            b_DELETE.k_FROM from_,
            String table_alias,
            b_OUTPUT output,
            b_WHERE where,
            b_OPTION option){
        return new b_DELETE(){
            {
                withFrom(true);
                withTableAlias(table_alias);
                withOutput(output.build());
                withWhere(where.build());
                withOption(option.build());
            }
        };
    }
    static b_DELETE DELETE(
            b_DELETE.k_FROM from_,
            String table_alias,
            b_OUTPUT output,
            b_WHERE where){
        return new b_DELETE(){
            {
                withFrom(true);
                withTableAlias(table_alias);
                withOutput(output.build());
                withWhere(where.build());
            }
        };
    }
    static b_DELETE DELETE(
            b_DELETE.k_FROM from_,
            String table_alias,
            b_OUTPUT output,
            b_OPTION option){
        return new b_DELETE(){
            {
                withFrom(true);
                withTableAlias(table_alias);
                withOutput(output.build());
                withOption(option.build());
            }
        };
    }
    static b_DELETE DELETE(
            b_DELETE.k_FROM from_,
            String table_alias,
            b_OUTPUT output){
        return new b_DELETE(){
            {
                withFrom(true);
                withTableAlias(table_alias);
                withOutput(output.build());
            }
        };
    }
    static b_DELETE DELETE(
            b_DELETE.k_FROM from_,
            String table_alias,
            b_FROM from,
            b_WHERE where,
            b_OPTION option){
        return new b_DELETE(){
            {
                withFrom(true);
                withTableAlias(table_alias);
                withFrom(from.build());
                withWhere(where.build());
                withOption(option.build());
            }
        };
    }
    static b_DELETE DELETE(
            b_DELETE.k_FROM from_,
            String table_alias,
            b_FROM from,
            b_WHERE where){
        return new b_DELETE(){
            {
                withFrom(true);
                withTableAlias(table_alias);
                withFrom(from.build());
                withWhere(where.build());
            }
        };
    }
    static b_DELETE DELETE(
            b_DELETE.k_FROM from_,
            String table_alias,
            b_FROM from,
            b_OPTION option){
        return new b_DELETE(){
            {
                withFrom(true);
                withTableAlias(table_alias);
                withFrom(from.build());
                withOption(option.build());
            }
        };
    }
    static b_DELETE DELETE(
            b_DELETE.k_FROM from_,
            String table_alias,
            b_FROM from){
        return new b_DELETE(){
            {
                withFrom(true);
                withTableAlias(table_alias);
                withFrom(from.build());
            }
        };
    }
    static b_DELETE DELETE(
            b_DELETE.k_FROM from_,
            String table_alias,
            b_WHERE where,
            b_OPTION option){
        return new b_DELETE(){
            {
                withFrom(true);
                withTableAlias(table_alias);
                withWhere(where.build());
                withOption(option.build());
            }
        };
    }
    static b_DELETE DELETE(
            b_DELETE.k_FROM from_,
            String table_alias,
            b_WHERE where){
        return new b_DELETE(){
            {
                withFrom(true);
                withTableAlias(table_alias);
                withWhere(where.build());
            }
        };
    }
    static b_DELETE DELETE(
            b_DELETE.k_FROM from_,
            String table_alias,
            b_OPTION option){
        return new b_DELETE(){
            {
                withFrom(true);
                withTableAlias(table_alias);
                withOption(option.build());
            }
        };
    }
    static b_DELETE DELETE(
            b_DELETE.k_FROM from_,
            String table_alias){
        return new b_DELETE(){
            {
                withFrom(true);
                withTableAlias(table_alias);
            }
        };
    }
    static b_DELETE DELETE(
            b_DELETE.k_FROM from_,
            TableName table_or_view_name,
            b$table_hint_limited.b_WITH with,
            b_OUTPUT output,
            b_FROM from,
            b_WHERE where,
            b_OPTION option){
        return new b_DELETE(){
            {
                withTableName(table_or_view_name);
                withTableHint(with.build());
                withOutput(output.build());
                withFrom(from.build());
                withWhere(where.build());
                withOption(option.build());
            }
        };
    }
    static b_DELETE DELETE(
            b_DELETE.k_FROM from_,
            TableName table_or_view_name,
            b$table_hint_limited.b_WITH with,
            b_OUTPUT output,
            b_FROM from,
            b_WHERE where){
        return new b_DELETE(){
            {
                withFrom(true);
                withTableName(table_or_view_name);
                withTableHint(with.build());
                withOutput(output.build());
                withFrom(from.build());
                withWhere(where.build());
            }
        };
    }
    static b_DELETE DELETE(
            b_DELETE.k_FROM from_,
            TableName table_or_view_name,
            b$table_hint_limited.b_WITH with,
            b_OUTPUT output,
            b_FROM from,
            b_OPTION option){
        return new b_DELETE(){
            {
                withFrom(true);
                withTableName(table_or_view_name);
                withTableHint(with.build());
                withOutput(output.build());
                withFrom(from.build());
                withOption(option.build());
            }
        };
    }
    static b_DELETE DELETE(
            b_DELETE.k_FROM from_,
            TableName table_or_view_name,
            b$table_hint_limited.b_WITH with,
            b_OUTPUT output,
            b_FROM from){
        return new b_DELETE(){
            {
                withFrom(true);
                withTableName(table_or_view_name);
                withTableHint(with.build());
                withOutput(output.build());
                withFrom(from.build());
            }
        };
    }
    static b_DELETE DELETE(
            b_DELETE.k_FROM from_,
            TableName table_or_view_name,
            b$table_hint_limited.b_WITH with,
            b_OUTPUT output,
            b_WHERE where,
            b_OPTION option){
        return new b_DELETE(){
            {
                withFrom(true);
                withTableName(table_or_view_name);
                withTableHint(with.build());
                withOutput(output.build());
                withWhere(where.build());
                withOption(option.build());
            }
        };
    }
    static b_DELETE DELETE(
            b_DELETE.k_FROM from_,
            TableName table_or_view_name,
            b$table_hint_limited.b_WITH with,
            b_OUTPUT output,
            b_WHERE where){
        return new b_DELETE(){
            {
                withFrom(true);
                withTableName(table_or_view_name);
                withTableHint(with.build());
                withOutput(output.build());
                withWhere(where.build());
            }
        };
    }
    static b_DELETE DELETE(
            b_DELETE.k_FROM from_,
            TableName table_or_view_name,
            b$table_hint_limited.b_WITH with,
            b_OUTPUT output,
            b_OPTION option){
        return new b_DELETE(){
            {
                withFrom(true);
                withTableName(table_or_view_name);
                withTableHint(with.build());
                withOutput(output.build());
                withOption(option.build());
            }
        };
    }
    static b_DELETE DELETE(
            b_DELETE.k_FROM from_,
            TableName table_or_view_name,
            b$table_hint_limited.b_WITH with,
            b_OUTPUT output){
        return new b_DELETE(){
            {
                withFrom(true);
                withTableName(table_or_view_name);
                withTableHint(with.build());
                withOutput(output.build());
            }
        };
    }
    static b_DELETE DELETE(
            b_DELETE.k_FROM from_,
            TableName table_or_view_name,
            b$table_hint_limited.b_WITH with,
            b_FROM from,
            b_WHERE where,
            b_OPTION option){
        return new b_DELETE(){
            {
                withFrom(true);
                withTableName(table_or_view_name);
                withTableHint(with.build());
                withFrom(from.build());
                withWhere(where.build());
                withOption(option.build());
            }
        };
    }
    static b_DELETE DELETE(
            b_DELETE.k_FROM from_,
            TableName table_or_view_name,
            b$table_hint_limited.b_WITH with,
            b_FROM from,
            b_WHERE where){
        return new b_DELETE(){
            {
                withFrom(true);
                withTableName(table_or_view_name);
                withTableHint(with.build());
                withFrom(from.build());
                withWhere(where.build());
            }
        };
    }
    static b_DELETE DELETE(
            b_DELETE.k_FROM from_,
            TableName table_or_view_name,
            b$table_hint_limited.b_WITH with,
            b_FROM from,
            b_OPTION option){
        return new b_DELETE(){
            {
                withFrom(true);
                withTableName(table_or_view_name);
                withTableHint(with.build());
                withFrom(from.build());
                withOption(option.build());
            }
        };
    }
    static b_DELETE DELETE(
            b_DELETE.k_FROM from_,
            TableName table_or_view_name,
            b$table_hint_limited.b_WITH with,
            b_FROM from){
        return new b_DELETE(){
            {
                withFrom(true);
                withTableName(table_or_view_name);
                withTableHint(with.build());
                withFrom(from.build());
            }
        };
    }
    static b_DELETE DELETE(
            b_DELETE.k_FROM from_,
            TableName table_or_view_name,
            b$table_hint_limited.b_WITH with,
            b_WHERE where,
            b_OPTION option){
        return new b_DELETE(){
            {
                withFrom(true);
                withTableName(table_or_view_name);
                withTableHint(with.build());
                withWhere(where.build());
                withOption(option.build());
            }
        };
    }
    static b_DELETE DELETE(
            b_DELETE.k_FROM from_,
            TableName table_or_view_name,
            b$table_hint_limited.b_WITH with,
            b_WHERE where){
        return new b_DELETE(){
            {
                withFrom(true);
                withTableName(table_or_view_name);
                withTableHint(with.build());
                withWhere(where.build());
            }
        };
    }
    static b_DELETE DELETE(
            b_DELETE.k_FROM from_,
            TableName table_or_view_name,
            b$table_hint_limited.b_WITH with,
            b_OPTION option){
        return new b_DELETE(){
            {
                withFrom(true);
                withTableName(table_or_view_name);
                withTableHint(with.build());
                withOption(option.build());
            }
        };
    }
    static b_DELETE DELETE(
            b_DELETE.k_FROM from_,
            TableName table_or_view_name,
            b$table_hint_limited.b_WITH with){
        return new b_DELETE(){
            {
                withFrom(true);
                withTableName(table_or_view_name);
                withTableHint(with.build());
            }
        };
    }
    static b_DELETE DELETE(
            b_DELETE.k_FROM from_,
            TableName table_or_view_name,
            b_OUTPUT output,
            b_FROM from,
            b_WHERE where,
            b_OPTION option){
        return new b_DELETE(){
            {
                withTableName(table_or_view_name);
                withOutput(output.build());
                withFrom(from.build());
                withWhere(where.build());
                withOption(option.build());
            }
        };
    }
    static b_DELETE DELETE(
            b_DELETE.k_FROM from_,
            TableName table_or_view_name,
            b_OUTPUT output,
            b_FROM from,
            b_WHERE where){
        return new b_DELETE(){
            {
                withFrom(true);
                withTableName(table_or_view_name);
                withOutput(output.build());
                withFrom(from.build());
                withWhere(where.build());
            }
        };
    }
    static b_DELETE DELETE(
            b_DELETE.k_FROM from_,
            TableName table_or_view_name,
            b_OUTPUT output,
            b_FROM from,
            b_OPTION option){
        return new b_DELETE(){
            {
                withFrom(true);
                withTableName(table_or_view_name);
                withOutput(output.build());
                withFrom(from.build());
                withOption(option.build());
            }
        };
    }
    static b_DELETE DELETE(
            b_DELETE.k_FROM from_,
            TableName table_or_view_name,
            b_OUTPUT output,
            b_FROM from){
        return new b_DELETE(){
            {
                withFrom(true);
                withTableName(table_or_view_name);
                withOutput(output.build());
                withFrom(from.build());
            }
        };
    }
    static b_DELETE DELETE(
            b_DELETE.k_FROM from_,
            TableName table_or_view_name,
            b_OUTPUT output,
            b_WHERE where,
            b_OPTION option){
        return new b_DELETE(){
            {
                withFrom(true);
                withTableName(table_or_view_name);
                withOutput(output.build());
                withWhere(where.build());
                withOption(option.build());
            }
        };
    }
    static b_DELETE DELETE(
            b_DELETE.k_FROM from_,
            TableName table_or_view_name,
            b_OUTPUT output,
            b_WHERE where){
        return new b_DELETE(){
            {
                withFrom(true);
                withTableName(table_or_view_name);
                withOutput(output.build());
                withWhere(where.build());
            }
        };
    }
    static b_DELETE DELETE(
            b_DELETE.k_FROM from_,
            TableName table_or_view_name,
            b_OUTPUT output,
            b_OPTION option){
        return new b_DELETE(){
            {
                withFrom(true);
                withTableName(table_or_view_name);
                withOutput(output.build());
                withOption(option.build());
            }
        };
    }
    static b_DELETE DELETE(
            b_DELETE.k_FROM from_,
            TableName table_or_view_name,
            b_OUTPUT output){
        return new b_DELETE(){
            {
                withFrom(true);
                withTableName(table_or_view_name);
                withOutput(output.build());
            }
        };
    }
    static b_DELETE DELETE(
            b_DELETE.k_FROM from_,
            TableName table_or_view_name,
            b_FROM from,
            b_WHERE where,
            b_OPTION option){
        return new b_DELETE(){
            {
                withFrom(true);
                withTableName(table_or_view_name);
                withFrom(from.build());
                withWhere(where.build());
                withOption(option.build());
            }
        };
    }
    static b_DELETE DELETE(
            b_DELETE.k_FROM from_,
            TableName table_or_view_name,
            b_FROM from,
            b_WHERE where){
        return new b_DELETE(){
            {
                withFrom(true);
                withTableName(table_or_view_name);
                withFrom(from.build());
                withWhere(where.build());
            }
        };
    }
    static b_DELETE DELETE(
            b_DELETE.k_FROM from_,
            TableName table_or_view_name,
            b_FROM from,
            b_OPTION option){
        return new b_DELETE(){
            {
                withFrom(true);
                withTableName(table_or_view_name);
                withFrom(from.build());
                withOption(option.build());
            }
        };
    }
    static b_DELETE DELETE(
            b_DELETE.k_FROM from_,
            TableName table_or_view_name,
            b_FROM from){
        return new b_DELETE(){
            {
                withFrom(true);
                withTableName(table_or_view_name);
                withFrom(from.build());
            }
        };
    }
    static b_DELETE DELETE(
            b_DELETE.k_FROM from_,
            TableName table_or_view_name,
            b_WHERE where,
            b_OPTION option){
        return new b_DELETE(){
            {
                withFrom(true);
                withTableName(table_or_view_name);
                withWhere(where.build());
                withOption(option.build());
            }
        };
    }
    static b_DELETE DELETE(
            b_DELETE.k_FROM from_,
            TableName table_or_view_name,
            b_WHERE where){
        return new b_DELETE(){
            {
                withFrom(true);
                withTableName(table_or_view_name);
                withWhere(where.build());
            }
        };
    }
    static b_DELETE DELETE(
            b_DELETE.k_FROM from_,
            TableName table_or_view_name,
            b_OPTION option){
        return new b_DELETE(){
            {
                withFrom(true);
                withTableName(table_or_view_name);
                withOption(option.build());
            }
        };
    }
    static b_DELETE DELETE(
            b_DELETE.k_FROM from_,
            TableName table_or_view_name){
        return new b_DELETE(){
            {
                withFrom(true);
                withTableName(table_or_view_name);
            }
        };
    }

    static b_DELETE DELETE(
            String table_alias,
            b$table_hint_limited.b_WITH with,
            b_OUTPUT output,
            b_FROM from,
            b_WHERE where,
            b_OPTION option){
        return new b_DELETE(){
            {
                withTableAlias(table_alias);
                withTableHint(with.build());
                withOutput(output.build());
                withFrom(from.build());
                withWhere(where.build());
                withOption(option.build());
            }
        };
    }
    static b_DELETE DELETE(
            String table_alias,
            b$table_hint_limited.b_WITH with,
            b_OUTPUT output,
            b_FROM from,
            b_WHERE where){
        return new b_DELETE(){
            {
                withTableAlias(table_alias);
                withTableHint(with.build());
                withOutput(output.build());
                withFrom(from.build());
                withWhere(where.build());
            }
        };
    }
    static b_DELETE DELETE(
            String table_alias,
            b$table_hint_limited.b_WITH with,
            b_OUTPUT output,
            b_FROM from,
            b_OPTION option){
        return new b_DELETE(){
            {
                withTableAlias(table_alias);
                withTableHint(with.build());
                withOutput(output.build());
                withFrom(from.build());
                withOption(option.build());
            }
        };
    }
    static b_DELETE DELETE(
            String table_alias,
            b$table_hint_limited.b_WITH with,
            b_OUTPUT output,
            b_FROM from){
        return new b_DELETE(){
            {
                withTableAlias(table_alias);
                withTableHint(with.build());
                withOutput(output.build());
                withFrom(from.build());
            }
        };
    }
    static b_DELETE DELETE(
            String table_alias,
            b$table_hint_limited.b_WITH with,
            b_OUTPUT output,
            b_WHERE where,
            b_OPTION option){
        return new b_DELETE(){
            {
                withTableAlias(table_alias);
                withTableHint(with.build());
                withOutput(output.build());
                withWhere(where.build());
                withOption(option.build());
            }
        };
    }
    static b_DELETE DELETE(
            String table_alias,
            b$table_hint_limited.b_WITH with,
            b_OUTPUT output,
            b_WHERE where){
        return new b_DELETE(){
            {
                withTableAlias(table_alias);
                withTableHint(with.build());
                withOutput(output.build());
                withWhere(where.build());
            }
        };
    }
    static b_DELETE DELETE(
            String table_alias,
            b$table_hint_limited.b_WITH with,
            b_OUTPUT output,
            b_OPTION option){
        return new b_DELETE(){
            {
                withTableAlias(table_alias);
                withTableHint(with.build());
                withOutput(output.build());
                withOption(option.build());
            }
        };
    }
    static b_DELETE DELETE(
            String table_alias,
            b$table_hint_limited.b_WITH with,
            b_OUTPUT output){
        return new b_DELETE(){
            {
                withTableAlias(table_alias);
                withTableHint(with.build());
                withOutput(output.build());
            }
        };
    }
    static b_DELETE DELETE(
            String table_alias,
            b$table_hint_limited.b_WITH with,
            b_FROM from,
            b_WHERE where,
            b_OPTION option){
        return new b_DELETE(){
            {
                withTableAlias(table_alias);
                withTableHint(with.build());
                withFrom(from.build());
                withWhere(where.build());
                withOption(option.build());
            }
        };
    }
    static b_DELETE DELETE(
            String table_alias,
            b$table_hint_limited.b_WITH with,
            b_FROM from,
            b_WHERE where){
        return new b_DELETE(){
            {
                withTableAlias(table_alias);
                withTableHint(with.build());
                withFrom(from.build());
                withWhere(where.build());
            }
        };
    }
    static b_DELETE DELETE(
            String table_alias,
            b$table_hint_limited.b_WITH with,
            b_FROM from,
            b_OPTION option){
        return new b_DELETE(){
            {
                withTableAlias(table_alias);
                withTableHint(with.build());
                withFrom(from.build());
                withOption(option.build());
            }
        };
    }
    static b_DELETE DELETE(
            String table_alias,
            b$table_hint_limited.b_WITH with,
            b_FROM from){
        return new b_DELETE(){
            {
                withTableAlias(table_alias);
                withTableHint(with.build());
                withFrom(from.build());
            }
        };
    }
    static b_DELETE DELETE(
            String table_alias,
            b$table_hint_limited.b_WITH with,
            b_WHERE where,
            b_OPTION option){
        return new b_DELETE(){
            {
                withTableAlias(table_alias);
                withTableHint(with.build());
                withWhere(where.build());
                withOption(option.build());
            }
        };
    }
    static b_DELETE DELETE(
            String table_alias,
            b$table_hint_limited.b_WITH with,
            b_WHERE where){
        return new b_DELETE(){
            {
                withTableAlias(table_alias);
                withTableHint(with.build());
                withWhere(where.build());
            }
        };
    }
    static b_DELETE DELETE(
            String table_alias,
            b$table_hint_limited.b_WITH with,
            b_OPTION option){
        return new b_DELETE(){
            {
                withTableAlias(table_alias);
                withTableHint(with.build());
                withOption(option.build());
            }
        };
    }
    static b_DELETE DELETE(
            String table_alias,
            b$table_hint_limited.b_WITH with){
        return new b_DELETE(){
            {
                withTableAlias(table_alias);
                withTableHint(with.build());
            }
        };
    }

    static b_DELETE DELETE(
            String table_alias,
            b_OUTPUT output,
            b_FROM from,
            b_WHERE where,
            b_OPTION option){
        return new b_DELETE(){
            {
                withTableAlias(table_alias);
                withOutput(output.build());
                withFrom(from.build());
                withWhere(where.build());
                withOption(option.build());
            }
        };
    }
    static b_DELETE DELETE(
            String table_alias,
            b_OUTPUT output,
            b_FROM from,
            b_WHERE where){
        return new b_DELETE(){
            {
                withTableAlias(table_alias);
                withOutput(output.build());
                withFrom(from.build());
                withWhere(where.build());
            }
        };
    }
    static b_DELETE DELETE(
            String table_alias,
            b_OUTPUT output,
            b_FROM from,
            b_OPTION option){
        return new b_DELETE(){
            {
                withTableAlias(table_alias);
                withOutput(output.build());
                withFrom(from.build());
                withOption(option.build());
            }
        };
    }
    static b_DELETE DELETE(
            String table_alias,
            b_OUTPUT output,
            b_FROM from){
        return new b_DELETE(){
            {
                withTableAlias(table_alias);
                withOutput(output.build());
                withFrom(from.build());
            }
        };
    }
    static b_DELETE DELETE(
            String table_alias,
            b_OUTPUT output,
            b_WHERE where,
            b_OPTION option){
        return new b_DELETE(){
            {
                withTableAlias(table_alias);
                withOutput(output.build());
                withWhere(where.build());
                withOption(option.build());
            }
        };
    }
    static b_DELETE DELETE(
            String table_alias,
            b_OUTPUT output,
            b_WHERE where){
        return new b_DELETE(){
            {
                withTableAlias(table_alias);
                withOutput(output.build());
                withWhere(where.build());
            }
        };
    }
    static b_DELETE DELETE(
            String table_alias,
            b_OUTPUT output,
            b_OPTION option){
        return new b_DELETE(){
            {
                withTableAlias(table_alias);
                withOutput(output.build());
                withOption(option.build());
            }
        };
    }
    static b_DELETE DELETE(
            String table_alias,
            b_OUTPUT output){
        return new b_DELETE(){
            {
                withTableAlias(table_alias);
                withOutput(output.build());
            }
        };
    }
    static b_DELETE DELETE(
            String table_alias,
            b_FROM from,
            b_WHERE where,
            b_OPTION option){
        return new b_DELETE(){
            {
                withTableAlias(table_alias);
                withFrom(from.build());
                withWhere(where.build());
                withOption(option.build());
            }
        };
    }
    static b_DELETE DELETE(
            String table_alias,
            b_FROM from,
            b_WHERE where){
        return new b_DELETE(){
            {
                withTableAlias(table_alias);
                withFrom(from.build());
                withWhere(where.build());
            }
        };
    }
    static b_DELETE DELETE(
            String table_alias,
            b_FROM from,
            b_OPTION option){
        return new b_DELETE(){
            {
                withTableAlias(table_alias);
                withFrom(from.build());
                withOption(option.build());
            }
        };
    }
    static b_DELETE DELETE(
            String table_alias,
            b_FROM from){
        return new b_DELETE(){
            {
                withTableAlias(table_alias);
                withFrom(from.build());
            }
        };
    }
    static b_DELETE DELETE(
            String table_alias,
            b_WHERE where,
            b_OPTION option){
        return new b_DELETE(){
            {
                withTableAlias(table_alias);
                withWhere(where.build());
                withOption(option.build());
            }
        };
    }
    static b_DELETE DELETE(
            String table_alias,
            b_WHERE where){
        return new b_DELETE(){
            {
                withTableAlias(table_alias);
                withWhere(where.build());
            }
        };
    }
    static b_DELETE DELETE(
            String table_alias,
            b_OPTION option){
        return new b_DELETE(){
            {
                withTableAlias(table_alias);
                withOption(option.build());
            }
        };
    }
    static b_DELETE DELETE(
            String table_alias){
        return new b_DELETE(){
            {
                withTableAlias(table_alias);
            }
        };
    }
    static b_DELETE DELETE(
            TableName table_or_view_name,
            b$table_hint_limited.b_WITH with,
            b_OUTPUT output,
            b_FROM from,
            b_WHERE where,
            b_OPTION option){
        return new b_DELETE(){
            {
                withTableName(table_or_view_name);
                withTableHint(with.build());
                withOutput(output.build());
                withFrom(from.build());
                withWhere(where.build());
                withOption(option.build());
            }
        };
    }
    static b_DELETE DELETE(
            TableName table_or_view_name,
            b$table_hint_limited.b_WITH with,
            b_OUTPUT output,
            b_FROM from,
            b_WHERE where){
        return new b_DELETE(){
            {
                withTableName(table_or_view_name);
                withTableHint(with.build());
                withOutput(output.build());
                withFrom(from.build());
                withWhere(where.build());
            }
        };
    }
    static b_DELETE DELETE(
            TableName table_or_view_name,
            b$table_hint_limited.b_WITH with,
            b_OUTPUT output,
            b_FROM from,
            b_OPTION option){
        return new b_DELETE(){
            {
                withTableName(table_or_view_name);
                withTableHint(with.build());
                withOutput(output.build());
                withFrom(from.build());
                withOption(option.build());
            }
        };
    }
    static b_DELETE DELETE(
            TableName table_or_view_name,
            b$table_hint_limited.b_WITH with,
            b_OUTPUT output,
            b_FROM from){
        return new b_DELETE(){
            {
                withTableName(table_or_view_name);
                withTableHint(with.build());
                withOutput(output.build());
                withFrom(from.build());
            }
        };
    }
    static b_DELETE DELETE(
            TableName table_or_view_name,
            b$table_hint_limited.b_WITH with,
            b_OUTPUT output,
            b_WHERE where,
            b_OPTION option){
        return new b_DELETE(){
            {
                withTableName(table_or_view_name);
                withTableHint(with.build());
                withOutput(output.build());
                withWhere(where.build());
                withOption(option.build());
            }
        };
    }
    static b_DELETE DELETE(
            TableName table_or_view_name,
            b$table_hint_limited.b_WITH with,
            b_OUTPUT output,
            b_WHERE where){
        return new b_DELETE(){
            {
                withTableName(table_or_view_name);
                withTableHint(with.build());
                withOutput(output.build());
                withWhere(where.build());
            }
        };
    }
    static b_DELETE DELETE(
            TableName table_or_view_name,
            b$table_hint_limited.b_WITH with,
            b_OUTPUT output,
            b_OPTION option){
        return new b_DELETE(){
            {
                withTableName(table_or_view_name);
                withTableHint(with.build());
                withOutput(output.build());
                withOption(option.build());
            }
        };
    }
    static b_DELETE DELETE(
            TableName table_or_view_name,
            b$table_hint_limited.b_WITH with,
            b_OUTPUT output){
        return new b_DELETE(){
            {
                withTableName(table_or_view_name);
                withTableHint(with.build());
                withOutput(output.build());
            }
        };
    }
    static b_DELETE DELETE(
            TableName table_or_view_name,
            b$table_hint_limited.b_WITH with,
            b_FROM from,
            b_WHERE where,
            b_OPTION option){
        return new b_DELETE(){
            {
                withTableName(table_or_view_name);
                withTableHint(with.build());
                withFrom(from.build());
                withWhere(where.build());
                withOption(option.build());
            }
        };
    }
    static b_DELETE DELETE(
            TableName table_or_view_name,
            b$table_hint_limited.b_WITH with,
            b_FROM from,
            b_WHERE where){
        return new b_DELETE(){
            {
                withTableName(table_or_view_name);
                withTableHint(with.build());
                withFrom(from.build());
                withWhere(where.build());
            }
        };
    }
    static b_DELETE DELETE(
            TableName table_or_view_name,
            b$table_hint_limited.b_WITH with,
            b_FROM from,
            b_OPTION option){
        return new b_DELETE(){
            {
                withTableName(table_or_view_name);
                withTableHint(with.build());
                withFrom(from.build());
                withOption(option.build());
            }
        };
    }
    static b_DELETE DELETE(
            TableName table_or_view_name,
            b$table_hint_limited.b_WITH with,
            b_FROM from){
        return new b_DELETE(){
            {
                withTableName(table_or_view_name);
                withTableHint(with.build());
                withFrom(from.build());
            }
        };
    }
    static b_DELETE DELETE(
            TableName table_or_view_name,
            b$table_hint_limited.b_WITH with,
            b_WHERE where,
            b_OPTION option){
        return new b_DELETE(){
            {
                withTableName(table_or_view_name);
                withTableHint(with.build());
                withWhere(where.build());
                withOption(option.build());
            }
        };
    }
    static b_DELETE DELETE(
            TableName table_or_view_name,
            b$table_hint_limited.b_WITH with,
            b_WHERE where){
        return new b_DELETE(){
            {
                withTableName(table_or_view_name);
                withTableHint(with.build());
                withWhere(where.build());
            }
        };
    }
    static b_DELETE DELETE(
            TableName table_or_view_name,
            b$table_hint_limited.b_WITH with,
            b_OPTION option){
        return new b_DELETE(){
            {
                withTableName(table_or_view_name);
                withTableHint(with.build());
                withOption(option.build());
            }
        };
    }
    static b_DELETE DELETE(
            TableName table_or_view_name,
            b$table_hint_limited.b_WITH with){
        return new b_DELETE(){
            {
                withTableName(table_or_view_name);
                withTableHint(with.build());
            }
        };
    }
    static b_DELETE DELETE(
            TableName table_or_view_name,
            b_OUTPUT output,
            b_FROM from,
            b_WHERE where,
            b_OPTION option){
        return new b_DELETE(){
            {
                withTableName(table_or_view_name);
                withOutput(output.build());
                withFrom(from.build());
                withWhere(where.build());
                withOption(option.build());
            }
        };
    }
    static b_DELETE DELETE(
            TableName table_or_view_name,
            b_OUTPUT output,
            b_FROM from,
            b_WHERE where){
        return new b_DELETE(){
            {
                withTableName(table_or_view_name);
                withOutput(output.build());
                withFrom(from.build());
                withWhere(where.build());
            }
        };
    }
    static b_DELETE DELETE(
            TableName table_or_view_name,
            b_OUTPUT output,
            b_FROM from,
            b_OPTION option){
        return new b_DELETE(){
            {
                withTableName(table_or_view_name);
                withOutput(output.build());
                withFrom(from.build());
                withOption(option.build());
            }
        };
    }
    static b_DELETE DELETE(
            TableName table_or_view_name,
            b_OUTPUT output,
            b_FROM from){
        return new b_DELETE(){
            {
                withTableName(table_or_view_name);
                withOutput(output.build());
                withFrom(from.build());
            }
        };
    }
    static b_DELETE DELETE(
            TableName table_or_view_name,
            b_OUTPUT output,
            b_WHERE where,
            b_OPTION option){
        return new b_DELETE(){
            {
                withTableName(table_or_view_name);
                withOutput(output.build());
                withWhere(where.build());
                withOption(option.build());
            }
        };
    }
    static b_DELETE DELETE(
            TableName table_or_view_name,
            b_OUTPUT output,
            b_WHERE where){
        return new b_DELETE(){
            {
                withTableName(table_or_view_name);
                withOutput(output.build());
                withWhere(where.build());
            }
        };
    }
    static b_DELETE DELETE(
            TableName table_or_view_name,
            b_OUTPUT output,
            b_OPTION option){
        return new b_DELETE(){
            {
                withTableName(table_or_view_name);
                withOutput(output.build());
                withOption(option.build());
            }
        };
    }
    static b_DELETE DELETE(
            TableName table_or_view_name,
            b_OUTPUT output){
        return new b_DELETE(){
            {
                withTableName(table_or_view_name);
                withOutput(output.build());
            }
        };
    }
    static b_DELETE DELETE(
            TableName table_or_view_name,
            b_FROM from,
            b_WHERE where,
            b_OPTION option){
        return new b_DELETE(){
            {
                withTableName(table_or_view_name);
                withFrom(from.build());
                withWhere(where.build());
                withOption(option.build());
            }
        };
    }
    static b_DELETE DELETE(
            TableName table_or_view_name,
            b_FROM from,
            b_WHERE where){
        return new b_DELETE(){
            {
                withTableName(table_or_view_name);
                withFrom(from.build());
                withWhere(where.build());
            }
        };
    }
    static b_DELETE DELETE(
            TableName table_or_view_name,
            b_FROM from,
            b_OPTION option){
        return new b_DELETE(){
            {
                withTableName(table_or_view_name);
                withFrom(from.build());
                withOption(option.build());
            }
        };
    }
    static b_DELETE DELETE(
            TableName table_or_view_name,
            b_FROM from){
        return new b_DELETE(){
            {
                withTableName(table_or_view_name);
                withFrom(from.build());
            }
        };
    }
    static b_DELETE DELETE(
            TableName table_or_view_name,
            b_WHERE where,
            b_OPTION option){
        return new b_DELETE(){
            {
                withTableName(table_or_view_name);
                withWhere(where.build());
                withOption(option.build());
            }
        };
    }
    static b_DELETE DELETE(
            TableName table_or_view_name,
            b_WHERE where){
        return new b_DELETE(){
            {
                withTableName(table_or_view_name);
                withWhere(where.build());
            }
        };
    }
    static b_DELETE DELETE(
            TableName table_or_view_name,
            b_OPTION option){
        return new b_DELETE(){
            {
                withTableName(table_or_view_name);
                withOption(option.build());
            }
        };
    }
    static b_DELETE DELETE(
            TableName table_or_view_name){
        return new b_DELETE(){
            {
                withTableName(table_or_view_name);
            }
        };
    }


    //
    static b_DELETE.k_FROM FROM_(){
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

}
