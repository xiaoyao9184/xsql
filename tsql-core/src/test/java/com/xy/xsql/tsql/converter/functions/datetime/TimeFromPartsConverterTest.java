package com.xy.xsql.tsql.converter.functions.datetime;

import com.xy.xsql.block.core.printer.MetaBlockPrinter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.builder.chain.functions.datetime.TimeFromPartsFunctionTest;
import com.xy.xsql.tsql.converter.BaseTest;
import com.xy.xsql.tsql.model.functions.datetime.TimeFromParts;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.StringWriter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by xiaoyao9184 on 2018/6/1.
 */
public class TimeFromPartsConverterTest {

    @Test
    public void testMetaPrint() throws Exception {
        BlockMeta b = TimeFromPartsConverter.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<TIMEFROMPARTS> ::=\n" +
                        "TIMEFROMPARTS ( hour , minute , seconds , fractions , precision )");
    }

    private Map<TimeFromParts,String> model2StringMap;

    @Before
    public void init(){
        TimeFromPartsFunctionTest builderTest = new TimeFromPartsFunctionTest();
        model2StringMap = new LinkedHashMap<>();

        model2StringMap.put(
                builderTest.exampleA,
                "TIMEFROMPARTS ( 23, 59, 59, 0, 0 )");
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