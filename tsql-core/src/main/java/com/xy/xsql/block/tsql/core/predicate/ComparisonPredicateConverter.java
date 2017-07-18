package com.xy.xsql.block.tsql.core.predicate;

import com.xy.xsql.block.core.meta.BlockMetaBuilder;
import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.predicate.Comparison;

/**
 * Created by xiaoyao9184 on 2017/6/15.
 */
public class ComparisonPredicateConverter
        implements ModelMetaBlockConverter<Comparison> {

    // @formatter:off
    public static BlockMeta meta =
            new BlockMetaBuilder<Void,Comparison>()
                    .overall("Comparison Predicate")
                    .sub("expression")
                        .scope(Comparison::getExpression)
                        .and()
                    .sub("= | < > | ! = | > | > = | ! > | < | < = | ! <")
                        .scope(Comparison::getOperator)
                        .syntax_required()
                        .and()
                    .sub("expression")
                        .scope(Comparison::getOperatorExpression)
                        .and()
                    .build();
    // @formatter:on

    @Override
    public BlockMeta meta() {
        return meta;
    }

}
