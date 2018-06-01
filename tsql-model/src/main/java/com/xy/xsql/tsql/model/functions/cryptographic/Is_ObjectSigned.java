package com.xy.xsql.tsql.model.functions.cryptographic;

import com.xy.xsql.tsql.model.datatypes.constants.StringConstant;
import com.xy.xsql.tsql.model.elements.expressions.Expression;
import com.xy.xsql.tsql.model.elements.variables.LocalVariable;
import com.xy.xsql.tsql.model.functions.Function;

/**
 * Created by xiaoyao9184 on 2018/5/29.
 */
public class Is_ObjectSigned
        implements CryptographicFunction, Function.InternalFunction {

    private Expression objectId;
    private Expression clazz;
    private Expression thumbprint;

    public Expression getObjectId() {
        return objectId;
    }

    public void setObjectId(Expression objectId) {
        this.objectId = objectId;
    }

    public Expression getClazz() {
        return clazz;
    }

    public void setClazz(Expression clazz) {
        this.clazz = clazz;
    }

    public Expression getThumbprint() {
        return thumbprint;
    }

    public void setThumbprint(Expression thumbprint) {
        this.thumbprint = thumbprint;
    }
}
