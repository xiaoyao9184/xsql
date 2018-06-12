package com.xy.xsql.tsql.model.functions.security;

import com.xy.xsql.tsql.model.elements.expressions.Expression;
import com.xy.xsql.tsql.model.functions.Function;

/**
 * Created by xiaoyao9184 on 2018/5/29.
 */
public class SUser_Name
        implements SecurityFunction, Function.InternalFunction {

    private Expression serverUserId;

    public Expression getServerUserId() {
        return serverUserId;
    }

    public void setServerUserId(Expression serverUserId) {
        this.serverUserId = serverUserId;
    }
}
