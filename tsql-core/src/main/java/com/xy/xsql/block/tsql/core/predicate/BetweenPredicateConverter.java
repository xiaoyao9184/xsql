package com.xy.xsql.block.tsql.core.predicate;

import com.xy.xsql.block.core.ReferenceBlockConverter;
import com.xy.xsql.block.core.BlockMetaBuilder;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.Keywords;
import com.xy.xsql.tsql.model.operator.Logical;
import com.xy.xsql.tsql.model.predicate.Between;

/**
 * Created by xiaoyao9184 on 2017/6/15.
 */
public class BetweenPredicateConverter
        implements ReferenceBlockConverter<Between> {

    // @formatter:off
    private static BlockMetaBuilder<Void,Between> builder =
            new BlockMetaBuilder<Void,Between>()
                    .overall("BETWEEN")
                    .sub("expression")
                        .data(Between::getExpression)
                        .and()
                    .sub()
                        .optional(d -> !d.isUseNotOperator())
                        .keyword(Logical.NOT)
                        .and()
                    .sub_keyword(Logical.BETWEEN)
                    .sub("expression")
                        .data(Between::getStartExpression)
                        .required()
                        .and()
                    .sub_keyword(Keywords.AND)
                    .sub("expression")
                        .data(Between::getEndExpression)
                        .and();
    // @formatter:on

    public static BlockMeta meta() {
        return builder.build();
    }

    @Override
    public BlockMeta convert(Between between) {
        return builder
                .data(between)
                .build();
    }
}
