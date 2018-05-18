package com.xy.xsql.tsql.model.datatypes.datetime;

import com.xy.xsql.tsql.model.datatypes.DataType.*;

/**
 * Created by xiaoyao9184 on 2018/5/18.
 */
public class DateTimeOffset
        extends FractionalSecondsPrecisionDataType
        implements KeywordNamed {
    @Override
    public Keywords keyword() {
        return Keywords.datetimeoffset;
    }
}
