package com.xy.xsql.tsql.style.constructor.builder.elements.operators;

import com.xy.xsql.core.builder.SimpleBuilder;
import com.xy.xsql.tsql.model.elements.expressions.Expression;
import com.xy.xsql.tsql.model.elements.operators.Bitwise;

/**
 * Created by xiaoyao9184 on 2015/5/25.
 */
@SuppressWarnings("Duplicates")
public interface b2bitwise
        extends SimpleBuilder<Bitwise> {

    b2bitwise $and(Expression expression);
    b2bitwise $and_equals(Expression expression);
    b2bitwise $or(Expression expression);
    b2bitwise $or_equals(Expression expression);
    b2bitwise $xor(Expression expression);
    b2bitwise $xor_equals(Expression expression);
    b2bitwise $not(Expression expression);

}