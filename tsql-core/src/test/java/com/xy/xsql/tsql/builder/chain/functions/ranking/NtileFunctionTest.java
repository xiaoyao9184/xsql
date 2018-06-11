package com.xy.xsql.tsql.builder.chain.functions.ranking;

import com.xy.xsql.tsql.model.datatypes.constants.NumberConstant;
import com.xy.xsql.tsql.model.functions.ranking.Ntile;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.datatypes.Constants.c_number;
import static com.xy.xsql.tsql.builder.chain.datatypes.table.ColumnNameFactory.c;
import static com.xy.xsql.tsql.builder.chain.elements.expressions.Expressions.e_variable;
import static com.xy.xsql.tsql.builder.chain.functions.AggregateFunctions.f_sum;
import static com.xy.xsql.tsql.builder.chain.functions.RankingFunctions.f_ntile;
import static com.xy.xsql.tsql.builder.chain.queries.Queries.$Over;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by xiaoyao9184 on 2018/6/5.
 */
public class NtileFunctionTest {


    /**
     * NTILE(4) OVER(ORDER BY SalesYTD DESC)
     */
    public Ntile exampleA = f_ntile(
            c_number(4),
            $Over()
                .$PartitionBy(c("i","LocationID"))
                .$OrderByDesc(c("i","Quantity"))
                .build()
    );

    @Test
    public void testExampleA(){
        assertEquals(exampleA.getIntegerExpression().getClass(), NumberConstant.class);
        assertNotNull(exampleA.getPartitionBy());
        assertNotNull(exampleA.getOrderBy());
    }

    /**
     * NTILE(@NTILE_Var) OVER(PARTITION BY PostalCode ORDER BY SalesYTD DESC)
     */
    public Ntile exampleB = f_ntile(
            e_variable("NTILE_Var"),
            $Over()
                    .$PartitionBy(c("PostalCode"))
                    .$OrderByDesc(c("SalesYTD"))
                    .build()
    );

    @Test
    public void testExampleB(){
        assertNotNull(exampleB.getOrderBy());
    }

    /**
     * NTILE(4) OVER(ORDER BY SUM(SalesAmountQuota) DESC)
     */
    public Ntile exampleC = f_ntile(
            c_number(4),
            $Over()
                    .$OrderByDesc(f_sum(c("SalesAmountQuota")))
                    .build()
    );

    @Test
    public void testExampleC(){
        assertNotNull(exampleC.getOrderBy());
    }

    /**
     * NTILE(2) OVER(PARTITION BY e.SalesTerritoryKey ORDER BY SUM(SalesAmountQuota)
     */
    public Ntile exampleD = f_ntile(
            c_number(2),
            $Over()
                    .$PartitionBy(c("e","SalesTerritoryKey"))
                    .$OrderByDesc(f_sum(c("SalesAmountQuota")))
                    .build()
    );

    @Test
    public void testExampleD(){
        assertNotNull(exampleD.getPartitionBy());
        assertNotNull(exampleD.getOrderBy());
    }

}