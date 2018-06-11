package com.xy.xsql.tsql.model.functions.datetime;

import com.xy.xsql.tsql.model.elements.expressions.Expression;
import com.xy.xsql.tsql.model.functions.Function;

/**
 * Created by xiaoyao9184 on 2018/5/29.
 */
public class DateName
        implements DateTimeFunction, Function.InternalFunction {

    private DatePart.DatePartArgument datepart;
    private Expression date;

    public DatePart.DatePartArgument getDatepart() {
        return datepart;
    }

    public void setDatepart(DatePart.DatePartArgument datepart) {
        this.datepart = datepart;
    }

    public Expression getDate() {
        return date;
    }

    public void setDate(Expression date) {
        this.date = date;
    }
}
