package com.xy.xsql.tsql.model.expression;

/**
 * Created by xiaoyao9184 on 2018/5/17.
 */
public class NotExpression implements Expression {
    private Expression expression;

    public Expression getExpression() {
        return expression;
    }

    public void setExpression(Expression expression) {
        this.expression = expression;
    }
}
