package com.xy.xsql.tsql.converter.functions.security;

import com.xy.xsql.block.core.printer.MetaBlockPrinter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.builder.chain.functions.security.CertEncodedFunctionTest;
import com.xy.xsql.tsql.converter.BaseTest;
import com.xy.xsql.tsql.model.functions.security.CertEncoded;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.StringWriter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by xiaoyao9184 on 2018/6/11.
 */
public class CertEncodedConverterTest {

    @Test
    public void testMetaPrint() throws Exception {
        BlockMeta b = CertEncodedConverter.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<CERTENCODED> ::=\n" +
                        "CERTENCODED ( cert_id )");
    }

    private Map<CertEncoded,String> model2StringMap;

    @Before
    public void init(){
        CertEncodedFunctionTest builderTest = new CertEncodedFunctionTest();
        model2StringMap = new LinkedHashMap<>();

        model2StringMap.put(
                builderTest.exampleA,
                "CERTENCODED(CERT_ID('Shipping04'))");
        model2StringMap.put(
                builderTest.exampleB,
                "CERTENCODED(CERT_ID('SOURCE_CERT'))");
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