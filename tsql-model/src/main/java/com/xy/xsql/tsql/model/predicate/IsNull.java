package com.xy.xsql.tsql.model.predicate;

import com.xy.xsql.tsql.model.expression.Expression;

/**
 * expression IS [ NOT ] NULL
 * Created by xiaoyao9184 on 2017/3/12.
 */
public class IsNull implements Predicate, Expression {
    //expression IS [ NOT ] NULL
    private Expression expression;
    private boolean useNotOperator;

    public Expression getExpression() {
        return expression;
    }

    public void setExpression(Expression expression) {
        this.expression = expression;
    }

    public boolean isUseNotOperator() {
        return useNotOperator;
    }

    public void setUseNotOperator(boolean useNotOperator) {
        this.useNotOperator = useNotOperator;
    }

}
