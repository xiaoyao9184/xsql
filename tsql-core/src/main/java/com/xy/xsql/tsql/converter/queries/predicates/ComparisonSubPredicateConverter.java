package com.xy.xsql.tsql.converter.queries.predicates;

import com.xy.xsql.block.meta.BlockMetaBuilder;
import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.elements.Other;
import com.xy.xsql.tsql.model.queries.predicates.ComparisonSubQuery;

/**
 * Created by xiaoyao9184 on 2017/6/15.
 */
public class ComparisonSubPredicateConverter
        implements ModelMetaBlockConverter<ComparisonSubQuery> {

    // @formatter:off
    public static BlockMeta meta =
            new BlockMetaBuilder<Void,ComparisonSubQuery>()
                    .overall("Comparison Predicate with SubQuery")
                    .sub("expression")
                        .scope(ComparisonSubQuery::getExpression)
                        .and()
                    .sub("= | < > | ! = | > | > = | ! > | < | < = | ! <")
                        .scope(ComparisonSubQuery::getOperator)
                        .syntax_required()
                        .and()
                    .sub("ALL | SOME | ANY")
                        .scope(ComparisonSubQuery::getAll_some_any)
                        .syntax_required()
                        .and()
                    .sub_keyword(Other.GROUP_START)
                    .sub("subquery")
                        .scope(ComparisonSubQuery::getSubquery)
                        .format_indentation_right()
                        .and()
                    .sub_keyword(Other.GROUP_END)
                    .build();
    // @formatter:on

    @Override
    public BlockMeta meta() {
        return meta;
    }

}
