package com.xy.xsql.block.tsql.core.datatype;

import com.xy.xsql.block.core.MetaContextBlockPrinter;
import com.xy.xsql.block.model.BlockMeta;
import org.junit.Assert;
import org.junit.Test;

import java.io.StringWriter;

/**
 * Created by xiaoyao9184 on 2017/6/21.
 */
public class TableTypeDefinitionConverterTest {

    @Test
    public void test() throws Exception {
        BlockMeta b = TableTypeDefinitionConverter.meta;

        StringWriter writer = new MetaContextBlockPrinter()
                .print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<table_type_definition> ::=\n" +
                        "TABLE ( { <column_definition> | <table_constraint> } [,...n] )");
    }

    @Test
    public void testTableConstraint() throws Exception {
        BlockMeta b = TableTypeDefinitionConverter.TableConstraintConverter.meta;

        StringWriter writer = new MetaContextBlockPrinter()
                .print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<table_constraint> ::=\n" +
                        "{ PRIMARY KEY | UNIQUE } ( column_name [,...n] )\n" +
                        "| CHECK ( logical_expression )");
    }

}