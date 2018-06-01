package com.xy.xsql.tsql.model.functions.system;

import com.xy.xsql.tsql.model.elements.expressions.Expression;
import com.xy.xsql.tsql.model.functions.Function;

/**
 * Created by xiaoyao9184 on 2018/5/29.
 */
public class ConnectionProperty
        implements SystemFunction, Function.InternalFunction {

    private Expression property;

    public Expression getProperty() {
        return property;
    }

    public void setProperty(Expression property) {
        this.property = property;
    }
}
