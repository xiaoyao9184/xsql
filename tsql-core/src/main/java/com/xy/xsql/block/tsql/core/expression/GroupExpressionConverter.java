package com.xy.xsql.block.tsql.core.expression;

import com.xy.xsql.block.core.ReferenceBlockBuilder;
import com.xy.xsql.block.core.ReferenceBlockConverter;
import com.xy.xsql.block.model.ReferenceBlock;
import com.xy.xsql.tsql.model.Keywords;
import com.xy.xsql.tsql.model.element.Other;
import com.xy.xsql.tsql.model.expression.GroupExpression;
import com.xy.xsql.tsql.model.expression.NullIf;

/**
 * Created by xiaoyao9184 on 2017/6/15.
 */
public class GroupExpressionConverter
        implements ReferenceBlockConverter<GroupExpression> {

    // @formatter:off
    private static ReferenceBlockBuilder<Void,GroupExpression> builder =
            new ReferenceBlockBuilder<Void,GroupExpression>()
                    .description("binary expression")
                    .czse(d -> d.getStatement() == null)
                        .description("expression { binary_operator } expression")
                        .sub("expression")
                            .data(GroupExpression::getExpressionLeft)
                            .and()
                        .sub("binary_operator")
                            .data(GroupExpression::getOperator)
                            .and()
                        .sub("expression")
                            .data(GroupExpression::getExpressionRight)
                            .and()
                        .and()
                    .czse(d -> d.getStatement() != null,"")
                        .data(GroupExpression::getStatement)
                        .and();
    // @formatter:on


    public static ReferenceBlock meta() {
        return builder.build();
    }

    @Override
    public ReferenceBlock convert(GroupExpression groupExpression) {
        return builder
                .data(groupExpression)
                .build();
    }

}
