package com.xy.xsql.tsql.model.functions.metadata;

import com.xy.xsql.tsql.model.elements.expressions.Expression;
import com.xy.xsql.tsql.model.functions.Function;
import com.xy.xsql.tsql.model.functions.security.SecurityFunction;

/**
 * Created by xiaoyao9184 on 2018/5/29.
 */
public class Type_Name
        implements MetaDataFunction, Function.InternalFunction {

    private Expression typeId;

    public Expression getTypeId() {
        return typeId;
    }

    public void setTypeId(Expression typeId) {
        this.typeId = typeId;
    }
}
