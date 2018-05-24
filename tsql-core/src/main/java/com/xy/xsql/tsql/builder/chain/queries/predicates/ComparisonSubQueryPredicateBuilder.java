package com.xy.xsql.tsql.builder.chain.queries.predicates;

import com.xy.xsql.core.builder.CodeTreeBuilder;
import com.xy.xsql.tsql.model.elements.expressions.Expression;
import com.xy.xsql.tsql.model.elements.operators.Comparison;
import com.xy.xsql.tsql.model.queries.predicates.ComparisonSubQuery;
import com.xy.xsql.tsql.model.queries.Select;

/**
 * ComparisonSubQueryPredicateBuilder
 * Created by xiaoyao9184 on 2017/3/16.
 * @param <ParentBuilder>
 */
@SuppressWarnings("WeakerAccess")
public class ComparisonSubQueryPredicateBuilder<ParentBuilder>
        extends CodeTreeBuilder<ComparisonSubQueryPredicateBuilder<ParentBuilder>,ParentBuilder,ComparisonSubQuery> {

    public ComparisonSubQueryPredicateBuilder() {
        super(new ComparisonSubQuery());
    }

    public ComparisonSubQueryPredicateBuilder(ComparisonSubQuery predicate) {
        super(predicate);
    }

    /**
     * set
     * @param expression Expression
     * @return THIS
     */
    public ComparisonSubQueryPredicateBuilder<ParentBuilder> withExpression(Expression expression) {
        target.setExpression(expression);
        return this;
    }

    /**
     * set
     * @param operator Comparison
     * @return THIS
     */
    public ComparisonSubQueryPredicateBuilder<ParentBuilder> withOperator(Comparison operator) {
        target.setOperator(operator);
        return this;
    }

    /**
     * set
     * @param oneOf ALL_SOME_ANY
     * @return THIS
     */
    public ComparisonSubQueryPredicateBuilder<ParentBuilder> withAllSomeAny(ComparisonSubQuery.ALL_SOME_ANY oneOf) {
        target.setAll_some_any(oneOf);
        return this;
    }

    /**
     * set
     * @param subQuery Select
     * @return THIS
     */
    public ComparisonSubQueryPredicateBuilder<ParentBuilder> withSubQuery(Select subQuery) {
        target.setSubquery(subQuery);
        return this;
    }

}
