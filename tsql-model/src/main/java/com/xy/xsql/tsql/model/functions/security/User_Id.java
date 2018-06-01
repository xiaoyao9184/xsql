package com.xy.xsql.tsql.model.functions.security;

import com.xy.xsql.tsql.model.datatypes.constants.StringConstant;
import com.xy.xsql.tsql.model.functions.Function;

/**
 * Created by xiaoyao9184 on 2018/5/29.
 */
public class User_Id
        implements SecurityFunction, Function.InternalFunction {

    private StringConstant user;

    public StringConstant getUser() {
        return user;
    }

    public void setUser(StringConstant user) {
        this.user = user;
    }
}
