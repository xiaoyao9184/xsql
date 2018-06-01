package com.xy.xsql.tsql.model.functions.string;

import com.xy.xsql.tsql.model.elements.expressions.Expression;
import com.xy.xsql.tsql.model.functions.Function;

/**
 * Created by xiaoyao9184 on 2018/5/29.
 */
public class Upper
        implements StringFunction, StringFunction.CharacterExpressionParam, Function.InternalFunction {

    private Expression characterExpression ;

    public Expression getCharacterExpression() {
        return characterExpression;
    }

    public void setCharacterExpression(Expression characterExpression) {
        this.characterExpression = characterExpression;
    }
}
