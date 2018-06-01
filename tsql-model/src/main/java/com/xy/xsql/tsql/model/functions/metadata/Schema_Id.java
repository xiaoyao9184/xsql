package com.xy.xsql.tsql.model.functions.metadata;

import com.xy.xsql.tsql.model.datatypes.constants.StringConstant;
import com.xy.xsql.tsql.model.elements.expressions.Expression;
import com.xy.xsql.tsql.model.functions.Function;
import com.xy.xsql.tsql.model.functions.security.SecurityFunction;

/**
 * Created by xiaoyao9184 on 2018/5/29.
 */
public class Schema_Id
        implements MetaDataFunction, Function.InternalFunction {

    private Expression schemaName;

    public Expression getSchemaName() {
        return schemaName;
    }

    public void setSchemaName(Expression schemaName) {
        this.schemaName = schemaName;
    }
}
