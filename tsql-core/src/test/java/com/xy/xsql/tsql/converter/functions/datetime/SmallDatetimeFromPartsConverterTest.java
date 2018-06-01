package com.xy.xsql.tsql.converter.functions.datetime;

import com.xy.xsql.block.core.printer.MetaBlockPrinter;
import com.xy.xsql.block.model.BlockMeta;
import org.junit.Assert;
import org.junit.Test;

import java.io.StringWriter;

import static org.junit.Assert.*;

/**
 * Created by xiaoyao9184 on 2018/6/1.
 */
public class SmallDatetimeFromPartsConverterTest {

    @Test
    public void testMetaPrint() throws Exception {
        BlockMeta b = SmallDatetimeFromPartsConverter.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<SMALLDATETIMEFROMPARTS> ::=\n" +
                        "SMALLDATETIMEFROMPARTS ( year , month , day , hour , minute )");
    }

}