package com.xy.xsql.tsql.converter.functions.metadata;

import com.xy.xsql.block.core.printer.MetaBlockPrinter;
import com.xy.xsql.block.model.BlockMeta;
import org.junit.Assert;
import org.junit.Test;

import java.io.StringWriter;

import static org.junit.Assert.*;

/**
 * Created by xiaoyao9184 on 2018/6/1.
 */
public class TypePropertyConverterTest {

    @Test
    public void testMetaPrint() throws Exception {
        BlockMeta b = TypePropertyConverter.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<TYPEPROPERTY> ::=\n" +
                        "TYPEPROPERTY ( type , property )");
    }

}