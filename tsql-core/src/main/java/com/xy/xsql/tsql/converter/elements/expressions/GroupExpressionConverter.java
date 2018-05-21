package com.xy.xsql.tsql.converter.elements.expressions;

import com.xy.xsql.block.meta.BlockMetaBuilder;
import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.elements.Other;
import com.xy.xsql.tsql.model.elements.expressions.GroupExpression;

/**
 * Created by xiaoyao9184 on 2017/6/15.
 */
public class GroupExpressionConverter
        implements ModelMetaBlockConverter<GroupExpression> {

    // @formatter:off
    public static BlockMeta meta =
            new BlockMetaBuilder<Void,GroupExpression>()
                    .description("(expression)/(scalar_subquery)")
                    .sub()
                        .czse(d -> d.getExpression() != null,"expression")
                            .ref(ExpressionConverter.meta)
                            .scope(d -> d)
                            .and()
                        .czse(d -> d.getStatement() != null, "scalar_subquery")
                            .ref(ScalarSubqueryConverter.meta)
                            .scope(d -> d)
                            .and()
                        .and()
                    .build();
    // @formatter:on

    @Override
    public BlockMeta meta() {
        return meta;
    }


    public static class ExpressionConverter {

        // @formatter:off
        public static BlockMeta meta =
                new BlockMetaBuilder<Void,GroupExpression>()
                        .description("( expression )")
                        .sub_keyword(Other.GROUP_START)
                        .sub("expression")
                            .scope(GroupExpression::getExpression)
                            .format_indentation_right()
                            .and()
                        .sub_keyword(Other.GROUP_END)
                        .build();
        // @formatter:on

    }

    public static class ScalarSubqueryConverter {

        // @formatter:off
        public static BlockMeta meta =
                new BlockMetaBuilder<Void,GroupExpression>()
                        .description("( scalar_subquery )")
                        .sub_keyword(Other.GROUP_START)
                        .sub("scalar_subquery")
                            .scope(GroupExpression::getStatement)
                            .format_indentation_right()
                            .and()
                        .sub_keyword(Other.GROUP_END)
                        .build();
        // @formatter:on

    }

}
