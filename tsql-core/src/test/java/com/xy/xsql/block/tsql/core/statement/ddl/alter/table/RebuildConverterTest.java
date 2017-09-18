package com.xy.xsql.block.tsql.core.statement.ddl.alter.table;

import com.xy.xsql.block.core.printer.MetaBlockPrinter;
import com.xy.xsql.block.model.BlockMeta;
import org.junit.Assert;
import org.junit.Test;

import java.io.StringWriter;

import static org.junit.Assert.*;

/**
 * Created by xiaoyao9184 on 2017/9/18
 */
public class RebuildConverterTest {

    @Test
    public void testMetaPrint() throws Exception {
        BlockMeta b = RebuildConverter.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "REBUILD\n" +
                        "[ PARTITION = ALL ] [ WITH ( <rebuild_option> [,...n] ) ]\n" +
                        "| [ PARTITION = partition_number ] [ WITH ( <single_partition_rebuild_option> [,...n] ) ]");
    }

}