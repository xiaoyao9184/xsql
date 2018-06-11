package com.xy.xsql.tsql.builder.chain.functions.analytic;

import com.xy.xsql.tsql.model.datatypes.constants.NumberConstant;
import com.xy.xsql.tsql.model.functions.analytic.Percentile_Disc;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.datatypes.table.ColumnNameFactory.c;
import static com.xy.xsql.tsql.builder.chain.elements.expressions.Expressions.e_number;
import static com.xy.xsql.tsql.builder.chain.functions.AnalyticFunctions.$Within_Group_Order_By;
import static com.xy.xsql.tsql.builder.chain.functions.AnalyticFunctions.f_percentile_disc;
import static com.xy.xsql.tsql.builder.chain.queries.Queries.$Over;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by xiaoyao9184 on 2018/6/5.
 */
public class PercentileDiscFunctionTest {


    /**
     * PERCENTILE_DISC(0.5) WITHIN GROUP (ORDER BY ph.Rate)
     OVER (PARTITION BY Name)
     */
    public Percentile_Disc exampleA = f_percentile_disc(
            e_number(0.5),
            $Within_Group_Order_By(c("ph","Rate")),
            $Over().$PartitionBy(c("Name")).build());

    @Test
    public void testExampleA(){
        assertEquals(exampleA.getNumericLiteral().getClass(), NumberConstant.class);
        assertNotNull(exampleA.getOrderBy());
        assertNotNull(exampleA.getOver());
    }

    /**
     * PERCENTILE_DISC(0.5) WITHIN GROUP (ORDER BY BaseRate)
     OVER (PARTITION BY DepartmentName)
     */
    public Percentile_Disc exampleB = f_percentile_disc(
            e_number(0.5),
            $Within_Group_Order_By(c("BaseRate")),
            $Over().$PartitionBy(c("DepartmentName")).build());

    @Test
    public void testExampleB(){
        assertEquals(exampleB.getNumericLiteral().getClass(), NumberConstant.class);
        assertNotNull(exampleA.getOrderBy());
        assertNotNull(exampleB.getOver());
    }
}