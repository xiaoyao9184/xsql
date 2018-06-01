package com.xy.xsql.tsql.model.functions.string;

import com.xy.xsql.tsql.model.elements.expressions.Expression;
import com.xy.xsql.tsql.model.functions.Function;

/**
 * Created by xiaoyao9184 on 2018/5/29.
 */
public class Unicode
        implements StringFunction, Function.InternalFunction {

    private Expression ncharacterExpression;

    public Expression getNcharacterExpression() {
        return ncharacterExpression;
    }

    public void setNcharacterExpression(Expression ncharacterExpression) {
        this.ncharacterExpression = ncharacterExpression;
    }
}
