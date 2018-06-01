package com.xy.xsql.tsql.model.functions.metadata;

import com.xy.xsql.tsql.model.datatypes.constants.StringConstant;
import com.xy.xsql.tsql.model.elements.expressions.Expression;
import com.xy.xsql.tsql.model.functions.Function;
import com.xy.xsql.tsql.model.functions.security.SecurityFunction;

/**
 * Created by xiaoyao9184 on 2018/5/29.
 */
public class FullTextCatalogProperty
        implements MetaDataFunction, Function.InternalFunction {

    private StringConstant catalogName;
    private Expression property;

    public StringConstant getCatalogName() {
        return catalogName;
    }

    public void setCatalogName(StringConstant catalogName) {
        this.catalogName = catalogName;
    }

    public Expression getProperty() {
        return property;
    }

    public void setProperty(Expression property) {
        this.property = property;
    }
}
