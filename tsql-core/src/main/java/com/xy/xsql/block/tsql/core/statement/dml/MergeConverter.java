package com.xy.xsql.block.tsql.core.statement.dml;

import com.xy.xsql.block.core.ReferenceBlockBuilder;
import com.xy.xsql.block.core.ReferenceBlockConverter;
import com.xy.xsql.block.model.ReferenceBlock;
import com.xy.xsql.block.tsql.core.clause.FromConverter;
import com.xy.xsql.block.tsql.core.clause.OutputConverter;
import com.xy.xsql.block.tsql.core.clause.TableValueConstructorConverter;
import com.xy.xsql.block.tsql.core.clause.hints.TableHintLimitedConverter;
import com.xy.xsql.tsql.model.Keywords;
import com.xy.xsql.tsql.model.clause.From;
import com.xy.xsql.tsql.model.element.ColumnName;
import com.xy.xsql.tsql.model.element.Other;
import com.xy.xsql.tsql.model.statement.dml.Merge;
import com.xy.xsql.tsql.model.statement.dml.Merge;

import java.util.List;

/**
 * Created by xiaoyao9184 on 2017/6/17.
 */
public class MergeConverter
        implements ReferenceBlockConverter<Merge> {

    // @formatter:off
    private static ReferenceBlockBuilder<Void,Merge> builder =
            new ReferenceBlockBuilder<Void,Merge>()
                    .overall("MERGE")
                    .sub("WITH <common_table_expression> [ ,...n ]")
                        .optional(d -> d.getWith() == null)
                        .data(Merge::getWith)
                        .and()
                    .sub_keyword(Keywords.MERGE)
                    .sub("TOP ( expression ) [ PERCENT ]")
                        .optional(d -> d.getTop() == null)
                        .data(Merge::getTop)
                        .and()
                    .sub()
                        .optional(d -> !d.isUseInto())
                        .keyword(Keywords.INTO)
                        .and()
                    .sub("<target_table>")
                        .data(Merge::getTargetTable)
                        .and()
                    .sub("with")
                        .optional(d -> d.getMergeHint() == null)
                        .sub_keyword(Keywords.WITH)
                        .sub_keyword(Other.GROUP_START)
                        .sub("merge_hint")
                            .ref(MergeHintConverter.class)
                            .data(Merge::getMergeHint)
                            .and()
                        .sub_keyword(Other.GROUP_END)
                        .and()
                    .sub("as table_alias")
                        .optional(d -> d.getTableAlias() == null)
                        .sub()
                            .optional(d -> !d.isUseAs())
                            .keyword(Keywords.AS)
                            .and()
                        .sub("table_alias")
                            .data(Merge::getTableAlias)
                            .and()
                        .and()
                    .sub_keyword(Keywords.Key.USING)
                    .sub("table_source")
                        .ref(FromConverter.TableSourceConverter.class)
                        .data(Merge::getTableSource)
                        .and()
                    .sub_keyword(Keywords.ON)
                    .sub("<merge_search_condition>")
                        .data(Merge::getMergeSearchCondition)
                        .and()
                    .sub()
                        .description("when matched list")
                        .optional(d -> d.getMatchedWhenThenList() == null)
                        .repeat()
                        .ref(MatchedWhenThenConverter.meta())
                        .data(Merge::getMatchedWhenThenList)
                        .and()
                    .sub()
                        .description("when not matched target")
                        .optional(d -> d.getNotMatchedWhenThenTarget() == null)
                        .ref(NotMatchedTargetWhenThenConverter.meta())
                        .data(Merge::getNotMatchedWhenThenTarget)
                        .and()
                    .sub()
                        .description("when not matched source list")
                        .optional(d -> d.getNotMatchedWhenThenSourceList() == null)
                        .repeat()
                        .ref(NotMatchedSourceWhenThenConverter.meta())
                        .data(Merge::getNotMatchedWhenThenSourceList)
                        .and()
                    .sub("output_clause")
                        .optional(d -> d.getOutput() == null)
                        .ref(OutputConverter.class)
                        .data(Merge::getOutput)
                        .and()
                    .sub("OPTION ( <query_hint> [ ,...n ] )")
                        .optional(d -> d.getOption() == null)
                        .data(Merge::getOption)
                        .and()
                    .subTakeLine();
    // @formatter:on

    public static ReferenceBlock meta() {
        return builder.build();
    }

    @Override
    public ReferenceBlock convert(Merge merge) {
        return builder
                .data(merge)
                .build();
    }

    public static class MergeHintConverter
            implements ReferenceBlockConverter<Merge.MergeHint> {

        // @formatter:off
        private static ReferenceBlockBuilder<Void,Merge.MergeHint> builder =
                new ReferenceBlockBuilder<Void,Merge.MergeHint>()
                        .overall("merge_hint")
                        .required()
                        .sub("table_hint_limited")
                            .optional(d -> d.getTableHintLimitedList() == null)
                            .list()
                            .ref(TableHintLimitedConverter.class)
                            .data(Merge.MergeHint::getTableHintLimitedList)
                            .and()
                        .sub()
                            .description("index_val list")
                            .optional(d -> d.getIndexValues() == null)
                            .sub()
                                .optional(d -> d.getTableHintLimitedList() == null)
                                .keyword(Other.DELIMITER)
                                .and()
                            .sub_keyword(Keywords.INDEX)
                            .sub_keyword(Other.GROUP_START)
                            .sub("index_val")
                                .list()
                                .data(Merge.MergeHint::getIndexValues)
                                .and()
                            .sub_keyword(Other.GROUP_END)
                            .and()
                        .subTakeLine()
                        .headFootTakeLine();
        // @formatter:on

        public static ReferenceBlock meta() {
            return builder.build();
        }

        @Override
        public ReferenceBlock convert(Merge.MergeHint mergeHint) {
            return builder
                    .data(mergeHint)
                    .build();
        }
    }

    public static class MatchedWhenThenConverter
            implements ReferenceBlockConverter<Merge.MatchedWhenThen> {

        // @formatter:off
        private static ReferenceBlockBuilder<Void,Merge.MatchedWhenThen> builder =
                new ReferenceBlockBuilder<Void,Merge.MatchedWhenThen>()
                        .description("when matched then")
                        .sub_keyword(Keywords.WHEN)
                        .sub_keyword(Keywords.Key.MATCHED)
                        .sub()
                            .description("and clause_search_condition")
                            .optional(d -> d.getClauseSearchCondition() == null)
                            .sub_keyword(Keywords.AND)
                            .sub("<clause_search_condition>")
                                .data(Merge.MatchedWhenThen::getClauseSearchCondition)
                                .and()
                            .and()
                        .sub()
                            .keyword(Keywords.THEN)
                            .startNewline()
                            .and()
                        .sub("merge_matched")
                            .ref(MergeMatchedConverter.class)
                            .data(Merge.MatchedWhenThen::getMergeMatched)
                            .and();
        // @formatter:on

        public static ReferenceBlock meta() {
            return builder.build();
        }

        @Override
        public ReferenceBlock convert(Merge.MatchedWhenThen matchedWhenThen) {
            return builder
                    .data(matchedWhenThen)
                    .build();
        }
    }

    public static class NotMatchedTargetWhenThenConverter
            implements ReferenceBlockConverter<Merge.NotMatchedWhenThen> {

        // @formatter:off
        private static ReferenceBlockBuilder<Void,Merge.NotMatchedWhenThen> builder =
                new ReferenceBlockBuilder<Void,Merge.NotMatchedWhenThen>()
                        .description("when not matched then")
                        .sub_keyword(Keywords.WHEN)
                        .sub_keyword(Keywords.NOT)
                        .sub_keyword(Keywords.Key.MATCHED)
                        .sub()
                            .description("target keyword")
                            .optional(d -> !d.isUseByTarget())
                            .sub_keyword(Keywords.BY)
                            .sub_keyword(Keywords.Key.TARGET)
                            .and()
                        .sub()
                            .description("and clause_search_condition")
                            .optional(d -> d.getClauseSearchCondition() == null)
                            .sub_keyword(Keywords.AND)
                            .sub("<clause_search_condition>")
                                .data(Merge.MatchedWhenThen::getClauseSearchCondition)
                                .and()
                            .and()
                        .sub()
                            .keyword(Keywords.THEN)
                            .startNewline()
                            .and()
                        .sub("merge_not_matched")
                            .ref(MergeNotMatchedConverter.class)
                            .data(Merge.MatchedWhenThen::getMergeNotMatched)
                            .and();
        // @formatter:on

        public static ReferenceBlock meta() {
            return builder.build();
        }

        @Override
        public ReferenceBlock convert(Merge.NotMatchedWhenThen notMatchedWhenThen) {
            return builder
                    .data(notMatchedWhenThen)
                    .build();
        }
    }

    public static class NotMatchedSourceWhenThenConverter
            implements ReferenceBlockConverter<Merge.NotMatchedWhenThen> {

        // @formatter:off
        private static ReferenceBlockBuilder<Void,Merge.NotMatchedWhenThen> builder =
                new ReferenceBlockBuilder<Void,Merge.NotMatchedWhenThen>()
                        .description("when not matched by source then")
                        .sub_keyword(Keywords.WHEN)
                        .sub_keyword(Keywords.NOT)
                        .sub_keyword(Keywords.Key.MATCHED)
                        .sub_keyword(Keywords.BY)
                        .sub_keyword(Keywords.Key.SOURCE)
                        .sub()
                            .description("and clause_search_condition")
                            .optional(d -> d.getClauseSearchCondition() == null)
                            .sub_keyword(Keywords.AND)
                            .sub("<clause_search_condition>")
                                .data(Merge.MatchedWhenThen::getClauseSearchCondition)
                                .and()
                            .and()
                        .sub()
                            .keyword(Keywords.THEN)
                            .startNewline()
                            .and()
                        .sub("merge_matched")
                            .ref(MergeMatchedConverter.class)
                            .data(Merge.MatchedWhenThen::getMergeMatched)
                            .and();
        // @formatter:on

        public static ReferenceBlock meta() {
            return builder.build();
        }

        @Override
        public ReferenceBlock convert(Merge.NotMatchedWhenThen notMatchedWhenThen) {
            return builder
                    .data(notMatchedWhenThen)
                    .build();
        }
    }

    public static class MergeMatchedConverter
            implements ReferenceBlockConverter<Merge.MergeMatched> {

        // @formatter:off
        private static ReferenceBlockBuilder<Void,Merge.MergeMatched> builder =
                new ReferenceBlockBuilder<Void,Merge.MergeMatched>()
                        .overall("merge_matched")
                        .required()
                        .czse(Merge.MergeMatched::isUseSet)
                            .description("update")
                            .sub_keyword(Keywords.UPDATE)
                            .sub_keyword(Keywords.SET)
                            .sub("<set_clause>")
                                .sub()
                                    .list()
                                    .ref(UpdateConverter.SetItemConverter.class)
                                    .data(Merge.MergeMatched::getSets)
                                    .and()
//
//                            .sub("set_clause")
//                                .list()
//                                .ref(UpdateConverter.SetItemConverter.class)
//                                .data(Merge.MergeMatched::getSets)
                                .and()
                            .and()
                        .czse(d -> !d.isUseSet())
                            .description("delete")
                            .sub_keyword(Keywords.DELETE)
                            .and();
        // @formatter:on

        public static ReferenceBlock meta() {
            return builder.build();
        }

        @Override
        public ReferenceBlock convert(Merge.MergeMatched mergeMatched) {
            return builder
                    .data(mergeMatched)
                    .build();
        }
    }

    public static class MergeNotMatchedConverter
            implements ReferenceBlockConverter<Merge.MergeNotMatched> {

        // @formatter:off
        private static ReferenceBlockBuilder<Void,Merge.MergeNotMatched> builder =
                new ReferenceBlockBuilder<Void,Merge.MergeNotMatched>()
                        .overall("merge_not_matched")
                        .required()
                        .sub_keyword(Keywords.INSERT)
                        .sub()
                            .description("(column_list)")
                            .optional(d -> d.getColumns() == null)
                            .sub_keyword(Other.GROUP_START)
                            .sub("column_list")
                                .ref(InsertConverter.ColumnListConverter.meta())
                                .data(Merge.MergeNotMatched::getColumns)
                                .and()
                            .sub_keyword(Other.GROUP_END)
                            .and()
                        .sub()
                            .description("values")
                            .required()
                            //TODO donot use ref
                            .czse(d -> d.getValues() != null, "VALUES ( values_list )")
                                .ref(TableValueConstructorConverter.meta())
                                .data(Merge.MergeNotMatched::getValues)
                                .and()
                            .czse(Merge.MergeNotMatched::isUseDefaultValues)
                                .description("detault values")
                                .sub_keyword(Keywords.DEFAULT)
                                .sub_keyword(Keywords.VALUES)
                                .and()
                            .subTakeLine()
                            .startNewline()
                            .and()
                        .headFootTakeLine();
        // @formatter:on

        public static ReferenceBlock meta() {
            return builder.build();
        }

        @Override
        public ReferenceBlock convert(Merge.MergeNotMatched mergeNotMatched) {
            return builder
                    .data(mergeNotMatched)
                    .build();
        }
    }

}
