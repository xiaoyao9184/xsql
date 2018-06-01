package com.xy.xsql.tsql.converter.functions.trigger;

import com.xy.xsql.block.core.printer.MetaBlockPrinter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.converter.functions.text.TextValidConverter;
import org.junit.Assert;
import org.junit.Test;

import java.io.StringWriter;

import static org.junit.Assert.*;

/**
 * Created by xiaoyao9184 on 2018/5/31.
 */
public class ColumnsUpdatedConverterTest {

    @Test
    public void testMetaPrint() throws Exception {
        BlockMeta b = ColumnsUpdatedConverter.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<COLUMNS_UPDATED> ::=\n" +
                        "COLUMNS_UPDATED ( )");
    }

}