package com.xy.xsql.tsql.converter.functions.ranking;

import com.xy.xsql.block.core.printer.MetaBlockPrinter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.builder.chain.functions.ranking.RowNumberFunctionTest;
import com.xy.xsql.tsql.converter.BaseTest;
import com.xy.xsql.tsql.model.functions.ranking.Row_Number;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.StringWriter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by xiaoyao9184 on 2018/5/31.
 */
public class RowNumberConverterTest {

    @Test
    public void testMetaPrint() throws Exception {
        BlockMeta b = RowNumberConverter.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<ROW_NUMBER> ::=\n" +
                        "ROW_NUMBER ( ) OVER ( [ partition_by_clause ] order_by_clause )");
    }

    private Map<Row_Number,String> model2StringMap;

    @Before
    public void init(){
        RowNumberFunctionTest builderTest = new RowNumberFunctionTest();
        model2StringMap = new LinkedHashMap<>();

        model2StringMap.put(
                builderTest.exampleA1,
                "ROW_NUMBER() OVER(ORDER BY name ASC)");
        model2StringMap.put(
                builderTest.exampleA2,
                "ROW_NUMBER() OVER(PARTITION BY recovery_model_desc ORDER BY name ASC)");
        model2StringMap.put(
                builderTest.exampleB,
                "ROW_NUMBER() OVER(ORDER BY SalesYTD DESC)");
        model2StringMap.put(
                builderTest.exampleC,
                "ROW_NUMBER() OVER (ORDER BY OrderDate)");
        model2StringMap.put(
                builderTest.exampleD,
                "ROW_NUMBER() OVER(PARTITION BY TerritoryName ORDER BY SalesYTD DESC)");
        model2StringMap.put(
                builderTest.exampleE,
                "ROW_NUMBER() OVER(ORDER BY SUM(SalesAmountQuota) DESC)");
        model2StringMap.put(
                builderTest.exampleF,
                "ROW_NUMBER() OVER(PARTITION BY SalesTerritoryKey\n" +
                        "     ORDER BY SUM(SalesAmountQuota) DESC)");
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