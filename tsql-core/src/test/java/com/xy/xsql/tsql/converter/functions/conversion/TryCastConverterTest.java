package com.xy.xsql.tsql.converter.functions.conversion;

import com.xy.xsql.block.core.printer.MetaBlockPrinter;
import com.xy.xsql.block.model.BlockMeta;
import org.junit.Assert;
import org.junit.Test;

import java.io.StringWriter;

import static org.junit.Assert.*;

/**
 * Created by xiaoyao9184 on 2018/5/31.
 */
public class TryCastConverterTest {

    @Test
    public void testMetaPrint() throws Exception {
        BlockMeta b = TryCastConverter.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<TRY_CAST> ::=\n" +
                        "TRY_CAST ( expression AS data_type [ ( length ) ] )");
    }

}