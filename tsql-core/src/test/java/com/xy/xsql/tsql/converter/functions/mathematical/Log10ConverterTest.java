package com.xy.xsql.tsql.converter.functions.mathematical;

import com.xy.xsql.block.core.printer.MetaBlockPrinter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.builder.chain.functions.mathematical.Log10FunctionTest;
import com.xy.xsql.tsql.converter.BaseTest;
import com.xy.xsql.tsql.model.functions.mathematical.Log10;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.StringWriter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by xiaoyao9184 on 2018/5/31.
 */
public class Log10ConverterTest {

    @Test
    public void testMetaPrint() throws Exception {
        BlockMeta b = Log10Converter.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<LOG10> ::=\n" +
                        "LOG10 ( float_expression )");
    }

    private Map<Log10,String> model2StringMap;

    @Before
    public void init(){
        Log10FunctionTest builderTest = new Log10FunctionTest();
        model2StringMap = new LinkedHashMap<>();

        model2StringMap.put(
                builderTest.example1,
                "LOG10(@var)");
        model2StringMap.put(
                builderTest.example2,
                "LOG10(5)");
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