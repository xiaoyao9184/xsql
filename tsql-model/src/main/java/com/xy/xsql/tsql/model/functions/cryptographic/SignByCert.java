package com.xy.xsql.tsql.model.functions.cryptographic;

import com.xy.xsql.tsql.model.datatypes.Uniqueidentifier;
import com.xy.xsql.tsql.model.datatypes.numeric.Int;
import com.xy.xsql.tsql.model.datatypes.string.NVarChar;
import com.xy.xsql.tsql.model.datatypes.string.VarBinary;
import com.xy.xsql.tsql.model.elements.expressions.Expression;
import com.xy.xsql.tsql.model.elements.variables.LocalVariable;
import com.xy.xsql.tsql.model.functions.Function;
import org.omg.CORBA.INTERNAL;

/**
 * Created by xiaoyao9184 on 2018/5/29.
 */
public class SignByCert
        implements CryptographicFunction, Function.InternalFunction {

    private Expression certificateId;
    private LocalVariable cleartext;
    private Expression password;

    public Expression getCertificateId() {
        return certificateId;
    }

    public void setCertificateId(Expression certificateId) {
        this.certificateId = certificateId;
    }

    public LocalVariable getCleartext() {
        return cleartext;
    }

    public void setCleartext(LocalVariable cleartext) {
        this.cleartext = cleartext;
    }

    public Expression getPassword() {
        return password;
    }

    public void setPassword(Expression password) {
        this.password = password;
    }
}
