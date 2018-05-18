package com.xy.xsql.block.tsql.core.predicate;

import com.xy.xsql.block.meta.BlockMetaBuilder;
import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.elements.Keywords;
import com.xy.xsql.tsql.model.elements.operators.Logical;
import com.xy.xsql.tsql.model.predicate.Between;

/**
 * Created by xiaoyao9184 on 2017/6/15.
 */
public class BetweenPredicateConverter
        implements ModelMetaBlockConverter<Between> {

    // @formatter:off
    public static BlockMeta meta =
            new BlockMetaBuilder<Void,Between>()
                    .overall("BETWEEN")
                    .sub("expression")
                        .scope(Between::getExpression)
                        .and()
                    .sub()
                        .optional(d -> !d.isUseNotOperator())
                        .keyword(Logical.NOT)
                        .and()
                    .sub_keyword(Logical.BETWEEN)
                    .sub("expression")
                        .scope(Between::getStartExpression)
                        .syntax_required()
                        .and()
                    .sub_keyword(Keywords.AND)
                    .sub("expression")
                        .scope(Between::getEndExpression)
                        .and()
                    .build();
    // @formatter:on

    @Override
    public BlockMeta meta() {
        return meta;
    }

}
