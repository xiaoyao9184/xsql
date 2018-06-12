package com.xy.xsql.tsql.converter.functions.datetime;

import com.xy.xsql.block.core.printer.MetaBlockPrinter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.builder.chain.functions.datetime.DateDiffFunctionTest;
import com.xy.xsql.tsql.converter.BaseTest;
import com.xy.xsql.tsql.model.functions.datetime.DateDiff;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.StringWriter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by xiaoyao9184 on 2018/6/1.
 */
public class DateDiffConverterTest {

    @Test
    public void testMetaPrint() throws Exception {
        BlockMeta b = DateDiffConverter.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<DATEDIFF> ::=\n" +
                        "DATEDIFF ( datepart , startdate , startdate )");
    }

    private Map<DateDiff,String> model2StringMap;

    @Before
    public void init(){
        DateDiffFunctionTest builderTest = new DateDiffFunctionTest();
        model2StringMap = new LinkedHashMap<>();

        model2StringMap.put(
                builderTest.exampleA,
                "DATEDIFF(day,startDate,endDate)");
        model2StringMap.put(
                builderTest.exampleB,
                "DATEDIFF(day, @startDate, @endDate)");
        model2StringMap.put(
                builderTest.exampleC,
                "DATEDIFF(millisecond, GETDATE(), SYSDATETIME())");
        model2StringMap.put(
                builderTest.exampleD,
                "DATEDIFF(day,(SELECT MIN(OrderDate) FROM Sales.SalesOrderHeader),\n" +
                        "     (SELECT MAX(OrderDate) FROM Sales.SalesOrderHeader))");
        model2StringMap.put(
                builderTest.exampleE,
                "DATEDIFF(day, '2007-05-07 09:53:01.0376635'\n" +
                        "     , '2007-05-08 09:53:01.0376635')");
        model2StringMap.put(
                builderTest.exampleF1,
                "DATEDIFF(day, '2007-05-07 09:53:01.0376635', GETDATE()+ 1)");
        model2StringMap.put(
                builderTest.exampleF2,
                "DATEDIFF(day, '2007-05-07 09:53:01.0376635', DATEADD(day,1,SYSDATETIME()))");
        model2StringMap.put(
                builderTest.exampleG,
                "DATEDIFF(day,ROW_NUMBER() OVER (ORDER BY\n" +
                        "     a.PostalCode),SYSDATETIME())");
        model2StringMap.put(
                builderTest.exampleH,
                "DATEDIFF(day,MIN(soh.OrderDate)\n" +
                        "     OVER(PARTITION BY soh.SalesOrderID),SYSDATETIME() )");
    }

    @Test
    public void testPrint() throws Exception {
        BaseTest.testPrint(model2StringMap);
    }

    @Test
    public void testKeywordPrint() throws Exception {
        BaseTest.testKeywordPrint(model2StringMap);
    }

}