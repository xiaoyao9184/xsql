package com.xy.xsql.tsql.model.functions.aggregate;

import com.xy.xsql.tsql.model.elements.expressions.Expression;
import com.xy.xsql.tsql.model.functions.Function;

/**
 * Created by xiaoyao9184 on 2018/5/29.
 */
public class Grouping
        implements AggregateFunction, Function.InternalFunction {
    //
    private Expression columnExpression;

    public Expression getColumnExpression() {
        return columnExpression;
    }

    public void setColumnExpression(Expression columnExpression) {
        this.columnExpression = columnExpression;
    }
}
