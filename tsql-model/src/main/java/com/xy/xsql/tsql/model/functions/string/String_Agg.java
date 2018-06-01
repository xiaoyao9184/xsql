package com.xy.xsql.tsql.model.functions.string;

import com.xy.xsql.tsql.model.elements.expressions.Expression;
import com.xy.xsql.tsql.model.functions.Function;
import com.xy.xsql.tsql.model.queries.select.OrderBy;

/**
 * Created by xiaoyao9184 on 2018/5/29.
 */
public class String_Agg
        implements StringFunction, Function.InternalFunction {

    private Expression expression;
    private Expression separator;
    private OrderBy.Item orderBy;

    public Expression getExpression() {
        return expression;
    }

    public void setExpression(Expression expression) {
        this.expression = expression;
    }

    public Expression getSeparator() {
        return separator;
    }

    public void setSeparator(Expression separator) {
        this.separator = separator;
    }

    public OrderBy.Item getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(OrderBy.Item orderBy) {
        this.orderBy = orderBy;
    }
}
