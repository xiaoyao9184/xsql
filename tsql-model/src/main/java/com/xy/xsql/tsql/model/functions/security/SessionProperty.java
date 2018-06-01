package com.xy.xsql.tsql.model.functions.security;

import com.xy.xsql.tsql.model.datatypes.constants.StringConstant;
import com.xy.xsql.tsql.model.functions.Function;

/**
 * Created by xiaoyao9184 on 2018/5/29.
 */
public class SessionProperty
        implements SecurityFunction, Function.InternalFunction {

    private StringConstant option;

    public StringConstant getOption() {
        return option;
    }

    public void setOption(StringConstant option) {
        this.option = option;
    }
}
