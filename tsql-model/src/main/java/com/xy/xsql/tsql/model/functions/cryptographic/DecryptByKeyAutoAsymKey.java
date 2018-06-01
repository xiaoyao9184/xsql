package com.xy.xsql.tsql.model.functions.cryptographic;

import com.xy.xsql.tsql.model.datatypes.constants.NumberConstant;
import com.xy.xsql.tsql.model.datatypes.constants.StringConstant;
import com.xy.xsql.tsql.model.datatypes.numeric.Int;
import com.xy.xsql.tsql.model.datatypes.string.NVarChar;
import com.xy.xsql.tsql.model.elements.expressions.Expression;
import com.xy.xsql.tsql.model.elements.variables.LocalVariable;
import com.xy.xsql.tsql.model.functions.Function;

/**
 * Created by xiaoyao9184 on 2018/5/29.
 */
public class DecryptByKeyAutoAsymKey
        implements CryptographicFunction, Function.InternalFunction {
    private Expression akeyId;
    private Expression akeyPassword;
    private StringConstant ciphertext;
    private LocalVariable ciphertextVariable;
    private NumberConstant addAuthenticator;
    private LocalVariable addAuthenticatorVariable;
    private StringConstant authenticator;
    private LocalVariable authenticatorVariable;

    public Expression getAkeyId() {
        return akeyId;
    }

    public void setAkeyId(Expression akeyId) {
        this.akeyId = akeyId;
    }

    public Expression getAkeyPassword() {
        return akeyPassword;
    }

    public void setAkeyPassword(Expression akeyPassword) {
        this.akeyPassword = akeyPassword;
    }

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

    public NumberConstant getAddAuthenticator() {
        return addAuthenticator;
    }

    public void setAddAuthenticator(NumberConstant addAuthenticator) {
        this.addAuthenticator = addAuthenticator;
    }

    public LocalVariable getAddAuthenticatorVariable() {
        return addAuthenticatorVariable;
    }

    public void setAddAuthenticatorVariable(LocalVariable addAuthenticatorVariable) {
        this.addAuthenticatorVariable = addAuthenticatorVariable;
    }

    public StringConstant getAuthenticator() {
        return authenticator;
    }

    public void setAuthenticator(StringConstant authenticator) {
        this.authenticator = authenticator;
    }

    public LocalVariable getAuthenticatorVariable() {
        return authenticatorVariable;
    }

    public void setAuthenticatorVariable(LocalVariable authenticatorVariable) {
        this.authenticatorVariable = authenticatorVariable;
    }
}
