package com.xy.xsql.tsql.model.functions.system;

import com.xy.xsql.tsql.model.datatypes.constants.StringConstant;
import com.xy.xsql.tsql.model.functions.Function;

/**
 * Created by xiaoyao9184 on 2018/5/29.
 */
public class Getansinull
        implements SystemFunction, Function.InternalFunction {

    private StringConstant database;

    public StringConstant getDatabase() {
        return database;
    }

    public void setDatabase(StringConstant database) {
        this.database = database;
    }
}
