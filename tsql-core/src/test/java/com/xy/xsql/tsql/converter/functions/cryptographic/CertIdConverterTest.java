package com.xy.xsql.tsql.converter.functions.cryptographic;

import com.xy.xsql.block.core.printer.MetaBlockPrinter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.builder.chain.functions.cryptographic.CertIdFunctionTest;
import com.xy.xsql.tsql.converter.BaseTest;
import com.xy.xsql.tsql.model.functions.cryptographic.Cert_Id;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.StringWriter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by xiaoyao9184 on 2018/5/31.
 */
public class CertIdConverterTest {

    @Test
    public void testMetaPrint() throws Exception {
        BlockMeta b = CertIdConverter.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<Cert_ID> ::=\n" +
                        "Cert_ID ( 'cert_name' )");
    }

    private Map<Cert_Id,String> model2StringMap;

    @Before
    public void init(){
        CertIdFunctionTest builderTest = new CertIdFunctionTest();
        model2StringMap = new LinkedHashMap<>();

        model2StringMap.put(
                builderTest.example1,
                "Cert_ID('ABerglundCert3')");
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