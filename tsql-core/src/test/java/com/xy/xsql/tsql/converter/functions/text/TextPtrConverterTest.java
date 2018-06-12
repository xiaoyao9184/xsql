package com.xy.xsql.tsql.converter.functions.text;

import com.xy.xsql.block.core.printer.MetaBlockPrinter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.builder.chain.functions.text.TextPtrFunctionTest;
import com.xy.xsql.tsql.converter.BaseTest;
import com.xy.xsql.tsql.model.functions.text.TextPtr;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.StringWriter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by xiaoyao9184 on 2018/5/31.
 */
public class TextPtrConverterTest {

    @Test
    public void testMetaPrint() throws Exception {
        BlockMeta b = TextPtrConverter.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<TEXTPTR> ::=\n" +
                        "TEXTPTR ( column )");
    }

    private Map<TextPtr,String> model2StringMap;

    @Before
    public void init(){
        TextPtrFunctionTest builderTest = new TextPtrFunctionTest();
        model2StringMap = new LinkedHashMap<>();

        model2StringMap.put(
                builderTest.exampleA,
                "TEXTPTR(logo)");
        model2StringMap.put(
                builderTest.exampleB,
                "TEXTPTR(c2)");
        model2StringMap.put(
                builderTest.exampleC,
                "TEXTPTR(pr_info)");
        model2StringMap.put(
                builderTest.exampleD,
                "TEXTPTR(pr_info)");
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