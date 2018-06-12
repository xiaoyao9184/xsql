package com.xy.xsql.tsql.converter.functions.ranking;

import com.xy.xsql.block.core.printer.MetaBlockPrinter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.builder.chain.functions.ranking.RankFunctionTest;
import com.xy.xsql.tsql.converter.BaseTest;
import com.xy.xsql.tsql.model.functions.ranking.Rank;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.StringWriter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by xiaoyao9184 on 2018/5/31.
 */
public class RankConverterTest {

    @Test
    public void testMetaPrint() throws Exception {
        BlockMeta b = RankConverter.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<RANK> ::=\n" +
                        "RANK ( ) OVER ( [ partition_by_clause ] order_by_clause )");
    }

    private Map<Rank,String> model2StringMap;

    @Before
    public void init(){
        RankFunctionTest builderTest = new RankFunctionTest();
        model2StringMap = new LinkedHashMap<>();

        model2StringMap.put(
                builderTest.exampleA,
                "RANK() OVER\n" +
                        "     (PARTITION BY i.LocationID ORDER BY i.Quantity DESC)");
        model2StringMap.put(
                builderTest.exampleB,
                "RANK() OVER (ORDER BY Rate DESC)");
        model2StringMap.put(
                builderTest.exampleC,
                "RANK() OVER (PARTITION BY SalesTerritoryRegion ORDER BY SUM(SalesAmountQuota) DESC )");
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