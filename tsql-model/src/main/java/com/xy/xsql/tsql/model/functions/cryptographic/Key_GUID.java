package com.xy.xsql.tsql.model.functions.cryptographic;

import com.xy.xsql.tsql.model.datatypes.constants.StringConstant;
import com.xy.xsql.tsql.model.functions.Function;

/**
 * Created by xiaoyao9184 on 2018/5/29.
 */
public class Key_GUID
        implements CryptographicFunction, Function.InternalFunction {

    private StringConstant keyName;

    public StringConstant getKeyName() {
        return keyName;
    }

    public void setKeyName(StringConstant keyName) {
        this.keyName = keyName;
    }
}
