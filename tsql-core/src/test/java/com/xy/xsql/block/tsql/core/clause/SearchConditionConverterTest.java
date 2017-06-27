package com.xy.xsql.block.tsql.core.clause;

import com.xy.xsql.block.core.ReferenceBlockPrinter;
import com.xy.xsql.block.model.ReferenceBlock;
import org.junit.Assert;
import org.junit.Test;

import java.io.StringWriter;

import static org.junit.Assert.*;

/**
 * Created by xiaoyao9184 on 2017/6/20.
 */
public class SearchConditionConverterTest {

    @Test
    public void test() throws Exception {
        ReferenceBlock b = SearchConditionConverter.meta();

        StringWriter writer = new ReferenceBlockPrinter()
                .print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<search_condition> ::=\n" +
                        "{ [ NOT ] <predicate> | ( <search_condition> ) }\n" +
                        "[ { AND | OR } [ NOT ] { <predicate> | ( <search_condition> ) } ] [,...n]");
    }

    @Test
    public void testAndOrNotItem() throws Exception {
        ReferenceBlock b = SearchConditionConverter.AndOrNotItemConverter.meta();

        StringWriter writer = new ReferenceBlockPrinter()
                .print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "[ { AND | OR } [ NOT ] { <predicate> | ( <search_condition> ) } ]");
    }

    @Test
    public void testPredicate() throws Exception {
        ReferenceBlock b = SearchConditionConverter.PredicateConverter.meta();

        StringWriter writer = new ReferenceBlockPrinter()
                .print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<predicate> ::=\n" +
                        "expression { = | < > | ! = | > | > = | ! > | < | < = | ! < } expression\n" +
                        "| string_expression [ NOT ] LIKE string_expression [ ESCAPE 'escape_character' ]\n" +
                        "| expression [ NOT ] BETWEEN { expression } AND expression\n" +
                        "| expression IS [ NOT ] NULL\n" +
                        "| CONTAINS ( { column_name | ( column_list ) | * | PROPERTY ( { column_name } , 'property_name' ) } , '<contains_search_condition>' , [ LANGUAGE language_term ] )\n" +
                        "| FREETEXT ( { column_name | ( column_list ) | * } , 'freetext_string' , [ LANGUAGE language_term ] )\n" +
                        "| expression [ NOT ] IN ( subquery | { expression } [,...n] )\n" +
                        "| expression { = | < > | ! = | > | > = | ! > | < | < = | ! < } { ALL | SOME | ANY } ( subquery )\n" +
                        "| EXISTS ( subquery )");
    }
}