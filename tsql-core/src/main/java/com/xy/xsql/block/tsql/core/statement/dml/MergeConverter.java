package com.xy.xsql.block.tsql.core.statement.dml;

import com.xy.xsql.block.meta.BlockMetaBuilder;
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
                        .and()
                    .sub()
                        .description("into table")
                        .sub()
                            .optional(d -> !d.isUseInto())
                            .keyword(Keywords.INTO)
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
                        .and()
                    .sub()
                        .description("using table_source")
                        .sub()
                            .keyword(Keywords.Key.USING)
                            .and()
                        .sub("table_source")
                            .ref(FromConverter.TableSourceConverter.class)
                            .scope(Merge::getTableSource)
                            .format_indentation_right()
                            .and()
                        .and()
                    .sub()
                        .description("using table_source")
                        .sub()
                            .keyword(Keywords.ON)
                            .and()
                        .sub("<merge_search_condition>")
                            .scope(Merge::getMergeSearchCondition)
                            .format_indentation_right()
                            .and()
                        .and()
                    .sub()
                        .description("when matched list")
                        .optional(d -> d.getMatchedWhenThenList() == null)
                        .repeat()
                        .ref(MatchedWhenThenConverter.meta)
                        .scope(Merge::getMatchedWhenThenList)
                        .sub_format_line(true)
                        .and()
                    .sub()
                        .description("when not matched target")
                        .optional(d -> d.getNotMatchedWhenThenTarget() == null)
                        .ref(NotMatchedTargetWhenThenConverter.meta)
                        .scope(Merge::getNotMatchedWhenThenTarget)
                        .and()
                    .sub()
                        .description("when not matched source list")
                        .optional(d -> d.getNotMatchedWhenThenSourceList() == null)
                        .repeat()
                        .ref(NotMatchedSourceWhenThenConverter.meta)
                        .scope(Merge::getNotMatchedWhenThenSourceList)
                        .and()
                    .sub("output_clause")
                        .optional(d -> d.getOutput() == null)
                        .ref(OutputConverter.class)
                        .scope(Merge::getOutput)
                        .and()
                    .sub("OPTION ( <query_hint> [ ,...n ] )")
                        .optional(d -> d.getOption() == null)
                        .scope(Merge::getOption)
                        .and()
                    .syntax_sub_line()
                    .sub_format_line(true)
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
                        .syntax_required()
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
                        .syntax_sub_line()
                        .syntax_context_indentation()
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
                            .syntax_indentation_right()
                            .format_line(true)
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
                            .format_indentation_right()
                            .and()
                        .sub()
                            .keyword(Keywords.THEN)
                            .syntax_indentation_right()
                            .format_line(true)
                            .and()
                        .sub("merge_not_matched")
                            .ref(MergeNotMatchedConverter.class)
                            .scope(Merge.MatchedWhenThen::getMergeNotMatched)
                            .format_indentation_right()
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
                            .syntax_indentation_right()
                            .format_line()
                            .and()
                        .sub("merge_matched")
                            .ref(MergeMatchedConverter.class)
                            .scope(Merge.MatchedWhenThen::getMergeMatched)
                            .format_indentation_right()
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
                        .syntax_required()
                        .czse(Merge.MergeMatched::isUseSet)
                            .description("update")
                            .sub_keyword(Keywords.UPDATE)
                            .sub_keyword(Keywords.SET)
                            .sub("<set_clause>")
                                .sub()
                                    .description("not output [,...n]")
                                    .list()
                                    .ref(UpdateConverter.SetItemConverter.class)
                                    .scope(Merge.MergeMatched::getSets)
                                    .sub_format_line()
                                    .and()
                                .format_indentation_right()
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
                        .syntax_required()
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
                                .syntax_reference_remove()
                                .and()
                            .sub_keyword(Other.GROUP_END)
                            .and()
                        .sub()
                            .description("values")
                            //TODO donot use ref
                            .czse(d -> d.getValues() != null, "VALUES ( values_list )")
                    //TODO reference style
//                                .sub_ref(TableValueConstructorConverter.meta,Merge.MergeNotMatched::getValues)
                                .ref(TableValueConstructorConverter.meta)
                                .scope(Merge.MergeNotMatched::getValues)
                                .syntax_reference_remove()
                                .and()
                            .czse(Merge.MergeNotMatched::isUseDefaultValues)
                                .description("detault values")
                                .sub_keyword(Keywords.DEFAULT)
                                .sub_keyword(Keywords.VALUES)
                                .and()
                            .syntax_required()
                            .syntax_indentation_right()
                            .syntax_sub_line()
                            .format_line(true)
                            .and()
                        .syntax_context_indentation()
                        .build();
        // @formatter:on

        @Override
        public BlockMeta meta() {
            return meta;
        }

    }

}
