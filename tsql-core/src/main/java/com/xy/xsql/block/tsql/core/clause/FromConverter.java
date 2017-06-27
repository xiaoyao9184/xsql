package com.xy.xsql.block.tsql.core.clause;

import com.xy.xsql.block.core.ReferenceBlockConverter;
import com.xy.xsql.block.core.ReferenceBlockBuilder;
import com.xy.xsql.block.model.ReferenceBlock;
import com.xy.xsql.block.tsql.core.clause.hints.JoinHintConverter;
import com.xy.xsql.block.tsql.core.clause.select.SelectConverter;
import com.xy.xsql.tsql.model.Keywords;
import com.xy.xsql.tsql.model.clause.From;
import com.xy.xsql.tsql.model.element.Other;
import com.xy.xsql.tsql.model.statement.dml.Select;

/**
 * Created by xiaoyao9184 on 2017/6/20.
 */
public class FromConverter
        implements ReferenceBlockConverter<From> {

    // @formatter:off
    private static ReferenceBlockBuilder<Void,From> builder =
            new ReferenceBlockBuilder<Void,From>()
                    .overall("FROM")
                    .sub_keyword(Keywords.FROM)
                    .sub()
                        .description("{ <table_source> } [ ,...n ]")
                        .required()
                        .list("table_source")
                        .ref(TableSourceConverter.class)
                        .data(From::getTableSourceList)
                        .and();
    // @formatter:on

    public static ReferenceBlock meta() {
        return builder.build();
    }

    @Override
    public ReferenceBlock convert(From from) {
        return builder
                .data(from)
                .build();
    }


    public static class TableSourceConverter
            implements ReferenceBlockConverter<From.TableSource> {

        // @formatter:off
        private static ReferenceBlockBuilder<Void,From.TableSource> builder =
                new ReferenceBlockBuilder<Void,From.TableSource>()
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

        public static ReferenceBlock meta() {
            return builder.build();
        }

        @Override
        public ReferenceBlock convert(From.TableSource tableSource) {
            return builder
                    .data(tableSource)
                    .build();
        }
    }

    public static class BaseTableConverter
            implements ReferenceBlockConverter<From.BaseTable> {

        // @formatter:off
        private static ReferenceBlockBuilder<Void,From.BaseTable> builder =
                new ReferenceBlockBuilder<Void,From.BaseTable>()
                        .description("base table")
                        .sub("table_or_view_name")
                            .data(From.BaseTable::getTableName)
                            .and()
                        .sub()
                            .description("[ [ AS ] table_alias ]")
                            .optional(bt -> bt.getTableAlias() == null)
                            .sub("AS")
                                .keyword(Keywords.AS)
                                .optional(t -> true)
                                .and()
                            .sub("table_alias")
                                .data(From.BaseTable::getTableAlias)
                                .and()
                            .and();
        // @formatter:on

        public static ReferenceBlock meta() {
            return builder.build();
        }

        @Override
        public ReferenceBlock convert(From.BaseTable baseTable) {
            return builder
                    .data(baseTable)
                    .build();
        }
    }

    public static class DerivedTableConverter
            implements ReferenceBlockConverter<From.DerivedTable> {

        // @formatter:off
        private static ReferenceBlockBuilder<Void,From.DerivedTable> builder =
                new ReferenceBlockBuilder<Void,From.DerivedTable>()
                        .description("derived table")
                        .sub("derived_table")
//                            .ref(com.xy.xsql.block.tsql.core.statement.dml.SelectConverter.QuerySpecificationConverter.class)
                            .data(From.DerivedTable::getSubQuery)
                            .and()
                        .sub()
                            .description("[ [ AS ] table_alias ]")
                            .optional(bt -> bt.getTableAlias() == null)
                            .sub()
                                .optional(From.DerivedTable::isUseAs)
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

        public static ReferenceBlock meta() {
            return builder.build();
        }

        @Override
        public ReferenceBlock convert(From.DerivedTable derivedTable) {
            return builder
                    .data(derivedTable)
                    .build();
        }
    }

    public static class JoinedTableConverter
            implements ReferenceBlockConverter<From.JoinedTable> {

        // @formatter:off
        private static ReferenceBlockBuilder<Void,From.JoinedTable> builder =
                new ReferenceBlockBuilder<Void,From.JoinedTable>()
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

        public static ReferenceBlock meta() {
            return builder.build();
        }

        @Override
        public ReferenceBlock convert(From.JoinedTable joinedTable) {
            return builder
                    .data(joinedTable)
                    .build();
        }
    }

    public static class VariableTableConverter
            implements ReferenceBlockConverter<From.VariableTable> {

        // @formatter:off
        private static ReferenceBlockBuilder<Void,From.VariableTable> builder =
                new ReferenceBlockBuilder<Void,From.VariableTable>()
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

        public static ReferenceBlock meta() {
            return builder.build();
        }

        @Override
        public ReferenceBlock convert(From.VariableTable variableTable) {
            return builder
                    .data(variableTable)
                    .build();
        }
    }


    public static class JoinTypeConverter
            implements ReferenceBlockConverter<From.JoinType> {

        // @formatter:off
        private static ReferenceBlockBuilder<Void,From.JoinType> builder =
                new ReferenceBlockBuilder<Void,From.JoinType>()
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

        public static ReferenceBlock meta() {
            return builder.build();
        }

        @Override
        public ReferenceBlock convert(From.JoinType joinType) {
            return builder
                    .data(joinType)
                    .build();
        }
    }

}
