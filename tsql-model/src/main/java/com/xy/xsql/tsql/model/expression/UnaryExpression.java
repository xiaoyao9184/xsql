package com.xy.xsql.tsql.model.expression;

import com.xy.xsql.tsql.model.operator.Unary;

/**
 * Created by xiaoyao9184 on 2018/5/17.
 */
public class UnaryExpression implements Expression {
    private Unary unary;
    private Expression expression;

    public Unary getUnary() {
        return unary;
    }

    public void setUnary(Unary unary) {
        this.unary = unary;
    }

    public Expression getExpression() {
        return expression;
    }

    public void setExpression(Expression expression) {
        this.expression = expression;
    }
}
