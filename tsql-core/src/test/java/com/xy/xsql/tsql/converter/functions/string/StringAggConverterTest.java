package com.xy.xsql.tsql.converter.functions.string;

import com.xy.xsql.block.core.printer.MetaBlockPrinter;
import com.xy.xsql.block.model.BlockMeta;
import org.junit.Assert;
import org.junit.Test;

import java.io.StringWriter;

import static org.junit.Assert.*;

/**
 * Created by xiaoyao9184 on 2018/5/31.
 */
public class StringAggConverterTest {

    @Test
    public void testMetaPrint() throws Exception {
        BlockMeta b = StringAggConverter.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<STRING_AGG> ::=\n" +
                        "STR ( expression , separator ) [ <order_clause> ]");
    }

    @Test
    public void testMetaPrint_OrderBy() throws Exception {
        BlockMeta b = StringAggConverter.metaOrder;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<order_clause> ::=\n" +
                        "WITHIN GROUP ( ORDER BY <order_by_expression_list> [ ASC | DESC ] )");
    }

}