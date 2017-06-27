package com.xy.xsql.block.tsql.core.clause;

import com.xy.xsql.block.core.ReferenceBlockPrinter;
import com.xy.xsql.block.model.ReferenceBlock;
import org.junit.Assert;
import org.junit.Test;

import java.io.StringWriter;

/**
 * Created by xiaoyao9184 on 2017/6/20.
 */
public class TableValueConstructorConverterTest {

    @Test
    public void test() throws Exception {
        ReferenceBlock b = TableValueConstructorConverter.meta();

        StringWriter writer = new ReferenceBlockPrinter()
                .print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<Table Value Constructor> ::=\n" +
                        "VALUES ( <row value expression list> ) [,...n]");
    }

    @Test
    public void testRowValueExpressionList() throws Exception {
        ReferenceBlock b = TableValueConstructorConverter.RowValueExpressionListConverter.meta();

        StringWriter writer = new ReferenceBlockPrinter()
                .print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<row value expression list> ::=\n" +
                        "{ <row value expression> } [,...n]");
    }

    @Test
    public void testRowValueExpression() throws Exception {
        ReferenceBlock b = TableValueConstructorConverter.RowValueExpressionConverter.meta();

        StringWriter writer = new ReferenceBlockPrinter()
                .print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<row value expression> ::=\n" +
                        "{ DEFAULT | NULL | expression }");
    }

}