package com.xy.xsql.tsql.model.functions.security;

import com.xy.xsql.tsql.model.datatypes.constants.StringConstant;
import com.xy.xsql.tsql.model.functions.Function;

/**
 * Created by xiaoyao9184 on 2018/5/29.
 */
public class LoginProperty
        implements SecurityFunction, Function.InternalFunction {

    private StringConstant loginName;
    private StringConstant propertyName;

    public StringConstant getLoginName() {
        return loginName;
    }

    public void setLoginName(StringConstant loginName) {
        this.loginName = loginName;
    }

    public StringConstant getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(StringConstant propertyName) {
        this.propertyName = propertyName;
    }
}
