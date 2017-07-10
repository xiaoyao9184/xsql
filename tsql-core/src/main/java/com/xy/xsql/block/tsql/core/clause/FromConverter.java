package com.xy.xsql.block.tsql.core.clause;

import com.xy.xsql.block.core.MetaContextBlockBuilder;
import com.xy.xsql.block.core.MetaContextBlockConverter;
import com.xy.xsql.block.core.BlockMetaBuilder;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.block.model.MetaContextBlock;
import com.xy.xsql.block.tsql.core.clause.hints.JoinHintConverter;
import com.xy.xsql.block.tsql.core.clause.hints.TableHintConverter;
import com.xy.xsql.tsql.model.Keywords;
import com.xy.xsql.tsql.model.clause.From;
import com.xy.xsql.tsql.model.element.Other;

/**
 * Created by xiaoyao9184 on 2017/6/20.
 */
public class FromConverter
        implements MetaContextBlockConverter<From> {

    // @formatter:off
    private static BlockMetaBuilder<Void,From> builder =
            new BlockMetaBuilder<Void,From>()
                    .overall("FROM")
                    .sub_keyword(Keywords.FROM)
                    .sub("table_source")
                        .description("{ <table_source> } [ ,...n ]")
                        .required()
                        .list()
                        .ref(TableSourceConverter.class)
                        .data(From::getTableSourceList)
                        .and();
    // @formatter:on

    public static BlockMeta meta() {
        return builder.build();
    }

    @Override
    public MetaContextBlock convert(From context) {
        return MetaContextBlockBuilder
                .meta(meta())
                .build(context);
    }


    public static class TableSourceConverter
            implements MetaContextBlockConverter<From.TableSource> {

        // @formatter:off
        private static BlockMetaBuilder<Void,From.TableSource> builder =
                new BlockMetaBuilder<Void,From.TableSource>()
                        .overall("table_source")
//                        .when(d -> d instanceof From.BaseTable)
//                            .then(BaseTableConverter.meta())
//                        .when(d -> d instanceof From.DerivedTable)
//                            .then(BaseTableConverter.meta())
//                        .when(d -> d instanceof From.JoinedTable)
//                            .then("joined_table")
//                                .ref(From.JoinedTable.class)
//                                .data(d -> d)
//                                .and()
//                        .when(d -> d instanceof From.VariableTable)
//                            .then(VariableTableConverter.meta())

                        .czse_meta(d -> d instanceof From.BaseTable,BaseTableConverter.meta())
                        .czse_meta(d -> d instanceof From.DerivedTable,DerivedTableConverter.meta())
                        .czse(d -> d instanceof From.JoinedTable,"joined_table")
                            .ref(JoinedTableConverter.class)
                            .data(d -> d)
                            .and()
                        .czse_meta(d -> d instanceof From.VariableTable,VariableTableConverter.meta())
                        .czse_meta(d -> d instanceof From.BaseWithTimeTable,BaseWithTimeTableConverter.meta())
//                        .czse_meta(BaseTableConverter.meta())
//                            .filter(d -> d instanceof From.BaseTable)
//                            .and()
//                        .czse_meta(DerivedTableConverter.meta())
//                            .filter(d -> d instanceof From.DerivedTable)
//                            .and()
//                        .czse_meta("joined_table")
//                            .filter(d -> d instanceof From.JoinedTable)
//                            .ref(From.JoinedTable.class)
//                            .data(d -> d)
//                            .and()
//                        .czse_meta(VariableTableConverter.meta())
//                            .filter(d -> d instanceof From.VariableTable)
//                            .and()
                        .subTakeLine();
        // @formatter:on

        public static BlockMeta meta() {
            return builder.build();
        }

        @Override
        public MetaContextBlock convert(From.TableSource context) {
            return MetaContextBlockBuilder
                    .meta(meta())
                    .build(context);
        }
    }

    public static class BaseTableConverter
            implements MetaContextBlockConverter<From.BaseTable> {

        // @formatter:off
        private static BlockMetaBuilder<Void,From.BaseTable> builder =
                new BlockMetaBuilder<Void,From.BaseTable>()
                        .description("base table")
                        .sub("table_or_view_name")
                            .data(From.BaseTable::getTableName)
                            .and()
                        .sub()
                            .description("[ [ AS ] table_alias ]")
                            .optional(bt -> bt.getTableAlias() == null)
                            .sub()
                                .optional(d -> !d.isUseAs())
                                .keyword(Keywords.AS)
                                .and()
                            .sub("table_alias")
                                .data(From.BaseTable::getTableAlias)
                                .and()
                            .and()
                        .sub("tablesample_clause")
                            .optional(d -> d.getTableSample() == null)
                            .ref(TableSampleConverter.class)
                            .data(From.BaseTable::getTableSample)
                            .and()
                        .sub()
                            .description("with")
                            .optional(d -> d.getTableHintList() == null)
                            .sub_keyword(Keywords.WITH)
                            .sub_keyword(Other.GROUP_START)
                            .sub("table_hint")
                                .list()
                                .repeat()
                                .ref(TableHintConverter.class)
                                .data(From.BaseTable::getTableHintList)
                                .and()
                            .sub_keyword(Other.GROUP_END)
                            .startNewline()
                            .and();
        // @formatter:on

        public static BlockMeta meta() {
            return builder.build();
        }

        @Override
        public MetaContextBlock convert(From.BaseTable context) {
            return MetaContextBlockBuilder
                    .meta(meta())
                    .build(context);
        }
    }

    public static class DerivedTableConverter
            implements MetaContextBlockConverter<From.DerivedTable> {

        // @formatter:off
        private static BlockMetaBuilder<Void,From.DerivedTable> builder =
                new BlockMetaBuilder<Void,From.DerivedTable>()
                        .description("derived table")
                        .sub("derived_table")
                            .sub_keyword(Other.GROUP_START)
                            .sub()
                                .description("select/values")
                                .czse(d -> d.getSubQuery() != null)
                                    .description("select")
                                    .data(From.DerivedTable::getSubQuery)
                                    .and()
                                .czse(d -> d.getValues() != null)
                                    .description("values")
                                    .data(From.DerivedTable::getValues)
                                    .and()
                                .and()
                            .sub_keyword(Other.GROUP_END)
                            .and()
                        .sub()
                            .description("[ [ AS ] table_alias ]")
                            .optional(bt -> bt.getTableAlias() == null)
                            .sub()
                                .optional(d -> !d.isUseAs())
                                .keyword(Keywords.AS)
                                .and()
                            .sub("table_alias")
                                .data(From.DerivedTable::getTableAlias)
                                .and()
                            .and()
                        .sub()
                            .description("[ ( column_alias [ ,...n ] ) ]")
                            .optional(bt -> bt.getColumnAliass() == null)
                            .sub_keyword(Other.GROUP_START)
                            .sub_list("column_alias")
                                .data(From.DerivedTable::getColumnAliass)
                                .and()
                            .sub_keyword(Other.GROUP_END)
                            .and();
        // @formatter:on

        public static BlockMeta meta() {
            return builder.build();
        }

        @Override
        public MetaContextBlock convert(From.DerivedTable context) {
            return MetaContextBlockBuilder
                    .meta(meta())
                    .build(context);
        }
    }

    public static class VariableTableConverter
            implements MetaContextBlockConverter<From.VariableTable> {

        // @formatter:off
        private static BlockMetaBuilder<Void,From.VariableTable> builder =
                new BlockMetaBuilder<Void,From.VariableTable>()
                        .description("variable table")
                        .sub("@variable")
                            .data(From.VariableTable::getVariable)
                            .and()
                        .sub()
                            .description("[ [ AS ] table_alias ]")
                            .optional(bt -> bt.getTableAlias() == null)
                            .sub("AS")
                                .optional(t -> true)
                                .keyword(Keywords.AS)
                                .and()
                            .sub("table_alias")
                                .data(From.VariableTable::getTableAlias)
                                .and()
                            .and();
        // @formatter:on

        public static BlockMeta meta() {
            return builder.build();
        }

        @Override
        public MetaContextBlock convert(From.VariableTable context) {
            return MetaContextBlockBuilder
                    .meta(meta())
                    .build(context);
        }
    }

    public static class BaseWithTimeTableConverter
            implements MetaContextBlockConverter<From.BaseWithTimeTable> {

        // @formatter:off
        private static BlockMetaBuilder<Void,From.BaseWithTimeTable> builder =
                new BlockMetaBuilder<Void,From.BaseWithTimeTable>()
                        .description("base time table")
                        .sub("table_or_view_name")
                            .data(From.BaseWithTimeTable::getTableName)
                            .and()
                        .sub_keyword(Keywords.FOR)
                        .sub_keyword(Keywords.Key.SYSTEM_TIME)
                        .sub("system_time")
                            .ref(SystemTimeConverter.class)
                            .data(From.BaseWithTimeTable::getSystemTime)
                            .and();
        // @formatter:on

        public static BlockMeta meta() {
            return builder.build();
        }

        @Override
        public MetaContextBlock convert(From.BaseWithTimeTable context) {
            return MetaContextBlockBuilder
                    .meta(meta())
                    .build(context);
        }
    }

    /**
     *
     */
    public static class TableSampleConverter
            implements MetaContextBlockConverter<From.TableSample> {

        // @formatter:off
        private static BlockMetaBuilder<Void,From.TableSample> builder =
                new BlockMetaBuilder<Void,From.TableSample>()
                        .overall("tablesample_clause")
                        .sub_keyword(Keywords.Key.TABLESAMPLE)
                        .sub()
                            .optional(d -> !d.isUseSystem())
                            .keyword(Keywords.Key.SYSTEM)
                            .and()
                        .sub_keyword(Other.GROUP_START)
                        .sub("sample_number")
                            .data(From.TableSample::getSampleNumber)
                            .and()
                        .sub()
                            .description("percent/rows")
                            .optional(d -> !d.isUseRows() && !d.isUsePercent())
                            .czse_keyword(From.TableSample::isUsePercent,Keywords.PERCENT)
                            .czse_keyword(From.TableSample::isUseRows,Keywords.Key.ROWS)
                            .and()
                        .sub_keyword(Other.GROUP_END)
                        .sub()
                            .description("repeatable")
                            .optional(d -> d.getRepeatSeed() == null)
                            .sub_keyword(Keywords.Key.REPEATABLE)
                            .sub_keyword(Other.GROUP_START)
                            .sub("repeat_seed")
                                .data(From.TableSample::getRepeatSeed)
                                .and()
                            .sub_keyword(Other.GROUP_END)
                            .startNewline()
                            .and();
        // @formatter:on

        public static BlockMeta meta() {
            return builder.build();
        }

        @Override
        public MetaContextBlock convert(From.TableSample context) {
            return MetaContextBlockBuilder
                    .meta(meta())
                    .build(context);
        }
    }

    /**
     *
     */
    public static class JoinedTableConverter
            implements MetaContextBlockConverter<From.JoinedTable> {

        // @formatter:off
        private static BlockMetaBuilder<Void,From.JoinedTable> builder =
                new BlockMetaBuilder<Void,From.JoinedTable>()
                        .overall("joined_table")
                        .czse(From.JoinedTable::isUseJoinOn)
                            .description("base join")
                            .sub("table_source")
                                .ref(TableSourceConverter.class)
                                .data(From.JoinedTable::getTableSource)
                                .and()
                            .sub("join_type")
                                .ref(JoinTypeConverter.class)
                                .data(From.JoinedTable::getJoinType)
                                .and()
                            .sub("table_source")
                                .ref(TableSourceConverter.class)
                                .data(From.JoinedTable::getTableSource2)
                                .and()
                            .sub_keyword(Keywords.ON)
                            .sub("search_condition")
                                .ref(SearchConditionConverter.class)
                                .data(From.JoinedTable::getSearchCondition)
                                .and()
                            .and()
                        .czse(From.JoinedTable::isUseCrossJoin)
                            .description("cross join")
                            .sub("table_source")
                                .ref(TableSourceConverter.class)
                                .data(From.JoinedTable::getTableSource)
                                .and()
                            .sub_keyword(Keywords.CROSS)
                            .sub_keyword(Keywords.JOIN)
                            .sub("table_source")
                                .ref(TableSourceConverter.class)
                                .data(From.JoinedTable::getTableSource2)
                                .and()
                            .and()
                        .czse(d -> d.isUseCrossApply() || d.isUseOuterApply())
                            .description("cross/outer apply")
                            .sub("left_table_source")
                                .data(From.JoinedTable::getTableSource)
                                .and()
                            .sub()
                                .description("CROSS | OUTER")
                                .required()
                                .czse(From.JoinedTable::isUseCrossApply)
                                    .keyword(Keywords.CROSS)
                                    .and()
                                .czse(From.JoinedTable::isUseOuterApply)
                                    .keyword(Keywords.OUTER)
                                    .and()
                                .and()
                            .sub_keyword(Keywords.Key.APPLY)
                            .sub("right_table_source")
                                .data(From.JoinedTable::getTableSource2)
                                .and()
                            .and()
                        .czse(From.JoinedTable::isUseParenthesis)
                            .description("joined_table with ()")
                            .sub()
                                .optional(d -> true)
                                .keyword(Other.GROUP_START)
                                .and()
                            .sub("joined_table")
                                .ref(JoinedTableConverter.class)
                                .data(d -> d)
                                .and()
                            .sub()
                                .optional(d -> true)
                                .keyword(Other.GROUP_END)
                                .and()
                            .and()
                        .subTakeLine();
        // @formatter:on

        public static BlockMeta meta() {
            return builder.build();
        }

        @Override
        public MetaContextBlock convert(From.JoinedTable context) {
            return MetaContextBlockBuilder
                    .meta(meta())
                    .build(context);
        }
    }

    /**
     *
     */
    public static class JoinTypeConverter
            implements MetaContextBlockConverter<From.JoinType> {

        // @formatter:off
        private static BlockMetaBuilder<Void,From.JoinType> builder =
                new BlockMetaBuilder<Void,From.JoinType>()
                        .overall("join_type")
                        .sub()
                            .description("inner/outer with join_hint")
                            .optional(d -> d.getKeywords().equals(From.JoinTypeKeywords.JOIN))
                            .sub()
                                .description("inner/outer")
                                .required()
                                .czse(d -> d.getKeywords().equals(From.JoinTypeKeywords.INNER_JOIN))
                                    .keyword(Keywords.INNER)
                                    .and()
                                .czse(d -> !d.getKeywords().equals(From.JoinTypeKeywords.INNER_JOIN))
                                    .description("left/right/full outer")
                                    .required()
                                    .sub()
                                        .required()
                                        .czse(d -> d.getKeywords().equals(From.JoinTypeKeywords.LEFT_OUTER_JOIN))
                                            .keyword(Keywords.LEFT)
                                            .and()
                                        .czse(d -> d.getKeywords().equals(From.JoinTypeKeywords.RIGHT_OUTER_JOIN))
                                            .keyword(Keywords.RIGHT)
                                            .and()
                                        .czse(d -> d.getKeywords().equals(From.JoinTypeKeywords.FULL_OUTER_JOIN))
                                            .keyword(Keywords.FULL)
                                            .and()
                                        .and()
                                    .sub()
                                        .optional(d ->
                                                !d.getKeywords().equals(From.JoinTypeKeywords.LEFT_OUTER_JOIN) &&
                                                !d.getKeywords().equals(From.JoinTypeKeywords.RIGHT_OUTER_JOIN) &&
                                                !d.getKeywords().equals(From.JoinTypeKeywords.FULL_OUTER_JOIN
                                        ))
                                        .keyword(Keywords.OUTER)
                                        .and()
                                    .and()
                                .and()
                            .sub("join_hint")
                                .optional(d -> d.getJoinHint() == null)
                                .ref(JoinHintConverter.class)
                                .data(From.JoinType::getJoinHint)
                                .and()
                            .and()
                        .sub_keyword(Keywords.JOIN)
                        .subTakeLine();
        // @formatter:on

        public static BlockMeta meta() {
            return builder.build();
        }

        @Override
        public MetaContextBlock convert(From.JoinType context) {
            return MetaContextBlockBuilder
                    .meta(meta())
                    .build(context);
        }
    }

    /**
     *
     */
    public static class SystemTimeConverter
            implements MetaContextBlockConverter<From.SystemTime> {

        // @formatter:off
        private static BlockMetaBuilder<Void,From.SystemTime> builder =
                new BlockMetaBuilder<Void,From.SystemTime>()
                        .overall("system_time")
                        .required()
                        .czse(d -> d.getDateTime() != null)
                            .description("as of")
                            .sub_keyword(Keywords.AS)
                            .sub_keyword(Keywords.OF)
                            .sub("date_time")
                                .ref(DateTimeConverter.class)
                                .data(From.SystemTime::getDateTime)
                                .and()
                            .and()
                        .czse(From.SystemTime::isUseFrom)
                            .description("from to")
                            .sub_keyword(Keywords.FROM)
                            .sub("start_date_time")
                                .ref(DateTimeConverter.class)
                                .data(From.SystemTime::getStartDateTime)
                                .and()
                            .sub_keyword(Keywords.TO)
                            .sub("end_date_time")
                                .ref(DateTimeConverter.class)
                                .data(From.SystemTime::getEndDateTime)
                                .and()
                            .and()
                        .czse(From.SystemTime::isUseBetween)
                            .description("between and")
                            .sub_keyword(Keywords.BETWEEN)
                            .sub("start_date_time")
                                .ref(DateTimeConverter.class)
                                .data(From.SystemTime::getStartDateTime)
                                .and()
                            .sub_keyword(Keywords.AND)
                            .sub("end_date_time")
                                .ref(DateTimeConverter.class)
                                .data(From.SystemTime::getEndDateTime)
                                .and()
                            .and()
                        .czse(From.SystemTime::isUseContained)
                            .description("contained in")
                            .sub_keyword(Keywords.Key.CONTAINED)
                            .sub_keyword(Keywords.IN)
                            .sub_keyword(Other.GROUP_START)
                            .sub("start_date_time")
                                .ref(DateTimeConverter.class)
                                .data(From.SystemTime::getStartDateTime)
                                .and()
                            .sub_keyword(Other.DELIMITER)
                            .sub("end_date_time")
                                .ref(DateTimeConverter.class)
                                .data(From.SystemTime::getEndDateTime)
                                .and()
                            .sub_keyword(Other.GROUP_END)
                            .and()
                        .czse(From.SystemTime::isUseAll)
                            .description("all")
                            .sub_keyword(Keywords.ALL)
                            .and()
                        .subTakeLine()
                        .headFootTakeLine();
        // @formatter:on

        public static BlockMeta meta() {
            return builder.build();
        }

        @Override
        public MetaContextBlock convert(From.SystemTime context) {
            return MetaContextBlockBuilder
                    .meta(meta())
                    .build(context);
        }
    }

    /**
     *
     */
    public static class DateTimeConverter
            implements MetaContextBlockConverter<From.DateTime> {

        // @formatter:off
        private static BlockMetaBuilder<Void,From.DateTime> builder =
                new BlockMetaBuilder<Void,From.DateTime>()
                        .overall("date_time")
                        .czse(d -> d.getDateTimeLiteral() != null,"<date_time_literal>")
                            .data(From.DateTime::getDateTimeLiteral)
                            .and()
                        .czse(d -> d.getDateTimeVariable() != null,"@date_time_variable")
                            .data(From.DateTime::getDateTimeVariable)
                            .and();
        // @formatter:on

        public static BlockMeta meta() {
            return builder.build();
        }

        @Override
        public MetaContextBlock convert(From.DateTime context) {
            return MetaContextBlockBuilder
                    .meta(meta())
                    .build(context);
        }
    }

}
