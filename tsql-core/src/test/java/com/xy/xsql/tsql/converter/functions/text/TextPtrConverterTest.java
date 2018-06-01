package com.xy.xsql.tsql.converter.functions.text;

import com.xy.xsql.block.core.printer.MetaBlockPrinter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.converter.functions.rowset.OpenXmlConverter;
import org.junit.Assert;
import org.junit.Test;

import java.io.StringWriter;

import static org.junit.Assert.*;

/**
 * Created by xiaoyao9184 on 2018/5/31.
 */
public class TextPtrConverterTest {

    @Test
    public void testMetaPrint() throws Exception {
        BlockMeta b = TextPtrConverter.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<TEXTPTR> ::=\n" +
                        "TEXTPTR ( column )");
    }

}