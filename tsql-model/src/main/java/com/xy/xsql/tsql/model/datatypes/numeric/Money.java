package com.xy.xsql.tsql.model.datatypes.numeric;

import com.xy.xsql.tsql.model.datatypes.DataType.*;

/**
 * Created by xiaoyao9184 on 2018/5/18.
 */
public class Money
        extends SimpleDataType
        implements KeywordNamed {
    @Override
    public Keywords keyword() {
        return Keywords.money;
    }
}
