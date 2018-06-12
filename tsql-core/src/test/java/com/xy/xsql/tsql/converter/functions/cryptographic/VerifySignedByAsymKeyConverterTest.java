package com.xy.xsql.tsql.converter.functions.cryptographic;

import com.xy.xsql.block.core.printer.MetaBlockPrinter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.builder.chain.functions.cryptographic.VerifySignedByAsymKeyFunctionTest;
import com.xy.xsql.tsql.converter.BaseTest;
import com.xy.xsql.tsql.model.functions.cryptographic.VerifySignedByAsymKey;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.StringWriter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by xiaoyao9184 on 2018/5/31.
 */
public class VerifySignedByAsymKeyConverterTest {

    @Test
    public void testMetaPrint() throws Exception {
        BlockMeta b = VerifySignedByAsymKeyConverter.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<VerifySignedByAsymKey> ::=\n" +
                        "VerifySignedByAsymKey ( Asym_Key_ID , clear_text , signature )");
    }

    private Map<VerifySignedByAsymKey,String> model2StringMap;

    @Before
    public void init(){
        VerifySignedByAsymKeyFunctionTest builderTest = new VerifySignedByAsymKeyFunctionTest();
        model2StringMap = new LinkedHashMap<>();

        model2StringMap.put(
                builderTest.exampleA,
                "VerifySignedByAsymKey( AsymKey_Id( 'WillisKey74' ), SignedData,\n" +
                        "     DataSignature )");
        model2StringMap.put(
                builderTest.exampleB,
                "VerifySignedByAsymKey( AsymKey_Id( 'WillisKey74' ), Data,\n" +
                        "     DataSignature )");
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