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
public class LastValueConverterTest {

    @Test
    public void testMetaPrint() throws Exception {
        BlockMeta b = LastValueConverter.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<LAST_VALUE> ::=\n" +
                        "LAST_VALUE ( [ scalar_expression ] )\n" +
                        "\tOVER ( [ partition_by_clause ] order_by_clause [ rows_range_clause ] )");
    }

}