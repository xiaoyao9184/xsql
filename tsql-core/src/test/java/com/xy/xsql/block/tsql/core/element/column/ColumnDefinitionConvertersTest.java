package com.xy.xsql.block.tsql.core.element.column;

import com.xy.xsql.block.core.printer.ModelMetaBlockPrinter;
import com.xy.xsql.block.model.BlockMeta;
import org.junit.Assert;
import org.junit.Test;

import java.io.StringWriter;

/**
 * Created by xiaoyao9184 on 2017/9/4.
 */
public class ColumnDefinitionConvertersTest {


    @Test
    public void testMetaPrint_DiskBased() throws Exception {
        BlockMeta b = ColumnDefinitionConverters.DiskBased.meta;

        StringWriter writer = new ModelMetaBlockPrinter()
                .printMeta(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<column_definition> ::=\n" +
                        "column_name <data_type> \n" +
                        "\t[ FILESTREAM ]\n" +
                        "\t[ COLLATE collation_name ]\n" +
                        "\t[ SPARSE ]\n" +
                        "\t[ MASKED WITH ( FUNCTION = 'mask_function' ) ]\n" +
                        "\t[ CONSTRAINT constraint_name DEFAULT constant_expression ]\n" +
                        "\t[ IDENTITY ( seed , increment ) ]\n" +
                        "\t[ NOT FOR REPLICATION ]\n" +
                        "\t[ GENERATED ALWAYS AS ROW { START | END } [ HIDDEN ] ]\n" +
                        "\t[ NULL | NOT NULL ]\n" +
                        "\t[ ROWGUIDCOL ]\n" +
                        "\t[ ENCRYPTED WITH ( \n" +
                        "\t\tCOLUMN_ENCRYPTION_KEY = key_name ,\n" +
                        "\t\t{ ENCRYPTION_TYPE = DETERMINISTIC | RANDOMIZED , }\n" +
                        "\t\tALGORITHM = 'AEAD_AES_256_CBC_HMAC_SHA_256'\n" +
                        "\t ) ]\n" +
                        "\t[ <column_constraint> ] [,...n]\n" +
                        "\t[ <column_index> ]\n");
    }

    @Test
    public void testMetaPrint_MemoryOptimized() throws Exception {
        BlockMeta b = ColumnDefinitionConverters.MemoryOptimized.meta;

        StringWriter writer = new ModelMetaBlockPrinter()
                .printMeta(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<column_definition> ::=\n" +
                        "column_name <data_type> \n" +
                        "\t[ COLLATE collation_name ]\n" +
                        "\t[ GENERATED ALWAYS AS ROW { START | END } [ HIDDEN ] ]\n" +
                        "\t[ NULL | NOT NULL ]\n" +
                        "\t[\n" +
                        "\t\t[ CONSTRAINT constraint_name ] DEFAULT memory_optimized_constant_expression\n" +
                        "\t\t| IDENTITY ( 1 , 1 )\n" +
                        "\t]\n" +
                        "\t[ <column_constraint> ]\n" +
                        "\t[ <column_index> ]\n");
    }

}