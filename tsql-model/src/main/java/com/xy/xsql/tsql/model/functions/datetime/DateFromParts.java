package com.xy.xsql.tsql.model.functions.datetime;

import com.xy.xsql.tsql.model.elements.expressions.Expression;
import com.xy.xsql.tsql.model.functions.Function;

/**
 * Created by xiaoyao9184 on 2018/5/29.
 */
public class DateFromParts
        implements DateTimeFunction, Function.InternalFunction {

    private Expression year;
    private Expression month;
    private Expression day;

    public Expression getYear() {
        return year;
    }

    public void setYear(Expression year) {
        this.year = year;
    }

    public Expression getMonth() {
        return month;
    }

    public void setMonth(Expression month) {
        this.month = month;
    }

    public Expression getDay() {
        return day;
    }

    public void setDay(Expression day) {
        this.day = day;
    }
}
