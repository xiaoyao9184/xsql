package com.xy.xsql.block.tsql.core.element.column;

import com.xy.xsql.block.core.printer.ModelMetaBlockPrinter;
import com.xy.xsql.block.model.BlockMeta;
import org.junit.Assert;
import org.junit.Test;

import java.io.StringWriter;

/**
 * Created by xiaoyao9184 on 2017/9/4.
 */
public class ComputedColumnDefinitionConvertersTest {


    @Test
    public void testMetaPrint() throws Exception {
        BlockMeta b = ComputedColumnDefinitionConverter.meta;

        StringWriter writer = new ModelMetaBlockPrinter()
                .printMeta(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<computed_column_definition> ::=\n" +
                        "column_name AS computed_column_expression\n" +
                        "[ PERSISTED [ NOT NULL ] ]\n" +
                        "[\n" +
                        "\t[ CONSTRAINT constraint_name ]\n" +
                        "\t{\n" +
                        "\t\t{ PRIMARY KEY | UNIQUE } \n" +
                        "\t\t\t[ CLUSTERED | NONCLUSTERED ]\n" +
                        "\t\t\t[\n" +
                        "\t\t\t\tWITH FILLFACTOR = fillfactor\n" +
                        "\t\t\t\t| WITH ( index_option [,...n] )\n" +
                        "\t\t\t]\n" +
                        "\t\t\tON { partition_scheme_name ( partition_column_name )\n" +
                        "\t\t\t\t| filegroup_name\n" +
                        "\t\t\t\t| \"default\" }\n" +
                        "\t\t | [ FOREIGN KEY ] \n" +
                        "\t\t\tREFERENCES [ schema_name . ] referenced_table_name [ ( ref_column ) ]\n" +
                        "\t\t\t[ ON DELETE [ NO ACTION | CASCADE | SET NULL | SET DEFAULT ] ]\n" +
                        "\t\t\t[ ON DELETE [ NO ACTION | CASCADE | SET NULL | SET DEFAULT ] ]\n" +
                        "\t\t\t[ NOT FOR REPLICATION ]\n" +
                        "\t\t | CHECK [ NOT FOR REPLICATION ] ( logical_expression )\n" +
                        "\t}\n" +
                        "]");
    }
}