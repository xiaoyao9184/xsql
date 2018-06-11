package com.xy.xsql.tsql.converter.functions.aggregate;

import com.xy.xsql.block.core.converter.ModelKeywordBlockConverter;
import com.xy.xsql.block.core.printer.MetaBlockPrinter;
import com.xy.xsql.block.core.printer.ModelMetaBlockPrinter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.builder.chain.functions.aggregate.AvgFunctionTest;
import com.xy.xsql.tsql.model.functions.aggregate.Avg;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.StringWriter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by xiaoyao9184 on 2018/5/30.
 */
public class AvgConverterTest {

    @Test
    public void testMetaPrint() throws Exception {
        BlockMeta b = AvgConverter.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<Avg> ::=\n" +
                        "AVG ( [ ALL | DISTINCT ] expression )\n" +
                        "\t[ OVER ( [ partition_by_clause ] order_by_clause ) ]");
    }

    private Map<Avg,String> model2StringMap;

    @Before
    public void init(){
        AvgFunctionTest builderTest = new AvgFunctionTest();
        model2StringMap = new LinkedHashMap<>();

        model2StringMap.put(
                builderTest.exampleA,
                "AVG(VacationHours)");
        model2StringMap.put(
                builderTest.exampleB,
                "AVG(Bonus)");
        model2StringMap.put(
                builderTest.exampleC,
                "AVG(DISTINCT ListPrice)");
        model2StringMap.put(
                builderTest.exampleD,
                "AVG(ListPrice)");
        model2StringMap.put(
                builderTest.exampleE1,
                "AVG(SalesYTD) OVER (PARTITION BY TerritoryID\n" +
                        "     ORDER BY DATEPART(yy,ModifiedDate)\n" +
                        "     )");
        model2StringMap.put(
                builderTest.exampleE2,
                "AVG(SalesYTD) OVER (ORDER BY DATEPART(yy,ModifiedDate))");
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