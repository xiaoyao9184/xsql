package com.xy.xsql.tsql.model.functions.mathematical;

import com.xy.xsql.tsql.model.elements.expressions.Expression;
import com.xy.xsql.tsql.model.functions.Function;

/**
 * Created by xiaoyao9184 on 2018/5/29.
 */
public class Round
        implements MathematicalFunction, Function.InternalFunction {

    private Expression numericExpression;
    private Expression length;
    private Function function;

    public Expression getNumericExpression() {
        return numericExpression;
    }

    public void setNumericExpression(Expression numericExpression) {
        this.numericExpression = numericExpression;
    }

    public Expression getLength() {
        return length;
    }

    public void setLength(Expression length) {
        this.length = length;
    }

    public Function getFunction() {
        return function;
    }

    public void setFunction(Function function) {
        this.function = function;
    }
}
