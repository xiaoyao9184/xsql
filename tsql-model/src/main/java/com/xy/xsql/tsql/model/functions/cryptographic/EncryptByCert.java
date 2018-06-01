package com.xy.xsql.tsql.model.functions.cryptographic;

import com.xy.xsql.tsql.model.datatypes.constants.StringConstant;
import com.xy.xsql.tsql.model.datatypes.numeric.Int;
import com.xy.xsql.tsql.model.elements.expressions.Expression;
import com.xy.xsql.tsql.model.elements.variables.LocalVariable;
import com.xy.xsql.tsql.model.functions.Function;

/**
 * Created by xiaoyao9184 on 2018/5/29.
 */
public class EncryptByCert
        implements CryptographicFunction, Function.InternalFunction {
    private Expression certificateId;
    private StringConstant cleartext;
    private LocalVariable cleartextVariable;

    public Expression getCertificateId() {
        return certificateId;
    }

    public void setCertificateId(Expression certificateId) {
        this.certificateId = certificateId;
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
}
