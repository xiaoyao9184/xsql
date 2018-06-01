package com.xy.xsql.tsql.model.functions.security;

import com.xy.xsql.tsql.model.datatypes.constants.StringConstant;
import com.xy.xsql.tsql.model.elements.expressions.Expression;
import com.xy.xsql.tsql.model.functions.Function;

/**
 * Created by xiaoyao9184 on 2018/5/29.
 */
public class Has_Perms_By_Name
        implements SecurityFunction, Function.InternalFunction {

    private Expression securable;
    private Expression securableClass;
    private Expression permission;
    private Expression subSecurable;
    private Expression subSecurableClass;

    public Expression getSecurable() {
        return securable;
    }

    public void setSecurable(Expression securable) {
        this.securable = securable;
    }

    public Expression getSecurableClass() {
        return securableClass;
    }

    public void setSecurableClass(Expression securableClass) {
        this.securableClass = securableClass;
    }

    public Expression getPermission() {
        return permission;
    }

    public void setPermission(Expression permission) {
        this.permission = permission;
    }

    public Expression getSubSecurable() {
        return subSecurable;
    }

    public void setSubSecurable(Expression subSecurable) {
        this.subSecurable = subSecurable;
    }

    public Expression getSubSecurableClass() {
        return subSecurableClass;
    }

    public void setSubSecurableClass(Expression subSecurableClass) {
        this.subSecurableClass = subSecurableClass;
    }
}
