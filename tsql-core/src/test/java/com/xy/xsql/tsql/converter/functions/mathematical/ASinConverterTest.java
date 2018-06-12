package com.xy.xsql.tsql.converter.functions.mathematical;

import com.xy.xsql.block.core.printer.MetaBlockPrinter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.builder.chain.functions.mathematical.ASinFunctionTest;
import com.xy.xsql.tsql.converter.BaseTest;
import com.xy.xsql.tsql.model.functions.mathematical.ASin;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.StringWriter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by xiaoyao9184 on 2018/5/31.
 */
public class ASinConverterTest {

    @Test
    public void testMetaPrint() throws Exception {
        BlockMeta b = ASinConverter.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<ASIN> ::=\n" +
                        "ASIN ( float_expression )");
    }

    private Map<ASin,String> model2StringMap;

    @Before
    public void init(){
        ASinFunctionTest builderTest = new ASinFunctionTest();
        model2StringMap = new LinkedHashMap<>();

        model2StringMap.put(
                builderTest.example1,
                "ASIN(@angle)");
        model2StringMap.put(
                builderTest.example2,
                "ASIN(1.1472738)");
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