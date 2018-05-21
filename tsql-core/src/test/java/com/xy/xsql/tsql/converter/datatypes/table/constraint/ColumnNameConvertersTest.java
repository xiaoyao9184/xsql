package com.xy.xsql.tsql.converter.datatypes.table.constraint;

import com.xy.xsql.block.core.printer.MetaBlockPrinter;
import com.xy.xsql.block.model.BlockMeta;
import org.junit.Assert;
import org.junit.Test;

import java.io.StringWriter;

/**
 * Created by xiaoyao9184 on 2017/9/6.
 */
public class ColumnNameConvertersTest {


    @Test
    public void testMetaPrint_Base() throws Exception {
        BlockMeta b = ColumnNameConverters.Base.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "column");
    }

    @Test
    public void testMetaPrint_Order() throws Exception {
        BlockMeta b = ColumnNameConverters.Order.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "column [ ASC | DESC ]");
    }
}