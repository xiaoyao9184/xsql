package com.xy.xsql.tsql.converter.functions.cryptographic;

import com.xy.xsql.block.core.printer.MetaBlockPrinter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.builder.chain.functions.cryptographic.EncryptByKeyFunctionTest;
import com.xy.xsql.tsql.converter.BaseTest;
import com.xy.xsql.tsql.model.functions.cryptographic.EncryptByKey;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.StringWriter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by xiaoyao9184 on 2018/5/31.
 */
public class EncryptByKeyConverterTest {

    @Test
    public void testMetaPrint() throws Exception {
        BlockMeta b = EncryptByKeyConverter.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<EncryptByKey> ::=\n" +
                        "EncryptByKey ( key_GUID , { 'cleartext' | @cleartext } [ , { add_authenticator | @add_authenticator  } , { authenticator | @authenticator } ] )");
    }

    private Map<EncryptByKey,String> model2StringMap;

    @Before
    public void init(){
        EncryptByKeyFunctionTest builderTest = new EncryptByKeyFunctionTest();
        model2StringMap = new LinkedHashMap<>();

        model2StringMap.put(
                builderTest.exampleA,
                "EncryptByKey(Key_GUID('SSN_Key_01'), NationalIDNumber)");
        model2StringMap.put(
                builderTest.exampleB,
                "EncryptByKey(Key_GUID('CreditCards_Key11'),\n" +
                        "     CardNumber, 1, CONVERT( varbinary, CreditCardID) )");
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