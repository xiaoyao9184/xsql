package com.xy.xsql.tsql.converter.functions.security;

import com.xy.xsql.block.core.printer.MetaBlockPrinter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.builder.chain.functions.security.LoginPropertyFunctionTest;
import com.xy.xsql.tsql.converter.BaseTest;
import com.xy.xsql.tsql.model.functions.security.LoginProperty;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.StringWriter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by xiaoyao9184 on 2018/6/11.
 */
public class LoginPropertyConverterTest {

    @Test
    public void testMetaPrint() throws Exception {
        BlockMeta b = LoginPropertyConverter.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<LOGINPROPERTY> ::=\n" +
                        "LOGINPROPERTY ( 'login_name' , 'property_name' )");
    }

    private Map<LoginProperty,String> model2StringMap;

    @Before
    public void init(){
        LoginPropertyFunctionTest builderTest = new LoginPropertyFunctionTest();
        model2StringMap = new LinkedHashMap<>();

        model2StringMap.put(
                builderTest.exampleA,
                "LOGINPROPERTY('John3', 'IsMustChange')");
        model2StringMap.put(
                builderTest.exampleB,
                "LOGINPROPERTY('John3', 'IsLocked')");
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