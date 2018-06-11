package com.xy.xsql.tsql.converter.functions.aggregate;

import com.xy.xsql.block.core.converter.ModelKeywordBlockConverter;
import com.xy.xsql.block.core.printer.MetaBlockPrinter;
import com.xy.xsql.block.core.printer.ModelMetaBlockPrinter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.builder.chain.functions.aggregate.CountFunctionTest;
import com.xy.xsql.tsql.model.functions.aggregate.Count;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.StringWriter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by xiaoyao9184 on 2018/5/30.
 */
public class CountConverterTest {

    @Test
    public void testMetaPrint() throws Exception {
        BlockMeta b = CountConverter.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<COUNT> ::=\n" +
                        "COUNT ( [ ALL | DISTINCT ] expression | * )\n" +
                        "\t[ OVER ( \n" +
                        "\t\t[ <PARTITION BY clause> ]\n" +
                        "\t\t[ <ORDER BY clause> ]\n" +
                        "\t\t[ <ROW or RANGE clause> ]\n" +
                        "\t ) ]");
    }

    private Map<Count,String> model2StringMap;

    @Before
    public void init(){
        CountFunctionTest builderTest = new CountFunctionTest();
        model2StringMap = new LinkedHashMap<>();

        model2StringMap.put(
                builderTest.exampleA,
                "COUNT(DISTINCT Title)");
        model2StringMap.put(
                builderTest.exampleB,
                "COUNT(*)");
        model2StringMap.put(
                builderTest.exampleD,
                "COUNT(edh.BusinessEntityID) OVER (PARTITION BY edh.DepartmentID)");
        model2StringMap.put(
                builderTest.exampleG,
                "COUNT(EmployeeKey)");
        model2StringMap.put(
                builderTest.exampleI,
                "COUNT(ProductKey) OVER(PARTITION BY SalesOrderNumber)");
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
            System.out.println("==========");

            check = check
                    .replaceAll(" ", "")
                    .replaceAll("\t", "")
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