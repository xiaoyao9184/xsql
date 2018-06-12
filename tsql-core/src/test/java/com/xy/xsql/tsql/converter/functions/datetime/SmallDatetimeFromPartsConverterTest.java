package com.xy.xsql.tsql.converter.functions.datetime;

import com.xy.xsql.block.core.printer.MetaBlockPrinter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.builder.chain.functions.datetime.SmallDatetimeFromPartsFunctionTest;
import com.xy.xsql.tsql.converter.BaseTest;
import com.xy.xsql.tsql.model.functions.datetime.SmallDatetimeFromParts;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.StringWriter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by xiaoyao9184 on 2018/6/1.
 */
public class SmallDatetimeFromPartsConverterTest {

    @Test
    public void testMetaPrint() throws Exception {
        BlockMeta b = SmallDatetimeFromPartsConverter.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<SMALLDATETIMEFROMPARTS> ::=\n" +
                        "SMALLDATETIMEFROMPARTS ( year , month , day , hour , minute )");
    }

    private Map<SmallDatetimeFromParts,String> model2StringMap;

    @Before
    public void init(){
        SmallDatetimeFromPartsFunctionTest builderTest = new SmallDatetimeFromPartsFunctionTest();
        model2StringMap = new LinkedHashMap<>();

        model2StringMap.put(
                builderTest.example1,
                "SMALLDATETIMEFROMPARTS ( 2010, 12, 31, 23, 59 )");
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