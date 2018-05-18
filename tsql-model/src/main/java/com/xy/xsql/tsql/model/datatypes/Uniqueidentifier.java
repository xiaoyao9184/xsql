package com.xy.xsql.tsql.model.datatypes;

import com.xy.xsql.tsql.model.datatypes.DataType.*;

/**
 * Created by xiaoyao9184 on 2018/5/18.
 */
public class Uniqueidentifier
        extends SimpleDataType
        implements KeywordNamed {
    @Override
    public Keywords keyword() {
        return Keywords.uniqueidentifier;
    }
}
