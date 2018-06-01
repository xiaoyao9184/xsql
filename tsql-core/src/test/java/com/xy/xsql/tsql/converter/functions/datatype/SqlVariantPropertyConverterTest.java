package com.xy.xsql.tsql.converter.functions.datatype;

import com.xy.xsql.block.core.printer.MetaBlockPrinter;
import com.xy.xsql.block.model.BlockMeta;
import org.junit.Assert;
import org.junit.Test;

import java.io.StringWriter;

import static org.junit.Assert.*;

/**
 * Created by xiaoyao9184 on 2018/5/31.
 */
public class SqlVariantPropertyConverterTest {

    @Test
    public void testMetaPrint() throws Exception {
        BlockMeta b = SqlVariantPropertyConverter.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<SQL_VARIANT_PROPERTY> ::=\n" +
                        "SQL_VARIANT_PROPERTY ( expression , property )");
    }

}