package com.xy.xsql.tsql.model.functions.cryptographic;

import com.xy.xsql.tsql.model.datatypes.constants.StringConstant;
import com.xy.xsql.tsql.model.functions.Function;

/**
 * Created by xiaoyao9184 on 2018/5/29.
 */
public class AsymKey_Id
        implements CryptographicFunction, Function.InternalFunction {

    private StringConstant asymKeyName;

    public StringConstant getAsymKeyName() {
        return asymKeyName;
    }

    public void setAsymKeyName(StringConstant asymKeyName) {
        this.asymKeyName = asymKeyName;
    }
}
