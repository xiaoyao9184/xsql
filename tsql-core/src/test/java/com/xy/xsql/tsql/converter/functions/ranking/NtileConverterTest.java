package com.xy.xsql.tsql.converter.functions.ranking;

import com.xy.xsql.block.core.printer.MetaBlockPrinter;
import com.xy.xsql.block.model.BlockMeta;
import org.junit.Assert;
import org.junit.Test;

import java.io.StringWriter;

import static org.junit.Assert.*;

/**
 * Created by xiaoyao9184 on 2018/5/31.
 */
public class NtileConverterTest {

    @Test
    public void testMetaPrint() throws Exception {
        BlockMeta b = NtileConverter.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<NTILE> ::=\n" +
                        "NTILE ( integer_expression ) OVER ( [ <partition_by_clause> ] <order_by_clause> )");
    }

}