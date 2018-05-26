package com.xy.xsql.tsql.builder.chain.queries.predicates;

import com.xy.xsql.core.builder.parent.ParentHoldBuilder;
import com.xy.xsql.tsql.model.elements.expressions.Expression;
import com.xy.xsql.tsql.model.queries.predicates.IsNull;

/**
 * IsNullPredicateBuilder
 * Created by xiaoyao9184 on 2017/3/16.
 * @param <ParentBuilder>
 */
@SuppressWarnings("WeakerAccess")
public class IsNullPredicateBuilder<ParentBuilder>
        extends ParentHoldBuilder<IsNullPredicateBuilder<ParentBuilder>,ParentBuilder,IsNull> {

    public IsNullPredicateBuilder() {
        super(new IsNull());
    }

    public IsNullPredicateBuilder(IsNull target) {
        super(target);
    }

    /**
     * set
     * @param expression Expression
     * @return THIS
     */
    public IsNullPredicateBuilder<ParentBuilder> withExpression(Expression expression) {
        target.setExpression(expression);
        return this;
    }

    /**
     * set
     * @return THIS
     */
    public IsNullPredicateBuilder<ParentBuilder> withNot() {
        target.setUseNotOperator(true);
        return this;
    }

}
