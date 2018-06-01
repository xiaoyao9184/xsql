package com.xy.xsql.tsql.model.functions.aggregate;

import com.xy.xsql.tsql.model.elements.expressions.Expression;
import com.xy.xsql.tsql.model.functions.Function;
import com.xy.xsql.tsql.model.queries.select.Over;

/**
 * Created by xiaoyao9184 on 2018/5/29.
 */
public class Count_Big
        implements AggregateFunction, Function.InternalFunction {

    // [ ALL | DISTINCT ]
    private boolean useAll;
    private boolean useDistinct;

    //
    private Expression expression;
    private boolean useAllCount;

    //[ OVER ( [ partition_by_clause ] [ order_by_clause ] ) ]
    private Over.PartitionBy partitionBy;
    private Over.OrderBy orderBy;

    public boolean isUseAll() {
        return useAll;
    }

    public void setUseAll(boolean useAll) {
        this.useAll = useAll;
    }

    public boolean isUseDistinct() {
        return useDistinct;
    }

    public void setUseDistinct(boolean useDistinct) {
        this.useDistinct = useDistinct;
    }

    public Expression getExpression() {
        return expression;
    }

    public void setExpression(Expression expression) {
        this.expression = expression;
    }

    public boolean isUseAllCount() {
        return useAllCount;
    }

    public void setUseAllCount(boolean useAllCount) {
        this.useAllCount = useAllCount;
    }

    public Over.PartitionBy getPartitionBy() {
        return partitionBy;
    }

    public void setPartitionBy(Over.PartitionBy partitionBy) {
        this.partitionBy = partitionBy;
    }

    public Over.OrderBy getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(Over.OrderBy orderBy) {
        this.orderBy = orderBy;
    }
}
