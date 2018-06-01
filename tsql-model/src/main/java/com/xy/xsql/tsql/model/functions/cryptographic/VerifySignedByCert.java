package com.xy.xsql.tsql.model.functions.cryptographic;

import com.xy.xsql.tsql.model.datatypes.numeric.Int;
import com.xy.xsql.tsql.model.datatypes.string.VarBinary;
import com.xy.xsql.tsql.model.elements.expressions.Expression;
import com.xy.xsql.tsql.model.elements.variables.LocalVariable;
import com.xy.xsql.tsql.model.functions.Function;

/**
 * Created by xiaoyao9184 on 2018/5/29.
 */
public class VerifySignedByCert
        implements CryptographicFunction, Function.InternalFunction {

    private Expression certId;
    private LocalVariable signedData;
    private Expression signature;

    public Expression getCertId() {
        return certId;
    }

    public void setCertId(Expression certId) {
        this.certId = certId;
    }

    public LocalVariable getSignedData() {
        return signedData;
    }

    public void setSignedData(LocalVariable signedData) {
        this.signedData = signedData;
    }

    public Expression getSignature() {
        return signature;
    }

    public void setSignature(Expression signature) {
        this.signature = signature;
    }
}
