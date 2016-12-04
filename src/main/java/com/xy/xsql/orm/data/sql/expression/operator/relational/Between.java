package com.xy.xsql.orm.data.sql.expression.operator.relational;

import com.xy.xsql.orm.data.sql.Element;
import com.xy.xsql.orm.data.sql.Expression;
import com.xy.xsql.orm.data.sql.element.GrammarEnum;
import com.xy.xsql.orm.core.element.ListElementBuilder;

import java.util.List;

/**
 * expression BETWEEN expression AND expression
 *
 * expression NOT BETWEEN expression AND expression
 *
 * Created by xiaoyao9184 on 2016/10/23.
 */
public class Between implements Expression {

    private Expression leftExpression;
    private boolean not = false;
    private Expression betweenExpressionStart;
    private Expression betweenExpressionEnd;


    public Between withNot(boolean not){
        this.not = not;
        return this;
    }

    public Between withLeftExpression(Expression leftExpression){
        this.leftExpression = leftExpression;
        return this;
    }

    public Between withBetweenExpressionStart(Expression betweenExpressionStart){
        this.betweenExpressionStart = betweenExpressionStart;
        return this;
    }

    public Between withBetweenExpressionEnd(Expression betweenExpressionStart){
        this.betweenExpressionStart = betweenExpressionStart;
        return this;
    }

    @Override
    public List<Element> toElementList() {
        return new ListElementBuilder()
                .append(leftExpression)
                .append(not ? GrammarEnum.NOT : null)
                .append(GrammarEnum.BETWEEN)
                .append(betweenExpressionStart)
                .append(GrammarEnum.AND)
                .append(betweenExpressionEnd)
                .build();
    }
}
