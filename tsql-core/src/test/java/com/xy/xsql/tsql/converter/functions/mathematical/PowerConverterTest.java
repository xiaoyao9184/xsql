package com.xy.xsql.tsql.converter.functions.mathematical;

import com.xy.xsql.block.core.printer.MetaBlockPrinter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.builder.chain.functions.mathematical.PowerFunctionTest;
import com.xy.xsql.tsql.converter.BaseTest;
import com.xy.xsql.tsql.model.functions.mathematical.Power;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.StringWriter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by xiaoyao9184 on 2018/5/31.
 */
public class PowerConverterTest {

    @Test
    public void testMetaPrint() throws Exception {
        BlockMeta b = PowerConverter.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<POWER> ::=\n" +
                        "POWER ( float_expression , y )");
    }

    private Map<Power,String> model2StringMap;

    @Before
    public void init(){
        PowerFunctionTest builderTest = new PowerFunctionTest();
        model2StringMap = new LinkedHashMap<>();

        model2StringMap.put(
                builderTest.exampleA,
                "POWER(@input1, 3)");
        model2StringMap.put(
                builderTest.exampleB,
                "POWER(CAST(2.0 AS float), -100.0)");
        model2StringMap.put(
                builderTest.exampleC,
                "POWER(@value, @counter)");
        model2StringMap.put(
                builderTest.exampleD,
                "POWER(2.0, 3)");
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