package com.xy.xsql.block.tsql.core.expression;

import com.xy.xsql.block.core.ReferenceBlockBuilder;
import com.xy.xsql.block.core.ReferenceBlockConverter;
import com.xy.xsql.block.model.ReferenceBlock;
import com.xy.xsql.tsql.model.expression.BinaryExpression;
import com.xy.xsql.tsql.model.expression.GroupExpression;

/**
 * Created by xiaoyao9184 on 2017/6/29.
 */
public class BinaryExpressionConverter
        implements ReferenceBlockConverter<BinaryExpression> {

    // @formatter:off
    private static ReferenceBlockBuilder<Void,BinaryExpression> builder =
            new ReferenceBlockBuilder<Void,BinaryExpression>()
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


    public static ReferenceBlock meta() {
        return builder.build();
    }

    @Override
    public ReferenceBlock convert(BinaryExpression binaryExpression) {
        return builder
                .data(binaryExpression)
                .build();
    }

}
