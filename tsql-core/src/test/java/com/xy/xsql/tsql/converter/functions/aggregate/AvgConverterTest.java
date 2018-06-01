package com.xy.xsql.tsql.converter.functions.aggregate;

import com.xy.xsql.block.core.printer.MetaBlockPrinter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.converter.queries.AtTimeConverter;
import org.junit.Assert;
import org.junit.Test;

import java.io.StringWriter;

import static org.junit.Assert.*;

/**
 * Created by xiaoyao9184 on 2018/5/30.
 */
public class AvgConverterTest {

    @Test
    public void testMetaPrint() throws Exception {
        BlockMeta b = AvgConverter.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<Avg> ::=\n" +
                        "AVG ( [ ALL | DISTINCT ] expression )\n" +
                        "\tOVER ( [ partition_by_clause ] order_by_clause )");
    }
}