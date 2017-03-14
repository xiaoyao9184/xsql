package com.xy.xsql.orm.data.sql.element.predicate;


import com.xy.xsql.orm.core.element.ListElementBuilder;
import com.xy.xsql.orm.data.sql.Element;
import com.xy.xsql.orm.data.sql.Expression;
import com.xy.xsql.orm.data.sql.element.GrammarEnum;
import com.xy.xsql.orm.data.sql.element.OperatorEnum;

import java.util.List;

/**
 * expression [ NOT ] BETWEEN expression AND expression
 * Created by xiaoyao9184 on 2017/3/12.
 */
public class Between implements Predicate {
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
    public List<Element> toElementList() {
        ListElementBuilder b = new ListElementBuilder();
        b.append(expression)
                .append(useNotOperator ? GrammarEnum.NOT : null)
                .append(OperatorEnum.BETWEEN)
                .append(startExpression)
                .append(OperatorEnum.AND)
                .append(endExpression);
        return b.build();
    }
}
