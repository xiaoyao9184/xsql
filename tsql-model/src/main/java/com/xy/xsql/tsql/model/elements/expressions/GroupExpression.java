package com.xy.xsql.tsql.model.elements.expressions;

import com.xy.xsql.tsql.model.statement.Statement;

/**
 * Created by xiaoyao9184 on 2017/3/12.
 */
public class GroupExpression implements Expression, ScalarExpression {
    private Statement statement;
    private Expression expression;

    public GroupExpression() {
    }

    public GroupExpression(Statement statement) {
        this.statement = statement;
    }

    public Statement getStatement() {
        return statement;
    }

    public void setStatement(Statement statement) {
        this.statement = statement;
    }

    public Expression getExpression() {
        return expression;
    }

    public void setExpression(Expression expression) {
        this.expression = expression;
    }
}
