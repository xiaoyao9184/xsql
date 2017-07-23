package com.xy.xsql.block.tsql.core.expression;

import com.xy.xsql.block.meta.BlockMetaBuilder;
import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.expression.BinaryExpression;

/**
 * Created by xiaoyao9184 on 2017/6/29.
 */
public class BinaryExpressionConverter
        implements ModelMetaBlockConverter<BinaryExpression> {

    // @formatter:off
    public static BlockMeta meta =
            new BlockMetaBuilder<Void,BinaryExpression>()
                    .description("binary expression")
                    .sub("expression")
                        .scope(BinaryExpression::getExpressionLeft)
                        .and()
                    .sub("binary_operator")
                        .scope(BinaryExpression::getOperator)
                        .and()
                    .sub("expression")
                        .scope(BinaryExpression::getExpressionRight)
                        .and()
                    .build();
    // @formatter:on

    @Override
    public BlockMeta meta() {
        return meta;
    }

}
