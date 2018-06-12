package com.xy.xsql.tsql.converter.functions.string;

import com.xy.xsql.block.core.printer.MetaBlockPrinter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.builder.chain.functions.string.UnicodeFunctionTest;
import com.xy.xsql.tsql.converter.BaseTest;
import com.xy.xsql.tsql.model.functions.string.Unicode;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.StringWriter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by xiaoyao9184 on 2018/5/31.
 */
public class UnicodeConverterTest {

    @Test
    public void testMetaPrint() throws Exception {
        BlockMeta b = UnicodeConverter.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<UNICODE> ::=\n" +
                        "UNICODE ( 'ncharacter_expression' )");
    }

    private Map<Unicode,String> model2StringMap;

    @Before
    public void init(){
        UnicodeFunctionTest builderTest = new UnicodeFunctionTest();
        model2StringMap = new LinkedHashMap<>();

        model2StringMap.put(
                builderTest.exampleA,
                "UNICODE(@nstring)");
        model2StringMap.put(
                builderTest.exampleB,
                "UNICODE(SUBSTRING(@nstring, @position, 1))");
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