package com.xy.xsql.block.tsql.core.statement.ddl.create;

import com.xy.xsql.block.core.converter.ModelKeywordBlockConverter;
import com.xy.xsql.block.core.printer.MetaBlockPrinter;
import com.xy.xsql.block.core.printer.ModelMetaBlockPrinter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.core.statement.ddl.CreateDataBaseBuilderTest;
import com.xy.xsql.tsql.model.statements.create.CreateDataBase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.StringWriter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by xiaoyao9184 on 2017/8/4.
 */
public class CreateDataBaseConverterTest {

    @Test
    public void testMetaPrint() throws Exception {
        BlockMeta b = CreateDataBaseConverter.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<Create a database> ::=\n" +
                        "CREATE DATABASE database_name\n" +
                        "[ CONTAINMENT = { NONE | PARTIAL } ]");
    }


    private Map<CreateDataBase,String> model2StringMap;

    @Before
    public void init(){
        CreateDataBaseBuilderTest builderTest = new CreateDataBaseBuilderTest();
        model2StringMap = new LinkedHashMap<>();

        model2StringMap.put(
                builderTest.exampleA,
                "CREATE DATABASE mytest");

//        model2StringMap.put(
//                builderTest.exampleB,
//                "");
//
//        model2StringMap.put(
//                builderTest.exampleC,
//                "");
//
//        model2StringMap.put(
//                builderTest.exampleD,
//                "");
//
//        model2StringMap.put(
//                builderTest.exampleE,
//                "");
//
//        model2StringMap.put(
//                builderTest.exampleF,
//                "");
//
//        model2StringMap.put(
//                builderTest.exampleG,
//                "");
//
//        model2StringMap.put(
//                builderTest.exampleH,
//                "");
//
//        model2StringMap.put(
//                builderTest.exampleI,
//                "");
//
//        model2StringMap.put(
//                builderTest.exampleJ,
//                "");

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
                    .replaceAll("\t", "")
                    .replaceAll("\n", "");

            String ok = value
                    .replaceAll(" ", "")
                    .replaceAll("\t", "")
                    .replaceAll("\n", "");
            Assert.assertEquals(
                    "Not Equal Index:" + index[0],
                    check,
                    ok);
            index[0]++;
        });
    }
}