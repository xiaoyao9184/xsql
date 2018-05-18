package com.xy.xsql.tsql.core.predicate;

import com.xy.xsql.core.builder.CodeTreeBuilder;
import com.xy.xsql.tsql.model.elements.expressions.Expression;
import com.xy.xsql.tsql.model.queries.predicates.Comparison;

/**
 * Created by xiaoyao9184 on 2017/3/16.
 *
 * ComparisonPredicateBuilder
 *
 * @see Comparison
 * @param <ParentBuilder>
 */
@SuppressWarnings("Duplicates")
public class ComparisonPredicateBuilder<ParentBuilder>
        extends CodeTreeBuilder<ComparisonPredicateBuilder<ParentBuilder>,ParentBuilder,Comparison> {

    public ComparisonPredicateBuilder() {
        super(new Comparison());
    }

    public ComparisonPredicateBuilder(Comparison predicate) {
        super(predicate);
    }

    private int index = 0;

    public ComparisonPredicateBuilder<ParentBuilder> withExpression(Expression expression) {
        if(index == 0){
            target.setExpression(expression);
            index = 1;
        } else {
            target.setOperatorExpression(expression);
            index = 0;
        }
        return this;
    }

    public ComparisonPredicateBuilder<ParentBuilder> withOperator(com.xy.xsql.tsql.model.elements.operators.Comparison operator) {
        target.setOperator(operator);
        return this;
    }

}