package com.xy.xsql.tsql.model.queries.predicates;

import com.xy.xsql.tsql.model.elements.expressions.Expression;
import com.xy.xsql.tsql.model.queries.predicates.Predicate;

/**
 * expression [ NOT ] BETWEEN expression AND expression
 * Created by xiaoyao9184 on 2017/3/12.
 */
public class Between implements Predicate, Expression {
    //expression [ NOT ] BETWEEN expression AND expression
    private Expression expression;
    private boolean useNotOperator;
    private Expression startExpression;
    private Expression endExpression;

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

    public Expression getStartExpression() {
        return startExpression;
    }

    public void setStartExpression(Expression startExpression) {
        this.startExpression = startExpression;
    }

    public Expression getEndExpression() {
        return endExpression;
    }

    public void setEndExpression(Expression endExpression) {
        this.endExpression = endExpression;
    }

}
