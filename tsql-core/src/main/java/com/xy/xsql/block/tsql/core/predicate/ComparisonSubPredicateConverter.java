package com.xy.xsql.block.tsql.core.predicate;

import com.xy.xsql.block.core.meta.BlockMetaBuilder;
import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.element.Other;
import com.xy.xsql.tsql.model.predicate.ComparisonSubQuery;

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
                        .style_required()
                        .and()
                    .sub("ALL | SOME | ANY")
                        .scope(ComparisonSubQuery::getAll_some_any)
                        .style_required()
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
