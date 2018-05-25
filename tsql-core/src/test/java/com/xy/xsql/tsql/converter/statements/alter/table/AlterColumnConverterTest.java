package com.xy.xsql.tsql.converter.statements.alter.table;

import com.xy.xsql.block.core.printer.MetaBlockPrinter;
import com.xy.xsql.block.model.BlockMeta;
import org.junit.Assert;
import org.junit.Test;

import java.io.StringWriter;

/**
 * Created by xiaoyao9184 on 2017/9/17.
 */
public class AlterColumnConverterTest {

    @Test
    public void testMetaPrint() throws Exception {
        BlockMeta b = AlterColumnConverter.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "ALTER COLUMN column_name\n" +
                        "{\n" +
                        "\t[ <data_type> ]\n" +
//                        "\t[ type_schema_name ] type_name\n" +
//                        "\t\t[ ( \n" +
//                        "\t\t\t{\n" +
//                        "\t\t\t\tprecision [ , scale ]\n" +
//                        "\t\t\t\t| max\n" +
//                        "\t\t\t\t| xml_schema_collection\n" +
//                        "\t\t\t}\n" +
//                        "\t\t ) ]\n" +
                        "\t[ COLLATE collation_name ]\n" +
                        "\t[ NULL | NULL ] [ SPARSE ]\n" +
                        "\t| { ADD | DROP } { ROWGUIDCOL | PERSISTED | [ NOT FOR REPLICATION ] | SPARSE | HIDDEN }\n" +
                        "\t| { ADD | DROP } MASKED [ WITH ( FUNCTION = 'mask_function' ) ]\n" +
                        "}\n" +
                        "[ WITH ( ONLINE = ON | OFF )\n" +
                        "| WITH CHECK | NOCHECK ]");
    }

}