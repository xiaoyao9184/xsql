package com.xy.xsql.tsql.core.predicate;

import com.xy.xsql.core.builder.CodeTreeBuilder;
import com.xy.xsql.tsql.model.expression.Expression;
import com.xy.xsql.tsql.model.predicate.In;
import com.xy.xsql.tsql.model.statement.dml.Select;

import java.util.Arrays;

import static com.xy.xsql.core.ListBuilder.initAdd;

/**
 * Created by xiaoyao9184 on 2017/3/16.
 *
 * InPredicateBuilder
 *
 * @see In
 * @param <ParentBuilder>
 */
public class InPredicateBuilder<ParentBuilder>
        extends CodeTreeBuilder<InPredicateBuilder<ParentBuilder>,ParentBuilder,In> {

    public InPredicateBuilder() {
        super(new In());
    }

    public InPredicateBuilder(In predicate) {
        super(predicate);
    }

    public InPredicateBuilder<ParentBuilder> withExpression(Expression expression) {
        target.setExpression(expression);
        return this;
    }

    public InPredicateBuilder<ParentBuilder> withNot() {
        target.setUseNotOperator(true);
        return this;
    }

    public InPredicateBuilder<ParentBuilder> withValueExpression(Expression... expressions) {
        initAdd(Arrays.asList(expressions),
                target::getExpressionList,
                target::setExpressionList);
        return this;
    }

    public InPredicateBuilder<ParentBuilder> withSubQuery(Select subquery) {
        target.setSubquery(subquery);
        return this;
    }

}
