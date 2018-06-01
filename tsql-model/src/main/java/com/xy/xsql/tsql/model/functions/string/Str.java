package com.xy.xsql.tsql.model.functions.string;

import com.xy.xsql.tsql.model.elements.expressions.Expression;
import com.xy.xsql.tsql.model.functions.Function;

/**
 * Created by xiaoyao9184 on 2018/5/29.
 */
public class Str
        implements StringFunction, Function.InternalFunction {

    private Expression floatExpression;
    private Integer length;
    private Integer decimal;

    public Expression getFloatExpression() {
        return floatExpression;
    }

    public void setFloatExpression(Expression floatExpression) {
        this.floatExpression = floatExpression;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public Integer getDecimal() {
        return decimal;
    }

    public void setDecimal(Integer decimal) {
        this.decimal = decimal;
    }
}
