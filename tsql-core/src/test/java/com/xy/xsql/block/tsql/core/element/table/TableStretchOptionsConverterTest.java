package com.xy.xsql.block.tsql.core.element.table;

import com.xy.xsql.block.core.printer.ModelMetaBlockPrinter;
import com.xy.xsql.block.model.BlockMeta;
import org.junit.Assert;
import org.junit.Test;

import java.io.StringWriter;

/**
 * Created by xiaoyao9184 on 2017/9/6.
 */
public class TableStretchOptionsConverterTest {


    @Test
    public void testMetaPrint() throws Exception {
        BlockMeta b = TableStretchOptionsConverter.meta;

        StringWriter writer = new ModelMetaBlockPrinter()
                .printMeta(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<table_stretch_options> ::=\n" +
                        "{\n" +
                        "\t[ FILTER_PREDICATE = { null | table_predicate_function } , ]\n" +
                        "\tMIGRATION_STATE = OUTBOUND | INBOUND | PAUSED\n" +
                        "}");
    }

}