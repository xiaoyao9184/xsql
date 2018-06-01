package com.xy.xsql.tsql.model.functions.metadata;

import com.xy.xsql.tsql.model.datatypes.constants.StringConstant;
import com.xy.xsql.tsql.model.elements.expressions.Expression;
import com.xy.xsql.tsql.model.functions.Function;
import com.xy.xsql.tsql.model.functions.security.SecurityFunction;

/**
 * Created by xiaoyao9184 on 2018/5/29.
 */
public class FileGroup_Name
        implements MetaDataFunction, Function.InternalFunction {

    private Expression filegroupId;

    public Expression getFilegroupId() {
        return filegroupId;
    }

    public void setFilegroupId(Expression filegroupId) {
        this.filegroupId = filegroupId;
    }
}
