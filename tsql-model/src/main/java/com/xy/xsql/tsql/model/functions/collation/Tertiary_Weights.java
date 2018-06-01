package com.xy.xsql.tsql.model.functions.collation;

import com.xy.xsql.tsql.model.elements.expressions.Expression;
import com.xy.xsql.tsql.model.functions.Function;

/**
 * Created by xiaoyao9184 on 2018/5/29.
 */
public class Tertiary_Weights
        implements CollationFunction, Function.InternalFunction {

    private Expression nonUnicodeCharacterStringExpression;

    public Expression getNonUnicodeCharacterStringExpression() {
        return nonUnicodeCharacterStringExpression;
    }

    public void setNonUnicodeCharacterStringExpression(Expression nonUnicodeCharacterStringExpression) {
        this.nonUnicodeCharacterStringExpression = nonUnicodeCharacterStringExpression;
    }
}
