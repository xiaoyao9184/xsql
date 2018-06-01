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
public class PercentileContConverterTest {

    @Test
    public void testMetaPrint() throws Exception {
        BlockMeta b = PercentileContConverter.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<PERCENTILE_CONT> ::=\n" +
                        "PERCENTILE_CONT ( numeric_literal )\n" +
                        "\tWITHIN GROUP ( ORDER BY order_by_expression [ ASC | DESC ] )\n" +
                        "\tOVER ( [ partition_by_clause ] )");
    }

}