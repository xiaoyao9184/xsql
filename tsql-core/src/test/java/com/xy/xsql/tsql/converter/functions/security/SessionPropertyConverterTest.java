package com.xy.xsql.tsql.converter.functions.security;

import com.xy.xsql.block.core.printer.MetaBlockPrinter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.builder.chain.functions.security.SessionPropertyFunctionTest;
import com.xy.xsql.tsql.converter.BaseTest;
import com.xy.xsql.tsql.model.functions.security.SessionProperty;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.StringWriter;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by xiaoyao9184 on 2018/6/11.
 */
public class SessionPropertyConverterTest {

    @Test
    public void testMetaPrint() throws Exception {
        BlockMeta b = SessionPropertyConverter.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<SESSIONPROPERTY> ::=\n" +
                        "SESSIONPROPERTY ( option )");
    }

    private Map<SessionProperty,String> model2StringMap;

    @Before
    public void init(){
        SessionPropertyFunctionTest builderTest = new SessionPropertyFunctionTest();
        model2StringMap = new LinkedHashMap<>();

        model2StringMap.put(
                builderTest.example1,
                "SESSIONPROPERTY ('CONCAT_NULL_YIELDS_NULL')");
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