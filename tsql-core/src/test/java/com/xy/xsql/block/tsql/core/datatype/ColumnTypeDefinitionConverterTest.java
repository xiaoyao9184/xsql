package com.xy.xsql.block.tsql.core.datatype;

import com.xy.xsql.block.core.ReferenceBlockPrinter;
import com.xy.xsql.block.model.ReferenceBlock;
import com.xy.xsql.block.tsql.core.element.MultipartNamesConverter;
import org.junit.Assert;
import org.junit.Test;

import java.io.StringWriter;

import static org.junit.Assert.*;

/**
 * Created by xiaoyao9184 on 2017/6/21.
 */
public class ColumnTypeDefinitionConverterTest {

    @Test
    public void test() throws Exception {
        ReferenceBlock b = ColumnTypeDefinitionConverter.meta();

        StringWriter writer = new ReferenceBlockPrinter()
                .print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<column_definition> ::=\n" +
                        "column_name\n" +
                        "scalar_data_type\n" +
                        "[ COLLATE <collation_definition> ]\n" +
                        "[ ROWGUIDCOL ]\n" +
                        "[ column_constraint ] [,...n]");
    }

    @Test
    public void testColumnConstraint() throws Exception {
        ReferenceBlock b = ColumnTypeDefinitionConverter.ColumnConstraintConverter.meta();

        StringWriter writer = new ReferenceBlockPrinter()
                .print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<column_constraint> ::=\n" +
                        "[ NULL | NOT NULL ]\n" +
                        "| [ PRIMARY KEY | UNIQUE ]\n" +
                        "| CHECK ( logical_expression )");
    }

}