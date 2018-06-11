package com.xy.xsql.tsql.builder.chain.functions.aggregate;

import com.xy.xsql.tsql.model.datatypes.table.ColumnName;
import com.xy.xsql.tsql.model.functions.aggregate.Min;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.datatypes.table.ColumnNameFactory.c;
import static com.xy.xsql.tsql.builder.chain.functions.AggregateFunctions.f_min;
import static com.xy.xsql.tsql.builder.chain.queries.Queries.$Over;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by xiaoyao9184 on 2018/6/5.
 */
public class MinFunctionTest {


    /**
     * MIN(TaxRate)
     */
    public Min exampleA = f_min(c("TaxRate"));

    @Test
    public void testExampleA(){
        assertEquals(exampleA.getExpression().getClass(), ColumnName.class);
    }


    /**
     * MIN(Rate) OVER (PARTITION BY edh.DepartmentID)
     */
    public Min exampleB = f_min(c("Rate"),
            $Over().$PartitionBy(c("edh","DepartmentID")).build());

    @Test
    public void testExampleB(){
        assertEquals(exampleB.getExpression().getClass(), ColumnName.class);
        assertNotNull(exampleB.getPartitionBy());
    }

    /**
     * MIN(UnitPrice)
     */
    public Min exampleC = f_min(c("UnitPrice"));

    @Test
    public void testExampleC(){
        assertEquals(exampleC.getExpression().getClass(),ColumnName.class);
    }


    /**
     * MIN(UnitPrice) OVER(PARTITION BY SalesOrderNumber)
     */
    public Min exampleD = f_min(c("UnitPrice"),
            $Over().$PartitionBy(c("SalesOrderNumber")).build());

    @Test
    public void testExampleD(){
        assertEquals(exampleD.getExpression().getClass(), ColumnName.class);
        assertNotNull(exampleD.getPartitionBy());
    }

}