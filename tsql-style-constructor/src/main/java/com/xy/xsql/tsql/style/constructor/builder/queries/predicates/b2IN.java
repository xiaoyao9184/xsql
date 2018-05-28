package com.xy.xsql.tsql.style.constructor.builder.queries.predicates;

import com.xy.xsql.core.builder.SimpleBuilder;
import com.xy.xsql.tsql.model.elements.expressions.Expression;
import com.xy.xsql.tsql.model.queries.Select;
import com.xy.xsql.tsql.model.queries.predicates.In;

/**
 * Created by xiaoyao9184 on 2015/5/25.
 */
public interface b2IN
        extends SimpleBuilder<In> {

    b2IN IN(
            Select subquery);

    b2IN IN(
            Expression... expression);

    b2IN NOT_IN(
            Select subquery);

    b2IN NOT_IN(
            Expression... expression);

}
