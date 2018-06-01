package com.xy.xsql.tsql.model.functions.aggregate;

import com.xy.xsql.tsql.model.elements.expressions.Expression;
import com.xy.xsql.tsql.model.functions.Function;

/**
 * Created by xiaoyao9184 on 2018/5/29.
 */
public class CheckSum_Agg
        implements AggregateFunction, Function.InternalFunction {

    // [ ALL | DISTINCT ]
    private boolean useAll;
    private boolean useDistinct;

    //
    private Expression expression;

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
}
