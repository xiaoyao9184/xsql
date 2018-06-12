package com.xy.xsql.tsql.converter.functions.datetime;

import com.xy.xsql.block.core.printer.MetaBlockPrinter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.builder.chain.functions.datetime.DatetimeOffsetFromPartsFunctionTest;
import com.xy.xsql.tsql.converter.BaseTest;
import com.xy.xsql.tsql.model.functions.datetime.DatetimeOffsetFromParts;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.StringWriter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by xiaoyao9184 on 2018/6/1.
 */
public class DatetimeOffsetFromPartsConverterTest {

    @Test
    public void testMetaPrint() throws Exception {
        BlockMeta b = DatetimeOffsetFromPartsConverter.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<DATETIMEOFFSETFROMPARTS> ::=\n" +
                        "DATETIMEOFFSETFROMPARTS ( year , month , day , hour , minute , seconds , fractions , hour_offset , minute_offset , precision )");
    }

    private Map<DatetimeOffsetFromParts,String> model2StringMap;

    @Before
    public void init(){
        DatetimeOffsetFromPartsFunctionTest builderTest = new DatetimeOffsetFromPartsFunctionTest();
        model2StringMap = new LinkedHashMap<>();

        model2StringMap.put(
                builderTest.exampleA,
                "DATETIMEOFFSETFROMPARTS ( 2010, 12, 31, 14, 23, 23, 0, 12, 0, 7 )");
        model2StringMap.put(
                builderTest.exampleB,
                "DATETIMEOFFSETFROMPARTS ( 2011, 8, 15, 14, 30, 0, 5, 12, 30, 1 )");
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