package com.xy.xsql.block.tsql.core.predicate;

import com.xy.xsql.block.core.ReferenceBlockConverter;
import com.xy.xsql.block.core.ReferenceBlockBuilder;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.predicate.Comparison;

/**
 * Created by xiaoyao9184 on 2017/6/15.
 */
public class ComparisonPredicateConverter
        implements ReferenceBlockConverter<Comparison> {

    // @formatter:off
    private static ReferenceBlockBuilder<Void,Comparison> builder =
            new ReferenceBlockBuilder<Void,Comparison>()
                    .overall("Comparison Predicate")
                    .sub("expression")
                        .data(Comparison::getExpression)
                        .and()
                    .sub("= | < > | ! = | > | > = | ! > | < | < = | ! <")
                        .data(Comparison::getOperator)
                        .required()
                        .and()
                    .sub("expression")
                        .data(Comparison::getOperatorExpression)
                        .and();
    // @formatter:on

    public static BlockMeta meta() {
        return builder.build();
    }

    @Override
    public BlockMeta convert(Comparison comparison) {
        return builder
                .data(comparison)
                .build();
    }
}
