package com.xy.xsql.tsql.model.functions.string;

import com.xy.xsql.tsql.model.elements.expressions.Expression;
import com.xy.xsql.tsql.model.functions.Function;

/**
 * Created by xiaoyao9184 on 2018/5/29.
 */
public interface StringFunction extends Function {

    interface CharacterExpressionParam {

        Expression getCharacterExpression();

        void setCharacterExpression(Expression floatExpression);
    }
}
