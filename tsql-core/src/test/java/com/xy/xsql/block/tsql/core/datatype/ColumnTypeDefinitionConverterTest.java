package com.xy.xsql.block.tsql.core.datatype;

import com.xy.xsql.block.core.MetaContextBlockPrinter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.core.datatype.ColumnDefinitionBuilderTest;
import com.xy.xsql.tsql.model.datatype.ColumnDefinition;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.StringWriter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by xiaoyao9184 on 2017/6/21.
 */
public class ColumnTypeDefinitionConverterTest {

    @Test
    public void test() throws Exception {
        BlockMeta b = ColumnTypeDefinitionConverter.meta();

        StringWriter writer = new MetaContextBlockPrinter()
                .print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<column_definition> ::=\n" +
                        "column_name\n" +
                        "scalar_data_type\n" +
                        "[ COLLATE <collation_definition> ]\n" +
                        "[ ROWGUIDCOL ]");
    }

    @Test
    public void testColumnConstraint() throws Exception {
        BlockMeta b = ColumnTypeDefinitionConverter.ColumnConstraintConverter.meta();

        StringWriter writer = new MetaContextBlockPrinter()
                .print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<column_constraint> ::=\n" +
                        "[ NULL | NOT NULL ]\n" +
                        "| [ PRIMARY KEY | UNIQUE ]\n" +
                        "| CHECK ( logical_expression )");
    }
    private ColumnDefinitionBuilderTest builderTest;
    private Map<ColumnDefinition,String> model2StringMap;

    @Before
    public void init(){
        builderTest = new ColumnDefinitionBuilderTest();
        model2StringMap = new LinkedHashMap<>();

        model2StringMap.put(
                builderTest.exampleA,
                "EmpID int");

    }

    @SuppressWarnings("Duplicates")
    @Test
    public void testPrint() throws Exception {
        model2StringMap.forEach((key, value) -> {
            StringWriter writer = MetaContextBlockPrinter.print(key);
            String check = writer.toString()
                    .replaceAll(" ", "")
                    .replaceAll("\n", "");

            String ok = value
                    .replaceAll(" ", "")
                    .replaceAll("\n", "");
            Assert.assertEquals(
                    check,
                    ok);
        });
    }
}