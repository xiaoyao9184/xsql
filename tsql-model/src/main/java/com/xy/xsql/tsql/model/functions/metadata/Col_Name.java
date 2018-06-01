package com.xy.xsql.tsql.model.functions.metadata;

import com.xy.xsql.tsql.model.datatypes.constants.StringConstant;
import com.xy.xsql.tsql.model.elements.expressions.Expression;
import com.xy.xsql.tsql.model.functions.Function;
import com.xy.xsql.tsql.model.functions.security.SecurityFunction;

/**
 * Created by xiaoyao9184 on 2018/5/29.
 */
public class Col_Name
        implements MetaDataFunction, Function.InternalFunction {

    private Expression tableId;
    private Expression columnId;

    public Expression getTableId() {
        return tableId;
    }

    public void setTableId(Expression tableId) {
        this.tableId = tableId;
    }

    public Expression getColumnId() {
        return columnId;
    }

    public void setColumnId(Expression columnId) {
        this.columnId = columnId;
    }
}
