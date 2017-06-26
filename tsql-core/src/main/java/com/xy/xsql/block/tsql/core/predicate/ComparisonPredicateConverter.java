package com.xy.xsql.block.tsql.core.predicate;

import com.xy.xsql.block.core.BlockConverter;
import com.xy.xsql.block.core.ReferenceBlockBuilder;
import com.xy.xsql.block.model.ReferenceBlock;
import com.xy.xsql.block.model.Block;
import com.xy.xsql.tsql.model.predicate.Comparison;

/**
 * Created by xiaoyao9184 on 2017/6/15.
 */
public class ComparisonPredicateConverter
        implements BlockConverter<Comparison> {

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

    public static ReferenceBlock meta() {
        return builder.build();
    }

    @Override
    public Block convert(Comparison comparison) {
        return builder
                .data(comparison)
                .build();
    }
}
