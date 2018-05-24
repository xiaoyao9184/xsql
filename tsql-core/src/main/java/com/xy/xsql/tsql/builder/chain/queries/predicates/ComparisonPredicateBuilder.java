package com.xy.xsql.tsql.builder.chain.queries.predicates;

import com.xy.xsql.core.builder.CodeTreeBuilder;
import com.xy.xsql.tsql.model.elements.expressions.Expression;
import com.xy.xsql.tsql.model.queries.predicates.Comparison;

/**
 * ComparisonPredicateBuilder
 * Created by xiaoyao9184 on 2017/3/16.
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

    /**
     * set
     * @param expression Expression
     * @return THIS
     */
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

    /**
     * set
     * @param operator Comparison
     * @return THIS
     */
    public ComparisonPredicateBuilder<ParentBuilder> withOperator(com.xy.xsql.tsql.model.elements.operators.Comparison operator) {
        target.setOperator(operator);
        return this;
    }

}