package com.xy.xsql.tsql.converter.functions.cryptographic;

import com.xy.xsql.block.core.printer.MetaBlockPrinter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.builder.chain.functions.cryptographic.DecryptByKeyFunctionTest;
import com.xy.xsql.tsql.converter.BaseTest;
import com.xy.xsql.tsql.model.functions.cryptographic.DecryptByKey;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.StringWriter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by xiaoyao9184 on 2018/5/31.
 */
public class DecryptByKeyConverterTest {

    @Test
    public void testMetaPrint() throws Exception {
        BlockMeta b = DecryptByKeyConverter.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<DecryptByKey> ::=\n" +
                        "DecryptByKey ( { 'ciphertext' | @ciphertext } [ , add_authenticator , { authenticator | @authenticator } ] )");
    }

    private Map<DecryptByKey,String> model2StringMap;

    @Before
    public void init(){
        DecryptByKeyFunctionTest builderTest = new DecryptByKeyFunctionTest();
        model2StringMap = new LinkedHashMap<>();

        model2StringMap.put(
                builderTest.exampleA,
                "DecryptByKey(EncryptedNationalID)");
        model2StringMap.put(
                builderTest.exampleB,
                "DecryptByKey(CardNumber_Encrypted, 1 ,\n" +
                        "     HashBytes('SHA1', CONVERT(varbinary, CreditCardID)))");
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