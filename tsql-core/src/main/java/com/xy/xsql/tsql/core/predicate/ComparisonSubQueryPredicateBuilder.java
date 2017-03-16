package com.xy.xsql.tsql.core.predicate;

import com.xy.xsql.core.CodeTreeBuilder;
import com.xy.xsql.tsql.model.expression.Expression;
import com.xy.xsql.tsql.model.predicate.ComparisonSubQuery;
import com.xy.xsql.tsql.model.statement.dml.Select;

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
        tar.setExpression(expression);
        return this;
    }

    public ComparisonSubQueryPredicateBuilder<ParentBuilder> withOperator(com.xy.xsql.tsql.model.operator.Operator operator) {
        tar.setOperator(operator);
        return this;
    }

    public ComparisonSubQueryPredicateBuilder<ParentBuilder> withALL_SOME_ANY(ComparisonSubQuery.ALL_SOME_ANY oneOf) {
        tar.setAll_some_any(oneOf);
        return this;
    }

    public ComparisonSubQueryPredicateBuilder<ParentBuilder> withSubQuery(Select subquery) {
        tar.setSubquery(subquery);
        return this;
    }

}
