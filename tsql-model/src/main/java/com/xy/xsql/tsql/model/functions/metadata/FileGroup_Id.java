package com.xy.xsql.tsql.model.functions.metadata;

import com.xy.xsql.tsql.model.datatypes.constants.StringConstant;
import com.xy.xsql.tsql.model.elements.expressions.Expression;
import com.xy.xsql.tsql.model.functions.Function;
import com.xy.xsql.tsql.model.functions.security.SecurityFunction;

/**
 * Created by xiaoyao9184 on 2018/5/29.
 */
public class FileGroup_Id
        implements MetaDataFunction, Function.InternalFunction {

    private StringConstant filegroupName;

    public StringConstant getFilegroupName() {
        return filegroupName;
    }

    public void setFilegroupName(StringConstant filegroupName) {
        this.filegroupName = filegroupName;
    }
}
