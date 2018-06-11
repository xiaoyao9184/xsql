package com.xy.xsql.tsql.model.functions.analytic;

import com.xy.xsql.tsql.model.elements.expressions.Expression;
import com.xy.xsql.tsql.model.elements.expressions.ScalarExpression;
import com.xy.xsql.tsql.model.functions.Function;
import com.xy.xsql.tsql.model.queries.select.Over;

/**
 * Created by xiaoyao9184 on 2018/5/29.
 */
public class Lead
        implements AnalyticFunction, Function.InternalFunction {

    private Expression scalarExpression;
    private Expression offset;
    private Expression defaultValue;
    private Over over;

    public Expression getScalarExpression() {
        return scalarExpression;
    }

    public void setScalarExpression(Expression scalarExpression) {
        this.scalarExpression = scalarExpression;
    }

    public Expression getOffset() {
        return offset;
    }

    public void setOffset(Expression offset) {
        this.offset = offset;
    }

    public Expression getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(Expression defaultValue) {
        this.defaultValue = defaultValue;
    }

    public Over getOver() {
        return over;
    }

    public void setOver(Over over) {
        this.over = over;
    }
}
