package com.xy.xsql.tsql.model.functions.datetime;

import com.xy.xsql.tsql.model.elements.expressions.Expression;
import com.xy.xsql.tsql.model.functions.Function;
import com.xy.xsql.tsql.model.functions.mathematical.Exp;

/**
 * Created by xiaoyao9184 on 2018/5/29.
 */
public class DateAdd
        implements DateTimeFunction, Function.InternalFunction {

    private DatePart.DatePartArgument datepart;
    private Expression number;
    private Expression date;

    public DatePart.DatePartArgument getDatepart() {
        return datepart;
    }

    public void setDatepart(DatePart.DatePartArgument datepart) {
        this.datepart = datepart;
    }

    public Expression getNumber() {
        return number;
    }

    public void setNumber(Expression number) {
        this.number = number;
    }

    public Expression getDate() {
        return date;
    }

    public void setDate(Expression date) {
        this.date = date;
    }

}
