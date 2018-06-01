package com.xy.xsql.tsql.model.functions.string;

import com.xy.xsql.tsql.model.elements.expressions.Expression;
import com.xy.xsql.tsql.model.functions.Function;

/**
 * Created by xiaoyao9184 on 2018/5/29.
 */
public class NChar
        implements StringFunction, Function.InternalFunction {

    private Expression integerExpression;

    public Expression getIntegerExpression() {
        return integerExpression;
    }

    public void setIntegerExpression(Expression integerExpression) {
        this.integerExpression = integerExpression;
    }
}
