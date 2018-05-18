package com.xy.xsql.block.tsql.core.clause;

import com.xy.xsql.block.core.printer.MetaBlockPrinter;
import com.xy.xsql.block.core.printer.ModelMetaBlockPrinter;
import com.xy.xsql.block.core.converter.ModelKeywordBlockConverter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.builder.chain.queries.WhereBuilderTest;
import com.xy.xsql.tsql.model.queries.Where;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.StringWriter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by xiaoyao9184 on 2017/6/20.
 */
public class WhereConverterTest {

    @Test
    public void testMetaPrint() throws Exception {
        BlockMeta b = WhereConverter.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<WHERE> ::=\n" +
                        "WHERE <search_condition>");
    }

    private Map<Where,String> model2StringMap;

    @Before
    public void init(){
        WhereBuilderTest builderTest = new WhereBuilderTest();
        model2StringMap = new LinkedHashMap<>();

        model2StringMap.put(
                builderTest.exampleA,
                "WHERE Name = 'Blade'");

        model2StringMap.put(
                builderTest.exampleB,
                "WHERE Name LIKE '%Frame%'");

        model2StringMap.put(
                builderTest.exampleC,
                "WHERE ProductID <= 12");

        model2StringMap.put(
                builderTest.exampleD,
                "WHERE ProductID = 2\n" +
                        "     OR ProductID = 4\n" +
                        "     OR Name = 'Spokes' ");

        model2StringMap.put(
                builderTest.exampleE,
                "WHERE Name LIKE '%Frame%'\n" +
                        "     AND Name LIKE 'HL%'\n" +
                        "     AND Color = 'Red'");

        model2StringMap.put(
                builderTest.exampleF,
                "WHERE Name IN ('Blade', 'Crown Race', 'Spokes')");

        model2StringMap.put(
                builderTest.exampleG,
                "WHERE ProductID BETWEEN 725 AND 734");

        model2StringMap.put(
                builderTest.exampleH,
                "WHERE LastName = 'Smith'");

        model2StringMap.put(
                builderTest.exampleI,
                "WHERE LastName LIKE '%Smi%'");

        model2StringMap.put(
                builderTest.exampleJ,
                "WHERE EmployeeKey  <= 500");

        model2StringMap.put(
                builderTest.exampleK,
                "WHERE EmployeeKey = 1 OR EmployeeKey = 8 OR EmployeeKey = 12");

        model2StringMap.put(
                builderTest.exampleL,
                "WHERE EmployeeKey <= 500 AND LastName LIKE '%Smi%' AND FirstName LIKE '%A%'");

        model2StringMap.put(
                builderTest.exampleM,
                "WHERE LastName IN ('Smith', 'Godfrey', 'Johnson')");

        model2StringMap.put(
                builderTest.exampleN,
                "WHERE EmployeeKey BETWEEN 100 AND 200");
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
                    .replaceAll("\n", "");
            Assert.assertEquals(
                    "Not Equal Index:" + index[0],
                    check,
                    ok);
            index[0]++;
        });
    }

}