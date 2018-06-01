package com.xy.xsql.tsql.model.functions.analytic;

import com.xy.xsql.tsql.model.elements.expressions.ScalarExpression;
import com.xy.xsql.tsql.model.functions.Function;
import com.xy.xsql.tsql.model.queries.select.Over;

/**
 * Created by xiaoyao9184 on 2018/5/29.
 */
public class Last_Value
        implements AnalyticFunction, Function.InternalFunction {

    private ScalarExpression scalarExpression;
    private Over over;

    public ScalarExpression getScalarExpression() {
        return scalarExpression;
    }

    public void setScalarExpression(ScalarExpression scalarExpression) {
        this.scalarExpression = scalarExpression;
    }

    public Over getOver() {
        return over;
    }

    public void setOver(Over over) {
        this.over = over;
    }
}
