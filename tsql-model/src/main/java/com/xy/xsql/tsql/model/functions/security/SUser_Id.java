package com.xy.xsql.tsql.model.functions.security;

import com.xy.xsql.tsql.model.datatypes.constants.StringConstant;
import com.xy.xsql.tsql.model.functions.Function;

/**
 * Created by xiaoyao9184 on 2018/5/29.
 */
public class SUser_Id
        implements SecurityFunction, Function.InternalFunction {

    private StringConstant login;

    public StringConstant getLogin() {
        return login;
    }

    public void setLogin(StringConstant login) {
        this.login = login;
    }
}
