package com.xy.xsql.block.tsql.core.statement.ddl.alter.table;

import com.xy.xsql.block.core.printer.MetaBlockPrinter;
import com.xy.xsql.block.model.BlockMeta;
import org.junit.Assert;
import org.junit.Test;

import java.io.StringWriter;

import static org.junit.Assert.*;

/**
 * Created by xiaoyao9184 on 2017/9/18.
 */
public class LowPriorityLockWaitConverterTest {

    @Test
    public void testMetaPrint() throws Exception {
        BlockMeta b = LowPriorityLockWaitConverter.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<low_priority_lock_wait> ::=\n" +
                        "{\n" +
                        "\tWAIT_AT_LOW_PRIORITY ( MAX_DURATION = <time> [ MINUTES ] ,\n" +
                        "\t\tABORT_AFTER_WAIT = { NONE | SELF | BLOCKERS }\n" +
                        "}");
    }

}