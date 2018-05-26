package com.xy.xsql.tsql.builder.chain.elements.expressions;

import com.xy.xsql.core.builder.parent.ParentHoldBuilder;
import com.xy.xsql.tsql.model.elements.expressions.Case;
import com.xy.xsql.tsql.model.elements.expressions.Expression;

import java.util.List;

import static com.xy.xsql.core.handler.list.ListHandler.list;

/**
 * Created by xiaoyao9184 on 2017/3/10.
 */
@SuppressWarnings({"WeakerAccess", "UnusedReturnValue","unused"})
public class CaseBuilder<ParentBuilder>
        extends ParentHoldBuilder<CaseBuilder<ParentBuilder>,ParentBuilder,Case> {

    public CaseBuilder() {
        super(new Case());
    }

    public CaseBuilder(Case target) {
        super(target);
    }

    /**
     * set
     * @param inputExpression Expression
     * @return THIS
     */
    public CaseBuilder<ParentBuilder> withInput(Expression inputExpression) {
        target.withInputExpression(inputExpression);
        return this;
    }

    /**
     * set
     * @param whenExpression Expression
     * @param resulExpression Expression
     * @return THIS
     */
    public CaseBuilder<ParentBuilder> withWhen(Expression whenExpression, Expression resulExpression) {
        Case.WhenThenExpression whenThenExpression = new Case.WhenThenExpression()
                .withWhenExpression(whenExpression)
                .withThenExpression(resulExpression);
        list(target::getWhenThenExpressionList, target::setWhenThenExpressionList)
                .add(whenThenExpression);
        return this;
    }

    /**
     * set
     * @param whenThenExpressionList WhenThenExpression
     * @return THIS
     */
    public CaseBuilder<ParentBuilder> withWhen(List<Case.WhenThenExpression> whenThenExpressionList) {
        list(target::getWhenThenExpressionList, target::setWhenThenExpressionList)
                .addAll(whenThenExpressionList);
        return this;
    }

    /**
     * set
     * @param elseResultExpression Expression
     * @return THIS
     */
    public CaseBuilder<ParentBuilder> withElse(Expression elseResultExpression) {
        target.withElseResultExpression(elseResultExpression);
        return this;
    }




    /*
    Quick
     */

    /**
     * Quick set
     * @param expression Expression
     * @return THIS
     */
    public WhenThenExpressionBuilder<CaseBuilder<ParentBuilder>> $When(Expression expression){
        return new WhenThenExpressionBuilder<CaseBuilder<ParentBuilder>>
                (list(target::getWhenThenExpressionList, target::setWhenThenExpressionList)
                        .addNew(Case.WhenThenExpression::new))
                .in(this)
                .withWhen(expression);
    }

    /**
     * Quick set
     * @param elseResultExpression Expression
     * @return THIS
     */
    public CaseBuilder<ParentBuilder> $Else(Expression elseResultExpression) {
        return withElse(elseResultExpression);
    }


    /**
     * WhenThenExpressionBuilder
     * @param <ParentBuilder>
     */
    public static class WhenThenExpressionBuilder<ParentBuilder>
            extends ParentHoldBuilder<WhenThenExpressionBuilder<ParentBuilder>,ParentBuilder,Case.WhenThenExpression> {

        public WhenThenExpressionBuilder() {
            super(new Case.WhenThenExpression());
        }

        public WhenThenExpressionBuilder(Case.WhenThenExpression target) {
            super(target);
        }

        /**
         * set
         * @param expression Expression
         * @return THIS
         */
        public WhenThenExpressionBuilder<ParentBuilder> withWhen(Expression expression){
            target.setWhenExpression(expression);
            return this;
        }

        /**
         * set
         * @param expression Expression
         * @return THIS
         */
        public WhenThenExpressionBuilder<ParentBuilder> withThen(Expression expression){
            target.setResultExpression(expression);
            return this;
        }

        /**
         * set
         * @param expression Expression
         * @return PARENT
         */
        public ParentBuilder $Then(Expression expression){
            return withThen(expression).and();
        }

    }

}
