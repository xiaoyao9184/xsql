package com.xy.xsql.tsql.converter.functions.security;

import com.xy.xsql.block.core.printer.MetaBlockPrinter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.builder.chain.functions.security.SUserIdFunctionTest;
import com.xy.xsql.tsql.converter.BaseTest;
import com.xy.xsql.tsql.model.functions.security.SUser_Id;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.StringWriter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by xiaoyao9184 on 2018/6/11.
 */
public class SUserIdConverterTest {

    @Test
    public void testMetaPrint() throws Exception {
        BlockMeta b = SUserIdConverter.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<SUSER_ID> ::=\n" +
                        "SUSER_ID ( [ 'login' ] )");
    }

    private Map<SUser_Id,String> model2StringMap;

    @Before
    public void init(){
        SUserIdFunctionTest builderTest = new SUserIdFunctionTest();
        model2StringMap = new LinkedHashMap<>();

        model2StringMap.put(
                builderTest.example1,
                "SUSER_ID('sa')");
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