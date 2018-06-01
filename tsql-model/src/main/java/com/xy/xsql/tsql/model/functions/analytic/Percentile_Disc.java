package com.xy.xsql.tsql.model.functions.analytic;

import com.xy.xsql.tsql.model.elements.expressions.ScalarExpression;
import com.xy.xsql.tsql.model.functions.Function;
import com.xy.xsql.tsql.model.queries.select.OrderBy;
import com.xy.xsql.tsql.model.queries.select.Over;

/**
 * Created by xiaoyao9184 on 2018/5/29.
 */
public class Percentile_Disc
        implements AnalyticFunction, Function.InternalFunction {

    private ScalarExpression numericLiteral;
    //WITHIN GROUP ( ORDER BY order_by_expression [ ASC | DESC ] )
    private OrderBy.Item orderBy;
    //OVER ( [ <partition_by_clause> ] )
    private Over over;

    public ScalarExpression getNumericLiteral() {
        return numericLiteral;
    }

    public void setNumericLiteral(ScalarExpression numericLiteral) {
        this.numericLiteral = numericLiteral;
    }

    public OrderBy.Item getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(OrderBy.Item orderBy) {
        this.orderBy = orderBy;
    }

    public Over getOver() {
        return over;
    }

    public void setOver(Over over) {
        this.over = over;
    }
}
