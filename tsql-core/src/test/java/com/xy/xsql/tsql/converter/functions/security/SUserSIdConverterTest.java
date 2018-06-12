package com.xy.xsql.tsql.converter.functions.security;

import com.xy.xsql.block.core.printer.MetaBlockPrinter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.builder.chain.functions.security.SUserSIdFunctionTest;
import com.xy.xsql.tsql.converter.BaseTest;
import com.xy.xsql.tsql.model.functions.security.SUser_SId;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.StringWriter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by xiaoyao9184 on 2018/6/11.
 */
public class SUserSIdConverterTest {

    @Test
    public void testMetaPrint() throws Exception {
        BlockMeta b = SUserSIdConverter.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<SUSER_SID> ::=\n" +
                        "SUSER_SID ( [ 'login' ] [ , Param2  ] )");
    }

    private Map<SUser_SId,String> model2StringMap;

    @Before
    public void init(){
        SUserSIdFunctionTest builderTest = new SUserSIdFunctionTest();
        model2StringMap = new LinkedHashMap<>();

        model2StringMap.put(
                builderTest.exampleA,
                "SUSER_SID()");
        model2StringMap.put(
                builderTest.exampleB,
                "SUSER_SID('sa')");
        model2StringMap.put(
                builderTest.exampleC,
                "SUSER_SID('London\\Workstation1')");
        model2StringMap.put(
                builderTest.exampleE,
                "SUSER_SID('TestComputer\\User', 0)");
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