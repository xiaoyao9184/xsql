package com.xy.xsql.block.tsql.core.expression;

import com.xy.xsql.block.core.BlockMetaBuilder;
import com.xy.xsql.block.core.MetaContextBlockBuilder;
import com.xy.xsql.block.core.MetaContextBlockConverter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.block.model.MetaContextBlock;
import com.xy.xsql.tsql.model.element.Other;
import com.xy.xsql.tsql.model.expression.GroupExpression;

/**
 * Created by xiaoyao9184 on 2017/6/15.
 */
public class GroupExpressionConverter
        implements MetaContextBlockConverter<GroupExpression> {

    // @formatter:off
    private static BlockMetaBuilder<Void,GroupExpression> builder =
            new BlockMetaBuilder<Void,GroupExpression>()
                    .description("( expression ) | ( scalar_subquery )")
                    .sub()
                        .czse(d -> d.getExpression() != null,"expression")
                            .ref(ExpressionConverter.meta())
                            .data(d -> d)
                            .and()
                        .czse(d -> d.getStatement() != null, "scalar_subquery")
                            .ref(ScalarSubqueryConverter.meta())
                            .data(d -> d)
                            .and()
                        .and();
    // @formatter:on

    public static BlockMeta meta() {
        return builder.build();
    }

    @Override
    public MetaContextBlock convert(GroupExpression context) {
        return MetaContextBlockBuilder
                .meta(meta())
                .build(context);
    }


    public static class ExpressionConverter {

        // @formatter:off
        private static BlockMetaBuilder<Void,GroupExpression> builder =
                new BlockMetaBuilder<Void,GroupExpression>()
                        .description("( expression )")
                        .sub_keyword(Other.GROUP_START)
                        .sub("expression")
                            .data(GroupExpression::getExpression)
                            .and()
                        .sub_keyword(Other.GROUP_END);
        // @formatter:on


        public static BlockMeta meta() {
            return builder.build();
        }

    }

    public static class ScalarSubqueryConverter {

        // @formatter:off
        private static BlockMetaBuilder<Void,GroupExpression> builder =
                new BlockMetaBuilder<Void,GroupExpression>()
                        .description("( scalar_subquery )")
                        .sub_keyword(Other.GROUP_START)
                        .sub("scalar_subquery")
                            .data(GroupExpression::getStatement)
                            .and()
                        .sub_keyword(Other.GROUP_END);
        // @formatter:on


        public static BlockMeta meta() {
            return builder.build();
        }

    }

}
