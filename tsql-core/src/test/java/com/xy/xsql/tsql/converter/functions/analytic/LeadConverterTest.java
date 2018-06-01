package com.xy.xsql.tsql.converter.functions.analytic;

import com.xy.xsql.block.core.printer.MetaBlockPrinter;
import com.xy.xsql.block.model.BlockMeta;
import org.junit.Assert;
import org.junit.Test;

import java.io.StringWriter;

import static org.junit.Assert.*;

/**
 * Created by xiaoyao9184 on 2018/5/31.
 */
public class LeadConverterTest {

    @Test
    public void testMetaPrint() throws Exception {
        BlockMeta b = LeadConverter.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<LEAD> ::=\n" +
                        "LEAD ( [ scalar_expression ] [ , offset ] [ , default ] )\n" +
                        "\tOVER ( [ partition_by_clause ] order_by_clause )");
    }

}