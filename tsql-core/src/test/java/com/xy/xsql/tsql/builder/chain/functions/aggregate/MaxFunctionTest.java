package com.xy.xsql.tsql.builder.chain.functions.aggregate;

import com.xy.xsql.tsql.model.datatypes.table.ColumnName;
import com.xy.xsql.tsql.model.functions.aggregate.Max;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.datatypes.table.ColumnNameFactory.c;
import static com.xy.xsql.tsql.builder.chain.functions.AggregateFunctions.f_max;
import static com.xy.xsql.tsql.builder.chain.queries.Queries.$Over;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by xiaoyao9184 on 2018/6/5.
 */
public class MaxFunctionTest {


    /**
     * MAX(TaxRate)
     */
    public Max exampleA = f_max(c("TaxRate"));

    @Test
    public void testExampleA(){
        assertEquals(exampleA.getExpression().getClass(), ColumnName.class);
    }


    /**
     * MAX(Rate) OVER (PARTITION BY edh.DepartmentID)
     */
    public Max exampleB = f_max(c("Rate"),
            $Over().$PartitionBy(c("edh","DepartmentID")).build());

    @Test
    public void testExampleB(){
        assertEquals(exampleB.getExpression().getClass(), ColumnName.class);
        assertNotNull(exampleB.getPartitionBy());
    }

    /**
     * MAX(name)
     */
    public Max exampleC = f_max(c("name"));

    @Test
    public void testExampleC(){
        assertEquals(exampleC.getExpression().getClass(),ColumnName.class);
    }

}