package com.xy.xsql.tsql.style.constructor.builder.elements.operators;

import com.xy.xsql.core.builder.SimpleBuilder;
import com.xy.xsql.tsql.model.elements.expressions.Expression;
import com.xy.xsql.tsql.model.elements.operators.Assignment;

/**
 * Created by xiaoyao9184 on 2015/5/25.
 */
public interface b2assignment
        extends SimpleBuilder<Assignment> {

    b2assignment $assignment(Expression expression);

}