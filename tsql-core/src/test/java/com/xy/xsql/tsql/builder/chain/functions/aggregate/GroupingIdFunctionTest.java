package com.xy.xsql.tsql.builder.chain.functions.aggregate;

import com.xy.xsql.tsql.model.functions.aggregate.Grouping_Id;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.datatypes.table.ColumnNameFactory.c;
import static com.xy.xsql.tsql.builder.chain.elements.expressions.Expressions.e;
import static com.xy.xsql.tsql.builder.chain.functions.AggregateFunctions.f_grouping_id;
import static com.xy.xsql.tsql.builder.chain.functions.DataTimeFunctions.f_datepart;
import static com.xy.xsql.tsql.model.functions.datetime.DatePart.DatePartArgument.dd;
import static com.xy.xsql.tsql.model.functions.datetime.DatePart.DatePartArgument.mm;
import static com.xy.xsql.tsql.model.functions.datetime.DatePart.DatePartArgument.yyyy;
import static org.junit.Assert.assertEquals;

/**
 * Created by xiaoyao9184 on 2018/6/5.
 */
public class GroupingIdFunctionTest {


    /**
     * GROUPING_ID(D.Name, E.JobTitle)
     */
    public Grouping_Id exampleA = f_grouping_id(
            c("D","Name"),
            c("E","JobTitle"));

    @Test
    public void testExampleA(){
        assertEquals(exampleA.getColumnExpressionList().size(), 2);
    }

    /**
     * GROUPING_ID((T.[Group])
     ,(T.CountryRegionCode),(S.Name),(H.SalesPersonID)
     )
     */
    public Grouping_Id exampleB = f_grouping_id(
            e(c("T","[Group]")),
            e(c("T","CountryRegionCode")),
            e(c("S","Name")),
            e(c("H","SalesPersonID")));

    @Test
    public void testExampleB(){
        assertEquals(exampleB.getColumnExpressionList().size(), 4);
    }

    /**
     * GROUPING_ID(DATEPART(yyyy,OrderDate)
     ,DATEPART(mm,OrderDate)
     ,DATEPART(dd,OrderDate))
     */
    public Grouping_Id exampleC = f_grouping_id(
            f_datepart(yyyy,c("OrderDate")),
            f_datepart(mm,c("OrderDate")),
            f_datepart(dd,c("OrderDate")));

    @Test
    public void testExampleC(){
        assertEquals(exampleC.getColumnExpressionList().size(), 3);
    }
    
}