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
public class StretchConfigurationConverterTest {

    @Test
    public void testMetaPrint() throws Exception {
        BlockMeta b = StretchConfigurationConverter.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<stretch_configuration> ::=\n" +
                        "{\n" +
                        "\tSET ( \n" +
                        "\t\tREMOTE_DATA_ARCHIVE\n" +
                        "\t\t{\n" +
                        "\t\t\t= ON ( table_stretch_options )\n" +
                        "\t\t\t| = OFF_WITHOUT_DATA_RECOVERY ( MIGRATION_STATE = PAUSED )\n" +
                        "\t\t\t| ( table_stretch_options [,...n] )\n" +
                        "\t\t}\n" +
                        "\t )\n" +
                        "}");
    }

}