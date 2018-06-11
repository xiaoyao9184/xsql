package com.xy.xsql.tsql.model.functions.analytic;

import com.xy.xsql.tsql.model.elements.expressions.Expression;
import com.xy.xsql.tsql.model.functions.Function;
import com.xy.xsql.tsql.model.queries.select.Over;

/**
 * Created by xiaoyao9184 on 2018/5/29.
 */
public class First_Value
        implements AnalyticFunction, Function.InternalFunction {

    private Expression scalarExpression;
    private Over over;

    public Expression getScalarExpression() {
        return scalarExpression;
    }

    public void setScalarExpression(Expression scalarExpression) {
        this.scalarExpression = scalarExpression;
    }

    public Over getOver() {
        return over;
    }

    public void setOver(Over over) {
        this.over = over;
    }
}
