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
public class FormatMessageConverterTest {

    @Test
    public void testMetaPrint() throws Exception {
        BlockMeta b = FormatMessageConverter.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<FORMATMESSAGE> ::=\n" +
                        "FORMATMESSAGE ( { msg_number | 'msg_string' } [ , param_value [,...n] ] )");
    }

}