package com.xy.xsql.tsql.style.constructor.introducer.statements;

import com.xy.xsql.core.builder.simple.CodeBuilder;
import com.xy.xsql.tsql.model.datatypes.table.ColumnName;
import com.xy.xsql.tsql.model.datatypes.table.TableName;
import com.xy.xsql.tsql.model.queries.Select;
import com.xy.xsql.tsql.model.queries.predicates.Predicate;
import com.xy.xsql.tsql.style.constructor.builder.queries.*;
import com.xy.xsql.tsql.style.constructor.builder.queries.hints.b$table_hint_limited;
import com.xy.xsql.tsql.style.constructor.builder.queries.select.b_SELECT;
import com.xy.xsql.tsql.style.constructor.builder.statements.b_INSERT;
import com.xy.xsql.tsql.style.constructor.builder.statements.b_MERGE;

import java.util.Arrays;

import static com.xy.xsql.core.handler.list.ListHandler.list;
import static com.xy.xsql.tsql.builder.chain.elements.expressions.Expressions.e_variable;

/**
 * Created by xiaoyao9184 on 2018/5/9.
 */
@SuppressWarnings({"WeakerAccess", "unused"})
public interface merge {

    static b_MERGE $(
            b_WITH with_,
            b_MERGE merge){
        merge.withWith(with_.build());
        return merge;
    }

    //

    static b_MERGE MERGE(
            b_TOP top,
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_WITH with,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OUTPUT output,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTop(top.build());
                withInto();
                withTargetTable(table_name);
                withMergeHint(with.build());
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOutput(output.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_WITH with,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OUTPUT output){
        return new b_MERGE(){
            {
                withTop(top.build());
                withInto();
                withTargetTable(table_name);
                withMergeHint(with.build());
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOutput(output.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_WITH with,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTop(top.build());
                withInto();
                withTargetTable(table_name);
                withMergeHint(with.build());
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_WITH with,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source){
        return new b_MERGE(){
            {
                withTop(top.build());
                withInto();
                withTargetTable(table_name);
                withMergeHint(with.build());
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_WITH with,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_OUTPUT output,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTop(top.build());
                withInto();
                withTargetTable(table_name);
                withMergeHint(with.build());
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withOutput(output.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_WITH with,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_OUTPUT output){
        return new b_MERGE(){
            {
                withTop(top.build());
                withInto();
                withTargetTable(table_name);
                withMergeHint(with.build());
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withOutput(output.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_WITH with,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTop(top.build());
                withInto();
                withTargetTable(table_name);
                withMergeHint(with.build());
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_WITH with,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched){
        return new b_MERGE(){
            {
                withTop(top.build());
                withInto();
                withTargetTable(table_name);
                withMergeHint(with.build());
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_WITH with,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OUTPUT output,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTop(top.build());
                withInto();
                withTargetTable(table_name);
                withMergeHint(with.build());
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOutput(output.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_WITH with,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OUTPUT output){
        return new b_MERGE(){
            {
                withTop(top.build());
                withInto();
                withTargetTable(table_name);
                withMergeHint(with.build());
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOutput(output.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_WITH with,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTop(top.build());
                withInto();
                withTargetTable(table_name);
                withMergeHint(with.build());
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_WITH with,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source){
        return new b_MERGE(){
            {
                withTop(top.build());
                withInto();
                withTargetTable(table_name);
                withMergeHint(with.build());
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_WITH with,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_OUTPUT output,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTop(top.build());
                withInto();
                withTargetTable(table_name);
                withMergeHint(with.build());
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withOutput(output.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_WITH with,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_OUTPUT output){
        return new b_MERGE(){
            {
                withTop(top.build());
                withInto();
                withTargetTable(table_name);
                withMergeHint(with.build());
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withOutput(output.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_WITH with,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTop(top.build());
                withInto();
                withTargetTable(table_name);
                withMergeHint(with.build());
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_WITH with,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched){
        return new b_MERGE(){
            {
                withTop(top.build());
                withInto();
                withTargetTable(table_name);
                withMergeHint(with.build());
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_WITH with,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OUTPUT output,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTop(top.build());
                withInto();
                withTargetTable(table_name);
                withMergeHint(with.build());
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOutput(output.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_WITH with,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OUTPUT output){
        return new b_MERGE(){
            {
                withTop(top.build());
                withInto();
                withTargetTable(table_name);
                withMergeHint(with.build());
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOutput(output.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_WITH with,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTop(top.build());
                withInto();
                withTargetTable(table_name);
                withMergeHint(with.build());
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_WITH with,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source){
        return new b_MERGE(){
            {
                withTop(top.build());
                withInto();
                withTargetTable(table_name);
                withMergeHint(with.build());
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_WITH with,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_OUTPUT output,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTop(top.build());
                withInto();
                withTargetTable(table_name);
                withMergeHint(with.build());
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withOutput(output.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_WITH with,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_OUTPUT output){
        return new b_MERGE(){
            {
                withTop(top.build());
                withInto();
                withTargetTable(table_name);
                withMergeHint(with.build());
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withOutput(output.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_WITH with,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTop(top.build());
                withInto();
                withTargetTable(table_name);
                withMergeHint(with.build());
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_WITH with,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched){
        return new b_MERGE(){
            {
                withTop(top.build());
                withInto();
                withTargetTable(table_name);
                withMergeHint(with.build());
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_WITH with,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OUTPUT output,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTop(top.build());
                withInto();
                withTargetTable(table_name);
                withMergeHint(with.build());
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOutput(output.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_WITH with,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OUTPUT output){
        return new b_MERGE(){
            {
                withTop(top.build());
                withInto();
                withTargetTable(table_name);
                withMergeHint(with.build());
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOutput(output.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_WITH with,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTop(top.build());
                withInto();
                withTargetTable(table_name);
                withMergeHint(with.build());
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_WITH with,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source){
        return new b_MERGE(){
            {
                withTop(top.build());
                withInto();
                withTargetTable(table_name);
                withMergeHint(with.build());
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_WITH with,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_OUTPUT output,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTop(top.build());
                withInto();
                withTargetTable(table_name);
                withMergeHint(with.build());
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withOutput(output.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_WITH with,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_OUTPUT output){
        return new b_MERGE(){
            {
                withTop(top.build());
                withInto();
                withTargetTable(table_name);
                withMergeHint(with.build());
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withOutput(output.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_WITH with,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTop(top.build());
                withInto();
                withTargetTable(table_name);
                withMergeHint(with.build());
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_WITH with,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on){
        return new b_MERGE(){
            {
                withTop(top.build());
                withInto();
                withTargetTable(table_name);
                withMergeHint(with.build());
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_WITH with,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OUTPUT output,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTop(top.build());
                withInto();
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOutput(output.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_WITH with,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OUTPUT output){
        return new b_MERGE(){
            {
                withTop(top.build());
                withInto();
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOutput(output.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_WITH with,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTop(top.build());
                withInto();
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_WITH with,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source){
        return new b_MERGE(){
            {
                withTop(top.build());
                withInto();
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_WITH with,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_OUTPUT output,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTop(top.build());
                withInto();
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withOutput(output.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_WITH with,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_OUTPUT output){
        return new b_MERGE(){
            {
                withTop(top.build());
                withInto();
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withOutput(output.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_WITH with,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTop(top.build());
                withInto();
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_WITH with,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched){
        return new b_MERGE(){
            {
                withTop(top.build());
                withInto();
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_WITH with,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OUTPUT output,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTop(top.build());
                withInto();
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOutput(output.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_WITH with,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OUTPUT output){
        return new b_MERGE(){
            {
                withTop(top.build());
                withInto();
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOutput(output.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_WITH with,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTop(top.build());
                withInto();
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_WITH with,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source){
        return new b_MERGE(){
            {
                withTop(top.build());
                withInto();
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_WITH with,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_OUTPUT output,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTop(top.build());
                withInto();
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withOutput(output.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_WITH with,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_OUTPUT output){
        return new b_MERGE(){
            {
                withTop(top.build());
                withInto();
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withOutput(output.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_WITH with,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTop(top.build());
                withInto();
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_WITH with,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched){
        return new b_MERGE(){
            {
                withTop(top.build());
                withInto();
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_WITH with,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OUTPUT output,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTop(top.build());
                withInto();
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOutput(output.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_WITH with,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OUTPUT output){
        return new b_MERGE(){
            {
                withTop(top.build());
                withInto();
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOutput(output.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_WITH with,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTop(top.build());
                withInto();
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_WITH with,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source){
        return new b_MERGE(){
            {
                withTop(top.build());
                withInto();
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_WITH with,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_OUTPUT output,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTop(top.build());
                withInto();
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withOutput(output.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_WITH with,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_OUTPUT output){
        return new b_MERGE(){
            {
                withTop(top.build());
                withInto();
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withOutput(output.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_WITH with,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTop(top.build());
                withInto();
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_WITH with,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched){
        return new b_MERGE(){
            {
                withTop(top.build());
                withInto();
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_WITH with,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OUTPUT output,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTop(top.build());
                withInto();
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOutput(output.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_WITH with,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OUTPUT output){
        return new b_MERGE(){
            {
                withTop(top.build());
                withInto();
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOutput(output.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_WITH with,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTop(top.build());
                withInto();
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_WITH with,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source){
        return new b_MERGE(){
            {
                withTop(top.build());
                withInto();
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_WITH with,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_OUTPUT output,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTop(top.build());
                withInto();
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withOutput(output.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_WITH with,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_OUTPUT output){
        return new b_MERGE(){
            {
                withTop(top.build());
                withInto();
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withOutput(output.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_WITH with,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTop(top.build());
                withInto();
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_WITH with,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on){
        return new b_MERGE(){
            {
                withTop(top.build());
                withInto();
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
            }
        };
    }

    static b_MERGE MERGE(
            b_TOP top,
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_WITH with,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OUTPUT output,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTop(top.build());
                withInto();
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOutput(output.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_WITH with,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OUTPUT output){
        return new b_MERGE(){
            {
                withTop(top.build());
                withInto();
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOutput(output.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_WITH with,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTop(top.build());
                withInto();
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_WITH with,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source){
        return new b_MERGE(){
            {
                withTop(top.build());
                withInto();
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_WITH with,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_OUTPUT output,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTop(top.build());
                withInto();
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withOutput(output.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_WITH with,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_OUTPUT output){
        return new b_MERGE(){
            {
                withTop(top.build());
                withInto();
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withOutput(output.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_WITH with,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTop(top.build());
                withInto();
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_WITH with,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched){
        return new b_MERGE(){
            {
                withTop(top.build());
                withInto();
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_WITH with,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OUTPUT output,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTop(top.build());
                withInto();
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOutput(output.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_WITH with,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OUTPUT output){
        return new b_MERGE(){
            {
                withTop(top.build());
                withInto();
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOutput(output.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_WITH with,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTop(top.build());
                withInto();
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_WITH with,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source){
        return new b_MERGE(){
            {
                withTop(top.build());
                withInto();
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_WITH with,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_OUTPUT output,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTop(top.build());
                withInto();
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withOutput(output.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_WITH with,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_OUTPUT output){
        return new b_MERGE(){
            {
                withTop(top.build());
                withInto();
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withOutput(output.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_WITH with,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTop(top.build());
                withInto();
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_WITH with,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched){
        return new b_MERGE(){
            {
                withTop(top.build());
                withInto();
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_WITH with,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OUTPUT output,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTop(top.build());
                withInto();
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOutput(output.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_WITH with,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OUTPUT output){
        return new b_MERGE(){
            {
                withTop(top.build());
                withInto();
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOutput(output.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_WITH with,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTop(top.build());
                withInto();
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_WITH with,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source){
        return new b_MERGE(){
            {
                withTop(top.build());
                withInto();
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_WITH with,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_OUTPUT output,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTop(top.build());
                withInto();
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withOutput(output.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_WITH with,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_OUTPUT output){
        return new b_MERGE(){
            {
                withTop(top.build());
                withInto();
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withOutput(output.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_WITH with,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTop(top.build());
                withInto();
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_WITH with,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched){
        return new b_MERGE(){
            {
                withTop(top.build());
                withInto();
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_WITH with,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OUTPUT output,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTop(top.build());
                withInto();
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOutput(output.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_WITH with,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OUTPUT output){
        return new b_MERGE(){
            {
                withTop(top.build());
                withInto();
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOutput(output.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_WITH with,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTop(top.build());
                withInto();
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_WITH with,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source){
        return new b_MERGE(){
            {
                withTop(top.build());
                withInto();
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_WITH with,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_OUTPUT output,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTop(top.build());
                withInto();
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withOutput(output.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_WITH with,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_OUTPUT output){
        return new b_MERGE(){
            {
                withTop(top.build());
                withInto();
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withOutput(output.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_WITH with,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTop(top.build());
                withInto();
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_WITH with,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on){
        return new b_MERGE(){
            {
                withTop(top.build());
                withInto();
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            b_MERGE.k_INTO into,
            TableName table_name,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OUTPUT output,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTop(top.build());
                withInto();
                withTargetTable(table_name);
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOutput(output.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            b_MERGE.k_INTO into,
            TableName table_name,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OUTPUT output){
        return new b_MERGE(){
            {
                withTop(top.build());
                withInto();
                withTargetTable(table_name);
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOutput(output.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            b_MERGE.k_INTO into,
            TableName table_name,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTop(top.build());
                withInto();
                withTargetTable(table_name);
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            b_MERGE.k_INTO into,
            TableName table_name,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source){
        return new b_MERGE(){
            {
                withTop(top.build());
                withInto();
                withTargetTable(table_name);
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            b_MERGE.k_INTO into,
            TableName table_name,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_OUTPUT output,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTop(top.build());
                withInto();
                withTargetTable(table_name);
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withOutput(output.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            b_MERGE.k_INTO into,
            TableName table_name,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_OUTPUT output){
        return new b_MERGE(){
            {
                withTop(top.build());
                withInto();
                withTargetTable(table_name);
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withOutput(output.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            b_MERGE.k_INTO into,
            TableName table_name,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTop(top.build());
                withInto();
                withTargetTable(table_name);
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            b_MERGE.k_INTO into,
            TableName table_name,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched){
        return new b_MERGE(){
            {
                withTop(top.build());
                withInto();
                withTargetTable(table_name);
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            b_MERGE.k_INTO into,
            TableName table_name,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OUTPUT output,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTop(top.build());
                withInto();
                withTargetTable(table_name);
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOutput(output.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            b_MERGE.k_INTO into,
            TableName table_name,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OUTPUT output){
        return new b_MERGE(){
            {
                withTop(top.build());
                withInto();
                withTargetTable(table_name);
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOutput(output.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            b_MERGE.k_INTO into,
            TableName table_name,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTop(top.build());
                withInto();
                withTargetTable(table_name);
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            b_MERGE.k_INTO into,
            TableName table_name,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source){
        return new b_MERGE(){
            {
                withTop(top.build());
                withInto();
                withTargetTable(table_name);
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            b_MERGE.k_INTO into,
            TableName table_name,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_OUTPUT output,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTop(top.build());
                withInto();
                withTargetTable(table_name);
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withOutput(output.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            b_MERGE.k_INTO into,
            TableName table_name,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_OUTPUT output){
        return new b_MERGE(){
            {
                withTop(top.build());
                withInto();
                withTargetTable(table_name);
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withOutput(output.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            b_MERGE.k_INTO into,
            TableName table_name,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTop(top.build());
                withInto();
                withTargetTable(table_name);
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            b_MERGE.k_INTO into,
            TableName table_name,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched){
        return new b_MERGE(){
            {
                withTop(top.build());
                withInto();
                withTargetTable(table_name);
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            b_MERGE.k_INTO into,
            TableName table_name,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OUTPUT output,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTop(top.build());
                withInto();
                withTargetTable(table_name);
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOutput(output.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            b_MERGE.k_INTO into,
            TableName table_name,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OUTPUT output){
        return new b_MERGE(){
            {
                withTop(top.build());
                withInto();
                withTargetTable(table_name);
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOutput(output.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            b_MERGE.k_INTO into,
            TableName table_name,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTop(top.build());
                withInto();
                withTargetTable(table_name);
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            b_MERGE.k_INTO into,
            TableName table_name,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source){
        return new b_MERGE(){
            {
                withTop(top.build());
                withInto();
                withTargetTable(table_name);
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            b_MERGE.k_INTO into,
            TableName table_name,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_OUTPUT output,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTop(top.build());
                withInto();
                withTargetTable(table_name);
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withOutput(output.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            b_MERGE.k_INTO into,
            TableName table_name,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_OUTPUT output){
        return new b_MERGE(){
            {
                withTop(top.build());
                withInto();
                withTargetTable(table_name);
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withOutput(output.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            b_MERGE.k_INTO into,
            TableName table_name,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTop(top.build());
                withInto();
                withTargetTable(table_name);
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            b_MERGE.k_INTO into,
            TableName table_name,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched){
        return new b_MERGE(){
            {
                withTop(top.build());
                withInto();
                withTargetTable(table_name);
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            b_MERGE.k_INTO into,
            TableName table_name,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OUTPUT output,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTop(top.build());
                withInto();
                withTargetTable(table_name);
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOutput(output.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            b_MERGE.k_INTO into,
            TableName table_name,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OUTPUT output){
        return new b_MERGE(){
            {
                withTop(top.build());
                withInto();
                withTargetTable(table_name);
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOutput(output.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            b_MERGE.k_INTO into,
            TableName table_name,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTop(top.build());
                withInto();
                withTargetTable(table_name);
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            b_MERGE.k_INTO into,
            TableName table_name,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source){
        return new b_MERGE(){
            {
                withTop(top.build());
                withInto();
                withTargetTable(table_name);
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            b_MERGE.k_INTO into,
            TableName table_name,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_OUTPUT output,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTop(top.build());
                withInto();
                withTargetTable(table_name);
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withOutput(output.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            b_MERGE.k_INTO into,
            TableName table_name,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_OUTPUT output){
        return new b_MERGE(){
            {
                withTop(top.build());
                withInto();
                withTargetTable(table_name);
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withOutput(output.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            b_MERGE.k_INTO into,
            TableName table_name,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTop(top.build());
                withInto();
                withTargetTable(table_name);
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            b_MERGE.k_INTO into,
            TableName table_name,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on){
        return new b_MERGE(){
            {
                withTop(top.build());
                withInto();
                withTargetTable(table_name);
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            b_MERGE.k_INTO into,
            TableName table_name,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OUTPUT output,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTop(top.build());
                withInto();
                withTargetTable(table_name);
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOutput(output.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            b_MERGE.k_INTO into,
            TableName table_name,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OUTPUT output){
        return new b_MERGE(){
            {
                withTop(top.build());
                withInto();
                withTargetTable(table_name);
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOutput(output.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            b_MERGE.k_INTO into,
            TableName table_name,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTop(top.build());
                withInto();
                withTargetTable(table_name);
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            b_MERGE.k_INTO into,
            TableName table_name,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source){
        return new b_MERGE(){
            {
                withTop(top.build());
                withInto();
                withTargetTable(table_name);
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            b_MERGE.k_INTO into,
            TableName table_name,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_OUTPUT output,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTop(top.build());
                withInto();
                withTargetTable(table_name);
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withOutput(output.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            b_MERGE.k_INTO into,
            TableName table_name,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_OUTPUT output){
        return new b_MERGE(){
            {
                withTop(top.build());
                withInto();
                withTargetTable(table_name);
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withOutput(output.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            b_MERGE.k_INTO into,
            TableName table_name,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTop(top.build());
                withInto();
                withTargetTable(table_name);
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            b_MERGE.k_INTO into,
            TableName table_name,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched){
        return new b_MERGE(){
            {
                withTop(top.build());
                withInto();
                withTargetTable(table_name);
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            b_MERGE.k_INTO into,
            TableName table_name,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OUTPUT output,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTop(top.build());
                withInto();
                withTargetTable(table_name);
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOutput(output.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            b_MERGE.k_INTO into,
            TableName table_name,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OUTPUT output){
        return new b_MERGE(){
            {
                withTop(top.build());
                withInto();
                withTargetTable(table_name);
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOutput(output.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            b_MERGE.k_INTO into,
            TableName table_name,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTop(top.build());
                withInto();
                withTargetTable(table_name);
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            b_MERGE.k_INTO into,
            TableName table_name,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source){
        return new b_MERGE(){
            {
                withTop(top.build());
                withInto();
                withTargetTable(table_name);
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            b_MERGE.k_INTO into,
            TableName table_name,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_OUTPUT output,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTop(top.build());
                withInto();
                withTargetTable(table_name);
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withOutput(output.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            b_MERGE.k_INTO into,
            TableName table_name,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_OUTPUT output){
        return new b_MERGE(){
            {
                withTop(top.build());
                withInto();
                withTargetTable(table_name);
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withOutput(output.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            b_MERGE.k_INTO into,
            TableName table_name,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTop(top.build());
                withInto();
                withTargetTable(table_name);
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            b_MERGE.k_INTO into,
            TableName table_name,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched){
        return new b_MERGE(){
            {
                withTop(top.build());
                withInto();
                withTargetTable(table_name);
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            b_MERGE.k_INTO into,
            TableName table_name,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OUTPUT output,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTop(top.build());
                withInto();
                withTargetTable(table_name);
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOutput(output.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            b_MERGE.k_INTO into,
            TableName table_name,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OUTPUT output){
        return new b_MERGE(){
            {
                withTop(top.build());
                withInto();
                withTargetTable(table_name);
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOutput(output.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            b_MERGE.k_INTO into,
            TableName table_name,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTop(top.build());
                withInto();
                withTargetTable(table_name);
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            b_MERGE.k_INTO into,
            TableName table_name,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source){
        return new b_MERGE(){
            {
                withTop(top.build());
                withInto();
                withTargetTable(table_name);
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            b_MERGE.k_INTO into,
            TableName table_name,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_OUTPUT output,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTop(top.build());
                withInto();
                withTargetTable(table_name);
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withOutput(output.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            b_MERGE.k_INTO into,
            TableName table_name,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_OUTPUT output){
        return new b_MERGE(){
            {
                withTop(top.build());
                withInto();
                withTargetTable(table_name);
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withOutput(output.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            b_MERGE.k_INTO into,
            TableName table_name,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTop(top.build());
                withInto();
                withTargetTable(table_name);
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            b_MERGE.k_INTO into,
            TableName table_name,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched){
        return new b_MERGE(){
            {
                withTop(top.build());
                withInto();
                withTargetTable(table_name);
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            b_MERGE.k_INTO into,
            TableName table_name,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OUTPUT output,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTop(top.build());
                withInto();
                withTargetTable(table_name);
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOutput(output.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            b_MERGE.k_INTO into,
            TableName table_name,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OUTPUT output){
        return new b_MERGE(){
            {
                withTop(top.build());
                withInto();
                withTargetTable(table_name);
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOutput(output.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            b_MERGE.k_INTO into,
            TableName table_name,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTop(top.build());
                withInto();
                withTargetTable(table_name);
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            b_MERGE.k_INTO into,
            TableName table_name,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source){
        return new b_MERGE(){
            {
                withTop(top.build());
                withInto();
                withTargetTable(table_name);
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            b_MERGE.k_INTO into,
            TableName table_name,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_OUTPUT output,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTop(top.build());
                withInto();
                withTargetTable(table_name);
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withOutput(output.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            b_MERGE.k_INTO into,
            TableName table_name,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_OUTPUT output){
        return new b_MERGE(){
            {
                withTop(top.build());
                withInto();
                withTargetTable(table_name);
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withOutput(output.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            b_MERGE.k_INTO into,
            TableName table_name,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTop(top.build());
                withInto();
                withTargetTable(table_name);
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            b_MERGE.k_INTO into,
            TableName table_name,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on){
        return new b_MERGE(){
            {
                withTop(top.build());
                withInto();
                withTargetTable(table_name);
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OUTPUT output,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTop(top.build());
                withInto();
                withTargetTable(table_name);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOutput(output.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OUTPUT output){
        return new b_MERGE(){
            {
                withTop(top.build());
                withInto();
                withTargetTable(table_name);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOutput(output.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTop(top.build());
                withInto();
                withTargetTable(table_name);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source){
        return new b_MERGE(){
            {
                withTop(top.build());
                withInto();
                withTargetTable(table_name);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_OUTPUT output,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTop(top.build());
                withInto();
                withTargetTable(table_name);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withOutput(output.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_OUTPUT output){
        return new b_MERGE(){
            {
                withTop(top.build());
                withInto();
                withTargetTable(table_name);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withOutput(output.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTop(top.build());
                withInto();
                withTargetTable(table_name);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched){
        return new b_MERGE(){
            {
                withTop(top.build());
                withInto();
                withTargetTable(table_name);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OUTPUT output,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTop(top.build());
                withInto();
                withTargetTable(table_name);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOutput(output.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OUTPUT output){
        return new b_MERGE(){
            {
                withTop(top.build());
                withInto();
                withTargetTable(table_name);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOutput(output.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTop(top.build());
                withInto();
                withTargetTable(table_name);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source){
        return new b_MERGE(){
            {
                withTop(top.build());
                withInto();
                withTargetTable(table_name);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_OUTPUT output,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTop(top.build());
                withInto();
                withTargetTable(table_name);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withOutput(output.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_OUTPUT output){
        return new b_MERGE(){
            {
                withTop(top.build());
                withInto();
                withTargetTable(table_name);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withOutput(output.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTop(top.build());
                withInto();
                withTargetTable(table_name);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched){
        return new b_MERGE(){
            {
                withTop(top.build());
                withInto();
                withTargetTable(table_name);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OUTPUT output,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTop(top.build());
                withInto();
                withTargetTable(table_name);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOutput(output.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OUTPUT output){
        return new b_MERGE(){
            {
                withTop(top.build());
                withInto();
                withTargetTable(table_name);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOutput(output.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTop(top.build());
                withInto();
                withTargetTable(table_name);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source){
        return new b_MERGE(){
            {
                withTop(top.build());
                withInto();
                withTargetTable(table_name);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_OUTPUT output,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTop(top.build());
                withInto();
                withTargetTable(table_name);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withOutput(output.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_OUTPUT output){
        return new b_MERGE(){
            {
                withTop(top.build());
                withInto();
                withTargetTable(table_name);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withOutput(output.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTop(top.build());
                withInto();
                withTargetTable(table_name);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched){
        return new b_MERGE(){
            {
                withTop(top.build());
                withInto();
                withTargetTable(table_name);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OUTPUT output,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTop(top.build());
                withInto();
                withTargetTable(table_name);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOutput(output.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OUTPUT output){
        return new b_MERGE(){
            {
                withTop(top.build());
                withInto();
                withTargetTable(table_name);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOutput(output.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTop(top.build());
                withInto();
                withTargetTable(table_name);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source){
        return new b_MERGE(){
            {
                withTop(top.build());
                withInto();
                withTargetTable(table_name);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_OUTPUT output,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTop(top.build());
                withInto();
                withTargetTable(table_name);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withOutput(output.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_OUTPUT output){
        return new b_MERGE(){
            {
                withTop(top.build());
                withInto();
                withTargetTable(table_name);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withOutput(output.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTop(top.build());
                withInto();
                withTargetTable(table_name);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on){
        return new b_MERGE(){
            {
                withTop(top.build());
                withInto();
                withTargetTable(table_name);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            TableName table_name,
            b_MERGE.b_WITH with,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OUTPUT output,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTop(top.build());
                withTargetTable(table_name);
                withMergeHint(with.build());
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOutput(output.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            TableName table_name,
            b_MERGE.b_WITH with,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OUTPUT output){
        return new b_MERGE(){
            {
                withTop(top.build());
                withTargetTable(table_name);
                withMergeHint(with.build());
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOutput(output.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            TableName table_name,
            b_MERGE.b_WITH with,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTop(top.build());
                withTargetTable(table_name);
                withMergeHint(with.build());
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            TableName table_name,
            b_MERGE.b_WITH with,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source){
        return new b_MERGE(){
            {
                withTop(top.build());
                withTargetTable(table_name);
                withMergeHint(with.build());
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            TableName table_name,
            b_MERGE.b_WITH with,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_OUTPUT output,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTop(top.build());
                withTargetTable(table_name);
                withMergeHint(with.build());
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withOutput(output.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            TableName table_name,
            b_MERGE.b_WITH with,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_OUTPUT output){
        return new b_MERGE(){
            {
                withTop(top.build());
                withTargetTable(table_name);
                withMergeHint(with.build());
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withOutput(output.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            TableName table_name,
            b_MERGE.b_WITH with,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTop(top.build());
                withTargetTable(table_name);
                withMergeHint(with.build());
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            TableName table_name,
            b_MERGE.b_WITH with,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched){
        return new b_MERGE(){
            {
                withTop(top.build());
                withTargetTable(table_name);
                withMergeHint(with.build());
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            TableName table_name,
            b_MERGE.b_WITH with,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OUTPUT output,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTop(top.build());
                withTargetTable(table_name);
                withMergeHint(with.build());
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOutput(output.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            TableName table_name,
            b_MERGE.b_WITH with,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OUTPUT output){
        return new b_MERGE(){
            {
                withTop(top.build());
                withTargetTable(table_name);
                withMergeHint(with.build());
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOutput(output.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            TableName table_name,
            b_MERGE.b_WITH with,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTop(top.build());
                withTargetTable(table_name);
                withMergeHint(with.build());
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            TableName table_name,
            b_MERGE.b_WITH with,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source){
        return new b_MERGE(){
            {
                withTop(top.build());
                withTargetTable(table_name);
                withMergeHint(with.build());
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            TableName table_name,
            b_MERGE.b_WITH with,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_OUTPUT output,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTop(top.build());
                withTargetTable(table_name);
                withMergeHint(with.build());
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withOutput(output.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            TableName table_name,
            b_MERGE.b_WITH with,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_OUTPUT output){
        return new b_MERGE(){
            {
                withTop(top.build());
                withTargetTable(table_name);
                withMergeHint(with.build());
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withOutput(output.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            TableName table_name,
            b_MERGE.b_WITH with,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTop(top.build());
                withTargetTable(table_name);
                withMergeHint(with.build());
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            TableName table_name,
            b_MERGE.b_WITH with,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched){
        return new b_MERGE(){
            {
                withTop(top.build());
                withTargetTable(table_name);
                withMergeHint(with.build());
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            TableName table_name,
            b_MERGE.b_WITH with,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OUTPUT output,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTop(top.build());
                withTargetTable(table_name);
                withMergeHint(with.build());
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOutput(output.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            TableName table_name,
            b_MERGE.b_WITH with,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OUTPUT output){
        return new b_MERGE(){
            {
                withTop(top.build());
                withTargetTable(table_name);
                withMergeHint(with.build());
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOutput(output.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            TableName table_name,
            b_MERGE.b_WITH with,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTop(top.build());
                withTargetTable(table_name);
                withMergeHint(with.build());
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            TableName table_name,
            b_MERGE.b_WITH with,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source){
        return new b_MERGE(){
            {
                withTop(top.build());
                withTargetTable(table_name);
                withMergeHint(with.build());
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            TableName table_name,
            b_MERGE.b_WITH with,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_OUTPUT output,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTop(top.build());
                withTargetTable(table_name);
                withMergeHint(with.build());
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withOutput(output.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            TableName table_name,
            b_MERGE.b_WITH with,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_OUTPUT output){
        return new b_MERGE(){
            {
                withTop(top.build());
                withTargetTable(table_name);
                withMergeHint(with.build());
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withOutput(output.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            TableName table_name,
            b_MERGE.b_WITH with,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTop(top.build());
                withTargetTable(table_name);
                withMergeHint(with.build());
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            TableName table_name,
            b_MERGE.b_WITH with,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched){
        return new b_MERGE(){
            {
                withTop(top.build());
                withTargetTable(table_name);
                withMergeHint(with.build());
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            TableName table_name,
            b_MERGE.b_WITH with,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OUTPUT output,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTop(top.build());
                withTargetTable(table_name);
                withMergeHint(with.build());
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOutput(output.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            TableName table_name,
            b_MERGE.b_WITH with,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OUTPUT output){
        return new b_MERGE(){
            {
                withTop(top.build());
                withTargetTable(table_name);
                withMergeHint(with.build());
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOutput(output.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            TableName table_name,
            b_MERGE.b_WITH with,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTop(top.build());
                withTargetTable(table_name);
                withMergeHint(with.build());
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            TableName table_name,
            b_MERGE.b_WITH with,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source){
        return new b_MERGE(){
            {
                withTop(top.build());
                withTargetTable(table_name);
                withMergeHint(with.build());
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            TableName table_name,
            b_MERGE.b_WITH with,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_OUTPUT output,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTop(top.build());
                withTargetTable(table_name);
                withMergeHint(with.build());
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withOutput(output.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            TableName table_name,
            b_MERGE.b_WITH with,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_OUTPUT output){
        return new b_MERGE(){
            {
                withTop(top.build());
                withTargetTable(table_name);
                withMergeHint(with.build());
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withOutput(output.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            TableName table_name,
            b_MERGE.b_WITH with,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTop(top.build());
                withTargetTable(table_name);
                withMergeHint(with.build());
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            TableName table_name,
            b_MERGE.b_WITH with,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on){
        return new b_MERGE(){
            {
                withTop(top.build());
                withTargetTable(table_name);
                withMergeHint(with.build());
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            TableName table_name,
            b_MERGE.b_WITH with,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OUTPUT output,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTop(top.build());
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOutput(output.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            TableName table_name,
            b_MERGE.b_WITH with,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OUTPUT output){
        return new b_MERGE(){
            {
                withTop(top.build());
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOutput(output.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            TableName table_name,
            b_MERGE.b_WITH with,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTop(top.build());
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            TableName table_name,
            b_MERGE.b_WITH with,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source){
        return new b_MERGE(){
            {
                withTop(top.build());
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            TableName table_name,
            b_MERGE.b_WITH with,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_OUTPUT output,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTop(top.build());
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withOutput(output.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            TableName table_name,
            b_MERGE.b_WITH with,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_OUTPUT output){
        return new b_MERGE(){
            {
                withTop(top.build());
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withOutput(output.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            TableName table_name,
            b_MERGE.b_WITH with,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTop(top.build());
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            TableName table_name,
            b_MERGE.b_WITH with,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched){
        return new b_MERGE(){
            {
                withTop(top.build());
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            TableName table_name,
            b_MERGE.b_WITH with,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OUTPUT output,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTop(top.build());
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOutput(output.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            TableName table_name,
            b_MERGE.b_WITH with,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OUTPUT output){
        return new b_MERGE(){
            {
                withTop(top.build());
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOutput(output.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            TableName table_name,
            b_MERGE.b_WITH with,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTop(top.build());
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            TableName table_name,
            b_MERGE.b_WITH with,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source){
        return new b_MERGE(){
            {
                withTop(top.build());
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            TableName table_name,
            b_MERGE.b_WITH with,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_OUTPUT output,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTop(top.build());
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withOutput(output.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            TableName table_name,
            b_MERGE.b_WITH with,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_OUTPUT output){
        return new b_MERGE(){
            {
                withTop(top.build());
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withOutput(output.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            TableName table_name,
            b_MERGE.b_WITH with,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTop(top.build());
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            TableName table_name,
            b_MERGE.b_WITH with,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched){
        return new b_MERGE(){
            {
                withTop(top.build());
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            TableName table_name,
            b_MERGE.b_WITH with,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OUTPUT output,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTop(top.build());
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOutput(output.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            TableName table_name,
            b_MERGE.b_WITH with,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OUTPUT output){
        return new b_MERGE(){
            {
                withTop(top.build());
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOutput(output.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            TableName table_name,
            b_MERGE.b_WITH with,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTop(top.build());
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            TableName table_name,
            b_MERGE.b_WITH with,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source){
        return new b_MERGE(){
            {
                withTop(top.build());
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            TableName table_name,
            b_MERGE.b_WITH with,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_OUTPUT output,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTop(top.build());
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withOutput(output.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            TableName table_name,
            b_MERGE.b_WITH with,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_OUTPUT output){
        return new b_MERGE(){
            {
                withTop(top.build());
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withOutput(output.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            TableName table_name,
            b_MERGE.b_WITH with,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTop(top.build());
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            TableName table_name,
            b_MERGE.b_WITH with,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched){
        return new b_MERGE(){
            {
                withTop(top.build());
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            TableName table_name,
            b_MERGE.b_WITH with,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OUTPUT output,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTop(top.build());
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOutput(output.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            TableName table_name,
            b_MERGE.b_WITH with,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OUTPUT output){
        return new b_MERGE(){
            {
                withTop(top.build());
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOutput(output.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            TableName table_name,
            b_MERGE.b_WITH with,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTop(top.build());
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            TableName table_name,
            b_MERGE.b_WITH with,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source){
        return new b_MERGE(){
            {
                withTop(top.build());
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            TableName table_name,
            b_MERGE.b_WITH with,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_OUTPUT output,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTop(top.build());
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withOutput(output.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            TableName table_name,
            b_MERGE.b_WITH with,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_OUTPUT output){
        return new b_MERGE(){
            {
                withTop(top.build());
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withOutput(output.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            TableName table_name,
            b_MERGE.b_WITH with,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTop(top.build());
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            TableName table_name,
            b_MERGE.b_WITH with,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on){
        return new b_MERGE(){
            {
                withTop(top.build());
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            TableName table_name,
            b_MERGE.b_WITH with,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OUTPUT output,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTop(top.build());
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOutput(output.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            TableName table_name,
            b_MERGE.b_WITH with,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OUTPUT output){
        return new b_MERGE(){
            {
                withTop(top.build());
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOutput(output.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            TableName table_name,
            b_MERGE.b_WITH with,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTop(top.build());
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            TableName table_name,
            b_MERGE.b_WITH with,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source){
        return new b_MERGE(){
            {
                withTop(top.build());
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            TableName table_name,
            b_MERGE.b_WITH with,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_OUTPUT output,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTop(top.build());
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withOutput(output.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            TableName table_name,
            b_MERGE.b_WITH with,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_OUTPUT output){
        return new b_MERGE(){
            {
                withTop(top.build());
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withOutput(output.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            TableName table_name,
            b_MERGE.b_WITH with,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTop(top.build());
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            TableName table_name,
            b_MERGE.b_WITH with,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched){
        return new b_MERGE(){
            {
                withTop(top.build());
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            TableName table_name,
            b_MERGE.b_WITH with,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OUTPUT output,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTop(top.build());
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOutput(output.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            TableName table_name,
            b_MERGE.b_WITH with,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OUTPUT output){
        return new b_MERGE(){
            {
                withTop(top.build());
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOutput(output.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            TableName table_name,
            b_MERGE.b_WITH with,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTop(top.build());
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            TableName table_name,
            b_MERGE.b_WITH with,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source){
        return new b_MERGE(){
            {
                withTop(top.build());
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            TableName table_name,
            b_MERGE.b_WITH with,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_OUTPUT output,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTop(top.build());
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withOutput(output.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            TableName table_name,
            b_MERGE.b_WITH with,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_OUTPUT output){
        return new b_MERGE(){
            {
                withTop(top.build());
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withOutput(output.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            TableName table_name,
            b_MERGE.b_WITH with,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTop(top.build());
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            TableName table_name,
            b_MERGE.b_WITH with,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched){
        return new b_MERGE(){
            {
                withTop(top.build());
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            TableName table_name,
            b_MERGE.b_WITH with,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OUTPUT output,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTop(top.build());
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOutput(output.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            TableName table_name,
            b_MERGE.b_WITH with,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OUTPUT output){
        return new b_MERGE(){
            {
                withTop(top.build());
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOutput(output.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            TableName table_name,
            b_MERGE.b_WITH with,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTop(top.build());
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            TableName table_name,
            b_MERGE.b_WITH with,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source){
        return new b_MERGE(){
            {
                withTop(top.build());
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            TableName table_name,
            b_MERGE.b_WITH with,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_OUTPUT output,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTop(top.build());
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withOutput(output.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            TableName table_name,
            b_MERGE.b_WITH with,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_OUTPUT output){
        return new b_MERGE(){
            {
                withTop(top.build());
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withOutput(output.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            TableName table_name,
            b_MERGE.b_WITH with,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTop(top.build());
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            TableName table_name,
            b_MERGE.b_WITH with,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched){
        return new b_MERGE(){
            {
                withTop(top.build());
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            TableName table_name,
            b_MERGE.b_WITH with,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OUTPUT output,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTop(top.build());
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOutput(output.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            TableName table_name,
            b_MERGE.b_WITH with,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OUTPUT output){
        return new b_MERGE(){
            {
                withTop(top.build());
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOutput(output.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            TableName table_name,
            b_MERGE.b_WITH with,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTop(top.build());
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            TableName table_name,
            b_MERGE.b_WITH with,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source){
        return new b_MERGE(){
            {
                withTop(top.build());
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            TableName table_name,
            b_MERGE.b_WITH with,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_OUTPUT output,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTop(top.build());
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withOutput(output.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            TableName table_name,
            b_MERGE.b_WITH with,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_OUTPUT output){
        return new b_MERGE(){
            {
                withTop(top.build());
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withOutput(output.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            TableName table_name,
            b_MERGE.b_WITH with,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTop(top.build());
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            TableName table_name,
            b_MERGE.b_WITH with,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on){
        return new b_MERGE(){
            {
                withTop(top.build());
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            TableName table_name,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OUTPUT output,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTop(top.build());
                withTargetTable(table_name);
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOutput(output.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            TableName table_name,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OUTPUT output){
        return new b_MERGE(){
            {
                withTop(top.build());
                withTargetTable(table_name);
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOutput(output.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            TableName table_name,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTop(top.build());
                withTargetTable(table_name);
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            TableName table_name,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source){
        return new b_MERGE(){
            {
                withTop(top.build());
                withTargetTable(table_name);
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            TableName table_name,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_OUTPUT output,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTop(top.build());
                withTargetTable(table_name);
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withOutput(output.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            TableName table_name,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_OUTPUT output){
        return new b_MERGE(){
            {
                withTop(top.build());
                withTargetTable(table_name);
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withOutput(output.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            TableName table_name,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTop(top.build());
                withTargetTable(table_name);
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            TableName table_name,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched){
        return new b_MERGE(){
            {
                withTop(top.build());
                withTargetTable(table_name);
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            TableName table_name,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OUTPUT output,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTop(top.build());
                withTargetTable(table_name);
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOutput(output.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            TableName table_name,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OUTPUT output){
        return new b_MERGE(){
            {
                withTop(top.build());
                withTargetTable(table_name);
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOutput(output.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            TableName table_name,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTop(top.build());
                withTargetTable(table_name);
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            TableName table_name,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source){
        return new b_MERGE(){
            {
                withTop(top.build());
                withTargetTable(table_name);
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            TableName table_name,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_OUTPUT output,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTop(top.build());
                withTargetTable(table_name);
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withOutput(output.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            TableName table_name,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_OUTPUT output){
        return new b_MERGE(){
            {
                withTop(top.build());
                withTargetTable(table_name);
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withOutput(output.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            TableName table_name,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTop(top.build());
                withTargetTable(table_name);
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            TableName table_name,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched){
        return new b_MERGE(){
            {
                withTop(top.build());
                withTargetTable(table_name);
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            TableName table_name,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OUTPUT output,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTop(top.build());
                withTargetTable(table_name);
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOutput(output.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            TableName table_name,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OUTPUT output){
        return new b_MERGE(){
            {
                withTop(top.build());
                withTargetTable(table_name);
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOutput(output.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            TableName table_name,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTop(top.build());
                withTargetTable(table_name);
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            TableName table_name,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source){
        return new b_MERGE(){
            {
                withTop(top.build());
                withTargetTable(table_name);
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            TableName table_name,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_OUTPUT output,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTop(top.build());
                withTargetTable(table_name);
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withOutput(output.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            TableName table_name,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_OUTPUT output){
        return new b_MERGE(){
            {
                withTop(top.build());
                withTargetTable(table_name);
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withOutput(output.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            TableName table_name,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTop(top.build());
                withTargetTable(table_name);
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            TableName table_name,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched){
        return new b_MERGE(){
            {
                withTop(top.build());
                withTargetTable(table_name);
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            TableName table_name,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OUTPUT output,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTop(top.build());
                withTargetTable(table_name);
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOutput(output.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            TableName table_name,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OUTPUT output){
        return new b_MERGE(){
            {
                withTop(top.build());
                withTargetTable(table_name);
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOutput(output.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            TableName table_name,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTop(top.build());
                withTargetTable(table_name);
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            TableName table_name,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source){
        return new b_MERGE(){
            {
                withTop(top.build());
                withTargetTable(table_name);
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            TableName table_name,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_OUTPUT output,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTop(top.build());
                withTargetTable(table_name);
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withOutput(output.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            TableName table_name,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_OUTPUT output){
        return new b_MERGE(){
            {
                withTop(top.build());
                withTargetTable(table_name);
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withOutput(output.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            TableName table_name,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTop(top.build());
                withTargetTable(table_name);
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            TableName table_name,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on){
        return new b_MERGE(){
            {
                withTop(top.build());
                withTargetTable(table_name);
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            TableName table_name,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OUTPUT output,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTop(top.build());
                withTargetTable(table_name);
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOutput(output.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            TableName table_name,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OUTPUT output){
        return new b_MERGE(){
            {
                withTop(top.build());
                withTargetTable(table_name);
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOutput(output.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            TableName table_name,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTop(top.build());
                withTargetTable(table_name);
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            TableName table_name,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source){
        return new b_MERGE(){
            {
                withTop(top.build());
                withTargetTable(table_name);
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            TableName table_name,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_OUTPUT output,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTop(top.build());
                withTargetTable(table_name);
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withOutput(output.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            TableName table_name,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_OUTPUT output){
        return new b_MERGE(){
            {
                withTop(top.build());
                withTargetTable(table_name);
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withOutput(output.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            TableName table_name,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTop(top.build());
                withTargetTable(table_name);
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            TableName table_name,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched){
        return new b_MERGE(){
            {
                withTop(top.build());
                withTargetTable(table_name);
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            TableName table_name,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OUTPUT output,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTop(top.build());
                withTargetTable(table_name);
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOutput(output.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            TableName table_name,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OUTPUT output){
        return new b_MERGE(){
            {
                withTop(top.build());
                withTargetTable(table_name);
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOutput(output.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            TableName table_name,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTop(top.build());
                withTargetTable(table_name);
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            TableName table_name,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source){
        return new b_MERGE(){
            {
                withTop(top.build());
                withTargetTable(table_name);
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            TableName table_name,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_OUTPUT output,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTop(top.build());
                withTargetTable(table_name);
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withOutput(output.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            TableName table_name,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_OUTPUT output){
        return new b_MERGE(){
            {
                withTop(top.build());
                withTargetTable(table_name);
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withOutput(output.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            TableName table_name,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTop(top.build());
                withTargetTable(table_name);
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            TableName table_name,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched){
        return new b_MERGE(){
            {
                withTop(top.build());
                withTargetTable(table_name);
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            TableName table_name,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OUTPUT output,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTop(top.build());
                withTargetTable(table_name);
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOutput(output.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            TableName table_name,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OUTPUT output){
        return new b_MERGE(){
            {
                withTop(top.build());
                withTargetTable(table_name);
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOutput(output.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            TableName table_name,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTop(top.build());
                withTargetTable(table_name);
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            TableName table_name,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source){
        return new b_MERGE(){
            {
                withTop(top.build());
                withTargetTable(table_name);
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            TableName table_name,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_OUTPUT output,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTop(top.build());
                withTargetTable(table_name);
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withOutput(output.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            TableName table_name,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_OUTPUT output){
        return new b_MERGE(){
            {
                withTop(top.build());
                withTargetTable(table_name);
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withOutput(output.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            TableName table_name,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTop(top.build());
                withTargetTable(table_name);
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            TableName table_name,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched){
        return new b_MERGE(){
            {
                withTop(top.build());
                withTargetTable(table_name);
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            TableName table_name,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OUTPUT output,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTop(top.build());
                withTargetTable(table_name);
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOutput(output.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            TableName table_name,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OUTPUT output){
        return new b_MERGE(){
            {
                withTop(top.build());
                withTargetTable(table_name);
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOutput(output.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            TableName table_name,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTop(top.build());
                withTargetTable(table_name);
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            TableName table_name,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source){
        return new b_MERGE(){
            {
                withTop(top.build());
                withTargetTable(table_name);
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            TableName table_name,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_OUTPUT output,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTop(top.build());
                withTargetTable(table_name);
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withOutput(output.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            TableName table_name,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_OUTPUT output){
        return new b_MERGE(){
            {
                withTop(top.build());
                withTargetTable(table_name);
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withOutput(output.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            TableName table_name,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTop(top.build());
                withTargetTable(table_name);
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            TableName table_name,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on){
        return new b_MERGE(){
            {
                withTop(top.build());
                withTargetTable(table_name);
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            TableName table_name,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OUTPUT output,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTop(top.build());
                withTargetTable(table_name);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOutput(output.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            TableName table_name,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OUTPUT output){
        return new b_MERGE(){
            {
                withTop(top.build());
                withTargetTable(table_name);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOutput(output.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            TableName table_name,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTop(top.build());
                withTargetTable(table_name);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            TableName table_name,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source){
        return new b_MERGE(){
            {
                withTop(top.build());
                withTargetTable(table_name);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            TableName table_name,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_OUTPUT output,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTop(top.build());
                withTargetTable(table_name);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withOutput(output.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            TableName table_name,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_OUTPUT output){
        return new b_MERGE(){
            {
                withTop(top.build());
                withTargetTable(table_name);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withOutput(output.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            TableName table_name,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTop(top.build());
                withTargetTable(table_name);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            TableName table_name,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched){
        return new b_MERGE(){
            {
                withTop(top.build());
                withTargetTable(table_name);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            TableName table_name,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OUTPUT output,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTop(top.build());
                withTargetTable(table_name);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOutput(output.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            TableName table_name,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OUTPUT output){
        return new b_MERGE(){
            {
                withTop(top.build());
                withTargetTable(table_name);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOutput(output.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            TableName table_name,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTop(top.build());
                withTargetTable(table_name);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            TableName table_name,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source){
        return new b_MERGE(){
            {
                withTop(top.build());
                withTargetTable(table_name);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            TableName table_name,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_OUTPUT output,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTop(top.build());
                withTargetTable(table_name);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withOutput(output.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            TableName table_name,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_OUTPUT output){
        return new b_MERGE(){
            {
                withTop(top.build());
                withTargetTable(table_name);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withOutput(output.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            TableName table_name,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTop(top.build());
                withTargetTable(table_name);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            TableName table_name,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched){
        return new b_MERGE(){
            {
                withTop(top.build());
                withTargetTable(table_name);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            TableName table_name,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OUTPUT output,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTop(top.build());
                withTargetTable(table_name);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOutput(output.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            TableName table_name,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OUTPUT output){
        return new b_MERGE(){
            {
                withTop(top.build());
                withTargetTable(table_name);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOutput(output.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            TableName table_name,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTop(top.build());
                withTargetTable(table_name);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            TableName table_name,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source){
        return new b_MERGE(){
            {
                withTop(top.build());
                withTargetTable(table_name);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            TableName table_name,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_OUTPUT output,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTop(top.build());
                withTargetTable(table_name);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withOutput(output.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            TableName table_name,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_OUTPUT output){
        return new b_MERGE(){
            {
                withTop(top.build());
                withTargetTable(table_name);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withOutput(output.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            TableName table_name,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTop(top.build());
                withTargetTable(table_name);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            TableName table_name,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched){
        return new b_MERGE(){
            {
                withTop(top.build());
                withTargetTable(table_name);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            TableName table_name,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OUTPUT output,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTop(top.build());
                withTargetTable(table_name);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOutput(output.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            TableName table_name,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OUTPUT output){
        return new b_MERGE(){
            {
                withTop(top.build());
                withTargetTable(table_name);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOutput(output.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            TableName table_name,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTop(top.build());
                withTargetTable(table_name);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            TableName table_name,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source){
        return new b_MERGE(){
            {
                withTop(top.build());
                withTargetTable(table_name);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            TableName table_name,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_OUTPUT output,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTop(top.build());
                withTargetTable(table_name);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withOutput(output.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            TableName table_name,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_OUTPUT output){
        return new b_MERGE(){
            {
                withTop(top.build());
                withTargetTable(table_name);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withOutput(output.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            TableName table_name,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTop(top.build());
                withTargetTable(table_name);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_TOP top,
            TableName table_name,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on){
        return new b_MERGE(){
            {
                withTop(top.build());
                withTargetTable(table_name);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_WITH with,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OUTPUT output,
            b_OPTION option){
        return new b_MERGE(){
            {
                withInto();
                withTargetTable(table_name);
                withMergeHint(with.build());
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOutput(output.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_WITH with,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OUTPUT output){
        return new b_MERGE(){
            {
                withInto();
                withTargetTable(table_name);
                withMergeHint(with.build());
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOutput(output.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_WITH with,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OPTION option){
        return new b_MERGE(){
            {
                withInto();
                withTargetTable(table_name);
                withMergeHint(with.build());
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_WITH with,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source){
        return new b_MERGE(){
            {
                withInto();
                withTargetTable(table_name);
                withMergeHint(with.build());
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_WITH with,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_OUTPUT output,
            b_OPTION option){
        return new b_MERGE(){
            {
                withInto();
                withTargetTable(table_name);
                withMergeHint(with.build());
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withOutput(output.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_WITH with,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_OUTPUT output){
        return new b_MERGE(){
            {
                withInto();
                withTargetTable(table_name);
                withMergeHint(with.build());
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withOutput(output.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_WITH with,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_OPTION option){
        return new b_MERGE(){
            {
                withInto();
                withTargetTable(table_name);
                withMergeHint(with.build());
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_WITH with,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched){
        return new b_MERGE(){
            {
                withInto();
                withTargetTable(table_name);
                withMergeHint(with.build());
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_WITH with,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OUTPUT output,
            b_OPTION option){
        return new b_MERGE(){
            {
                withInto();
                withTargetTable(table_name);
                withMergeHint(with.build());
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOutput(output.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_WITH with,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OUTPUT output){
        return new b_MERGE(){
            {
                withInto();
                withTargetTable(table_name);
                withMergeHint(with.build());
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOutput(output.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_WITH with,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OPTION option){
        return new b_MERGE(){
            {
                withInto();
                withTargetTable(table_name);
                withMergeHint(with.build());
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_WITH with,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source){
        return new b_MERGE(){
            {
                withInto();
                withTargetTable(table_name);
                withMergeHint(with.build());
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_WITH with,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_OUTPUT output,
            b_OPTION option){
        return new b_MERGE(){
            {
                withInto();
                withTargetTable(table_name);
                withMergeHint(with.build());
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withOutput(output.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_WITH with,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_OUTPUT output){
        return new b_MERGE(){
            {
                withInto();
                withTargetTable(table_name);
                withMergeHint(with.build());
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withOutput(output.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_WITH with,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_OPTION option){
        return new b_MERGE(){
            {
                withInto();
                withTargetTable(table_name);
                withMergeHint(with.build());
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_WITH with,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched){
        return new b_MERGE(){
            {
                withInto();
                withTargetTable(table_name);
                withMergeHint(with.build());
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_WITH with,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OUTPUT output,
            b_OPTION option){
        return new b_MERGE(){
            {
                withInto();
                withTargetTable(table_name);
                withMergeHint(with.build());
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOutput(output.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_WITH with,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OUTPUT output){
        return new b_MERGE(){
            {
                withInto();
                withTargetTable(table_name);
                withMergeHint(with.build());
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOutput(output.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_WITH with,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OPTION option){
        return new b_MERGE(){
            {
                withInto();
                withTargetTable(table_name);
                withMergeHint(with.build());
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_WITH with,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source){
        return new b_MERGE(){
            {
                withInto();
                withTargetTable(table_name);
                withMergeHint(with.build());
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_WITH with,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_OUTPUT output,
            b_OPTION option){
        return new b_MERGE(){
            {
                withInto();
                withTargetTable(table_name);
                withMergeHint(with.build());
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withOutput(output.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_WITH with,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_OUTPUT output){
        return new b_MERGE(){
            {
                withInto();
                withTargetTable(table_name);
                withMergeHint(with.build());
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withOutput(output.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_WITH with,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_OPTION option){
        return new b_MERGE(){
            {
                withInto();
                withTargetTable(table_name);
                withMergeHint(with.build());
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_WITH with,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched){
        return new b_MERGE(){
            {
                withInto();
                withTargetTable(table_name);
                withMergeHint(with.build());
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_WITH with,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OUTPUT output,
            b_OPTION option){
        return new b_MERGE(){
            {
                withInto();
                withTargetTable(table_name);
                withMergeHint(with.build());
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOutput(output.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_WITH with,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OUTPUT output){
        return new b_MERGE(){
            {
                withInto();
                withTargetTable(table_name);
                withMergeHint(with.build());
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOutput(output.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_WITH with,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OPTION option){
        return new b_MERGE(){
            {
                withInto();
                withTargetTable(table_name);
                withMergeHint(with.build());
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_WITH with,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source){
        return new b_MERGE(){
            {
                withInto();
                withTargetTable(table_name);
                withMergeHint(with.build());
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_WITH with,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_OUTPUT output,
            b_OPTION option){
        return new b_MERGE(){
            {
                withInto();
                withTargetTable(table_name);
                withMergeHint(with.build());
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withOutput(output.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_WITH with,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_OUTPUT output){
        return new b_MERGE(){
            {
                withInto();
                withTargetTable(table_name);
                withMergeHint(with.build());
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withOutput(output.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_WITH with,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_OPTION option){
        return new b_MERGE(){
            {
                withInto();
                withTargetTable(table_name);
                withMergeHint(with.build());
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_WITH with,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on){
        return new b_MERGE(){
            {
                withInto();
                withTargetTable(table_name);
                withMergeHint(with.build());
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_WITH with,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OUTPUT output,
            b_OPTION option){
        return new b_MERGE(){
            {
                withInto();
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOutput(output.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_WITH with,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OUTPUT output){
        return new b_MERGE(){
            {
                withInto();
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOutput(output.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_WITH with,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OPTION option){
        return new b_MERGE(){
            {
                withInto();
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_WITH with,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source){
        return new b_MERGE(){
            {
                withInto();
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_WITH with,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_OUTPUT output,
            b_OPTION option){
        return new b_MERGE(){
            {
                withInto();
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withOutput(output.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_WITH with,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_OUTPUT output){
        return new b_MERGE(){
            {
                withInto();
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withOutput(output.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_WITH with,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_OPTION option){
        return new b_MERGE(){
            {
                withInto();
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_WITH with,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched){
        return new b_MERGE(){
            {
                withInto();
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_WITH with,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OUTPUT output,
            b_OPTION option){
        return new b_MERGE(){
            {
                withInto();
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOutput(output.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_WITH with,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OUTPUT output){
        return new b_MERGE(){
            {
                withInto();
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOutput(output.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_WITH with,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OPTION option){
        return new b_MERGE(){
            {
                withInto();
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_WITH with,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source){
        return new b_MERGE(){
            {
                withInto();
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_WITH with,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_OUTPUT output,
            b_OPTION option){
        return new b_MERGE(){
            {
                withInto();
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withOutput(output.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_WITH with,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_OUTPUT output){
        return new b_MERGE(){
            {
                withInto();
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withOutput(output.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_WITH with,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_OPTION option){
        return new b_MERGE(){
            {
                withInto();
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_WITH with,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched){
        return new b_MERGE(){
            {
                withInto();
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_WITH with,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OUTPUT output,
            b_OPTION option){
        return new b_MERGE(){
            {
                withInto();
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOutput(output.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_WITH with,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OUTPUT output){
        return new b_MERGE(){
            {
                withInto();
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOutput(output.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_WITH with,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OPTION option){
        return new b_MERGE(){
            {
                withInto();
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_WITH with,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source){
        return new b_MERGE(){
            {
                withInto();
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_WITH with,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_OUTPUT output,
            b_OPTION option){
        return new b_MERGE(){
            {
                withInto();
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withOutput(output.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_WITH with,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_OUTPUT output){
        return new b_MERGE(){
            {
                withInto();
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withOutput(output.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_WITH with,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_OPTION option){
        return new b_MERGE(){
            {
                withInto();
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_WITH with,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched){
        return new b_MERGE(){
            {
                withInto();
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_WITH with,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OUTPUT output,
            b_OPTION option){
        return new b_MERGE(){
            {
                withInto();
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOutput(output.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_WITH with,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OUTPUT output){
        return new b_MERGE(){
            {
                withInto();
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOutput(output.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_WITH with,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OPTION option){
        return new b_MERGE(){
            {
                withInto();
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_WITH with,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source){
        return new b_MERGE(){
            {
                withInto();
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_WITH with,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_OUTPUT output,
            b_OPTION option){
        return new b_MERGE(){
            {
                withInto();
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withOutput(output.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_WITH with,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_OUTPUT output){
        return new b_MERGE(){
            {
                withInto();
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withOutput(output.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_WITH with,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_OPTION option){
        return new b_MERGE(){
            {
                withInto();
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_WITH with,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on){
        return new b_MERGE(){
            {
                withInto();
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_WITH with,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OUTPUT output,
            b_OPTION option){
        return new b_MERGE(){
            {
                withInto();
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOutput(output.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_WITH with,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OUTPUT output){
        return new b_MERGE(){
            {
                withInto();
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOutput(output.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_WITH with,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OPTION option){
        return new b_MERGE(){
            {
                withInto();
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_WITH with,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source){
        return new b_MERGE(){
            {
                withInto();
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_WITH with,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_OUTPUT output,
            b_OPTION option){
        return new b_MERGE(){
            {
                withInto();
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withOutput(output.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_WITH with,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_OUTPUT output){
        return new b_MERGE(){
            {
                withInto();
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withOutput(output.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_WITH with,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_OPTION option){
        return new b_MERGE(){
            {
                withInto();
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_WITH with,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched){
        return new b_MERGE(){
            {
                withInto();
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_WITH with,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OUTPUT output,
            b_OPTION option){
        return new b_MERGE(){
            {
                withInto();
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOutput(output.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_WITH with,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OUTPUT output){
        return new b_MERGE(){
            {
                withInto();
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOutput(output.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_WITH with,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OPTION option){
        return new b_MERGE(){
            {
                withInto();
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_WITH with,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source){
        return new b_MERGE(){
            {
                withInto();
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_WITH with,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_OUTPUT output,
            b_OPTION option){
        return new b_MERGE(){
            {
                withInto();
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withOutput(output.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_WITH with,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_OUTPUT output){
        return new b_MERGE(){
            {
                withInto();
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withOutput(output.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_WITH with,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_OPTION option){
        return new b_MERGE(){
            {
                withInto();
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_WITH with,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched){
        return new b_MERGE(){
            {
                withInto();
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_WITH with,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OUTPUT output,
            b_OPTION option){
        return new b_MERGE(){
            {
                withInto();
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOutput(output.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_WITH with,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OUTPUT output){
        return new b_MERGE(){
            {
                withInto();
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOutput(output.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_WITH with,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OPTION option){
        return new b_MERGE(){
            {
                withInto();
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_WITH with,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source){
        return new b_MERGE(){
            {
                withInto();
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_WITH with,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_OUTPUT output,
            b_OPTION option){
        return new b_MERGE(){
            {
                withInto();
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withOutput(output.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_WITH with,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_OUTPUT output){
        return new b_MERGE(){
            {
                withInto();
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withOutput(output.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_WITH with,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_OPTION option){
        return new b_MERGE(){
            {
                withInto();
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_WITH with,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched){
        return new b_MERGE(){
            {
                withInto();
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_WITH with,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OUTPUT output,
            b_OPTION option){
        return new b_MERGE(){
            {
                withInto();
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOutput(output.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_WITH with,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OUTPUT output){
        return new b_MERGE(){
            {
                withInto();
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOutput(output.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_WITH with,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OPTION option){
        return new b_MERGE(){
            {
                withInto();
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_WITH with,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source){
        return new b_MERGE(){
            {
                withInto();
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_WITH with,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_OUTPUT output,
            b_OPTION option){
        return new b_MERGE(){
            {
                withInto();
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withOutput(output.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_WITH with,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_OUTPUT output){
        return new b_MERGE(){
            {
                withInto();
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withOutput(output.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_WITH with,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_OPTION option){
        return new b_MERGE(){
            {
                withInto();
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_WITH with,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on){
        return new b_MERGE(){
            {
                withInto();
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_MERGE.k_INTO into,
            TableName table_name,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OUTPUT output,
            b_OPTION option){
        return new b_MERGE(){
            {
                withInto();
                withTargetTable(table_name);
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOutput(output.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_MERGE.k_INTO into,
            TableName table_name,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OUTPUT output){
        return new b_MERGE(){
            {
                withInto();
                withTargetTable(table_name);
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOutput(output.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_MERGE.k_INTO into,
            TableName table_name,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OPTION option){
        return new b_MERGE(){
            {
                withInto();
                withTargetTable(table_name);
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_MERGE.k_INTO into,
            TableName table_name,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source){
        return new b_MERGE(){
            {
                withInto();
                withTargetTable(table_name);
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_MERGE.k_INTO into,
            TableName table_name,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_OUTPUT output,
            b_OPTION option){
        return new b_MERGE(){
            {
                withInto();
                withTargetTable(table_name);
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withOutput(output.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_MERGE.k_INTO into,
            TableName table_name,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_OUTPUT output){
        return new b_MERGE(){
            {
                withInto();
                withTargetTable(table_name);
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withOutput(output.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_MERGE.k_INTO into,
            TableName table_name,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_OPTION option){
        return new b_MERGE(){
            {
                withInto();
                withTargetTable(table_name);
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_MERGE.k_INTO into,
            TableName table_name,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched){
        return new b_MERGE(){
            {
                withInto();
                withTargetTable(table_name);
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_MERGE.k_INTO into,
            TableName table_name,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OUTPUT output,
            b_OPTION option){
        return new b_MERGE(){
            {
                withInto();
                withTargetTable(table_name);
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOutput(output.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_MERGE.k_INTO into,
            TableName table_name,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OUTPUT output){
        return new b_MERGE(){
            {
                withInto();
                withTargetTable(table_name);
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOutput(output.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_MERGE.k_INTO into,
            TableName table_name,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OPTION option){
        return new b_MERGE(){
            {
                withInto();
                withTargetTable(table_name);
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_MERGE.k_INTO into,
            TableName table_name,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source){
        return new b_MERGE(){
            {
                withInto();
                withTargetTable(table_name);
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_MERGE.k_INTO into,
            TableName table_name,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_OUTPUT output,
            b_OPTION option){
        return new b_MERGE(){
            {
                withInto();
                withTargetTable(table_name);
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withOutput(output.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_MERGE.k_INTO into,
            TableName table_name,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_OUTPUT output){
        return new b_MERGE(){
            {
                withInto();
                withTargetTable(table_name);
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withOutput(output.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_MERGE.k_INTO into,
            TableName table_name,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_OPTION option){
        return new b_MERGE(){
            {
                withInto();
                withTargetTable(table_name);
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_MERGE.k_INTO into,
            TableName table_name,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched){
        return new b_MERGE(){
            {
                withInto();
                withTargetTable(table_name);
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_MERGE.k_INTO into,
            TableName table_name,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OUTPUT output,
            b_OPTION option){
        return new b_MERGE(){
            {
                withInto();
                withTargetTable(table_name);
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOutput(output.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_MERGE.k_INTO into,
            TableName table_name,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OUTPUT output){
        return new b_MERGE(){
            {
                withInto();
                withTargetTable(table_name);
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOutput(output.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_MERGE.k_INTO into,
            TableName table_name,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OPTION option){
        return new b_MERGE(){
            {
                withInto();
                withTargetTable(table_name);
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_MERGE.k_INTO into,
            TableName table_name,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source){
        return new b_MERGE(){
            {
                withInto();
                withTargetTable(table_name);
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_MERGE.k_INTO into,
            TableName table_name,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_OUTPUT output,
            b_OPTION option){
        return new b_MERGE(){
            {
                withInto();
                withTargetTable(table_name);
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withOutput(output.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_MERGE.k_INTO into,
            TableName table_name,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_OUTPUT output){
        return new b_MERGE(){
            {
                withInto();
                withTargetTable(table_name);
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withOutput(output.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_MERGE.k_INTO into,
            TableName table_name,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_OPTION option){
        return new b_MERGE(){
            {
                withInto();
                withTargetTable(table_name);
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_MERGE.k_INTO into,
            TableName table_name,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched){
        return new b_MERGE(){
            {
                withInto();
                withTargetTable(table_name);
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_MERGE.k_INTO into,
            TableName table_name,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OUTPUT output,
            b_OPTION option){
        return new b_MERGE(){
            {
                withInto();
                withTargetTable(table_name);
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOutput(output.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_MERGE.k_INTO into,
            TableName table_name,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OUTPUT output){
        return new b_MERGE(){
            {
                withInto();
                withTargetTable(table_name);
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOutput(output.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_MERGE.k_INTO into,
            TableName table_name,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OPTION option){
        return new b_MERGE(){
            {
                withInto();
                withTargetTable(table_name);
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_MERGE.k_INTO into,
            TableName table_name,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source){
        return new b_MERGE(){
            {
                withInto();
                withTargetTable(table_name);
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_MERGE.k_INTO into,
            TableName table_name,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_OUTPUT output,
            b_OPTION option){
        return new b_MERGE(){
            {
                withInto();
                withTargetTable(table_name);
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withOutput(output.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_MERGE.k_INTO into,
            TableName table_name,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_OUTPUT output){
        return new b_MERGE(){
            {
                withInto();
                withTargetTable(table_name);
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withOutput(output.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_MERGE.k_INTO into,
            TableName table_name,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_OPTION option){
        return new b_MERGE(){
            {
                withInto();
                withTargetTable(table_name);
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_MERGE.k_INTO into,
            TableName table_name,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on){
        return new b_MERGE(){
            {
                withInto();
                withTargetTable(table_name);
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_MERGE.k_INTO into,
            TableName table_name,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OUTPUT output,
            b_OPTION option){
        return new b_MERGE(){
            {
                withInto();
                withTargetTable(table_name);
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOutput(output.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_MERGE.k_INTO into,
            TableName table_name,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OUTPUT output){
        return new b_MERGE(){
            {
                withInto();
                withTargetTable(table_name);
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOutput(output.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_MERGE.k_INTO into,
            TableName table_name,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OPTION option){
        return new b_MERGE(){
            {
                withInto();
                withTargetTable(table_name);
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_MERGE.k_INTO into,
            TableName table_name,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source){
        return new b_MERGE(){
            {
                withInto();
                withTargetTable(table_name);
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_MERGE.k_INTO into,
            TableName table_name,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_OUTPUT output,
            b_OPTION option){
        return new b_MERGE(){
            {
                withInto();
                withTargetTable(table_name);
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withOutput(output.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_MERGE.k_INTO into,
            TableName table_name,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_OUTPUT output){
        return new b_MERGE(){
            {
                withInto();
                withTargetTable(table_name);
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withOutput(output.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_MERGE.k_INTO into,
            TableName table_name,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_OPTION option){
        return new b_MERGE(){
            {
                withInto();
                withTargetTable(table_name);
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_MERGE.k_INTO into,
            TableName table_name,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched){
        return new b_MERGE(){
            {
                withInto();
                withTargetTable(table_name);
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_MERGE.k_INTO into,
            TableName table_name,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OUTPUT output,
            b_OPTION option){
        return new b_MERGE(){
            {
                withInto();
                withTargetTable(table_name);
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOutput(output.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_MERGE.k_INTO into,
            TableName table_name,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OUTPUT output){
        return new b_MERGE(){
            {
                withInto();
                withTargetTable(table_name);
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOutput(output.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_MERGE.k_INTO into,
            TableName table_name,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OPTION option){
        return new b_MERGE(){
            {
                withInto();
                withTargetTable(table_name);
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_MERGE.k_INTO into,
            TableName table_name,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source){
        return new b_MERGE(){
            {
                withInto();
                withTargetTable(table_name);
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_MERGE.k_INTO into,
            TableName table_name,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_OUTPUT output,
            b_OPTION option){
        return new b_MERGE(){
            {
                withInto();
                withTargetTable(table_name);
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withOutput(output.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_MERGE.k_INTO into,
            TableName table_name,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_OUTPUT output){
        return new b_MERGE(){
            {
                withInto();
                withTargetTable(table_name);
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withOutput(output.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_MERGE.k_INTO into,
            TableName table_name,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_OPTION option){
        return new b_MERGE(){
            {
                withInto();
                withTargetTable(table_name);
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_MERGE.k_INTO into,
            TableName table_name,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched){
        return new b_MERGE(){
            {
                withInto();
                withTargetTable(table_name);
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_MERGE.k_INTO into,
            TableName table_name,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OUTPUT output,
            b_OPTION option){
        return new b_MERGE(){
            {
                withInto();
                withTargetTable(table_name);
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOutput(output.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_MERGE.k_INTO into,
            TableName table_name,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OUTPUT output){
        return new b_MERGE(){
            {
                withInto();
                withTargetTable(table_name);
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOutput(output.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_MERGE.k_INTO into,
            TableName table_name,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OPTION option){
        return new b_MERGE(){
            {
                withInto();
                withTargetTable(table_name);
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_MERGE.k_INTO into,
            TableName table_name,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source){
        return new b_MERGE(){
            {
                withInto();
                withTargetTable(table_name);
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_MERGE.k_INTO into,
            TableName table_name,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_OUTPUT output,
            b_OPTION option){
        return new b_MERGE(){
            {
                withInto();
                withTargetTable(table_name);
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withOutput(output.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_MERGE.k_INTO into,
            TableName table_name,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_OUTPUT output){
        return new b_MERGE(){
            {
                withInto();
                withTargetTable(table_name);
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withOutput(output.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_MERGE.k_INTO into,
            TableName table_name,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_OPTION option){
        return new b_MERGE(){
            {
                withInto();
                withTargetTable(table_name);
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_MERGE.k_INTO into,
            TableName table_name,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched){
        return new b_MERGE(){
            {
                withInto();
                withTargetTable(table_name);
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_MERGE.k_INTO into,
            TableName table_name,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OUTPUT output,
            b_OPTION option){
        return new b_MERGE(){
            {
                withInto();
                withTargetTable(table_name);
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOutput(output.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_MERGE.k_INTO into,
            TableName table_name,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OUTPUT output){
        return new b_MERGE(){
            {
                withInto();
                withTargetTable(table_name);
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOutput(output.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_MERGE.k_INTO into,
            TableName table_name,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OPTION option){
        return new b_MERGE(){
            {
                withInto();
                withTargetTable(table_name);
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_MERGE.k_INTO into,
            TableName table_name,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source){
        return new b_MERGE(){
            {
                withInto();
                withTargetTable(table_name);
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_MERGE.k_INTO into,
            TableName table_name,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_OUTPUT output,
            b_OPTION option){
        return new b_MERGE(){
            {
                withInto();
                withTargetTable(table_name);
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withOutput(output.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_MERGE.k_INTO into,
            TableName table_name,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_OUTPUT output){
        return new b_MERGE(){
            {
                withInto();
                withTargetTable(table_name);
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withOutput(output.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_MERGE.k_INTO into,
            TableName table_name,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_OPTION option){
        return new b_MERGE(){
            {
                withInto();
                withTargetTable(table_name);
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_MERGE.k_INTO into,
            TableName table_name,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on){
        return new b_MERGE(){
            {
                withInto();
                withTargetTable(table_name);
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OUTPUT output,
            b_OPTION option){
        return new b_MERGE(){
            {
                withInto();
                withTargetTable(table_name);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOutput(output.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OUTPUT output){
        return new b_MERGE(){
            {
                withInto();
                withTargetTable(table_name);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOutput(output.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OPTION option){
        return new b_MERGE(){
            {
                withInto();
                withTargetTable(table_name);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source){
        return new b_MERGE(){
            {
                withInto();
                withTargetTable(table_name);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_OUTPUT output,
            b_OPTION option){
        return new b_MERGE(){
            {
                withInto();
                withTargetTable(table_name);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withOutput(output.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_OUTPUT output){
        return new b_MERGE(){
            {
                withInto();
                withTargetTable(table_name);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withOutput(output.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_OPTION option){
        return new b_MERGE(){
            {
                withInto();
                withTargetTable(table_name);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched){
        return new b_MERGE(){
            {
                withInto();
                withTargetTable(table_name);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OUTPUT output,
            b_OPTION option){
        return new b_MERGE(){
            {
                withInto();
                withTargetTable(table_name);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOutput(output.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OUTPUT output){
        return new b_MERGE(){
            {
                withInto();
                withTargetTable(table_name);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOutput(output.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OPTION option){
        return new b_MERGE(){
            {
                withInto();
                withTargetTable(table_name);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source){
        return new b_MERGE(){
            {
                withInto();
                withTargetTable(table_name);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_OUTPUT output,
            b_OPTION option){
        return new b_MERGE(){
            {
                withInto();
                withTargetTable(table_name);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withOutput(output.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_OUTPUT output){
        return new b_MERGE(){
            {
                withInto();
                withTargetTable(table_name);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withOutput(output.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_OPTION option){
        return new b_MERGE(){
            {
                withInto();
                withTargetTable(table_name);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched){
        return new b_MERGE(){
            {
                withInto();
                withTargetTable(table_name);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OUTPUT output,
            b_OPTION option){
        return new b_MERGE(){
            {
                withInto();
                withTargetTable(table_name);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOutput(output.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OUTPUT output){
        return new b_MERGE(){
            {
                withInto();
                withTargetTable(table_name);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOutput(output.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OPTION option){
        return new b_MERGE(){
            {
                withInto();
                withTargetTable(table_name);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source){
        return new b_MERGE(){
            {
                withInto();
                withTargetTable(table_name);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_OUTPUT output,
            b_OPTION option){
        return new b_MERGE(){
            {
                withInto();
                withTargetTable(table_name);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withOutput(output.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_OUTPUT output){
        return new b_MERGE(){
            {
                withInto();
                withTargetTable(table_name);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withOutput(output.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_OPTION option){
        return new b_MERGE(){
            {
                withInto();
                withTargetTable(table_name);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched){
        return new b_MERGE(){
            {
                withInto();
                withTargetTable(table_name);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OUTPUT output,
            b_OPTION option){
        return new b_MERGE(){
            {
                withInto();
                withTargetTable(table_name);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOutput(output.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OUTPUT output){
        return new b_MERGE(){
            {
                withInto();
                withTargetTable(table_name);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOutput(output.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OPTION option){
        return new b_MERGE(){
            {
                withInto();
                withTargetTable(table_name);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source){
        return new b_MERGE(){
            {
                withInto();
                withTargetTable(table_name);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_OUTPUT output,
            b_OPTION option){
        return new b_MERGE(){
            {
                withInto();
                withTargetTable(table_name);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withOutput(output.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_OUTPUT output){
        return new b_MERGE(){
            {
                withInto();
                withTargetTable(table_name);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withOutput(output.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_OPTION option){
        return new b_MERGE(){
            {
                withInto();
                withTargetTable(table_name);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            b_MERGE.k_INTO into,
            TableName table_name,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on){
        return new b_MERGE(){
            {
                withInto();
                withTargetTable(table_name);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
            }
        };
    }
    static b_MERGE MERGE(
            TableName table_name,
            b_MERGE.b_WITH with,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OUTPUT output,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTargetTable(table_name);
                withMergeHint(with.build());
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOutput(output.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            TableName table_name,
            b_MERGE.b_WITH with,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OUTPUT output){
        return new b_MERGE(){
            {
                withTargetTable(table_name);
                withMergeHint(with.build());
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOutput(output.build());
            }
        };
    }
    static b_MERGE MERGE(
            TableName table_name,
            b_MERGE.b_WITH with,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTargetTable(table_name);
                withMergeHint(with.build());
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            TableName table_name,
            b_MERGE.b_WITH with,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source){
        return new b_MERGE(){
            {
                withTargetTable(table_name);
                withMergeHint(with.build());
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
            }
        };
    }
    static b_MERGE MERGE(
            TableName table_name,
            b_MERGE.b_WITH with,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_OUTPUT output,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTargetTable(table_name);
                withMergeHint(with.build());
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withOutput(output.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            TableName table_name,
            b_MERGE.b_WITH with,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_OUTPUT output){
        return new b_MERGE(){
            {
                withTargetTable(table_name);
                withMergeHint(with.build());
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withOutput(output.build());
            }
        };
    }
    static b_MERGE MERGE(
            TableName table_name,
            b_MERGE.b_WITH with,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTargetTable(table_name);
                withMergeHint(with.build());
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            TableName table_name,
            b_MERGE.b_WITH with,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched){
        return new b_MERGE(){
            {
                withTargetTable(table_name);
                withMergeHint(with.build());
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
            }
        };
    }
    static b_MERGE MERGE(
            TableName table_name,
            b_MERGE.b_WITH with,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OUTPUT output,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTargetTable(table_name);
                withMergeHint(with.build());
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOutput(output.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            TableName table_name,
            b_MERGE.b_WITH with,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OUTPUT output){
        return new b_MERGE(){
            {
                withTargetTable(table_name);
                withMergeHint(with.build());
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOutput(output.build());
            }
        };
    }
    static b_MERGE MERGE(
            TableName table_name,
            b_MERGE.b_WITH with,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTargetTable(table_name);
                withMergeHint(with.build());
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            TableName table_name,
            b_MERGE.b_WITH with,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source){
        return new b_MERGE(){
            {
                withTargetTable(table_name);
                withMergeHint(with.build());
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
            }
        };
    }
    static b_MERGE MERGE(
            TableName table_name,
            b_MERGE.b_WITH with,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_OUTPUT output,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTargetTable(table_name);
                withMergeHint(with.build());
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withOutput(output.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            TableName table_name,
            b_MERGE.b_WITH with,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_OUTPUT output){
        return new b_MERGE(){
            {
                withTargetTable(table_name);
                withMergeHint(with.build());
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withOutput(output.build());
            }
        };
    }
    static b_MERGE MERGE(
            TableName table_name,
            b_MERGE.b_WITH with,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTargetTable(table_name);
                withMergeHint(with.build());
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            TableName table_name,
            b_MERGE.b_WITH with,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched){
        return new b_MERGE(){
            {
                withTargetTable(table_name);
                withMergeHint(with.build());
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
            }
        };
    }
    static b_MERGE MERGE(
            TableName table_name,
            b_MERGE.b_WITH with,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OUTPUT output,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTargetTable(table_name);
                withMergeHint(with.build());
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOutput(output.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            TableName table_name,
            b_MERGE.b_WITH with,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OUTPUT output){
        return new b_MERGE(){
            {
                withTargetTable(table_name);
                withMergeHint(with.build());
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOutput(output.build());
            }
        };
    }
    static b_MERGE MERGE(
            TableName table_name,
            b_MERGE.b_WITH with,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTargetTable(table_name);
                withMergeHint(with.build());
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            TableName table_name,
            b_MERGE.b_WITH with,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source){
        return new b_MERGE(){
            {
                withTargetTable(table_name);
                withMergeHint(with.build());
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
            }
        };
    }
    static b_MERGE MERGE(
            TableName table_name,
            b_MERGE.b_WITH with,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_OUTPUT output,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTargetTable(table_name);
                withMergeHint(with.build());
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withOutput(output.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            TableName table_name,
            b_MERGE.b_WITH with,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_OUTPUT output){
        return new b_MERGE(){
            {
                withTargetTable(table_name);
                withMergeHint(with.build());
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withOutput(output.build());
            }
        };
    }
    static b_MERGE MERGE(
            TableName table_name,
            b_MERGE.b_WITH with,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTargetTable(table_name);
                withMergeHint(with.build());
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            TableName table_name,
            b_MERGE.b_WITH with,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched){
        return new b_MERGE(){
            {
                withTargetTable(table_name);
                withMergeHint(with.build());
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
            }
        };
    }
    static b_MERGE MERGE(
            TableName table_name,
            b_MERGE.b_WITH with,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OUTPUT output,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTargetTable(table_name);
                withMergeHint(with.build());
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOutput(output.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            TableName table_name,
            b_MERGE.b_WITH with,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OUTPUT output){
        return new b_MERGE(){
            {
                withTargetTable(table_name);
                withMergeHint(with.build());
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOutput(output.build());
            }
        };
    }
    static b_MERGE MERGE(
            TableName table_name,
            b_MERGE.b_WITH with,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTargetTable(table_name);
                withMergeHint(with.build());
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            TableName table_name,
            b_MERGE.b_WITH with,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source){
        return new b_MERGE(){
            {
                withTargetTable(table_name);
                withMergeHint(with.build());
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
            }
        };
    }
    static b_MERGE MERGE(
            TableName table_name,
            b_MERGE.b_WITH with,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_OUTPUT output,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTargetTable(table_name);
                withMergeHint(with.build());
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withOutput(output.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            TableName table_name,
            b_MERGE.b_WITH with,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_OUTPUT output){
        return new b_MERGE(){
            {
                withTargetTable(table_name);
                withMergeHint(with.build());
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withOutput(output.build());
            }
        };
    }
    static b_MERGE MERGE(
            TableName table_name,
            b_MERGE.b_WITH with,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTargetTable(table_name);
                withMergeHint(with.build());
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            TableName table_name,
            b_MERGE.b_WITH with,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on){
        return new b_MERGE(){
            {
                withTargetTable(table_name);
                withMergeHint(with.build());
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
            }
        };
    }
    static b_MERGE MERGE(
            TableName table_name,
            b_MERGE.b_WITH with,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OUTPUT output,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOutput(output.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            TableName table_name,
            b_MERGE.b_WITH with,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OUTPUT output){
        return new b_MERGE(){
            {
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOutput(output.build());
            }
        };
    }
    static b_MERGE MERGE(
            TableName table_name,
            b_MERGE.b_WITH with,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            TableName table_name,
            b_MERGE.b_WITH with,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source){
        return new b_MERGE(){
            {
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
            }
        };
    }
    static b_MERGE MERGE(
            TableName table_name,
            b_MERGE.b_WITH with,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_OUTPUT output,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withOutput(output.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            TableName table_name,
            b_MERGE.b_WITH with,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_OUTPUT output){
        return new b_MERGE(){
            {
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withOutput(output.build());
            }
        };
    }
    static b_MERGE MERGE(
            TableName table_name,
            b_MERGE.b_WITH with,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            TableName table_name,
            b_MERGE.b_WITH with,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched){
        return new b_MERGE(){
            {
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
            }
        };
    }
    static b_MERGE MERGE(
            TableName table_name,
            b_MERGE.b_WITH with,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OUTPUT output,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOutput(output.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            TableName table_name,
            b_MERGE.b_WITH with,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OUTPUT output){
        return new b_MERGE(){
            {
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOutput(output.build());
            }
        };
    }
    static b_MERGE MERGE(
            TableName table_name,
            b_MERGE.b_WITH with,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            TableName table_name,
            b_MERGE.b_WITH with,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source){
        return new b_MERGE(){
            {
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
            }
        };
    }
    static b_MERGE MERGE(
            TableName table_name,
            b_MERGE.b_WITH with,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_OUTPUT output,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withOutput(output.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            TableName table_name,
            b_MERGE.b_WITH with,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_OUTPUT output){
        return new b_MERGE(){
            {
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withOutput(output.build());
            }
        };
    }
    static b_MERGE MERGE(
            TableName table_name,
            b_MERGE.b_WITH with,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            TableName table_name,
            b_MERGE.b_WITH with,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched){
        return new b_MERGE(){
            {
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
            }
        };
    }
    static b_MERGE MERGE(
            TableName table_name,
            b_MERGE.b_WITH with,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OUTPUT output,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOutput(output.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            TableName table_name,
            b_MERGE.b_WITH with,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OUTPUT output){
        return new b_MERGE(){
            {
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOutput(output.build());
            }
        };
    }
    static b_MERGE MERGE(
            TableName table_name,
            b_MERGE.b_WITH with,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            TableName table_name,
            b_MERGE.b_WITH with,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source){
        return new b_MERGE(){
            {
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
            }
        };
    }
    static b_MERGE MERGE(
            TableName table_name,
            b_MERGE.b_WITH with,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_OUTPUT output,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withOutput(output.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            TableName table_name,
            b_MERGE.b_WITH with,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_OUTPUT output){
        return new b_MERGE(){
            {
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withOutput(output.build());
            }
        };
    }
    static b_MERGE MERGE(
            TableName table_name,
            b_MERGE.b_WITH with,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            TableName table_name,
            b_MERGE.b_WITH with,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched){
        return new b_MERGE(){
            {
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
            }
        };
    }
    static b_MERGE MERGE(
            TableName table_name,
            b_MERGE.b_WITH with,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OUTPUT output,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOutput(output.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            TableName table_name,
            b_MERGE.b_WITH with,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OUTPUT output){
        return new b_MERGE(){
            {
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOutput(output.build());
            }
        };
    }
    static b_MERGE MERGE(
            TableName table_name,
            b_MERGE.b_WITH with,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            TableName table_name,
            b_MERGE.b_WITH with,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source){
        return new b_MERGE(){
            {
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
            }
        };
    }
    static b_MERGE MERGE(
            TableName table_name,
            b_MERGE.b_WITH with,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_OUTPUT output,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withOutput(output.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            TableName table_name,
            b_MERGE.b_WITH with,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_OUTPUT output){
        return new b_MERGE(){
            {
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withOutput(output.build());
            }
        };
    }
    static b_MERGE MERGE(
            TableName table_name,
            b_MERGE.b_WITH with,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            TableName table_name,
            b_MERGE.b_WITH with,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on){
        return new b_MERGE(){
            {
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
            }
        };
    }
    static b_MERGE MERGE(
            TableName table_name,
            b_MERGE.b_WITH with,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OUTPUT output,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOutput(output.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            TableName table_name,
            b_MERGE.b_WITH with,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OUTPUT output){
        return new b_MERGE(){
            {
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOutput(output.build());
            }
        };
    }
    static b_MERGE MERGE(
            TableName table_name,
            b_MERGE.b_WITH with,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            TableName table_name,
            b_MERGE.b_WITH with,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source){
        return new b_MERGE(){
            {
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
            }
        };
    }
    static b_MERGE MERGE(
            TableName table_name,
            b_MERGE.b_WITH with,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_OUTPUT output,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withOutput(output.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            TableName table_name,
            b_MERGE.b_WITH with,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_OUTPUT output){
        return new b_MERGE(){
            {
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withOutput(output.build());
            }
        };
    }
    static b_MERGE MERGE(
            TableName table_name,
            b_MERGE.b_WITH with,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            TableName table_name,
            b_MERGE.b_WITH with,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched){
        return new b_MERGE(){
            {
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
            }
        };
    }
    static b_MERGE MERGE(
            TableName table_name,
            b_MERGE.b_WITH with,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OUTPUT output,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOutput(output.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            TableName table_name,
            b_MERGE.b_WITH with,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OUTPUT output){
        return new b_MERGE(){
            {
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOutput(output.build());
            }
        };
    }
    static b_MERGE MERGE(
            TableName table_name,
            b_MERGE.b_WITH with,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            TableName table_name,
            b_MERGE.b_WITH with,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source){
        return new b_MERGE(){
            {
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
            }
        };
    }
    static b_MERGE MERGE(
            TableName table_name,
            b_MERGE.b_WITH with,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_OUTPUT output,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withOutput(output.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            TableName table_name,
            b_MERGE.b_WITH with,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_OUTPUT output){
        return new b_MERGE(){
            {
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withOutput(output.build());
            }
        };
    }
    static b_MERGE MERGE(
            TableName table_name,
            b_MERGE.b_WITH with,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            TableName table_name,
            b_MERGE.b_WITH with,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched){
        return new b_MERGE(){
            {
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
            }
        };
    }
    static b_MERGE MERGE(
            TableName table_name,
            b_MERGE.b_WITH with,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OUTPUT output,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOutput(output.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            TableName table_name,
            b_MERGE.b_WITH with,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OUTPUT output){
        return new b_MERGE(){
            {
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOutput(output.build());
            }
        };
    }
    static b_MERGE MERGE(
            TableName table_name,
            b_MERGE.b_WITH with,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            TableName table_name,
            b_MERGE.b_WITH with,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source){
        return new b_MERGE(){
            {
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
            }
        };
    }
    static b_MERGE MERGE(
            TableName table_name,
            b_MERGE.b_WITH with,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_OUTPUT output,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withOutput(output.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            TableName table_name,
            b_MERGE.b_WITH with,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_OUTPUT output){
        return new b_MERGE(){
            {
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withOutput(output.build());
            }
        };
    }
    static b_MERGE MERGE(
            TableName table_name,
            b_MERGE.b_WITH with,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            TableName table_name,
            b_MERGE.b_WITH with,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched){
        return new b_MERGE(){
            {
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
            }
        };
    }
    static b_MERGE MERGE(
            TableName table_name,
            b_MERGE.b_WITH with,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OUTPUT output,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOutput(output.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            TableName table_name,
            b_MERGE.b_WITH with,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OUTPUT output){
        return new b_MERGE(){
            {
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOutput(output.build());
            }
        };
    }
    static b_MERGE MERGE(
            TableName table_name,
            b_MERGE.b_WITH with,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            TableName table_name,
            b_MERGE.b_WITH with,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source){
        return new b_MERGE(){
            {
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
            }
        };
    }
    static b_MERGE MERGE(
            TableName table_name,
            b_MERGE.b_WITH with,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_OUTPUT output,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withOutput(output.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            TableName table_name,
            b_MERGE.b_WITH with,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_OUTPUT output){
        return new b_MERGE(){
            {
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withOutput(output.build());
            }
        };
    }
    static b_MERGE MERGE(
            TableName table_name,
            b_MERGE.b_WITH with,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            TableName table_name,
            b_MERGE.b_WITH with,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on){
        return new b_MERGE(){
            {
                withTargetTable(table_name);
                withMergeHint(with.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
            }
        };
    }
    static b_MERGE MERGE(
            TableName table_name,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OUTPUT output,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTargetTable(table_name);
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOutput(output.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            TableName table_name,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OUTPUT output){
        return new b_MERGE(){
            {
                withTargetTable(table_name);
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOutput(output.build());
            }
        };
    }
    static b_MERGE MERGE(
            TableName table_name,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTargetTable(table_name);
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            TableName table_name,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source){
        return new b_MERGE(){
            {
                withTargetTable(table_name);
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
            }
        };
    }
    static b_MERGE MERGE(
            TableName table_name,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_OUTPUT output,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTargetTable(table_name);
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withOutput(output.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            TableName table_name,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_OUTPUT output){
        return new b_MERGE(){
            {
                withTargetTable(table_name);
                withAs();
                withTableAlias(as.build());
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withOutput(output.build());
            }
        };
    }
    static b_MERGE MERGE(
            TableName table_name,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTargetTable(table_name);
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            TableName table_name,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched){
        return new b_MERGE(){
            {
                withTargetTable(table_name);
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
            }
        };
    }
    static b_MERGE MERGE(
            TableName table_name,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OUTPUT output,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTargetTable(table_name);
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOutput(output.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            TableName table_name,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OUTPUT output){
        return new b_MERGE(){
            {
                withTargetTable(table_name);
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOutput(output.build());
            }
        };
    }
    static b_MERGE MERGE(
            TableName table_name,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTargetTable(table_name);
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            TableName table_name,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source){
        return new b_MERGE(){
            {
                withTargetTable(table_name);
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
            }
        };
    }
    static b_MERGE MERGE(
            TableName table_name,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_OUTPUT output,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTargetTable(table_name);
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withOutput(output.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            TableName table_name,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_OUTPUT output){
        return new b_MERGE(){
            {
                withTargetTable(table_name);
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withOutput(output.build());
            }
        };
    }
    static b_MERGE MERGE(
            TableName table_name,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTargetTable(table_name);
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            TableName table_name,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched){
        return new b_MERGE(){
            {
                withTargetTable(table_name);
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
            }
        };
    }
    static b_MERGE MERGE(
            TableName table_name,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OUTPUT output,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTargetTable(table_name);
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOutput(output.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            TableName table_name,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OUTPUT output){
        return new b_MERGE(){
            {
                withTargetTable(table_name);
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOutput(output.build());
            }
        };
    }
    static b_MERGE MERGE(
            TableName table_name,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTargetTable(table_name);
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            TableName table_name,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source){
        return new b_MERGE(){
            {
                withTargetTable(table_name);
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
            }
        };
    }
    static b_MERGE MERGE(
            TableName table_name,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_OUTPUT output,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTargetTable(table_name);
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withOutput(output.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            TableName table_name,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_OUTPUT output){
        return new b_MERGE(){
            {
                withTargetTable(table_name);
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withOutput(output.build());
            }
        };
    }
    static b_MERGE MERGE(
            TableName table_name,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTargetTable(table_name);
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            TableName table_name,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched){
        return new b_MERGE(){
            {
                withTargetTable(table_name);
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
            }
        };
    }
    static b_MERGE MERGE(
            TableName table_name,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OUTPUT output,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTargetTable(table_name);
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOutput(output.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            TableName table_name,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OUTPUT output){
        return new b_MERGE(){
            {
                withTargetTable(table_name);
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOutput(output.build());
            }
        };
    }
    static b_MERGE MERGE(
            TableName table_name,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTargetTable(table_name);
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            TableName table_name,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source){
        return new b_MERGE(){
            {
                withTargetTable(table_name);
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
            }
        };
    }
    static b_MERGE MERGE(
            TableName table_name,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_OUTPUT output,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTargetTable(table_name);
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withOutput(output.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            TableName table_name,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_OUTPUT output){
        return new b_MERGE(){
            {
                withTargetTable(table_name);
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withOutput(output.build());
            }
        };
    }
    static b_MERGE MERGE(
            TableName table_name,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTargetTable(table_name);
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            TableName table_name,
            b_SELECT.b_AS as,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on){
        return new b_MERGE(){
            {
                withTargetTable(table_name);
                withAs();
                withTableAlias(as.build());
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
            }
        };
    }
    static b_MERGE MERGE(
            TableName table_name,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OUTPUT output,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTargetTable(table_name);
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOutput(output.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            TableName table_name,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OUTPUT output){
        return new b_MERGE(){
            {
                withTargetTable(table_name);
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOutput(output.build());
            }
        };
    }
    static b_MERGE MERGE(
            TableName table_name,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTargetTable(table_name);
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            TableName table_name,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source){
        return new b_MERGE(){
            {
                withTargetTable(table_name);
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
            }
        };
    }
    static b_MERGE MERGE(
            TableName table_name,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_OUTPUT output,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTargetTable(table_name);
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withOutput(output.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            TableName table_name,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_OUTPUT output){
        return new b_MERGE(){
            {
                withTargetTable(table_name);
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withOutput(output.build());
            }
        };
    }
    static b_MERGE MERGE(
            TableName table_name,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTargetTable(table_name);
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            TableName table_name,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched){
        return new b_MERGE(){
            {
                withTargetTable(table_name);
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
            }
        };
    }
    static b_MERGE MERGE(
            TableName table_name,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OUTPUT output,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTargetTable(table_name);
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOutput(output.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            TableName table_name,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OUTPUT output){
        return new b_MERGE(){
            {
                withTargetTable(table_name);
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOutput(output.build());
            }
        };
    }
    static b_MERGE MERGE(
            TableName table_name,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTargetTable(table_name);
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            TableName table_name,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source){
        return new b_MERGE(){
            {
                withTargetTable(table_name);
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
            }
        };
    }
    static b_MERGE MERGE(
            TableName table_name,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_OUTPUT output,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTargetTable(table_name);
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withOutput(output.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            TableName table_name,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_OUTPUT output){
        return new b_MERGE(){
            {
                withTargetTable(table_name);
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withOutput(output.build());
            }
        };
    }
    static b_MERGE MERGE(
            TableName table_name,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTargetTable(table_name);
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            TableName table_name,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched){
        return new b_MERGE(){
            {
                withTargetTable(table_name);
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
            }
        };
    }
    static b_MERGE MERGE(
            TableName table_name,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OUTPUT output,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTargetTable(table_name);
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOutput(output.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            TableName table_name,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OUTPUT output){
        return new b_MERGE(){
            {
                withTargetTable(table_name);
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOutput(output.build());
            }
        };
    }
    static b_MERGE MERGE(
            TableName table_name,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTargetTable(table_name);
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            TableName table_name,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source){
        return new b_MERGE(){
            {
                withTargetTable(table_name);
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
            }
        };
    }
    static b_MERGE MERGE(
            TableName table_name,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_OUTPUT output,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTargetTable(table_name);
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withOutput(output.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            TableName table_name,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_OUTPUT output){
        return new b_MERGE(){
            {
                withTargetTable(table_name);
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withOutput(output.build());
            }
        };
    }
    static b_MERGE MERGE(
            TableName table_name,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTargetTable(table_name);
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            TableName table_name,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched){
        return new b_MERGE(){
            {
                withTargetTable(table_name);
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
            }
        };
    }
    static b_MERGE MERGE(
            TableName table_name,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OUTPUT output,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTargetTable(table_name);
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOutput(output.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            TableName table_name,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OUTPUT output){
        return new b_MERGE(){
            {
                withTargetTable(table_name);
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOutput(output.build());
            }
        };
    }
    static b_MERGE MERGE(
            TableName table_name,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTargetTable(table_name);
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            TableName table_name,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source){
        return new b_MERGE(){
            {
                withTargetTable(table_name);
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
            }
        };
    }
    static b_MERGE MERGE(
            TableName table_name,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_OUTPUT output,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTargetTable(table_name);
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withOutput(output.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            TableName table_name,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_OUTPUT output){
        return new b_MERGE(){
            {
                withTargetTable(table_name);
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withOutput(output.build());
            }
        };
    }
    static b_MERGE MERGE(
            TableName table_name,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTargetTable(table_name);
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            TableName table_name,
            String table_alias,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on){
        return new b_MERGE(){
            {
                withTargetTable(table_name);
                withTableAlias(table_alias);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
            }
        };
    }
    static b_MERGE MERGE(
            TableName table_name,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OUTPUT output,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTargetTable(table_name);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOutput(output.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            TableName table_name,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OUTPUT output){
        return new b_MERGE(){
            {
                withTargetTable(table_name);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOutput(output.build());
            }
        };
    }
    static b_MERGE MERGE(
            TableName table_name,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTargetTable(table_name);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            TableName table_name,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source){
        return new b_MERGE(){
            {
                withTargetTable(table_name);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
            }
        };
    }
    static b_MERGE MERGE(
            TableName table_name,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_OUTPUT output,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTargetTable(table_name);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withOutput(output.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            TableName table_name,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_OUTPUT output){
        return new b_MERGE(){
            {
                withTargetTable(table_name);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withOutput(output.build());
            }
        };
    }
    static b_MERGE MERGE(
            TableName table_name,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTargetTable(table_name);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            TableName table_name,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched){
        return new b_MERGE(){
            {
                withTargetTable(table_name);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
            }
        };
    }
    static b_MERGE MERGE(
            TableName table_name,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OUTPUT output,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTargetTable(table_name);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOutput(output.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            TableName table_name,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OUTPUT output){
        return new b_MERGE(){
            {
                withTargetTable(table_name);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOutput(output.build());
            }
        };
    }
    static b_MERGE MERGE(
            TableName table_name,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTargetTable(table_name);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            TableName table_name,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source){
        return new b_MERGE(){
            {
                withTargetTable(table_name);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
            }
        };
    }
    static b_MERGE MERGE(
            TableName table_name,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_OUTPUT output,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTargetTable(table_name);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withOutput(output.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            TableName table_name,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_OUTPUT output){
        return new b_MERGE(){
            {
                withTargetTable(table_name);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withOutput(output.build());
            }
        };
    }
    static b_MERGE MERGE(
            TableName table_name,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTargetTable(table_name);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            TableName table_name,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_MATCHED when_matched){
        return new b_MERGE(){
            {
                withTargetTable(table_name);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenMatchedThen(when_matched.build());
            }
        };
    }
    static b_MERGE MERGE(
            TableName table_name,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OUTPUT output,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTargetTable(table_name);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOutput(output.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            TableName table_name,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OUTPUT output){
        return new b_MERGE(){
            {
                withTargetTable(table_name);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOutput(output.build());
            }
        };
    }
    static b_MERGE MERGE(
            TableName table_name,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTargetTable(table_name);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            TableName table_name,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source){
        return new b_MERGE(){
            {
                withTargetTable(table_name);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
            }
        };
    }
    static b_MERGE MERGE(
            TableName table_name,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_OUTPUT output,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTargetTable(table_name);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withOutput(output.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            TableName table_name,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_OUTPUT output){
        return new b_MERGE(){
            {
                withTargetTable(table_name);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withOutput(output.build());
            }
        };
    }
    static b_MERGE MERGE(
            TableName table_name,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTargetTable(table_name);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            TableName table_name,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED when_not_matched){
        return new b_MERGE(){
            {
                withTargetTable(table_name);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedTargetThen(when_not_matched.build());
            }
        };
    }
    static b_MERGE MERGE(
            TableName table_name,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OUTPUT output,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTargetTable(table_name);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOutput(output.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            TableName table_name,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OUTPUT output){
        return new b_MERGE(){
            {
                withTargetTable(table_name);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOutput(output.build());
            }
        };
    }
    static b_MERGE MERGE(
            TableName table_name,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTargetTable(table_name);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            TableName table_name,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE when_not_matched_by_source){
        return new b_MERGE(){
            {
                withTargetTable(table_name);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withWhenNotMatchedSourceThen(when_not_matched_by_source.build());
            }
        };
    }
    static b_MERGE MERGE(
            TableName table_name,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_OUTPUT output,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTargetTable(table_name);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withOutput(output.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            TableName table_name,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_OUTPUT output){
        return new b_MERGE(){
            {
                withTargetTable(table_name);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withOutput(output.build());
            }
        };
    }
    static b_MERGE MERGE(
            TableName table_name,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on,
            b_OPTION option){
        return new b_MERGE(){
            {
                withTargetTable(table_name);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
                withOption(option.build());
            }
        };
    }
    static b_MERGE MERGE(
            TableName table_name,
            b_MERGE.b_USING using,
            b$search_condition.b_ON on){
        return new b_MERGE(){
            {
                withTargetTable(table_name);
                withTableSource(using.build());
                withMergeSearchCondition(on.build());
            }
        };
    }


    //

    static b_MERGE.k_INTO INTO(){
        return null;
    }

    //


    static b_MERGE.b_WITH WITH(b$table_hint_limited... with_item){
        return new b_MERGE.b_WITH(){
            {
                list(target::getTableHintLimitedList, target::setTableHintLimitedList)
                        .addAll(Arrays.stream(with_item).map(CodeBuilder::build));
            }
        };
    }


    //

    static b_MERGE.b_USING USING(
            TableName table_or_view_name,
            b_SELECT.b_AS as,
            b_FROM.b_TABLESAMPLE tablesample,
            b_FROM.$base_table.b_WITH with){
        return new b_MERGE.b_USING(){
            {
                _Base()
                        .withTableName(table_or_view_name)
                        .withAs()
                        .withTableAlias(as.build())
                        .withTableSample(tablesample.build())
                        .withTableHint(with.build());
            }
        };
    }
    static b_MERGE.b_USING USING(
            TableName table_or_view_name,
            b_SELECT.b_AS as,
            b_FROM.b_TABLESAMPLE tablesample){
        return new b_MERGE.b_USING(){
            {
                _Base()
                        .withTableName(table_or_view_name)
                        .withAs()
                        .withTableAlias(as.build())
                        .withTableSample(tablesample.build());
            }
        };
    }
    static b_MERGE.b_USING USING(
            TableName table_or_view_name,
            b_SELECT.b_AS as,
            b_FROM.$base_table.b_WITH with){
        return new b_MERGE.b_USING(){
            {
                _Base()
                        .withTableName(table_or_view_name)
                        .withAs()
                        .withTableAlias(as.build())
                        .withTableHint(with.build());
            }
        };
    }
    static b_MERGE.b_USING USING(
            TableName table_or_view_name,
            b_SELECT.b_AS as){
        return new b_MERGE.b_USING(){
            {
                _Base()
                        .withTableName(table_or_view_name)
                        .withAs()
                        .withTableAlias(as.build());
            }
        };
    }

    static b_MERGE.b_USING USING(
            TableName table_or_view_name,
            String table_alias,
            b_FROM.b_TABLESAMPLE tablesample,
            b_FROM.$base_table.b_WITH with){
        return new b_MERGE.b_USING(){
            {
                _Base()
                        .withTableName(table_or_view_name)
                        .withTableAlias(table_alias)
                        .withTableSample(tablesample.build())
                        .withTableHint(with.build());
            }
        };
    }
    static b_MERGE.b_USING USING(
            TableName table_or_view_name,
            String table_alias,
            b_FROM.b_TABLESAMPLE tablesample){
        return new b_MERGE.b_USING(){
            {
                _Base()
                        .withTableName(table_or_view_name)
                        .withTableAlias(table_alias)
                        .withTableSample(tablesample.build());
            }
        };
    }
    static b_MERGE.b_USING USING(
            TableName table_or_view_name,
            String table_alias,
            b_FROM.$base_table.b_WITH with){
        return new b_MERGE.b_USING(){
            {
                _Base()
                        .withTableName(table_or_view_name)
                        .withTableAlias(table_alias)
                        .withTableHint(with.build());
            }
        };
    }
    static b_MERGE.b_USING USING(
            TableName table_or_view_name,
            String table_alias){
        return new b_MERGE.b_USING(){
            {
                _Base()
                        .withTableName(table_or_view_name)
                        .withTableAlias(table_alias);
            }
        };
    }

    static b_MERGE.b_USING USING(
            TableName table_or_view_name,
            b_FROM.b_TABLESAMPLE tablesample,
            b_FROM.$base_table.b_WITH with){
        return new b_MERGE.b_USING(){
            {
                _Base()
                        .withTableName(table_or_view_name)
                        .withTableSample(tablesample.build())
                        .withTableHint(with.build());
            }
        };
    }
    static b_MERGE.b_USING USING(
            TableName table_or_view_name,
            b_FROM.b_TABLESAMPLE tablesample){
        return new b_MERGE.b_USING(){
            {
                _Base()
                        .withTableName(table_or_view_name)
                        .withTableSample(tablesample.build());
            }
        };
    }
    static b_MERGE.b_USING USING(
            TableName table_or_view_name,
            b_FROM.$base_table.b_WITH with){
        return new b_MERGE.b_USING(){
            {
                _Base()
                        .withTableName(table_or_view_name)
                        .withTableHint(with.build());
            }
        };
    }
    static b_MERGE.b_USING USING(
            TableName table_or_view_name){
        return new b_MERGE.b_USING(){
            {
                _Base()
                        .withTableName(table_or_view_name);
            }
        };
    }

    //derived_table

    static b_MERGE.b_USING USING(
            Select derived_table,
            b_SELECT.b_AS as,
            String... column_alias){
        return new b_MERGE.b_USING(){
            {
                _Derived()
                        .withSubQuery(derived_table)
                        .withAs()
                        .withTableAlias(as.build())
                        .withColumnAlias(column_alias);
            }
        };
    }
    static b_MERGE.b_USING USING(
            Select derived_table,
            String table_alias,
            String... column_alias){
        return new b_MERGE.b_USING(){
            {
                _Derived()
                        .withSubQuery(derived_table)
                        .withTableAlias(table_alias)
                        .withColumnAlias(column_alias);
            }
        };
    }
    static b_MERGE.b_USING USING(
            Select derived_table,
            String... column_alias){
        return new b_MERGE.b_USING(){
            {
                _Derived()
                        .withSubQuery(derived_table)
                        .withColumnAlias(column_alias);
            }
        };
    }

    static b_MERGE.b_USING USING(
            b_VALUES values,
            b_SELECT.b_AS as,
            String... column_alias){
        return new b_MERGE.b_USING(){
            {
                _Derived()
                        .withValues(values.build())
                        .withAs()
                        .withTableAlias(as.build())
                        .withColumnAlias(column_alias);
            }
        };
    }
    static b_MERGE.b_USING USING(
            b_VALUES values,
            String table_alias,
            String... column_alias){
        return new b_MERGE.b_USING(){
            {
                _Derived()
                        .withValues(values.build())
                        .withTableAlias(table_alias)
                        .withColumnAlias(column_alias);
            }
        };
    }
    static b_MERGE.b_USING USING(
            b_VALUES values,
            String... column_alias){
        return new b_MERGE.b_USING(){
            {
                _Derived()
                        .withValues(values.build())
                        .withColumnAlias(column_alias);
            }
        };
    }

    //variable_table

    static b_MERGE.b_USING USING(
            String variable,
            b_SELECT.b_AS as){
        return new b_MERGE.b_USING(){
            {
                _Variable()
                        .withVariable(e_variable(variable))
                        .withAs()
                        .withTableAlias(as.build());
            }
        };
    }
    static b_MERGE.b_USING USING(
            String variable,
            String table_alias){
        return new b_MERGE.b_USING(){
            {
                _Variable()
                        .withVariable(e_variable(variable))
                        .withTableAlias(table_alias);
            }
        };
    }
    static b_MERGE.b_USING USING(
            String variable){
        return new b_MERGE.b_USING(){
            {
                _Variable()
                        .withVariable(e_variable(variable));
            }
        };
    }

    //base_time_table

    static b_MERGE.b_USING USING(
            TableName table_or_view_name,
            b_FROM.b_FOR_SYSTEM_TIME for_system_time){
        return new b_MERGE.b_USING(){
            {
                _BaseTime()
                        .withTableName(table_or_view_name);
            }
        };
    }


    //

    static b$search_condition.b_ON ON(Predicate predicate){
        return new b$search_condition.b_ON(){
            {
                withPredicate(predicate);
            }
        };
    }

    static b$search_condition.b_ON ON(b$search_condition search_condition){
        return new b$search_condition.b_ON(){
            {
                withSearchCondition(search_condition.build());
            }
        };
    }

    //

    static b_MERGE.b_WHEN_MATCHED WHEN_MATCHED(
            b$search_condition.b_AND and,
            b_MERGE.b_THEN_UPDATE_DELETE then){
        return new b_MERGE.b_WHEN_MATCHED(){
            {
                withItem()
                        .withClauseSearchCondition(and.build())
                        .withMergeMatched(then.build());
            }
        };
    }
    static b_MERGE.b_WHEN_MATCHED WHEN_MATCHED(
            b_MERGE.b_THEN_UPDATE_DELETE then){
        return new b_MERGE.b_WHEN_MATCHED(){
            {
                withItem()
                        .withMergeMatched(then.build());
            }
        };
    }

    //
    static b_MERGE.b_WHEN_NOT_MATCHED WHEN_NOT_MATCHED_BY_TARGET(
            b$search_condition.b_AND and,
            b_MERGE.b_THEN_INSERT then){
        return new b_MERGE.b_WHEN_NOT_MATCHED(){
            {
                withByTarget(true)
                        .withClauseSearchCondition(and.build())
                        .withMergeNotMatched(then.build());
            }
        };
    }
    static b_MERGE.b_WHEN_NOT_MATCHED WHEN_NOT_MATCHED_BY_TARGET(
            b_MERGE.b_THEN_INSERT then){
        return new b_MERGE.b_WHEN_NOT_MATCHED(){
            {
                withByTarget(true)
                        .withMergeNotMatched(then.build());
            }
        };
    }
    static b_MERGE.b_WHEN_NOT_MATCHED WHEN_NOT_MATCHED(
            b$search_condition.b_AND and,
            b_MERGE.b_THEN_INSERT then){
        return new b_MERGE.b_WHEN_NOT_MATCHED(){
            {
                withClauseSearchCondition(and.build())
                        .withMergeNotMatched(then.build());
            }
        };
    }
    static b_MERGE.b_WHEN_NOT_MATCHED WHEN_NOT_MATCHED(
            b_MERGE.b_THEN_INSERT then){
        return new b_MERGE.b_WHEN_NOT_MATCHED(){
            {
                withMergeNotMatched(then.build());
            }
        };
    }

    //

    static b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE WHEN_NOT_MATCHED_BY_SOURCE(
            b$search_condition.b_AND and,
            b_MERGE.b_THEN_UPDATE_DELETE then){
        return new b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE(){
            {
                withItem()
                        .withClauseSearchCondition(and.build())
                        .withMergeMatched(then.build());
            }
        };
    }
    static b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE WHEN_NOT_MATCHED_BY_SOURCE(
            b_MERGE.b_THEN_UPDATE_DELETE then){
        return new b_MERGE.b_WHEN_NOT_MATCHED_BY_SOURCE(){
            {
                withItem()
                        .withMergeMatched(then.build());
            }
        };
    }

    //

    static b$search_condition.b_AND AND(Predicate predicate){
        return new b$search_condition.b_AND(){
            {
                withPredicate(predicate);
            }
        };
    }

    static b$search_condition.b_AND AND(b$search_condition search_condition){
        return new b$search_condition.b_AND(){
            {
                withSearchCondition(search_condition.build());
            }
        };
    }

    //

    //

    static b_MERGE.b_THEN_UPDATE_DELETE THEN(
            b_MERGE.b_THEN_UPDATE_DELETE.b_UPDATE update
    ){
        return new b_MERGE.b_THEN_UPDATE_DELETE(){
            {
                withSet(true);
                withSetItem(update.build());
            }
        };
    }
    static b_MERGE.b_THEN_UPDATE_DELETE THEN(
            b_MERGE.b_THEN_UPDATE_DELETE.k_DELETE delete
    ){
        return new b_MERGE.b_THEN_UPDATE_DELETE();
    }
    static b_MERGE.b_THEN_INSERT THEN(
            b_INSERT insert
    ){
        return new b_MERGE.b_THEN_INSERT(){
            {
                withValues(insert.build().getValues());
                withColumn(insert.build().getColumns());
                withDefaultValues(insert.build().isUseDefaultValues());
            }
        };
    }

    //

    static b_MERGE.b_THEN_UPDATE_DELETE.b_UPDATE UPATE(b_UPDATE.b_SET set){
        return new b_MERGE.b_THEN_UPDATE_DELETE.b_UPDATE(){
            {
                this.target = set.build();
            }
        };
    }
    static b_MERGE.b_THEN_UPDATE_DELETE.k_DELETE DELETE(){
        return null;
    }
    static b_INSERT INSERT(
            b_INSERT.b$column_list column_list,
            b_VALUES values){
        return new b_INSERT(){
            {
                withColumn(column_list.build());
                withValues(values.build());
            }
        };
    }
    static b_INSERT INSERT(
            b_INSERT.b$column_list column_list,
            b_INSERT.k_DEFAULT_VALUES default_values){
        return new b_INSERT(){
            {
                withColumn(column_list.build());
                withDefaultValues();
            }
        };
    }
    static b_INSERT INSERT(
            b_VALUES values){
        return new b_INSERT(){
            {
                withValues(values.build());
            }
        };
    }
    static b_INSERT INSERT(
            b_INSERT.k_DEFAULT_VALUES default_values){
        return new b_INSERT(){
            {
                withDefaultValues();
            }
        };
    }

    //

    static b_INSERT.b$column_list $(ColumnName... columnName){
        return new b_INSERT.b$column_list(){
            {
                this.target.addAll(Arrays.asList(columnName));
            }
        };
    }

}
