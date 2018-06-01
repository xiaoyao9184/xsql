package com.xy.xsql.tsql.model.functions.security;

import com.xy.xsql.tsql.model.datatypes.constants.StringConstant;
import com.xy.xsql.tsql.model.elements.expressions.Expression;
import com.xy.xsql.tsql.model.functions.Function;

/**
 * Created by xiaoyao9184 on 2018/5/29.
 */
public class CertPrivateKey
        implements SecurityFunction, Function.InternalFunction {

    private Expression certId;
    private StringConstant encryptionPassword;
    private StringConstant decryptionPassword ;

    public Expression getCertId() {
        return certId;
    }

    public void setCertId(Expression certId) {
        this.certId = certId;
    }

    public StringConstant getEncryptionPassword() {
        return encryptionPassword;
    }

    public void setEncryptionPassword(StringConstant encryptionPassword) {
        this.encryptionPassword = encryptionPassword;
    }

    public StringConstant getDecryptionPassword() {
        return decryptionPassword;
    }

    public void setDecryptionPassword(StringConstant decryptionPassword) {
        this.decryptionPassword = decryptionPassword;
    }
}
