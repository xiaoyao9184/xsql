package com.xy.xsql.tsql.converter.functions.cryptographic;

import com.xy.xsql.block.core.printer.MetaBlockPrinter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.builder.chain.functions.cryptographic.DecryptByKeyAutoCertFunctionTest;
import com.xy.xsql.tsql.converter.BaseTest;
import com.xy.xsql.tsql.model.functions.cryptographic.DecryptByKeyAutoCert;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.StringWriter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by xiaoyao9184 on 2018/5/31.
 */
public class DecryptByKeyAutoCertConverterTest {

    @Test
    public void testMetaPrint() throws Exception {
        BlockMeta b = DecryptByKeyAutoCertConverter.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<DecryptByKeyAutoCert> ::=\n" +
                        "DecryptByKeyAutoCert ( cert_ID , cert_password , { 'ciphertext' | @ciphertext } [ , { add_authenticator | @add_authenticator } [ , { authenticator | @authenticator } ] ] )");
    }

    private Map<DecryptByKeyAutoCert,String> model2StringMap;

    @Before
    public void init(){
        DecryptByKeyAutoCertFunctionTest builderTest = new DecryptByKeyAutoCertFunctionTest();
        model2StringMap = new LinkedHashMap<>();

        model2StringMap.put(
                builderTest.example1,
                "DecryptByKeyAutoCert ( cert_ID('HumanResources037') , NULL ,EncryptedNationalIDNumber)");
    }

    @Test
    public void testPrint() throws Exception {
        BaseTest.testPrintIgnoreCase(model2StringMap);
    }

    @Test
    public void testKeywordPrint() throws Exception {
        BaseTest.testKeywordPrint(model2StringMap);
    }

}