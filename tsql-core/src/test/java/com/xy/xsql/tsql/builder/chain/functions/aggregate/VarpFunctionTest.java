package com.xy.xsql.tsql.builder.chain.functions.aggregate;

import com.xy.xsql.tsql.model.datatypes.table.ColumnName;
import com.xy.xsql.tsql.model.functions.aggregate.Varp;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.datatypes.table.ColumnNameFactory.c;
import static com.xy.xsql.tsql.builder.chain.functions.AggregateFunctions.f_varp;
import static com.xy.xsql.tsql.builder.chain.functions.AggregateFunctions.f_varp_distinct;
import static com.xy.xsql.tsql.builder.chain.queries.Queries.$Over;
import static org.junit.Assert.*;

/**
 * Created by xiaoyao9184 on 2018/6/5.
 */
public class VarpFunctionTest {


    /**
     * VARP(Bonus)
     */
    public Varp exampleA = f_varp(c("Bonus"));

    @Test
    public void testExampleA(){
        assertEquals(exampleA.getExpression().getClass(), ColumnName.class);
    }


    /**
     * VARP(DISTINCT SalesAmountQuota)
     )
     */
    public Varp exampleB = f_varp_distinct(c("SalesAmountQuota"));

    @Test
    public void testExampleB(){
        assertEquals(exampleB.getExpression().getClass(), ColumnName.class);
        assertTrue(exampleB.isUseDistinct());
    }

    /**
     * VAR(SalesAmountQuota) OVER (ORDER BY CalendarYear, CalendarQuarter)
     */
    public Varp exampleC = f_varp(c("SalesAmountQuota"),
            $Over().$OrderBy(c("CalendarYear"),c("CalendarQuarter")).build());

    @Test
    public void testExampleC(){
        assertEquals(exampleC.getExpression().getClass(), ColumnName.class);
        assertNotNull(exampleC.getOrderBy());
    }

}