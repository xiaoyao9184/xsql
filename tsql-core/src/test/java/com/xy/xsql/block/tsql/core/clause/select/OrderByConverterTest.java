package com.xy.xsql.block.tsql.core.clause.select;

import com.xy.xsql.block.core.MetaContextBlockPrinter;
import com.xy.xsql.block.core.MetaContextKeywordBlockConverter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.core.clause.select.OrderByBuilderTest;
import com.xy.xsql.tsql.model.clause.select.OrderBy;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.StringWriter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by xiaoyao9184 on 2017/6/21.
 */
public class OrderByConverterTest {

    @Test
    public void test() throws Exception {
        BlockMeta b = OrderByConverter.meta;

        StringWriter writer = new MetaContextBlockPrinter()
                .printMeta(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<ORDER BY Clause> ::=\n" +
                        "ORDER BY \n" +
                        "order_by_expression [ ASC | DESC ] [,...n] \n" +
                        "[ <offset_fetch> ]");
    }

    @Test
    public void testOffsetFetch() throws Exception {
        BlockMeta b = OrderByConverter.OffsetFetchConverter.meta;

        StringWriter writer = new MetaContextBlockPrinter()
                .printMeta(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<offset_fetch> ::=\n" +
                        "OFFSET { integer_constant | offset_row_count_expression } { ROW | ROWS } \n" +
                        "[\n" +
                        "FETCH { FIRST | NEXT } { integer_constant | fetch_row_count_expression } { ROW | ROWS } ONLY\n" +
                        "]");
    }


    private Map<OrderBy,String> model2StringMap;

    @Before
    public void init(){
        OrderByBuilderTest builderTest = new OrderByBuilderTest();
        model2StringMap = new LinkedHashMap<>();

        model2StringMap.put(
                builderTest.example1A,
                "ORDER BY ProductID");

        model2StringMap.put(
                builderTest.example1D,
                "ORDER BY DATEPART(year, HireDate)");

        model2StringMap.put(
                builderTest.example2A,
                "ORDER BY ProductID DESC");

        model2StringMap.put(
                builderTest.example2B,
                "ORDER BY Name ASC");

        model2StringMap.put(
                builderTest.example2C,
                "ORDER BY FirstName ASC, LastName DESC");

        model2StringMap.put(
                builderTest.example31,
                "ORDER BY name");

        //TODO support COLLATE
//        model2StringMap.put(
//                builderTest.example32,
//                "ORDER BY name COLLATE Latin1_General_CS_AS");

        model2StringMap.put(
                builderTest.example41,
                "ORDER BY CASE SalariedFlag WHEN 1 THEN BusinessEntityID END DESC\n" +
                        "        ,CASE WHEN SalariedFlag = 0 THEN BusinessEntityID END");

        model2StringMap.put(
                builderTest.example42,
                "ORDER BY CASE CountryRegionName WHEN 'United States' THEN TerritoryName\n" +
                        "         ELSE CountryRegionName END");

        model2StringMap.put(
                builderTest.example6A1,
                "ORDER BY DepartmentID OFFSET 5 ROWS");

        model2StringMap.put(
                builderTest.example6A2,
                "ORDER BY DepartmentID\n" +
                        "     OFFSET 0 ROWS\n" +
                        "     FETCH NEXT 10 ROWS ONLY");

        model2StringMap.put(
                builderTest.example6B,
                "ORDER BY DepartmentID ASC\n" +
                        "     OFFSET @StartingRowNumber ROWS\n" +
                        "     FETCH NEXT @FetchRows ROWS ONLY");

        model2StringMap.put(
                builderTest.example6C,
                "ORDER BY DepartmentID ASC\n" +
                        "     OFFSET @StartingRowNumber - 1 ROWS\n" +
                        "     FETCH NEXT @EndingRowNumber - @StartingRowNumber + 1 ROWS ONLY");

        model2StringMap.put(
                builderTest.example6D,
                "ORDER BY DepartmentID ASC\n" +
                        "     OFFSET @StartingRowNumber ROWS\n" +
                        "     FETCH NEXT (SELECT PageSize FROM dbo.AppSettings WHERE AppSettingID = 1) ROWS ONLY");

        model2StringMap.put(
                builderTest.example6E,
                "ORDER BY DepartmentID ASC\n" +
                        "     OFFSET @StartingRowNumber - 1 ROWS\n" +
                        "     FETCH NEXT @RowCountPerPage ROWS ONLY");

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

    @SuppressWarnings("Duplicates")
    @Test
    public void testKeywordPrint() throws Exception {
        final int[] index = {1};
        model2StringMap.forEach((key, value) -> {
            String check = MetaContextKeywordBlockConverter
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