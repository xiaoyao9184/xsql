package com.xy.xsql.block.tsql.core.clause.select;

import com.xy.xsql.block.core.ReferenceBlockPrinter;
import com.xy.xsql.block.model.ReferenceBlock;
import org.junit.Assert;
import org.junit.Test;

import java.io.StringWriter;

import static org.junit.Assert.*;

/**
 * Created by xiaoyao9184 on 2017/6/21.
 */
public class OverConverterTest {

    @Test
    public void test() throws Exception {
        ReferenceBlock b = OverConverter.meta();

        StringWriter writer = new ReferenceBlockPrinter()
                .print(b);

        System.out.print(writer);
        Assert.assertEquals(writer.toString(),
                "<OVER> ::=\n" +
                        "OVER ( \n" +
                        "<PARTITION BY clause>\n" +
                        "<ORDER BY clause>\n" +
                        " )");
    }

    @Test
    public void testPartitionBy() throws Exception {
        ReferenceBlock b = OverConverter.PartitionByConverter.meta();

        StringWriter writer = new ReferenceBlockPrinter()
                .print(b);

        System.out.print(writer);
        Assert.assertEquals(writer.toString(),
                "<PARTITION BY clause> ::=\n" +
                        "PARTITION BY value_expression [,...n]");
    }

    @Test
    public void testOrderBy() throws Exception {
        ReferenceBlock b = OverConverter.OrderByConverter.meta();

        StringWriter writer = new ReferenceBlockPrinter()
                .print(b);

        System.out.print(writer);
        Assert.assertEquals(writer.toString(),
                "<ORDER BY clause> ::=\n" +
                        "ORDER BY order_by_expression [ ASC | DESC ] [,...n]");
    }

}