package com.xy.xsql.tsql.model.functions.metadata;

import com.xy.xsql.tsql.model.elements.expressions.Expression;
import com.xy.xsql.tsql.model.functions.Function;
import com.xy.xsql.tsql.model.functions.security.SecurityFunction;

/**
 * Created by xiaoyao9184 on 2018/5/29.
 */
public class ColumnProperty
        implements MetaDataFunction, Function.InternalFunction {

    private Expression id;
    private Expression column;
    private Expression property;

    public Expression getId() {
        return id;
    }

    public void setId(Expression id) {
        this.id = id;
    }

    public Expression getColumn() {
        return column;
    }

    public void setColumn(Expression column) {
        this.column = column;
    }

    public Expression getProperty() {
        return property;
    }

    public void setProperty(Expression property) {
        this.property = property;
    }
}
