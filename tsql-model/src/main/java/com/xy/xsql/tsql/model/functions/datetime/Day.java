package com.xy.xsql.tsql.model.functions.datetime;

import com.xy.xsql.tsql.model.elements.expressions.Expression;
import com.xy.xsql.tsql.model.functions.Function;

/**
 * Created by xiaoyao9184 on 2018/5/29.
 */
public class Day
        implements DateTimeFunction, Function.InternalFunction {

    private Expression date;

    public Expression getDate() {
        return date;
    }

    public void setDate(Expression date) {
        this.date = date;
    }
}
