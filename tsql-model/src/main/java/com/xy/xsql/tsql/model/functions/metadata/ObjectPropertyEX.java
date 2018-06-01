package com.xy.xsql.tsql.model.functions.metadata;

import com.xy.xsql.tsql.model.elements.expressions.Expression;
import com.xy.xsql.tsql.model.functions.Function;

/**
 * Created by xiaoyao9184 on 2018/5/29.
 */
public class ObjectPropertyEX
        implements MetaDataFunction, Function.InternalFunction {

    private Expression id;
    private Expression property;

    public Expression getId() {
        return id;
    }

    public void setId(Expression id) {
        this.id = id;
    }

    public Expression getProperty() {
        return property;
    }

    public void setProperty(Expression property) {
        this.property = property;
    }
}
