package com.xy.xsql.tsql.model.functions.ranking;

import com.xy.xsql.tsql.model.functions.Function;
import com.xy.xsql.tsql.model.queries.select.Over;

/**
 * Created by xiaoyao9184 on 2018/5/29.
 */
public class Dense_Rank
        implements RankingFunction, Function.InternalFunction {

    private Over.PartitionBy partitionBy;
    private Over.OrderBy orderBy;

    public Over.PartitionBy getPartitionBy() {
        return partitionBy;
    }

    public void setPartitionBy(Over.PartitionBy partitionBy) {
        this.partitionBy = partitionBy;
    }

    public Over.OrderBy getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(Over.OrderBy orderBy) {
        this.orderBy = orderBy;
    }
}
