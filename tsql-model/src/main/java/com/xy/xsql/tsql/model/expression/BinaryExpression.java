package com.xy.xsql.tsql.model.expression;

import com.xy.xsql.tsql.model.operator.Operator;
import com.xy.xsql.tsql.model.statement.Statement;

/**
 * Created by xiaoyao9184 on 2017/3/12.
 */
public class BinaryExpression implements Expression {
    private Expression expressionLeft;
    private Operator operator;
    private Expression expressionRight;

    public BinaryExpression() {
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


    public BinaryExpression withExpressionLeft(Expression expression) {
        this.expressionLeft = expression;
        return this;
    }

    public BinaryExpression withOperator(Operator operator) {
        this.operator = operator;
        return this;
    }

    public BinaryExpression withExpressionRight(Expression expression) {
        this.expressionRight = expression;
        return this;
    }

}
