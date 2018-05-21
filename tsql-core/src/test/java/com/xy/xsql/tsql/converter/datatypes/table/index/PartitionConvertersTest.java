package com.xy.xsql.tsql.converter.datatypes.table.index;

import com.xy.xsql.block.core.printer.MetaBlockPrinter;
import com.xy.xsql.block.model.BlockMeta;
import org.junit.Assert;
import org.junit.Test;

import java.io.StringWriter;

/**
 * Created by xiaoyao9184 on 2017/9/6.
 */
public class PartitionConvertersTest {


    @Test
    public void testMetaPrint_PartitionColumnFilegroupDefaultKey() throws Exception {
        BlockMeta b = PartitionConverters.PartitionColumnFilegroupDefaultKey.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "{ partition_scheme_name ( column_name )\n" +
                        "| filegroup_name\n" +
                        "| default }");
    }

    @Test
    public void testMetaPrint_FilegroupDefaultKey() throws Exception {
        BlockMeta b = PartitionConverters.FilegroupDefaultKey.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "{ filegroup_name | default }");
    }

    @Test
    public void testMetaPrint_PartitionColumnFilegroupDefault() throws Exception {
        BlockMeta b = PartitionConverters.PartitionColumnFilegroupDefault.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "{ partition_scheme_name ( partition_column_name )\n" +
                        "| filegroup_name\n" +
                        "| \"default\" }");
    }

    @Test
    public void testMetaPrint_PartitionFilegroupDefault() throws Exception {
        BlockMeta b = PartitionConverters.PartitionFilegroupDefault.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "{ partition_scheme_name\n" +
                        "| filegroup_name\n" +
                        "| \"default\" }");
    }

    @Test
    public void testMetaPrint_FilegroupDefault() throws Exception {
        BlockMeta b = PartitionConverters.FilegroupDefault.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "{ filegroup | \"default\" }");
    }

    @Test
    public void testMetaPrint_FileStreamGroupNull() throws Exception {
        BlockMeta b = PartitionConverters.FileStreamGroupNull.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "{ filestream_filegroup_name | partition_scheme_name | \"NULL\" }");
    }

}