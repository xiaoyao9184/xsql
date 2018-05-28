package com.xy.xsql.tsql.style.constructor.builder.queries.predicates;

import com.xy.xsql.core.builder.SimpleBuilder;
import com.xy.xsql.tsql.model.elements.expressions.Expression;
import com.xy.xsql.tsql.model.queries.predicates.Between;
import com.xy.xsql.tsql.style.constructor.builder.elements.expressions.b_;

/**
 * Created by xiaoyao9184 on 2015/5/25.
 */
public interface b2BETWEEN
        extends SimpleBuilder<Between> {

    b2BETWEEN BETWEEN(
            Expression begin_expression,
            b_.AND and);

}
