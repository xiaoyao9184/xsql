package com.xy.xsql.block.tsql.core.clause;

import com.xy.xsql.block.core.ReferenceBlockPrinter;
import com.xy.xsql.block.model.ReferenceBlock;
import org.junit.Assert;
import org.junit.Test;

import java.io.StringWriter;

/**
 * Created by xiaoyao9184 on 2017/6/20.
 */
public class OutputConverterTest {

    @Test
    public void test() throws Exception {
        ReferenceBlock b = OutputConverter.meta();

        StringWriter writer = new ReferenceBlockPrinter()
                .print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<OUTPUT_CLAUSE> ::=\n" +
                        "[ OUTPUT <dml_select_list> INTO { @table_variable | output_table } [ ( column_list ) ] ]\n" +
                        "[ OUTPUT <dml_select_list> ]");
    }

    @Test
    public void testDmlSelectList() throws Exception {
        ReferenceBlock b = OutputConverter.DmlSelectListConverter.meta();

        StringWriter writer = new ReferenceBlockPrinter()
                .print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<dml_select_list> ::=\n" +
                        "{ <column_name> | scalar_expression } [ [ AS ] column_alias_identifier ] [,...n]");
    }

    @Test
    public void testDmlSelect() throws Exception {
        ReferenceBlock b = OutputConverter.DmlSelectConverter.meta();

        StringWriter writer = new ReferenceBlockPrinter()
                .print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "{ <column_name> | scalar_expression } [ [ AS ] column_alias_identifier ]");
    }

    @Test
    public void testColumnName() throws Exception {
        ReferenceBlock b = OutputConverter.ColumnNameConverter.meta();

        StringWriter writer = new ReferenceBlockPrinter()
                .print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<column_name> ::=\n" +
                        "{ { DELETED | INSERTED | from_table_name } } . { * | column_name }\n" +
                        "| $action");
    }

}