package com.xy.xsql.tsql.converter.functions.cryptographic;

import com.xy.xsql.block.core.printer.MetaBlockPrinter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.builder.chain.functions.cryptographic.EncryptByAsymKeyFunctionTest;
import com.xy.xsql.tsql.converter.BaseTest;
import com.xy.xsql.tsql.model.functions.cryptographic.EncryptByAsymKey;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.StringWriter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by xiaoyao9184 on 2018/5/31.
 */
public class EncryptByAsymKeyConverterTest {

    @Test
    public void testMetaPrint() throws Exception {
        BlockMeta b = EncryptByAsymKeyConverter.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<EncryptByAsymKey> ::=\n" +
                        "EncryptByAsymKey Asym_Key_ID , { 'plaintext' | @plaintext } )");
    }

    private Map<EncryptByAsymKey,String> model2StringMap;

    @Before
    public void init(){
        EncryptByAsymKeyFunctionTest builderTest = new EncryptByAsymKeyFunctionTest();
        model2StringMap = new LinkedHashMap<>();

        model2StringMap.put(
                builderTest.example1,
                "EncryptByAsymKey(AsymKey_ID('JanainaAsymKey02'), @cleartext)");
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