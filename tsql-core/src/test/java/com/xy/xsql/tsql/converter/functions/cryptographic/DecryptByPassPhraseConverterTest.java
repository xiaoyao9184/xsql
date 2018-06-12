package com.xy.xsql.tsql.converter.functions.cryptographic;

import com.xy.xsql.block.core.printer.MetaBlockPrinter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.builder.chain.functions.cryptographic.DecryptByPassPhraseFunctionTest;
import com.xy.xsql.tsql.converter.BaseTest;
import com.xy.xsql.tsql.model.functions.cryptographic.DecryptByPassPhrase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.StringWriter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by xiaoyao9184 on 2018/5/31.
 */
public class DecryptByPassPhraseConverterTest {

    @Test
    public void testMetaPrint() throws Exception {
        BlockMeta b = DecryptByPassPhraseConverter.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<DecryptByPassPhrase> ::=\n" +
                        "DecryptByPassPhrase ( { 'passphrase' | @passphrase } , { 'ciphertext' | @ciphertext } [ , { add_authenticator | @add_authenticator } , { authenticator | @authenticator } ] )");
    }

    private Map<DecryptByPassPhrase,String> model2StringMap;

    @Before
    public void init(){
        DecryptByPassPhraseFunctionTest builderTest = new DecryptByPassPhraseFunctionTest();
        model2StringMap = new LinkedHashMap<>();

        model2StringMap.put(
                builderTest.example1,
                "DecryptByPassphrase(@PassphraseEnteredByUser, CardNumber_EncryptedbyPassphrase, 1\n" +
                        "     , CONVERT(varbinary, CreditCardID))");
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