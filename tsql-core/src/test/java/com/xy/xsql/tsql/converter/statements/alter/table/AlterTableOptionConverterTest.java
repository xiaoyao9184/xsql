package com.xy.xsql.tsql.converter.statements.alter.table;

import com.xy.xsql.block.core.printer.MetaBlockPrinter;
import com.xy.xsql.block.model.BlockMeta;
import org.junit.Assert;
import org.junit.Test;

import java.io.StringWriter;

import static org.junit.Assert.*;

/**
 * Created by xiaoyao9184 on 2017/9/18.
 */
public class AlterTableOptionConverterTest {

    @Test
    public void testMetaPrint() throws Exception {
        BlockMeta b = AlterTableOptionConverter.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<table_option> ::=\n" +
                        "{\n" +
                        "\tSET ( LOCK_ESCALATION = { AUTO | TABLE | DISABLE } )\n" +
                        "}");
    }

}