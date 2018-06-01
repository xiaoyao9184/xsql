package com.xy.xsql.tsql.model.functions.string;

import com.xy.xsql.tsql.model.elements.expressions.Expression;
import com.xy.xsql.tsql.model.functions.Function;

/**
 * Created by xiaoyao9184 on 2018/5/29.
 */
public class Reverse
        implements StringFunction, Function.InternalFunction {

    private Expression stringExpression;

    public Expression getStringExpression() {
        return stringExpression;
    }

    public void setStringExpression(Expression stringExpression) {
        this.stringExpression = stringExpression;
    }

}
