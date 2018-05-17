package com.xy.xsql.tsql.core.expression;

import com.xy.xsql.core.builder.CodeTreeBuilder;
import com.xy.xsql.tsql.model.expression.Expression;
import com.xy.xsql.tsql.model.expression.UnaryExpression;
import com.xy.xsql.tsql.model.operator.Unary;

/**
 * Created by xiaoyao9184 on 2018/5/17.
 */
public class UnaryExpressionBuilder<ParentBuilder>
        extends CodeTreeBuilder<UnaryExpressionBuilder<ParentBuilder>,ParentBuilder,UnaryExpression> {

    public UnaryExpressionBuilder() {
        super(new UnaryExpression());
    }

    public UnaryExpressionBuilder<ParentBuilder> withExpression(Expression expression){
        target.setExpression(expression);
        return this;
    }

    public UnaryExpressionBuilder<ParentBuilder> withUnary(Unary unary){
        target.setUnary(unary);
        return this;
    }

}
