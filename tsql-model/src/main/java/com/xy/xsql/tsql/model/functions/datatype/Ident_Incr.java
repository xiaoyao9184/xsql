package com.xy.xsql.tsql.model.functions.datatype;

import com.xy.xsql.tsql.model.datatypes.constants.StringConstant;
import com.xy.xsql.tsql.model.functions.Function;

/**
 * Created by xiaoyao9184 on 2018/5/29.
 */
public class Ident_Incr
        implements DataTypeFunction, Function.InternalFunction {

    private StringConstant tableOrView;

    public StringConstant getTableOrView() {
        return tableOrView;
    }

    public void setTableOrView(StringConstant tableOrView) {
        this.tableOrView = tableOrView;
    }
}
