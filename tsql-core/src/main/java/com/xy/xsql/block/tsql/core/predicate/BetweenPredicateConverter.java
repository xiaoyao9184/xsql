package com.xy.xsql.block.tsql.core.predicate;

import com.xy.xsql.block.core.BlockConverter;
import com.xy.xsql.block.core.ReferenceBlockBuilder;
import com.xy.xsql.block.model.ReferenceBlock;
import com.xy.xsql.block.model.Block;
import com.xy.xsql.tsql.model.Keywords;
import com.xy.xsql.tsql.model.operator.Logical;
import com.xy.xsql.tsql.model.predicate.Between;

/**
 * Created by xiaoyao9184 on 2017/6/15.
 */
public class BetweenPredicateConverter
        implements BlockConverter<Between> {

    // @formatter:off
    private static ReferenceBlockBuilder<Void,Between> builder =
            new ReferenceBlockBuilder<Void,Between>()
                    .overall("BETWEEN")
                    .sub("expression")
                        .data(Between::getExpression)
                        .and()
                    .sub()
                        .keyword(Logical.NOT)
                        .optional(Between::isUseNotOperator)
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

    public static ReferenceBlock meta() {
        return builder.build();
    }

    @Override
    public Block convert(Between between) {
        return builder
                .data(between)
                .build();
    }
}
