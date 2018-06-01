package com.xy.xsql.tsql.converter.functions.collation;

import com.xy.xsql.block.core.printer.MetaBlockPrinter;
import com.xy.xsql.block.model.BlockMeta;
import org.junit.Assert;
import org.junit.Test;

import java.io.StringWriter;

import static org.junit.Assert.*;

/**
 * Created by xiaoyao9184 on 2018/5/31.
 */
public class TertiaryWeightsConverterTest {

    @Test
    public void testMetaPrint() throws Exception {
        BlockMeta b = TertiaryWeightsConverter.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<TERTIARY_WEIGHTS> ::=\n" +
                        "TERTIARY_WEIGHTS ( non_Unicode_character_string_expression )");
    }

}