package com.xy.xsql.block.tsql.core.statement.ddl.alter.table;

import com.xy.xsql.block.core.printer.MetaBlockPrinter;
import com.xy.xsql.block.model.BlockMeta;
import org.junit.Assert;
import org.junit.Test;

import java.io.StringWriter;

/**
 * Created by xiaoyao9184 on 2017/9/17.
 */
public class AddConverterTest {

    @Test
    public void testMetaPrint() throws Exception {
        BlockMeta b = AddConverter.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "ADD\n" +
                        "{\n" +
                        "\t<column_definition>\n" +
                        "\t| <computed_column_definition>\n" +
                        "\t| <table_constraint>\n" +
                        "\t| <column_set_definition>\n" +
                        "} [,...n]\n" +
                        "| [\n" +
                        "\tsystem_start_time_column_name datetime2 GENERATED ALWAYS AS ROW START\n" +
                        "\t\t[ HIDDEN ] [ NOT NULL ] [ CONSTRAINT constraint_name ]\n" +
                        "\t\tDEFAULT constant_expression [ WITH VALUES ] ,\n" +
                        "\tsystem_end_time_column_name datetime2 GENERATED ALWAYS AS ROW END\n" +
                        "\t\t[ HIDDEN ] [ NOT NULL ] [ CONSTRAINT constraint_name ]\n" +
                        "\t\tDEFAULT constant_expression [ WITH VALUES ]\n" +
                        "]\n" +
                        "PERIOD FOR SYSTEM_TIME ( system_start_time_column_name , system_end_time_column_name  )");
    }
}