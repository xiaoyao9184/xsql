package com.xy.xsql.tsql.model.functions.rowset;

import com.xy.xsql.tsql.model.datatypes.constants.StringConstant;
import com.xy.xsql.tsql.model.elements.expressions.Expression;
import com.xy.xsql.tsql.model.functions.Function;

/**
 * Created by xiaoyao9184 on 2018/5/29.
 */
public class OpenQuery
        implements RowsetFunction, Function.InternalFunction {

    private String linkedServer;
    private StringConstant query;

    public String getLinkedServer() {
        return linkedServer;
    }

    public void setLinkedServer(String linkedServer) {
        this.linkedServer = linkedServer;
    }

    public StringConstant getQuery() {
        return query;
    }

    public void setQuery(StringConstant query) {
        this.query = query;
    }
}
