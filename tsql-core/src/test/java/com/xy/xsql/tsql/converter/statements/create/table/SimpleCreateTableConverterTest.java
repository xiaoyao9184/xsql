package com.xy.xsql.tsql.converter.statements.create.table;

import com.xy.xsql.block.core.printer.MetaBlockPrinter;
import com.xy.xsql.block.model.BlockMeta;
import org.junit.Assert;
import org.junit.Test;

import java.io.StringWriter;

/**
 * Created by xiaoyao9184 on 2017/9/6.
 */
public class SimpleCreateTableConverterTest {


    @Test
    public void testMetaPrint() throws Exception {
        BlockMeta b = SimpleCreateTableConverter.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "CREATE TABLE\n" +
                        "\t[ server_name . ] [ database_name . [ schema_name ] . | schema_name . ] table_name\n" +
                        "\t( <column definition list> [,...n] )");
    }
}