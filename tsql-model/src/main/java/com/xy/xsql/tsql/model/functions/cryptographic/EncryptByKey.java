package com.xy.xsql.tsql.model.functions.cryptographic;

import com.xy.xsql.tsql.model.datatypes.Uniqueidentifier;
import com.xy.xsql.tsql.model.datatypes.constants.NumberConstant;
import com.xy.xsql.tsql.model.datatypes.constants.StringConstant;
import com.xy.xsql.tsql.model.datatypes.numeric.Int;
import com.xy.xsql.tsql.model.elements.expressions.Expression;
import com.xy.xsql.tsql.model.elements.variables.LocalVariable;
import com.xy.xsql.tsql.model.functions.Function;

/**
 * Created by xiaoyao9184 on 2018/5/29.
 */
public class EncryptByKey
        implements CryptographicFunction, Function.InternalFunction {
    private Expression keyGUID;
    private StringConstant cleartext;
    private LocalVariable cleartextVariable;
    private Expression addAuthenticator;
    private LocalVariable addAuthenticatorVariable;
    private Expression authenticator;
    private LocalVariable authenticatorVariable;

    public Expression getKeyGUID() {
        return keyGUID;
    }

    public void setKeyGUID(Expression keyGUID) {
        this.keyGUID = keyGUID;
    }

    public StringConstant getCleartext() {
        return cleartext;
    }

    public void setCleartext(StringConstant cleartext) {
        this.cleartext = cleartext;
    }

    public LocalVariable getCleartextVariable() {
        return cleartextVariable;
    }

    public void setCleartextVariable(LocalVariable cleartextVariable) {
        this.cleartextVariable = cleartextVariable;
    }

    public Expression getAddAuthenticator() {
        return addAuthenticator;
    }

    public void setAddAuthenticator(Expression addAuthenticator) {
        this.addAuthenticator = addAuthenticator;
    }

    public LocalVariable getAddAuthenticatorVariable() {
        return addAuthenticatorVariable;
    }

    public void setAddAuthenticatorVariable(LocalVariable addAuthenticatorVariable) {
        this.addAuthenticatorVariable = addAuthenticatorVariable;
    }

    public Expression getAuthenticator() {
        return authenticator;
    }

    public void setAuthenticator(Expression authenticator) {
        this.authenticator = authenticator;
    }

    public LocalVariable getAuthenticatorVariable() {
        return authenticatorVariable;
    }

    public void setAuthenticatorVariable(LocalVariable authenticatorVariable) {
        this.authenticatorVariable = authenticatorVariable;
    }
}
