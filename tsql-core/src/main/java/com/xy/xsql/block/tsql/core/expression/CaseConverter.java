package com.xy.xsql.block.tsql.core.expression;

import com.xy.xsql.block.core.BlockConverter;
import com.xy.xsql.block.core.ReferenceBlockBuilder;
import com.xy.xsql.block.model.ReferenceBlock;
import com.xy.xsql.block.model.Block;
import com.xy.xsql.tsql.model.Keywords;
import com.xy.xsql.tsql.model.expression.Case;

/**
 * Created by xiaoyao9184 on 2017/6/15.
 */
public class CaseConverter
        implements BlockConverter<Case> {

    // @formatter:off
    private static ReferenceBlockBuilder<Void,Case> builder =
            new ReferenceBlockBuilder<Void,Case>()
                    .overall("CASE")
                    .sub_keyword(Keywords.CASE)
                    .sub("input_expression")
                        .data(Case::getInputExpression)
                        .and()
                    .sub()
                        .description("WHEN when_expression THEN result_expression [ ...n ]")
                        .repeat(CaseWhenThenExpressionRenderer.meta())
                        .data(Case::getWhenThenExpressionList)
                        .and()
                    .sub()
                        .description("[ ELSE else_result_expression ]")
                        .optional(c -> c.getElseResultExpression() != null)
                        .sub_keyword(Keywords.ELSE)
                        .sub("else_result_expression")
                            .data(Case::getElseResultExpression)
                            .and()
                        .and()
                    .sub_keyword(Keywords.END);
    // @formatter:on

    public static ReferenceBlock meta() {
        return builder.build();
    }

    @Override
    public Block convert(Case aCase) {
        return builder
                .data(aCase)
                .build();
    }


    public static class CaseWhenThenExpressionRenderer
            implements BlockConverter<Case.WhenThenExpression> {

        // @formatter:off
        private static ReferenceBlockBuilder<Void,Case.WhenThenExpression> builder =
                new ReferenceBlockBuilder<Void,Case.WhenThenExpression>()
                        .sub_keyword(Keywords.WHEN)
                        .sub("when_expression")
                            .data(Case.WhenThenExpression::getWhenExpression)
                            .and()
                        .sub_keyword(Keywords.THEN)
                        .sub("result_expression")
                            .data(Case.WhenThenExpression::getResultExpression)
                            .and();
        // @formatter:on

        public static ReferenceBlock meta() {
            return builder.build();
        }

        @Override
        public Block convert(Case.WhenThenExpression whenThenExpression) {
            return builder
                    .data(whenThenExpression)
                    .build();
        }
    }

}
