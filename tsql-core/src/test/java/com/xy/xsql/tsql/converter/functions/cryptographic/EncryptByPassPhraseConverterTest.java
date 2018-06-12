package com.xy.xsql.tsql.converter.functions.cryptographic;

import com.xy.xsql.block.core.printer.MetaBlockPrinter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.builder.chain.functions.cryptographic.EncryptByPassPhraseFunctionTest;
import com.xy.xsql.tsql.converter.BaseTest;
import com.xy.xsql.tsql.model.functions.cryptographic.EncryptByPassPhrase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.StringWriter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by xiaoyao9184 on 2018/5/31.
 */
public class EncryptByPassPhraseConverterTest {

    @Test
    public void testMetaPrint() throws Exception {
        BlockMeta b = EncryptByPassPhraseConverter.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<EncryptByPassPhrase> ::=\n" +
                        "EncryptByPassPhrase ( { 'passphrase' | @passphrase } , { 'cleartext' | @cleartext } [ , { add_authenticator | @add_authenticator  } , { authenticator | @authenticator } ] )");
    }

    private Map<EncryptByPassPhrase,String> model2StringMap;

    @Before
    public void init(){
        EncryptByPassPhraseFunctionTest builderTest = new EncryptByPassPhraseFunctionTest();
        model2StringMap = new LinkedHashMap<>();

        model2StringMap.put(
                builderTest.example1,
                "EncryptByPassPhrase(@PassphraseEnteredByUser\n" +
                        "     , CardNumber, 1, CONVERT( varbinary, CreditCardID))");
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