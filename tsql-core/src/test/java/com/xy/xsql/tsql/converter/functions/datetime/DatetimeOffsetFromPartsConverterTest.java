package com.xy.xsql.tsql.converter.functions.datetime;

import com.xy.xsql.block.core.printer.MetaBlockPrinter;
import com.xy.xsql.block.model.BlockMeta;
import org.junit.Assert;
import org.junit.Test;

import java.io.StringWriter;

import static org.junit.Assert.*;

/**
 * Created by xiaoyao9184 on 2018/6/1.
 */
public class DatetimeOffsetFromPartsConverterTest {

    @Test
    public void testMetaPrint() throws Exception {
        BlockMeta b = DatetimeOffsetFromPartsConverter.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<DATETIMEOFFSETFROMPARTS> ::=\n" +
                        "DATETIMEOFFSETFROMPARTS ( year , month , day , hour , minute , seconds , fractions , hour_offset , minute_offset , precision )");
    }

}