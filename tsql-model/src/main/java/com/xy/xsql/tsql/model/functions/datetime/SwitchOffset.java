package com.xy.xsql.tsql.model.functions.datetime;

import com.xy.xsql.tsql.model.elements.expressions.Expression;
import com.xy.xsql.tsql.model.functions.Function;

/**
 * Created by xiaoyao9184 on 2018/5/29.
 */
public class SwitchOffset
        implements DateTimeFunction, Function.InternalFunction {

    private Expression datetimeOffset;
    private Expression timeZone;

    public Expression getDatetimeOffset() {
        return datetimeOffset;
    }

    public void setDatetimeOffset(Expression datetimeOffset) {
        this.datetimeOffset = datetimeOffset;
    }

    public Expression getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(Expression timeZone) {
        this.timeZone = timeZone;
    }
}
