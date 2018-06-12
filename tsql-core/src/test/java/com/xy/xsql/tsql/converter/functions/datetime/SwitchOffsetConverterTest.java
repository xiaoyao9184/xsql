package com.xy.xsql.tsql.converter.functions.datetime;

import com.xy.xsql.block.core.printer.MetaBlockPrinter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.builder.chain.functions.datetime.SwitchOffsetFunctionTest;
import com.xy.xsql.tsql.converter.BaseTest;
import com.xy.xsql.tsql.model.functions.datetime.SwitchOffset;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.StringWriter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by xiaoyao9184 on 2018/6/1.
 */
public class SwitchOffsetConverterTest {

    @Test
    public void testMetaPrint() throws Exception {
        BlockMeta b = SwitchOffsetConverter.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<SWITCHOFFSET> ::=\n" +
                        "SWITCHOFFSET ( DATETIMEOFFSET , time_zone )");
    }

    private Map<SwitchOffset,String> model2StringMap;

    @Before
    public void init(){
        SwitchOffsetFunctionTest builderTest = new SwitchOffsetFunctionTest();
        model2StringMap = new LinkedHashMap<>();

        model2StringMap.put(
                builderTest.example1,
                "SWITCHOFFSET (ColDatetimeoffset, '-08:00')");
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