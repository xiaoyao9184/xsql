package com.xy.xsql.tsql.converter.functions.metadata;

import com.xy.xsql.block.core.printer.MetaBlockPrinter;
import com.xy.xsql.block.model.BlockMeta;
import org.junit.Assert;
import org.junit.Test;

import java.io.StringWriter;

/**
 * Created by xiaoyao9184 on 2018/6/1.
 */
public class ProcIdConverterTest {

    @Test
    public void testMetaPrint() throws Exception {
        BlockMeta b = ProcIdConverter.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<@@PROCID> ::=\n" +
                        "@@PROCID");
    }

}