package com.xy.xsql.tsql.model.functions.string;

import com.xy.xsql.tsql.model.elements.expressions.Expression;
import com.xy.xsql.tsql.model.functions.Function;

import java.util.List;

/**
 * Created by xiaoyao9184 on 2018/5/29.
 */
public class Difference
        implements StringFunction, Function.InternalFunction {

    private Expression characterExpression;
    private Expression characterExpression2;

    public Expression getCharacterExpression() {
        return characterExpression;
    }

    public void setCharacterExpression(Expression characterExpression) {
        this.characterExpression = characterExpression;
    }

    public Expression getCharacterExpression2() {
        return characterExpression2;
    }

    public void setCharacterExpression2(Expression characterExpression2) {
        this.characterExpression2 = characterExpression2;
    }
}
