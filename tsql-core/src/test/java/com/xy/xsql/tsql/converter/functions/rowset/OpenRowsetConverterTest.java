package com.xy.xsql.tsql.converter.functions.rowset;

import com.xy.xsql.block.core.printer.MetaBlockPrinter;
import com.xy.xsql.block.model.BlockMeta;
import org.junit.Assert;
import org.junit.Test;

import java.io.StringWriter;

import static org.junit.Assert.*;

/**
 * Created by xiaoyao9184 on 2018/5/31.
 */
public class OpenRowsetConverterTest {

    @Test
    public void testMetaPrint() throws Exception {
        BlockMeta b = OpenRowsetConverter.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        //TODO support OpenRowset
        Assert.assertEquals(writer.toString(),
                "<OPENQUERY> ::=\n" +
                        "OPENQUERY ( linked_server , 'query' )");
    }

}