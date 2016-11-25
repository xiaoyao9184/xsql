package com.xy.xsql.orm.data.sql.expression;

import com.xy.xsql.orm.data.sql.Element;
import com.xy.xsql.orm.data.sql.Expression;
import com.xy.xsql.orm.data.sql.element.GrammarEnum;
import com.xy.xsql.orm.util.ListBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * CASE
 * WHEN conditional THEN expression
 * [WHEN conditional THEN expression]...
 * [ELSE expression]
 * END
 *
 * CASE expression
 * WHEN conditional THEN expression
 * [WHEN conditional THEN expression]...
 * [ELSE expression]
 * END
 *
 * Created by xiaoyao9184 on 2016/11/12.
 */
public class Case implements Expression {
    private Expression caseExpression;
    private List<WhenThenExpression> whenThenExpressionList;
    private Expression elseExpression;

    @Override
    public List<Element> toElementList() {
        List<Element> result = new ListBuilder<Element>()
                .withItem(GrammarEnum.CASE)
                .withItem(caseExpression).build(null);
        for (WhenThenExpression whenThenExpression: whenThenExpressionList) {
            result.addAll(whenThenExpression.toElementList());
        }
        if(elseExpression != null){
            result.add(GrammarEnum.ELSE);
            result.add(elseExpression);
        }
        result.add(GrammarEnum.END);
        return result;
    }

    public Case withCaseExpression(Expression caseExpression) {
        this.caseExpression = caseExpression;
        return this;
    }

    public Case withWhenThenExpressionList(WhenThenExpression whenThenExpressionList) {
        if(this.whenThenExpressionList == null){
            this.whenThenExpressionList = new ArrayList<>();
        }
        this.whenThenExpressionList.add(whenThenExpressionList);
        return this;
    }

    public Case withElseExpression(Expression elseExpression) {
        this.elseExpression = elseExpression;
        return this;
    }


    /**
     * WHEN conditional THEN expression
     *
     */
    public static class WhenThenExpression implements Expression  {
        private Expression whenElement;
        private Expression thenElement;

        @Override
        public List<Element> toElementList() {
            List<Element> result = new ArrayList<>();
            result.add(GrammarEnum.WHEN);
            result.add(whenElement);
            result.add(GrammarEnum.THEN);
            result.add(thenElement);
            return result;
        }

        public WhenThenExpression withWhenElment(Expression whenElment) {
            this.whenElement = whenElment;
            return this;
        }

        public WhenThenExpression withThenElment(Expression thenElment) {
            this.thenElement = thenElment;
            return this;
        }
    }

}
