package com.xy.xsql.tsql.converter.functions.cursor;

import com.xy.xsql.block.core.printer.MetaBlockPrinter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.converter.functions.cryptographic.VerifySignedByCertConverter;
import org.junit.Assert;
import org.junit.Test;

import java.io.StringWriter;

import static org.junit.Assert.*;

/**
 * Created by xiaoyao9184 on 2018/5/31.
 */
public class CursorRowsConverterTest {

    @Test
    public void testMetaPrint() throws Exception {
        BlockMeta b = CursorRowsConverter.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<@@CURSOR_ROWS> ::=\n" +
                        "@@CURSOR_ROWS");
    }

}