package com.xy.xsql.tsql.model.datatypes;

import com.xy.xsql.tsql.model.datatypes.DataType.*;

/**
 * Created by xiaoyao9184 on 2018/5/18.
 */
public class RowVersion
        extends SimpleDataType
        implements InternalDataType {
    @Override
    public DataType.Keywords keyword() {
        return DataType.Keywords.rowversion;
    }
}
