package com.xy.xsql.tsql.model.functions.cryptographic;

import com.xy.xsql.tsql.model.datatypes.constants.StringConstant;
import com.xy.xsql.tsql.model.functions.Function;

/**
 * Created by xiaoyao9184 on 2018/5/29.
 */
public class Cert_Id
        implements CryptographicFunction, Function.InternalFunction {

    private StringConstant certName;

    public StringConstant getCertName() {
        return certName;
    }

    public void setCertName(StringConstant certName) {
        this.certName = certName;
    }
}
