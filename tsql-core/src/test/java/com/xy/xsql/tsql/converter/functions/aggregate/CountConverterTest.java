package com.xy.xsql.tsql.converter.functions.aggregate;

import com.xy.xsql.block.core.printer.MetaBlockPrinter;
import com.xy.xsql.block.model.BlockMeta;
import org.junit.Assert;
import org.junit.Test;

import java.io.StringWriter;

import static org.junit.Assert.*;

/**
 * Created by xiaoyao9184 on 2018/5/30.
 */
public class CountConverterTest {

    @Test
    public void testMetaPrint() throws Exception {
        BlockMeta b = CountConverter.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<COUNT> ::=\n" +
                        "COUNT ( [ ALL | DISTINCT ] expression | * )\n" +
                        "\t[ OVER ( \n" +
                        "\t\t[ <PARTITION BY clause> ]\n" +
                        "\t\t[ <ORDER BY clause> ]\n" +
                        "\t\t[ <ROW or RANGE clause> ]\n" +
                        "\t ) ]");
    }

}