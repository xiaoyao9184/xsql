package com.xy.xsql.block.tsql.core.statement.dml;

import com.xy.xsql.block.core.ReferenceBlockPrinter;
import com.xy.xsql.block.model.ReferenceBlock;
import com.xy.xsql.block.tsql.core.statement.ddl.ReNameDatabaseConverter;
import org.junit.Assert;
import org.junit.Test;

import java.io.StringWriter;

import static org.junit.Assert.*;

/**
 * Created by xiaoyao9184 on 2017/6/17.
 */
public class SelectConverterTest {

    @Test
    public void test() throws Exception {
        ReferenceBlock b = SelectConverter.meta();

        StringWriter writer = new ReferenceBlockPrinter()
                .print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<SELECT statement> ::=\n" +
                        "[ [ WITH { [ XMLNAMESPACES ,] [ <common_table_expression> [,...n] ] } ] ]\n" +
                        "<query_expression>\n" +
                        "[ ORDER BY { order_by_expression | column_position [ ASC | DESC ] } [ ,...n ] ]\n" +
                        "[ <FOR Clause> ]\n" +
                        "[ OPTION ( <query_hint> [ ,...n ] ) ]");
    }

    @Test
    public void testQueryExpression() throws Exception {
        ReferenceBlock b = SelectConverter.QueryExpressionConverter.meta();

        StringWriter writer = new ReferenceBlockPrinter()
                .print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<query_expression> ::=\n" +
                        "{ <query_specification> | ( <query_expression> ) }\n" +
                        "[ { UNION [ ALL ] | EXCEPT | INTERSECT }\n" +
                        "<query_specification> | ( <query_expression> ) ] [,...n]");
    }

    @Test
    public void testQuerySpecification() throws Exception {
        ReferenceBlock b = SelectConverter.QuerySpecificationConverter.meta();

        StringWriter writer = new ReferenceBlockPrinter()
                .print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<query_specification> ::=\n" +
                        "SELECT\n" +
                        "[ ALL | DISTINCT ]\n" +
                        "[ TOP ( expression ) [ PERCENT ] ]\n" +
                        "<select_list>\n" +
                        "[ INTO new_table ]\n" +
                        "[ FROM { <table_source> } [ ,...n ] ]\n" +
                        "[ WHERE <search_condition> ]\n" +
                        "[ <GROUP BY> ]\n" +
                        "[ HAVING < search_condition > ]");
    }

    @Test
    public void testUnionItem() throws Exception {
        ReferenceBlock b = SelectConverter.UnionItemConverter.meta();

        StringWriter writer = new ReferenceBlockPrinter()
                .print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "{ UNION [ ALL ] | EXCEPT | INTERSECT }\n" +
                        "<query_specification> | ( <query_expression> )");
    }


}