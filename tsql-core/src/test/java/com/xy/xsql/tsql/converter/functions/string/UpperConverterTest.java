package com.xy.xsql.tsql.converter.functions.string;

import com.xy.xsql.block.core.printer.MetaBlockPrinter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.builder.chain.functions.string.UpperFunctionTest;
import com.xy.xsql.tsql.converter.BaseTest;
import com.xy.xsql.tsql.model.functions.string.Upper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.StringWriter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by xiaoyao9184 on 2018/5/31.
 */
public class UpperConverterTest {

    @Test
    public void testMetaPrint() throws Exception {
        BlockMeta b = UpperConverter.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<UPPER> ::=\n" +
                        "UPPER ( character_expression )");
    }

    private Map<Upper,String> model2StringMap;

    @Before
    public void init(){
        UpperFunctionTest builderTest = new UpperFunctionTest();
        model2StringMap = new LinkedHashMap<>();

        model2StringMap.put(
                builderTest.example1,
                "UPPER(RTRIM(LastName))");
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