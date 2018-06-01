package com.xy.xsql.tsql.model.functions.json;

import com.xy.xsql.tsql.model.datatypes.string.NVarChar;
import com.xy.xsql.tsql.model.elements.expressions.Expression;
import com.xy.xsql.tsql.model.functions.Function;

/**
 * Created by xiaoyao9184 on 2018/5/29.
 */
public class Json_Modify
        implements JsonFunction, Function.InternalFunction {

    private Expression expression;
    private String path;
    private Expression newValue;

    public Expression getExpression() {
        return expression;
    }

    public void setExpression(Expression expression) {
        this.expression = expression;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Expression getNewValue() {
        return newValue;
    }

    public void setNewValue(Expression newValue) {
        this.newValue = newValue;
    }
}
