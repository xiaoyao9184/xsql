package com.xy.xsql.tsql.core.predicate;

/**
 * Created by xiaoyao9184 on 2017/3/16.
 */

import com.xy.xsql.core.builder.CodeTreeBuilder;
import com.xy.xsql.tsql.model.elements.expressions.Expression;
import com.xy.xsql.tsql.model.predicate.Between;

/**
 * Created by xiaoyao9184 on 2017/3/16.
 *
 * BetweenPredicateBuilder
 *
 * @see Between
 * @param <ParentBuilder>
 */
public class BetweenPredicateBuilder<ParentBuilder>
        extends CodeTreeBuilder<BetweenPredicateBuilder<ParentBuilder>,ParentBuilder,Between> {

    public BetweenPredicateBuilder() {
        super(new Between());
    }

    public BetweenPredicateBuilder(Between predicate) {
        super(predicate);
    }

    private int index = 0;

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

    public BetweenPredicateBuilder<ParentBuilder> withNot() {
        target.setUseNotOperator(true);
        return this;
    }

}
