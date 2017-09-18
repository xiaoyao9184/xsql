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
public class AlterChangeTrackingConverterTest {

    @Test
    public void testMetaPrint() throws Exception {
        BlockMeta b = AlterChangeTrackingConverter.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "{ CHECK | NOCHECK } CHANGE_TRACKING\n" +
                        "\t[ WITH ( TRACK_COLUMNS_UPDATED = { ON | OFF } ) ]");
    }

}