package com.xy.xsql.tsql.model.functions.trigger;

import com.xy.xsql.tsql.model.datatypes.table.ColumnName;
import com.xy.xsql.tsql.model.functions.Function;

/**
 * Created by xiaoyao9184 on 2018/5/29.
 */
public class Update
        implements TriggerFunction, Function.InternalFunction {

    private ColumnName column;

    public ColumnName getColumn() {
        return column;
    }

    public void setColumn(ColumnName column) {
        this.column = column;
    }
}
