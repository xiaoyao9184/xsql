package com.xy.xsql.tsql.converter.functions.cryptographic;

import com.xy.xsql.block.core.printer.MetaBlockPrinter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.builder.chain.functions.cryptographic.VerifySignedByCertFunctionTest;
import com.xy.xsql.tsql.converter.BaseTest;
import com.xy.xsql.tsql.model.functions.cryptographic.VerifySignedByCert;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.StringWriter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by xiaoyao9184 on 2018/5/31.
 */
public class VerifySignedByCertConverterTest {

    @Test
    public void testMetaPrint() throws Exception {
        BlockMeta b = VerifySignedByCertConverter.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<VerifySignedByCert> ::=\n" +
                        "VerifySignedByCert ( Cert_ID , signed_data , signed_data )");
    }

    private Map<VerifySignedByCert,String> model2StringMap;

    @Before
    public void init(){
        VerifySignedByCertFunctionTest builderTest = new VerifySignedByCertFunctionTest();
        model2StringMap = new LinkedHashMap<>();

        model2StringMap.put(
                builderTest.exampleA,
                "VerifySignedByCert( Cert_Id( 'Shipping04' ),\n" +
                        "     Signed_Data, DataSignature )");
        model2StringMap.put(
                builderTest.exampleB,
                "VerifySignedByCert( Cert_Id( 'Shipping04' ), Data,\n" +
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