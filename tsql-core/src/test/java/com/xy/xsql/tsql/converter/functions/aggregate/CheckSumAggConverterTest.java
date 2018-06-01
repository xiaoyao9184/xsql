package com.xy.xsql.tsql.converter.functions.aggregate;

import com.xy.xsql.block.core.printer.MetaBlockPrinter;
import com.xy.xsql.block.model.BlockMeta;
import org.junit.Assert;
import org.junit.Test;

import java.io.StringWriter;

import static org.junit.Assert.*;

/**
 * Created by xiaoyao9184 on 2018/5/30.
 */
public class CheckSumAggConverterTest {

    @Test
    public void testMetaPrint() throws Exception {
        BlockMeta b = CheckSumAggConverter.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<CHECKSUM_AGG> ::=\n" +
                        "CHECKSUM_AGG ( [ ALL | DISTINCT ] expression )");
    }
}