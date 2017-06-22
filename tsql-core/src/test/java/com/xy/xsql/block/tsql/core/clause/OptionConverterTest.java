package com.xy.xsql.block.tsql.core.clause;

import com.xy.xsql.block.core.ReferenceBlockPrinter;
import com.xy.xsql.block.model.ReferenceBlock;
import org.junit.Assert;
import org.junit.Test;

import java.io.StringWriter;

/**
 * Created by xiaoyao9184 on 2017/6/20.
 */
public class OptionConverterTest {

    @Test
    public void test() throws Exception {
        ReferenceBlock b = OptionConverter.meta();

        StringWriter writer = new ReferenceBlockPrinter()
                .print(b);

        System.out.print(writer);
        Assert.assertEquals(writer.toString(),
                "<OPTION Clause> ::=\n" +
                        "OPTION ( <query_hint> [,...n] )");
    }

    @Test
    public void testOptionQueryOption() throws Exception {
        ReferenceBlock b = OptionConverter.OptionQueryOptionConverter.meta();

        StringWriter writer = new ReferenceBlockPrinter()
                .print(b);

        System.out.print(writer);
        Assert.assertEquals(writer.toString(),
                "<query_option> ::=\n" +
                        "LABEL = label_name\n" +
                        "| <query_hint>");
    }

    @Test
    public void testOptionLabelQueryOption() throws Exception {
        ReferenceBlock b = OptionConverter.OptionLabelQueryOptionConverter.meta();

        StringWriter writer = new ReferenceBlockPrinter()
                .print(b);

        System.out.print(writer);
        Assert.assertEquals(writer.toString(),
                "LABEL = label_name");
    }

}