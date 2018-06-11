package com.xy.xsql.tsql.model.functions.metadata;

import com.xy.xsql.tsql.model.datatypes.table.TableName;
import com.xy.xsql.tsql.model.elements.expressions.Expression;
import com.xy.xsql.tsql.model.functions.Function;
import com.xy.xsql.tsql.model.functions.security.SecurityFunction;

/**
 * Created by xiaoyao9184 on 2018/5/29.
 */
public class Object_Id
        implements MetaDataFunction, Function.InternalFunction {

    private Expression objectName;
    private Expression objectType;

    public Expression getObjectName() {
        return objectName;
    }

    public void setObjectName(Expression objectName) {
        this.objectName = objectName;
    }

    public Expression getObjectType() {
        return objectType;
    }

    public void setObjectType(Expression objectType) {
        this.objectType = objectType;
    }
}
