package com.xy.xsql.tsql.model.functions.string;

import com.xy.xsql.tsql.model.datatypes.constants.StringConstant;
import com.xy.xsql.tsql.model.elements.expressions.Expression;
import com.xy.xsql.tsql.model.functions.Function;
import com.xy.xsql.tsql.model.queries.select.OrderBy;

/**
 * Created by xiaoyao9184 on 2018/5/29.
 */
public class String_Escape
        implements StringFunction, Function.InternalFunction {

    private Expression text;
    private Type type;

    public Expression getText() {
        return text;
    }

    public void setText(Expression text) {
        this.text = text;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public enum Type {
        json
    }
}
