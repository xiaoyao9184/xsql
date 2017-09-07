package com.xy.xsql.block.tsql.core.element.constraint;

import com.xy.xsql.block.core.printer.MetaBlockPrinter;
import com.xy.xsql.block.model.BlockMeta;
import org.junit.Assert;
import org.junit.Test;

import java.io.StringWriter;

/**
 * Created by xiaoyao9184 on 2017/9/6.
 */
public class PrimaryUniqueConvertersTest {


    @Test
    public void testMetaPrint_Simple() throws Exception {
        BlockMeta b = PrimaryUniqueConverters.Simple.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "{ PRIMARY KEY | UNIQUE } ( column_name [,...n] )");
    }

    @Test
    public void testMetaPrint_DiskBasedColumn() throws Exception {
        BlockMeta b = PrimaryUniqueConverters.DiskBasedColumn.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "{ PRIMARY KEY | UNIQUE } \n" +
                        "\t[ CLUSTERED | NONCLUSTERED ]\n" +
                        "\t[\n" +
                        "\t\tWITH FILLFACTOR = fillfactor\n" +
                        "\t\t| WITH ( index_option [,...n] )\n" +
                        "\t]\n" +
                        "\tON { partition_scheme_name ( partition_column_name )\n" +
                        "\t\t| filegroup_name\n" +
                        "\t\t| \"default\" }\n");
    }

    @Test
    public void testMetaPrint_DiskBasedTable() throws Exception {
        BlockMeta b = PrimaryUniqueConverters.DiskBasedTable.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "{ PRIMARY KEY | UNIQUE } \n" +
                        "\t[ CLUSTERED | NONCLUSTERED ]\n" +
                        "\t( column [ ASC | DESC ] [,...n] )\n" +
                        "\t[\n" +
                        "\t\tWITH FILLFACTOR = fillfactor\n" +
                        "\t\t| WITH ( <index_option> [,...n] )\n" +
                        "\t]\n" +
                        "\tON { partition_scheme_name ( partition_column_name )\n" +
                        "\t\t| filegroup_name\n" +
                        "\t\t| \"default\" }\n");
    }

    @Test
    public void testMetaPrint_MemoryOptimizedTable() throws Exception {
        BlockMeta b = PrimaryUniqueConverters.MemoryOptimizedTable.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "{ PRIMARY KEY | UNIQUE }\n" +
                        "\t{\n" +
                        "\t\tNONCLUSTERED ( column [ ASC | DESC ] [,...n] )\n" +
                        "\t\t| NONCLUSTERED HASH ( column [,...n] ) WITH ( BUCKET_COUNT = bucket_count )\n" +
                        "\t}");
    }
}