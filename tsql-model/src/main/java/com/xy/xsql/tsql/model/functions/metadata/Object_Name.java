package com.xy.xsql.tsql.model.functions.metadata;

import com.xy.xsql.tsql.model.datatypes.table.TableName;
import com.xy.xsql.tsql.model.elements.expressions.Expression;
import com.xy.xsql.tsql.model.functions.Function;
import com.xy.xsql.tsql.model.functions.security.SecurityFunction;

/**
 * Created by xiaoyao9184 on 2018/5/29.
 */
public class Object_Name
        implements MetaDataFunction, Function.InternalFunction {

    private Expression objectId;
    private Expression databaseId;

    public Expression getObjectId() {
        return objectId;
    }

    public void setObjectId(Expression objectId) {
        this.objectId = objectId;
    }

    public Expression getDatabaseId() {
        return databaseId;
    }

    public void setDatabaseId(Expression databaseId) {
        this.databaseId = databaseId;
    }
}
