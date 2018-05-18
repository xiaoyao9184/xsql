package com.xy.xsql.tsql.model.datatypes.precision_scale_length;

/**
 * number of digits the right of the decimal point
 * https://docs.microsoft.com/en-us/sql/t-sql/data-types/precision-scale-and-length-transact-sql?view=sql-server-2017
 * Created by xiaoyao9184 on 2018/5/18.
 */
public interface Scale {

    int scale();

    boolean scaleUsed();
}
