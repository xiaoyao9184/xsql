package com.xy.xsql.tsql.model.functions.security;

import com.xy.xsql.tsql.model.datatypes.constants.StringConstant;
import com.xy.xsql.tsql.model.functions.Function;

/**
 * Created by xiaoyao9184 on 2018/5/29.
 */
public class Is_RoleMember
        implements SecurityFunction, Function.InternalFunction {

    private StringConstant role;
    private StringConstant database_principal;

    public StringConstant getRole() {
        return role;
    }

    public void setRole(StringConstant role) {
        this.role = role;
    }

    public StringConstant getDatabase_principal() {
        return database_principal;
    }

    public void setDatabase_principal(StringConstant database_principal) {
        this.database_principal = database_principal;
    }
}
