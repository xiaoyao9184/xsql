package com.xy.xsql.block.tsql.core.element.column;

import com.xy.xsql.block.core.printer.MetaBlockPrinter;
import com.xy.xsql.block.model.BlockMeta;
import org.junit.Assert;
import org.junit.Test;

import java.io.StringWriter;

/**
 * Created by xiaoyao9184 on 2017/9/4.
 */
public class ColumnConstraintConverterTest {


    @Test
    public void testMetaPrint_DiskBased() throws Exception {
        BlockMeta b = ColumnConstraintConverters.DiskBased.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<column_constraint> ::=\n" +
                        "[ CONSTRAINT constraint_name ]\n" +
                        "{\n" +
                        "\t{ PRIMARY KEY | UNIQUE } \n" +
                        "\t\t[ CLUSTERED | NONCLUSTERED ]\n" +
                        "\t\t[\n" +
                        "\t\t\tWITH FILLFACTOR = fillfactor\n" +
                        "\t\t\t| WITH ( index_option [,...n] )\n" +
                        "\t\t]\n" +
                        "\t\t[ ON { partition_scheme_name ( partition_column_name )\n" +
                        "\t\t\t| filegroup_name\n" +
                        "\t\t\t| \"default\" } ]\n" +
                        "\t | [ FOREIGN KEY ] \n" +
                        "\t\tREFERENCES [ schema_name . ] referenced_table_name [ ( ref_column ) ]\n" +
                        "\t\t[ ON DELETE [ NO ACTION | CASCADE | SET NULL | SET DEFAULT ] ]\n" +
                        "\t\t[ ON DELETE [ NO ACTION | CASCADE | SET NULL | SET DEFAULT ] ]\n" +
                        "\t\t[ NOT FOR REPLICATION ]\n" +
                        "\t | CHECK [ NOT FOR REPLICATION ] ( logical_expression )\n" +
                        "}");
    }

    @Test
    public void testMetaPrint_MemoryOptimized() throws Exception {
        BlockMeta b = ColumnConstraintConverters.MemoryOptimized.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<column_constraint> ::=\n" +
                        "[ CONSTRAINT constraint_name ]\n" +
                        "{\n" +
                        "\t{ PRIMARY KEY | UNIQUE }\n" +
                        "\t\t{\n" +
                        "\t\t\tNONCLUSTERED\n" +
                        "\t\t\t| NONCLUSTERED HASH WITH ( BUCKET_COUNT = bucket_count )\n" +
                        "\t\t}\n" +
                        "\t| [ FOREIGN KEY ]\n" +
                        "\t\tREFERENCES [ schema_name . ] referenced_table_name [ ( ref_column ) ]\n" +
                        "\t| CHECK ( logical_expression )\n" +
                        "}");
    }

}