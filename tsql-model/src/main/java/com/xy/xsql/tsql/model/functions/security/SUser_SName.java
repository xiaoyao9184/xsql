package com.xy.xsql.tsql.model.functions.security;

import com.xy.xsql.tsql.model.elements.expressions.Expression;
import com.xy.xsql.tsql.model.functions.Function;

/**
 * Created by xiaoyao9184 on 2018/5/29.
 */
public class SUser_SName
        implements SecurityFunction, Function.InternalFunction {

    private Expression serverUserSId;

    public Expression getServerUserSId() {
        return serverUserSId;
    }

    public void setServerUserSId(Expression serverUserSId) {
        this.serverUserSId = serverUserSId;
    }
}
