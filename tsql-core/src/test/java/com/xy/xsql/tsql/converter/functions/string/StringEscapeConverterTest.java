package com.xy.xsql.tsql.converter.functions.string;

import com.xy.xsql.block.core.printer.MetaBlockPrinter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.builder.chain.functions.string.StringEscapeFunctionTest;
import com.xy.xsql.tsql.converter.BaseTest;
import com.xy.xsql.tsql.model.functions.string.String_Escape;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.StringWriter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by xiaoyao9184 on 2018/5/31.
 */
public class StringEscapeConverterTest {

    @Test
    public void testMetaPrint() throws Exception {
        BlockMeta b = StringEscapeConverter.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<STRING_ESCAPE> ::=\n" +
                        "STRING_ESCAPE ( text , type )");
    }

    private Map<String_Escape,String> model2StringMap;

    @Before
    public void init(){
        StringEscapeFunctionTest builderTest = new StringEscapeFunctionTest();
        model2StringMap = new LinkedHashMap<>();

        model2StringMap.put(
                builderTest.exampleA,
                "STRING_ESCAPE('\\   /\n" +
                        "     \\\\    \"     ', 'json')");
        model2StringMap.put(
                builderTest.exampleB,
                "STRING_ESCAPE(@name,'json')");
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