package com.xy.xsql.block.tsql.core.statement.ddl.create.table;

import com.xy.xsql.block.core.printer.ModelMetaBlockPrinter;
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

        StringWriter writer = new ModelMetaBlockPrinter()
                .printMeta(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "CREATE TABLE\n" +
                        "\t[ server_name . ] [ database_name . [ schema_name ] . | schema_name . ] table_name\n" +
                        "\t( <column definition list> [,...n] )");
    }
}