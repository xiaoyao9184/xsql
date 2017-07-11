package com.xy.xsql.block.tsql.core.predicate;

import com.xy.xsql.block.core.MetaContextBlockBuilder;
import com.xy.xsql.block.core.MetaContextBlockConverter;
import com.xy.xsql.block.core.BlockMetaBuilder;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.block.model.MetaContextBlock;
import com.xy.xsql.tsql.model.predicate.Comparison;

/**
 * Created by xiaoyao9184 on 2017/6/15.
 */
public class ComparisonPredicateConverter
        implements MetaContextBlockConverter<Comparison> {

    // @formatter:off
    public static BlockMeta meta =
            new BlockMetaBuilder<Void,Comparison>()
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
                        .and()
                    .build();
    // @formatter:on

    public BlockMeta meta() {
        return meta;
    }

    @Override
    public MetaContextBlock convert(Comparison context) {
        return MetaContextBlockBuilder
                .meta(meta())
                .build(context);
    }

}
