package com.xy.xsql.tsql.core.predicate;

import com.xy.xsql.core.CodeTreeBuilder;
import com.xy.xsql.tsql.model.expression.Expression;
import com.xy.xsql.tsql.model.predicate.IsNull;
import com.xy.xsql.tsql.model.predicate.Predicate;

/**
 * Created by xiaoyao9184 on 2017/3/16.
 * expression IS [ NOT ] NULL
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

    public IsNullPredicateBuilder<ParentBuilder> withNot(boolean useNot) {
        tar.setUseNotOperator(useNot);
        return this;
    }

    public static Predicate IS_NULL(Expression left){
        return new IsNullPredicateBuilder<Void>()
                .withExpression(left)
                .build();
    }

    public static Predicate IS_NOT_NULL(Expression left){
        return new IsNullPredicateBuilder<Void>()
                .withExpression(left)
                .withNot(true)
                .build();
    }
}
