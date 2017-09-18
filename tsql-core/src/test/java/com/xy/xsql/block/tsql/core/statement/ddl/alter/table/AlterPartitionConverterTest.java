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
public class AlterPartitionConverterTest {

    @Test
    public void testMetaPrint() throws Exception {
        BlockMeta b = AlterPartitionConverter.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "SWITCH\n" +
                        "[ PARTITION source_partition_number_expression ]\n" +
                        "\tTO target_table\n" +
                        "\t[ PARTITION target_partition_number_expression ]\n" +
                        "\t[ WITH ( <low_lock_priority_wait> ) ]");
    }

}