package com.xy.xsql.tsql.model.functions.metadata;

import com.xy.xsql.tsql.model.datatypes.constants.StringConstant;
import com.xy.xsql.tsql.model.functions.Function;
import com.xy.xsql.tsql.model.functions.security.SecurityFunction;

/**
 * Created by xiaoyao9184 on 2018/5/29.
 */
public class AppLock_Mode
        implements MetaDataFunction, Function.InternalFunction {

    private StringConstant databasePrincipal;
    private StringConstant resourceName;
    private StringConstant lockOwner;

    public StringConstant getDatabasePrincipal() {
        return databasePrincipal;
    }

    public void setDatabasePrincipal(StringConstant databasePrincipal) {
        this.databasePrincipal = databasePrincipal;
    }

    public StringConstant getResourceName() {
        return resourceName;
    }

    public void setResourceName(StringConstant resourceName) {
        this.resourceName = resourceName;
    }

    public StringConstant getLockOwner() {
        return lockOwner;
    }

    public void setLockOwner(StringConstant lockOwner) {
        this.lockOwner = lockOwner;
    }
}
