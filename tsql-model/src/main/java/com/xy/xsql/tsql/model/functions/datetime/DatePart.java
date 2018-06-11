package com.xy.xsql.tsql.model.functions.datetime;

import com.xy.xsql.tsql.model.elements.expressions.Expression;
import com.xy.xsql.tsql.model.functions.Function;

/**
 * Created by xiaoyao9184 on 2018/5/29.
 */
public class DatePart
        implements DateTimeFunction, Function.InternalFunction {

    private DatePartArgument datepart;
    private Expression date;

    public DatePartArgument getDatepart() {
        return datepart;
    }

    public void setDatepart(DatePartArgument datepart) {
        this.datepart = datepart;
    }

    public Expression getDate() {
        return date;
    }

    public void setDate(Expression date) {
        this.date = date;
    }

    public enum DatePartArgument {
        year,	yy, yyyy,
        quarter,	qq, q,
        month,	mm, m,
        dayofyear,	dy, y,
        day,	dd, d,
        week,	wk, ww,
        weekday,	dw, w,
        hour,	hh,
        minute,	mi, n,
        second,	ss, s,
        millisecond,	ms,
        microsecond,	mcs,
        nanosecond,	ns;
    }
}
