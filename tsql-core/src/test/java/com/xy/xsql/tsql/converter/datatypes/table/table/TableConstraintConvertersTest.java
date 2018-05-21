package com.xy.xsql.tsql.converter.datatypes.table.table;

import com.xy.xsql.block.core.printer.MetaBlockPrinter;
import com.xy.xsql.block.model.BlockMeta;
import org.junit.Assert;
import org.junit.Test;

import java.io.StringWriter;

/**
 * Created by xiaoyao9184 on 2017/9/6.
 */
public class TableConstraintConvertersTest {


    @Test
    public void testMetaPrint_DiskBased() throws Exception {
        BlockMeta b = TableConstraintConverters.DiskBased.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<table_constraint> ::=\n" +
                        "[ CONSTRAINT constraint_name ]\n" +
                        "{\n" +
                        "\t{ PRIMARY KEY | UNIQUE } \n" +
                        "\t\t[ CLUSTERED | NONCLUSTERED ]\n" +
                        "\t\t( column [ ASC | DESC ] [,...n] )\n" +
                        "\t\t[\n" +
                        "\t\t\tWITH FILLFACTOR = fillfactor\n" +
                        "\t\t\t| WITH ( <index_option> [,...n] )\n" +
                        "\t\t]\n" +
                        "\t\t[ ON { partition_scheme_name ( partition_column_name )\n" +
                        "\t\t\t| filegroup_name\n" +
                        "\t\t\t| \"default\" } ]\n" +
                        "\t | FOREIGN KEY \n" +
                        "\t\t( column [,...n] )\n" +
                        "\t\tREFERENCES referenced_table_name [ ( <ref_column> [,...n] ) ]\n" +
                        "\t\t[ ON DELETE [ NO ACTION | CASCADE | SET NULL | SET DEFAULT ] ]\n" +
                        "\t\t[ ON DELETE [ NO ACTION | CASCADE | SET NULL | SET DEFAULT ] ]\n" +
                        "\t\t[ NOT FOR REPLICATION ]\n" +
                        "\t | CHECK [ NOT FOR REPLICATION ] ( logical_expression )\n" +
                        "}");
    }

    @Test
    public void testMetaPrint_MemoryOptimized() throws Exception {
        BlockMeta b = TableConstraintConverters.MemoryOptimized.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<table_constraint> ::=\n" +
                        "[ CONSTRAINT constraint_name ]\n" +
                        "{\n" +
                        "\t{ PRIMARY KEY | UNIQUE }\n" +
                        "\t\t{\n" +
                        "\t\t\tNONCLUSTERED ( column [ ASC | DESC ] [,...n] )\n" +
                        "\t\t\t| NONCLUSTERED HASH ( column [,...n] ) WITH ( BUCKET_COUNT = bucket_count )\n" +
                        "\t\t}\n" +
                        "\t| FOREIGN KEY\n" +
                        "\t\t( column [,...n] )\n" +
                        "\t\tREFERENCES referenced_table_name [ ( <ref_column> [,...n] ) ]\n" +
                        "\t| CHECK ( logical_expression )\n" +
                        "}");
    }

}