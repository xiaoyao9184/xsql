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

}