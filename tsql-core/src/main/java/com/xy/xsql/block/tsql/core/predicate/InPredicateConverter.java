package com.xy.xsql.block.tsql.core.predicate;

import com.xy.xsql.block.core.ReferenceBlockConverter;
import com.xy.xsql.block.core.ReferenceBlockBuilder;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.Keywords;
import com.xy.xsql.tsql.model.element.Other;
import com.xy.xsql.tsql.model.predicate.In;

/**
 * Created by xiaoyao9184 on 2017/6/15.
 */
public class InPredicateConverter
        implements ReferenceBlockConverter<In> {

    // @formatter:off
    private static ReferenceBlockBuilder<Void,In> builder =
            new ReferenceBlockBuilder<Void,In>()
                    .overall("IN")
                    .sub("expression")
                        .data(In::getExpression)
                        .and()
                    .sub()
                        .optional(d -> !d.isUseNotOperator())
                        .keyword(Keywords.NOT)
                        .and()
                    .sub_keyword(Keywords.IN)
                    .sub_keyword(Other.GROUP_START)
                    .sub()
                        .czse(d -> d.getSubquery() != null,"subquery")
                            .data(In::getSubquery)
                            .and()
                        .czse(d -> d.getExpressionList() != null,"expression")
                            .list()
                            .required()
                            .data(In::getExpressionList)
                            .and()
                        .and()
                    .sub_keyword(Other.GROUP_END);
    // @formatter:on

    public static BlockMeta meta() {
        return builder.build();
    }

    @Override
    public BlockMeta convert(In in) {
        return builder
                .data(in)
                .build();
    }
}
