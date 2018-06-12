package com.xy.xsql.tsql.converter.functions.datetime;

import com.xy.xsql.block.core.printer.MetaBlockPrinter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.builder.chain.functions.datetime.MonthFunctionTest;
import com.xy.xsql.tsql.converter.BaseTest;
import com.xy.xsql.tsql.model.functions.datetime.Month;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.StringWriter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by xiaoyao9184 on 2018/6/1.
 */
public class MonthConverterTest {

    @Test
    public void testMetaPrint() throws Exception {
        BlockMeta b = MonthConverter.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<MONTH> ::=\n" +
                        "MONTH ( date )");
    }

    private Map<Month,String> model2StringMap;

    @Before
    public void init(){
        MonthFunctionTest builderTest = new MonthFunctionTest();
        model2StringMap = new LinkedHashMap<>();

        model2StringMap.put(
                builderTest.example1,
                "MONTH('2007-04-30T01:01:01.1234567 -07:00')");
        model2StringMap.put(
                builderTest.example2,
                "MONTH(0)");
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