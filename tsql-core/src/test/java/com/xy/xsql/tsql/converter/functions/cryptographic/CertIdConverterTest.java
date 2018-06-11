package com.xy.xsql.tsql.converter.functions.cryptographic;

import com.xy.xsql.block.core.printer.MetaBlockPrinter;
import com.xy.xsql.block.model.BlockMeta;
import org.junit.Assert;
import org.junit.Test;

import java.io.StringWriter;

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
                        "Cert_Id ( 'cert_name' )");
    }

}