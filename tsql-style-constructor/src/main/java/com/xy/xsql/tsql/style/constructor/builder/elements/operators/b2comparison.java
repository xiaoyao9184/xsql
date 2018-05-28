package com.xy.xsql.tsql.style.constructor.builder.elements.operators;

import com.xy.xsql.core.builder.SimpleBuilder;
import com.xy.xsql.tsql.model.elements.expressions.Expression;
import com.xy.xsql.tsql.model.elements.operators.Comparison;
import com.xy.xsql.tsql.style.constructor.builder.queries.predicates.b2comparison_predicate_use_logical;

/**
 * TODO use to replace
 * @see b2comparison_predicate_use_logical
 * Created by xiaoyao9184 on 2015/5/25.
 */
@Deprecated
public interface b2comparison
        extends SimpleBuilder<Comparison> {


    b2comparison $equal(Expression expression);

    b2comparison $not_equal(Expression expression);

    b2comparison $not_equal_not_iso(Expression expression);

    b2comparison $greater(Expression expression);

    b2comparison $greater_equal(Expression expression);

    b2comparison $not_greater_equal_not_iso(Expression expression);

    b2comparison $less(Expression expression);

    b2comparison $less_equal(Expression expression);

    b2comparison $not_less_not_iso(Expression expression);


}