package com.xy.xsql.tsql.model.functions.systemstatistical;

import com.xy.xsql.tsql.model.elements.expressions.Expression;
import com.xy.xsql.tsql.model.functions.Function;

/**
 * Created by xiaoyao9184 on 2018/5/29.
 */
public class fn_virtualfilestats
        implements SystemStatisticalFunction, Function.InternalFunction {

    private Expression databaseId;
    private Expression fileId;

    public Expression getDatabaseId() {
        return databaseId;
    }

    public void setDatabaseId(Expression databaseId) {
        this.databaseId = databaseId;
    }

    public Expression getFileId() {
        return fileId;
    }

    public void setFileId(Expression fileId) {
        this.fileId = fileId;
    }
}
