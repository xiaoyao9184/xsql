package com.xy.xsql.tsql.converter.functions.system;

import com.xy.xsql.block.core.printer.MetaBlockPrinter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.builder.chain.functions.system.FormatMessageFunctionTest;
import com.xy.xsql.tsql.converter.BaseTest;
import com.xy.xsql.tsql.model.functions.system.FormatMessage;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.StringWriter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by xiaoyao9184 on 2018/6/1.
 */
public class FormatMessageConverterTest {

    @Test
    public void testMetaPrint() throws Exception {
        BlockMeta b = FormatMessageConverter.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<FORMATMESSAGE> ::=\n" +
                        "FORMATMESSAGE ( { msg_number | 'msg_string' } [ , param_value [,...n] ] )");
    }

    private Map<FormatMessage,String> model2StringMap;

    @Before
    public void init(){
        FormatMessageFunctionTest builderTest = new FormatMessageFunctionTest();
        model2StringMap = new LinkedHashMap<>();

        model2StringMap.put(
                builderTest.exampleA,
                "FORMATMESSAGE(20009, 'First Variable', 'Second Variable')");
        model2StringMap.put(
                builderTest.exampleB,
                "FORMATMESSAGE('This is the %s and this is the %s.', 'first variable', 'second variable')");
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