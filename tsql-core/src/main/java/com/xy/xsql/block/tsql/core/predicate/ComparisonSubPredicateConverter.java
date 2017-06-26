package com.xy.xsql.block.tsql.core.predicate;

import com.xy.xsql.block.core.ReferenceBlockConverter;
import com.xy.xsql.block.core.ReferenceBlockBuilder;
import com.xy.xsql.block.model.ReferenceBlock;
import com.xy.xsql.tsql.model.element.Other;
import com.xy.xsql.tsql.model.predicate.ComparisonSubQuery;

/**
 * Created by xiaoyao9184 on 2017/6/15.
 */
public class ComparisonSubPredicateConverter
        implements ReferenceBlockConverter<ComparisonSubQuery> {

    // @formatter:off
    private static ReferenceBlockBuilder<Void,ComparisonSubQuery> builder =
            new ReferenceBlockBuilder<Void,ComparisonSubQuery>()
                    .overall("Comparison Predicate with SubQuery")
                    .sub("expression")
                        .data(ComparisonSubQuery::getExpression)
                        .and()
                    .sub("= | < > | ! = | > | > = | ! > | < | < = | ! <")
                        .data(ComparisonSubQuery::getOperator)
                        .required()
                        .and()
                    .sub("ALL | SOME | ANY")
                        .data(ComparisonSubQuery::getAll_some_any)
                        .required()
                        .and()
                    .sub_keyword(Other.GROUP_START)
                    .sub("subquery")
                        .data(ComparisonSubQuery::getSubquery)
                        .and()
                    .sub_keyword(Other.GROUP_END);
    // @formatter:on

    public static ReferenceBlock meta() {
        return builder.build();
    }

    @Override
    public ReferenceBlock convert(ComparisonSubQuery comparisonSubQuery) {
        return builder
                .data(comparisonSubQuery)
                .build();
    }
}
