package com.xy.xsql.tsql.model.functions.cryptographic;

import com.xy.xsql.tsql.model.datatypes.constants.NumberConstant;
import com.xy.xsql.tsql.model.datatypes.numeric.NumericDataType;
import com.xy.xsql.tsql.model.elements.expressions.Expression;
import com.xy.xsql.tsql.model.functions.Function;

/**
 * Created by xiaoyao9184 on 2018/5/29.
 */
public class AsymKeyProperty
        implements CryptographicFunction, Function.InternalFunction {

    private Expression keyId;
    private Properties properties;

    public Expression getKeyId() {
        return keyId;
    }

    public void setKeyId(Expression keyId) {
        this.keyId = keyId;
    }

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    public enum Properties {
        algorithm_desc,
        string_sid,
        sid;

        private String string;

        Properties(){
            this.string = "'" + this.name() + "'";
        }

        @Override
        public String toString(){
            return string;
        }
    }
}
