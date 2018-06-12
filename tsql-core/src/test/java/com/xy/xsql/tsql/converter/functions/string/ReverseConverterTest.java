package com.xy.xsql.tsql.converter.functions.string;

import com.xy.xsql.block.core.printer.MetaBlockPrinter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.builder.chain.functions.string.ReverseFunctionTest;
import com.xy.xsql.tsql.converter.BaseTest;
import com.xy.xsql.tsql.model.functions.string.Reverse;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.StringWriter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by xiaoyao9184 on 2018/5/31.
 */
public class ReverseConverterTest {

    @Test
    public void testMetaPrint() throws Exception {
        BlockMeta b = ReverseConverter.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<REVERSE> ::=\n" +
                        "REVERSE ( string_expression )");
    }

    private Map<Reverse,String> model2StringMap;

    @Before
    public void init(){
        ReverseFunctionTest builderTest = new ReverseFunctionTest();
        model2StringMap = new LinkedHashMap<>();

        model2StringMap.put(
                builderTest.example1,
                "REVERSE(FirstName)");
        model2StringMap.put(
                builderTest.example2,
                "REVERSE(@myvar)");
        model2StringMap.put(
                builderTest.example3,
                "REVERSE(1234)");
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