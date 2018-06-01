package com.xy.xsql.tsql.model.functions.datetime;

import com.xy.xsql.tsql.model.elements.expressions.Expression;
import com.xy.xsql.tsql.model.functions.Function;

/**
 * Created by xiaoyao9184 on 2018/5/29.
 */
public class TimeFromParts
        implements DateTimeFunction, Function.InternalFunction {

    private Expression hour;
    private Expression minute;
    private Expression seconds;
    private Expression fractions;
    private Expression precision;

    public Expression getHour() {
        return hour;
    }

    public void setHour(Expression hour) {
        this.hour = hour;
    }

    public Expression getMinute() {
        return minute;
    }

    public void setMinute(Expression minute) {
        this.minute = minute;
    }

    public Expression getSeconds() {
        return seconds;
    }

    public void setSeconds(Expression seconds) {
        this.seconds = seconds;
    }

    public Expression getFractions() {
        return fractions;
    }

    public void setFractions(Expression fractions) {
        this.fractions = fractions;
    }

    public Expression getPrecision() {
        return precision;
    }

    public void setPrecision(Expression precision) {
        this.precision = precision;
    }
}
