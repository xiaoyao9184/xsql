package com.xy.xsql.tsql.converter.functions.analytic;

import com.xy.xsql.block.core.printer.MetaBlockPrinter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.builder.chain.functions.analytic.LastValueFunctionTest;
import com.xy.xsql.tsql.converter.BaseTest;
import com.xy.xsql.tsql.model.functions.analytic.Last_Value;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.StringWriter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by xiaoyao9184 on 2018/5/31.
 */
public class LastValueConverterTest {

    @Test
    public void testMetaPrint() throws Exception {
        BlockMeta b = LastValueConverter.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<LAST_VALUE> ::=\n" +
                        "LAST_VALUE ( [ scalar_expression ] )\n" +
                        "\tOVER ( [ partition_by_clause ] order_by_clause [ rows_range_clause ] )");
    }

    private Map<Last_Value,String> model2StringMap;

    @Before
    public void init(){
        LastValueFunctionTest builderTest = new LastValueFunctionTest();
        model2StringMap = new LinkedHashMap<>();

        model2StringMap.put(
                builderTest.exampleA,
                "LAST_VALUE(HireDate) OVER (PARTITION BY Department ORDER BY Rate)");
        model2StringMap.put(
                builderTest.exampleB,
                "LAST_VALUE(SalesQuota)\n" +
                        "     OVER (PARTITION BY BusinessEntityID, YEAR(QuotaDate)\n" +
                        "     ORDER BY DATEPART(quarter,QuotaDate)\n" +
                        "     RANGE BETWEEN CURRENT ROW AND UNBOUNDED FOLLOWING )");
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