package com.xy.xsql.block.tsql.core.statement.ddl;

import com.xy.xsql.block.core.MetaContextBlockPrinter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.core.statement.ddl.RenameBuilderTest;
import com.xy.xsql.tsql.model.statement.ddl.rename.ReName;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.StringWriter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by xiaoyao9184 on 2017/6/17.
 */
public class ReNameConverterTest {

    @Test
    public void test() throws Exception {
        BlockMeta b = ReNameConverter.meta;

        StringWriter writer = new MetaContextBlockPrinter()
                .printMeta(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<RENAME> ::=\n" +
                        "RENAME DATABASE [ :: ] database_name TO new_database_name\n" +
                        "| RENAME OBJECT [ :: ] [ [ database_name .  [schema_name ] ] . ] | [schema_name . ] ] table_name TO new_table_name");
    }

    @Test
    public void testReNameDataBase() throws Exception {
        BlockMeta b = ReNameConverter.ReNameDataBaseConverter.meta;

        StringWriter writer = new MetaContextBlockPrinter()
                .printMeta(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<RENAME> ::=\n" +
                        "RENAME DATABASE [ :: ] database_name TO new_database_name");
    }

    @Test
    public void testReNameTable() throws Exception {
        BlockMeta b = ReNameConverter.ReNameTableConverter.meta;

        StringWriter writer = new MetaContextBlockPrinter()
                .printMeta(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<RENAME> ::=\n" +
                        "RENAME OBJECT [ :: ] [ [ database_name .  [schema_name ] ] . ] | [schema_name . ] ] table_name TO new_table_name");
    }


    private Map<ReName,String> model2StringMap;

    @Before
    public void init(){
        RenameBuilderTest builderTest = new RenameBuilderTest();
        model2StringMap = new LinkedHashMap<>();

        model2StringMap.put(
                builderTest.exampleA,
                "RENAME DATABASE AdWorks TO AdWorks2");
//                "RENAME DATABASE AdWorks to AdWorks2");

        model2StringMap.put(
                builderTest.exampleB1,
                "RENAME OBJECT Customer TO Customer1");

        model2StringMap.put(
                builderTest.exampleB2,
                "RENAME OBJECT mydb.dbo.Customer TO Customer1");

    }

    @SuppressWarnings("Duplicates")
    @Test
    public void testPrint() throws Exception {
        final int[] index = {1};
        model2StringMap.forEach((key, value) -> {
            StringWriter writer = MetaContextBlockPrinter.print(key);
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

}