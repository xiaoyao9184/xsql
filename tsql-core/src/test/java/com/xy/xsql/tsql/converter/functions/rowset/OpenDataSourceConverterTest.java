package com.xy.xsql.tsql.converter.functions.rowset;

import com.xy.xsql.block.core.printer.MetaBlockPrinter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.converter.functions.replication.PublishingServerNameConverter;
import org.junit.Assert;
import org.junit.Test;

import java.io.StringWriter;

import static org.junit.Assert.*;

/**
 * Created by xiaoyao9184 on 2018/5/31.
 */
public class OpenDataSourceConverterTest {

    @Test
    public void testMetaPrint() throws Exception {
        BlockMeta b = OpenDataSourceConverter.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<OPENDATASOURCE> ::=\n" +
                        "OPENDATASOURCE ( provider_name , init_string )");
    }

}