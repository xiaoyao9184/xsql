package com.xy.xsql.tsql.builder.chain.functions.aggregate;

import com.xy.xsql.tsql.model.datatypes.table.ColumnName;
import com.xy.xsql.tsql.model.functions.aggregate.Sum;
import com.xy.xsql.tsql.model.functions.datetime.DatePart;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.datatypes.table.ColumnNameFactory.c;
import static com.xy.xsql.tsql.builder.chain.functions.AggregateFunctions.f_sum;
import static com.xy.xsql.tsql.builder.chain.functions.DataTimeFunctions.f_datepart;
import static com.xy.xsql.tsql.builder.chain.queries.Queries.$Over;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by xiaoyao9184 on 2018/6/5.
 */
public class SumFunctionTest {


    /**
     * SUM(ListPrice)
     */
    public Sum exampleA = f_sum(c("ListPrice"));

    @Test
    public void testExampleA(){
        assertEquals(exampleA.getExpression().getClass(), ColumnName.class);
    }


    /**
     * SUM(SalesYTD) OVER (PARTITION BY TerritoryID
     ORDER BY DATEPART(yy,ModifiedDate)
     )
     */
    public Sum exampleB = f_sum(c("SalesYTD"),
            $Over().$PartitionBy(c("TerritoryID")).$OrderBy(f_datepart(DatePart.DatePartArgument.yy,c("ModifiedDate"))).build());

    @Test
    public void testExampleB(){
        assertEquals(exampleB.getExpression().getClass(), ColumnName.class);
        assertNotNull(exampleB.getPartitionBy());
        assertNotNull(exampleB.getOrderBy());
    }

    //ExampleC is almost like ExampleA

}