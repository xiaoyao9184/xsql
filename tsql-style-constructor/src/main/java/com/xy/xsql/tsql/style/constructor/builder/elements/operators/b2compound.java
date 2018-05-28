package com.xy.xsql.tsql.style.constructor.builder.elements.operators;

import com.xy.xsql.core.builder.SimpleBuilder;
import com.xy.xsql.tsql.model.elements.expressions.Expression;
import com.xy.xsql.tsql.model.elements.operators.Compound;

/**
 * Created by xiaoyao9184 on 2015/5/25.
 */
public interface b2compound
        extends SimpleBuilder<Compound> {

    b2compound $add_assignment(Expression expression);
    b2compound $subtract_assignment(Expression expression);
    b2compound $multiply_assignment(Expression expression);
    b2compound $divide_assignment(Expression expression);
    b2compound $modulo_assignment(Expression expression);
    b2compound $bitwise_and_assignment(Expression expression);
    b2compound $bitwise_exclusive_or_assignment(Expression expression);
    b2compound $bitwise_or_assignment(Expression expression);

}