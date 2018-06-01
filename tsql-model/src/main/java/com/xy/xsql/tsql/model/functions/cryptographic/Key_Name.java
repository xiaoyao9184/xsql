package com.xy.xsql.tsql.model.functions.cryptographic;

import com.xy.xsql.tsql.model.datatypes.Uniqueidentifier;
import com.xy.xsql.tsql.model.datatypes.constants.StringConstant;
import com.xy.xsql.tsql.model.datatypes.string.VarBinary;
import com.xy.xsql.tsql.model.datatypes.string.VarChar;
import com.xy.xsql.tsql.model.elements.expressions.Expression;
import com.xy.xsql.tsql.model.functions.Function;

/**
 * Created by xiaoyao9184 on 2018/5/29.
 */
public class Key_Name
        implements CryptographicFunction, Function.InternalFunction {

    private Expression ciphertext;
    private Expression keyGuid;

    public Expression getCiphertext() {
        return ciphertext;
    }

    public void setCiphertext(Expression ciphertext) {
        this.ciphertext = ciphertext;
    }

    public Expression getKeyGuid() {
        return keyGuid;
    }

    public void setKeyGuid(Expression keyGuid) {
        this.keyGuid = keyGuid;
    }
}
