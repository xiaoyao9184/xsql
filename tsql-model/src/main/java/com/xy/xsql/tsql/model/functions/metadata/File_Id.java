package com.xy.xsql.tsql.model.functions.metadata;

import com.xy.xsql.tsql.model.elements.expressions.Expression;
import com.xy.xsql.tsql.model.functions.Function;
import com.xy.xsql.tsql.model.functions.security.SecurityFunction;

/**
 * Created by xiaoyao9184 on 2018/5/29.
 */
public class File_Id
        implements MetaDataFunction, Function.InternalFunction {

    private Expression fileName;

    public Expression getFileName() {
        return fileName;
    }

    public void setFileName(Expression fileName) {
        this.fileName = fileName;
    }
}
