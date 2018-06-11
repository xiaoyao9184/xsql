package com.xy.xsql.tsql.builder.chain.functions.aggregate;

import com.xy.xsql.tsql.model.datatypes.table.ColumnName;
import com.xy.xsql.tsql.model.functions.aggregate.StDevp;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.datatypes.table.ColumnNameFactory.c;
import static com.xy.xsql.tsql.builder.chain.functions.AggregateFunctions.f_stdevp;
import static com.xy.xsql.tsql.builder.chain.functions.AggregateFunctions.f_stdevp_distinct;
import static com.xy.xsql.tsql.builder.chain.queries.Queries.$Over;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by xiaoyao9184 on 2018/6/5.
 */
public class StDevpFunctionTest {


    /**
     * STDEVP(Bonus)
     */
    public StDevp exampleA = f_stdevp(c("Bonus"));

    @Test
    public void testExampleA(){
        assertEquals(exampleA.getExpression().getClass(), ColumnName.class);
    }


    /**
     * STDEVP(DISTINCT SalesAmountQuota)
     */
    public StDevp exampleB = f_stdevp_distinct(c("SalesAmountQuota"));

    @Test
    public void testExampleB(){
        assertEquals(exampleB.getExpression().getClass(), ColumnName.class);
    }

    /**
     * STDEVP(SalesAmountQuota) OVER (ORDER BY CalendarYear, CalendarQuarter)
     */
    public StDevp exampleC = f_stdevp(c("SalesAmountQuota"),
            $Over().$OrderBy(c("CalendarYear"),c("CalendarQuarter")).build());

    @Test
    public void testExampleC(){
        assertEquals(exampleC.getExpression().getClass(),ColumnName.class);
        assertNotNull(exampleC.getOrderBy());
    }

}