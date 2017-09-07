package com.xy.xsql.block.tsql.core.element.constraint;

import com.xy.xsql.block.core.printer.MetaBlockPrinter;
import com.xy.xsql.block.model.BlockMeta;
import org.junit.Assert;
import org.junit.Test;

import java.io.StringWriter;

/**
 * Created by xiaoyao9184 on 2017/9/6.
 */
public class NullOrNotNullConverterTest {

    @Test
    public void testMetaPrint() throws Exception {
        BlockMeta b = NullOrNotNullConverter.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "NULL | NOT NULL");
    }

}