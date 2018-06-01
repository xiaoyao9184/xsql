package com.xy.xsql.tsql.model.functions.cryptographic;

import com.xy.xsql.tsql.model.datatypes.Uniqueidentifier;
import com.xy.xsql.tsql.model.datatypes.string.VarBinary;
import com.xy.xsql.tsql.model.elements.expressions.Expression;
import com.xy.xsql.tsql.model.elements.variables.LocalVariable;
import com.xy.xsql.tsql.model.functions.Function;

/**
 * Created by xiaoyao9184 on 2018/5/29.
 */
public class SignByAsymKey
        implements CryptographicFunction, Function.InternalFunction {

    private Expression asymKeyId;
    private LocalVariable plaintext;
    private Expression password;

    public Expression getAsymKeyId() {
        return asymKeyId;
    }

    public void setAsymKeyId(Expression asymKeyId) {
        this.asymKeyId = asymKeyId;
    }

    public LocalVariable getPlaintext() {
        return plaintext;
    }

    public void setPlaintext(LocalVariable plaintext) {
        this.plaintext = plaintext;
    }

    public Expression getPassword() {
        return password;
    }

    public void setPassword(Expression password) {
        this.password = password;
    }
}
