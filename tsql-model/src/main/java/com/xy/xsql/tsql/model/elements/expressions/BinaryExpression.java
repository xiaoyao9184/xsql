package com.xy.xsql.tsql.model.elements.expressions;

import com.xy.xsql.tsql.model.operator.BinaryOperator;
import com.xy.xsql.tsql.model.operator.Operator;

/**
 * Created by xiaoyao9184 on 2017/3/12.
 */
public class BinaryExpression implements Expression, ScalarExpression {
    private Expression expressionLeft;
    private BinaryOperator binaryOperator;
    private Expression expressionRight;

    public BinaryExpression() {
    }

    public Expression getExpressionLeft() {
        return expressionLeft;
    }

    public void setExpressionLeft(Expression expressionLeft) {
        this.expressionLeft = expressionLeft;
    }

    public Operator getBinaryOperator() {
        return binaryOperator;
    }

    public void setBinaryOperator(BinaryOperator binaryOperator) {
        this.binaryOperator = binaryOperator;
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

    public BinaryExpression withOperator(BinaryOperator operator) {
        this.binaryOperator = operator;
        return this;
    }

    public BinaryExpression withExpressionRight(Expression expression) {
        this.expressionRight = expression;
        return this;
    }

}
