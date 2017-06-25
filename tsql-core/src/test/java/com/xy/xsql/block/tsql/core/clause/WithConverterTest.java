package com.xy.xsql.block.tsql.core.clause;

import com.xy.xsql.block.core.ReferenceBlockPrinter;
import com.xy.xsql.block.model.ReferenceBlock;
import org.junit.Assert;
import org.junit.Test;

import java.io.StringWriter;

/**
 * Created by xiaoyao9184 on 2017/6/20.
 */
public class WithConverterTest {

    @Test
    public void test() throws Exception {
        ReferenceBlock b = WithConverter.meta();

        StringWriter writer = new ReferenceBlockPrinter()
                .print(b);

        System.out.print(writer);
        Assert.assertEquals(writer.toString(),
                "<WITH common_table_expression> ::=\n" +
                        "WITH <common_table_expression> [,...n]");
    }

    @Test
    public void testCommonTableExpression() throws Exception {
        ReferenceBlock b = WithConverter.CommonTableExpressionConverter.meta();

        StringWriter writer = new ReferenceBlockPrinter()
                .print(b);

        System.out.print(writer);
        Assert.assertEquals(writer.toString(),
                "<common_table_expression> ::=\n" +
                        "expression_name [ ( column_name [,...n] ) ] \n" +
                        "AS \n" +
                        "( CTE_query_definition )");
    }

}