package com.xy.xsql.tsql.model.functions.mathematical;

import com.xy.xsql.tsql.model.elements.expressions.Expression;
import com.xy.xsql.tsql.model.functions.Function;


/**
 * Created by xiaoyao9184 on 2018/5/29.
 */
public class ATn2
        implements MathematicalFunction, Function.InternalFunction {

    private Expression floatExpression;
    private Expression floatExpression2;

    public Expression getFloatExpression() {
        return floatExpression;
    }

    public void setFloatExpression(Expression floatExpression) {
        this.floatExpression = floatExpression;
    }

    public Expression getFloatExpression2() {
        return floatExpression2;
    }

    public void setFloatExpression2(Expression floatExpression2) {
        this.floatExpression2 = floatExpression2;
    }
}
