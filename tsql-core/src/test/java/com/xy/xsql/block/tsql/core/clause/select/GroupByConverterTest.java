package com.xy.xsql.block.tsql.core.clause.select;

import com.xy.xsql.block.core.ReferenceBlockPrinter;
import com.xy.xsql.block.model.ReferenceBlock;
import com.xy.xsql.tsql.core.clause.select.GroupByBuilderTest;
import com.xy.xsql.tsql.model.clause.select.GroupBy;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.StringWriter;

/**
 * Created by xiaoyao9184 on 2017/6/20.
 */
public class GroupByConverterTest {

    @Test
    public void test() throws Exception {
        ReferenceBlock b = GroupByConverter.meta();

        StringWriter writer = new ReferenceBlockPrinter()
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
        ReferenceBlock b = GroupByConverter.GroupByExpressionConverter.meta();

        StringWriter writer = new ReferenceBlockPrinter()
                .print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<group_by_expression> ::=\n" +
                        "column-expression\n" +
                        "| ( column-expression [,...n] )");
    }

    @Test
    public void testGroupingSet() throws Exception {
        ReferenceBlock b = GroupByConverter.GroupingSetConverter.meta();

        StringWriter writer = new ReferenceBlockPrinter()
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
        ReferenceBlock b = GroupByConverter.GroupingSetItemConverter.meta();

        StringWriter writer = new ReferenceBlockPrinter()
                .print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<grouping_set_item> ::=\n" +
                        "group_by_expression\n" +
                        "| ROLLUP ( { <group_by_expression> } [,...n] )\n" +
                        "| CUBE ( { <group_by_expression> } [,...n] )");
    }

    private GroupByBuilderTest builderTest;

    @Before
    public void init(){
        builderTest = new GroupByBuilderTest();
    }

    @Test
    public void testPrintA() throws Exception {
        GroupBy groupBy = builderTest.exampleA;

        StringWriter writer = ReferenceBlockPrinter.print(groupBy);
        String check = writer.toString()
                .replace(" ","")
                .replace("\n","");

        String ok = "GROUP BY SalesOrderID";
        ok = ok.replaceAll(" ","");
        Assert.assertEquals(
                check,
                ok);
    }

    @Test
    public void testPrintB() throws Exception {
        GroupBy groupBy = builderTest.exampleB;

        StringWriter writer = ReferenceBlockPrinter.print(groupBy);
        String check = writer.toString()
                .replace(" ","")
                .replace("\n","");

        String ok = "GROUP BY a.City";
        ok = ok.replaceAll(" ","");
        Assert.assertEquals(
                check,
                ok);
    }

    @Test
    public void testPrintC() throws Exception {
        GroupBy groupBy = builderTest.exampleC;

        StringWriter writer = ReferenceBlockPrinter.print(groupBy);
        String check = writer.toString()
                .replace(" ","")
                .replace("\n","");

        String ok = "GROUP BY DATEPART(yyyy,OrderDate)";
        ok = ok.replaceAll(" ","");
        Assert.assertEquals(
                check,
                ok);
    }
}