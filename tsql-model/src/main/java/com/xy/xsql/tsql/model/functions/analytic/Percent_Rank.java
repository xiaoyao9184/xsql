package com.xy.xsql.tsql.model.functions.analytic;

import com.xy.xsql.tsql.model.functions.Function;
import com.xy.xsql.tsql.model.queries.select.Over;

/**
 * Created by xiaoyao9184 on 2018/5/29.
 */
public class Percent_Rank
        implements AnalyticFunction, Function.InternalFunction {

    //OVER ( [ partition_by_clause ] order_by_clause )
    private Over over;

    public Over getOver() {
        return over;
    }

    public void setOver(Over over) {
        this.over = over;
    }
}
