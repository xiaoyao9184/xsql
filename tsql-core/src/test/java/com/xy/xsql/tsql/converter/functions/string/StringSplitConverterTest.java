package com.xy.xsql.tsql.converter.functions.string;

import com.xy.xsql.block.core.printer.MetaBlockPrinter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.builder.chain.functions.string.StringSplitFunctionTest;
import com.xy.xsql.tsql.converter.BaseTest;
import com.xy.xsql.tsql.model.functions.string.String_Split;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.StringWriter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by xiaoyao9184 on 2018/5/31.
 */
public class StringSplitConverterTest {

    @Test
    public void testMetaPrint() throws Exception {
        BlockMeta b = StringSplitConverter.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<STRING_SPLIT> ::=\n" +
                        "STRING_SPLIT ( string , separator )");
    }

    private Map<String_Split,String> model2StringMap;

    @Before
    public void init(){
        StringSplitFunctionTest builderTest = new StringSplitFunctionTest();
        model2StringMap = new LinkedHashMap<>();

        model2StringMap.put(
                builderTest.exampleA,
                "STRING_SPLIT(@tags, ',')");
        model2StringMap.put(
                builderTest.exampleB,
                "STRING_SPLIT(Tags, ',')");
        model2StringMap.put(
                builderTest.exampleE,
                "STRING_SPLIT('1,2,3',',')");
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