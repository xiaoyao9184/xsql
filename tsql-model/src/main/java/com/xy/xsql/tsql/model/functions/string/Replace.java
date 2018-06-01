package com.xy.xsql.tsql.model.functions.string;

import com.xy.xsql.tsql.model.elements.expressions.Expression;
import com.xy.xsql.tsql.model.functions.Function;

/**
 * Created by xiaoyao9184 on 2018/5/29.
 */
public class Replace
        implements StringFunction, Function.InternalFunction {

    private Expression stringExpression;
    private Expression stringPattern;
    private Expression stringReplacement;

    public Expression getStringExpression() {
        return stringExpression;
    }

    public void setStringExpression(Expression stringExpression) {
        this.stringExpression = stringExpression;
    }

    public Expression getStringPattern() {
        return stringPattern;
    }

    public void setStringPattern(Expression stringPattern) {
        this.stringPattern = stringPattern;
    }

    public Expression getStringReplacement() {
        return stringReplacement;
    }

    public void setStringReplacement(Expression stringReplacement) {
        this.stringReplacement = stringReplacement;
    }
}
