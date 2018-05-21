package com.xy.xsql.tsql.converter.datatypes.table.column;

import com.xy.xsql.block.core.printer.MetaBlockPrinter;
import com.xy.xsql.block.model.BlockMeta;
import org.junit.Assert;
import org.junit.Test;

import java.io.StringWriter;

/**
 * Created by xiaoyao9184 on 2017/9/4.
 */
public class ColumnSetDefinitionConverterTest {


    @Test
    public void testMetaPrint() throws Exception {
        BlockMeta b = ColumnSetDefinitionConverter.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<column_set_definition> ::=\n" +
                        "column_set_name XML COLUMN_SET FOR ALL_SPARSE_COLUMNS");
    }
}