package com.xy.xsql.tsql.model.functions.datetime;

import com.xy.xsql.tsql.model.elements.expressions.Expression;
import com.xy.xsql.tsql.model.functions.Function;

/**
 * Created by xiaoyao9184 on 2018/5/29.
 */
public class EoMonth
        implements DateTimeFunction, Function.InternalFunction {

    private Expression startDate;
    private Expression monthToAdd;

    public Expression getStartDate() {
        return startDate;
    }

    public void setStartDate(Expression startDate) {
        this.startDate = startDate;
    }

    public Expression getMonthToAdd() {
        return monthToAdd;
    }

    public void setMonthToAdd(Expression monthToAdd) {
        this.monthToAdd = monthToAdd;
    }
}
