package com.xy.xsql.tsql.model.functions.rowset;

import com.xy.xsql.tsql.model.elements.expressions.Expression;
import com.xy.xsql.tsql.model.functions.Function;

/**
 * Created by xiaoyao9184 on 2018/5/29.
 */
public class OpenRowSet
        implements RowsetFunction, Function.InternalFunction {

    private Expression providerName;
    private String initString;

    public Expression getProviderName() {
        return providerName;
    }

    public void setProviderName(Expression providerName) {
        this.providerName = providerName;
    }

    public String getInitString() {
        return initString;
    }

    public void setInitString(String initString) {
        this.initString = initString;
    }
}
