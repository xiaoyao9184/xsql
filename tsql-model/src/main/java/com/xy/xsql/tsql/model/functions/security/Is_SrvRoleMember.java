package com.xy.xsql.tsql.model.functions.security;

import com.xy.xsql.tsql.model.datatypes.constants.StringConstant;
import com.xy.xsql.tsql.model.functions.Function;

/**
 * Created by xiaoyao9184 on 2018/5/29.
 */
public class Is_SrvRoleMember
        implements SecurityFunction, Function.InternalFunction {

    private StringConstant role;
    private StringConstant login;

    public StringConstant getRole() {
        return role;
    }

    public void setRole(StringConstant role) {
        this.role = role;
    }

    public StringConstant getLogin() {
        return login;
    }

    public void setLogin(StringConstant login) {
        this.login = login;
    }
}
