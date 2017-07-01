package com.xy.xsql.block.tsql.core.statement.dml;

import com.xy.xsql.block.core.ReferenceBlockPrinter;
import com.xy.xsql.block.model.ReferenceBlock;
import com.xy.xsql.block.tsql.core.statement.ddl.ReNameDatabaseConverter;
import com.xy.xsql.tsql.core.statement.InsertBuilderTest;
import com.xy.xsql.tsql.model.statement.dml.Insert;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by xiaoyao9184 on 2017/6/17.
 */
public class InsertConverterTest {

    @Test
    public void test() throws Exception {
        ReferenceBlock b = InsertConverter.meta();

        StringWriter writer = new ReferenceBlockPrinter()
                .print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<INSERT> ::=\n" +
                        "[ WITH <common_table_expression> [ ,...n ] ]\n" +
                        "INSERT\n" +
                        "[ TOP ( expression ) [ PERCENT ] ]\n" +
                        "[ INTO ]\n" +
                        "{ <object> }\n" +
                        "[ ( column_list ) ]\n" +
                        "[ <OUTPUT Clause> ]\n" +
                        "{\n" +
                        "[ <VALUES ( { DEFAULT | NULL | expression } [ ,...n ] ) [ ,...n     ]> ]\n" +
                        "| [ DEFAULT VALUES ]\n" +
                        "}");
    }

    private InsertBuilderTest builderTest;
    private Map<Insert,String> model2StringMap;

    @Before
    public void init(){
        builderTest = new InsertBuilderTest();
        model2StringMap = new HashMap<>();

        model2StringMap.put(
                builderTest.example1A,
                "INSERT INTO Production.UnitMeasure\n" +
                        "     VALUES (N'FT', N'Feet', '20080414')");

        model2StringMap.put(
                builderTest.example1B,
                "INSERT INTO Production.UnitMeasure\n" +
                        "     VALUES (N'FT2', N'Square Feet ', '20080923'), (N'Y', N'Yards', '20080923'), (N'Y3', N'Cubic Yards', '20080923')");

        model2StringMap.put(
                builderTest.example1C,
                "INSERT INTO Production.UnitMeasure (Name, UnitMeasureCode,\n" +
                        "     ModifiedDate)\n" +
                        "     VALUES (N'Square Yards', N'Y2', GETDATE())");

        model2StringMap.put(
                builderTest.example2A,
                "INSERT INTO dbo.T1 (column_4)\n" +
                        "     VALUES ('Explicit value')");

        model2StringMap.put(
                builderTest.example2A1,
                "INSERT INTO dbo.T1 (column_2, column_4)\n" +
                        "     VALUES ('Explicit value', 'Explicit value')");

        model2StringMap.put(
                builderTest.example2A2,
                "INSERT INTO dbo.T1 (column_2)\n" +
                        "     VALUES ('Explicit value')");

        model2StringMap.put(
                builderTest.example2A3,
                "INSERT INTO T1 DEFAULT VALUES");

    }

    @SuppressWarnings("Duplicates")
    @Test
    public void testPrint() throws Exception {
        model2StringMap.forEach((key, value) -> {
            StringWriter writer = ReferenceBlockPrinter.print(key);
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