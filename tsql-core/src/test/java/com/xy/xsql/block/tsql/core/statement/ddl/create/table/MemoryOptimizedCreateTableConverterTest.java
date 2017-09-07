package com.xy.xsql.block.tsql.core.statement.ddl.create.table;

import com.xy.xsql.block.core.printer.MetaBlockPrinter;
import com.xy.xsql.block.model.BlockMeta;
import org.junit.Assert;
import org.junit.Test;

import java.io.StringWriter;

/**
 * Created by xiaoyao9184 on 2017/9/6.
 */
public class MemoryOptimizedCreateTableConverterTest {

    @Test
    public void testMetaPrint() throws Exception {
        BlockMeta b = MemoryOptimizedCreateTableConverter.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "CREATE TABLE\n" +
                        "\t[ server_name . ] [ database_name . [ schema_name ] . | schema_name . ] table_name\n" +
                        "\t( \n" +
                        "\t\t{ <column_definition>\n" +
                        "\t\t| <table_constraint>\n" +
                        "\t\t| <table_index> } [,...n]\n" +
                        "\t\t[ PERIOD FOR SYSTEM_TIME ( system_start_time_column_name , system_end_time_column_name ) ]\n" +
                        "\t )\n" +
                        "\t[ WITH ( <table_option> [,...n] ) ]");
    }


    @Test
    public void testMetaPrint_Item() throws Exception {
        BlockMeta b = MemoryOptimizedCreateTableConverter.ItemConverter.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<column_definition>\n" +
                        "| <table_constraint>\n" +
                        "| <table_index>");
    }
}