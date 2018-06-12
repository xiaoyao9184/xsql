package com.xy.xsql.tsql.converter.functions.mathematical;

import com.xy.xsql.block.core.printer.MetaBlockPrinter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.builder.chain.functions.mathematical.RoundFunctionTest;
import com.xy.xsql.tsql.converter.BaseTest;
import com.xy.xsql.tsql.model.functions.mathematical.Round;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.StringWriter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by xiaoyao9184 on 2018/5/31.
 */
public class RoundConverterTest {

    @Test
    public void testMetaPrint() throws Exception {
        BlockMeta b = RoundConverter.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<ROUND> ::=\n" +
                        "ROUND ( numeric_expression , length [ , function ] )");
    }

    private Map<Round,String> model2StringMap;

    @Before
    public void init(){
        RoundFunctionTest builderTest = new RoundFunctionTest();
        model2StringMap = new LinkedHashMap<>();

        model2StringMap.put(
                builderTest.exampleA,
                "ROUND(123.9994, 3)");
        model2StringMap.put(
                builderTest.exampleB,
                "ROUND(123.45, -2)");
        model2StringMap.put(
                builderTest.exampleC,
                "ROUND(150.75, 0, 1)");
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