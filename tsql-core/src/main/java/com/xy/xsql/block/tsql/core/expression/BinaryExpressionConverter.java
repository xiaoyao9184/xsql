package com.xy.xsql.block.tsql.core.expression;

import com.xy.xsql.block.core.BlockMetaBuilder;
import com.xy.xsql.block.core.MetaContextBlockBuilder;
import com.xy.xsql.block.core.MetaContextBlockConverter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.block.model.MetaContextBlock;
import com.xy.xsql.tsql.model.expression.BinaryExpression;

/**
 * Created by xiaoyao9184 on 2017/6/29.
 */
public class BinaryExpressionConverter
        implements MetaContextBlockConverter<BinaryExpression> {

    // @formatter:off
    private static BlockMetaBuilder<Void,BinaryExpression> builder =
            new BlockMetaBuilder<Void,BinaryExpression>()
                    .description("binary expression")
                    .sub("expression")
                        .data(BinaryExpression::getExpressionLeft)
                        .and()
                    .sub("binary_operator")
                        .data(BinaryExpression::getOperator)
                        .and()
                    .sub("expression")
                        .data(BinaryExpression::getExpressionRight)
                        .and();
    // @formatter:on

    public static BlockMeta meta() {
        return builder.build();
    }

    @Override
    public MetaContextBlock convert(BinaryExpression context) {
        return MetaContextBlockBuilder
                .meta(meta())
                .build(context);
    }

}
