package com.xy.xsql.tsql.builder.chain.functions.analytic;

import com.xy.xsql.tsql.model.datatypes.table.ColumnName;
import com.xy.xsql.tsql.model.functions.analytic.First_Value;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.datatypes.table.ColumnNameFactory.c;
import static com.xy.xsql.tsql.builder.chain.functions.AnalyticFunctions.f_first_value;
import static com.xy.xsql.tsql.builder.chain.queries.Queries.$Over;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by xiaoyao9184 on 2018/6/5.
 */
public class FirstValueFunctionTest {


    /**
     * FIRST_VALUE(Name) OVER (ORDER BY ListPrice ASC)
     */
    public First_Value exampleA = f_first_value(c("Name"),
            $Over().$OrderByAsc(c("ListPrice")).build());

    @Test
    public void testExampleA(){
        assertEquals(exampleA.getScalarExpression().getClass(), ColumnName.class);
        assertNotNull(exampleA.getOver());
    }

    /**
     * FIRST_VALUE(LastName) OVER (PARTITION BY JobTitle
     ORDER BY VacationHours ASC
     ROWS UNBOUNDED PRECEDING
     )
     */
    public First_Value exampleB = f_first_value(c("LastName"),
            $Over().$PartitionBy(c("JobTitle")).$OrderByAsc(c("VacationHours")).$Rows().$UnboundedPreceding().build());

    @Test
    public void testExampleB(){
        assertEquals(exampleB.getScalarExpression().getClass(), ColumnName.class);
        assertNotNull(exampleB.getOver());
    }
}