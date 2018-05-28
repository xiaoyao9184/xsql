package com.xy.xsql.tsql.style.constructor.builder.elements.operators;

import com.xy.xsql.core.builder.SimpleBuilder;
import com.xy.xsql.tsql.model.elements.expressions.Expression;
import com.xy.xsql.tsql.model.elements.operators.StringConcatenation;

/**
 * same as b2arithmetic
 * @see b2arithmetic
 * Created by xiaoyao9184 on 2015/5/25.
 */
@Deprecated
public interface b2string
        extends SimpleBuilder<StringConcatenation> {

    b2string $concatenation(Expression expression);
    b2string $concatenation_assignment(Expression expression);

}