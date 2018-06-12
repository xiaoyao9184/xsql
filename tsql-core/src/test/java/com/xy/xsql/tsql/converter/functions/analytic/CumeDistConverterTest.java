package com.xy.xsql.tsql.converter.functions.analytic;

import com.xy.xsql.block.core.printer.MetaBlockPrinter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.builder.chain.functions.analytic.CumeDistFunctionTest;
import com.xy.xsql.tsql.converter.BaseTest;
import com.xy.xsql.tsql.model.functions.analytic.Cume_Dist;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.StringWriter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by xiaoyao9184 on 2018/5/31.
 */
public class CumeDistConverterTest {

    @Test
    public void testMetaPrint() throws Exception {
        BlockMeta b = CumeDistConverter.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<CUME_DIST> ::=\n" +
                        "CUME_DIST ( )\n" +
                        "\tOVER ( [ partition_by_clause ] order_by_clause )");
    }

    private Map<Cume_Dist,String> model2StringMap;

    @Before
    public void init(){
        CumeDistFunctionTest builderTest = new CumeDistFunctionTest();
        model2StringMap = new LinkedHashMap<>();

        model2StringMap.put(
                builderTest.example1,
                "CUME_DIST () OVER (PARTITION BY Department ORDER BY Rate)");
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