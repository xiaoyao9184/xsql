package com.xy.xsql.block.tsql.core.expression;

import com.xy.xsql.block.core.ReferenceBlockBuilder;
import com.xy.xsql.block.core.ReferenceBlockConverter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.datatype.Constant;
import com.xy.xsql.tsql.model.element.ColumnName;
import com.xy.xsql.tsql.model.expression.BinaryExpression;
import com.xy.xsql.tsql.model.expression.Expression;
import com.xy.xsql.tsql.model.expression.GroupExpression;
import com.xy.xsql.tsql.model.variable.LocalVariable;

/**
 * Created by xiaoyao9184 on 2017/6/15.
 */
public class ExpressionConverter
        implements ReferenceBlockConverter<Expression> {

    // @formatter:off
    private static ReferenceBlockBuilder<Void,Expression> builder =
            new ReferenceBlockBuilder<Void,Expression>()
                    .description("expression")
                    .czse(d -> d instanceof Constant,"constant")
                        .data(Object::toString)
                        .and()
//                    .czse(d -> d instanceof ,"scalar_function")
//                        .and()
                    .czse(d -> d instanceof ColumnName,"column")
                        .data(Object::toString)
                        .and()
                    .czse(d -> d instanceof LocalVariable,"variable")
                        .data(Object::toString)
                        .and()
                    .czse_meta(d -> d instanceof GroupExpression,GroupExpressionConverter.ExpressionConverter.meta())
//                    .czse(d -> d instanceof ,"{ unary_operator } expression")
//                        .and()
                    .czse_meta(d -> d instanceof BinaryExpression,BinaryExpressionConverter.meta());
    // @formatter:on


    public static BlockMeta meta() {
        return builder.build();
    }

    @Override
    public BlockMeta convert(Expression expression) {
        return builder
                .data(expression)
                .build();
    }

}
