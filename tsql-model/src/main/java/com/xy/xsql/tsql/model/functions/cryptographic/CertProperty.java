package com.xy.xsql.tsql.model.functions.cryptographic;

import com.xy.xsql.tsql.model.datatypes.constants.NumberConstant;
import com.xy.xsql.tsql.model.datatypes.numeric.NumericDataType;
import com.xy.xsql.tsql.model.elements.expressions.Expression;
import com.xy.xsql.tsql.model.functions.Function;

/**
 * Created by xiaoyao9184 on 2018/5/29.
 */
public class CertProperty
        implements CryptographicFunction, Function.InternalFunction {

    private Expression certId;
    private Properties properties;

    public Expression getCertId() {
        return certId;
    }

    public void setCertId(Expression certId) {
        this.certId = certId;
    }

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    public enum Properties {
        Expiry_Date,
        Start_Date,
        Issuer_Name,
        Cert_Serial_Number,
        Subject,
        SID,
        String_SID;

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
