package com.xy.xsql.tsql.model.functions.text;

import com.xy.xsql.tsql.model.datatypes.table.ColumnName;
import com.xy.xsql.tsql.model.functions.Function;

/**
 * Created by xiaoyao9184 on 2018/5/29.
 */
public class TextPtr
        implements TextFunction, Function.InternalFunction {

    private ColumnName column;

    public ColumnName getColumn() {
        return column;
    }

    public void setColumn(ColumnName column) {
        this.column = column;
    }
}
