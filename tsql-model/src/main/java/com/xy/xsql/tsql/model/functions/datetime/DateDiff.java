package com.xy.xsql.tsql.model.functions.datetime;

import com.xy.xsql.tsql.model.elements.expressions.Expression;
import com.xy.xsql.tsql.model.functions.Function;

/**
 * Created by xiaoyao9184 on 2018/5/29.
 */
public class DateDiff
        implements DateTimeFunction, Function.InternalFunction {

    private DatePart.DatePartArgument datepart;
    private Expression startDate;
    private Expression endDate;

    public DatePart.DatePartArgument getDatepart() {
        return datepart;
    }

    public void setDatepart(DatePart.DatePartArgument datepart) {
        this.datepart = datepart;
    }

    public Expression getStartDate() {
        return startDate;
    }

    public void setStartDate(Expression startDate) {
        this.startDate = startDate;
    }

    public Expression getEndDate() {
        return endDate;
    }

    public void setEndDate(Expression endDate) {
        this.endDate = endDate;
    }
}
