package com.xy.xsql.tsql.converter.queries.select;

import com.xy.xsql.block.core.printer.MetaBlockPrinter;
import com.xy.xsql.block.core.printer.ModelMetaBlockPrinter;
import com.xy.xsql.block.core.converter.ModelKeywordBlockConverter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.builder.chain.queries.select.OverBuilderTest;
import com.xy.xsql.tsql.model.queries.select.Over;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.StringWriter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by xiaoyao9184 on 2017/6/21.
 */
public class OverConverterTest {

    @Test
    public void testMetaPrint() throws Exception {
        BlockMeta b = OverConverter.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<OVER> ::=\n" +
                        "OVER ( \n" +
                        "\t[ <PARTITION BY clause> ]\n" +
                        "\t[ <ORDER BY clause> ]\n" +
                        "\t[ <ROW or RANGE clause> ]\n" +
                        " )");
    }

    @Test
    public void testMetaPrint_PartitionBy() throws Exception {
        BlockMeta b = OverConverter.PartitionByConverter.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<PARTITION BY clause> ::=\n" +
                        "PARTITION BY value_expression [,...n]");
    }

    @Test
    public void testMetaPrint_OrderBy() throws Exception {
        BlockMeta b = OverConverter.OrderByConverter.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<ORDER BY clause> ::=\n" +
                        "ORDER BY order_by_expression [ ASC | DESC ] [,...n]");
    }

    @Test
    public void testMetaPrint_RowRange() throws Exception {
        BlockMeta b = OverConverter.RowRangeConverter.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<ROW or RANGE clause> ::=\n" +
                        "{ ROWS | RANGE } <window frame extent>");
    }

    @Test
    public void testMetaPrint_WindowFrameExtent() throws Exception {
        BlockMeta b = OverConverter.WindowFrameExtentConverter.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<window frame extent> ::=\n" +
                        "{\n" +
                        "\t<window frame preceding>\n" +
                        "\t| <window frame between>\n" +
                        "}");
    }

    @Test
    public void testMetaPrint_WindowFrameBetween() throws Exception {
        BlockMeta b = OverConverter.WindowFrameBetweenConverter.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<window frame between> ::=\n" +
                        "BETWEEN <window frame bound> AND <window frame bound>");
    }

    @Test
    public void testMetaPrint_WindowFrameBound() throws Exception {
        BlockMeta b = OverConverter.WindowFrameBoundConverter.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<window frame bound> ::=\n" +
                        "{\n" +
                        "\t<window frame preceding>\n" +
                        "\t| <window frame following>\n" +
                        "}");
    }

    @Test
    public void testMetaPrint_WindowFramePreceding() throws Exception {
        BlockMeta b = OverConverter.WindowFramePrecedingConverter.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<window frame preceding> ::=\n" +
                        "UNBOUNDED PRECEDING | <unsigned_value_specification> PRECEDING | CURRENT ROW");
    }

    @Test
    public void testMetaPrint_WindowFrameFollowing() throws Exception {
        BlockMeta b = OverConverter.WindowFrameFollowingConverter.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<window frame following> ::=\n" +
                        "UNBOUNDED FOLLOWING | <unsigned_value_specification> FOLLOWING | CURRENT ROW");
    }

    @Test
    public void testMetaPrint_UnsignedValueSpecification() throws Exception {
        BlockMeta b = OverConverter.UnsignedValueSpecificationConverter.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<unsigned value specification> ::=\n" +
                        "{ <unsigned integer literal> }");
    }



    private Map<Over,String> model2StringMap;

    @Before
    public void init(){
        OverBuilderTest builderTest = new OverBuilderTest();
        model2StringMap = new LinkedHashMap<>();

        model2StringMap.put(
                builderTest.exampleA,
                "OVER(PARTITION BY PostalCode ORDER BY SalesYTD DESC)");

        model2StringMap.put(
                builderTest.exampleB,
                "OVER(PARTITION BY SalesOrderID)");

        model2StringMap.put(
                builderTest.exampleC1,
                "OVER (PARTITION BY TerritoryID\n" +
                        "     ORDER BY DATEPART(yy,ModifiedDate)\n" +
                        "     )");

        model2StringMap.put(
                builderTest.exampleC2,
                "OVER (ORDER BY DATEPART(yy,ModifiedDate)   \n" +
                        "     )");

        model2StringMap.put(
                builderTest.exampleD1,
                "OVER (PARTITION BY TerritoryID\n" +
                        "     ORDER BY DATEPART(yy,ModifiedDate)\n" +
                        "     ROWS BETWEEN CURRENT ROW AND 1 FOLLOWING )");

        model2StringMap.put(
                builderTest.exampleD2,
                "OVER (PARTITION BY TerritoryID   \n" +
                        "    ORDER BY DATEPART(yy,ModifiedDate)   \n" +
                        "    ROWS UNBOUNDED PRECEDING)");

        model2StringMap.put(
                builderTest.exampleE,
                "OVER(ORDER BY SUM(SalesAmountQuota) DESC)");

        model2StringMap.put(
                builderTest.exampleF,
                "OVER(PARTITION BY SalesOrderNumber)");

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