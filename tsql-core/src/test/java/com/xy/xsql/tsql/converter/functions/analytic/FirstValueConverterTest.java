package com.xy.xsql.tsql.converter.functions.analytic;

import com.xy.xsql.block.core.printer.MetaBlockPrinter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.builder.chain.functions.analytic.FirstValueFunctionTest;
import com.xy.xsql.tsql.converter.BaseTest;
import com.xy.xsql.tsql.model.functions.analytic.First_Value;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.StringWriter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by xiaoyao9184 on 2018/5/31.
 */
public class FirstValueConverterTest {

    @Test
    public void testMetaPrint() throws Exception {
        BlockMeta b = FirstValueConverter.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<FIRST_VALUE> ::=\n" +
                        "FIRST_VALUE ( [ scalar_expression ] )\n" +
                        "\tOVER ( [ partition_by_clause ] order_by_clause [ rows_range_clause ] )");
    }

    private Map<First_Value,String> model2StringMap;

    @Before
    public void init(){
        FirstValueFunctionTest builderTest = new FirstValueFunctionTest();
        model2StringMap = new LinkedHashMap<>();

        model2StringMap.put(
                builderTest.exampleA,
                "FIRST_VALUE(Name) OVER (ORDER BY ListPrice ASC)");
        model2StringMap.put(
                builderTest.exampleB,
                "FIRST_VALUE(LastName) OVER (PARTITION BY JobTitle\n" +
                        "     ORDER BY VacationHours ASC\n" +
                        "     ROWS UNBOUNDED PRECEDING\n" +
                        "     )");
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