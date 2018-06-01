package com.xy.xsql.tsql.model.functions.string;

import com.xy.xsql.tsql.model.elements.expressions.Expression;
import com.xy.xsql.tsql.model.functions.Function;

/**
 * Created by xiaoyao9184 on 2018/5/29.
 */
public class Replicate
        implements StringFunction, Function.InternalFunction {

    private Expression stringExpression;
    private Expression integerExpression;

    public Expression getStringExpression() {
        return stringExpression;
    }

    public void setStringExpression(Expression stringExpression) {
        this.stringExpression = stringExpression;
    }

    public Expression getIntegerExpression() {
        return integerExpression;
    }

    public void setIntegerExpression(Expression integerExpression) {
        this.integerExpression = integerExpression;
    }
}
