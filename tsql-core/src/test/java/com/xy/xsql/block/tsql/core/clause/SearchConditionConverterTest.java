package com.xy.xsql.block.tsql.core.clause;

import com.xy.xsql.block.core.printer.MetaBlockPrinter;
import com.xy.xsql.block.core.printer.ModelMetaBlockPrinter;
import com.xy.xsql.block.core.converter.ModelKeywordBlockConverter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.builder.chain.queries.SearchConditionBuilderTest;
import com.xy.xsql.tsql.model.queries.SearchCondition;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.StringWriter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by xiaoyao9184 on 2017/6/20.
 */
public class SearchConditionConverterTest {

    @Test
    public void testMetaPrint() throws Exception {
        BlockMeta b = SearchConditionConverter.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<search_condition> ::=\n" +
                        "{ [ NOT ] <predicate> | ( <search_condition> ) }\n" +
                        "[ { AND | OR } [ NOT ] { <predicate> | ( <search_condition> ) } ] [...n]");
    }

    @Test
    public void testMetaPrint_AndOrNotItem() throws Exception {
        BlockMeta b = SearchConditionConverter.AndOrNotItemConverter.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "{ AND | OR } [ NOT ] { <predicate> | ( <search_condition> ) }");
    }

    @Test
    public void testMetaPrint_Predicate() throws Exception {
        BlockMeta b = SearchConditionConverter.PredicateConverter.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

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


    private Map<SearchCondition,String> model2StringMap;

    @Before
    public void init(){
        SearchConditionBuilderTest builderTest = new SearchConditionBuilderTest();
        model2StringMap = new LinkedHashMap<>();

        model2StringMap.put(
                builderTest.exampleA,
                "LargePhotoFileName LIKE '%greena_%' ESCAPE 'a'");

        model2StringMap.put(
                builderTest.exampleB,
                "CountryRegionCode NOT IN ('US')\n" +
                        "     AND City LIKE N'Pa%'");

        model2StringMap.put(
                builderTest.exampleC,
                "LastName LIKE '%and%'");

        model2StringMap.put(
                builderTest.exampleD,
                "LastName LIKE N'%and%'");

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