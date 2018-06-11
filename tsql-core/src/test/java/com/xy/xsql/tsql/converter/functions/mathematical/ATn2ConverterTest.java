package com.xy.xsql.tsql.converter.functions.mathematical;

import com.xy.xsql.block.core.printer.MetaBlockPrinter;
import com.xy.xsql.block.model.BlockMeta;
import org.junit.Assert;
import org.junit.Test;

import java.io.StringWriter;

/**
 * Created by xiaoyao9184 on 2018/5/31.
 */
public class ATn2ConverterTest {

    @Test
    public void testMetaPrint() throws Exception {
        BlockMeta b = ATn2Converter.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<ATN2> ::=\n" +
                        "ATN2 ( float_expression , float_expression2 )");
    }

}