package com.xy.xsql.tsql.model.functions.cryptographic;

import com.xy.xsql.tsql.model.datatypes.constants.StringConstant;
import com.xy.xsql.tsql.model.datatypes.numeric.NumericDataType;
import com.xy.xsql.tsql.model.elements.expressions.Expression;
import com.xy.xsql.tsql.model.elements.variables.LocalVariable;
import com.xy.xsql.tsql.model.functions.Function;

/**
 * Created by xiaoyao9184 on 2018/5/29.
 */
public class DecryptByAsymKey
        implements CryptographicFunction, Function.InternalFunction {

    private Expression asymKeyId;
    private StringConstant ciphertext;
    private LocalVariable ciphertextVariable;
    private StringConstant asymKeyPassword;

    public Expression getAsymKeyId() {
        return asymKeyId;
    }

    public void setAsymKeyId(Expression asymKeyId) {
        this.asymKeyId = asymKeyId;
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

    public StringConstant getAsymKeyPassword() {
        return asymKeyPassword;
    }

    public void setAsymKeyPassword(StringConstant asymKeyPassword) {
        this.asymKeyPassword = asymKeyPassword;
    }
}
