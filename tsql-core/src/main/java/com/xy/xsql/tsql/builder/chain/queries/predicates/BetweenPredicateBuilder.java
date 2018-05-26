package com.xy.xsql.tsql.builder.chain.queries.predicates;

import com.xy.xsql.core.builder.parent.ParentHoldBuilder;
import com.xy.xsql.tsql.model.elements.expressions.Expression;
import com.xy.xsql.tsql.model.queries.predicates.Between;

/**
 * BetweenPredicateBuilder
 * Created by xiaoyao9184 on 2017/3/16.
 * @param <ParentBuilder>
 */
@SuppressWarnings("WeakerAccess")
public class BetweenPredicateBuilder<ParentBuilder>
        extends ParentHoldBuilder<BetweenPredicateBuilder<ParentBuilder>,ParentBuilder,Between> {

    public BetweenPredicateBuilder() {
        super(new Between());
    }

    public BetweenPredicateBuilder(Between target) {
        super(target);
    }

    private int index = 0;

    /**
     * set
     * @param expression Expression
     * @return THIS
     */
    public BetweenPredicateBuilder<ParentBuilder> withExpression(Expression expression) {
        if(index == 0){
            target.setExpression(expression);
            index = 1;
        } else if(index == 1) {
            target.setStartExpression(expression);
            index = 2;
        } else {
            target.setEndExpression(expression);
            index = 0;
        }
        return this;
    }

    /**
     * set
     * @return THIS
     */
    public BetweenPredicateBuilder<ParentBuilder> withNot() {
        target.setUseNotOperator(true);
        return this;
    }

}
