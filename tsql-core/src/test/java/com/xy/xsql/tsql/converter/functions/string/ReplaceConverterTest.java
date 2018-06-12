package com.xy.xsql.tsql.converter.functions.string;

import com.xy.xsql.block.core.printer.MetaBlockPrinter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.builder.chain.functions.string.ReplaceFunctionTest;
import com.xy.xsql.tsql.converter.BaseTest;
import com.xy.xsql.tsql.model.functions.string.Replace;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.StringWriter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by xiaoyao9184 on 2018/5/31.
 */
public class ReplaceConverterTest {

    @Test
    public void testMetaPrint() throws Exception {
        BlockMeta b = ReplaceConverter.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<REPLACE> ::=\n" +
                        "REPLACE ( string_expression , string_pattern , string_replacement )");
    }

    private Map<Replace,String> model2StringMap;

    @Before
    public void init(){
        ReplaceFunctionTest builderTest = new ReplaceFunctionTest();
        model2StringMap = new LinkedHashMap<>();

        model2StringMap.put(
                builderTest.example1,
                "REPLACE('abcdefghicde','cde','xxx')");
//        model2StringMap.put(
//                builderTest.example2,
//                "REPLACE('This is a Test'  COLLATE Latin1_General_BIN,\n" +
//                        "     'Test', 'desk' )");
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