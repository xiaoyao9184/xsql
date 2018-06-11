package com.xy.xsql.tsql.builder.chain.functions.ranking;

import com.xy.xsql.tsql.model.functions.ranking.Rank;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.datatypes.table.ColumnNameFactory.c;
import static com.xy.xsql.tsql.builder.chain.functions.AggregateFunctions.f_sum;
import static com.xy.xsql.tsql.builder.chain.functions.RankingFunctions.f_rank;
import static com.xy.xsql.tsql.builder.chain.queries.Queries.$Over;
import static org.junit.Assert.assertNotNull;

/**
 * Created by xiaoyao9184 on 2018/6/5.
 */
public class RankFunctionTest {


    /**
     * RANK() OVER
     (PARTITION BY i.LocationID ORDER BY i.Quantity DESC)
     */
    public Rank exampleA = f_rank(
            $Over()
                .$PartitionBy(c("i","LocationID"))
                .$OrderByDesc(c("i","Quantity"))
                .build()
    );

    @Test
    public void testExampleA(){
        assertNotNull(exampleA.getPartitionBy());
        assertNotNull(exampleA.getOrderBy());
    }

    /**
     * RANK() OVER (ORDER BY Rate DESC)
     */
    public Rank exampleB = f_rank(
            $Over()
                    .$OrderByDesc(c("Rate"))
                    .build()
    );

    @Test
    public void testExampleB(){
        assertNotNull(exampleB.getOrderBy());
    }

    /**
     * RANK() OVER (PARTITION BY SalesTerritoryRegion ORDER BY SUM(SalesAmountQuota) DESC )
     */
    public Rank exampleC = f_rank(
            $Over()
                    .$PartitionBy(c("SalesTerritoryRegion"))
                    .$OrderByDesc(f_sum(c("SalesAmountQuota")))
                    .build()
    );

    @Test
    public void testExampleC(){
        assertNotNull(exampleC.getPartitionBy());
        assertNotNull(exampleC.getOrderBy());
    }

}