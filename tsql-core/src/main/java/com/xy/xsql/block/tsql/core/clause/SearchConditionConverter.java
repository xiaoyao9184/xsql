package com.xy.xsql.block.tsql.core.clause;

import com.xy.xsql.block.core.BlockConverter;
import com.xy.xsql.block.core.ReferenceBlockBuilder;
import com.xy.xsql.block.model.ReferenceBlock;
import com.xy.xsql.block.tsql.core.predicate.*;
import com.xy.xsql.tsql.model.Block;
import com.xy.xsql.tsql.model.Keywords;
import com.xy.xsql.tsql.model.clause.SearchCondition;
import com.xy.xsql.tsql.model.clause.With;
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
                            .oneOf("predicate")
                                .filter(d -> d.getPredicate() == null)
                                .ref(Predicate.class)
                                .data(SearchCondition::getPredicate)
                                .and()
                            .oneOf()
                                .description("( <search_condition> )")
                                .filter(d -> d.getSearchCondition() == null)
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
                            .oneOf("AND")
                                .keyword(Keywords.AND)
                                .and()
                            .oneOf("OR")
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
                            .oneOf("predicate")
                                .filter(d -> d.getPredicate() == null)
                                .ref(Predicate.class)
                                .data(SearchCondition.AndOrNotItem::getPredicate)
                                .and()
                            .oneOf()
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
                        .oneOf(ComparisonPredicateConverter.meta())
//                            .filter(d -> d instanceof Comparison)
//                            .and()
                        .oneOf(LikePredicateConverter.meta())
//                            .filter(d -> d instanceof Like)
//                            .and()
                        .oneOf(BetweenPredicateConverter.meta())
//                            .filter(d -> d instanceof Between)
//                            .and()
                        .oneOf(IsNullPredicateConverter.meta())
//                            .filter(d -> d instanceof IsNull)
//                            .and()
                        .oneOf(ContainsPredicateConverter.meta())
//                            .filter(d -> d instanceof Contains)
//                            .and()
                        .oneOf(FreeTextPredicateConverter.meta())
//                            .filter(d -> d instanceof FreeText)
//                            .and()
                        .oneOf(InPredicateConverter.meta())
//                            .filter(d -> d instanceof In)
//                            .and()
                        .oneOf(ComparisonSubPredicateConverter.meta())
//                            .filter(d -> d instanceof ComparisonSub)
//                            .and()
                        .oneOf(ExistsPredicateConverter.meta())
//                            .filter(d -> d instanceof Exists)
//                            .and()
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
