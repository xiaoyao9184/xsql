package com.xy.xsql.tsql.converter.functions.security;

import com.xy.xsql.block.core.printer.MetaBlockPrinter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.builder.chain.functions.security.SUserSNameFunctionTest;
import com.xy.xsql.tsql.converter.BaseTest;
import com.xy.xsql.tsql.model.functions.security.SUser_SName;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.StringWriter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by xiaoyao9184 on 2018/6/11.
 */
public class SUserSNameConverterTest {

    @Test
    public void testMetaPrint() throws Exception {
        BlockMeta b = SUserSNameConverter.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<SUSER_SNAME> ::=\n" +
                        "SUSER_SNAME ( [ server_user_sid ] )");
    }

    private Map<SUser_SName,String> model2StringMap;

    @Before
    public void init(){
        SUserSNameFunctionTest builderTest = new SUserSNameFunctionTest();
        model2StringMap = new LinkedHashMap<>();

        model2StringMap.put(
                builderTest.exampleA,
                "SUSER_SNAME()");
        model2StringMap.put(
                builderTest.exampleB,
                "SUSER_SNAME(0x010500000000000515000000a065cf7e784b9b5fe77c87705a2e0000)");
        model2StringMap.put(
                builderTest.exampleE,
                "SUSER_SNAME(0x01)");
    }

    @Test
    public void testPrint() throws Exception {
        BaseTest.testPrintIgnoreCase(model2StringMap);
    }

    @Test
    public void testKeywordPrint() throws Exception {
        BaseTest.testKeywordPrintIgnoreCase(model2StringMap);
    }

}