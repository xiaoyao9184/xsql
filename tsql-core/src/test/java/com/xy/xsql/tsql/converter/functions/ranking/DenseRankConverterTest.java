package com.xy.xsql.tsql.converter.functions.ranking;

import com.xy.xsql.block.core.printer.MetaBlockPrinter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.builder.chain.functions.ranking.DenseRankFunctionTest;
import com.xy.xsql.tsql.converter.BaseTest;
import com.xy.xsql.tsql.model.functions.ranking.Dense_Rank;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.StringWriter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by xiaoyao9184 on 2018/5/31.
 */
public class DenseRankConverterTest {

    @Test
    public void testMetaPrint() throws Exception {
        BlockMeta b = DenseRankConverter.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<DENSE_RANK> ::=\n" +
                        "DENSE_RANK ( ) OVER ( [ <partition_by_clause> ] <order_by_clause> )");
    }

    private Map<Dense_Rank,String> model2StringMap;

    @Before
    public void init(){
        DenseRankFunctionTest builderTest = new DenseRankFunctionTest();
        model2StringMap = new LinkedHashMap<>();

        model2StringMap.put(
                builderTest.exampleA,
                "DENSE_RANK() OVER\n" +
                        "     (PARTITION BY i.LocationID ORDER BY i.Quantity DESC)");
        model2StringMap.put(
                builderTest.exampleB,
                "DENSE_RANK() OVER (ORDER BY Rate DESC)");
        model2StringMap.put(
                builderTest.exampleC,
                "DENSE_RANK() OVER (ORDER BY a.PostalCode)");
        model2StringMap.put(
                builderTest.exampleD,
                "DENSE_RANK() OVER (PARTITION BY SalesTerritoryGroup ORDER BY SUM(SalesAmountQuota) DESC )");
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