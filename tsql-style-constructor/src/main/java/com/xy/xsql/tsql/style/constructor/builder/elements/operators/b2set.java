package com.xy.xsql.tsql.style.constructor.builder.elements.operators;

import com.xy.xsql.core.builder.SimpleBuilder;
import com.xy.xsql.tsql.style.constructor.builder.queries.b_SELECT;

import java.util.Set;

/**
 * @see b_SELECT.b$query_specification
 * @see b_SELECT.b$query_expression
 * Created by xiaoyao9184 on 2015/5/25.
 */
@Deprecated
public interface b2set
        extends SimpleBuilder<Set> {

    b2set UNION(b_SELECT.b$query_specification query_specification);
    b2set UNION(b_SELECT.b$query_expression query_expression);
    b2set UNION_ALL(b_SELECT.b$query_specification query_specification);
    b2set UNION_ALL(b_SELECT.b$query_expression query_expression);
    b2set EXCEPT(b_SELECT.b$query_specification query_specification);
    b2set EXCEPT(b_SELECT.b$query_expression query_expression);
    b2set INTERSECT(b_SELECT.b$query_specification query_specification);
    b2set INTERSECT(b_SELECT.b$query_expression query_expression);

}