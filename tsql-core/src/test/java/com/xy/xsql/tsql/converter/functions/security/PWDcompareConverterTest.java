package com.xy.xsql.tsql.converter.functions.security;

import com.xy.xsql.block.core.printer.MetaBlockPrinter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.builder.chain.functions.security.PWDcompareFunctionTest;
import com.xy.xsql.tsql.converter.BaseTest;
import com.xy.xsql.tsql.model.functions.security.PWDcompare;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.StringWriter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by xiaoyao9184 on 2018/6/11.
 */
public class PWDcompareConverterTest {

    @Test
    public void testMetaPrint() throws Exception {
        BlockMeta b = PWDcompareConverter.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<PWDCOMPARE> ::=\n" +
                        "PWDCOMPARE ( 'clear_text_password' , password_hash [ , version ] )");
    }

    private Map<PWDcompare,String> model2StringMap;

    @Before
    public void init(){
        PWDcompareFunctionTest builderTest = new PWDcompareFunctionTest();
        model2StringMap = new LinkedHashMap<>();

        model2StringMap.put(
                builderTest.exampleA,
                "PWDCOMPARE('', password_hash)");
        model2StringMap.put(
                builderTest.exampleB,
                "PWDCOMPARE('password', password_hash)");
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