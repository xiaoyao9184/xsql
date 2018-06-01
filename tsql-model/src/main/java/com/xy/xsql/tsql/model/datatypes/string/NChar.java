package com.xy.xsql.tsql.model.datatypes.string;

import com.xy.xsql.tsql.model.datatypes.DataType.*;

/**
 * Created by xiaoyao9184 on 2018/5/18.
 */
public class NChar
        extends FixedLengthDataType
        implements InternalDataType {
    @Override
    public Keywords keyword() {
        return Keywords.nchar;
    }
}
