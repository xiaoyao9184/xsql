package com.xy.xsql.tsql.style.constructor.builder.elements.operators;

import com.xy.xsql.core.builder.SimpleBuilder;
import com.xy.xsql.tsql.model.elements.expressions.Expression;
import com.xy.xsql.tsql.model.elements.operators.Operator;

/**
 * @see com.xy.xsql.tsql.model.elements.operators.Compound
 * @see com.xy.xsql.tsql.model.elements.operators.Assignment
 * Created by xiaoyao9184 on 2015/5/25.
 */
public interface b2compoundassignment
        extends SimpleBuilder<Operator> {

    b2compoundassignment $assignment(Expression expression);
    b2compoundassignment $add_assignment(Expression expression);
    b2compoundassignment $subtract_assignment(Expression expression);
    b2compoundassignment $multiply_assignment(Expression expression);
    b2compoundassignment $divide_assignment(Expression expression);
    b2compoundassignment $modulo_assignment(Expression expression);
    b2compoundassignment $bitwise_and_assignment(Expression expression);
    b2compoundassignment $bitwise_exclusive_or_assignment(Expression expression);
    b2compoundassignment $bitwise_or_assignment(Expression expression);

}