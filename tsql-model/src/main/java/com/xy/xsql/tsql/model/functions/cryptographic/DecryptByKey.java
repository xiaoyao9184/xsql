package com.xy.xsql.tsql.model.functions.cryptographic;

import com.xy.xsql.tsql.model.datatypes.constants.StringConstant;
import com.xy.xsql.tsql.model.elements.expressions.Expression;
import com.xy.xsql.tsql.model.elements.variables.LocalVariable;
import com.xy.xsql.tsql.model.functions.Function;

/**
 * Created by xiaoyao9184 on 2018/5/29.
 */
public class DecryptByKey
        implements CryptographicFunction, Function.InternalFunction {

    private StringConstant ciphertext;
    private LocalVariable ciphertextVariable;
    private Expression addAuthenticator;
    private Expression authenticator;
    private LocalVariable authenticatorVariable;

    public StringConstant getCiphertext() {
        return ciphertext;
    }

    public void setCiphertext(StringConstant ciphertext) {
        this.ciphertext = ciphertext;
    }

    public LocalVariable getCiphertextVariable() {
        return ciphertextVariable;
    }

    public void setCiphertextVariable(LocalVariable ciphertextVariable) {
        this.ciphertextVariable = ciphertextVariable;
    }

    public Expression getAddAuthenticator() {
        return addAuthenticator;
    }

    public void setAddAuthenticator(Expression addAuthenticator) {
        this.addAuthenticator = addAuthenticator;
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
