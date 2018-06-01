package com.xy.xsql.tsql.model.functions.security;

import com.xy.xsql.tsql.model.datatypes.constants.StringConstant;
import com.xy.xsql.tsql.model.functions.Function;

/**
 * Created by xiaoyao9184 on 2018/5/29.
 */
public class Has_DBAccess
        implements SecurityFunction, Function.InternalFunction {

    private StringConstant databaseName;

    public StringConstant getDatabaseName() {
        return databaseName;
    }

    public void setDatabaseName(StringConstant databaseName) {
        this.databaseName = databaseName;
    }
}
