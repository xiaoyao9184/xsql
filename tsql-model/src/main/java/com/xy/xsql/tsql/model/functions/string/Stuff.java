package com.xy.xsql.tsql.model.functions.string;

import com.xy.xsql.tsql.model.elements.expressions.Expression;
import com.xy.xsql.tsql.model.functions.Function;


/**
 * Created by xiaoyao9184 on 2018/5/29.
 */
public class Stuff
        implements StringFunction, Function.InternalFunction {

    private Expression characterExpression;
    private Expression start;
    private Expression length;
    private Expression replaceWithExpression;

    public Expression getCharacterExpression() {
        return characterExpression;
    }

    public void setCharacterExpression(Expression characterExpression) {
        this.characterExpression = characterExpression;
    }

    public Expression getStart() {
        return start;
    }

    public void setStart(Expression start) {
        this.start = start;
    }

    public Expression getLength() {
        return length;
    }

    public void setLength(Expression length) {
        this.length = length;
    }

    public Expression getReplaceWithExpression() {
        return replaceWithExpression;
    }

    public void setReplaceWithExpression(Expression replaceWithExpression) {
        this.replaceWithExpression = replaceWithExpression;
    }
}
