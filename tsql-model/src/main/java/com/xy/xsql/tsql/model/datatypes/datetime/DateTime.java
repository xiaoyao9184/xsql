package com.xy.xsql.tsql.model.datatypes.datetime;

import com.xy.xsql.tsql.model.datatypes.DataType.*;

/**
 * Created by xiaoyao9184 on 2018/5/18.
 */
public class DateTime
        extends SimpleDataType
        implements InternalDataType {
    @Override
    public Keywords keyword() {
        return Keywords.datetime;
    }
}
