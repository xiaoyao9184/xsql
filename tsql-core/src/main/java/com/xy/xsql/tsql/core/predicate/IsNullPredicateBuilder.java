package com.xy.xsql.tsql.core.predicate;

import com.xy.xsql.core.CodeTreeBuilder;
import com.xy.xsql.tsql.model.expression.Expression;
import com.xy.xsql.tsql.model.predicate.IsNull;

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
        tar.setExpression(expression);
        return this;
    }

    public IsNullPredicateBuilder<ParentBuilder> withNot() {
        tar.setUseNotOperator(true);
        return this;
    }

}
