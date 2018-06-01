package com.xy.xsql.tsql.model.functions.security;

import com.xy.xsql.tsql.model.datatypes.constants.StringConstant;
import com.xy.xsql.tsql.model.functions.Function;

/**
 * Created by xiaoyao9184 on 2018/5/29.
 */
public class SUser_Name
        implements SecurityFunction, Function.InternalFunction {

    private StringConstant serverUserId;

    public StringConstant getServerUserId() {
        return serverUserId;
    }

    public void setServerUserId(StringConstant serverUserId) {
        this.serverUserId = serverUserId;
    }
}
