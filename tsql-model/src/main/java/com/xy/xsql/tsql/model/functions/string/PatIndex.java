package com.xy.xsql.tsql.model.functions.string;

import com.xy.xsql.tsql.model.elements.expressions.Expression;
import com.xy.xsql.tsql.model.functions.Function;

/**
 * Created by xiaoyao9184 on 2018/5/29.
 */
public class PatIndex
        implements StringFunction, Function.InternalFunction {

    private Expression pattern;
    private Expression expression;

    public Expression getPattern() {
        return pattern;
    }

    public void setPattern(Expression pattern) {
        this.pattern = pattern;
    }

    public Expression getExpression() {
        return expression;
    }

    public void setExpression(Expression expression) {
        this.expression = expression;
    }
}
