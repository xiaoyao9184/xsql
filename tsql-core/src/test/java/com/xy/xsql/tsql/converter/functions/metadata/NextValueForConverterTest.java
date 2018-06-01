package com.xy.xsql.tsql.converter.functions.metadata;

import com.xy.xsql.block.core.printer.MetaBlockPrinter;
import com.xy.xsql.block.model.BlockMeta;
import org.junit.Assert;
import org.junit.Test;

import java.io.StringWriter;

import static org.junit.Assert.*;

/**
 * Created by xiaoyao9184 on 2018/6/1.
 */
public class NextValueForConverterTest {

    @Test
    public void testMetaPrint() throws Exception {
        BlockMeta b = NextValueForConverter.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        //TODO support sequence_name
        Assert.assertEquals(writer.toString(),
                "<NEXT VALUE FOR> ::=\n" +
                        "NEXT VALUE FOR ( [ server_name . ] [ database_name . [ schema_name ] . | schema_name . ] table_name\n" +
                        "\tOVER ( \n" +
                        "\t\t[ <PARTITION BY clause> ]\n" +
                        "\t\t[ <ORDER BY clause> ]\n" +
                        "\t\t[ <ROW or RANGE clause> ]\n" +
                        "\t )");
    }

}