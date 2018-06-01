package com.xy.xsql.tsql.model.functions.string;

import com.xy.xsql.tsql.model.elements.expressions.Expression;
import com.xy.xsql.tsql.model.functions.Function;

/**
 * Created by xiaoyao9184 on 2018/5/29.
 */
public class QuoteName
        implements StringFunction, Function.InternalFunction {

    private Expression characterString;
    private Expression quoteCharacter;

    public Expression getCharacterString() {
        return characterString;
    }

    public void setCharacterString(Expression characterString) {
        this.characterString = characterString;
    }

    public Expression getQuoteCharacter() {
        return quoteCharacter;
    }

    public void setQuoteCharacter(Expression quoteCharacter) {
        this.quoteCharacter = quoteCharacter;
    }
}
