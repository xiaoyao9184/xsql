package com.xy.xsql.tsql.core.predicate;

import com.xy.xsql.core.builder.CodeTreeBuilder;
import com.xy.xsql.tsql.model.elements.expressions.Expression;
import com.xy.xsql.tsql.model.queries.predicates.IsNull;

/**
 * Created by xiaoyao9184 on 2017/3/16.
 *
 * IsNullPredicateBuilder
 *
 * @see IsNull
 * @param <ParentBuilder>
 */
public class IsNullPredicateBuilder<ParentBuilder>
        extends CodeTreeBuilder<IsNullPredicateBuilder<ParentBuilder>,ParentBuilder,IsNull> {

    public IsNullPredicateBuilder() {
        super(new IsNull());
    }

    public IsNullPredicateBuilder(IsNull predicate) {
        super(predicate);
    }

    public IsNullPredicateBuilder<ParentBuilder> withExpression(Expression expression) {
        target.setExpression(expression);
        return this;
    }

    public IsNullPredicateBuilder<ParentBuilder> withNot() {
        target.setUseNotOperator(true);
        return this;
    }

}
