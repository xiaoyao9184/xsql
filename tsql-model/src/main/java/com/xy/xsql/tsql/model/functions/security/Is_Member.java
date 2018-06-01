package com.xy.xsql.tsql.model.functions.security;

import com.xy.xsql.tsql.model.datatypes.constants.StringConstant;
import com.xy.xsql.tsql.model.elements.expressions.Expression;
import com.xy.xsql.tsql.model.functions.Function;

/**
 * Created by xiaoyao9184 on 2018/5/29.
 */
public class Is_Member
        implements SecurityFunction, Function.InternalFunction {

    private StringConstant group;
    private StringConstant role;

    public StringConstant getGroup() {
        return group;
    }

    public void setGroup(StringConstant group) {
        this.group = group;
    }

    public StringConstant getRole() {
        return role;
    }

    public void setRole(StringConstant role) {
        this.role = role;
    }
}
