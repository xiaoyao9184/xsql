package com.xy.xsql.tsql.builder.chain.elements.expressions;

import com.xy.xsql.core.builder.parent.ParentHoldBuilder;
import com.xy.xsql.tsql.model.elements.expressions.Expression;
import com.xy.xsql.tsql.model.elements.expressions.UnaryExpression;
import com.xy.xsql.tsql.model.elements.operators.Unary;

/**
 * UnaryExpressionBuilder
 * Created by xiaoyao9184 on 2018/5/17.
 */
@SuppressWarnings({"WeakerAccess", "UnusedReturnValue", "unused"})
public class UnaryExpressionBuilder<ParentBuilder>
        extends ParentHoldBuilder<UnaryExpressionBuilder<ParentBuilder>,ParentBuilder,UnaryExpression> {

    public UnaryExpressionBuilder() {
        super(new UnaryExpression());
    }

    public UnaryExpressionBuilder(UnaryExpression target) {
        super(target);
    }

    /**
     * set
     * @param expression Expression
     * @return THIS
     */
    public UnaryExpressionBuilder<ParentBuilder> withExpression(Expression expression){
        target.setExpression(expression);
        return this;
    }

    /**
     * set
     * @param unary Unary
     * @return THIS
     */
    public UnaryExpressionBuilder<ParentBuilder> withUnary(Unary unary){
        target.setUnary(unary);
        return this;
    }

}
