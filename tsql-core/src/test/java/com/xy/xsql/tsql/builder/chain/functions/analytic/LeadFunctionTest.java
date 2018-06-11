package com.xy.xsql.tsql.builder.chain.functions.analytic;

import com.xy.xsql.tsql.model.datatypes.constants.NumberConstant;
import com.xy.xsql.tsql.model.datatypes.table.ColumnName;
import com.xy.xsql.tsql.model.elements.expressions.BinaryExpression;
import com.xy.xsql.tsql.model.functions.analytic.Lead;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.datatypes.table.ColumnNameFactory.c;
import static com.xy.xsql.tsql.builder.chain.datatypes.table.TableNameFactory.t;
import static com.xy.xsql.tsql.builder.chain.elements.expressions.Expressions.*;
import static com.xy.xsql.tsql.builder.chain.functions.AggregateFunctions.f_min;
import static com.xy.xsql.tsql.builder.chain.functions.AnalyticFunctions.f_lead;
import static com.xy.xsql.tsql.builder.chain.functions.DataTimeFunctions.f_year;
import static com.xy.xsql.tsql.builder.chain.queries.Queries.$Over;
import static com.xy.xsql.tsql.builder.chain.queries.Queries.$Query;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by xiaoyao9184 on 2018/6/5.
 */
public class LeadFunctionTest {


    /**
     * LEAD(SalesQuota, 1,0) OVER (ORDER BY YEAR(QuotaDate))
     */
    public Lead exampleA = f_lead(c("SalesQuota"),e_number(1),e_number(0),
            $Over().$OrderBy(f_year(c("QuotaDate"))).build());

    @Test
    public void testExampleA(){
        assertEquals(exampleA.getScalarExpression().getClass(), ColumnName.class);
        assertEquals(exampleA.getOffset().getClass(), NumberConstant.class);
        assertEquals(exampleA.getDefaultValue().getClass(), NumberConstant.class);
        assertNotNull(exampleA.getOver());
    }

    /**
     * LEAD (SalesYTD, 1, 0) OVER (PARTITION BY TerritoryName ORDER BY SalesYTD DESC)
     */
    public Lead exampleB = f_lead(c("SalesYTD"),e_number(1),e_number(0),
            $Over().$PartitionBy(c("TerritoryName")).$OrderByDesc(c("SalesYTD")).build());

    @Test
    public void testExampleB(){
        assertEquals(exampleB.getScalarExpression().getClass(), ColumnName.class);
        assertEquals(exampleB.getOffset().getClass(), NumberConstant.class);
        assertEquals(exampleB.getDefaultValue().getClass(), NumberConstant.class);
        assertNotNull(exampleB.getOver());
    }

    /**
     * LEAD(2*c, b*(SELECT MIN(b) FROM T), -c/2.0) OVER (ORDER BY a)
     */
    public Lead exampleC = f_lead(
            e_multiplication(e_number(2),c("c")),
            e_multiplication(c("c"),$Query().$(f_min(c("b"))).$From().$(t("T")).and().build()),
            e_division(e_negative(c("c")), e_number(2.0)),
            $Over().$OrderBy(c("a")).build());

    @Test
    public void testExampleC(){
        assertEquals(exampleC.getScalarExpression().getClass(), BinaryExpression.class);
        assertEquals(exampleC.getOffset().getClass(), BinaryExpression.class);
        assertEquals(exampleC.getDefaultValue().getClass(), BinaryExpression.class);
        assertNotNull(exampleC.getOver());
    }

    /**
     * LEAD(SalesAmountQuota,1,0) OVER (ORDER BY CalendarYear, CalendarQuarter)
     */
    public Lead exampleD = f_lead(c("SalesAmountQuota"),e_number(1),e_number(0),
            $Over().$OrderByDesc(c("CalendarYear"),c("CalendarQuarter")).build());

    @Test
    public void testExampleD(){
        assertEquals(exampleD.getScalarExpression().getClass(), ColumnName.class);
        assertEquals(exampleD.getOffset().getClass(), NumberConstant.class);
        assertEquals(exampleD.getDefaultValue().getClass(), NumberConstant.class);
        assertNotNull(exampleD.getOver());
    }
}