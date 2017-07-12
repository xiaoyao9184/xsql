package com.xy.xsql.block.tsql.core.clause;

import com.xy.xsql.block.core.MetaContextBlockBuilder;
import com.xy.xsql.block.core.MetaContextBlockConverter;
import com.xy.xsql.block.core.BlockMetaBuilder;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.block.model.MetaContextBlock;
import com.xy.xsql.block.tsql.core.predicate.*;
import com.xy.xsql.tsql.model.Keywords;
import com.xy.xsql.tsql.model.clause.SearchCondition;
import com.xy.xsql.tsql.model.element.Other;
import com.xy.xsql.tsql.model.predicate.*;

/**
 * Created by xiaoyao9184 on 2017/6/20.
 */
public class SearchConditionConverter
        implements MetaContextBlockConverter<SearchCondition> {

    // @formatter:off
    public static BlockMeta meta =
            new BlockMetaBuilder<Void,SearchCondition>()
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
                        .ref(AndOrNotItemConverter.meta)
                        .data(SearchCondition::getAndOrList)
                        .format_line()
                        .and()
                    .subTakeLine()
                    .build();
    // @formatter:on

    public BlockMeta meta() {
        return meta;
    }

    @Override
    public MetaContextBlock convert(SearchCondition context) {
        return MetaContextBlockBuilder
                .meta(meta())
                .build(context);
    }


    public static class AndOrNotItemConverter
            implements MetaContextBlockConverter<SearchCondition.AndOrNotItem> {

        // @formatter:off
        public static BlockMeta meta =
                new BlockMetaBuilder<Void,SearchCondition.AndOrNotItem>()
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
                            .and()
                        .build();
        // @formatter:on

        public BlockMeta meta() {
            return meta;
        }

        @Override
        public MetaContextBlock convert(SearchCondition.AndOrNotItem context) {
            return MetaContextBlockBuilder
                    .meta(meta())
                    .build(context);
        }
    }


    public static class PredicateConverter
            implements MetaContextBlockConverter<Predicate> {

        // @formatter:off
        public static BlockMeta meta =
                new BlockMetaBuilder<Void,Predicate>()
                        .overall("predicate")
                        .czse_meta(d -> d instanceof Comparison, ComparisonPredicateConverter.meta)
                        .czse_meta(d -> d instanceof Like, LikePredicateConverter.meta)
                        .czse_meta(d -> d instanceof Between, BetweenPredicateConverter.meta)
                        .czse_meta(d -> d instanceof IsNull, IsNullPredicateConverter.meta)
                        .czse_meta(d -> d instanceof Contains, ContainsPredicateConverter.meta)
                        .czse_meta(d -> d instanceof FreeText, FreeTextPredicateConverter.meta)
                        .czse_meta(d -> d instanceof In, InPredicateConverter.meta)
                        .czse_meta(d -> d instanceof ComparisonSubQuery, ComparisonSubPredicateConverter.meta)
                        .czse_meta(d -> d instanceof Exists, ExistsPredicateConverter.meta)
                        .subTakeLine()
                        .build();
        // @formatter:on

        public BlockMeta meta() {
            return meta;
        }

        @Override
        public MetaContextBlock convert(Predicate context) {
            return MetaContextBlockBuilder
                    .meta(meta())
                    .build(context);
        }
    }

}
