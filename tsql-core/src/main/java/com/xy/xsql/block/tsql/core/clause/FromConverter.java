package com.xy.xsql.block.tsql.core.clause;

import com.xy.xsql.block.core.meta.BlockMetaBuilder;
import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.block.tsql.core.clause.hints.JoinHintConverter;
import com.xy.xsql.block.tsql.core.clause.hints.TableHintConverter;
import com.xy.xsql.tsql.model.Keywords;
import com.xy.xsql.tsql.model.clause.From;
import com.xy.xsql.tsql.model.element.Other;

/**
 * Created by xiaoyao9184 on 2017/6/20.
 */
public class FromConverter
        implements ModelMetaBlockConverter<From> {

    // @formatter:off
    public static BlockMeta meta =
            new BlockMetaBuilder<Void,From>()
                    .overall("FROM")
                    .sub_keyword(Keywords.FROM)
                    .sub("table_source")
                        .description("{ <table_source> } [ ,...n ]")
                        .list()
                        .ref(TableSourceConverter.class)
                        .data(From::getTableSourceList)
                        .style_required()
                        .format_indentation_right()
                        .and()
                    .build();
    // @formatter:on

    @Override
    public BlockMeta meta() {
        return meta;
    }


    public static class TableSourceConverter
            implements ModelMetaBlockConverter<From.TableSource> {

        // @formatter:off
        public static BlockMeta meta =
                new BlockMetaBuilder<Void,From.TableSource>()
                        .overall("table_source")
                        .czse_ref(d -> d instanceof From.BaseTable,BaseTableConverter.meta)
                        .czse_ref(d -> d instanceof From.DerivedTable,DerivedTableConverter.meta)
                        .czse(d -> d instanceof From.JoinedTable,"joined_table")
                            .ref(JoinedTableConverter.class)
                            .data(d -> d)
                            .and()
                        .czse_ref(d -> d instanceof From.VariableTable,VariableTableConverter.meta)
                        .czse_ref(d -> d instanceof From.BaseWithTimeTable,BaseWithTimeTableConverter.meta)
                        .style_sub_line_delimiter()
                        .build();
        // @formatter:on

        @Override
        public BlockMeta meta() {
            return meta;
        }

    }

    public static class BaseTableConverter
            implements ModelMetaBlockConverter<From.BaseTable> {

        // @formatter:off
        public static BlockMeta meta =
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
                            .format_line()
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
                            .style_start_new_line()
                            .format_indentation_right()
                            .and()
                        .build();
        // @formatter:on

        @Override
        public BlockMeta meta() {
            return meta;
        }

    }

    public static class DerivedTableConverter
            implements ModelMetaBlockConverter<From.DerivedTable> {

        // @formatter:off
        public static BlockMeta meta =
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
                            .format_line()
                            .and()
                        .build();
        // @formatter:on

        @Override
        public BlockMeta meta() {
            return meta;
        }

    }

    public static class VariableTableConverter
            implements ModelMetaBlockConverter<From.VariableTable> {

        // @formatter:off
        public static BlockMeta meta =
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
                            .and()
                        .build();
        // @formatter:on

        @Override
        public BlockMeta meta() {
            return meta;
        }

    }

    public static class BaseWithTimeTableConverter
            implements ModelMetaBlockConverter<From.BaseWithTimeTable> {

        // @formatter:off
        public static BlockMeta meta =
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
                            .and()
                        .build();
        // @formatter:on

        @Override
        public BlockMeta meta() {
            return meta;
        }

    }

    /**
     *
     */
    public static class TableSampleConverter
            implements ModelMetaBlockConverter<From.TableSample> {

        // @formatter:off
        public static BlockMeta meta =
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
                            .style_start_new_line()
                            .and()
                        .build();
        // @formatter:on

        @Override
        public BlockMeta meta() {
            return meta;
        }

    }

    /**
     *
     */
    public static class JoinedTableConverter
            implements ModelMetaBlockConverter<From.JoinedTable> {

        // @formatter:off
        public static BlockMeta meta =
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
                                .format_line()
                                .and()
                            .sub("table_source")
                                .ref(TableSourceConverter.class)
                                .data(From.JoinedTable::getTableSource2)
                                .format_line()
                                .and()
                            .sub()
                                .keyword(Keywords.ON)
                                .format_line()
                                .and()
//                            .sub_keyword(Keywords.ON)
                            .sub("search_condition")
                                .ref(SearchConditionConverter.class)
                                .data(From.JoinedTable::getSearchCondition)
                                .format_indentation_right()
                                .and()
                            .and()
                        .czse(From.JoinedTable::isUseCrossJoin)
                            .description("cross join")
                            .sub("table_source")
                                .ref(TableSourceConverter.class)
                                .data(From.JoinedTable::getTableSource)
                                .and()
                            .sub()
                                .keyword(Keywords.CROSS)
                                .format_line()
                                .and()
//                            .sub_keyword(Keywords.CROSS)
                            .sub_keyword(Keywords.JOIN)
                            .sub("table_source")
                                .ref(TableSourceConverter.class)
                                .data(From.JoinedTable::getTableSource2)
                                .format_line()
                                .and()
                            .and()
                        .czse(d -> d.isUseCrossApply() || d.isUseOuterApply())
                            .description("cross/outer apply")
                            .sub("left_table_source")
                                .data(From.JoinedTable::getTableSource)
                                .and()
                            .sub()
                                .description("CROSS | OUTER")
                                .czse(From.JoinedTable::isUseCrossApply)
                                    .keyword(Keywords.CROSS)
                                    .and()
                                .czse(From.JoinedTable::isUseOuterApply)
                                    .keyword(Keywords.OUTER)
                                    .and()
                                .style_required()
                                .format_line()
                                .and()
                            .sub_keyword(Keywords.Key.APPLY)
                            .sub("right_table_source")
                                .data(From.JoinedTable::getTableSource2)
                                .format_line()
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
                                .format_indentation_right()
                                .and()
                            .sub()
                                .optional(d -> true)
                                .keyword(Other.GROUP_END)
                                .and()
                            .and()
                        .style_sub_line_delimiter()
                        .build();
        // @formatter:on

        @Override
        public BlockMeta meta() {
            return meta;
        }

    }

    /**
     *
     */
    public static class JoinTypeConverter
            implements ModelMetaBlockConverter<From.JoinType> {

        // @formatter:off
        public static BlockMeta meta =
                new BlockMetaBuilder<Void,From.JoinType>()
                        .overall("join_type")
                        .sub()
                            .description("inner/outer with join_hint")
                            .optional(d -> d.getKeywords().equals(From.JoinTypeKeywords.JOIN))
                            .sub()
                                .description("inner/outer")
                                .czse(d -> d.getKeywords().equals(From.JoinTypeKeywords.INNER_JOIN))
                                    .keyword(Keywords.INNER)
                                    .and()
                                .czse(d -> !d.getKeywords().equals(From.JoinTypeKeywords.INNER_JOIN))
                                    .description("left/right/full outer")
                                    .style_required()
                                    .sub()
                                        .style_required()
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
                                .style_required()
                                .and()
                            .sub("join_hint")
                                .optional(d -> d.getJoinHint() == null)
                                .ref(JoinHintConverter.class)
                                .data(From.JoinType::getJoinHint)
                                .and()
                            .and()
                        .sub_keyword(Keywords.JOIN)
                        .style_sub_line_delimiter()
                        .build();
        // @formatter:on

        @Override
        public BlockMeta meta() {
            return meta;
        }

    }

    /**
     *
     */
    public static class SystemTimeConverter
            implements ModelMetaBlockConverter<From.SystemTime> {

        // @formatter:off
        public static BlockMeta meta =
                new BlockMetaBuilder<Void,From.SystemTime>()
                        .overall("system_time")
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
                        .style_required()
                        .style_convention_line_delimiter()
                        .style_sub_line_delimiter()
                        .build();
        // @formatter:on

        @Override
        public BlockMeta meta() {
            return meta;
        }

    }

    /**
     *
     */
    public static class DateTimeConverter
            implements ModelMetaBlockConverter<From.DateTime> {

        // @formatter:off
        public static BlockMeta meta =
                new BlockMetaBuilder<Void,From.DateTime>()
                        .overall("date_time")
                        .czse(d -> d.getDateTimeLiteral() != null,"<date_time_literal>")
                            .data(From.DateTime::getDateTimeLiteral)
                            .and()
                        .czse(d -> d.getDateTimeVariable() != null,"@date_time_variable")
                            .data(From.DateTime::getDateTimeVariable)
                            .and()
                        .build();
        // @formatter:on

        @Override
        public BlockMeta meta() {
            return meta;
        }

    }

}
