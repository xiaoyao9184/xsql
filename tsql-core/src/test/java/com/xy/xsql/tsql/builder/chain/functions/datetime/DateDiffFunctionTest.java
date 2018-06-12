package com.xy.xsql.tsql.builder.chain.functions.datetime;

import com.xy.xsql.tsql.model.datatypes.constants.StringConstant;
import com.xy.xsql.tsql.model.datatypes.table.ColumnName;
import com.xy.xsql.tsql.model.elements.expressions.BinaryExpression;
import com.xy.xsql.tsql.model.elements.expressions.GroupExpression;
import com.xy.xsql.tsql.model.elements.variables.LocalVariable;
import com.xy.xsql.tsql.model.functions.aggregate.Min;
import com.xy.xsql.tsql.model.functions.datetime.*;
import com.xy.xsql.tsql.model.functions.ranking.Row_Number;
import com.xy.xsql.tsql.model.queries.Select;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.datatypes.Constants.c_number;
import static com.xy.xsql.tsql.builder.chain.datatypes.Constants.c_string;
import static com.xy.xsql.tsql.builder.chain.datatypes.table.ColumnNameFactory.c;
import static com.xy.xsql.tsql.builder.chain.datatypes.table.TableNameFactory.t;
import static com.xy.xsql.tsql.builder.chain.elements.expressions.Expressions.e;
import static com.xy.xsql.tsql.builder.chain.elements.expressions.Expressions.e_addition;
import static com.xy.xsql.tsql.builder.chain.elements.expressions.Expressions.e_variable;
import static com.xy.xsql.tsql.builder.chain.functions.AggregateFunctions.f_max;
import static com.xy.xsql.tsql.builder.chain.functions.AggregateFunctions.f_min;
import static com.xy.xsql.tsql.builder.chain.functions.DataTimeFunctions.*;
import static com.xy.xsql.tsql.builder.chain.functions.RankingFunctions.f_row_number;
import static com.xy.xsql.tsql.builder.chain.queries.Queries.$Over;
import static com.xy.xsql.tsql.builder.chain.queries.Queries.$Query;
import static org.junit.Assert.assertEquals;

/**
 * Created by xiaoyao9184 on 2018/6/5.
 */
public class DateDiffFunctionTest {


    /**
     * DATEDIFF(day,startDate,endDate)
     */
    public DateDiff exampleA = f_datediff(
            DatePart.DatePartArgument.day,
            c("startDate"),
            c("endDate")
    );

    @Test
    public void testExampleA(){
        assertEquals(exampleA.getDatepart(), DatePart.DatePartArgument.day);
        assertEquals(exampleA.getStartDate().getClass(), ColumnName.class);
        assertEquals(exampleA.getEndDate().getClass(), ColumnName.class);
    }

    /**
     * DATEDIFF(day, @startdate, @enddate)
     */
    public DateDiff exampleB = f_datediff(
            DatePart.DatePartArgument.day,
            e_variable("startDate"),
            e_variable("endDate")
    );

    @Test
    public void testExampleB(){
        assertEquals(exampleB.getDatepart(), DatePart.DatePartArgument.day);
        assertEquals(exampleB.getStartDate().getClass(), LocalVariable.class);
        assertEquals(exampleB.getEndDate().getClass(), LocalVariable.class);
    }

    /**
     * DATEDIFF(millisecond, GETDATE(), SYSDATETIME())
     */
    public DateDiff exampleC = f_datediff(
            DatePart.DatePartArgument.millisecond,
            f_getdate(),
            f_sysdatetime()
    );

    @Test
    public void testExampleC(){
        assertEquals(exampleC.getDatepart(), DatePart.DatePartArgument.millisecond);
        assertEquals(exampleC.getStartDate().getClass(), GetDate.class);
        assertEquals(exampleC.getEndDate().getClass(), SysDatetime.class);
    }


    /**
     * DATEDIFF(day,(SELECT MIN(OrderDate) FROM Sales.SalesOrderHeader),
     (SELECT MAX(OrderDate) FROM Sales.SalesOrderHeader))
     */
    public DateDiff exampleD = f_datediff(
            DatePart.DatePartArgument.day,
            e($Query()
                .$(f_min(c("OrderDate")))
                .$From()
                    .$(t("Sales","SalesOrderHeader"))
                    .and()
                .build()),
            e($Query()
                    .$(f_max(c("OrderDate")))
                    .$From()
                        .$(t("Sales","SalesOrderHeader"))
                        .and()
                    .build())
    );

    @Test
    public void testExampleD(){
        assertEquals(exampleD.getDatepart(), DatePart.DatePartArgument.day);
        assertEquals(exampleD.getStartDate().getClass(), GroupExpression.class);
        assertEquals(exampleD.getEndDate().getClass(), GroupExpression.class);
    }

    /**
     * DATEDIFF(day, '2007-05-07 09:53:01.0376635'
     , '2007-05-08 09:53:01.0376635')
     */
    public DateDiff exampleE = f_datediff(
            DatePart.DatePartArgument.day,
            c_string("2007-05-07 09:53:01.0376635"),
            c_string("2007-05-08 09:53:01.0376635")
    );

    @Test
    public void testExampleE(){
        assertEquals(exampleE.getDatepart(), DatePart.DatePartArgument.day);
        assertEquals(exampleE.getStartDate().getClass(), StringConstant.class);
        assertEquals(exampleE.getEndDate().getClass(), StringConstant.class);
    }

    /**
     * DATEDIFF(day, '2007-05-07 09:53:01.0376635', GETDATE()+ 1)
     */
    public DateDiff exampleF1 = f_datediff(
            DatePart.DatePartArgument.day,
            c_string("2007-05-07 09:53:01.0376635"),
            e_addition(f_getdate(),c_number(1))
    );

    /**
     * DATEDIFF(day, '2007-05-07 09:53:01.0376635', DATEADD(day,1,SYSDATETIME()))
     */
    public DateDiff exampleF2 = f_datediff(
            DatePart.DatePartArgument.day,
            c_string("2007-05-07 09:53:01.0376635"),
            f_dateadd(DatePart.DatePartArgument.day, c_number(1),f_sysdatetime())
    );

    @Test
    public void testExampleF(){
        assertEquals(exampleF1.getDatepart(), DatePart.DatePartArgument.day);
        assertEquals(exampleF1.getStartDate().getClass(), StringConstant.class);
        assertEquals(exampleF1.getEndDate().getClass(), BinaryExpression.class);
        assertEquals(exampleF2.getDatepart(), DatePart.DatePartArgument.day);
        assertEquals(exampleF2.getStartDate().getClass(), StringConstant.class);
        assertEquals(exampleF2.getEndDate().getClass(), DateAdd.class);
    }

    /**
     * DATEDIFF(day,ROW_NUMBER() OVER (ORDER BY
     a.PostalCode),SYSDATETIME())
     */
    public DateDiff exampleG = f_datediff(
            DatePart.DatePartArgument.day,
            f_row_number($Over().$OrderBy(c("a","PostalCode")).build()),
            f_sysdatetime()
    );

    @Test
    public void testExampleG(){
        assertEquals(exampleG.getDatepart(), DatePart.DatePartArgument.day);
        assertEquals(exampleG.getStartDate().getClass(), Row_Number.class);
        assertEquals(exampleG.getEndDate().getClass(), SysDatetime.class);
    }

    /**
     * DATEDIFF(day,MIN(soh.OrderDate)
     OVER(PARTITION BY soh.SalesOrderID),SYSDATETIME() )
     */
    public DateDiff exampleH = f_datediff(
            DatePart.DatePartArgument.day,
            f_min(c("soh","OrderDate"),
                    $Over().$PartitionBy(c("soh","SalesOrderID")).build()),
            f_sysdatetime()
    );

    @Test
    public void testExampleH(){
        assertEquals(exampleH.getDatepart(), DatePart.DatePartArgument.day);
        assertEquals(exampleH.getStartDate().getClass(), Min.class);
        assertEquals(exampleH.getEndDate().getClass(), SysDatetime.class);
    }

    //not need
    //Examples: Azure SQL Data Warehouse and Parallel Data Warehouse

}