package com.xy.xsql.tsql.model.functions.cryptographic;

import com.xy.xsql.tsql.model.datatypes.numeric.Int;
import com.xy.xsql.tsql.model.datatypes.string.VarBinary;
import com.xy.xsql.tsql.model.elements.expressions.Expression;
import com.xy.xsql.tsql.model.elements.variables.LocalVariable;
import com.xy.xsql.tsql.model.functions.Function;

/**
 * Created by xiaoyao9184 on 2018/5/29.
 */
public class VerifySignedByAsymKey
        implements CryptographicFunction, Function.InternalFunction {

    private Expression asymKeyId;
    private LocalVariable cleartext;
    private Expression signature;

    public Expression getAsymKeyId() {
        return asymKeyId;
    }

    public void setAsymKeyId(Expression asymKeyId) {
        this.asymKeyId = asymKeyId;
    }

    public LocalVariable getCleartext() {
        return cleartext;
    }

    public void setCleartext(LocalVariable cleartext) {
        this.cleartext = cleartext;
    }

    public Expression getSignature() {
        return signature;
    }

    public void setSignature(Expression signature) {
        this.signature = signature;
    }
}
