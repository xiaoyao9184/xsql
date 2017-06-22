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
public class SelectConverterTest {

    @Test
    public void test() throws Exception {
        ReferenceBlock b = SelectConverter.meta();

        StringWriter writer = new ReferenceBlockPrinter()
                .print(b);

        System.out.print(writer);
        Assert.assertEquals(writer.toString(),
                "<SELECT Clause> ::=\n" +
                        "SELECT [ ALL | DISTINCT ] \n" +
                        "[ TOP ( expression ) [ PERCENT ] [ WITH TIES ] ] \n" +
                        "<select_list>");
    }

    @Test
    public void testSelectList() throws Exception {
        ReferenceBlock b = SelectConverter.SelectListConverter.meta();

        StringWriter writer = new ReferenceBlockPrinter()
                .print(b);

        System.out.print(writer);
        Assert.assertEquals(writer.toString(),
                "<select_list> ::=\n" +
                        "{\n" +
                        "*\n" +
                        "| { table_name | view_name | table_alias }.* \n" +
                        "| {\n" +
                        "[ { table_name | view_name | table_alias }. ] { column_name | $IDENTITY | $ROWGUID }\n" +
                        "| expression\n" +
                        "[ [ AS ] column_alias ]\n" +
                        "}\n" +
                        "| column_alias = expression\n" +
                        "} [,...n]");
    }

}