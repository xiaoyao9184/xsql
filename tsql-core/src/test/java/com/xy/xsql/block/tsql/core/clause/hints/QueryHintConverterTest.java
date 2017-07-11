package com.xy.xsql.block.tsql.core.clause.hints;

import com.xy.xsql.block.core.MetaContextBlockPrinter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.core.clause.hint.QueryHintBuilderTest;
import com.xy.xsql.tsql.model.clause.hints.QueryHint;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.StringWriter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by xiaoyao9184 on 2017/6/20.
 */
public class QueryHintConverterTest {

    @Test
    public void test() throws Exception {
        BlockMeta b = QueryHintConverter.meta;

        StringWriter writer = new MetaContextBlockPrinter()
                .printMeta(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<query_hint> ::=\n" +
                        "{ HASH | ORDER } GROUP\n" +
                        "| { CONCAT | HASH | MERGE } UNION\n" +
                        "| { LOOP | MERGE | HASH } JOIN\n" +
                        "| EXPAND VIEWS\n" +
                        "| FAST number_rows\n" +
                        "| FORCE ORDER\n" +
                        "| { FORCE | DISABLE } EXTERNALPUSHDOWN\n" +
                        "| IGNORE_NONCLUSTERED_COLUMNSTORE_INDEX\n" +
                        "| KEEP PLAN\n" +
                        "| KEEPFIXED PLAN\n" +
                        "| MAX_GRANT_PERCENT = percent\n" +
                        "| MIN_GRANT_PERCENT = percent\n" +
                        "| MAXDOP number_of_processors\n" +
                        "| MAXRECURSION number\n" +
                        "| NO_PERFORMANCE_SPOOL\n" +
                        "| OPTIMIZE FOR ( @variable_name { UNKNOWN | = literal_constant } [,...n] )\n" +
                        "| OPTIMIZE FOR UNKNOWN\n" +
                        "| PARAMETERIZATION { SIMPLE | SIMPLE }\n" +
                        "| RECOMPILE\n" +
                        "| ROBUST PLAN\n" +
                        "| USE HINT ( '<hint_name>' [,...n] )\n" +
                        "| USE PLAN N'xml_plan'\n" +
                        "| TABLE HINT ( exposed_object_name [ , <table_hint> [ [, ]...n ] ] )");
    }

    private Map<QueryHint,String> model2StringMap;

    @Before
    public void init(){
        QueryHintBuilderTest builderTest = new QueryHintBuilderTest();
        model2StringMap = new LinkedHashMap<>();

        model2StringMap.put(
                builderTest.exampleA,
                "MERGE JOIN");

        model2StringMap.put(
                builderTest.exampleB,
                "OPTIMIZE FOR (@city_name = 'Seattle', @postal_code UNKNOWN)");

        model2StringMap.put(
                builderTest.exampleC,
                "MAXRECURSION 2");

        model2StringMap.put(
                builderTest.exampleD,
                "MERGE UNION");

        model2StringMap.put(
                builderTest.exampleE1,
                "HASH GROUP");

        model2StringMap.put(
                builderTest.exampleE2,
                "FAST 10");

        model2StringMap.put(
                builderTest.exampleF,
                "MAXDOP 2");

        model2StringMap.put(
                builderTest.exampleG1,
                "TABLE HINT(e, INDEX (IX_Employee_ManagerID))");

        model2StringMap.put(
                builderTest.exampleG2,
                "TABLE HINT(e, INDEX(PK_Employee_EmployeeID, IX_Employee_ManagerID))");

        model2StringMap.put(
                builderTest.exampleH,
                "TABLE HINT( HumanResources.Employee, FORCESEEK)");

        model2StringMap.put(
                builderTest.exampleI1,
                "TABLE HINT (e, INDEX( IX_Employee_ManagerID))");

        model2StringMap.put(
                builderTest.exampleI2,
                "TABLE HINT (c, FORCESEEK)");

        model2StringMap.put(
                builderTest.exampleJ,
                "TABLE HINT(e)");

        model2StringMap.put(
                builderTest.exampleK1,
                "TABLE HINT (e, INDEX(IX_Employee_ManagerID), NOLOCK, FORCESEEK)");

        model2StringMap.put(
                builderTest.exampleK2,
                "TABLE HINT (e, NOLOCK)");

        model2StringMap.put(
                builderTest.exampleL1,
                "RECOMPILE");

        model2StringMap.put(
                builderTest.exampleL2,
                "USE HINT ('ASSUME_MIN_SELECTIVITY_FOR_FILTER_ESTIMATES', 'DISABLE_PARAMETER_SNIFFING')");

    }

    @SuppressWarnings("Duplicates")
    @Test
    public void testPrint() throws Exception {
        final int[] index = {1};
        model2StringMap.forEach((key, value) -> {
            StringWriter writer = MetaContextBlockPrinter.print(key);
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

}