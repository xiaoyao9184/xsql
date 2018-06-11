package com.xy.xsql.tsql.builder.chain.functions.ranking;

import com.xy.xsql.tsql.model.functions.ranking.Row_Number;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.datatypes.table.ColumnNameFactory.c;
import static com.xy.xsql.tsql.builder.chain.functions.AggregateFunctions.f_sum;
import static com.xy.xsql.tsql.builder.chain.functions.RankingFunctions.f_row_number;
import static com.xy.xsql.tsql.builder.chain.queries.Queries.$Over;
import static org.junit.Assert.assertNotNull;

/**
 * Created by xiaoyao9184 on 2018/6/5.
 */
public class RowNumberFunctionTest {


    /**
     * ROW_NUMBER() OVER(ORDER BY name ASC)
     */
    public Row_Number exampleA1 = f_row_number(
            $Over()
                .$OrderBy(c("name"))
                .build()
    );

    /**
     * ROW_NUMBER() OVER(PARTITION BY recovery_model_desc ORDER BY name ASC)
     */
    public Row_Number exampleA2 = f_row_number(
            $Over()
                    .$PartitionBy(c("recovery_model_desc"))
                    .$OrderBy(c("name"))
                    .build()
    );

    @Test
    public void testExampleA(){
        assertNotNull(exampleA1.getOrderBy());
        assertNotNull(exampleA2.getPartitionBy());
        assertNotNull(exampleA2.getOrderBy());
    }

    /**
     * ROW_NUMBER() OVER(ORDER BY SalesYTD DESC)
     */
    public Row_Number exampleB = f_row_number(
            $Over()
                    .$OrderByDesc(c("SalesYTD"))
                    .build()
    );

    @Test
    public void testExampleB(){
        assertNotNull(exampleB.getOrderBy());
    }

    /**
     * ROW_NUMBER() OVER (ORDER BY OrderDate)
     */
    public Row_Number exampleC = f_row_number(
            $Over()
                    .$OrderBy(c("OrderDate"))
                    .build()
    );

    @Test
    public void testExampleC(){
        assertNotNull(exampleC.getOrderBy());
    }

    /**
     * ROW_NUMBER() OVER(PARTITION BY TerritoryName ORDER BY SalesYTD DESC)
     */
    public Row_Number exampleD = f_row_number(
            $Over()
                    .$PartitionBy(c("TerritoryName"))
                    .$OrderByDesc(c("SalesYTD"))
                    .build()
    );

    @Test
    public void testExampleD(){
        assertNotNull(exampleD.getPartitionBy());
        assertNotNull(exampleD.getOrderBy());
    }

    /**
     * ROW_NUMBER() OVER(ORDER BY SUM(SalesAmountQuota) DESC)
     */
    public Row_Number exampleE = f_row_number(
            $Over()
                    .$OrderByDesc(f_sum(c("SalesAmountQuota")))
                    .build()
    );

    @Test
    public void testExampleE(){
        assertNotNull(exampleE.getOrderBy());
    }

    /**
     * ROW_NUMBER() OVER(PARTITION BY SalesTerritoryKey
     ORDER BY SUM(SalesAmountQuota) DESC)
     */
    public Row_Number exampleF = f_row_number(
            $Over()
                    .$PartitionBy(c("SalesTerritoryKey"))
                    .$OrderByDesc(f_sum(c("SalesAmountQuota")))
                    .build()
    );

    @Test
    public void testExampleF(){
        assertNotNull(exampleF.getPartitionBy());
        assertNotNull(exampleF.getOrderBy());
    }

}