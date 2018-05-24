package com.xy.xsql.tsql.builder.chain.elements.expressions;

import com.xy.xsql.core.builder.CodeTreeBuilder;
import com.xy.xsql.tsql.model.elements.expressions.Expression;
import com.xy.xsql.tsql.model.elements.expressions.UnaryExpression;
import com.xy.xsql.tsql.model.elements.operators.Unary;

/**
 * UnaryExpressionBuilder
 * Created by xiaoyao9184 on 2018/5/17.
 */
@SuppressWarnings("WeakerAccess")
public class UnaryExpressionBuilder<ParentBuilder>
        extends CodeTreeBuilder<UnaryExpressionBuilder<ParentBuilder>,ParentBuilder,UnaryExpression> {

    public UnaryExpressionBuilder() {
        super(new UnaryExpression());
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
