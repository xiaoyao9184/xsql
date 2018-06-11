package com.xy.xsql.tsql.builder.chain.functions.aggregate;

import com.xy.xsql.tsql.model.datatypes.table.ColumnName;
import com.xy.xsql.tsql.model.functions.aggregate.StDev;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.datatypes.table.ColumnNameFactory.c;
import static com.xy.xsql.tsql.builder.chain.functions.AggregateFunctions.f_stdev;
import static com.xy.xsql.tsql.builder.chain.functions.AggregateFunctions.f_stdev_distinct;
import static com.xy.xsql.tsql.builder.chain.queries.Queries.$Over;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by xiaoyao9184 on 2018/6/5.
 */
public class StDevFunctionTest {


    /**
     * STDEV(Bonus)
     */
    public StDev exampleA = f_stdev(c("Bonus"));

    @Test
    public void testExampleA(){
        assertEquals(exampleA.getExpression().getClass(), ColumnName.class);
    }


    /**
     * STDEV(DISTINCT SalesAmountQuota)
     */
    public StDev exampleB = f_stdev_distinct(c("SalesAmountQuota"));

    @Test
    public void testExampleB(){
        assertEquals(exampleB.getExpression().getClass(), ColumnName.class);
    }

    /**
     * STDEV(SalesAmountQuota) OVER (ORDER BY CalendarYear, CalendarQuarter)
     */
    public StDev exampleC = f_stdev(c("SalesAmountQuota"),
            $Over().$OrderBy(c("CalendarYear"),c("CalendarQuarter")).build());

    @Test
    public void testExampleC(){
        assertEquals(exampleC.getExpression().getClass(),ColumnName.class);
        assertNotNull(exampleC.getOrderBy());
    }

}