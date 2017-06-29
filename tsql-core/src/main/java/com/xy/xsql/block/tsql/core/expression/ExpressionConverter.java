package com.xy.xsql.block.tsql.core.expression;

import com.xy.xsql.block.core.ReferenceBlockBuilder;
import com.xy.xsql.block.core.ReferenceBlockConverter;
import com.xy.xsql.block.model.ReferenceBlock;
import com.xy.xsql.tsql.model.datatype.StringConstant;
import com.xy.xsql.tsql.model.element.ColumnName;
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
                    .czse(d -> d instanceof StringConstant,"constant")
                        .data(d -> d)
                        .and()
//                    .czse(d -> d instanceof ,"scalar_function")
//                        .and()
                    .czse(d -> d instanceof ColumnName,"column")
                        .data(d -> d)
                        .and()
                    .czse(d -> d instanceof LocalVariable,"variable")
                        .and()
//                    .czse(d -> d instanceof ,"( expression )")
//                        .and()
//                    .czse(d -> d instanceof ,"{ unary_operator } expression")
//                        .and()
                    .czse(d -> d instanceof GroupExpression)
                        .description("expression { binary_operator } expression")
                        .sub_meta(GroupExpressionConverter.meta())
                        .data(d -> d)
                        .and();
    // @formatter:on


    public static ReferenceBlock meta() {
        return builder.build();
    }

    @Override
    public ReferenceBlock convert(Expression expression) {
        return builder
                .data(expression)
                .build();
    }

}
