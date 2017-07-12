package com.xy.xsql.block.tsql.core.expression;

import com.xy.xsql.block.core.MetaContextBlockPrinter;
import com.xy.xsql.block.model.BlockMeta;
import org.junit.Assert;
import org.junit.Test;

import java.io.StringWriter;

/**
 * Created by xiaoyao9184 on 2017/6/15.
 */
public class AtTimeConverterTest {

    @Test
    public void testMetaPrint() throws Exception {
        BlockMeta b = AtTimeConverter.meta;

        StringWriter writer = new MetaContextBlockPrinter()
                .printMeta(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<AT TIME ZONE> ::=\n" +
                        "inputdate AT TIME ZONE timezone");
    }

}