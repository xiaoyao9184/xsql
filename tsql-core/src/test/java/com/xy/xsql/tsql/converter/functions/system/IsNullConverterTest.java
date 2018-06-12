package com.xy.xsql.tsql.converter.functions.system;

import com.xy.xsql.block.core.printer.MetaBlockPrinter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.builder.chain.functions.system.IsNullFunctionTest;
import com.xy.xsql.tsql.converter.BaseTest;
import com.xy.xsql.tsql.model.functions.system.IsNull;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.StringWriter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by xiaoyao9184 on 2018/6/1.
 */
public class IsNullConverterTest {

    @Test
    public void testMetaPrint() throws Exception {
        BlockMeta b = IsNullConverter.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<ISNULL> ::=\n" +
                        "ISNULL ( check_expression , replacement_value )");
    }

    private Map<IsNull,String> model2StringMap;

    @Before
    public void init(){
        IsNullFunctionTest builderTest = new IsNullFunctionTest();
        model2StringMap = new LinkedHashMap<>();

        model2StringMap.put(
                builderTest.exampleA,
                "ISNULL(Weight, 50)");
        model2StringMap.put(
                builderTest.exampleB,
                "ISNULL(MaxQty, 0.0)");
        model2StringMap.put(
                builderTest.exampleD,
                "ISNULL(Weight, 50)");
        model2StringMap.put(
                builderTest.exampleE,
                "ISNULL(MinPaymentAmount,0)");
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