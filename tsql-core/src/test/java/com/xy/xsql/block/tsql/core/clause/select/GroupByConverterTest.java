package com.xy.xsql.block.tsql.core.clause.select;

import com.xy.xsql.block.core.MetaContextBlockPrinter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.core.clause.select.GroupByBuilderTest;
import com.xy.xsql.tsql.model.clause.select.GroupBy;
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
    public void test() throws Exception {
        BlockMeta b = GroupByConverter.meta;

        StringWriter writer = new MetaContextBlockPrinter()
                .print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "GROUP BY {\n" +
                        "column-expression\n" +
                        "| ROLLUP ( { <group_by_expression> } [,...n] )\n" +
                        "| CUBE ( { <group_by_expression> } [,...n] )\n" +
                        "| GROUPING SETS ( { <grouping_set> } [,...n] )\n" +
                        "| ()\n" +
                        "} [,...n]");
    }

    @Test
    public void testGroupByExpression() throws Exception {
        BlockMeta b = GroupByConverter.GroupByExpressionConverter.meta;

        StringWriter writer = new MetaContextBlockPrinter()
                .print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<group_by_expression> ::=\n" +
                        "column-expression\n" +
                        "| ( column-expression [,...n] )");
    }

    @Test
    public void testGroupingSet() throws Exception {
        BlockMeta b = GroupByConverter.GroupingSetConverter.meta;

        StringWriter writer = new MetaContextBlockPrinter()
                .print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<grouping_set> ::=\n" +
                        "()\n" +
                        "| <grouping_set_item>\n" +
                        "| ( <grouping_set_item> [,...n] )");
    }

    @Test
    public void testGroupingSetItem() throws Exception {
        BlockMeta b = GroupByConverter.GroupingSetItemConverter.meta;

        StringWriter writer = new MetaContextBlockPrinter()
                .print(b);

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