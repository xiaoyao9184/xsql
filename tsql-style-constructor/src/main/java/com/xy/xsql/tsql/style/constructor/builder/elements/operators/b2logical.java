package com.xy.xsql.tsql.style.constructor.builder.elements.operators;

import com.xy.xsql.core.builder.SimpleBuilder;
import com.xy.xsql.tsql.model.elements.expressions.Expression;
import com.xy.xsql.tsql.model.elements.operators.Logical;
import com.xy.xsql.tsql.model.queries.Select;
import com.xy.xsql.tsql.style.constructor.builder.elements.expressions.b_;
import com.xy.xsql.tsql.style.constructor.builder.queries.predicates.*;

/**
 * TODO use to replace
 * @see b2BETWEEN
 * @see b2comparison_subquery_predicate_use_logical
 * @see b2IN
 * @see b2IS_NULL
 * @see b2LIKE
 * @see b_EXISTS
 * Created by xiaoyao9184 on 2015/5/25.
 */
@Deprecated
public interface b2logical
        extends SimpleBuilder<Logical> {

    b2logical ALL(Select subquery);
    b2logical AND(Expression boolean_expression);
    b2logical ANY(Select subquery);
    b2logical BETWEEN(Expression begin_expression,b_.AND and);
    b2logical EXISTS(Select subquery);
    b2logical IN(Expression... expression);
    b2logical IN(Select subquery);
    b2logical LIKE(Expression expression);
    b2logical LIKE(Expression expression,b2LIKE.ESCAPE escape);
    b2logical NOT(Expression expression);
    b2logical OR(Expression expression);
    b2logical SOME(Select subquery);

}