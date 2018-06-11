package com.xy.xsql.tsql.builder.chain.functions.analytic;

import com.xy.xsql.tsql.model.datatypes.table.ColumnName;
import com.xy.xsql.tsql.model.functions.analytic.Last_Value;
import com.xy.xsql.tsql.model.functions.datetime.DatePart;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.datatypes.table.ColumnNameFactory.c;
import static com.xy.xsql.tsql.builder.chain.functions.AnalyticFunctions.f_last_value;
import static com.xy.xsql.tsql.builder.chain.functions.DataTimeFunctions.f_datepart;
import static com.xy.xsql.tsql.builder.chain.functions.DataTimeFunctions.f_year;
import static com.xy.xsql.tsql.builder.chain.queries.Queries.$Over;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by xiaoyao9184 on 2018/6/5.
 */
public class LastValueFunctionTest {


    /**
     * LAST_VALUE(HireDate) OVER (PARTITION BY Department ORDER BY Rate)
     */
    public Last_Value exampleA = f_last_value(c("HireDate"),
            $Over().$PartitionBy(c("Department")).$OrderBy(c("Rate")).build());

    @Test
    public void testExampleA(){
        assertEquals(exampleA.getScalarExpression().getClass(), ColumnName.class);
        assertNotNull(exampleA.getOver());
    }

    /**
     * LAST_VALUE(SalesQuota)
     OVER (PARTITION BY BusinessEntityID, YEAR(QuotaDate)
     ORDER BY DATEPART(QUARTER,QuotaDate)
     RANGE BETWEEN CURRENT ROW AND UNBOUNDED FOLLOWING )
     */
    public Last_Value exampleB = f_last_value(c("SalesQuota"),
            $Over().$PartitionBy(c("BusinessEntityID"),f_year(c("QuotaDate")))
                    .$OrderBy(f_datepart(DatePart.DatePartArgument.quarter,c("QuotaDate")))
                    .$Range().$Between().$CurrentRow().$And().$UnboundedFollowing().and().build());

    @Test
    public void testExampleB(){
        assertEquals(exampleB.getScalarExpression().getClass(), ColumnName.class);
        assertNotNull(exampleB.getOver());
    }
}