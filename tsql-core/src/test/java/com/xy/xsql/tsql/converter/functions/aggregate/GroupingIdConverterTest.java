package com.xy.xsql.tsql.converter.functions.aggregate;

import com.xy.xsql.block.core.converter.ModelKeywordBlockConverter;
import com.xy.xsql.block.core.printer.MetaBlockPrinter;
import com.xy.xsql.block.core.printer.ModelMetaBlockPrinter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.builder.chain.functions.aggregate.GroupingIdFunctionTest;
import com.xy.xsql.tsql.model.functions.aggregate.Grouping_Id;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.StringWriter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by xiaoyao9184 on 2018/5/30.
 */
public class GroupingIdConverterTest {

    @Test
    public void testMetaPrint() throws Exception {
        BlockMeta b = GroupingIdConverter.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<GROUPING_ID> ::=\n" +
                        "GROUPING_ID ( <column_expression> [,...n] )");
    }

    private Map<Grouping_Id,String> model2StringMap;

    @Before
    public void init(){
        GroupingIdFunctionTest builderTest = new GroupingIdFunctionTest();
        model2StringMap = new LinkedHashMap<>();

        model2StringMap.put(
                builderTest.exampleA,
                "GROUPING_ID(D.Name, E.JobTitle)");
        model2StringMap.put(
                builderTest.exampleB,
                "GROUPING_ID((T.[Group])\n" +
                        "     ,(T.CountryRegionCode),(S.Name),(H.SalesPersonID)\n" +
                        "     )");
        model2StringMap.put(
                builderTest.exampleC,
                "GROUPING_ID(DATEPART(yyyy,OrderDate)\n" +
                        "     ,DATEPART(mm,OrderDate)\n" +
                        "     ,DATEPART(dd,OrderDate))");
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
            System.out.println("==========");

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