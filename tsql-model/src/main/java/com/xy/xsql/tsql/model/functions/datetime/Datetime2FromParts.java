package com.xy.xsql.tsql.model.functions.datetime;

import com.xy.xsql.tsql.model.elements.expressions.Expression;
import com.xy.xsql.tsql.model.functions.Function;

/**
 * Created by xiaoyao9184 on 2018/5/29.
 */
public class Datetime2FromParts
        implements DateTimeFunction, Function.InternalFunction {

    private Expression year;
    private Expression month;
    private Expression day;
    private Expression hour;
    private Expression minute;
    private Expression seconds;
    private Expression fractions;
    private Expression precision;

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
