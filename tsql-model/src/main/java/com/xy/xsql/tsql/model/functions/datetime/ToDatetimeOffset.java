package com.xy.xsql.tsql.model.functions.datetime;

import com.xy.xsql.tsql.model.elements.expressions.Expression;
import com.xy.xsql.tsql.model.functions.Function;

/**
 * Created by xiaoyao9184 on 2018/5/29.
 */
public class ToDatetimeOffset
        implements DateTimeFunction, Function.InternalFunction {

    private Expression expression;
    private Expression timeZone;

    public Expression getExpression() {
        return expression;
    }

    public void setExpression(Expression expression) {
        this.expression = expression;
    }

    public Expression getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(Expression timeZone) {
        this.timeZone = timeZone;
    }
}
