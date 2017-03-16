package com.xy.xsql.tsql.core.predicate;

/**
 * Created by xiaoyao9184 on 2017/3/16.
 */

import com.xy.xsql.core.CodeTreeBuilder;
import com.xy.xsql.tsql.model.expression.Expression;
import com.xy.xsql.tsql.model.predicate.Between;
import com.xy.xsql.tsql.model.predicate.Predicate;

/**
 * expression [ NOT ] BETWEEN expression AND expression
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
            tar.setExpression(expression);
            index = 1;
        } else if(index == 1) {
            tar.setStartExpression(expression);
            index = 2;
        } else {
            tar.setEndExpression(expression);
            index = 0;
        }
        return this;
    }

    public BetweenPredicateBuilder<ParentBuilder> withNot(boolean useNot) {
        tar.setUseNotOperator(useNot);
        return this;
    }


    public static Predicate BETWEEN(Expression left, Expression start, Expression end){
        return new BetweenPredicateBuilder<Void>()
                .withExpression(left)
                .withExpression(start)
                .withExpression(end)
                .build();
    }

    public static Predicate NOT_BETWEEN(Expression left,Expression start,Expression end){
        return new BetweenPredicateBuilder<Void>()
                .withNot(true)
                .withExpression(left)
                .withExpression(start)
                .withExpression(end)
                .build();
    }
}
