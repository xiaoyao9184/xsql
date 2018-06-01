package com.xy.xsql.tsql.model.functions.cryptographic;

import com.xy.xsql.tsql.model.datatypes.constants.NumberConstant;
import com.xy.xsql.tsql.model.datatypes.constants.StringConstant;
import com.xy.xsql.tsql.model.datatypes.numeric.Int;
import com.xy.xsql.tsql.model.elements.expressions.Expression;
import com.xy.xsql.tsql.model.elements.variables.LocalVariable;
import com.xy.xsql.tsql.model.functions.Function;

/**
 * Created by xiaoyao9184 on 2018/5/29.
 */
public class EncryptByAsymKey
        implements CryptographicFunction, Function.InternalFunction {
    private Expression asymKeyId;
    private StringConstant plaintext;
    private LocalVariable plaintextVariable;

    public Expression getAsymKeyId() {
        return asymKeyId;
    }

    public void setAsymKeyId(Expression asymKeyId) {
        this.asymKeyId = asymKeyId;
    }

    public StringConstant getPlaintext() {
        return plaintext;
    }

    public void setPlaintext(StringConstant plaintext) {
        this.plaintext = plaintext;
    }

    public LocalVariable getPlaintextVariable() {
        return plaintextVariable;
    }

    public void setPlaintextVariable(LocalVariable plaintextVariable) {
        this.plaintextVariable = plaintextVariable;
    }
}
