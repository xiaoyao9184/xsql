package com.xy.xsql.tsql.converter.datatypes.table.constraint;

import com.xy.xsql.block.core.printer.MetaBlockPrinter;
import com.xy.xsql.block.model.BlockMeta;
import org.junit.Assert;
import org.junit.Test;

import java.io.StringWriter;

/**
 * Created by xiaoyao9184 on 2017/9/6.
 */
public class ForeignConvertersTest {


    @Test
    public void testMetaPrint_DiskBasedColumn() throws Exception {
        BlockMeta b = ForeignConverters.DiskBasedColumn.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "[ FOREIGN KEY ] \n" +
                        "\tREFERENCES [ schema_name . ] referenced_table_name [ ( ref_column ) ]\n" +
                        "\t[ ON DELETE [ NO ACTION | CASCADE | SET NULL | SET DEFAULT ] ]\n" +
                        "\t[ ON DELETE [ NO ACTION | CASCADE | SET NULL | SET DEFAULT ] ]\n" +
                        "\t[ NOT FOR REPLICATION ]\n");
    }

    @Test
    public void testMetaPrint_DiskBasedTable() throws Exception {
        BlockMeta b = ForeignConverters.DiskBasedTable.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "FOREIGN KEY \n" +
                        "\t( column [,...n] )\n" +
                        "\tREFERENCES referenced_table_name [ ( <ref_column> [,...n] ) ]\n" +
                        "\t[ ON DELETE [ NO ACTION | CASCADE | SET NULL | SET DEFAULT ] ]\n" +
                        "\t[ ON DELETE [ NO ACTION | CASCADE | SET NULL | SET DEFAULT ] ]\n" +
                        "\t[ NOT FOR REPLICATION ]\n");
    }
}