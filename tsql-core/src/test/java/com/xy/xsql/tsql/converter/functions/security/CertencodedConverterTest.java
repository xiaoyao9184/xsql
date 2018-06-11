package com.xy.xsql.tsql.converter.functions.security;

import com.xy.xsql.block.core.printer.MetaBlockPrinter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.converter.functions.trigger.ColumnsUpdatedConverter;
import org.junit.Assert;
import org.junit.Test;

import java.io.StringWriter;

import static org.junit.Assert.*;

/**
 * Created by xiaoyao9184 on 2018/6/11.
 */
public class CertencodedConverterTest {

    @Test
    public void testMetaPrint() throws Exception {
        BlockMeta b = CertencodedConverter.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<CERTENCODED> ::=\n" +
                        "CERTENCODED ( cert_id )");
    }

}