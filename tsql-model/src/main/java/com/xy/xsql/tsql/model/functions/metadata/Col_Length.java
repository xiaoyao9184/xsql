package com.xy.xsql.tsql.model.functions.metadata;

import com.xy.xsql.tsql.model.datatypes.constants.StringConstant;
import com.xy.xsql.tsql.model.functions.Function;
import com.xy.xsql.tsql.model.functions.security.SecurityFunction;

/**
 * Created by xiaoyao9184 on 2018/5/29.
 */
public class Col_Length
        implements MetaDataFunction, Function.InternalFunction {

    private StringConstant table;
    private StringConstant column;

    public StringConstant getTable() {
        return table;
    }

    public void setTable(StringConstant table) {
        this.table = table;
    }

    public StringConstant getColumn() {
        return column;
    }

    public void setColumn(StringConstant column) {
        this.column = column;
    }
}
