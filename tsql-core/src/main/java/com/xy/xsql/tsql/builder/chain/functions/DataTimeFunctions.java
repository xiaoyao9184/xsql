package com.xy.xsql.tsql.builder.chain.functions;

import com.xy.xsql.tsql.model.elements.expressions.Expression;
import com.xy.xsql.tsql.model.functions.datetime.*;

/**
 * Created by xiaoyao9184 on 2018/6/1.
 */
public interface DataTimeFunctions {


    static $$Datefirst f_$$datefirst(){
        return new $$Datefirst();
    }
    static Current_Timestamp f_current_timestamp(){
        return new Current_Timestamp();
    }
    static DateAdd f_dateadd(
            Expression datepart,
            Expression number,
            Expression date){
        DateAdd f = new DateAdd();
        f.setDatepart(datepart);
        f.setNumber(number);
        f.setDate(date);
        return f;
    }
    static DateDiff f_datediff(
            Expression datepart,
            Expression startDate,
            Expression endDate){
        DateDiff f = new DateDiff();
        f.setDatepart(datepart);
        f.setStartDate(startDate);
        f.setEndDate(endDate);
        return f;
    }
    static DateDiff_Big f_datediff_big(
            Expression datepart,
            Expression startDate,
            Expression endDate){
        DateDiff_Big f = new DateDiff_Big();
        f.setDatepart(datepart);
        f.setStartDate(startDate);
        f.setEndDate(endDate);
        return f;
    }
    static DateFromParts f_datefromparts(
            Expression year,
            Expression month,
            Expression day){
        DateFromParts f = new DateFromParts();
        f.setYear(year);
        f.setMonth(month);
        f.setDay(day);
        return f;
    }
    static DateName f_datename(
            Expression datepart,
            Expression date){
        DateName f = new DateName();
        f.setDatepart(datepart);
        f.setDate(date);
        return f;
    }
    static DatePart f_datepart(
            Expression datepart,
            Expression date){
        DatePart f = new DatePart();
        f.setDatepart(datepart);
        f.setDate(date);
        return f;
    }
    static Datetime2FromParts f_datetime2fromparts(
            Expression year,
            Expression month,
            Expression day,
            Expression hour,
            Expression minute,
            Expression seconds,
            Expression fractions,
            Expression precision){
        Datetime2FromParts f = new Datetime2FromParts();
        f.setYear(year);
        f.setMonth(month);
        f.setDay(day);
        f.setHour(hour);
        f.setMinute(minute);
        f.setSeconds(seconds);
        f.setFractions(fractions);
        f.setPrecision(precision);
        return f;
    }
    static DatetimeFromParts f_datetimefromparts(
            Expression year,
            Expression month,
            Expression day,
            Expression hour,
            Expression minute,
            Expression seconds,
            Expression milliseconds){
        DatetimeFromParts f = new DatetimeFromParts();
        f.setYear(year);
        f.setMonth(month);
        f.setDay(day);
        f.setHour(hour);
        f.setMinute(minute);
        f.setSeconds(seconds);
        f.setMilliseconds(milliseconds);
        return f;
    }
    static DatetimeOffsetFromParts f_datetimeoffsetfromparts(
            Expression year,
            Expression month,
            Expression day,
            Expression hour,
            Expression minute,
            Expression seconds,
            Expression fractions,
            Expression hourOffset,
            Expression minuteOffset,
            Expression precision){
        DatetimeOffsetFromParts f = new DatetimeOffsetFromParts();
        f.setYear(year);
        f.setMonth(month);
        f.setDay(day);
        f.setHour(hour);
        f.setMinute(minute);
        f.setSeconds(seconds);
        f.setFractions(fractions);
        f.setHourOffset(hourOffset);
        f.setMinuteOffset(minuteOffset);
        f.setPrecision(precision);
        return f;
    }
    static Day f_day(
            Expression date){
        Day f = new Day();
        f.setDate(date);
        return f;
    }
    static EoMonth f_eomonth(
            Expression startDate,
            Expression monthToAdd){
        EoMonth f = new EoMonth();
        f.setStartDate(startDate);
        f.setMonthToAdd(monthToAdd);
        return f;
    }
    static EoMonth f_eomonth(
            Expression startDate){
        EoMonth f = new EoMonth();
        f.setStartDate(startDate);
        return f;
    }
    static GetDate f_getdate(){
        return new GetDate();
    }
    static GetUTCDate f_getutcdate(){
        return new GetUTCDate();
    }
    static IsDate f_isdate(
            Expression expression){
        IsDate f = new IsDate();
        f.setExpression(expression);
        return f;
    }
    static Month f_month(
            Expression date){
        Month f = new Month();
        f.setDate(date);
        return f;
    }
    static SmallDatetimeFromParts f_smalldatetimefromparts(
            Expression year,
            Expression month,
            Expression day,
            Expression hour,
            Expression minute,
            Expression seconds,
            Expression fractions,
            Expression hourOffset,
            Expression minuteOffset,
            Expression precision){
        SmallDatetimeFromParts f = new SmallDatetimeFromParts();
        f.setYear(year);
        f.setMonth(month);
        f.setDay(day);
        f.setHour(hour);
        f.setMinute(minute);
        return f;
    }
    static SwitchOffset f_switchoffset(
            Expression datetimeOffset,
            Expression timeZone){
        SwitchOffset f = new SwitchOffset();
        f.setDatetimeOffset(datetimeOffset);
        f.setTimeZone(timeZone);
        return f;
    }
    static SysDatetime f_sysdatetime(){
        return new SysDatetime();
    }
    static SysDatetimeOffset f_sysdatetimeoffset(){
        return new SysDatetimeOffset();
    }
    static SysUTCDatetime f_sysutcdatetime(){
        return new SysUTCDatetime();
    }
    static TimeFromParts f_timefromparts(
            Expression hour,
            Expression minute,
            Expression seconds,
            Expression fractions,
            Expression precision){
        TimeFromParts f = new TimeFromParts();
        f.setHour(hour);
        f.setMinute(minute);
        f.setSeconds(seconds);
        f.setFractions(fractions);
        f.setPrecision(precision);
        return f;
    }
    static ToDatetimeOffset f_todatetimeoffset(
            Expression expression,
            Expression timeZone){
        ToDatetimeOffset f = new ToDatetimeOffset();
        f.setExpression(expression);
        f.setTimeZone(timeZone);
        return f;
    }
    static Year f_year(
            Expression date){
        Year f = new Year();
        f.setDate(date);
        return f;
    }

}
