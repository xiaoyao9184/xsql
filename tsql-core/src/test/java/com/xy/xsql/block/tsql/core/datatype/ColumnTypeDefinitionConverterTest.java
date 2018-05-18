package com.xy.xsql.block.tsql.core.datatype;

import com.xy.xsql.block.core.printer.MetaBlockPrinter;
import com.xy.xsql.block.core.printer.ModelMetaBlockPrinter;
import com.xy.xsql.block.core.converter.ModelKeywordBlockConverter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.builder.chain.datatypes.table.ColumnDefinitionBuilderTest;
import com.xy.xsql.tsql.model.datatypes.table.column.ColumnDefinition;
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
    public void testMetaPrint() throws Exception {
        BlockMeta b = ColumnTypeDefinitionConverter.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<column_definition> ::=\n" +
                        "column_name\n" +
                        "scalar_data_type\n" +
                        "[ COLLATE <collation_definition> ]\n" +
                        "[ ROWGUIDCOL ]");
    }

    @Test
    public void testMetaPrint_ColumnConstraint() throws Exception {
        BlockMeta b = ColumnTypeDefinitionConverter.ColumnConstraintConverter.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<column_constraint> ::=\n" +
                        "[ NULL | NOT NULL ]\n" +
                        "| [ PRIMARY KEY | UNIQUE ]\n" +
                        "| CHECK ( logical_expression )");
    }


    private Map<ColumnDefinition,String> model2StringMap;

    @Before
    public void init(){
        ColumnDefinitionBuilderTest builderTest = new ColumnDefinitionBuilderTest();
        model2StringMap = new LinkedHashMap<>();

        model2StringMap.put(
                builderTest.exampleA,
                "EmpID int");

    }

    @SuppressWarnings("Duplicates")
    @Test
    public void testPrint() throws Exception {
        final int[] index = {1};
        model2StringMap.forEach((key, value) -> {
            StringWriter writer = ModelMetaBlockPrinter.print(key);
            String check = writer.toString()
                    .replaceAll(" ", "")
                    .replaceAll("\n", "");

            String ok = value
                    .replaceAll(" ", "")
                    .replaceAll("\n", "");
            Assert.assertEquals(
                    "Not Equal Index:" + index[0],
                    check,
                    ok);
            index[0]++;
        });
    }

    @SuppressWarnings("Duplicates")
    @Test
    public void testKeywordPrint() throws Exception {
        final int[] index = {1};
        model2StringMap.forEach((key, value) -> {
            String check = ModelKeywordBlockConverter
                    .convert(key)
                    .print();
            System.out.println(check);

            check = check
                    .replaceAll(" ", "")
                    .replaceAll("\n", "");

            String ok = value
                    .replaceAll(" ", "")
                    .replaceAll("\n", "");
            Assert.assertEquals(
                    "Not Equal Index:" + index[0],
                    check,
                    ok);
            index[0]++;
        });
    }
}