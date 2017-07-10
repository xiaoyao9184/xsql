package com.xy.xsql.block.tsql.core.clause;

import com.xy.xsql.block.core.ReferenceBlockConverter;
import com.xy.xsql.block.core.ReferenceBlockBuilder;
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
        implements ReferenceBlockConverter<SearchCondition> {

    // @formatter:off
    private static ReferenceBlockBuilder<Void,SearchCondition> builder =
            new ReferenceBlockBuilder<Void,SearchCondition>()
                    .overall("search_condition")
                    .sub()
                        .description("[ NOT ] <predicate> | ( <search_condition> )")
                        .required()
                        .sub()
                            .optional(d -> !d.isUseNot())
                            .keyword(Keywords.NOT)
                            .and()
                        .sub()
                            .description("predicate/(search_condition)")
                            .czse(d -> d.getPredicate() != null, "predicate")
                                .ref(PredicateConverter.class)
                                .data(SearchCondition::getPredicate)
                                .and()
                            .czse(d -> d.getSearchCondition() != null)
                                .description("(search_condition)")
                                .sub_keyword(Other.GROUP_START)
                                .sub("search_condition")
                                    .ref(SearchConditionConverter.class)
                                    .data(SearchCondition::getSearchCondition)
                                    .and()
                                .sub_keyword(Other.GROUP_END)
                                .and()
                            .and()
                        .and()
                    .sub()
                        .description("search_condition's and/or list")
                        .optional(d -> d.getAndOrList() == null)
                        .repeat()
                        .ref(AndOrNotItemConverter.meta())
                        .data(SearchCondition::getAndOrList)
                        .and()
                    .subTakeLine();
    // @formatter:on

    public static BlockMeta meta() {
        return builder.build();
    }

    @Override
    public BlockMeta convert(SearchCondition searchCondition) {
        return builder
                .data(searchCondition)
                .build();
    }


    public static class AndOrNotItemConverter
            implements ReferenceBlockConverter<SearchCondition.AndOrNotItem> {

        // @formatter:off
        private static ReferenceBlockBuilder<Void,SearchCondition.AndOrNotItem> builder =
                new ReferenceBlockBuilder<Void,SearchCondition.AndOrNotItem>()
                        .description("search_condition's and/or item")
                        .sub()
                            .description("AND | OR")
                            .required()
                            .czse(SearchCondition.AndOrNotItem::isUseAnd, "AND")
                                .keyword(Keywords.AND)
                                .and()
                            .czse(d -> !d.isUseAnd(), "OR")
                                .keyword(Keywords.OR)
                                .and()
                            .and()
                        .sub()
                            .optional(d -> !d.isUseNot())
                            .keyword(Keywords.NOT)
                            .and()
                        .sub()
                            .description("predicate/(search_condition)")
                            .required()
                            .czse(d -> d.getPredicate() != null, "predicate")
                                .ref(PredicateConverter.class)
                                .data(SearchCondition.AndOrNotItem::getPredicate)
                                .and()
                            .czse(d -> d.getSearchCondition() != null)
                                .description("(search_condition)")
                                .sub_keyword(Other.GROUP_START)
                                .sub("search_condition")
                                    .ref(SearchConditionConverter.class)
                                    .data(SearchCondition.AndOrNotItem::getSearchCondition)
                                    .and()
                                .sub_keyword(Other.GROUP_END)
                                .and()
                            .and();
        // @formatter:on

        public static BlockMeta meta() {
            return builder.build();
        }

        @Override
        public BlockMeta convert(SearchCondition.AndOrNotItem andOrNotItem) {
            return builder
                    .data(andOrNotItem)
                    .build();
        }
    }


    public static class PredicateConverter
            implements ReferenceBlockConverter<Predicate> {

        // @formatter:off
        private static ReferenceBlockBuilder<Void,Predicate> builder =
                new ReferenceBlockBuilder<Void,Predicate>()
                        .overall("predicate")
                        .czse_meta(d -> d instanceof Comparison, ComparisonPredicateConverter.meta())
                        .czse_meta(d -> d instanceof Like, LikePredicateConverter.meta())
                        .czse_meta(d -> d instanceof Between, BetweenPredicateConverter.meta())
                        .czse_meta(d -> d instanceof IsNull, IsNullPredicateConverter.meta())
                        .czse_meta(d -> d instanceof Contains, ContainsPredicateConverter.meta())
                        .czse_meta(d -> d instanceof FreeText, FreeTextPredicateConverter.meta())
                        .czse_meta(d -> d instanceof In, InPredicateConverter.meta())
                        .czse_meta(d -> d instanceof ComparisonSubQuery, ComparisonSubPredicateConverter.meta())
                        .czse_meta(d -> d instanceof Exists, ExistsPredicateConverter.meta())
                        .subTakeLine();
        // @formatter:on

        public static BlockMeta meta() {
            return builder.build();
        }

        @Override
        public BlockMeta convert(Predicate predicate) {
            return builder
                    .data(predicate)
                    .build();
        }
    }

}
