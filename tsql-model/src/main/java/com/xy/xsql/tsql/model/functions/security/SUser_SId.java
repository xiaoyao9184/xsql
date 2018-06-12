package com.xy.xsql.tsql.model.functions.security;

import com.xy.xsql.tsql.model.datatypes.constants.StringConstant;
import com.xy.xsql.tsql.model.elements.expressions.Expression;
import com.xy.xsql.tsql.model.functions.Function;

/**
 * Created by xiaoyao9184 on 2018/5/29.
 */
public class SUser_SId
        implements SecurityFunction, Function.InternalFunction {

    private StringConstant login;
    private Expression param2;

    public StringConstant getLogin() {
        return login;
    }

    public void setLogin(StringConstant login) {
        this.login = login;
    }

    public Expression getParam2() {
        return param2;
    }

    public void setParam2(Expression param2) {
        this.param2 = param2;
    }
}
