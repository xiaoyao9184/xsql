package com.xy.xsql.tsql.model.functions.datatype;

import com.xy.xsql.tsql.model.datatypes.constants.StringConstant;
import com.xy.xsql.tsql.model.elements.expressions.Expression;
import com.xy.xsql.tsql.model.functions.Function;

/**
 * Created by xiaoyao9184 on 2018/5/29.
 */
public class Ident_Current
        implements DataTypeFunction, Function.InternalFunction {

    private StringConstant tableName;

    public StringConstant getTableName() {
        return tableName;
    }

    public void setTableName(StringConstant tableName) {
        this.tableName = tableName;
    }
}
