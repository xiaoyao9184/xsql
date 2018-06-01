package com.xy.xsql.tsql.model.functions.security;

import com.xy.xsql.tsql.model.datatypes.constants.StringConstant;
import com.xy.xsql.tsql.model.functions.Function;

/**
 * Created by xiaoyao9184 on 2018/5/29.
 */
public class SUser_SName
        implements SecurityFunction, Function.InternalFunction {

    private StringConstant serverUserSId;

    public StringConstant getServerUserSId() {
        return serverUserSId;
    }

    public void setServerUserSId(StringConstant serverUserSId) {
        this.serverUserSId = serverUserSId;
    }
}
