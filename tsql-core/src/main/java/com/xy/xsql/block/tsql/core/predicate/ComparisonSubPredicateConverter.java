package com.xy.xsql.block.tsql.core.predicate;

import com.xy.xsql.block.core.MetaContextBlockBuilder;
import com.xy.xsql.block.core.MetaContextBlockConverter;
import com.xy.xsql.block.core.BlockMetaBuilder;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.block.model.MetaContextBlock;
import com.xy.xsql.tsql.model.element.Other;
import com.xy.xsql.tsql.model.predicate.ComparisonSubQuery;

/**
 * Created by xiaoyao9184 on 2017/6/15.
 */
public class ComparisonSubPredicateConverter
        implements MetaContextBlockConverter<ComparisonSubQuery> {

    // @formatter:off
    private static BlockMetaBuilder<Void,ComparisonSubQuery> builder =
            new BlockMetaBuilder<Void,ComparisonSubQuery>()
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

    public static BlockMeta meta() {
        return builder.build();
    }

    @Override
    public MetaContextBlock convert(ComparisonSubQuery context) {
        return MetaContextBlockBuilder
                .meta(meta())
                .build(context);
    }

}
