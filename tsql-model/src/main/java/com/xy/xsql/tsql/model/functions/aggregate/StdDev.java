package com.xy.xsql.tsql.model.functions.aggregate;

import com.xy.xsql.tsql.model.functions.Function;

/**
 * Created by xiaoyao9184 on 2018/5/29.
 */
public class StdDev
        extends AggregateFunction.AllDistinctOrder
        implements AggregateFunction, Function.InternalFunction {

    public StdDev(){
        setKeyword(Keywords.STDEV);
    }

}
