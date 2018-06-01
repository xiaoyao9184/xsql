package com.xy.xsql.tsql.model.functions.metadata;

import com.xy.xsql.tsql.model.elements.expressions.Expression;
import com.xy.xsql.tsql.model.functions.Function;
import com.xy.xsql.tsql.model.functions.security.SecurityFunction;

/**
 * Created by xiaoyao9184 on 2018/5/29.
 */
public class File_Name
        implements MetaDataFunction, Function.InternalFunction {

    private Expression fileId;

    public Expression getFileId() {
        return fileId;
    }

    public void setFileId(Expression fileId) {
        this.fileId = fileId;
    }
}
