package com.xy.xsql.tsql.converter.functions.datetime;

import com.xy.xsql.block.core.printer.MetaBlockPrinter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.builder.chain.functions.datetime.ToDatetimeOffsetFunctionTest;
import com.xy.xsql.tsql.converter.BaseTest;
import com.xy.xsql.tsql.model.functions.datetime.ToDatetimeOffset;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.StringWriter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by xiaoyao9184 on 2018/6/1.
 */
public class ToDatetimeOffsetConverterTest {

    @Test
    public void testMetaPrint() throws Exception {
        BlockMeta b = ToDatetimeOffsetConverter.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<TODATETIMEOFFSET> ::=\n" +
                        "TODATETIMEOFFSET ( expression , time_zone )");
    }

    private Map<ToDatetimeOffset,String> model2StringMap;

    @Before
    public void init(){
        ToDatetimeOffsetFunctionTest builderTest = new ToDatetimeOffsetFunctionTest();
        model2StringMap = new LinkedHashMap<>();

        model2StringMap.put(
                builderTest.exampleA,
                "TODATETIMEOFFSET (@todaysDateTime, '-07:00')");
        model2StringMap.put(
                builderTest.exampleB,
                "TODATETIMEOFFSET (@todaysDate, -120)");
        model2StringMap.put(
                builderTest.exampleC,
                "TODATETIMEOFFSET (@dateTime, '+13:00')");
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