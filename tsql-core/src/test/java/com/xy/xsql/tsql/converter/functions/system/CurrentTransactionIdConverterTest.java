package com.xy.xsql.tsql.converter.functions.system;

import com.xy.xsql.block.core.printer.MetaBlockPrinter;
import com.xy.xsql.block.model.BlockMeta;
import org.junit.Assert;
import org.junit.Test;

import java.io.StringWriter;

import static org.junit.Assert.*;

/**
 * Created by xiaoyao9184 on 2018/6/1.
 */
public class CurrentTransactionIdConverterTest {

    @Test
    public void testMetaPrint() throws Exception {
        BlockMeta b = CurrentTransactionIdConverter.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<CURRENT_TRANSACTION_ID> ::=\n" +
                        "CURRENT_TRANSACTION_ID ( )");
    }

}