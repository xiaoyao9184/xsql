package com.xy.xsql.tsql.model.functions.string;

import com.xy.xsql.tsql.model.elements.expressions.Expression;
import com.xy.xsql.tsql.model.functions.Function;

/**
 * Created by xiaoyao9184 on 2018/5/29.
 */
public class Trim
        implements StringFunction, Function.InternalFunction {

    private Expression characters;
    private Expression string;

    public Expression getCharacters() {
        return characters;
    }

    public void setCharacters(Expression characters) {
        this.characters = characters;
    }

    public Expression getString() {
        return string;
    }

    public void setString(Expression string) {
        this.string = string;
    }
}
