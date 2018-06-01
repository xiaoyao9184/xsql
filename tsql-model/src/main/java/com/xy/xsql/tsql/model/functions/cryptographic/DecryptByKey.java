package com.xy.xsql.tsql.model.functions.cryptographic;

import com.xy.xsql.tsql.model.datatypes.constants.NumberConstant;
import com.xy.xsql.tsql.model.datatypes.constants.StringConstant;
import com.xy.xsql.tsql.model.datatypes.numeric.NumericDataType;
import com.xy.xsql.tsql.model.elements.variables.LocalVariable;
import com.xy.xsql.tsql.model.functions.Function;

/**
 * Created by xiaoyao9184 on 2018/5/29.
 */
public class DecryptByKey
        implements CryptographicFunction, Function.InternalFunction {

    private StringConstant ciphertext;
    private LocalVariable ciphertextVariable;
    private NumberConstant addAuthenticator;
    private StringConstant authenticator;
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

    public NumberConstant getAddAuthenticator() {
        return addAuthenticator;
    }

    public void setAddAuthenticator(NumberConstant addAuthenticator) {
        this.addAuthenticator = addAuthenticator;
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
