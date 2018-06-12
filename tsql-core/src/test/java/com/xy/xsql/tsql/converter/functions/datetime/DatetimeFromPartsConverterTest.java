package com.xy.xsql.tsql.converter.functions.datetime;

import com.xy.xsql.block.core.printer.MetaBlockPrinter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.builder.chain.functions.datetime.DatetimeFromPartsFunctionTest;
import com.xy.xsql.tsql.converter.BaseTest;
import com.xy.xsql.tsql.model.functions.datetime.DatetimeFromParts;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.StringWriter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by xiaoyao9184 on 2018/6/1.
 */
public class DatetimeFromPartsConverterTest {

    @Test
    public void testMetaPrint() throws Exception {
        BlockMeta b = DatetimeFromPartsConverter.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<DATETIMEFROMPARTS> ::=\n" +
                        "DATETIMEFROMPARTS ( year , month , day , hour , minute , seconds , milliseconds )");
    }

    private Map<DatetimeFromParts,String> model2StringMap;

    @Before
    public void init(){
        DatetimeFromPartsFunctionTest builderTest = new DatetimeFromPartsFunctionTest();
        model2StringMap = new LinkedHashMap<>();

        model2StringMap.put(
                builderTest.example1,
                "DATETIMEFROMPARTS ( 2010, 12, 31, 23, 59, 59, 0 )");
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