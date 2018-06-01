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
public class OpenJsonConverterTest {

    @Test
    public void testMetaPrint() throws Exception {
        BlockMeta b = OpenJsonConverter.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<OPENJSON> ::=\n" +
                        "OPENJSON ( jsonExpression [ , path ] ) [ <with_clause> ]");
    }

    @Test
    public void testMetaPrint_WithClause() throws Exception {
        BlockMeta b = OpenJsonConverter.metaWith;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<with_clause> ::=\n" +
                        "WITH ( { colName type [ column_path ] [ AS JSON ] } [,...n] )");
    }

}