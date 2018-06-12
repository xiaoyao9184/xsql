package com.xy.xsql.tsql.converter.functions.string;

import com.xy.xsql.block.core.printer.MetaBlockPrinter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.builder.chain.functions.string.SubStringFunctionTest;
import com.xy.xsql.tsql.converter.BaseTest;
import com.xy.xsql.tsql.model.functions.string.SubString;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.StringWriter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by xiaoyao9184 on 2018/5/31.
 */
public class SubStringConverterTest {

    @Test
    public void testMetaPrint() throws Exception {
        BlockMeta b = SubStringConverter.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<SUBSTRING> ::=\n" +
                        "SUBSTRING ( expression , start , length )");
    }

    private Map<SubString,String> model2StringMap;

    @Before
    public void init(){
        SubStringFunctionTest builderTest = new SubStringFunctionTest();
        model2StringMap = new LinkedHashMap<>();

        model2StringMap.put(
                builderTest.exampleA,
                "SUBSTRING(name, 1, 1)");
        model2StringMap.put(
                builderTest.exampleB1,
                "SUBSTRING(logo, 1, 10)");
        model2StringMap.put(
                builderTest.exampleB2,
                "SUBSTRING(pr.pr_info, 1, 35)");
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