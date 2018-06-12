package com.xy.xsql.tsql.model.functions.datatype;

import com.xy.xsql.tsql.model.elements.expressions.Expression;
import com.xy.xsql.tsql.model.functions.Function;

/**
 * Created by xiaoyao9184 on 2018/5/29.
 */
public class Ident_Seed
        implements DataTypeFunction, Function.InternalFunction {

    private Expression tableOrView;

    public Expression getTableOrView() {
        return tableOrView;
    }

    public void setTableOrView(Expression tableOrView) {
        this.tableOrView = tableOrView;
    }
}
