package com.xy.xsql.tsql.model.functions.logical;

import com.xy.xsql.tsql.model.elements.expressions.Expression;
import com.xy.xsql.tsql.model.functions.Function;

/**
 * Created by xiaoyao9184 on 2018/5/29.
 */
public class IIf
        implements LogicalFunction, Function.InternalFunction {

    private Expression booleanExpression;
    private Expression trueValue;
    private Expression falseValue;

    public Expression getBooleanExpression() {
        return booleanExpression;
    }

    public void setBooleanExpression(Expression booleanExpression) {
        this.booleanExpression = booleanExpression;
    }

    public Expression getTrueValue() {
        return trueValue;
    }

    public void setTrueValue(Expression trueValue) {
        this.trueValue = trueValue;
    }

    public Expression getFalseValue() {
        return falseValue;
    }

    public void setFalseValue(Expression falseValue) {
        this.falseValue = falseValue;
    }
}
