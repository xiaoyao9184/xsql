package com.xy.xsql.block.tsql.core.clause;

import com.xy.xsql.block.core.BlockConverter;
import com.xy.xsql.block.core.ReferenceBlockBuilder;
import com.xy.xsql.block.model.ReferenceBlock;
import com.xy.xsql.tsql.model.Block;
import com.xy.xsql.tsql.model.Keywords;
import com.xy.xsql.tsql.model.clause.From;
import com.xy.xsql.tsql.model.clause.SearchCondition;
import com.xy.xsql.tsql.model.clause.With;
import com.xy.xsql.tsql.model.clause.hints.JoinHint;
import com.xy.xsql.tsql.model.element.Other;

/**
 * Created by xiaoyao9184 on 2017/6/20.
 */
public class FromConverter
        implements BlockConverter<From> {

    // @formatter:off
    private static ReferenceBlockBuilder<Void,From> builder =
            new ReferenceBlockBuilder<Void,From>()
                    .overall("FROM")
                    .sub_keyword(Keywords.FROM)
                    .sub()
                        .description("{ <table_source> } [ ,...n ]")
                        .list()
                        .sub("table_source")
                            .ref(From.TableSource.class)
                            .and()
                        .data(From::getTableSourceList)
                        .oneMore()
                        .and();
    // @formatter:on

    public static ReferenceBlock meta() {
        return builder.build();
    }

    @Override
    public Block convert(From from) {
        return builder
                .data(from)
                .build();
    }


    public static class TableSourceConverter
            implements BlockConverter<From.TableSource> {

        // @formatter:off
        private static ReferenceBlockBuilder<Void,From.TableSource> builder =
                new ReferenceBlockBuilder<Void,From.TableSource>()
                        .overall("table_source")
                        .oneOf(BaseTableConverter.meta())
                        .oneOf(DerivedTableConverter.meta())
                        .oneOf("joined_table")
                            .ref(From.JoinedTable.class)
                            .data(d -> d)
                            .and()
                        .oneOf(VariableTableConverter.meta())
                        .subTakeLine();
        // @formatter:on

        public static ReferenceBlock meta() {
            return builder.build();
        }

        @Override
        public Block convert(From.TableSource tableSource) {
            return builder
                    .data(tableSource)
                    .build();
        }
    }

    public static class BaseTableConverter
            implements BlockConverter<From.BaseTable> {

        // @formatter:off
        private static ReferenceBlockBuilder<Void,From.BaseTable> builder =
                new ReferenceBlockBuilder<Void,From.BaseTable>()
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
        public Block convert(From.BaseTable baseTable) {
            return builder
                    .data(baseTable)
                    .build();
        }
    }

    public static class DerivedTableConverter
            implements BlockConverter<From.DerivedTable> {

        // @formatter:off
        private static ReferenceBlockBuilder<Void,From.DerivedTable> builder =
                new ReferenceBlockBuilder<Void,From.DerivedTable>()
                    .sub("derived_table")
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
                        .sub()
                            .list("column_alias")
                            .data(From.DerivedTable::getColumnAliass)
                            .more()
                            .and()
                        .sub_keyword(Other.GROUP_END)
                        .and();
        // @formatter:on

        public static ReferenceBlock meta() {
            return builder.build();
        }

        @Override
        public Block convert(From.DerivedTable derivedTable) {
            return builder
                    .data(derivedTable)
                    .build();
        }
    }

    public static class JoinedTableConverter
            implements BlockConverter<From.JoinedTable> {

        // @formatter:off
        private static ReferenceBlockBuilder<Void,From.JoinedTable> builder =
                new ReferenceBlockBuilder<Void,From.JoinedTable>()
                        .overall("joined_table")
                        .oneOf()
                            .description("-join_type")
                            .sub("table_source")
                                .ref(From.TableSource.class)
                                .data(From.JoinedTable::getTableSource)
                                .and()
                            .sub("join_type")
                                .ref(From.JoinType.class)
                                .data(From.JoinedTable::getJoinType)
                                .and()
                            .sub("table_source")
                                .ref(From.TableSource.class)
                                .data(From.JoinedTable::getTableSource2)
                                .and()
                            .sub_keyword(Keywords.ON)
                            .sub("search_condition")
                                .ref(SearchCondition.class)
                                .data(From.JoinedTable::getSearchCondition)
                                .and()
                            .and()
                        .oneOf()
                            .description("-CROSS JOIN")
                            .sub("table_source")
                                .ref(From.TableSource.class)
                                .data(From.JoinedTable::getTableSource)
                                .and()
                            .sub_keyword(Keywords.CROSS)
                            .sub_keyword(Keywords.JOIN)
                            .sub("table_source")
                                .ref(From.TableSource.class)
                                .data(From.JoinedTable::getTableSource2)
                                .and()
                            .and()
                        .oneOf()
                            .description("-{CROSS | OUTER} APPLY")
                            .sub("left_table_source")
                                .data(From.JoinedTable::getTableSource)
                                .and()
                            .sub()
                                .description("CROSS | OUTER")
                                .required()
                                .oneOf()
                                    .keyword(Keywords.CROSS)
                                    .and()
                                .oneOf()
                                    .keyword(Keywords.OUTER)
                                    .and()
                                .and()
                            .sub_keyword(Keywords.Key.APPLY)
                            .sub("right_table_source")
                                .data(From.JoinedTable::getTableSource2)
                                .and()
                            .and()
                        .oneOf()
                            .description("-<joined_table>")
                            .sub()
                                .optional(d -> true)
                                .keyword(Other.GROUP_START)
                                .and()
                            .sub("joined_table")
                                .ref(From.JoinedTable.class)
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
        public Block convert(From.JoinedTable joinedTable) {
            return builder
                    .data(joinedTable)
                    .build();
        }
    }

    public static class VariableTableConverter
            implements BlockConverter<From.VariableTable> {

        // @formatter:off
        private static ReferenceBlockBuilder<Void,From.VariableTable> builder =
                new ReferenceBlockBuilder<Void,From.VariableTable>()
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
        public Block convert(From.VariableTable variableTable) {
            return builder
                    .data(variableTable)
                    .build();
        }
    }


    public static class JoinTypeConverter
            implements BlockConverter<From.JoinType> {

        // @formatter:off
        private static ReferenceBlockBuilder<Void,From.JoinType> builder =
                new ReferenceBlockBuilder<Void,From.JoinType>()
                        .sub()
                            .optional(d -> d.equals(From.JoinType.JOIN))
                            .sub()
                                .required()
                                .oneOf()
                                    .filter(d -> !d.equals(From.JoinType.INNER_JOIN))
                                    .keyword(Keywords.INNER)
                                    .and()
                                .oneOf()
                                    .filter(d -> d.equals(From.JoinType.INNER_JOIN))
                                    .required()
                                    .sub()
                                        .required()
                                        .oneOf()
                                            .filter(d -> !d.equals(From.JoinType.LEFT_OUTER_JOIN))
                                            .keyword(Keywords.LEFT)
                                            .and()
                                        .oneOf()
                                            .filter(d -> !d.equals(From.JoinType.RIGHT_OUTER_JOIN))
                                            .keyword(Keywords.RIGHT)
                                            .and()
                                        .oneOf()
                                            .filter(d -> !d.equals(From.JoinType.FULL_OUTER_JOIN))
                                            .keyword(Keywords.FULL)
                                            .and()
                                        .and()
                                    .sub()
                                        .optional(d ->
                                                !d.equals(From.JoinType.LEFT_OUTER_JOIN) &&
                                                !d.equals(From.JoinType.RIGHT_OUTER_JOIN) &&
                                                !d.equals(From.JoinType.FULL_OUTER_JOIN
                                        ))
                                        .keyword(Keywords.OUTER)
                                        .and()
                                    .and()
                                .and()
                            .sub("join_hint")
                                .optional()
                                .ref(JoinHint.class)
                                .and()
                            .and()
                        .sub_keyword(Keywords.JOIN)
                        .subTakeLine();
        // @formatter:on

        public static ReferenceBlock meta() {
            return builder.build();
        }

        @Override
        public Block convert(From.JoinType joinType) {
            return builder
                    .data(joinType)
                    .build();
        }
    }

}
