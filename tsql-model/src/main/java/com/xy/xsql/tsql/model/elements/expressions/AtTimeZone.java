package com.xy.xsql.tsql.model.elements.expressions;

import com.xy.xsql.tsql.model.datatypes.constants.StringConstant;

/**
 * https://msdn.microsoft.com/en-us/library/mt612795.aspx
 *
 inputdate AT TIME ZONE timezone

 *
 * Created by xiaoyao9184 on 2017/3/16.
 */
public class AtTimeZone implements Expression {
    //inputdate
    private Expression inputExpression;
    //timezone
    private StringConstant timezone;

    public Expression getInputExpression() {
        return inputExpression;
    }

    public void setInputExpression(Expression inputExpression) {
        this.inputExpression = inputExpression;
    }

    public StringConstant getTimezone() {
        return timezone;
    }

    public void setTimezone(StringConstant timezone) {
        this.timezone = timezone;
    }


    public AtTimeZone withInputExpression(Expression inputExpression) {
        this.inputExpression = inputExpression;
        return this;
    }

    public AtTimeZone withTimezone(StringConstant timezone) {
        this.timezone = timezone;
        return this;
    }

}
