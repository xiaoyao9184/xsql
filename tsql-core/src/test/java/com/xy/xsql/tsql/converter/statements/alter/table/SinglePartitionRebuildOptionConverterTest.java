package com.xy.xsql.tsql.converter.statements.alter.table;

import com.xy.xsql.block.core.printer.MetaBlockPrinter;
import com.xy.xsql.block.model.BlockMeta;
import org.junit.Assert;
import org.junit.Test;

import java.io.StringWriter;

import static org.junit.Assert.*;

/**
 * Created by xiaoyao9184 on 2017/9/18.
 */
public class SinglePartitionRebuildOptionConverterTest {

    @Test
    public void testMetaPrint() throws Exception {
        BlockMeta b = SinglePartitionRebuildOptionConverter.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<single_partition_rebuild__option> ::=\n" +
                        "{\n" +
                        "\tSORT_IN_TEMPDB = { ON | OFF }\n" +
                        "\t| MAXDOP = max_degree_of_parallelism\n" +
                        "\t| DATA_COMPRESSION = { NONE | ROW | PAGE | COLUMNSTORE | COLUMNSTORE_ARCHIVE }\n" +
                        "\t| ONLINE = { ON [ ( <low_priority_lock_wait> ) ] | OFF }\n" +
                        "}");
    }

}