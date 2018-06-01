package com.xy.xsql.tsql.model.functions.mathematical;

import com.xy.xsql.tsql.model.elements.expressions.Expression;
import com.xy.xsql.tsql.model.functions.Function;

/**
 * Created by xiaoyao9184 on 2018/5/29.
 */
public class Degrees
        implements MathematicalFunction.NumericExpressionParam, Function.InternalFunction {

    private Expression numericExpression;

    public Expression getNumericExpression() {
        return numericExpression;
    }

    public void setNumericExpression(Expression numericExpression) {
        this.numericExpression = numericExpression;
    }
}
