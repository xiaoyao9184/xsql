package com.xy.xsql.tsql.converter.functions.string;

import com.xy.xsql.block.core.printer.MetaBlockPrinter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.builder.chain.functions.string.ReplicateFunctionTest;
import com.xy.xsql.tsql.converter.BaseTest;
import com.xy.xsql.tsql.model.functions.string.Replicate;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.StringWriter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by xiaoyao9184 on 2018/5/31.
 */
public class ReplicateConverterTest {

    @Test
    public void testMetaPrint() throws Exception {
        BlockMeta b = ReplicateConverter.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<REPLICATE> ::=\n" +
                        "REPLICATE ( string_expression , integer_expression )");
    }

    private Map<Replicate,String> model2StringMap;

    @Before
    public void init(){
        ReplicateFunctionTest builderTest = new ReplicateFunctionTest();
        model2StringMap = new LinkedHashMap<>();

        model2StringMap.put(
                builderTest.exampleA,
                "REPLICATE('0', 4)");
        model2StringMap.put(
                builderTest.exampleB,
                "REPLICATE('0', 3 - DATALENGTH(c1))");
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