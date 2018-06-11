package com.xy.xsql.tsql.model.functions.cryptographic;

import com.xy.xsql.tsql.model.datatypes.constants.StringConstant;
import com.xy.xsql.tsql.model.elements.expressions.Expression;
import com.xy.xsql.tsql.model.elements.variables.LocalVariable;
import com.xy.xsql.tsql.model.functions.Function;

/**
 * Created by xiaoyao9184 on 2018/5/29.
 */
public class DecryptByPassPhrase
        implements CryptographicFunction, Function.InternalFunction {
    private StringConstant passphrase;
    private LocalVariable passphraseVariable;
    private StringConstant ciphertext;
    private LocalVariable ciphertextVariable;
    private Expression addAuthenticator;
    private LocalVariable addAuthenticatorVariable;
    private Expression authenticator;
    private LocalVariable authenticatorVariable;

    public StringConstant getPassphrase() {
        return passphrase;
    }

    public void setPassphrase(StringConstant passphrase) {
        this.passphrase = passphrase;
    }

    public LocalVariable getPassphraseVariable() {
        return passphraseVariable;
    }

    public void setPassphraseVariable(LocalVariable passphraseVariable) {
        this.passphraseVariable = passphraseVariable;
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
