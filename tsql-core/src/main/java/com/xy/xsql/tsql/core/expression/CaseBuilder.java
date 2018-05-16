package com.xy.xsql.tsql.core.expression;

import com.xy.xsql.core.builder.CodeTreeBuilder;
import com.xy.xsql.tsql.model.expression.Case;
import com.xy.xsql.tsql.model.expression.Expression;

import java.util.List;

import static com.xy.xsql.core.ListBuilder.initAdd;
import static com.xy.xsql.core.ListBuilder.initNew;

/**
 * Created by xiaoyao9184 on 2017/3/10.
 */
public class CaseBuilder<ParentBuilder>
        extends CodeTreeBuilder<CaseBuilder<ParentBuilder>,ParentBuilder,Case> {

    public CaseBuilder() {
        super(new Case());
    }

    public CaseBuilder(Case tar) {
        super(tar);
    }

    public CaseBuilder<ParentBuilder> withInput(Expression inputExpression) {
        target.withInputExpression(inputExpression);
        return this;
    }

    public CaseBuilder<ParentBuilder> withWhen(Expression whenExpression, Expression resulExpression) {
        Case.WhenThenExpression whenThenExpression = new Case.WhenThenExpression()
                .withWhenExpression(whenExpression)
                .withThenExpression(resulExpression);
        initAdd(whenThenExpression,
                target::getWhenThenExpressionList,
                target::setWhenThenExpressionList);
        return this;
    }

    public CaseBuilder<ParentBuilder> withWhen(List<Case.WhenThenExpression> whenThenExpressionList) {
        initAdd(whenThenExpressionList,
                target::getWhenThenExpressionList,
                target::setWhenThenExpressionList);
        return this;
    }

    public CaseBuilder<ParentBuilder> withElse(Expression elseResultExpression) {
        target.withElseResultExpression(elseResultExpression);
        return this;
    }


    public WhenThenExpressionBuilder<CaseBuilder<ParentBuilder>> $When(Expression expression){
        return new WhenThenExpressionBuilder<CaseBuilder<ParentBuilder>>
                (initNew(Case.WhenThenExpression::new,
                        target::getWhenThenExpressionList,
                        target::setWhenThenExpressionList))
                .in(this)
                .withWhen(expression);
    }

    public CaseBuilder<ParentBuilder> $Else(Expression elseResultExpression) {
        return withElse(elseResultExpression);
    }



    public static class WhenThenExpressionBuilder<ParentBuilder>
            extends CodeTreeBuilder<WhenThenExpressionBuilder<ParentBuilder>,ParentBuilder,Case.WhenThenExpression> {


        public WhenThenExpressionBuilder(Case.WhenThenExpression whenThenExpression) {
            super(whenThenExpression);
        }

        public WhenThenExpressionBuilder<ParentBuilder> withWhen(Expression expression){
            target.setWhenExpression(expression);
            return this;
        }

        public WhenThenExpressionBuilder<ParentBuilder> withThen(Expression expression){
            target.setResultExpression(expression);
            return this;
        }

        public ParentBuilder $Then(Expression expression){
            return withThen(expression).and();
        }

    }

}
