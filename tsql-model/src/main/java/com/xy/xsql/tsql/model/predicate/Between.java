package com.xy.xsql.tsql.model.predicate;

import com.xy.xsql.tsql.model.Block;
import com.xy.xsql.tsql.model.Keywords;
import com.xy.xsql.tsql.model.expression.Expression;
import com.xy.xsql.tsql.model.operator.Logical;
import com.xy.xsql.tsql.util.ListBlockBuilder;

import java.util.List;

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

    @Override
    public List<Block> toBlockList() {
        ListBlockBuilder b = new ListBlockBuilder();
        b.append(expression)
                .append(useNotOperator ? Keywords.NOT : null)
                .append(Logical.BETWEEN)
                .append(startExpression)
                .append(Keywords.AND)
                .append(endExpression);
        return b.build();
    }
}
