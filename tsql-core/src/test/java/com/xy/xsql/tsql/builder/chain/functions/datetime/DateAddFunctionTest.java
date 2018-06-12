package com.xy.xsql.tsql.builder.chain.functions.datetime;

import com.xy.xsql.tsql.model.datatypes.constants.NumberConstant;
import com.xy.xsql.tsql.model.elements.expressions.GroupExpression;
import com.xy.xsql.tsql.model.elements.expressions.UnaryExpression;
import com.xy.xsql.tsql.model.elements.variables.LocalVariable;
import com.xy.xsql.tsql.model.functions.aggregate.Sum;
import com.xy.xsql.tsql.model.functions.datetime.DateAdd;
import com.xy.xsql.tsql.model.functions.datetime.DatePart;
import com.xy.xsql.tsql.model.functions.datetime.SysDatetime;
import com.xy.xsql.tsql.model.functions.ranking.Row_Number;
import com.xy.xsql.tsql.model.queries.Select;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.datatypes.Constants.c_number;
import static com.xy.xsql.tsql.builder.chain.datatypes.table.ColumnNameFactory.c;
import static com.xy.xsql.tsql.builder.chain.datatypes.table.TableNameFactory.t;
import static com.xy.xsql.tsql.builder.chain.elements.expressions.Expressions.*;
import static com.xy.xsql.tsql.builder.chain.functions.AggregateFunctions.f_max;
import static com.xy.xsql.tsql.builder.chain.functions.AggregateFunctions.f_sum;
import static com.xy.xsql.tsql.builder.chain.functions.DataTimeFunctions.f_dateadd;
import static com.xy.xsql.tsql.builder.chain.functions.DataTimeFunctions.f_sysdatetime;
import static com.xy.xsql.tsql.builder.chain.functions.RankingFunctions.f_row_number;
import static com.xy.xsql.tsql.builder.chain.queries.Queries.$Over;
import static com.xy.xsql.tsql.builder.chain.queries.Queries.$Query;
import static org.junit.Assert.assertEquals;

/**
 * Created by xiaoyao9184 on 2018/6/5.
 */
public class DateAddFunctionTest {


    /**
     * DATEADD(year,1,@datetime2)
     */
    public DateAdd exampleA1 = f_dateadd(
            DatePart.DatePartArgument.year,
            c_number(1),
            e_variable("datetime2")
    );

    @Test
    public void testExampleA(){
        assertEquals(exampleA1.getDatepart(), DatePart.DatePartArgument.year);
        assertEquals(exampleA1.getNumber().getClass(), NumberConstant.class);
        assertEquals(exampleA1.getDate().getClass(), LocalVariable.class);
    }

    /**
     * DATEADD(day, @days, @datetime)
     */
    public DateAdd exampleC = f_dateadd(
            DatePart.DatePartArgument.day,
            e_variable("days"),
            e_variable("datetime")
    );

    /**
     * DATEADD(month, 1, SYSDATETIME())
     */
    public DateAdd exampleC2 = f_dateadd(
            DatePart.DatePartArgument.month,
            c_number(1),
            f_sysdatetime()
    );

    /**
     * DATEADD(month,(SELECT TOP 1 BusinessEntityID FROM Person.Person),
     (SELECT MAX(ModifiedDate) FROM Person.Person))
     */
    public DateAdd exampleC3 = f_dateadd(
            DatePart.DatePartArgument.month,
            e($Query()
                .$Top().$(e_number(1))
                    .and()
                .$(c("BusinessEntityID"))
                .$From()
                    .$(t("Person","Person"))
                    .and()
                .build()),
            e($Query()
                    .$(f_max(c("ModifiedDate")))
                    .$From()
                        .$(t("Person","Person"))
                        .and()
                    .build())
    );

    /**
     * DATEADD(month,-(10/2), SYSDATETIME())
     */
    public DateAdd exampleC4 = f_dateadd(
            DatePart.DatePartArgument.month,
            e_negative(e(
                    e_division(e_number(10),e_number(2)))),
            f_sysdatetime()
    );

    /**
     * DATEADD(day,ROW_NUMBER() OVER (ORDER BY
     a.PostalCode),SYSDATETIME())
     */
    public DateAdd exampleC5 = f_dateadd(
            DatePart.DatePartArgument.day,
            f_row_number($Over().$OrderBy(c("a","PostalCode")).build()),
            f_sysdatetime()
    );

    /**
     * DATEADD(day,SUM(OrderQty)
     OVER(PARTITION BY SalesOrderID),SYSDATETIME())
     */
    public DateAdd exampleC6 = f_dateadd(
            DatePart.DatePartArgument.day,
            f_sum(c("OrderQty"),$Over().$PartitionBy(c("SalesOrderID")).build()),
            f_sysdatetime()
    );

    @Test
    public void testExampleC(){
        assertEquals(exampleC.getDatepart(), DatePart.DatePartArgument.day);
        assertEquals(exampleC.getNumber().getClass(), LocalVariable.class);
        assertEquals(exampleC.getDate().getClass(), LocalVariable.class);
        assertEquals(exampleC2.getDatepart(), DatePart.DatePartArgument.month);
        assertEquals(exampleC2.getNumber().getClass(), NumberConstant.class);
        assertEquals(exampleC2.getDate().getClass(), SysDatetime.class);
        assertEquals(exampleC3.getDatepart(), DatePart.DatePartArgument.month);
        assertEquals(exampleC3.getNumber().getClass(), GroupExpression.class);
        assertEquals(exampleC3.getDate().getClass(), GroupExpression.class);
        assertEquals(exampleC4.getDatepart(), DatePart.DatePartArgument.month);
        assertEquals(exampleC4.getNumber().getClass(), UnaryExpression.class);
        assertEquals(exampleC4.getDate().getClass(), SysDatetime.class);
        assertEquals(exampleC5.getDatepart(), DatePart.DatePartArgument.day);
        assertEquals(exampleC5.getNumber().getClass(), Row_Number.class);
        assertEquals(exampleC5.getDate().getClass(), SysDatetime.class);
        assertEquals(exampleC6.getDatepart(), DatePart.DatePartArgument.day);
        assertEquals(exampleC6.getNumber().getClass(), Sum.class);
        assertEquals(exampleC6.getDate().getClass(), SysDatetime.class);
    }

}