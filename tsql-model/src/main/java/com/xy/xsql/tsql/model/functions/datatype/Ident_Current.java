package com.xy.xsql.tsql.model.functions.datatype;

import com.xy.xsql.tsql.model.elements.expressions.Expression;
import com.xy.xsql.tsql.model.functions.Function;

/**
 * Created by xiaoyao9184 on 2018/5/29.
 */
public class Ident_Current
        implements DataTypeFunction, Function.InternalFunction {

    private Expression tableName;

    public Expression getTableName() {
        return tableName;
    }

    public void setTableName(Expression tableName) {
        this.tableName = tableName;
    }
}
