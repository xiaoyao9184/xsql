package com.xy.xsql.tsql.model.functions.system;

import com.xy.xsql.tsql.model.datatypes.constants.StringConstant;
import com.xy.xsql.tsql.model.functions.Function;

/**
 * Created by xiaoyao9184 on 2018/5/29.
 */
public class Session_Context
        implements SystemFunction, Function.InternalFunction {

    private StringConstant key;

    public StringConstant getKey() {
        return key;
    }

    public void setKey(StringConstant key) {
        this.key = key;
    }
}
