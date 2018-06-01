package com.xy.xsql.tsql.model.functions.cryptographic;

import com.xy.xsql.tsql.model.datatypes.constants.StringConstant;
import com.xy.xsql.tsql.model.datatypes.numeric.Int;
import com.xy.xsql.tsql.model.datatypes.string.NVarChar;
import com.xy.xsql.tsql.model.elements.expressions.Expression;
import com.xy.xsql.tsql.model.elements.variables.LocalVariable;
import com.xy.xsql.tsql.model.functions.Function;

/**
 * Created by xiaoyao9184 on 2018/5/29.
 */
public class SymKeyProperty
        implements CryptographicFunction, Function.InternalFunction {

    private Expression keyId;
    private AsymKeyProperty.Properties properties;

    public Expression getKeyId() {
        return keyId;
    }

    public void setKeyId(Expression keyId) {
        this.keyId = keyId;
    }

    public AsymKeyProperty.Properties getProperties() {
        return properties;
    }

    public void setProperties(AsymKeyProperty.Properties properties) {
        this.properties = properties;
    }
}
