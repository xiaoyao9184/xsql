package com.xy.xsql.tsql.style.constructor.builder.elements.operators;


import com.xy.xsql.core.builder.SimpleBuilder;
import com.xy.xsql.tsql.model.elements.expressions.Expression;
import com.xy.xsql.tsql.model.elements.operators.Arithmetic;

/**
 * Created by xiaoyao9184 on 2015/5/25.、
 */
public interface b2arithmetic
        extends SimpleBuilder<Arithmetic> {

    b2arithmetic $addition(Expression expression);
    b2arithmetic $subtraction(Expression expression);
    b2arithmetic $multiplication(Expression expression);
    b2arithmetic $division(Expression expression);
    b2arithmetic $modulo(Expression expression);

}