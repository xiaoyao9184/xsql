package com.xy.xsql.tsql.style.constructor.introducer.queries;

import com.xy.xsql.core.builder.CodeTreeBuilder;
import com.xy.xsql.core.builder.parent.ParentHoldBuilder;
import com.xy.xsql.tsql.model.datatypes.table.TableName;
import com.xy.xsql.tsql.model.elements.variables.LocalVariable;
import com.xy.xsql.tsql.model.queries.From;
import com.xy.xsql.tsql.model.queries.Select;
import com.xy.xsql.tsql.model.queries.predicates.Predicate;
import com.xy.xsql.tsql.style.constructor.builder.queries.b_FROM;
import com.xy.xsql.tsql.style.constructor.builder.queries.b_VALUES;
import com.xy.xsql.tsql.style.constructor.builder.queries.hints.b$table_hint;
import com.xy.xsql.tsql.style.constructor.builder.queries.k_;
import com.xy.xsql.tsql.style.constructor.builder.queries.select.b_SELECT;
import com.xy.xsql.tsql.style.constructor.builder.queries.b$search_condition;

import java.util.Arrays;
import java.util.stream.Collectors;

import static com.xy.xsql.tsql.builder.chain.elements.expressions.Expressions.e_string;
import static com.xy.xsql.tsql.builder.chain.elements.expressions.Expressions.e_variable;
import static com.xy.xsql.tsql.builder.chain.queries.Queries.$SubQuery;

/**
 * Created by xiaoyao9184 on 2018/5/9.
 */
@SuppressWarnings({"WeakerAccess", "unused"})
public interface from {

    //base_table

    static b_FROM FROM(
            TableName table_or_view_name,
            b_SELECT.b_AS as,
            b_FROM.b_TABLESAMPLE tablesample,
            b_FROM.$base_table.b_WITH with){
        return new b_FROM(){
            {
                withItem()._Base()
                        .withTableName(table_or_view_name)
                        .withAs()
                        .withTableAlias(as.build())
                        .withTableSample(tablesample.build())
                        .withTableHint(with.build());
            }
        };
    }
    static b_FROM FROM(
            TableName table_or_view_name,
            b_SELECT.b_AS as,
            b_FROM.b_TABLESAMPLE tablesample){
        return new b_FROM(){
            {
                withItem()._Base()
                        .withTableName(table_or_view_name)
                        .withAs()
                        .withTableAlias(as.build())
                        .withTableSample(tablesample.build());
            }
        };
    }
    static b_FROM FROM(
            TableName table_or_view_name,
            b_SELECT.b_AS as,
            b_FROM.$base_table.b_WITH with){
        return new b_FROM(){
            {
                withItem()._Base()
                        .withTableName(table_or_view_name)
                        .withAs()
                        .withTableAlias(as.build())
                        .withTableHint(with.build());
            }
        };
    }
    static b_FROM FROM(
            TableName table_or_view_name,
            b_SELECT.b_AS as){
        return new b_FROM(){
            {
                withItem()._Base()
                        .withTableName(table_or_view_name)
                        .withAs()
                        .withTableAlias(as.build());
            }
        };
    }

    static b_FROM FROM(
            TableName table_or_view_name,
            String table_alias,
            b_FROM.b_TABLESAMPLE tablesample,
            b_FROM.$base_table.b_WITH with){
        return new b_FROM(){
            {
                withItem()._Base()
                        .withTableName(table_or_view_name)
                        .withTableAlias(table_alias)
                        .withTableSample(tablesample.build())
                        .withTableHint(with.build());
            }
        };
    }
    static b_FROM FROM(
            TableName table_or_view_name,
            String table_alias,
            b_FROM.b_TABLESAMPLE tablesample){
        return new b_FROM(){
            {
                withItem()._Base()
                        .withTableName(table_or_view_name)
                        .withTableAlias(table_alias)
                        .withTableSample(tablesample.build());
            }
        };
    }
    static b_FROM FROM(
            TableName table_or_view_name,
            String table_alias,
            b_FROM.$base_table.b_WITH with){
        return new b_FROM(){
            {
                withItem()._Base()
                        .withTableName(table_or_view_name)
                        .withTableAlias(table_alias)
                        .withTableHint(with.build());
            }
        };
    }
    static b_FROM FROM(
            TableName table_or_view_name,
            String table_alias){
        return new b_FROM(){
            {
                withItem()._Base()
                        .withTableName(table_or_view_name)
                        .withTableAlias(table_alias);
            }
        };
    }

    static b_FROM FROM(
            TableName table_or_view_name,
            b_FROM.b_TABLESAMPLE tablesample,
            b_FROM.$base_table.b_WITH with){
        return new b_FROM(){
            {
                withItem()._Base()
                        .withTableName(table_or_view_name)
                        .withTableSample(tablesample.build())
                        .withTableHint(with.build());
            }
        };
    }
    static b_FROM FROM(
            TableName table_or_view_name,
            b_FROM.b_TABLESAMPLE tablesample){
        return new b_FROM(){
            {
                withItem()._Base()
                        .withTableName(table_or_view_name)
                        .withTableSample(tablesample.build());
            }
        };
    }
    static b_FROM FROM(
            TableName table_or_view_name,
            b_FROM.$base_table.b_WITH with){
        return new b_FROM(){
            {
                withItem()._Base()
                        .withTableName(table_or_view_name)
                        .withTableHint(with.build());
            }
        };
    }
    static b_FROM FROM(
            TableName table_or_view_name){
        return new b_FROM(){
            {
                withItem()._Base()
                        .withTableName(table_or_view_name);
            }
        };
    }

    //derived_table

    static b_FROM FROM(
            Select derived_table,
            b_SELECT.b_AS as,
            String... column_alias){
        return new b_FROM(){
            {
                withItem()._Derived()
                        .withSubQuery(derived_table)
                        .withAs()
                        .withTableAlias(as.build())
                        .withColumnAlias(column_alias);
            }
        };
    }
    static b_FROM FROM(
            Select derived_table,
            String table_alias,
            String... column_alias){
        return new b_FROM(){
            {
                withItem()._Derived()
                        .withSubQuery(derived_table)
                        .withTableAlias(table_alias)
                        .withColumnAlias(column_alias);
            }
        };
    }
    static b_FROM FROM(
            Select derived_table,
            String... column_alias){
        return new b_FROM(){
            {
                withItem()._Derived()
                        .withSubQuery(derived_table)
                        .withColumnAlias(column_alias);
            }
        };
    }

    static b_FROM FROM(
            b_VALUES values,
            b_SELECT.b_AS as,
            String... column_alias){
        return new b_FROM(){
            {
                withItem()._Derived()
                        .withValues(values.build())
                        .withAs()
                        .withTableAlias(as.build())
                        .withColumnAlias(column_alias);
            }
        };
    }
    static b_FROM FROM(
            b_VALUES values,
            String table_alias,
            String... column_alias){
        return new b_FROM(){
            {
                withItem()._Derived()
                        .withValues(values.build())
                        .withTableAlias(table_alias)
                        .withColumnAlias(column_alias);
            }
        };
    }
    static b_FROM FROM(
            b_VALUES values,
            String... column_alias){
        return new b_FROM(){
            {
                withItem()._Derived()
                        .withValues(values.build())
                        .withColumnAlias(column_alias);
            }
        };
    }

    //variable_table

    static b_FROM FROM(
            String variable,
            b_SELECT.b_AS as){
        return new b_FROM(){
            {
                withItem()._Variable()
                        .withVariable(e_variable(variable))
                        .withAs()
                        .withTableAlias(as.build());
            }
        };
    }
    static b_FROM FROM(
            String variable,
            String table_alias){
        return new b_FROM(){
            {
                withItem()._Variable()
                        .withVariable(e_variable(variable))
                        .withTableAlias(table_alias);
            }
        };
    }
    static b_FROM FROM(
            String variable){
        return new b_FROM(){
            {
                withItem()._Variable()
                        .withVariable(e_variable(variable));
            }
        };
    }

    //base_time_table

    static b_FROM FROM(
            TableName table_or_view_name,
            b_FROM.b_FOR_SYSTEM_TIME for_system_time){
        return new b_FROM(){
            {
                withItem(for_system_time.withTableName(table_or_view_name).build());
            }
        };
    }



    //for base_table

    static b_FROM.$base_table.b_WITH WITH(b$table_hint... table_hint){
        return new b_FROM.$base_table.b_WITH(){
            {
                this.target = Arrays.stream(table_hint)
                        .map(ParentHoldBuilder::build)
                        .collect(Collectors.toList());
            }
        };
    }

    //for joined_table

    static b_FROM.b_JOIN JOIN(
            TableName table_or_view_name,
            b_SELECT.b_AS as,
            b_FROM.b_TABLESAMPLE tablesample,
            b_FROM.$base_table.b_WITH with){
        return new b_FROM.b_JOIN(){
            {
                withTableSource2()._Base()
                        .withTableName(table_or_view_name)
                        .withAs()
                        .withTableAlias(as.build())
                        .withTableSample(tablesample.build())
                        .withTableHint(with.build());
            }
        };
    }
    static b_FROM.b_JOIN JOIN(
            TableName table_or_view_name,
            b_SELECT.b_AS as,
            b_FROM.b_TABLESAMPLE tablesample){
        return new b_FROM.b_JOIN(){
            {
                withTableSource2()._Base()
                        .withTableName(table_or_view_name)
                        .withAs()
                        .withTableAlias(as.build())
                        .withTableSample(tablesample.build());
            }
        };
    }
    static b_FROM.b_JOIN JOIN(
            TableName table_or_view_name,
            b_SELECT.b_AS as,
            b_FROM.$base_table.b_WITH with){
        return new b_FROM.b_JOIN(){
            {
                withTableSource2()._Base()
                        .withTableName(table_or_view_name)
                        .withAs()
                        .withTableAlias(as.build())
                        .withTableHint(with.build());
            }
        };
    }
    static b_FROM.b_JOIN JOIN(
            TableName table_or_view_name,
            b_SELECT.b_AS as){
        return new b_FROM.b_JOIN(){
            {
                withTableSource2()._Base()
                        .withTableName(table_or_view_name)
                        .withAs()
                        .withTableAlias(as.build());
            }
        };
    }

    static b_FROM.b_JOIN JOIN(
            TableName table_or_view_name,
            String table_alias,
            b_FROM.b_TABLESAMPLE tablesample,
            b_FROM.$base_table.b_WITH with){
        return new b_FROM.b_JOIN(){
            {
                withTableSource2()._Base()
                        .withTableName(table_or_view_name)
                        .withTableAlias(table_alias)
                        .withTableSample(tablesample.build())
                        .withTableHint(with.build());
            }
        };
    }
    static b_FROM.b_JOIN JOIN(
            TableName table_or_view_name,
            String table_alias,
            b_FROM.b_TABLESAMPLE tablesample){
        return new b_FROM.b_JOIN(){
            {
                withTableSource2()._Base()
                        .withTableName(table_or_view_name)
                        .withTableAlias(table_alias)
                        .withTableSample(tablesample.build());
            }
        };
    }
    static b_FROM.b_JOIN JOIN(
            TableName table_or_view_name,
            String table_alias,
            b_FROM.$base_table.b_WITH with){
        return new b_FROM.b_JOIN(){
            {
                withTableSource2()._Base()
                        .withTableName(table_or_view_name)
                        .withTableAlias(table_alias)
                        .withTableHint(with.build());
            }
        };
    }
    static b_FROM.b_JOIN JOIN(
            TableName table_or_view_name,
            String table_alias){
        return new b_FROM.b_JOIN(){
            {
                withTableSource2()._Base()
                        .withTableName(table_or_view_name)
                        .withTableAlias(table_alias);
            }
        };
    }

    static b_FROM.b_JOIN JOIN(
            TableName table_or_view_name,
            b_FROM.b_TABLESAMPLE tablesample,
            b_FROM.$base_table.b_WITH with){
        return new b_FROM.b_JOIN(){
            {
                withTableSource2()._Base()
                        .withTableName(table_or_view_name)
                        .withTableSample(tablesample.build())
                        .withTableHint(with.build());
            }
        };
    }
    static b_FROM.b_JOIN JOIN(
            TableName table_or_view_name,
            b_FROM.b_TABLESAMPLE tablesample){
        return new b_FROM.b_JOIN(){
            {
                withTableSource2()._Base()
                        .withTableName(table_or_view_name)
                        .withTableSample(tablesample.build());
            }
        };
    }
    static b_FROM.b_JOIN JOIN(
            TableName table_or_view_name,
            b_FROM.$base_table.b_WITH with){
        return new b_FROM.b_JOIN(){
            {
                withTableSource2()._Base()
                        .withTableName(table_or_view_name)
                        .withTableHint(with.build());
            }
        };
    }
    static b_FROM.b_JOIN JOIN(
            TableName table_or_view_name){
        return new b_FROM.b_JOIN(){
            {
                withTableSource2()._Base()
                        .withTableName(table_or_view_name);
            }
        };
    }

    static b_FROM.b_JOIN JOIN(
            Select derived_table,
            b_SELECT.b_AS as,
            String... column_alias){
        return new b_FROM.b_JOIN(){
            {
                withTableSource2()._Derived()
                        .withSubQuery(derived_table)
                        .withAs()
                        .withTableAlias(as.build())
                        .withColumnAlias(column_alias);
            }
        };
    }
    static b_FROM.b_JOIN JOIN(
            Select derived_table,
            String table_alias,
            String... column_alias){
        return new b_FROM.b_JOIN(){
            {
                withTableSource2()._Derived()
                        .withSubQuery(derived_table)
                        .withTableAlias(table_alias)
                        .withColumnAlias(column_alias);
            }
        };
    }
    static b_FROM.b_JOIN JOIN(
            Select derived_table,
            String... column_alias){
        return new b_FROM.b_JOIN(){
            {
                withTableSource2()._Derived()
                        .withSubQuery(derived_table)
                        .withColumnAlias(column_alias);
            }
        };
    }

    static b_FROM.b_JOIN JOIN(
            Select.QuerySpecification derived_table,
            b_SELECT.b_AS as,
            String... column_alias){
        return new b_FROM.b_JOIN(){
            {
                withTableSource2()._Derived()
                        .withSubQuery($SubQuery(derived_table))
                        .withAs()
                        .withTableAlias(as.build())
                        .withColumnAlias(column_alias);
            }
        };
    }
    static b_FROM.b_JOIN JOIN(
            Select.QuerySpecification derived_table,
            String table_alias,
            String... column_alias){
        return new b_FROM.b_JOIN(){
            {
                withTableSource2()._Derived()
                        .withSubQuery($SubQuery(derived_table))
                        .withTableAlias(table_alias)
                        .withColumnAlias(column_alias);
            }
        };
    }
    static b_FROM.b_JOIN JOIN(
            Select.QuerySpecification derived_table,
            String... column_alias){
        return new b_FROM.b_JOIN(){
            {
                withTableSource2()._Derived()
                        .withSubQuery($SubQuery(derived_table))
                        .withColumnAlias(column_alias);
            }
        };
    }

    static b_FROM.b_JOIN JOIN(
            b_VALUES values,
            b_SELECT.b_AS as,
            String... column_alias){
        return new b_FROM.b_JOIN(){
            {
                withTableSource2()._Derived()
                        .withValues(values.build())
                        .withAs()
                        .withTableAlias(as.build())
                        .withColumnAlias(column_alias);
            }
        };
    }
    static b_FROM.b_JOIN JOIN(
            b_VALUES values,
            String table_alias,
            String... column_alias){
        return new b_FROM.b_JOIN(){
            {
                withTableSource2()._Derived()
                        .withValues(values.build())
                        .withTableAlias(table_alias)
                        .withColumnAlias(column_alias);
            }
        };
    }
    static b_FROM.b_JOIN JOIN(
            b_VALUES values,
            String... column_alias){
        return new b_FROM.b_JOIN(){
            {
                withTableSource2()._Derived()
                        .withValues(values.build())
                        .withColumnAlias(column_alias);
            }
        };
    }

    static b_FROM.b_JOIN JOIN(
            String variable,
            b_SELECT.b_AS as){
        return new b_FROM.b_JOIN(){
            {
                withTableSource2()._Variable()
                        .withVariable(e_variable(variable))
                        .withAs()
                        .withTableAlias(as.build());
            }
        };
    }
    static b_FROM.b_JOIN JOIN(
            String variable,
            String table_alias){
        return new b_FROM.b_JOIN(){
            {
                withTableSource2()._Variable()
                        .withVariable(e_variable(variable))
                        .withTableAlias(table_alias);
            }
        };
    }
    static b_FROM.b_JOIN JOIN(
            String variable){
        return new b_FROM.b_JOIN(){
            {
                withTableSource2()._Variable()
                        .withVariable(e_variable(variable));
            }
        };
    }

    static b_FROM.b_JOIN JOIN(
            b_FROM.b_FOR_SYSTEM_TIME for_system_time){
        return new b_FROM.b_JOIN(){
            {
                withTableSource2(for_system_time.build());
            }
        };
    }


    static b_FROM.b_APPLY APPLY(
            TableName table_or_view_name,
            b_SELECT.b_AS as,
            b_FROM.b_TABLESAMPLE tablesample,
            b_FROM.$base_table.b_WITH with){
        return new b_FROM.b_APPLY(){
            {
                withTableSource2()._Base()
                        .withTableName(table_or_view_name)
                        .withAs()
                        .withTableAlias(as.build())
                        .withTableSample(tablesample.build())
                        .withTableHint(with.build());
            }
        };
    }
    static b_FROM.b_APPLY APPLY(
            TableName table_or_view_name,
            b_SELECT.b_AS as,
            b_FROM.b_TABLESAMPLE tablesample){
        return new b_FROM.b_APPLY(){
            {
                withTableSource2()._Base()
                        .withTableName(table_or_view_name)
                        .withAs()
                        .withTableAlias(as.build())
                        .withTableSample(tablesample.build());
            }
        };
    }
    static b_FROM.b_APPLY APPLY(
            TableName table_or_view_name,
            b_SELECT.b_AS as,
            b_FROM.$base_table.b_WITH with){
        return new b_FROM.b_APPLY(){
            {
                withTableSource2()._Base()
                        .withTableName(table_or_view_name)
                        .withAs()
                        .withTableAlias(as.build())
                        .withTableHint(with.build());
            }
        };
    }
    static b_FROM.b_APPLY APPLY(
            TableName table_or_view_name,
            b_SELECT.b_AS as){
        return new b_FROM.b_APPLY(){
            {
                withTableSource2()._Base()
                        .withTableName(table_or_view_name)
                        .withAs()
                        .withTableAlias(as.build());
            }
        };
    }

    static b_FROM.b_APPLY APPLY(
            TableName table_or_view_name,
            String table_alias,
            b_FROM.b_TABLESAMPLE tablesample,
            b_FROM.$base_table.b_WITH with){
        return new b_FROM.b_APPLY(){
            {
                withTableSource2()._Base()
                        .withTableName(table_or_view_name)
                        .withTableAlias(table_alias)
                        .withTableSample(tablesample.build())
                        .withTableHint(with.build());
            }
        };
    }
    static b_FROM.b_APPLY APPLY(
            TableName table_or_view_name,
            String table_alias,
            b_FROM.b_TABLESAMPLE tablesample){
        return new b_FROM.b_APPLY(){
            {
                withTableSource2()._Base()
                        .withTableName(table_or_view_name)
                        .withTableAlias(table_alias)
                        .withTableSample(tablesample.build());
            }
        };
    }
    static b_FROM.b_APPLY APPLY(
            TableName table_or_view_name,
            String table_alias,
            b_FROM.$base_table.b_WITH with){
        return new b_FROM.b_APPLY(){
            {
                withTableSource2()._Base()
                        .withTableName(table_or_view_name)
                        .withTableAlias(table_alias)
                        .withTableHint(with.build());
            }
        };
    }
    static b_FROM.b_APPLY APPLY(
            TableName table_or_view_name,
            String table_alias){
        return new b_FROM.b_APPLY(){
            {
                withTableSource2()._Base()
                        .withTableName(table_or_view_name)
                        .withTableAlias(table_alias);
            }
        };
    }

    static b_FROM.b_APPLY APPLY(
            TableName table_or_view_name,
            b_FROM.b_TABLESAMPLE tablesample,
            b_FROM.$base_table.b_WITH with){
        return new b_FROM.b_APPLY(){
            {
                withTableSource2()._Base()
                        .withTableName(table_or_view_name)
                        .withTableSample(tablesample.build())
                        .withTableHint(with.build());
            }
        };
    }
    static b_FROM.b_APPLY APPLY(
            TableName table_or_view_name,
            b_FROM.b_TABLESAMPLE tablesample){
        return new b_FROM.b_APPLY(){
            {
                withTableSource2()._Base()
                        .withTableName(table_or_view_name)
                        .withTableSample(tablesample.build());
            }
        };
    }
    static b_FROM.b_APPLY APPLY(
            TableName table_or_view_name,
            b_FROM.$base_table.b_WITH with){
        return new b_FROM.b_APPLY(){
            {
                withTableSource2()._Base()
                        .withTableName(table_or_view_name)
                        .withTableHint(with.build());
            }
        };
    }
    static b_FROM.b_APPLY APPLY(
            TableName table_or_view_name){
        return new b_FROM.b_APPLY(){
            {
                withTableSource2()._Base()
                        .withTableName(table_or_view_name);
            }
        };
    }

    static b_FROM.b_APPLY APPLY(
            Select derived_table,
            b_SELECT.b_AS as,
            String... column_alias){
        return new b_FROM.b_APPLY(){
            {
                withTableSource2()._Derived()
                        .withSubQuery(derived_table)
                        .withAs()
                        .withTableAlias(as.build())
                        .withColumnAlias(column_alias);
            }
        };
    }
    static b_FROM.b_APPLY APPLY(
            Select derived_table,
            String table_alias,
            String... column_alias){
        return new b_FROM.b_APPLY(){
            {
                withTableSource2()._Derived()
                        .withSubQuery(derived_table)
                        .withTableAlias(table_alias)
                        .withColumnAlias(column_alias);
            }
        };
    }
    static b_FROM.b_APPLY APPLY(
            Select derived_table,
            String... column_alias){
        return new b_FROM.b_APPLY(){
            {
                withTableSource2()._Derived()
                        .withSubQuery(derived_table)
                        .withColumnAlias(column_alias);
            }
        };
    }

    static b_FROM.b_APPLY APPLY(
            b_VALUES values,
            b_SELECT.b_AS as,
            String... column_alias){
        return new b_FROM.b_APPLY(){
            {
                withTableSource2()._Derived()
                        .withValues(values.build())
                        .withAs()
                        .withTableAlias(as.build())
                        .withColumnAlias(column_alias);
            }
        };
    }
    static b_FROM.b_APPLY APPLY(
            b_VALUES values,
            String table_alias,
            String... column_alias){
        return new b_FROM.b_APPLY(){
            {
                withTableSource2()._Derived()
                        .withValues(values.build())
                        .withTableAlias(table_alias)
                        .withColumnAlias(column_alias);
            }
        };
    }
    static b_FROM.b_APPLY APPLY(
            b_VALUES values,
            String... column_alias){
        return new b_FROM.b_APPLY(){
            {
                withTableSource2()._Derived()
                        .withValues(values.build())
                        .withColumnAlias(column_alias);
            }
        };
    }

    static b_FROM.b_APPLY APPLY(
            String variable,
            b_SELECT.b_AS as){
        return new b_FROM.b_APPLY(){
            {
                withTableSource2()._Variable()
                        .withVariable(e_variable(variable))
                        .withAs()
                        .withTableAlias(as.build());
            }
        };
    }
    static b_FROM.b_APPLY APPLY(
            String variable,
            String table_alias){
        return new b_FROM.b_APPLY(){
            {
                withTableSource2()._Variable()
                        .withVariable(e_variable(variable))
                        .withTableAlias(table_alias);
            }
        };
    }
    static b_FROM.b_APPLY APPLY(
            String variable){
        return new b_FROM.b_APPLY(){
            {
                withTableSource2()._Variable()
                        .withVariable(e_variable(variable));
            }
        };
    }

    static b_FROM.b_APPLY APPLY(
            b_FROM.b_FOR_SYSTEM_TIME for_system_time){
        return new b_FROM.b_APPLY(){
            {
                withTableSource2(for_system_time.build());
            }
        };
    }


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



    //system_time

    static b_FROM.b_FOR_SYSTEM_TIME FOR_SYSTEM_TIME(
            b_FROM.b$system_time.b_AS_OF as_of
    ){
        return new b_FROM.b_FOR_SYSTEM_TIME(){
            {
                withSystemTime()._AsOf(as_of.build());
            }
        };
    }
    static b_FROM.b_FOR_SYSTEM_TIME FOR_SYSTEM_TIME(
            b_FROM.b$system_time.b_FROM_ from,
            b_FROM.b$system_time.b_TO to
    ){
        return new b_FROM.b_FOR_SYSTEM_TIME(){
            {
                withSystemTime()._From()
                        .withFrom(from.build())
                        .withTo(to.build());
            }
        };
    }
    static b_FROM.b_FOR_SYSTEM_TIME FOR_SYSTEM_TIME(
            b_FROM.b$system_time.b_BETWEEN between,
            b_FROM.b$system_time.b_AND and
    ){
        return new b_FROM.b_FOR_SYSTEM_TIME(){
            {
                withSystemTime()._Between()
                        .withBetween(between.build())
                        .withAnd(and.build());
            }
        };
    }
    static b_FROM.b_FOR_SYSTEM_TIME FOR_SYSTEM_TIME(
            b_FROM.b$system_time.b_CONTAINED_IN contained_in
    ){
        return new b_FROM.b_FOR_SYSTEM_TIME(){
            {
                withSystemTime()._ContainedIn()
                        .withStart(contained_in.build()[0])
                        .withEnd(contained_in.build()[1]);
            }
        };
    }
    static b_FROM.b_FOR_SYSTEM_TIME FOR_SYSTEM_TIME(
            k_.k_ALL all
    ){
        return new b_FROM.b_FOR_SYSTEM_TIME(){
            {
                withSystemTime()
                        .$All();
            }
        };
    }

    static b_FROM.b$system_time.b_AS_OF AS_OF(LocalVariable date_time){
        return new b_FROM.b$system_time.b_AS_OF(new From.DateTime(date_time));
    }
    static b_FROM.b$system_time.b_AS_OF AS_OF(String date_time){
        return new b_FROM.b$system_time.b_AS_OF(new From.DateTime(e_string(date_time)));
    }
    static b_FROM.b$system_time.b_FROM_ FROM_(LocalVariable start_date_time){
        return new b_FROM.b$system_time.b_FROM_(new From.DateTime(start_date_time));
    }
    static b_FROM.b$system_time.b_FROM_ FROM_(String start_date_time){
        return new b_FROM.b$system_time.b_FROM_(new From.DateTime(e_string(start_date_time)));
    }
    static b_FROM.b$system_time.b_TO TO(LocalVariable end_date_time){
        return new b_FROM.b$system_time.b_TO(new From.DateTime(end_date_time));
    }
    static b_FROM.b$system_time.b_TO TO(String end_date_time){
        return new b_FROM.b$system_time.b_TO(new From.DateTime(e_string(end_date_time)));
    }
    static b_FROM.b$system_time.b_BETWEEN BETWEEN(LocalVariable start_date_time){
        return new b_FROM.b$system_time.b_BETWEEN(new From.DateTime(start_date_time));
    }
    static b_FROM.b$system_time.b_BETWEEN BETWEEN(String start_date_time){
        return new b_FROM.b$system_time.b_BETWEEN(new From.DateTime(e_string(start_date_time)));
    }
    static b_FROM.b$system_time.b_AND AND(LocalVariable end_date_time){
        return new b_FROM.b$system_time.b_AND(new From.DateTime(end_date_time));
    }
    static b_FROM.b$system_time.b_AND AND(String end_date_time){
        return new b_FROM.b$system_time.b_AND(new From.DateTime(e_string(end_date_time)));
    }
    static b_FROM.b$system_time.b_CONTAINED_IN CONTAINED_IN(
            LocalVariable start_date_time,
            LocalVariable end_date_time){
        return new b_FROM.b$system_time.b_CONTAINED_IN(
                new From.DateTime(start_date_time),
                new From.DateTime(end_date_time));
    }
    static b_FROM.b$system_time.b_CONTAINED_IN CONTAINED_IN(
            LocalVariable start_date_time,
            String end_date_time){
        return new b_FROM.b$system_time.b_CONTAINED_IN(
                new From.DateTime(start_date_time),
                new From.DateTime(e_string(end_date_time)));
    }
    static b_FROM.b$system_time.b_CONTAINED_IN CONTAINED_IN(
            String start_date_time,
            LocalVariable end_date_time){
        return new b_FROM.b$system_time.b_CONTAINED_IN(
                new From.DateTime(e_string(start_date_time)),
                new From.DateTime(end_date_time));
    }
    static b_FROM.b$system_time.b_CONTAINED_IN CONTAINED_IN(
            String start_date_time,
            String end_date_time){
        return new b_FROM.b$system_time.b_CONTAINED_IN(
                new From.DateTime(e_string(start_date_time)),
                new From.DateTime(e_string(end_date_time)));
    }
//    static FROM.$system_time.ALL ALL(){
//        return null;
//    }

    //tablesample_clause

    static b_FROM.b_TABLESAMPLE TABLESAMPLE(
            b_FROM.b_TABLESAMPLE.k_SYSTEM system,
            Integer sample_number,
            k_.k_PERCENT percent,
            b_FROM.b_TABLESAMPLE.b_REPEATABLE repeatable){
        return new b_FROM.b_TABLESAMPLE(){
            {
                withSystem();
                withSampleNumber(sample_number);
                withPercent();
                withRepeatSeed(repeatable.build());
            }
        };
    }
    static b_FROM.b_TABLESAMPLE TABLESAMPLE(
            b_FROM.b_TABLESAMPLE.k_SYSTEM system,
            Integer sample_number,
            k_.k_PERCENT percent){
        return new b_FROM.b_TABLESAMPLE(){
            {
                withSystem();
                withPercent();
                withSampleNumber(sample_number);
            }
        };
    }
    static b_FROM.b_TABLESAMPLE TABLESAMPLE(
            b_FROM.b_TABLESAMPLE.k_SYSTEM system,
            Integer sample_number,
            b_FROM.b_TABLESAMPLE.k_ROWS rows,
            b_FROM.b_TABLESAMPLE.b_REPEATABLE repeatable){
        return new b_FROM.b_TABLESAMPLE(){
            {
                withSystem();
                withSampleNumber(sample_number);
                withRows();
                withRepeatSeed(repeatable.build());
            }
        };
    }
    static b_FROM.b_TABLESAMPLE TABLESAMPLE(
            b_FROM.b_TABLESAMPLE.k_SYSTEM system,
            Integer sample_number,
            b_FROM.b_TABLESAMPLE.k_ROWS rows){
        return new b_FROM.b_TABLESAMPLE(){
            {
                withSystem();
                withRows();
                withSampleNumber(sample_number);
            }
        };
    }

    static b_FROM.b_TABLESAMPLE TABLESAMPLE(
            Integer sample_number,
            k_.k_PERCENT percent,
            b_FROM.b_TABLESAMPLE.b_REPEATABLE repeatable){
        return new b_FROM.b_TABLESAMPLE(){
            {
                withSampleNumber(sample_number);
                withPercent();
                withRepeatSeed(repeatable.build());
            }
        };
    }
    static b_FROM.b_TABLESAMPLE TABLESAMPLE(
            Integer sample_number,
            k_.k_PERCENT percent){
        return new b_FROM.b_TABLESAMPLE(){
            {
                withPercent();
                withSampleNumber(sample_number);
            }
        };
    }
    static b_FROM.b_TABLESAMPLE TABLESAMPLE(
            Integer sample_number,
            b_FROM.b_TABLESAMPLE.k_ROWS rows,
            b_FROM.b_TABLESAMPLE.b_REPEATABLE repeatable){
        return new b_FROM.b_TABLESAMPLE(){
            {
                withSampleNumber(sample_number);
                withRows();
                withRepeatSeed(repeatable.build());
            }
        };
    }
    static b_FROM.b_TABLESAMPLE TABLESAMPLE(
            Integer sample_number,
            b_FROM.b_TABLESAMPLE.k_ROWS rows){
        return new b_FROM.b_TABLESAMPLE(){
            {
                withRows();
                withSampleNumber(sample_number);
            }
        };
    }


    static b_FROM.b_TABLESAMPLE.k_SYSTEM SYSTEM(){
        return null;
    }
    static k_.k_PERCENT PERCENT(){
        return null;
    }
    static b_FROM.b_TABLESAMPLE.k_ROWS ROWS(){
        return null;
    }
    static b_FROM.b_TABLESAMPLE.b_REPEATABLE REPEATABLE(Integer repeat_seed){
        return new b_FROM.b_TABLESAMPLE.b_REPEATABLE(repeat_seed);
    }

    //
}
