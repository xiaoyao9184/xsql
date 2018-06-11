package com.xy.xsql.tsql.converter.functions.security;

import com.xy.xsql.block.core.printer.MetaBlockPrinter;
import com.xy.xsql.block.model.BlockMeta;
import org.junit.Assert;
import org.junit.Test;

import java.io.StringWriter;

import static org.junit.Assert.*;

/**
 * Created by xiaoyao9184 on 2018/6/11.
 */
public class OriginalLoginConverterTest {

    @Test
    public void testMetaPrint() throws Exception {
        BlockMeta b = OriginalLoginConverter.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<ORIGINAL_LOGIN> ::=\n" +
                        "ORIGINAL_LOGIN ( )");
    }

}