package com.xy.xsql.tsql.converter.functions.cryptographic;

import com.xy.xsql.block.core.printer.MetaBlockPrinter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.builder.chain.functions.cryptographic.CertPropertyFunctionTest;
import com.xy.xsql.tsql.converter.BaseTest;
import com.xy.xsql.tsql.model.functions.cryptographic.CertProperty;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.StringWriter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by xiaoyao9184 on 2018/5/31.
 */
public class CertPropertyConverterTest {

    @Test
    public void testMetaPrint() throws Exception {
        BlockMeta b = CertPropertyConverter.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<CertProperty> ::=\n" +
                        "CertProperty ( Cert_ID , '<PropertyName>' )");
    }

    @Test
    public void testMetaPrint_() throws Exception {
        BlockMeta b = CertPropertyConverter.PropertiesConverter.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<PropertyName> ::=\n" +
                        "'Expiry_Date' | 'Start_Date' | 'Issuer_Name' | 'Cert_Serial_Number' | 'Subject' | 'SID' | 'String_SID'");
    }

    private Map<CertProperty,String> model2StringMap;

    @Before
    public void init(){
        CertPropertyFunctionTest builderTest = new CertPropertyFunctionTest();
        model2StringMap = new LinkedHashMap<>();

        model2StringMap.put(
                builderTest.example1,
                "CertProperty( Cert_ID('Marketing19'), 'Subject')");
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