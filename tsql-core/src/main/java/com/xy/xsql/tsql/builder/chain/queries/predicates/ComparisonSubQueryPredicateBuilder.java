package com.xy.xsql.tsql.builder.chain.queries.predicates;

import com.xy.xsql.core.builder.CodeTreeBuilder;
import com.xy.xsql.tsql.model.elements.expressions.Expression;
import com.xy.xsql.tsql.model.elements.operators.Comparison;
import com.xy.xsql.tsql.model.queries.predicates.ComparisonSubQuery;
import com.xy.xsql.tsql.model.queries.Select;

/**
 * Created by xiaoyao9184 on 2017/3/16.
 *
 * ComparisonSubQueryPredicateBuilder
 *
 * @see ComparisonSubQuery
 * @param <ParentBuilder>
 */
public class ComparisonSubQueryPredicateBuilder<ParentBuilder>
        extends CodeTreeBuilder<ComparisonSubQueryPredicateBuilder<ParentBuilder>,ParentBuilder,ComparisonSubQuery> {

    public ComparisonSubQueryPredicateBuilder() {
        super(new ComparisonSubQuery());
    }

    public ComparisonSubQueryPredicateBuilder(ComparisonSubQuery predicate) {
        super(predicate);
    }

    public ComparisonSubQueryPredicateBuilder<ParentBuilder> withExpression(Expression expression) {
        target.setExpression(expression);
        return this;
    }

    public ComparisonSubQueryPredicateBuilder<ParentBuilder> withOperator(Comparison operator) {
        target.setOperator(operator);
        return this;
    }

    public ComparisonSubQueryPredicateBuilder<ParentBuilder> withALL_SOME_ANY(ComparisonSubQuery.ALL_SOME_ANY oneOf) {
        target.setAll_some_any(oneOf);
        return this;
    }

    public ComparisonSubQueryPredicateBuilder<ParentBuilder> withSubQuery(Select subquery) {
        target.setSubquery(subquery);
        return this;
    }

}
