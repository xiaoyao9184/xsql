package com.xy.xsql.tsql.converter.functions.analytic;

import com.xy.xsql.block.core.printer.MetaBlockPrinter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.builder.chain.functions.analytic.PercentileContFunctionTest;
import com.xy.xsql.tsql.converter.BaseTest;
import com.xy.xsql.tsql.model.functions.analytic.Percentile_Cont;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.StringWriter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by xiaoyao9184 on 2018/5/31.
 */
public class PercentileContConverterTest {

    @Test
    public void testMetaPrint() throws Exception {
        BlockMeta b = PercentileContConverter.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<PERCENTILE_CONT> ::=\n" +
                        "PERCENTILE_CONT ( numeric_literal )\n" +
                        "\tWITHIN GROUP ( ORDER BY order_by_expression [ ASC | DESC ] )\n" +
                        "\tOVER ( [ partition_by_clause ] )");
    }

    private Map<Percentile_Cont,String> model2StringMap;

    @Before
    public void init(){
        PercentileContFunctionTest builderTest = new PercentileContFunctionTest();
        model2StringMap = new LinkedHashMap<>();

        model2StringMap.put(
                builderTest.exampleA,
                "PERCENTILE_CONT(0.5) WITHIN GROUP (ORDER BY ph.Rate)\n" +
                        "     OVER (PARTITION BY Name)");
        model2StringMap.put(
                builderTest.exampleB,
                "PERCENTILE_CONT(0.5) WITHIN GROUP (ORDER BY BaseRate)\n" +
                        "     OVER (PARTITION BY DepartmentName)");
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