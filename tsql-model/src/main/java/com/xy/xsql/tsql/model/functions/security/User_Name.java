package com.xy.xsql.tsql.model.functions.security;

import com.xy.xsql.tsql.model.datatypes.constants.StringConstant;
import com.xy.xsql.tsql.model.elements.expressions.Expression;
import com.xy.xsql.tsql.model.functions.Function;

/**
 * Created by xiaoyao9184 on 2018/5/29.
 */
public class User_Name
        implements SecurityFunction, Function.InternalFunction {

    private Expression id;

    public Expression getId() {
        return id;
    }

    public void setId(Expression id) {
        this.id = id;
    }
}
