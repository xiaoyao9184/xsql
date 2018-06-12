package com.xy.xsql.tsql.converter.functions.cryptographic;

import com.xy.xsql.block.core.printer.MetaBlockPrinter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.builder.chain.functions.cryptographic.DecryptByKeyAutoAsymKeyFunctionTest;
import com.xy.xsql.tsql.converter.BaseTest;
import com.xy.xsql.tsql.model.functions.cryptographic.DecryptByKeyAutoAsymKey;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.StringWriter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by xiaoyao9184 on 2018/5/31.
 */
public class DecryptByKeyAutoAsymKeyConverterTest {

    @Test
    public void testMetaPrint() throws Exception {
        BlockMeta b = DecryptByKeyAutoAsymKeyConverter.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<DecryptByKeyAutoAsymKey> ::=\n" +
                        "DecryptByKeyAutoAsymKey ( akey_ID , akey_password , { 'ciphertext' | @ciphertext } [ , { add_authenticator | @add_authenticator } [ , { authenticator | @authenticator } ] ] )");
    }

    private Map<DecryptByKeyAutoAsymKey,String> model2StringMap;

    @Before
    public void init(){
        DecryptByKeyAutoAsymKeyFunctionTest builderTest = new DecryptByKeyAutoAsymKeyFunctionTest();
        model2StringMap = new LinkedHashMap<>();

        model2StringMap.put(
                builderTest.example1,
                "DecryptByKeyAutoAsymKey ( AsymKey_ID('SSN_AKey') , NULL ,EncryptedNationalIDNumber2)");
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