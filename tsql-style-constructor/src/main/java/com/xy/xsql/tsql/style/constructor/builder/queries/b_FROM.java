package com.xy.xsql.tsql.style.constructor.builder.queries;

import com.xy.xsql.core.builder.CodeBuilder;
import com.xy.xsql.tsql.builder.chain.queries.FromBuilder;
import com.xy.xsql.tsql.model.datatypes.table.TableName;
import com.xy.xsql.tsql.model.queries.From;
import com.xy.xsql.tsql.model.queries.hints.TableHint;
import com.xy.xsql.tsql.style.constructor.builder.queries.hints.b$join_hint;
import com.xy.xsql.tsql.style.constructor.builder.queries.select.b_ORDER_BY;
import com.xy.xsql.tsql.style.constructor.builder.queries.select.b_SELECT;

import java.util.ArrayList;
import java.util.List;

import static com.xy.xsql.core.ListBuilder.popLastItem;

/**
 * Created by xiaoyao9184 on 2015/5/25.
 */
public class b_FROM extends FromBuilder<b_FROM> {

    public b_FROM() {
        this.in(this);
    }


    /*
     * Item
     */

    public b_FROM $$(
            TableName table_or_view_name,
            b_SELECT.b_AS as,
            b_TABLESAMPLE tablesample,
            $base_table.b_WITH with){
        return withItem()._Base()
                .withTableName(table_or_view_name)
                .withAs()
                .withTableAlias(as.build())
                .withTableSample(tablesample.build())
                .withTableHint(with.build())
                .and()
                .and();
    }
    public b_FROM $$(
            TableName table_or_view_name,
            b_SELECT.b_AS as,
            b_TABLESAMPLE tablesample){
        return withItem()._Base()
                .withTableName(table_or_view_name)
                .withAs()
                .withTableAlias(as.build())
                .withTableSample(tablesample.build())
                .and()
                .and();
    }
    public b_FROM $$(
            TableName table_or_view_name,
            b_SELECT.b_AS as,
            $base_table.b_WITH with){
        return withItem()._Base()
                .withTableName(table_or_view_name)
                .withAs()
                .withTableAlias(as.build())
                .withTableHint(with.build())
                .and()
                .and();
    }
    public b_FROM $$(
            TableName table_or_view_name,
            b_SELECT.b_AS as){
        return withItem()._Base()
                .withTableName(table_or_view_name)
                .withAs()
                .withTableAlias(as.build())
                .and()
                .and();
    }

    public b_FROM $$(
            TableName table_or_view_name,
            String table_alias,
            b_TABLESAMPLE tablesample,
            $base_table.b_WITH with){
        return withItem()._Base()
                .withTableName(table_or_view_name)
                .withTableAlias(table_alias)
                .withTableSample(tablesample.build())
                .withTableHint(with.build())
                .and()
                .and();
    }
    public b_FROM $$(
            TableName table_or_view_name,
            String table_alias,
            b_TABLESAMPLE tablesample){
        return withItem()._Base()
                .withTableName(table_or_view_name)
                .withTableAlias(table_alias)
                .withTableSample(tablesample.build())
                .and()
                .and();
    }
    public b_FROM $$(
            TableName table_or_view_name,
            String table_alias,
            $base_table.b_WITH with){
        return withItem()._Base()
                .withTableName(table_or_view_name)
                .withTableAlias(table_alias)
                .withTableHint(with.build())
                .and()
                .and();
    }
    public b_FROM $$(
            TableName table_or_view_name,
            String table_alias){
        return withItem()._Base()
                .withTableName(table_or_view_name)
                .withTableAlias(table_alias)
                .and()
                .and();
    }

    public b_FROM $$(
            TableName table_or_view_name,
            b_TABLESAMPLE tablesample,
            $base_table.b_WITH with){
        return withItem()._Base()
                .withTableName(table_or_view_name)
                .withTableSample(tablesample.build())
                .withTableHint(with.build())
                .and()
                .and();
    }
    public b_FROM $$(
            TableName table_or_view_name,
            b_TABLESAMPLE tablesample){
        return withItem()._Base()
                .withTableName(table_or_view_name)
                .withTableSample(tablesample.build())
                .and()
                .and();
    }
    public b_FROM $$(
            TableName table_or_view_name,
            $base_table.b_WITH with){
        return withItem()._Base()
                .withTableName(table_or_view_name)
                .withTableHint(with.build())
                .and()
                .and();
    }
    public b_FROM $$(
            TableName table_or_view_name){
        return withItem()._Base()
                .withTableName(table_or_view_name)
                .and()
                .and();
    }




    //change to joined_table


    public b_FROM $(
            b$join_hint join_hint,
            b_JOIN join,
            b$search_condition.b_ON on){
        From.TableSource ts = popLastItem(target.getTableSourceList());
        ts = join.withTableSource(ts)
                .withJoinType(From.JoinTypeKeywords.JOIN,join_hint.build())
                .withSearchCondition(on.build())
                .build();
        return withItem(ts)
                .and();
    }
    public b_FROM $(
            b$join_hint join_hint,
            b_JOIN join){
        From.TableSource ts = popLastItem(target.getTableSourceList());
        ts = join.withTableSource(ts)
                .withJoinType(From.JoinTypeKeywords.JOIN,join_hint.build())
                .build();
        return withItem(ts)
                .and();
    }
    public b_FROM $(
        b_JOIN join,
        b$search_condition.b_ON on){
        From.TableSource ts = popLastItem(target.getTableSourceList());
        ts = join.withTableSource(ts)
                .withJoinType(From.JoinTypeKeywords.JOIN)
                .withSearchCondition(on.build())
                .build();
        return withItem(ts)
                .and();
    }
    public b_FROM $(
            b_JOIN join){
        From.TableSource ts = popLastItem(target.getTableSourceList());
        ts = join.withTableSource(ts)
                .withJoinType(From.JoinTypeKeywords.JOIN)
                .build();
        return withItem(ts)
                .and();
    }


    public b_FROM INNER(
            b$join_hint join_hint,
            b_JOIN join,
            b$search_condition.b_ON on){
        From.TableSource ts = popLastItem(target.getTableSourceList());
        ts = join.withTableSource(ts)
                .withJoinType(From.JoinTypeKeywords.INNER_JOIN,join_hint.build())
                .withSearchCondition(on.build())
                .build();
        return withItem(ts)
                .and();
    }
    public b_FROM INNER(
            b$join_hint join_hint,
            b_JOIN join){
        From.TableSource ts = popLastItem(target.getTableSourceList());
        ts = join.withTableSource(ts)
                .withJoinType(From.JoinTypeKeywords.INNER_JOIN,join_hint.build())
                .build();
        return withItem(ts)
                .and();
    }
    public b_FROM INNER(
            b_JOIN join,
            b$search_condition.b_ON on){
        From.TableSource ts = popLastItem(target.getTableSourceList());
        ts = join.withTableSource(ts)
                .withJoinType(From.JoinTypeKeywords.INNER_JOIN)
                .withSearchCondition(on.build())
                .build();
        return withItem(ts)
                .and();
    }
    public b_FROM INNER(
            b_JOIN join){
        From.TableSource ts = popLastItem(target.getTableSourceList());
        ts = join.withTableSource(ts)
                .withJoinType(From.JoinTypeKeywords.INNER_JOIN)
                .build();
        return withItem(ts)
                .and();
    }
    public b_FROM LEFT_OUTER(
            b$join_hint join_hint,
            b_JOIN join,
            b$search_condition.b_ON on){
        From.TableSource ts = popLastItem(target.getTableSourceList());
        ts = join.withTableSource(ts)
                .withJoinType(From.JoinTypeKeywords.LEFT_OUTER_JOIN,join_hint.build())
                .withSearchCondition(on.build())
                .build();
        return withItem(ts)
                .and();
    }
    public b_FROM LEFT_OUTER(
            b$join_hint join_hint,
            b_JOIN join){
        From.TableSource ts = popLastItem(target.getTableSourceList());
        ts = join.withTableSource(ts)
                .withJoinType(From.JoinTypeKeywords.LEFT_OUTER_JOIN,join_hint.build())
                .build();
        return withItem(ts)
                .and();
    }
    public b_FROM LEFT_OUTER(
            b_JOIN join,
            b$search_condition.b_ON on){
        From.TableSource ts = popLastItem(target.getTableSourceList());
        ts = join.withTableSource(ts)
                .withJoinType(From.JoinTypeKeywords.LEFT_OUTER_JOIN)
                .withSearchCondition(on.build())
                .build();
        return withItem(ts)
                .and();
    }
    public b_FROM LEFT_OUTER(
            b_JOIN join){
        From.TableSource ts = popLastItem(target.getTableSourceList());
        ts = join.withTableSource(ts)
                .withJoinType(From.JoinTypeKeywords.LEFT_OUTER_JOIN)
                .build();
        return withItem(ts)
                .and();
    }
    public b_FROM LEFT(
            b$join_hint join_hint,
            b_JOIN join,
            b$search_condition.b_ON on){
        From.TableSource ts = popLastItem(target.getTableSourceList());
        ts = join.withTableSource(ts)
                .withJoinType(From.JoinTypeKeywords.LEFT_JOIN,join_hint.build())
                .withSearchCondition(on.build())
                .build();
        return withItem(ts)
                .and();
    }
    public b_FROM LEFT(
            b$join_hint join_hint,
            b_JOIN join){
        From.TableSource ts = popLastItem(target.getTableSourceList());
        ts = join.withTableSource(ts)
                .withJoinType(From.JoinTypeKeywords.LEFT_JOIN,join_hint.build())
                .build();
        return withItem(ts)
                .and();
    }
    public b_FROM LEFT(
            b_JOIN join,
            b$search_condition.b_ON on){
        From.TableSource ts = popLastItem(target.getTableSourceList());
        ts = join.withTableSource(ts)
                .withJoinType(From.JoinTypeKeywords.LEFT_JOIN)
                .withSearchCondition(on.build())
                .build();
        return withItem(ts)
                .and();
    }
    public b_FROM LEFT(
            b_JOIN join){
        From.TableSource ts = popLastItem(target.getTableSourceList());
        ts = join.withTableSource(ts)
                .withJoinType(From.JoinTypeKeywords.LEFT_JOIN)
                .build();
        return withItem(ts)
                .and();
    }
    public b_FROM RIGHT_OUTER(
            b$join_hint join_hint,
            b_JOIN join,
            b$search_condition.b_ON on){
        From.TableSource ts = popLastItem(target.getTableSourceList());
        ts = join.withTableSource(ts)
                .withJoinType(From.JoinTypeKeywords.RIGHT_OUTER_JOIN,join_hint.build())
                .withSearchCondition(on.build())
                .build();
        return withItem(ts)
                .and();
    }
    public b_FROM RIGHT_OUTER(
            b$join_hint join_hint,
            b_JOIN join){
        From.TableSource ts = popLastItem(target.getTableSourceList());
        ts = join.withTableSource(ts)
                .withJoinType(From.JoinTypeKeywords.RIGHT_OUTER_JOIN,join_hint.build())
                .build();
        return withItem(ts)
                .and();
    }
    public b_FROM RIGHT_OUTER(
            b_JOIN join,
            b$search_condition.b_ON on){
        From.TableSource ts = popLastItem(target.getTableSourceList());
        ts = join.withTableSource(ts)
                .withJoinType(From.JoinTypeKeywords.RIGHT_OUTER_JOIN)
                .withSearchCondition(on.build())
                .build();
        return withItem(ts)
                .and();
    }
    public b_FROM RIGHT_OUTER(
            b_JOIN join){
        From.TableSource ts = popLastItem(target.getTableSourceList());
        ts = join.withTableSource(ts)
                .withJoinType(From.JoinTypeKeywords.RIGHT_OUTER_JOIN)
                .build();
        return withItem(ts)
                .and();
    }
    public b_FROM RIGHT(
            b$join_hint join_hint,
            b_JOIN join,
            b$search_condition.b_ON on){
        From.TableSource ts = popLastItem(target.getTableSourceList());
        ts = join.withTableSource(ts)
                .withJoinType(From.JoinTypeKeywords.RIGHT_JOIN,join_hint.build())
                .withSearchCondition(on.build())
                .build();
        return withItem(ts)
                .and();
    }
    public b_FROM RIGHT(
            b$join_hint join_hint,
            b_JOIN join){
        From.TableSource ts = popLastItem(target.getTableSourceList());
        ts = join.withTableSource(ts)
                .withJoinType(From.JoinTypeKeywords.RIGHT_JOIN,join_hint.build())
                .build();
        return withItem(ts)
                .and();
    }
    public b_FROM RIGHT(
            b_JOIN join,
            b$search_condition.b_ON on){
        From.TableSource ts = popLastItem(target.getTableSourceList());
        ts = join.withTableSource(ts)
                .withJoinType(From.JoinTypeKeywords.RIGHT_JOIN)
                .withSearchCondition(on.build())
                .build();
        return withItem(ts)
                .and();
    }
    public b_FROM RIGHT(
            b_JOIN join){
        From.TableSource ts = popLastItem(target.getTableSourceList());
        ts = join.withTableSource(ts)
                .withJoinType(From.JoinTypeKeywords.RIGHT_JOIN)
                .build();
        return withItem(ts)
                .and();
    }
    public b_FROM FULL_OUTER(
            b$join_hint join_hint,
            b_JOIN join,
            b$search_condition.b_ON on){
        From.TableSource ts = popLastItem(target.getTableSourceList());
        ts = join.withTableSource(ts)
                .withJoinType(From.JoinTypeKeywords.FULL_OUTER_JOIN,join_hint.build())
                .withSearchCondition(on.build())
                .build();
        return withItem(ts)
                .and();
    }
    public b_FROM FULL_OUTER(
            b$join_hint join_hint,
            b_JOIN join){
        From.TableSource ts = popLastItem(target.getTableSourceList());
        ts = join.withTableSource(ts)
                .withJoinType(From.JoinTypeKeywords.FULL_OUTER_JOIN,join_hint.build())
                .build();
        return withItem(ts)
                .and();
    }
    public b_FROM FULL_OUTER(
            b_JOIN join,
            b$search_condition.b_ON on){
        From.TableSource ts = popLastItem(target.getTableSourceList());
        ts = join.withTableSource(ts)
                .withJoinType(From.JoinTypeKeywords.FULL_OUTER_JOIN)
                .withSearchCondition(on.build())
                .build();
        return withItem(ts)
                .and();
    }
    public b_FROM FULL_OUTER(
            b_JOIN join){
        From.TableSource ts = popLastItem(target.getTableSourceList());
        ts = join.withTableSource(ts)
                .withJoinType(From.JoinTypeKeywords.FULL_OUTER_JOIN)
                .build();
        return withItem(ts)
                .and();
    }
    public b_FROM FULL(
            b$join_hint join_hint,
            b_JOIN join,
            b$search_condition.b_ON on){
        From.TableSource ts = popLastItem(target.getTableSourceList());
        ts = join.withTableSource(ts)
                .withJoinType(From.JoinTypeKeywords.FULL_JOIN,join_hint.build())
                .withSearchCondition(on.build())
                .build();
        return withItem(ts)
                .and();
    }
    public b_FROM FULL(
            b$join_hint join_hint,
            b_JOIN join){
        From.TableSource ts = popLastItem(target.getTableSourceList());
        ts = join.withTableSource(ts)
                .withJoinType(From.JoinTypeKeywords.FULL_JOIN,join_hint.build())
                .build();
        return withItem(ts)
                .and();
    }
    public b_FROM FULL(
            b_JOIN join,
            b$search_condition.b_ON on){
        From.TableSource ts = popLastItem(target.getTableSourceList());
        ts = join.withTableSource(ts)
                .withJoinType(From.JoinTypeKeywords.FULL_JOIN)
                .withSearchCondition(on.build())
                .build();
        return withItem(ts)
                .and();
    }
    public b_FROM FULL(
            b_JOIN join){
        From.TableSource ts = popLastItem(target.getTableSourceList());
        ts = join.withTableSource(ts)
                .withJoinType(From.JoinTypeKeywords.FULL_JOIN)
                .build();
        return withItem(ts)
                .and();
    }

    public b_FROM CROSS(b_JOIN join){
        From.TableSource ts = popLastItem(target.getTableSourceList());
        ts = join.withTableSource(ts)
                .withCrossJoin()
                .build();
        return withItem(ts)
                .and();
    }
    public b_FROM CROSS(b_APPLY apply){
        From.TableSource ts = popLastItem(target.getTableSourceList());
        ts = apply.withTableSource(ts)
                .withCrossApply()
                .build();
        return withItem(ts)
                .and();
    }
    public b_FROM OUTER(b_APPLY apply){
        From.TableSource ts = popLastItem(target.getTableSourceList());
        ts = apply.withTableSource(ts)
                .withOuterApply()
                .build();
        return withItem(ts)
                .and();
    }

    
    
    public static class $base_table extends BaseTableBuilder<$base_table> {

        public $base_table() {
            this.in(this);
        }

        public static class b_WITH extends CodeBuilder<List<TableHint>>{

            public b_WITH() {
                super(new ArrayList<>());
            }
            
        }
    }

    public static class b_JOIN extends JoinedTableBuilder<b_JOIN> {

        public b_JOIN() {
            this.in(this);
        }

    }

    public static class b_APPLY extends JoinedTableBuilder<b_APPLY> {

        public b_APPLY() {
            this.in(this);
        }

    }

    public static class b_FOR_SYSTEM_TIME extends BaseWithTimeTableBuilder<b_FOR_SYSTEM_TIME> {

        public b_FOR_SYSTEM_TIME() {
            this.in(this);
        }

    }

    public static class b_TABLESAMPLE extends TableSampleBuilder<b_TABLESAMPLE> {

        public b_TABLESAMPLE() {
            this.in(this);
        }

        public static class k_SYSTEM {}

        /**
         * same as
         * @see b_FROM.b_TABLESAMPLE.k_ROWS
         * @see b_ORDER_BY.k_ROWS
         */
        public static class k_ROWS {}

        public static class b_REPEATABLE extends CodeBuilder<Integer>{

            public b_REPEATABLE(Integer repeat_seed) {
                super(repeat_seed);
            }

        }

    }

    public static class b$system_time extends SystemTimeBuilder<b$system_time> {

        public b$system_time() {
            this.in(this);
        }

        public static class b_AS_OF extends CodeBuilder<From.DateTime>{
            public b_AS_OF(From.DateTime dateTime) {
                super(dateTime);
            }
        }

        public static class b_FROM_ extends CodeBuilder<From.DateTime>{
            public b_FROM_(From.DateTime dateTime) {
                super(dateTime);
            }
        }

        public static class b_TO extends CodeBuilder<From.DateTime>{
            public b_TO(From.DateTime dateTime) {
                super(dateTime);
            }
        }

        public static class b_BETWEEN extends CodeBuilder<From.DateTime>{
            public b_BETWEEN(From.DateTime dateTime) {
                super(dateTime);
            }
        }

        public static class b_AND extends CodeBuilder<From.DateTime>{
            public b_AND(From.DateTime dateTime) {
                super(dateTime);
            }
        }

        public static class b_CONTAINED_IN extends CodeBuilder<From.DateTime[]>{
            public b_CONTAINED_IN(From.DateTime... dateTime) {
                super(dateTime);
            }
        }

    }

}
