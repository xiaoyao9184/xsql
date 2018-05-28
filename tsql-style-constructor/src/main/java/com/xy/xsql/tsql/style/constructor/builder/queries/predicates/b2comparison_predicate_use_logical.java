package com.xy.xsql.tsql.style.constructor.builder.queries.predicates;

import com.xy.xsql.core.builder.SimpleBuilder;
import com.xy.xsql.tsql.model.elements.expressions.Expression;
import com.xy.xsql.tsql.model.elements.operators.Comparison;

/**
 * Created by xiaoyao9184 on 2015/5/25.
 */
public interface b2comparison_predicate_use_logical
        extends SimpleBuilder<Comparison> {


    b2comparison_predicate_use_logical $equal(Expression expression);

    b2comparison_predicate_use_logical $not_equal(Expression expression);

    b2comparison_predicate_use_logical $not_equal_not_iso(Expression expression);

    b2comparison_predicate_use_logical $greater(Expression expression);

    b2comparison_predicate_use_logical $greater_equal(Expression expression);

    b2comparison_predicate_use_logical $not_greater_equal_not_iso(Expression expression);

    b2comparison_predicate_use_logical $less(Expression expression);

    b2comparison_predicate_use_logical $less_equal(Expression expression);

    b2comparison_predicate_use_logical $not_less_not_iso(Expression expression);


}