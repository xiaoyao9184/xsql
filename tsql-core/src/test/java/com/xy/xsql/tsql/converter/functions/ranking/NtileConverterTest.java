package com.xy.xsql.tsql.converter.functions.ranking;

import com.xy.xsql.block.core.printer.MetaBlockPrinter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.builder.chain.functions.ranking.NtileFunctionTest;
import com.xy.xsql.tsql.converter.BaseTest;
import com.xy.xsql.tsql.model.functions.ranking.Ntile;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.StringWriter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by xiaoyao9184 on 2018/5/31.
 */
public class NtileConverterTest {

    @Test
    public void testMetaPrint() throws Exception {
        BlockMeta b = NtileConverter.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<NTILE> ::=\n" +
                        "NTILE ( integer_expression ) OVER ( [ <partition_by_clause> ] <order_by_clause> )");
    }

    private Map<Ntile,String> model2StringMap;

    @Before
    public void init(){
        NtileFunctionTest builderTest = new NtileFunctionTest();
        model2StringMap = new LinkedHashMap<>();

        model2StringMap.put(
                builderTest.exampleA,
                "NTILE(4) OVER(ORDER BY SalesYTD DESC)");
        model2StringMap.put(
                builderTest.exampleB,
                "NTILE(@NTILE_Var) OVER(PARTITION BY PostalCode ORDER BY SalesYTD DESC)");
        model2StringMap.put(
                builderTest.exampleC,
                "NTILE(4) OVER(ORDER BY SUM(SalesAmountQuota) DESC)");
        model2StringMap.put(
                builderTest.exampleD,
                "NTILE(2) OVER(PARTITION BY e.SalesTerritoryKey ORDER BY SUM(SalesAmountQuota))");
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