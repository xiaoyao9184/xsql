package com.xy.xsql.tsql.converter.datatypes.table.table;

import com.xy.xsql.block.core.printer.MetaBlockPrinter;
import com.xy.xsql.block.model.BlockMeta;
import org.junit.Assert;
import org.junit.Test;

import java.io.StringWriter;

/**
 * Created by xiaoyao9184 on 2017/6/21.
 */
public class TableTypeDefinitionConverterTest {

    @Test
    public void testMetaPrint() throws Exception {
        BlockMeta b = TableTypeDefinitionConverter.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<table_type_definition> ::=\n" +
                        "TABLE ( { <column_definition> | <table_constraint> } [,...n] )");
    }

    @Test
    public void testMetaPrint_TableConstraint() throws Exception {
        BlockMeta b = TableTypeDefinitionConverter.TableConstraintConverter.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<table_constraint> ::=\n" +
                        "{ PRIMARY KEY | UNIQUE } ( column_name [,...n] )\n" +
                        "| CHECK ( logical_expression )");
    }

}