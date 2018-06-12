package com.xy.xsql.tsql.converter.functions.conversion;

import com.xy.xsql.block.core.printer.MetaBlockPrinter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.builder.chain.functions.conversion.ParseFunctionTest;
import com.xy.xsql.tsql.converter.BaseTest;
import com.xy.xsql.tsql.model.functions.conversion.Parse;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.StringWriter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by xiaoyao9184 on 2018/5/31.
 */
public class ParseConverterTest {

    @Test
    public void testMetaPrint() throws Exception {
        BlockMeta b = ParseConverter.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<PARSE> ::=\n" +
                        "PARSE ( string_value AS data_type [ USING culture ] )");
    }

    private Map<Parse,String> model2StringMap;

    @Before
    public void init(){
        ParseFunctionTest builderTest = new ParseFunctionTest();
        model2StringMap = new LinkedHashMap<>();

        model2StringMap.put(
                builderTest.exampleA,
                "PARSE('Monday, 13 December 2010' AS datetime2 USING 'en-US')");
        model2StringMap.put(
                builderTest.exampleB,
                "PARSE('€345,98' AS money USING 'de-DE')");
        model2StringMap.put(
                builderTest.exampleC,
                "PARSE('12/16/2010' AS datetime2)");
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