package com.xy.xsql.tsql.converter.functions.string;

import com.xy.xsql.block.core.printer.MetaBlockPrinter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.builder.chain.functions.string.NCharFunctionTest;
import com.xy.xsql.tsql.converter.BaseTest;
import com.xy.xsql.tsql.model.functions.string.NChar;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.StringWriter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by xiaoyao9184 on 2018/5/31.
 */
public class NCharConverterTest {

    @Test
    public void testMetaPrint() throws Exception {
        BlockMeta b = NCharConverter.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<NCHAR> ::=\n" +
                        "NCHAR ( integer_expression )");
    }

    private Map<NChar,String> model2StringMap;

    @Before
    public void init(){
        NCharFunctionTest builderTest = new NCharFunctionTest();
        model2StringMap = new LinkedHashMap<>();

        model2StringMap.put(
                builderTest.exampleA,
                "NCHAR(UNICODE(SUBSTRING(@nstring, 2, 1)))");
        model2StringMap.put(
                builderTest.exampleB,
                "NCHAR(UNICODE(SUBSTRING(@nstring, @position, 1)))");
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