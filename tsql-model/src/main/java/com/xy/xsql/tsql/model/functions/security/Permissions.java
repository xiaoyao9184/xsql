package com.xy.xsql.tsql.model.functions.security;

import com.xy.xsql.tsql.model.datatypes.constants.StringConstant;
import com.xy.xsql.tsql.model.elements.expressions.Expression;
import com.xy.xsql.tsql.model.functions.Function;

/**
 * Created by xiaoyao9184 on 2018/5/29.
 */
public class Permissions
        implements SecurityFunction, Function.InternalFunction {

    private Expression objectId;
    private StringConstant column;

    public Expression getObjectId() {
        return objectId;
    }

    public void setObjectId(Expression objectId) {
        this.objectId = objectId;
    }

    public StringConstant getColumn() {
        return column;
    }

    public void setColumn(StringConstant column) {
        this.column = column;
    }
}
