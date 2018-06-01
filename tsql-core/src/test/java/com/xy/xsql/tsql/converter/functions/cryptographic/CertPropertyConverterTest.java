package com.xy.xsql.tsql.converter.functions.cryptographic;

import com.xy.xsql.block.core.printer.MetaBlockPrinter;
import com.xy.xsql.block.model.BlockMeta;
import org.junit.Assert;
import org.junit.Test;

import java.io.StringWriter;

import static org.junit.Assert.*;

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

}