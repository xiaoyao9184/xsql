package com.xy.xsql.tsql.model.functions.metadata;

import com.xy.xsql.tsql.model.elements.expressions.Expression;
import com.xy.xsql.tsql.model.functions.Function;
import com.xy.xsql.tsql.model.functions.security.SecurityFunction;

/**
 * Created by xiaoyao9184 on 2018/5/29.
 */
public class TypeProperty
        implements MetaDataFunction, Function.InternalFunction {

    private Expression type;
    private Expression property;

    public Expression getType() {
        return type;
    }

    public void setType(Expression type) {
        this.type = type;
    }

    public Expression getProperty() {
        return property;
    }

    public void setProperty(Expression property) {
        this.property = property;
    }
}
