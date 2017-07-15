package com.xy.xsql.block.tsql.core.clause;

import com.xy.xsql.block.core.meta.BlockMetaBuilder;
import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.block.tsql.core.predicate.*;
import com.xy.xsql.tsql.model.Keywords;
import com.xy.xsql.tsql.model.clause.SearchCondition;
import com.xy.xsql.tsql.model.element.Other;
import com.xy.xsql.tsql.model.predicate.*;

/**
 * Created by xiaoyao9184 on 2017/6/20.
 */
public class SearchConditionConverter
        implements ModelMetaBlockConverter<SearchCondition> {

    // @formatter:off
    public static BlockMeta meta =
            new BlockMetaBuilder<Void,SearchCondition>()
                    .overall("search_condition")
                    .sub()
                        .description("not predicate/(search_condition)")
                        .style_required()
                        .sub()
                            .optional(d -> !d.isUseNot())
                            .keyword(Keywords.NOT)
                            .and()
                        .sub()
                            .description("predicate/(search_condition)")
                            .czse(d -> d.getPredicate() != null, "predicate")
                                .ref(PredicateConverter.class)
                                .scope(SearchCondition::getPredicate)
                                .and()
                            .czse(d -> d.getSearchCondition() != null)
                                .description("(search_condition)")
                                .sub_keyword(Other.GROUP_START)
                                .sub("search_condition")
                                    .ref(SearchConditionConverter.class)
                                    .scope(SearchCondition::getSearchCondition)
                                    .and()
                                .sub_keyword(Other.GROUP_END)
                                .and()
                            .and()
                        .and()
                    .sub()
                        .description("search_condition's and/or list")
                        .optional(d -> d.getAndOrList() == null)
                        .repeat()
                        .ref(AndOrNotItemConverter.meta)
                        .scope(SearchCondition::getAndOrList)
                        .format_line()
                        .and()
                    .style_sub_line_delimiter()
                    .build();
    // @formatter:on

    @Override
    public BlockMeta meta() {
        return meta;
    }


    public static class AndOrNotItemConverter
            implements ModelMetaBlockConverter<SearchCondition.AndOrNotItem> {

        // @formatter:off
        public static BlockMeta meta =
                new BlockMetaBuilder<Void,SearchCondition.AndOrNotItem>()
                        .description("search_condition's and/or item")
                        .sub()
                            .description("and/or")
                            .style_required()
                            .czse_keyword(SearchCondition.AndOrNotItem::isUseAnd, Keywords.AND)
                            .czse_keyword(d -> !d.isUseAnd(), Keywords.OR)
                            .and()
                        .sub()
                            .optional(d -> !d.isUseNot())
                            .keyword(Keywords.NOT)
                            .and()
                        .sub()
                            .description("predicate/(search_condition)")
                            .style_required()
                            .czse(d -> d.getPredicate() != null, "predicate")
                                .ref(PredicateConverter.class)
                                .scope(SearchCondition.AndOrNotItem::getPredicate)
                                .and()
                            .czse(d -> d.getSearchCondition() != null)
                                .description("(search_condition)")
                                .sub_keyword(Other.GROUP_START)
                                .sub("search_condition")
                                    .ref(SearchConditionConverter.class)
                                    .scope(SearchCondition.AndOrNotItem::getSearchCondition)
                                    .and()
                                .sub_keyword(Other.GROUP_END)
                                .and()
                            .and()
                        .format_line()
                        .build();
        // @formatter:on

        @Override
        public BlockMeta meta() {
            return meta;
        }

    }


    public static class PredicateConverter
            implements ModelMetaBlockConverter<Predicate> {

        // @formatter:off
        public static BlockMeta meta =
                new BlockMetaBuilder<Void,Predicate>()
                        .overall("predicate")
                        .czse_ref(d -> d instanceof Comparison, ComparisonPredicateConverter.meta)
                        .czse_ref(d -> d instanceof Like, LikePredicateConverter.meta)
                        .czse_ref(d -> d instanceof Between, BetweenPredicateConverter.meta)
                        .czse_ref(d -> d instanceof IsNull, IsNullPredicateConverter.meta)
                        .czse_ref(d -> d instanceof Contains, ContainsPredicateConverter.meta)
                        .czse_ref(d -> d instanceof FreeText, FreeTextPredicateConverter.meta)
                        .czse_ref(d -> d instanceof In, InPredicateConverter.meta)
                        .czse_ref(d -> d instanceof ComparisonSubQuery, ComparisonSubPredicateConverter.meta)
                        .czse_ref(d -> d instanceof Exists, ExistsPredicateConverter.meta)
                        .style_sub_line_delimiter()
                        .build();
        // @formatter:on

        @Override
        public BlockMeta meta() {
            return meta;
        }

    }

}
