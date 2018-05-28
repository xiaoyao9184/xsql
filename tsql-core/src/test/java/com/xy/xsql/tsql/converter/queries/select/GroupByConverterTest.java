package com.xy.xsql.tsql.converter.queries.select;

import com.xy.xsql.block.core.printer.MetaBlockPrinter;
import com.xy.xsql.block.core.printer.ModelMetaBlockPrinter;
import com.xy.xsql.block.core.converter.ModelKeywordBlockConverter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.builder.chain.queries.select.GroupByBuilderTest;
import com.xy.xsql.tsql.converter.queries.select.GroupByConverter;
import com.xy.xsql.tsql.model.queries.select.GroupBy;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.StringWriter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by xiaoyao9184 on 2017/6/20.
 */
public class GroupByConverterTest {

    @Test
    public void testMetaPrint() throws Exception {
        BlockMeta b = GroupByConverter.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<GROUP BY> ::=\n" +
                        "GROUP BY {\n" +
                        "\tcolumn-expression\n" +
                        "\t| ROLLUP ( { <group_by_expression> } [,...n] )\n" +
                        "\t| CUBE ( { <group_by_expression> } [,...n] )\n" +
                        "\t| GROUPING SETS ( { <grouping_set> } [,...n] )\n" +
                        "\t| ()\n" +
                        "} [,...n]");
    }

    @Test
    public void testMetaPrint_GroupByExpression() throws Exception {
        BlockMeta b = GroupByConverter.GroupByExpressionConverter.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<group_by_expression> ::=\n" +
                        "column-expression\n" +
                        "| ( column-expression [,...n] )");
    }

    @Test
    public void testMetaPrint_GroupingSet() throws Exception {
        BlockMeta b = GroupByConverter.GroupingSetConverter.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<grouping_set> ::=\n" +
                        "()\n" +
                        "| <grouping_set_item>\n" +
                        "| ( <grouping_set_item> [,...n] )");
    }

    @Test
    public void testMetaPrint_GroupingSetItem() throws Exception {
        BlockMeta b = GroupByConverter.GroupingSetItemConverter.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<grouping_set_item> ::=\n" +
                        "group_by_expression\n" +
                        "| ROLLUP ( { <group_by_expression> } [,...n] )\n" +
                        "| CUBE ( { <group_by_expression> } [,...n] )");
    }


    private Map<GroupBy,String> model2StringMap;

    @Before
    public void init(){
        GroupByBuilderTest builderTest = new GroupByBuilderTest();
        model2StringMap = new LinkedHashMap<>();

        model2StringMap.put(
                builderTest.exampleA,
                "GROUP BY SalesOrderID");

        model2StringMap.put(
                builderTest.exampleB,
                "GROUP BY a.City");

        model2StringMap.put(
                builderTest.exampleC,
                "GROUP BY DATEPART(yyyy,OrderDate)");

        model2StringMap.put(
                builderTest.exampleD,
                "GROUP BY DATEPART(yyyy,OrderDate)");

        model2StringMap.put(
                builderTest.exampleF,
                "GROUP BY CustomerKey WITH (DISTRIBUTED_AGG)");

        model2StringMap.put(
                builderTest.exampleG5,
                "GROUP BY SalesAmount, SalesAmount*1.1");
//                "GROUP BY SalesAmount, SalesAmount*1.10");

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