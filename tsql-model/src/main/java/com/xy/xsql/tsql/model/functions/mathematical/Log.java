package com.xy.xsql.tsql.model.functions.mathematical;

import com.xy.xsql.tsql.model.elements.expressions.Expression;
import com.xy.xsql.tsql.model.functions.Function;

/**
 * Created by xiaoyao9184 on 2018/5/29.
 */
public class Log
        implements MathematicalFunction, Function.InternalFunction {

    private Expression floatExpression;
    private Integer base;

    public Expression getFloatExpression() {
        return floatExpression;
    }

    public void setFloatExpression(Expression floatExpression) {
        this.floatExpression = floatExpression;
    }

    public Integer getBase() {
        return base;
    }

    public void setBase(Integer base) {
        this.base = base;
    }
}
