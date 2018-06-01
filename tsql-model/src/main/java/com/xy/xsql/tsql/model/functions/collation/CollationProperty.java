package com.xy.xsql.tsql.model.functions.collation;

import com.xy.xsql.tsql.model.functions.Function;
import com.xy.xsql.tsql.model.functions.analytic.AnalyticFunction;

/**
 * Created by xiaoyao9184 on 2018/5/29.
 */
public class CollationProperty
        implements CollationFunction, Function.InternalFunction {

    private String collationName;
    private String property;

    public String getCollationName() {
        return collationName;
    }

    public void setCollationName(String collationName) {
        this.collationName = collationName;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }
}
