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
public class DropConverterTest {

    @Test
    public void testMetaPrint() throws Exception {
        BlockMeta b = DropConverter.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "DROP {\n" +
                        "\t[ CONSTRAINT ] [ IF EXISTS ]\n" +
                        "\t{\n" +
                        "\t\tconstraint_name\n" +
                        "\t\tWITH ( <drop_clustered_constraint_option> [,...n] )\n" +
                        "\t} [,...n]\n" +
                        "\t| COLUMN [ IF EXISTS ]\n" +
                        "\t{\n" +
                        "\t\tcolumn_name\n" +
                        "\t} [,...n]\n" +
                        "\t| PERIOD FOR SYSTEM_TIME\n" +
                        "} [,...n]");
    }

}