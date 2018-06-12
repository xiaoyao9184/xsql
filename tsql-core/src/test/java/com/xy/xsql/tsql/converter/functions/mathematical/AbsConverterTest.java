package com.xy.xsql.tsql.converter.functions.mathematical;

import com.xy.xsql.block.core.printer.MetaBlockPrinter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.builder.chain.functions.mathematical.AbsFunctionTest;
import com.xy.xsql.tsql.converter.BaseTest;
import com.xy.xsql.tsql.model.functions.mathematical.Abs;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.StringWriter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by xiaoyao9184 on 2018/5/31.
 */
public class AbsConverterTest {

    @Test
    public void testMetaPrint() throws Exception {
        BlockMeta b = AbsConverter.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<ABS> ::=\n" +
                        "ABS ( numeric_expression )");
    }

    private Map<Abs,String> model2StringMap;

    @Before
    public void init(){
        AbsFunctionTest builderTest = new AbsFunctionTest();
        model2StringMap = new LinkedHashMap<>();

        model2StringMap.put(
                builderTest.example1,
                "ABS(-1.0)");
        model2StringMap.put(
                builderTest.example2,
                "ABS(@i)");
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