package com.xy.xsql.block.tsql.core.clause.select;

import com.xy.xsql.block.core.ReferenceBlockPrinter;
import com.xy.xsql.block.model.ReferenceBlock;
import org.junit.Assert;
import org.junit.Test;

import java.io.StringWriter;

import static org.junit.Assert.*;

/**
 * Created by xiaoyao9184 on 2017/6/20.
 */
public class GroupByConverterTest {

    @Test
    public void test() throws Exception {
        ReferenceBlock b = GroupByConverter.meta();

        StringWriter writer = new ReferenceBlockPrinter()
                .print(b);

        System.out.print(writer);
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

        System.out.print(writer);
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

        System.out.print(writer);
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

        System.out.print(writer);
        Assert.assertEquals(writer.toString(),
                "<grouping_set_item> ::=\n" +
                        "group_by_expression\n" +
                        "| ROLLUP ( { <group_by_expression> } [,...n] )\n" +
                        "| CUBE ( { <group_by_expression> } [,...n] )");
    }

}