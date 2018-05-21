package com.xy.xsql.tsql.converter.datatypes.table.column;

import com.xy.xsql.block.core.printer.MetaBlockPrinter;
import com.xy.xsql.block.model.BlockMeta;
import org.junit.Assert;
import org.junit.Test;

import java.io.StringWriter;

/**
 * Created by xiaoyao9184 on 2017/9/6.
 */
public class DataTypeConverterTest {

    @Test
    public void testMetaPrint_Full() throws Exception {
        BlockMeta b = DataTypeConverters.Full.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<data type> ::=\n" +
                        "[ type_schema_name . ] type_name\n" +
                        "\t[ ( \n" +
                        "\t\tprecision [ , scale ]\n" +
                        "\t\t| max\n" +
                        "\t\t| { CONTENT | DOCUMENT } xml_schema_collection\n" +
                        "\t ) ]");
    }

    @Test
    public void testMetaPrint_Base() throws Exception {
        BlockMeta b = DataTypeConverters.Base.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<data type> ::=\n" +
                        "[ type_schema_name . ] type_name [ ( precision [ , scale ] ]");
    }
}