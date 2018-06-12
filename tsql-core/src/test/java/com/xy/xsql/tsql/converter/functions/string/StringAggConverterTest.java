package com.xy.xsql.tsql.converter.functions.string;

import com.xy.xsql.block.core.printer.MetaBlockPrinter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.builder.chain.functions.string.StringAggFunctionTest;
import com.xy.xsql.tsql.converter.BaseTest;
import com.xy.xsql.tsql.model.functions.string.String_Agg;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.StringWriter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by xiaoyao9184 on 2018/5/31.
 */
public class StringAggConverterTest {

    @Test
    public void testMetaPrint() throws Exception {
        BlockMeta b = StringAggConverter.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<STRING_AGG> ::=\n" +
                        "STRING_AGG ( expression , separator ) [ <order_clause> ]");
    }

    @Test
    public void testMetaPrint_OrderBy() throws Exception {
        BlockMeta b = StringAggConverter.metaOrder;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<order_clause> ::=\n" +
                        "WITHIN GROUP ( ORDER BY <order_by_expression_list> [ ASC | DESC ] )");
    }

    private Map<String_Agg,String> model2StringMap;

    @Before
    public void init(){
        StringAggFunctionTest builderTest = new StringAggFunctionTest();
        model2StringMap = new LinkedHashMap<>();

        model2StringMap.put(
                builderTest.exampleA,
                "STRING_AGG (FirstName, CHAR(13))");
        model2StringMap.put(
                builderTest.exampleB,
                "STRING_AGG ( ISNULL(FirstName,'N/A'), ',')");
        model2StringMap.put(
                builderTest.exampleC,
                "STRING_AGG(CONCAT(FirstName, ' ', LastName, ' (', ModifiedDate, ')'), CHAR(13))");
        model2StringMap.put(
                builderTest.exampleD,
                "STRING_AGG (tag, ',')");
        model2StringMap.put(
                builderTest.exampleE,
                "STRING_AGG (email, ';')");
    }

    @Test
    public void testPrint() throws Exception {
        BaseTest.testPrint(model2StringMap);
    }

    @Test
    public void testKeywordPrint() throws Exception {
        BaseTest.testKeywordPrint(model2StringMap);
    }

}