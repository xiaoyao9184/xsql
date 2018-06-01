package com.xy.xsql.tsql.model.functions.text;

import com.xy.xsql.tsql.model.datatypes.constants.StringConstant;
import com.xy.xsql.tsql.model.elements.expressions.Expression;
import com.xy.xsql.tsql.model.functions.Function;

/**
 * Created by xiaoyao9184 on 2018/5/29.
 */
public class TextValid
        implements TextFunction, Function.InternalFunction {

    private StringConstant column;
    private Expression textPtr;

    public StringConstant getColumn() {
        return column;
    }

    public void setColumn(StringConstant column) {
        this.column = column;
    }

    public Expression getTextPtr() {
        return textPtr;
    }

    public void setTextPtr(Expression textPtr) {
        this.textPtr = textPtr;
    }
}
