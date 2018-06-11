package com.xy.xsql.tsql.builder.chain.functions.ranking;

import com.xy.xsql.tsql.model.functions.ranking.Dense_Rank;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.datatypes.table.ColumnNameFactory.c;
import static com.xy.xsql.tsql.builder.chain.functions.AggregateFunctions.f_sum;
import static com.xy.xsql.tsql.builder.chain.functions.RankingFunctions.f_dense_rank;
import static com.xy.xsql.tsql.builder.chain.queries.Queries.$Over;
import static org.junit.Assert.assertNotNull;

/**
 * Created by xiaoyao9184 on 2018/6/5.
 */
public class DenseRankFunctionTest {


    /**
     * DENSE_RANK() OVER
     (PARTITION BY i.LocationID ORDER BY i.Quantity DESC)
     */
    public Dense_Rank exampleA = f_dense_rank(
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
     * DENSE_RANK() OVER (ORDER BY Rate DESC)
     */
    public Dense_Rank exampleB = f_dense_rank(
            $Over()
                    .$OrderByDesc(c("Rate"))
                    .build()
    );

    @Test
    public void testExampleB(){
        assertNotNull(exampleB.getOrderBy());
    }

    /**
     * DENSE_RANK() OVER (ORDER BY a.PostalCode)
     */
    public Dense_Rank exampleC = f_dense_rank(
            $Over()
                    .$OrderBy(c("a","PostalCode"))
                    .build()
    );

    @Test
    public void testExampleC(){
        assertNotNull(exampleC.getOrderBy());
    }

    /**
     * DENSE_RANK() OVER (PARTITION BY SalesTerritoryGroup ORDER BY SUM(SalesAmountQuota) DESC )
     */
    public Dense_Rank exampleD = f_dense_rank(
            $Over()
                    .$PartitionBy(c("SalesTerritoryGroup"))
                    .$OrderByDesc(f_sum(c("SalesAmountQuota")))
                    .build()
    );

    @Test
    public void testExampleD(){
        assertNotNull(exampleD.getPartitionBy());
        assertNotNull(exampleD.getOrderBy());
    }

}