package com.xy.xsql.tsql.model.functions.string;

import com.xy.xsql.tsql.model.elements.expressions.Expression;
import com.xy.xsql.tsql.model.functions.Function;

/**
 * Created by xiaoyao9184 on 2018/5/29.
 */
public class String_Split
        implements StringFunction, Function.InternalFunction {

    private Expression string;
    private Expression separator;

    public Expression getString() {
        return string;
    }

    public void setString(Expression string) {
        this.string = string;
    }

    public Expression getSeparator() {
        return separator;
    }

    public void setSeparator(Expression separator) {
        this.separator = separator;
    }
}
