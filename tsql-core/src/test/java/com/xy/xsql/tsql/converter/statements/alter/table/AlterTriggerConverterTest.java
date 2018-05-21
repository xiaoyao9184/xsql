package com.xy.xsql.tsql.converter.statements.alter.table;

import com.xy.xsql.block.core.printer.MetaBlockPrinter;
import com.xy.xsql.block.model.BlockMeta;
import org.junit.Assert;
import org.junit.Test;

import java.io.StringWriter;

import static org.junit.Assert.*;

/**
 * Created by xiaoyao9184 on 2017/9/18
 */
public class AlterTriggerConverterTest {

    @Test
    public void testMetaPrint() throws Exception {
        BlockMeta b = AlterTriggerConverter.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "{ ENABLE | DISABLE } TRIGGER\n" +
                        "\t{ ALL | trigger_name [,...n] }");
    }

}