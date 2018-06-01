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
public class IndexKeyPropertyConverterTest {

    @Test
    public void testMetaPrint() throws Exception {
        BlockMeta b = IndexKeyPropertyConverter.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<INDEXKEY_PROPERTY> ::=\n" +
                        "INDEXKEY_PROPERTY ( object_ID , index_ID , key_ID , property )");
    }

}