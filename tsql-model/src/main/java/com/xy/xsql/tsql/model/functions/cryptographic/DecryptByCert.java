package com.xy.xsql.tsql.model.functions.cryptographic;

import com.xy.xsql.tsql.model.datatypes.constants.StringConstant;
import com.xy.xsql.tsql.model.datatypes.numeric.NumericDataType;
import com.xy.xsql.tsql.model.elements.expressions.Expression;
import com.xy.xsql.tsql.model.elements.variables.LocalVariable;
import com.xy.xsql.tsql.model.functions.Function;

/**
 * Created by xiaoyao9184 on 2018/5/29.
 */
public class DecryptByCert
        implements CryptographicFunction, Function.InternalFunction {

    private Expression certificateId;
    private StringConstant ciphertext;
    private LocalVariable ciphertextVariable;
    private StringConstant certPassword;
    private LocalVariable certPasswordVariable;

    public Expression getCertificateId() {
        return certificateId;
    }

    public void setCertificateId(Expression certificateId) {
        this.certificateId = certificateId;
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

    public StringConstant getCertPassword() {
        return certPassword;
    }

    public void setCertPassword(StringConstant certPassword) {
        this.certPassword = certPassword;
    }

    public LocalVariable getCertPasswordVariable() {
        return certPasswordVariable;
    }

    public void setCertPasswordVariable(LocalVariable certPasswordVariable) {
        this.certPasswordVariable = certPasswordVariable;
    }
}
