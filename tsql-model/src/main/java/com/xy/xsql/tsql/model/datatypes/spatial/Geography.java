package com.xy.xsql.tsql.model.datatypes.spatial;

import com.xy.xsql.tsql.model.datatypes.DataType.*;

/**
 * Created by xiaoyao9184 on 2018/5/18.
 */
public class Geography
        extends SpatialDataType
        implements KeywordNamed {
    @Override
    public Keywords keyword() {
        return Keywords.geography;
    }
}
