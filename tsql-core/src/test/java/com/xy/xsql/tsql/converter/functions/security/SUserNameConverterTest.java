package com.xy.xsql.tsql.converter.functions.security;

import com.xy.xsql.block.core.printer.MetaBlockPrinter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.builder.chain.functions.security.SUserNameFunctionTest;
import com.xy.xsql.tsql.converter.BaseTest;
import com.xy.xsql.tsql.model.functions.security.SUser_Name;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.StringWriter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by xiaoyao9184 on 2018/6/11.
 */
public class SUserNameConverterTest {

    @Test
    public void testMetaPrint() throws Exception {
        BlockMeta b = SUserNameConverter.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<SUSER_NAME> ::=\n" +
                        "SUSER_NAME ( [ server_user_id ] )");
    }

    private Map<SUser_Name,String> model2StringMap;

    @Before
    public void init(){
        SUserNameFunctionTest builderTest = new SUserNameFunctionTest();
        model2StringMap = new LinkedHashMap<>();

        model2StringMap.put(
                builderTest.example1,
                "SUSER_NAME(1)");
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