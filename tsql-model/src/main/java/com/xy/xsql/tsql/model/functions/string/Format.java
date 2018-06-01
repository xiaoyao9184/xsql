package com.xy.xsql.tsql.model.functions.string;

import com.xy.xsql.tsql.model.elements.expressions.Expression;
import com.xy.xsql.tsql.model.functions.Function;

/**
 * Created by xiaoyao9184 on 2018/5/29.
 */
public class Format
        implements StringFunction, Function.InternalFunction {

    private Expression value;
    private Expression format;
    private Expression culture;

    public Expression getValue() {
        return value;
    }

    public void setValue(Expression value) {
        this.value = value;
    }

    public Expression getFormat() {
        return format;
    }

    public void setFormat(Expression format) {
        this.format = format;
    }

    public Expression getCulture() {
        return culture;
    }

    public void setCulture(Expression culture) {
        this.culture = culture;
    }
}
