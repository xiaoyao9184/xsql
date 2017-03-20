package com.xy.xsql.tsql.core.expression;

import com.xy.xsql.core.builder.CodeTreeBuilder;
import com.xy.xsql.tsql.model.expression.Case;
import com.xy.xsql.tsql.model.expression.Expression;

import static com.xy.xsql.core.ListBuilder.initAdd;

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

    public CaseBuilder<ParentBuilder> withElse(Expression elseResultExpression) {
        target.withElseResultExpression(elseResultExpression);
        return this;
    }
}
