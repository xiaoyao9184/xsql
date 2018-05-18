package com.xy.xsql.block.tsql.core.clause;

import com.xy.xsql.block.meta.BlockMetaBuilder;
import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.block.tsql.core.clause.hints.JoinHintConverter;
import com.xy.xsql.block.tsql.core.clause.hints.TableHintConverter;
import com.xy.xsql.tsql.model.elements.Keywords;
import com.xy.xsql.tsql.model.queries.From;
import com.xy.xsql.tsql.model.elements.Other;

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
                        .list()
                        .ref(TableSourceConverter.class)
                        .scope(From::getTableSourceList)
                        .syntax_required()
                        .format_indentation_right()
                        .sub_format_line()
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
                            .scope(d -> d)
                            .and()
                        .czse_ref(d -> d instanceof From.VariableTable,VariableTableConverter.meta)
                        .czse_ref(d -> d instanceof From.BaseWithTimeTable,BaseWithTimeTableConverter.meta)
                        .syntax_sub_line()
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
                            .scope(From.BaseTable::getTableName)
                            .and()
                        .sub()
                            .description("as table_alias")
                            .optional(bt -> bt.getTableAlias() == null)
                            .sub()
                                .optional(d -> !d.isUseAs())
                                .keyword(Keywords.AS)
                                .and()
                            .sub("table_alias")
                                .scope(From.BaseTable::getTableAlias)
                                .and()
                            .and()
                        .sub("tablesample_clause")
                            .optional(d -> d.getTableSample() == null)
                            .ref(TableSampleConverter.class)
                            .scope(From.BaseTable::getTableSample)
                            .syntax_indentation_right()
                            .format_indentation_right()
                            .and()
                        .sub()
                            .description("with table_hint")
                            .optional(d -> d.getTableHintList() == null)
                            .sub_keyword(Keywords.WITH)
                            .sub_keyword(Other.GROUP_START)
                            .sub("table_hint")
                                .list()
                                .repeat()
                                .ref(TableHintConverter.class)
                                .scope(From.BaseTable::getTableHintList)
                                .and()
                            .sub_keyword(Other.GROUP_END)
                            .syntax_indentation_right()
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
                                    .scope(From.DerivedTable::getSubQuery)
                                    .and()
                                .czse(d -> d.getValues() != null)
                                    .description("values")
                                    .scope(From.DerivedTable::getValues)
                                    .and()
                                .and()
                            .format_indentation_right()
                            .sub_keyword(Other.GROUP_END)
                            .and()
                        .sub()
                            .description("as table_alias")
                            .optional(bt -> bt.getTableAlias() == null)
                            .sub()
                                .optional(d -> !d.isUseAs())
                                .keyword(Keywords.AS)
                                .and()
                            .sub("table_alias")
                                .scope(From.DerivedTable::getTableAlias)
                                .and()
                            .and()
                        .sub()
                            .description("derived table's column_alias list")
                            .optional(bt -> bt.getColumnAliass() == null)
                            .sub_keyword(Other.GROUP_START)
                            .sub_list("column_alias")
                                .scope(From.DerivedTable::getColumnAliass)
                                .and()
                            .sub_keyword(Other.GROUP_END)
                            .and()
                        .sub_format_line(true)
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
                            .scope(From.VariableTable::getVariable)
                            .and()
                        .sub()
                            .description("as table_alias")
                            .optional(bt -> bt.getTableAlias() == null)
                            .sub()
                                .optional(d -> !d.isUseAs())
                                .keyword(Keywords.AS)
                                .and()
                            .sub("table_alias")
                                .scope(From.VariableTable::getTableAlias)
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
                            .scope(From.BaseWithTimeTable::getTableName)
                            .and()
                        .sub_keyword(Keywords.FOR)
                        .sub_keyword(Keywords.Key.SYSTEM_TIME)
                        .sub("system_time")
                            .ref(SystemTimeConverter.class)
                            .scope(From.BaseWithTimeTable::getSystemTime)
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
                            .scope(From.TableSample::getSampleNumber)
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
                                .scope(From.TableSample::getRepeatSeed)
                                .and()
                            .sub_keyword(Other.GROUP_END)
                            .syntax_indentation_right()
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
                                .scope(From.JoinedTable::getTableSource)
                                .and()
                            .sub("join_type")
                                .ref(JoinTypeConverter.class)
                                .scope(From.JoinedTable::getJoinType)
                                .format_empty_delimiter()
                                .format_indentation_left()
                                .and()
                            .sub("table_source")
                                .ref(TableSourceConverter.class)
                                .scope(From.JoinedTable::getTableSource2)
                                .and()
                            .sub()
                                .keyword(Keywords.ON)
//                                .format_empty_delimiter()
//                                .format_indentation_left()
                                .and()
                            .sub("search_condition")
                                .ref(SearchConditionConverter.class)
                                .scope(From.JoinedTable::getSearchCondition)
                                .format_indentation_right()
                                .and()
                            .sub_format_line_empty_delimiter()
                            .and()
                        .czse(From.JoinedTable::isUseCrossJoin)
                            .description("cross join")
                            .sub("table_source")
                                .ref(TableSourceConverter.class)
                                .scope(From.JoinedTable::getTableSource)
                                .and()
                            .sub()
                                .sub_keyword(Keywords.CROSS)
                                .sub_keyword(Keywords.JOIN)
                                .format_empty_delimiter()
                                .format_indentation_left()
                                .and()
                            .sub("table_source")
                                .ref(TableSourceConverter.class)
                                .scope(From.JoinedTable::getTableSource2)
                                .and()
                            .sub_format_line_empty_delimiter()
                            .and()
                        .czse(d -> d.isUseCrossApply() || d.isUseOuterApply())
                            .description("cross/outer apply")
                            .sub("left_table_source")
                                .scope(From.JoinedTable::getTableSource)
                                .and()
                            .sub()
                                .description("cross/outer apply")
                                .sub()
                                    .description("cross/outer")
                                    .czse_keyword(From.JoinedTable::isUseCrossApply, Keywords.CROSS)
                                    .czse_keyword(From.JoinedTable::isUseOuterApply, Keywords.OUTER)
                                    .syntax_required()
                                    .format_line()
                                    .and()
                                .sub_keyword(Keywords.Key.APPLY)
                                .format_empty_delimiter()
                                .format_indentation_left()
                                .and()
                            .sub("right_table_source")
                                .scope(From.JoinedTable::getTableSource2)
                                .and()
                            .sub_format_line_empty_delimiter()
                            .and()
                        .czse(From.JoinedTable::isUseParenthesis)
                            .description("joined_table with ()")
                            .sub()
                                .optional(d -> true)
                                .keyword(Other.GROUP_START)
                                .and()
                            .sub("joined_table")
                                .ref(JoinedTableConverter.class)
                                .scope(d -> d)
                                .format_indentation_right()
                                .and()
                            .sub()
                                .optional(d -> true)
                                .keyword(Other.GROUP_END)
                                .and()
                            .sub_format_line_empty_delimiter()
                            .and()
                        .syntax_sub_line()
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
                                    .syntax_required()
                                    .sub()
                                        .syntax_required()
                                        .czse_keyword(d -> d.getKeywords().equals(From.JoinTypeKeywords.LEFT_OUTER_JOIN), Keywords.LEFT)
                                        .czse_keyword(d -> d.getKeywords().equals(From.JoinTypeKeywords.RIGHT_OUTER_JOIN), Keywords.RIGHT)
                                        .czse_keyword(d -> d.getKeywords().equals(From.JoinTypeKeywords.FULL_OUTER_JOIN), Keywords.FULL)
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
                                .syntax_required()
                                .and()
                            .sub("join_hint")
                                .optional(d -> d.getJoinHint() == null)
                                .ref(JoinHintConverter.class)
                                .scope(From.JoinType::getJoinHint)
                                .and()
                            .and()
                        .sub_keyword(Keywords.JOIN)
                        .syntax_sub_line()
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
                                .scope(From.SystemTime::getDateTime)
                                .and()
                            .and()
                        .czse(From.SystemTime::isUseFrom)
                            .description("from to")
                            .sub_keyword(Keywords.FROM)
                            .sub("start_date_time")
                                .ref(DateTimeConverter.class)
                                .scope(From.SystemTime::getStartDateTime)
                                .and()
                            .sub_keyword(Keywords.TO)
                            .sub("end_date_time")
                                .ref(DateTimeConverter.class)
                                .scope(From.SystemTime::getEndDateTime)
                                .and()
                            .and()
                        .czse(From.SystemTime::isUseBetween)
                            .description("between and")
                            .sub_keyword(Keywords.BETWEEN)
                            .sub("start_date_time")
                                .ref(DateTimeConverter.class)
                                .scope(From.SystemTime::getStartDateTime)
                                .and()
                            .sub_keyword(Keywords.AND)
                            .sub("end_date_time")
                                .ref(DateTimeConverter.class)
                                .scope(From.SystemTime::getEndDateTime)
                                .and()
                            .and()
                        .czse(From.SystemTime::isUseContained)
                            .description("contained in")
                            .sub_keyword(Keywords.Key.CONTAINED)
                            .sub_keyword(Keywords.IN)
                            .sub_keyword(Other.GROUP_START)
                            .sub("start_date_time")
                                .ref(DateTimeConverter.class)
                                .scope(From.SystemTime::getStartDateTime)
                                .and()
                            .sub_keyword(Other.DELIMITER)
                            .sub("end_date_time")
                                .ref(DateTimeConverter.class)
                                .scope(From.SystemTime::getEndDateTime)
                                .and()
                            .sub_keyword(Other.GROUP_END)
                            .and()
                        .czse(From.SystemTime::isUseAll)
                            .description("all")
                            .sub_keyword(Keywords.ALL)
                            .and()
                        .syntax_required()
                        .syntax_context_indentation()
                        .syntax_sub_line()

//                        .syntax_required2()
//                        .syntax_sub_indentation_right()
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
                            .scope(From.DateTime::getDateTimeLiteral)
                            .and()
                        .czse(d -> d.getDateTimeVariable() != null,"@date_time_variable")
                            .scope(From.DateTime::getDateTimeVariable)
                            .and()
                        .build();
        // @formatter:on

        @Override
        public BlockMeta meta() {
            return meta;
        }

    }

}
