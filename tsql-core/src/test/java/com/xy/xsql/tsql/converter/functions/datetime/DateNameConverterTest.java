package com.xy.xsql.tsql.converter.functions.datetime;

import com.xy.xsql.block.core.printer.MetaBlockPrinter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.builder.chain.functions.datetime.DateNameFunctionTest;
import com.xy.xsql.tsql.converter.BaseTest;
import com.xy.xsql.tsql.model.functions.datetime.DateName;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.StringWriter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by xiaoyao9184 on 2018/6/1.
 */
public class DateNameConverterTest {

    @Test
    public void testMetaPrint() throws Exception {
        BlockMeta b = DateNameConverter.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<DATENAME> ::=\n" +
                        "DATENAME ( datepart , date )");
    }

    private Map<DateName,String> model2StringMap;

    @Before
    public void init(){
        DateNameFunctionTest builderTest = new DateNameFunctionTest();
        model2StringMap = new LinkedHashMap<>();

        model2StringMap.put(
                builderTest.example1,
                "DATENAME(yyyy,'2007-10-30 12:15:32.1234567 +05:10')");
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