package com.xy.xsql.tsql.builder.chain.functions.aggregate;

import com.xy.xsql.tsql.model.datatypes.table.ColumnName;
import com.xy.xsql.tsql.model.functions.aggregate.Count;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.datatypes.table.ColumnNameFactory.c;
import static com.xy.xsql.tsql.builder.chain.functions.AggregateFunctions.f_count;
import static com.xy.xsql.tsql.builder.chain.functions.AggregateFunctions.f_count_distinct;
import static com.xy.xsql.tsql.builder.chain.queries.Queries.$Over;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by xiaoyao9184 on 2018/6/5.
 */
public class CountFunctionTest {


    /**
     * COUNT(DISTINCT Title)
     */
    public Count exampleA = f_count_distinct(c("Title"));

    @Test
    public void testExampleA(){
        assertEquals(exampleA.getExpression().getClass(), ColumnName.class);
    }


    /**
     * COUNT(*)
     */
    public Count exampleB = f_count();

    @Test
    public void testExampleB(){
        assertTrue(exampleB.isUseAllCount());
    }


    //ExampleC is same as ExampleB

    /**
     * COUNT(edh.BusinessEntityID) OVER (PARTITION BY edh.DepartmentID)
     */
    public Count exampleD = f_count(c("edh","BusinessEntityID"),
            $Over().$PartitionBy(c("edh","DepartmentID")).build());

    @Test
    public void testExampleD(){
        assertEquals(exampleD.getExpression().getClass(),ColumnName.class);
        assertNotNull(exampleD.getOver());
    }

    //ExampleE is same as ExampleA
    //ExampleF is same as ExampleB

    /**
     * COUNT(EmployeeKey)
     */
    public Count exampleG = f_count(c("EmployeeKey"));

    @Test
    public void testExampleG(){
        assertEquals(exampleG.getExpression().getClass(),ColumnName.class);
    }

    //ExampleH is same as ExampleG

    /**
     * COUNT(ProductKey) OVER(PARTITION BY SalesOrderNumber)
     */
    public Count exampleI = f_count(c("ProductKey"),
            $Over().$PartitionBy(c("SalesOrderNumber")).build());

    @Test
    public void testExampleI(){
        assertEquals(exampleI.getExpression().getClass(),ColumnName.class);
        assertNotNull(exampleI.getOver());
    }
}