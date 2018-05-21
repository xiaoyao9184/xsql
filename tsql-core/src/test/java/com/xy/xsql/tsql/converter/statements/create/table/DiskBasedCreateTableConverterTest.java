package com.xy.xsql.tsql.converter.statements.create.table;

import com.xy.xsql.block.core.printer.MetaBlockPrinter;
import com.xy.xsql.block.model.BlockMeta;
import org.junit.Assert;
import org.junit.Test;

import java.io.StringWriter;

/**
 * Created by xiaoyao9184 on 2017/9/6.
 */
public class DiskBasedCreateTableConverterTest {


    @Test
    public void testMetaPrint() throws Exception {
        BlockMeta b = DiskBasedCreateTableConverter.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "CREATE TABLE\n" +
                        "\t[ server_name . ] [ database_name . [ schema_name ] . | schema_name . ] table_name\n" +
                        "\t[ AS FileTable ]\n" +
                        "\t( \n" +
                        "\t\t{ <column_definition>\n" +
                        "\t\t| <computed_column_definition>\n" +
                        "\t\t| <column_set_definition>\n" +
                        "\t\t| <table_constraint>\n" +
                        "\t\t| <table_index> } [,...n]\n" +
                        "\t\t[ PERIOD FOR SYSTEM_TIME ( system_start_time_column_name , system_end_time_column_name ) ]\n" +
                        "\t )\n" +
                        "\t[ ON { partition_scheme_name ( partition_column_name )\n" +
                        "\t\t| filegroup_name\n" +
                        "\t\t| \"default\" } ]\n" +
                        "\t[ TEXTIMAGE_ON { filegroup | \"default\" } ]\n" +
                        "\t[ FILESTREAM_ON { partition_scheme_name ( partition_column_name )\n" +
                        "\t\t| filegroup_name\n" +
                        "\t\t| \"default\" } ]\n" +
                        "\t[ WITH ( <table_option> [,...n] ) ]");
    }


    @Test
    public void testMetaPrint_Item() throws Exception {
        BlockMeta b = DiskBasedCreateTableConverter.ItemConverter.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<column_definition>\n" +
                        "| <computed_column_definition>\n" +
                        "| <column_set_definition>\n" +
                        "| <table_constraint>\n" +
                        "| <table_index>");
    }

}