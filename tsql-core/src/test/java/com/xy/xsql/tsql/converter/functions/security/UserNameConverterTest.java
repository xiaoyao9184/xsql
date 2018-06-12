package com.xy.xsql.tsql.converter.functions.security;

import com.xy.xsql.block.core.printer.MetaBlockPrinter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.builder.chain.functions.security.UserNameFunctionTest;
import com.xy.xsql.tsql.converter.BaseTest;
import com.xy.xsql.tsql.model.functions.security.User_Name;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.StringWriter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by xiaoyao9184 on 2018/6/11.
 */
public class UserNameConverterTest {

    @Test
    public void testMetaPrint() throws Exception {
        BlockMeta b = UserNameConverter.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<USER_NAME> ::=\n" +
                        "USER_NAME ( [ id ] )");
    }

    private Map<User_Name,String> model2StringMap;

    @Before
    public void init(){
        UserNameFunctionTest builderTest = new UserNameFunctionTest();
        model2StringMap = new LinkedHashMap<>();

        model2StringMap.put(
                builderTest.exampleA,
                "USER_NAME(13)");
        model2StringMap.put(
                builderTest.exampleB,
                "USER_NAME()");
        model2StringMap.put(
                builderTest.exampleC,
                "USER_NAME(1)");
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