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
public class GroupExpressionConverter {

    public static class ExpressionConverter
            implements ReferenceBlockConverter<GroupExpression> {

        // @formatter:off
        private static ReferenceBlockBuilder<Void,GroupExpression> builder =
                new ReferenceBlockBuilder<Void,GroupExpression>()
                        .description("( expression )")
                        .sub_keyword(Other.GROUP_START)
                        .sub("expression")
                            .data(GroupExpression::getExpression)
                            .and()
                        .sub_keyword(Other.GROUP_END);
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

    public static class ScalarSubqueryConverter
            implements ReferenceBlockConverter<GroupExpression> {

        // @formatter:off
        private static ReferenceBlockBuilder<Void,GroupExpression> builder =
                new ReferenceBlockBuilder<Void,GroupExpression>()
                        .description("( scalar_subquery )")
                        .sub_keyword(Other.GROUP_START)
                        .sub("scalar_subquery")
                            .data(GroupExpression::getStatement)
                            .and()
                        .sub_keyword(Other.GROUP_END);
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



}
