package com.xy.xsql.tsql.converter.functions.datetime;

import com.xy.xsql.block.core.printer.MetaBlockPrinter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.builder.chain.functions.datetime.DateAddFunctionTest;
import com.xy.xsql.tsql.converter.BaseTest;
import com.xy.xsql.tsql.model.functions.datetime.DateAdd;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.StringWriter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by xiaoyao9184 on 2018/6/1.
 */
public class DateAddConverterTest {

    @Test
    public void testMetaPrint() throws Exception {
        BlockMeta b = DateAddConverter.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<DATEADD> ::=\n" +
                        "DATEADD ( datepart , number , date )");
    }

    private Map<DateAdd,String> model2StringMap;

    @Before
    public void init(){
        DateAddFunctionTest builderTest = new DateAddFunctionTest();
        model2StringMap = new LinkedHashMap<>();

        model2StringMap.put(
                builderTest.exampleA1,
                "DATEADD(year,1,@datetime2)");
        model2StringMap.put(
                builderTest.exampleC,
                "DATEADD(day, @days, @datetime)");
        model2StringMap.put(
                builderTest.exampleC2,
                "DATEADD(month, 1, SYSDATETIME())");
        model2StringMap.put(
                builderTest.exampleC3,
                "DATEADD(month,(SELECT TOP (1) BusinessEntityID FROM Person.Person),\n" +
                        "     (SELECT MAX(ModifiedDate) FROM Person.Person))");
        model2StringMap.put(
                builderTest.exampleC4,
                "DATEADD(month,-(10/2), SYSDATETIME())");
        model2StringMap.put(
                builderTest.exampleC5,
                "DATEADD(day,ROW_NUMBER() OVER (ORDER BY\n" +
                        "     a.PostalCode),SYSDATETIME())");
        model2StringMap.put(
                builderTest.exampleC6,
                "DATEADD(day,SUM(OrderQty)\n" +
                        "     OVER(PARTITION BY SalesOrderID),SYSDATETIME())");
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