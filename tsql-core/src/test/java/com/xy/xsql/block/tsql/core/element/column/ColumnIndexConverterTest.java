package com.xy.xsql.block.tsql.core.element.column;

import com.xy.xsql.block.core.printer.MetaBlockPrinter;
import com.xy.xsql.block.model.BlockMeta;
import org.junit.Assert;
import org.junit.Test;

import java.io.StringWriter;

/**
 * Created by xiaoyao9184 on 2017/9/4.
 */
public class ColumnIndexConverterTest {


    @Test
    public void testMetaPrint_DiskBased() throws Exception {
        BlockMeta b = ColumnIndexConverters.DiskBased.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<column_index> ::=\n" +
                        "INDEX index_name [ CLUSTERED | NONCLUSTERED ] \n" +
                        "\t[ WITH ( index_option [,...n] ) ]\n" +
                        "\tON { partition_scheme_name ( partition_column_name )\n" +
                        "\t\t| filegroup_name\n" +
                        "\t\t| \"default\" }\n" +
                        "\tFILESTREAM_ON { filestream_filegroup_name | partition_scheme_name | \"NULL\" }\n");
    }

    @Test
    public void testMetaPrint_MemoryOptimized() throws Exception {
        BlockMeta b = ColumnIndexConverters.MemoryOptimized.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<column_index> ::=\n" +
                        "INDEX index_name\n" +
                        "{ [ NONCLUSTERED ] | [ NONCLUSTERED ] HASH WITH ( BUCKET_COUNT = bucket_count ) }");
    }

}