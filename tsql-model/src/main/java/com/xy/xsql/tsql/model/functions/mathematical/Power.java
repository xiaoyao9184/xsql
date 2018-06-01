package com.xy.xsql.tsql.model.functions.mathematical;

import com.xy.xsql.tsql.model.elements.expressions.Expression;
import com.xy.xsql.tsql.model.functions.Function;

/**
 * Created by xiaoyao9184 on 2018/5/29.
 */
public class Power
        implements MathematicalFunction, Function.InternalFunction {

    private Expression floatExpression;
    private Expression y;

    public Expression getFloatExpression() {
        return floatExpression;
    }

    public void setFloatExpression(Expression floatExpression) {
        this.floatExpression = floatExpression;
    }

    public Expression getY() {
        return y;
    }

    public void setY(Expression y) {
        this.y = y;
    }
}
