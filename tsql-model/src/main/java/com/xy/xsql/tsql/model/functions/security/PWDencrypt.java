package com.xy.xsql.tsql.model.functions.security;

import com.xy.xsql.tsql.model.datatypes.constants.StringConstant;
import com.xy.xsql.tsql.model.elements.expressions.Expression;
import com.xy.xsql.tsql.model.functions.Function;

/**
 * Created by xiaoyao9184 on 2018/5/29.
 */
public class PWDencrypt
        implements SecurityFunction, Function.InternalFunction {

    private StringConstant password;

    public StringConstant getPassword() {
        return password;
    }

    public void setPassword(StringConstant password) {
        this.password = password;
    }
}
