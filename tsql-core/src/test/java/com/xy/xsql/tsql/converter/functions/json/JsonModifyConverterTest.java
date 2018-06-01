package com.xy.xsql.tsql.converter.functions.json;

import com.xy.xsql.block.core.printer.MetaBlockPrinter;
import com.xy.xsql.block.model.BlockMeta;
import org.junit.Assert;
import org.junit.Test;

import java.io.StringWriter;

import static org.junit.Assert.*;

/**
 * Created by xiaoyao9184 on 2018/5/31.
 */
public class JsonModifyConverterTest {

    @Test
    public void testMetaPrint() throws Exception {
        BlockMeta b = JsonModifyConverter.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<JSON_MODIFY> ::=\n" +
                        "JSON_MODIFY ( expression , path , newValue )");
    }

}