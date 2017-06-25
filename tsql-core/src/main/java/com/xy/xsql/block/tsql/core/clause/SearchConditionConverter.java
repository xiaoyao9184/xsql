package com.xy.xsql.block.tsql.core.clause;

import com.xy.xsql.block.core.BlockConverter;
import com.xy.xsql.block.core.ReferenceBlockBuilder;
import com.xy.xsql.block.model.ReferenceBlock;
import com.xy.xsql.block.tsql.core.predicate.*;
import com.xy.xsql.tsql.model.Block;
import com.xy.xsql.tsql.model.Keywords;
import com.xy.xsql.tsql.model.clause.SearchCondition;
import com.xy.xsql.tsql.model.element.Other;
import com.xy.xsql.tsql.model.predicate.*;

/**
 * Created by xiaoyao9184 on 2017/6/20.
 */
public class SearchConditionConverter
        implements BlockConverter<SearchCondition> {

    // @formatter:off
    private static ReferenceBlockBuilder<Void,SearchCondition> builder =
            new ReferenceBlockBuilder<Void,SearchCondition>()
                    .overall("search_condition")
                    .sub()
                        .description("[ NOT ] <predicate> | ( <search_condition> )")
                        .required()
                        .sub("NOT")
                            .optional(SearchCondition::isUseNot)
                            .keyword(Keywords.NOT)
                            .and()
                        .sub()
                            .description("<predicate> | ( <search_condition> )")
                            .czse(d -> d.getPredicate() != null, "predicate")
                                .ref(Predicate.class)
                                .data(SearchCondition::getPredicate)
                                .and()
                            .czse(d -> d.getSearchCondition() != null)
                                .description("( <search_condition> )")
                                .sub_keyword(Other.GROUP_START)
                                .sub("search_condition")
                                    .ref(SearchCondition.class)
                                    .data(SearchCondition::getSearchCondition)
                                    .and()
                                .sub_keyword(Other.GROUP_END)
                                .and()
                            .and()
                        .and()
                    .sub()
                        .description("--And Or List")
                        .filter(d -> d.getAndOrList() == null)
                        .list(AndOrNotItemConverter.meta())
                        .more()
                        .data(SearchCondition::getAndOrList)
                        .and()
                    .subTakeLine();
    // @formatter:on

    public static ReferenceBlock meta() {
        return builder.build();
    }

    @Override
    public Block convert(SearchCondition searchCondition) {
        return builder
                .data(searchCondition)
                .build();
    }


    public static class AndOrNotItemConverter
            implements BlockConverter<SearchCondition.AndOrNotItem> {

        // @formatter:off
        private static ReferenceBlockBuilder<Void,SearchCondition.AndOrNotItem> builder =
                new ReferenceBlockBuilder<Void,SearchCondition.AndOrNotItem>()
                        .optional()
                        .sub()
                            .description("AND | OR")
                            .required()
                            .czse(d -> d.isUseAnd(), "AND")
                                .keyword(Keywords.AND)
                                .and()
                            .czse(d -> !d.isUseAnd(), "OR")
                                .keyword(Keywords.OR)
                                .and()
                            .and()
                        .sub("NOT")
                            .optional(d -> !d.isUseNot())
                            .keyword(Keywords.NOT)
                            .and()
                        .sub()
                            .description("<predicate> | ( <search_condition> )")
                            .required()
                            .czse(d -> d.getPredicate() != null, "predicate")
                                .ref(Predicate.class)
                                .data(SearchCondition.AndOrNotItem::getPredicate)
                                .and()
                            .czse(d -> d.getSearchCondition() != null)
                                .description("( <search_condition> )")
                                .filter(d -> d.getSearchCondition() == null)
                                .sub_keyword(Other.GROUP_START)
                                .sub("search_condition")
                                    .ref(SearchCondition.class)
                                    .data(SearchCondition.AndOrNotItem::getSearchCondition)
                                    .and()
                                .sub_keyword(Other.GROUP_END)
                                .and()
                            .and();
        // @formatter:on

        public static ReferenceBlock meta() {
            return builder.build();
        }

        @Override
        public Block convert(SearchCondition.AndOrNotItem andOrNotItem) {
            return builder
                    .data(andOrNotItem)
                    .build();
        }
    }


    public static class PredicateConverter
            implements BlockConverter<Predicate> {

        // @formatter:off
        private static ReferenceBlockBuilder<Void,Predicate> builder =
                new ReferenceBlockBuilder<Void,Predicate>()
                        .overall("predicate")
                        .czse(d -> d instanceof Comparison, ComparisonPredicateConverter.meta())
                        .czse(d -> d instanceof Like, LikePredicateConverter.meta())
                        .czse(d -> d instanceof Between, BetweenPredicateConverter.meta())
                        .czse(d -> d instanceof IsNull, IsNullPredicateConverter.meta())
                        .czse(d -> d instanceof Contains, ContainsPredicateConverter.meta())
                        .czse(d -> d instanceof FreeText, FreeTextPredicateConverter.meta())
                        .czse(d -> d instanceof In, InPredicateConverter.meta())
                        .czse(d -> d instanceof ComparisonSubQuery, ComparisonSubPredicateConverter.meta())
                        .czse(d -> d instanceof Exists, ExistsPredicateConverter.meta())
                        .subTakeLine();
        // @formatter:on

        public static ReferenceBlock meta() {
            return builder.build();
        }

        @Override
        public Block convert(Predicate predicate) {
            return builder
                    .data(predicate)
                    .build();
        }
    }

}
