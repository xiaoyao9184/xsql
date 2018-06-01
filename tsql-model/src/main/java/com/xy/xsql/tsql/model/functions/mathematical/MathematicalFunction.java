package com.xy.xsql.tsql.model.functions.mathematical;

import com.xy.xsql.tsql.model.elements.expressions.Expression;
import com.xy.xsql.tsql.model.elements.expressions.ScalarExpression;
import com.xy.xsql.tsql.model.functions.Function;

/**
 * Created by xiaoyao9184 on 2017/6/29.
 */
public interface MathematicalFunction extends Function {



    interface NumericExpressionParam extends MathematicalFunction {

        Expression getNumericExpression();

        void setNumericExpression(Expression floatExpression);
    }

    interface FloatExpressionParam extends MathematicalFunction {

        Expression getFloatExpression();

        void setFloatExpression(Expression floatExpression);
    }
}
