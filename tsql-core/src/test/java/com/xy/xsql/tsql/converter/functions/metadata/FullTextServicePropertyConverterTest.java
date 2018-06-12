package com.xy.xsql.tsql.converter.functions.metadata;

import com.xy.xsql.block.core.printer.MetaBlockPrinter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.builder.chain.functions.metadata.FullTextServicePropertyFunctionTest;
import com.xy.xsql.tsql.converter.BaseTest;
import com.xy.xsql.tsql.model.functions.metadata.FullTextServiceProperty;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.StringWriter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by xiaoyao9184 on 2018/6/1.
 */
public class FullTextServicePropertyConverterTest {

    @Test
    public void testMetaPrint() throws Exception {
        BlockMeta b = FullTextServicePropertyConverter.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<FULLTEXTSERVICEPROPERTY> ::=\n" +
                        "FULLTEXTSERVICEPROPERTY ( 'property' )");
    }

    private Map<FullTextServiceProperty,String> model2StringMap;

    @Before
    public void init(){
        FullTextServicePropertyFunctionTest builderTest = new FullTextServicePropertyFunctionTest();
        model2StringMap = new LinkedHashMap<>();

        model2StringMap.put(
                builderTest.example1,
                "fulltextserviceproperty('VerifySignature')");
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