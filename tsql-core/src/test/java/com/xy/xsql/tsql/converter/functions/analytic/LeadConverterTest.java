package com.xy.xsql.tsql.converter.functions.analytic;

import com.xy.xsql.block.core.printer.MetaBlockPrinter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.builder.chain.functions.analytic.LeadFunctionTest;
import com.xy.xsql.tsql.converter.BaseTest;
import com.xy.xsql.tsql.model.functions.analytic.Lead;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.StringWriter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by xiaoyao9184 on 2018/5/31.
 */
public class LeadConverterTest {

    @Test
    public void testMetaPrint() throws Exception {
        BlockMeta b = LeadConverter.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<LEAD> ::=\n" +
                        "LEAD ( [ scalar_expression ] [ , offset ] [ , default ] )\n" +
                        "\tOVER ( [ partition_by_clause ] order_by_clause )");
    }

    private Map<Lead,String> model2StringMap;

    @Before
    public void init(){
        LeadFunctionTest builderTest = new LeadFunctionTest();
        model2StringMap = new LinkedHashMap<>();

        model2StringMap.put(
                builderTest.exampleA,
                "LEAD(SalesQuota, 1,0) OVER (ORDER BY YEAR(QuotaDate))");
        model2StringMap.put(
                builderTest.exampleB,
                "LEAD (SalesYTD, 1, 0) OVER (PARTITION BY TerritoryName ORDER BY SalesYTD DESC)");
        model2StringMap.put(
                builderTest.exampleC,
                "LEAD(2*c, b*(SELECT MIN(b) FROM T), -c/2.0) OVER (ORDER BY a)");
        model2StringMap.put(
                builderTest.exampleD,
                "LEAD(SalesAmountQuota,1,0) OVER (ORDER BY CalendarYear, CalendarQuarter)");
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