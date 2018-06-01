package com.xy.xsql.tsql.model.functions.security;

import com.xy.xsql.tsql.model.datatypes.constants.StringConstant;
import com.xy.xsql.tsql.model.elements.expressions.Expression;
import com.xy.xsql.tsql.model.functions.Function;

/**
 * Created by xiaoyao9184 on 2018/5/29.
 */
public class PWDcompare
        implements SecurityFunction, Function.InternalFunction {

    private StringConstant clearTextPassword;
    private Expression passwordHash;
    private Expression version;

    public StringConstant getClearTextPassword() {
        return clearTextPassword;
    }

    public void setClearTextPassword(StringConstant clearTextPassword) {
        this.clearTextPassword = clearTextPassword;
    }

    public Expression getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(Expression passwordHash) {
        this.passwordHash = passwordHash;
    }

    public Expression getVersion() {
        return version;
    }

    public void setVersion(Expression version) {
        this.version = version;
    }
}
