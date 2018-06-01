package com.xy.xsql.tsql.model.functions.mathematical;

import com.xy.xsql.tsql.model.elements.expressions.Expression;
import com.xy.xsql.tsql.model.functions.Function;

/**
 * Created by xiaoyao9184 on 2018/5/29.
 */
public class ASin
        implements MathematicalFunction.FloatExpressionParam, Function.InternalFunction {

    private Expression floatExpression;

    public Expression getFloatExpression() {
        return floatExpression;
    }

    public void setFloatExpression(Expression floatExpression) {
        this.floatExpression = floatExpression;
    }
}
