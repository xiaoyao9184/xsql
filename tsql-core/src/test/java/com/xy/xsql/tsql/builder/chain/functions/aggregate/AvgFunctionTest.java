package com.xy.xsql.tsql.builder.chain.functions.aggregate;

import com.xy.xsql.tsql.model.datatypes.table.ColumnName;
import com.xy.xsql.tsql.model.functions.aggregate.Avg;
import com.xy.xsql.tsql.model.functions.datetime.DatePart;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.datatypes.table.ColumnNameFactory.c;
import static com.xy.xsql.tsql.builder.chain.functions.AggregateFunctions.f_avg;
import static com.xy.xsql.tsql.builder.chain.functions.AggregateFunctions.f_avg_distinct;
import static com.xy.xsql.tsql.builder.chain.functions.DataTimeFunctions.f_datepart;
import static com.xy.xsql.tsql.builder.chain.queries.Queries.$Over;
import static org.junit.Assert.*;

/**
 * Created by xiaoyao9184 on 2018/6/5.
 */
public class AvgFunctionTest {


    /**
     * AVG(VacationHours)
     */
    public Avg exampleA = f_avg(c("VacationHours"));

    @Test
    public void testExampleA(){
        assertEquals(exampleA.getExpression().getClass(), ColumnName.class);
    }


    /**
     * AVG(Bonus)
     */
    public Avg exampleB = f_avg(c("Bonus"));

    @Test
    public void testExampleB(){
        assertEquals(exampleB.getExpression().getClass(), ColumnName.class);
    }


    /**
     * AVG(DISTINCT ListPrice)
     */
    public Avg exampleC = f_avg_distinct(c("ListPrice"));

    @Test
    public void testExampleC(){
        assertEquals(exampleC.getExpression().getClass(), ColumnName.class);
        assertTrue(exampleC.isUseDistinct());
    }


    /**
     * AVG(ListPrice)
     */
    public Avg exampleD = f_avg(c("ListPrice"));

    @Test
    public void testExampleD(){
        assertEquals(exampleD.getExpression().getClass(), ColumnName.class);
        assertFalse(exampleD.isUseDistinct());
    }


    /**
     * AVG(SalesYTD) OVER (PARTITION BY TerritoryID
     ORDER BY DATEPART(yy,ModifiedDate)
     )
     */
    public Avg exampleE1 = f_avg(c("SalesYTD"),
            $Over()
                    .$PartitionBy(c("TerritoryID"))
                    .$OrderBy(
                            f_datepart(DatePart.DatePartArgument.yy,c("ModifiedDate"))
                    )
                    .build());

    /**
     * AVG(SalesYTD) OVER (ORDER BY DATEPART(yy,ModifiedDate))
     */
    public Avg exampleE2 = f_avg(c("SalesYTD"),
            $Over()
                    .$OrderBy(
                            f_datepart(DatePart.DatePartArgument.yy,c("ModifiedDate"))
                    )
                    .build());

    @Test
    public void testexampleE(){
        assertEquals(exampleE1.getExpression().getClass(), ColumnName.class);
        assertNotNull(exampleE1.getOrderBy());
        assertEquals(exampleE2.getExpression().getClass(), ColumnName.class);
        assertNotNull(exampleE2.getOrderBy());
    }

}