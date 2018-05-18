package com.xy.xsql.tsql.model.datatypes.datetime;

import com.xy.xsql.tsql.model.datatypes.DataType.*;

/**
 * Created by xiaoyao9184 on 2018/5/18.
 */
public class Time
        extends FractionalSecondsScaleDataType
        implements KeywordNamed {

    @Override
    public Keywords keyword() {
        return Keywords.time;
    }
}
