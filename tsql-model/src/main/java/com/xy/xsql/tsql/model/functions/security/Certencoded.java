package com.xy.xsql.tsql.model.functions.security;

import com.xy.xsql.tsql.model.elements.expressions.Expression;
import com.xy.xsql.tsql.model.functions.Function;
import com.xy.xsql.tsql.model.functions.system.SystemFunction;

import java.util.List;

/**
 * Created by xiaoyao9184 on 2018/5/29.
 */
public class Certencoded
        implements SecurityFunction, Function.InternalFunction {

    private Expression certId;

    public Expression getCertId() {
        return certId;
    }

    public void setCertId(Expression certId) {
        this.certId = certId;
    }
}
