package com.xy.xsql.tsql.model.functions.security;

import com.xy.xsql.tsql.model.datatypes.constants.StringConstant;
import com.xy.xsql.tsql.model.functions.Function;

/**
 * Created by xiaoyao9184 on 2018/5/29.
 */
public class User_Name
        implements SecurityFunction, Function.InternalFunction {

    private StringConstant id;

    public StringConstant getId() {
        return id;
    }

    public void setId(StringConstant id) {
        this.id = id;
    }
}
