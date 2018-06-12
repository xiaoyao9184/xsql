package com.xy.xsql.tsql.converter.elements.expressions;

import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.meta.BlockMetaBuilder;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.elements.expressions.UnaryExpression;

/**
 * Created by xiaoyao9184 on 2018/6/11.
 */
public class UnaryExpressionConverter
        implements ModelMetaBlockConverter<UnaryExpression> {

    // @formatter:off
    public static BlockMeta meta =
            new BlockMetaBuilder<Void,UnaryExpression>()
                    .description("UnaryExpression")
                    .sub()
                        .scope(d -> d.getUnary().getKeyword())
                        .and()
                    .sub()
                        .scope(d -> d.getExpression())
                        .and()
                    .build();
    // @formatter:on

    @Override
    public BlockMeta meta() {
        return meta;
    }

}
