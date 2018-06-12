package com.xy.xsql.tsql.converter.functions.security;

import com.xy.xsql.block.core.printer.MetaBlockPrinter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.builder.chain.functions.security.CertPrivateKeyFunctionTest;
import com.xy.xsql.tsql.converter.BaseTest;
import com.xy.xsql.tsql.model.functions.security.CertPrivateKey;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.StringWriter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by xiaoyao9184 on 2018/6/11.
 */
public class CertPrivateKeyConverterTest {

    @Test
    public void testMetaPrint() throws Exception {
        BlockMeta b = CertPrivateKeyConverter.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<CERTPRIVATEKEY> ::=\n" +
                        "CERTPRIVATEKEY ( cert_ID , ' encryption_password ' [ , ' decryption_password ' ] )");
    }

    private Map<CertPrivateKey,String> model2StringMap;

    @Before
    public void init(){
        CertPrivateKeyFunctionTest builderTest = new CertPrivateKeyFunctionTest();
        model2StringMap = new LinkedHashMap<>();

        model2StringMap.put(
                builderTest.example1,
                "CERTPRIVATEKEY(CERT_ID('Shipping04'), 'jklalkaa/; uia3dd')");
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