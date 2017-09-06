package com.xy.xsql.block.tsql.core.element.table;

import com.xy.xsql.block.core.printer.ModelMetaBlockPrinter;
import com.xy.xsql.block.model.BlockMeta;
import org.junit.Assert;
import org.junit.Test;

import java.io.StringWriter;

/**
 * Created by xiaoyao9184 on 2017/9/6.
 */
public class TableOptionConvertersTest {


    @Test
    public void testMetaPrint_DiskBased() throws Exception {
        BlockMeta b = TableOptionConverters.DiskBased.meta;

        StringWriter writer = new ModelMetaBlockPrinter()
                .printMeta(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<table_option> ::=\n" +
                        "{\n" +
                        "\tDATA_COMPRESSION = { NONE | ROW | PAGE }\n" +
                        "\t\t[ { <partition_number_expression> | <range> } [,...n] ]\n" +
                        "\t| FILETABLE_DIRECTORY = <directory_name>\n" +
                        "\t| FILETABLE_COLLATE_FILENAME = <collation_name> | database_default\n" +
                        "\t| FILETABLE_PRIMARY_KEY_CONSTRAINT_NAME = <collation_name>\n" +
                        "\t| FILETABLE_STREAMID_UNIQUE_CONSTRAINT_NAME = <collation_name>\n" +
                        "\t| FILETABLE_FULLPATH_UNIQUE_CONSTRAINT_NAME = <collation_name>\n" +
                        "\t| SYSTEM_VERSIONING = ON [ ( HISTORY_TABLE = schema_name . history_table_name\n" +
                        "\t\t[ , DATA_CONSISTENCY_CHECK = ON | OFF ] ]\n" +
                        "\t| REMOTE_DATA_ARCHIVE =\n" +
                        "\t\t{\n" +
                        "\t\t\tON [ ( <table_stretch_options> [,...n] ) ]\n" +
                        "\t\t\t| OFF ( MIGRATION_STATE = PAUSED )\n" +
                        "\t\t}\n" +
                        "}");
    }

    @Test
    public void testMetaPrint_MemoryOptimized() throws Exception {
        BlockMeta b = TableOptionConverters.MemoryOptimized.meta;

        StringWriter writer = new ModelMetaBlockPrinter()
                .printMeta(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<table_option> ::=\n" +
                        "{\n" +
                        "\tMEMORY_OPTIMIZED = ON\n" +
                        "\t| DURABILITY = { SCHEMA_ONLY | SCHEMA_AND_DATA }\n" +
                        "\t| SYSTEM_VERSIONING = ON [ ( HISTORY_TABLE = schema_name . history_table_name\n" +
                        "\t\t[ , DATA_CONSISTENCY_CHECK = ON | OFF ] ]\n" +
                        "}");
    }

}