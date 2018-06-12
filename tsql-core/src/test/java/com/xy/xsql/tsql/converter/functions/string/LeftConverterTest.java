package com.xy.xsql.tsql.converter.functions.string;

import com.xy.xsql.block.core.printer.MetaBlockPrinter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.builder.chain.functions.string.LeftFunctionTest;
import com.xy.xsql.tsql.converter.BaseTest;
import com.xy.xsql.tsql.model.functions.string.Left;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.StringWriter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by xiaoyao9184 on 2018/5/31.
 */
public class LeftConverterTest {

    @Test
    public void testMetaPrint() throws Exception {
        BlockMeta b = LeftConverter.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<LEFT> ::=\n" +
                        "LEFT ( character_expression , integer_expression )");
    }

    private Map<Left,String> model2StringMap;

    @Before
    public void init(){
        LeftFunctionTest builderTest = new LeftFunctionTest();
        model2StringMap = new LinkedHashMap<>();

        model2StringMap.put(
                builderTest.exampleA,
                "LEFT(Name, 5)");
        model2StringMap.put(
                builderTest.exampleB,
                "LEFT('abcdefg',2)");
        model2StringMap.put(
                builderTest.exampleC,
                "LEFT(EnglishProductName, 5)");
        model2StringMap.put(
                builderTest.exampleD,
                "LEFT('abcdefg',2)");
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