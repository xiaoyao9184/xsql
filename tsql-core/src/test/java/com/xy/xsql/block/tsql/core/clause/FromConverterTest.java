package com.xy.xsql.block.tsql.core.clause;

import com.xy.xsql.block.core.ReferenceBlockPrinter;
import com.xy.xsql.block.model.ReferenceBlock;
import org.junit.Assert;
import org.junit.Test;

import java.io.StringWriter;

/**
 * Created by xiaoyao9184 on 2017/6/20.
 */
public class FromConverterTest {

    @Test
    public void test() throws Exception {
        ReferenceBlock b = FromConverter.meta();

        StringWriter writer = new ReferenceBlockPrinter()
                .print(b);

        System.out.print(writer);
        Assert.assertEquals(writer.toString(),
                "<FROM> ::=\n" +
                        "FROM { <table_source> } [,...n]");
    }

    @Test
    public void testTableSource() throws Exception {
        ReferenceBlock b = FromConverter.TableSourceConverter.meta();

        StringWriter writer = new ReferenceBlockPrinter()
                .print(b);

        System.out.print(writer);
        Assert.assertEquals(writer.toString(),
                "<table_source> ::=\n" +
                        "table_or_view_name [ [ AS ] table_alias ]\n" +
                        "| derived_table [ [ AS ] table_alias ] [ ( column_alias [,...n] ) ]\n" +
                        "| <joined_table>\n" +
                        "| @variable [ [ AS ] table_alias ]");
    }

    @Test
    public void testBaseTable() throws Exception {
        ReferenceBlock b = FromConverter.BaseTableConverter.meta();

        StringWriter writer = new ReferenceBlockPrinter()
                .print(b);

        System.out.print(writer);
        Assert.assertEquals(writer.toString(),
                "table_or_view_name [ [ AS ] table_alias ]");
    }

    @Test
    public void testDerivedTable() throws Exception {
        ReferenceBlock b = FromConverter.DerivedTableConverter.meta();

        StringWriter writer = new ReferenceBlockPrinter()
                .print(b);

        System.out.print(writer);
        Assert.assertEquals(writer.toString(),
                "derived_table [ [ AS ] table_alias ] [ ( column_alias [,...n] ) ]");
    }

    @Test
    public void testJoinedTable() throws Exception {
        ReferenceBlock b = FromConverter.JoinedTableConverter.meta();

        StringWriter writer = new ReferenceBlockPrinter()
                .print(b);

        System.out.print(writer);
        Assert.assertEquals(writer.toString(),
                "<joined_table> ::=\n" +
                        "<table_source> <join_type> <table_source> ON <search_condition>\n" +
                        "| <table_source> CROSS JOIN <table_source>\n" +
                        "| left_table_source { CROSS | OUTER } APPLY right_table_source\n" +
                        "| [ ( ] <joined_table> [ ) ]");
    }

    @Test
    public void testVariableTable() throws Exception {
        ReferenceBlock b = FromConverter.VariableTableConverter.meta();

        StringWriter writer = new ReferenceBlockPrinter()
                .print(b);

        System.out.print(writer);
        Assert.assertEquals(writer.toString(),
                "@variable [ [ AS ] table_alias ]");
    }


    @Test
    public void testJoinType() throws Exception {
        ReferenceBlock b = FromConverter.JoinTypeConverter.meta();

        StringWriter writer = new ReferenceBlockPrinter()
                .print(b);

        System.out.print(writer);
        Assert.assertEquals(writer.toString(),
                "[ { INNER | { { LEFT | RIGHT | FULL } [ OUTER ] } } [ <join_hint> ] ]\n" +
                        "JOIN");
    }

}