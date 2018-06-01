package com.xy.xsql.tsql.converter.functions.systemstatistical;

import com.xy.xsql.block.core.printer.MetaBlockPrinter;
import com.xy.xsql.block.model.BlockMeta;
import org.junit.Assert;
import org.junit.Test;

import java.io.StringWriter;

import static org.junit.Assert.*;

/**
 * Created by xiaoyao9184 on 2018/5/31.
 */
public class fn_virtualfilestatsConverterTest {

    @Test
    public void testMetaPrint() throws Exception {
        BlockMeta b = fn_virtualfilestatsConverter.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<fn_virtualfilestats> ::=\n" +
                        "fn_virtualfilestats ( { database_id | NULL } , { file_id | NULL } )");
    }

}