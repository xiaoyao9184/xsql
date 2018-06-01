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
public class PercentRankConverterTest {

    @Test
    public void testMetaPrint() throws Exception {
        BlockMeta b = PercentRankConverter.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<PERCENT_RANK> ::=\n" +
                        "PERCENT_RANK ( )\n" +
                        "\tOVER ( [ partition_by_clause ] order_by_clause )");
    }

}