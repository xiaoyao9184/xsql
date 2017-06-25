package com.xy.xsql.tsql.model.expression;

import com.xy.xsql.tsql.model.operator.Operator;
import com.xy.xsql.tsql.model.statement.Statement;

/**
 * Created by xiaoyao9184 on 2017/3/12.
 */
public class GroupExpression implements Expression {
    private Expression expressionLeft;
    private Operator operator;
    private Expression expressionRight;
    private Statement statement;

    public GroupExpression() {
    }

    public GroupExpression(Statement statement) {
        this.statement = statement;
    }

    public Expression getExpressionLeft() {
        return expressionLeft;
    }

    public void setExpressionLeft(Expression expressionLeft) {
        this.expressionLeft = expressionLeft;
    }

    public Operator getOperator() {
        return operator;
    }

    public void setOperator(Operator operator) {
        this.operator = operator;
    }

    public Expression getExpressionRight() {
        return expressionRight;
    }

    public void setExpressionRight(Expression expressionRight) {
        this.expressionRight = expressionRight;
    }

    public Statement getStatement() {
        return statement;
    }

    public void setStatement(Statement statement) {
        this.statement = statement;
    }



    public GroupExpression withExpressionLeft(Expression expression) {
        this.expressionLeft = expression;
        return this;
    }

    public GroupExpression withOperator(Operator operator) {
        this.operator = operator;
        return this;
    }

    public GroupExpression withExpressionRight(Expression expression) {
        this.expressionRight = expression;
        return this;
    }

}
