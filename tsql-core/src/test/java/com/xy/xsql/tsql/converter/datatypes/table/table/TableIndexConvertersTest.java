package com.xy.xsql.tsql.converter.datatypes.table.table;

import com.xy.xsql.block.core.printer.MetaBlockPrinter;
import com.xy.xsql.block.model.BlockMeta;
import org.junit.Assert;
import org.junit.Test;

import java.io.StringWriter;

/**
 * Created by xiaoyao9184 on 2017/9/6.
 */
public class TableIndexConvertersTest {


    @Test
    public void testMetaPrint_DiskBased() throws Exception {
        BlockMeta b = TableIndexConverters.DiskBased.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<table_index> ::=\n" +
                        "{\n" +
                        "\tINDEX index_name [ CLUSTERED | NONCLUSTERED ]\n" +
                        "\t\t( column_name [,...n] )\n" +
                        "\t| INDEX index_name CLUSTERED COLUMNSTORE\n" +
                        "\t| INDEX index_name [ NONCLUSTERED ] CLUSTERED ( column_name [,...n] )\n" +
                        "\t[ WITH ( index_option [,...n] ) ]\n" +
                        "\tON { partition_scheme_name ( partition_column_name )\n" +
                        "\t\t| filegroup_name\n" +
                        "\t\t| \"default\" }\n" +
                        "\tON { filestream_filegroup_name | partition_scheme_name | \"NULL\" }\n" +
                        "}");
    }


    @Test
    public void testMetaPrint_MemoryOptimized() throws Exception {
        BlockMeta b = TableIndexConverters.MemoryOptimized.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<table_index> ::=\n" +
                        "INDEX index_name\n" +
                        "{\n" +
                        "\t[ NONCLUSTERED ] HASH ( column_name [,...n] ) WITH ( BUCKET_COUNT = bucket_count )\n" +
                        "\t| [ NONCLUSTERED ] ( column_name [,...n] )\n" +
                        "\t\t[ ON { filegroup_name | default } ]\n" +
                        "\t| CLUSTERED COLUMNSTORE [ WITH ( COMPRESSION_DELAY = 0 | delay [Minutes] ) ]\n" +
                        "\t\t[ ON { filegroup_name | default } ]\n" +
                        "}");
    }

}