package com.xy.xsql.tsql.converter.functions.configuration;

import com.xy.xsql.block.core.printer.MetaBlockPrinter;
import com.xy.xsql.block.model.BlockMeta;
import org.junit.Assert;
import org.junit.Test;

import java.io.StringWriter;

import static org.junit.Assert.*;

/**
 * Created by xiaoyao9184 on 2018/5/31.
 */
public class OptionsConverterTest {

    @Test
    public void testMetaPrint() throws Exception {
        BlockMeta b = OptionsConverter.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<@@OPTIONS> ::=\n" +
                        "@@OPTIONS");
    }

}