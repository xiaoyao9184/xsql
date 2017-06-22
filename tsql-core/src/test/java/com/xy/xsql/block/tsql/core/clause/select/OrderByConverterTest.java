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
public class OrderByConverterTest {

    @Test
    public void test() throws Exception {
        ReferenceBlock b = OrderByConverter.meta();

        StringWriter writer = new ReferenceBlockPrinter()
                .print(b);

        System.out.print(writer);
        Assert.assertEquals(writer.toString(),
                "ORDER BY \n" +
                        "order_by_expression [ ASC | DESC ] [,...n] \n" +
                        "[ <offset_fetch> ]");
    }

    @Test
    public void testOffsetFetch() throws Exception {
        ReferenceBlock b = OrderByConverter.OffsetFetchConverter.meta();

        StringWriter writer = new ReferenceBlockPrinter()
                .print(b);

        System.out.print(writer);
        Assert.assertEquals(writer.toString(),
                "<offset_fetch> ::=\n" +
                        "OFFSET { integer_constant | offset_row_count_expression } { ROW | ROWS } \n" +
                        "[\n" +
                        "FETCH { FIRST | NEXT } { integer_constant | fetch_row_count_expression } { ROW | ROWS } ONLY\n" +
                        "]");
    }

}