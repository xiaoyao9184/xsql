package com.xy.xsql.tsql.model.functions.string;

import com.xy.xsql.tsql.model.elements.expressions.Expression;
import com.xy.xsql.tsql.model.functions.Function;

/**
 * Created by xiaoyao9184 on 2018/5/29.
 */
public class SubString
        implements StringFunction, Function.InternalFunction {

    private Expression expression;
    private Expression start;
    private Expression length;

    public Expression getExpression() {
        return expression;
    }

    public void setExpression(Expression expression) {
        this.expression = expression;
    }

    public Expression getStart() {
        return start;
    }

    public void setStart(Expression start) {
        this.start = start;
    }

    public Expression getLength() {
        return length;
    }

    public void setLength(Expression length) {
        this.length = length;
    }
}
