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
public class AlterFiletableOptionConverterTest {

    @Test
    public void testMetaPrint() throws Exception {
        BlockMeta b = AlterFiletableOptionConverter.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<filetable_option> ::=\n" +
                        "{\n" +
                        "\t[ ENABLE | DISABLE FILETABLE_NAMESPACE ]\n" +
                        "\t[ SET ( FILETABLE_DIRECTORY = directory_name ) ]\n" +
                        "}");
    }

}