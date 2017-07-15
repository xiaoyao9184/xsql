package com.xy.xsql.block.tsql.core.statement.dml;

import com.xy.xsql.block.core.meta.BlockMetaBuilder;
import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.block.tsql.core.clause.FromConverter;
import com.xy.xsql.block.tsql.core.clause.OutputConverter;
import com.xy.xsql.block.tsql.core.clause.TableValueConstructorConverter;
import com.xy.xsql.block.tsql.core.clause.hints.TableHintLimitedConverter;
import com.xy.xsql.tsql.model.Keywords;
import com.xy.xsql.tsql.model.element.Other;
import com.xy.xsql.tsql.model.statement.dml.Merge;

/**
 * Created by xiaoyao9184 on 2017/6/17.
 */
public class MergeConverter
        implements ModelMetaBlockConverter<Merge> {

    // @formatter:off
    public static BlockMeta meta =
            new BlockMetaBuilder<Void,Merge>()
                    .overall("MERGE")
                    .sub("WITH <common_table_expression> [ ,...n ]")
                        .optional(d -> d.getWith() == null)
                        .scope(Merge::getWith)
                        .and()
                    .sub_keyword(Keywords.MERGE)
                    .sub("TOP ( expression ) [ PERCENT ]")
                        .optional(d -> d.getTop() == null)
                        .scope(Merge::getTop)
                        .format_line()
                        .and()
                    .sub()
                        .optional(d -> !d.isUseInto())
                        .keyword(Keywords.INTO)
                        .format_line()
                        .and()
                    .sub("<target_table>")
                        .scope(Merge::getTargetTable)
                        .format_indentation_right()
                        .and()
                    .sub()
                        .description("with merge hint")
                        .optional(d -> d.getMergeHint() == null)
                        .sub_keyword(Keywords.WITH)
                        .sub_keyword(Other.GROUP_START)
                        .sub("merge_hint")
                            .ref(MergeHintConverter.class)
                            .scope(Merge::getMergeHint)
                            .and()
                        .sub_keyword(Other.GROUP_END)
                        .and()
                    .sub()
                        .description("as table_alias")
                        .optional(d -> d.getTableAlias() == null)
                        .sub()
                            .optional(d -> !d.isUseAs())
                            .keyword(Keywords.AS)
                            .and()
                        .sub("table_alias")
                            .scope(Merge::getTableAlias)
                            .and()
                        .and()
                    .sub()
                        .keyword(Keywords.Key.USING)
                        .format_line()
                        .and()
                    .sub("table_source")
                        .ref(FromConverter.TableSourceConverter.class)
                        .scope(Merge::getTableSource)
                        .and()
                    .sub()
                        .keyword(Keywords.ON)
                        .format_line()
                        .and()
                    .sub("<merge_search_condition>")
                        .scope(Merge::getMergeSearchCondition)
                        .and()
                    .sub()
                        .description("when matched list")
                        .optional(d -> d.getMatchedWhenThenList() == null)
                        .repeat()
                        .ref(MatchedWhenThenConverter.meta)
                        .scope(Merge::getMatchedWhenThenList)
                        .format_line()
                        .and()
                    .sub()
                        .description("when not matched target")
                        .optional(d -> d.getNotMatchedWhenThenTarget() == null)
                        .ref(NotMatchedTargetWhenThenConverter.meta)
                        .scope(Merge::getNotMatchedWhenThenTarget)
                        .format_line()
                        .and()
                    .sub()
                        .description("when not matched source list")
                        .optional(d -> d.getNotMatchedWhenThenSourceList() == null)
                        .repeat()
                        .ref(NotMatchedSourceWhenThenConverter.meta)
                        .scope(Merge::getNotMatchedWhenThenSourceList)
                        .format_line()
                        .and()
                    .sub("output_clause")
                        .optional(d -> d.getOutput() == null)
                        .ref(OutputConverter.class)
                        .scope(Merge::getOutput)
                        .format_line()
                        .and()
                    .sub("OPTION ( <query_hint> [ ,...n ] )")
                        .optional(d -> d.getOption() == null)
                        .scope(Merge::getOption)
                        .format_line()
                        .and()
                    .style_sub_line_delimiter()
                    .build();
    // @formatter:on

    @Override
    public BlockMeta meta() {
        return meta;
    }


    public static class MergeHintConverter
            implements ModelMetaBlockConverter<Merge.MergeHint> {

        // @formatter:off
        public static BlockMeta meta =
                new BlockMetaBuilder<Void,Merge.MergeHint>()
                        .overall("merge_hint")
                        .style_required()
                        .sub("table_hint_limited")
                            .optional(d -> d.getTableHintLimitedList() == null)
                            .list()
                            .ref(TableHintLimitedConverter.class)
                            .scope(Merge.MergeHint::getTableHintLimitedList)
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
                                .scope(Merge.MergeHint::getIndexValues)
                                .and()
                            .sub_keyword(Other.GROUP_END)
                            .and()
                        .style_sub_line_delimiter()
                        .style_convention_line_delimiter()
                        .build();
        // @formatter:on

        @Override
        public BlockMeta meta() {
            return meta;
        }

    }

    public static class MatchedWhenThenConverter
            implements ModelMetaBlockConverter<Merge.MatchedWhenThen> {

        // @formatter:off
        public static BlockMeta meta =
                new BlockMetaBuilder<Void,Merge.MatchedWhenThen>()
                        .description("when matched then")
                        .sub_keyword(Keywords.WHEN)
                        .sub_keyword(Keywords.Key.MATCHED)
                        .sub()
                            .description("and clause_search_condition")
                            .optional(d -> d.getClauseSearchCondition() == null)
                            .sub_keyword(Keywords.AND)
                            .sub("<clause_search_condition>")
                                .scope(Merge.MatchedWhenThen::getClauseSearchCondition)
                                .and()
                            .and()
                        .sub()
                            .keyword(Keywords.THEN)
                            .style_start_new_line()
                            .format_line()
                            .and()
                        .sub("merge_matched")
                            .ref(MergeMatchedConverter.class)
                            .scope(Merge.MatchedWhenThen::getMergeMatched)
                            .and()
                        .build();
        // @formatter:on

        @Override
        public BlockMeta meta() {
            return meta;
        }

    }

    public static class NotMatchedTargetWhenThenConverter {

        // @formatter:off
        public static BlockMeta meta =
                new BlockMetaBuilder<Void,Merge.NotMatchedWhenThen>()
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
                                .scope(Merge.MatchedWhenThen::getClauseSearchCondition)
                                .and()
                            .and()
                        .sub()
                            .keyword(Keywords.THEN)
                            .style_start_new_line()
                            .format_line()
                            .and()
                        .sub("merge_not_matched")
                            .ref(MergeNotMatchedConverter.class)
                            .scope(Merge.MatchedWhenThen::getMergeNotMatched)
                            .and()
                        .build();
        // @formatter:on

        public BlockMeta meta() {
            return meta;
        }

    }

    public static class NotMatchedSourceWhenThenConverter {

        // @formatter:off
        public static BlockMeta meta =
                new BlockMetaBuilder<Void,Merge.NotMatchedWhenThen>()
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
                                .scope(Merge.MatchedWhenThen::getClauseSearchCondition)
                                .and()
                            .and()
                        .sub()
                            .keyword(Keywords.THEN)
                            .style_start_new_line()
                            .format_line()
                            .and()
                        .sub("merge_matched")
                            .ref(MergeMatchedConverter.class)
                            .scope(Merge.MatchedWhenThen::getMergeMatched)
                            .and()
                        .build();
        // @formatter:on

        public BlockMeta meta() {
            return meta;
        }

    }

    public static class MergeMatchedConverter
            implements ModelMetaBlockConverter<Merge.MergeMatched> {

        // @formatter:off
        public static BlockMeta meta =
                new BlockMetaBuilder<Void,Merge.MergeMatched>()
                        .overall("merge_matched")
                        .style_required()
                        .czse(Merge.MergeMatched::isUseSet)
                            .description("update")
                            .sub_keyword(Keywords.UPDATE)
                            .sub_keyword(Keywords.SET)
                            .sub("<set_clause>")
                                .sub()
                                    .list()
                                    .ref(UpdateConverter.SetItemConverter.class)
                                    .scope(Merge.MergeMatched::getSets)
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
                            .and()
                        .build();
        // @formatter:on

        @Override
        public BlockMeta meta() {
            return meta;
        }

    }

    public static class MergeNotMatchedConverter
            implements ModelMetaBlockConverter<Merge.MergeNotMatched> {

        // @formatter:off
        public static BlockMeta meta =
                new BlockMetaBuilder<Void,Merge.MergeNotMatched>()
                        .overall("merge_not_matched")
                        .style_required()
                        .sub_keyword(Keywords.INSERT)
                        .sub()
                            .description("(column_list)")
                            .optional(d -> d.getColumns() == null)
                            .sub_keyword(Other.GROUP_START)
                            .sub("column_list")
                    //TODO reference style
//                                .sub_ref(InsertConverter.ColumnListConverter.meta,Merge.MergeNotMatched::getColumns)
                                .ref(InsertConverter.ColumnListConverter.meta)
                                .scope(Merge.MergeNotMatched::getColumns)
                                .style_remove_reference()
                                .and()
                            .sub_keyword(Other.GROUP_END)
                            .and()
                        .sub()
                            .description("values")
                            .style_required()
                            //TODO donot use ref
                            .czse(d -> d.getValues() != null, "VALUES ( values_list )")
                    //TODO reference style
//                                .sub_ref(TableValueConstructorConverter.meta,Merge.MergeNotMatched::getValues)
                                .ref(TableValueConstructorConverter.meta)
                                .scope(Merge.MergeNotMatched::getValues)
                                .style_remove_reference()
                                .and()
                            .czse(Merge.MergeNotMatched::isUseDefaultValues)
                                .description("detault values")
                                .sub_keyword(Keywords.DEFAULT)
                                .sub_keyword(Keywords.VALUES)
                                .and()
                            .style_sub_line_delimiter()
                            .style_start_new_line()
                            .format_line()
                            .and()
                        .style_convention_line_delimiter()
                        .build();
        // @formatter:on

        @Override
        public BlockMeta meta() {
            return meta;
        }

    }

}
