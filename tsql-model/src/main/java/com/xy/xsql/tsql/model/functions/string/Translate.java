package com.xy.xsql.tsql.model.functions.string;

import com.xy.xsql.tsql.model.elements.expressions.Expression;
import com.xy.xsql.tsql.model.functions.Function;

/**
 * Created by xiaoyao9184 on 2018/5/29.
 */
public class Translate
        implements StringFunction, Function.InternalFunction {

    private Expression inputString;
    private Expression characters;
    private Expression translations;

    public Expression getInputString() {
        return inputString;
    }

    public void setInputString(Expression inputString) {
        this.inputString = inputString;
    }

    public Expression getCharacters() {
        return characters;
    }

    public void setCharacters(Expression characters) {
        this.characters = characters;
    }

    public Expression getTranslations() {
        return translations;
    }

    public void setTranslations(Expression translations) {
        this.translations = translations;
    }
}
