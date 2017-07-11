package com.xy.xsql.block.tsql.core.clause;

import com.xy.xsql.block.core.MetaContextBlockPrinter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.core.clause.FromBuilderTest;
import com.xy.xsql.tsql.model.clause.From;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.StringWriter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by xiaoyao9184 on 2017/6/20.
 */
public class FromConverterTest {

    @Test
    public void test() throws Exception {
        BlockMeta b = FromConverter.meta;

        StringWriter writer = new MetaContextBlockPrinter()
                .print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<FROM> ::=\n" +
                        "FROM { <table_source> } [,...n]");
    }

    @Test
    public void testTableSource() throws Exception {
        BlockMeta b = FromConverter.TableSourceConverter.meta;

        StringWriter writer = new MetaContextBlockPrinter()
                .print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<table_source> ::=\n" +
                        "table_or_view_name [ [ AS ] table_alias ] [ <tablesample_clause> ] \n" +
                        "[ WITH ( <table_hint> [ [, ]...n ] ) ]\n" +
                        "| derived_table [ [ AS ] table_alias ] [ ( column_alias [,...n] ) ]\n" +
                        "| <joined_table>\n" +
                        "| @variable [ [ AS ] table_alias ]\n" +
                        "| table_or_view_name FOR SYSTEM_TIME <system_time>");
    }

    @Test
    public void testBaseTable() throws Exception {
        BlockMeta b = FromConverter.BaseTableConverter.meta;

        StringWriter writer = new MetaContextBlockPrinter()
                .print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "table_or_view_name [ [ AS ] table_alias ] [ <tablesample_clause> ] \n" +
                        "[ WITH ( <table_hint> [ [, ]...n ] ) ]");
    }

    @Test
    public void testDerivedTable() throws Exception {
        BlockMeta b = FromConverter.DerivedTableConverter.meta;

        StringWriter writer = new MetaContextBlockPrinter()
                .print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "derived_table [ [ AS ] table_alias ] [ ( column_alias [,...n] ) ]");
    }

    @Test
    public void testJoinedTable() throws Exception {
        BlockMeta b = FromConverter.JoinedTableConverter.meta;

        StringWriter writer = new MetaContextBlockPrinter()
                .print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<joined_table> ::=\n" +
                        "<table_source> <join_type> <table_source> ON <search_condition>\n" +
                        "| <table_source> CROSS JOIN <table_source>\n" +
                        "| left_table_source { CROSS | OUTER } APPLY right_table_source\n" +
                        "| [ ( ] <joined_table> [ ) ]");
    }

    @Test
    public void testVariableTable() throws Exception {
        BlockMeta b = FromConverter.VariableTableConverter.meta;

        StringWriter writer = new MetaContextBlockPrinter()
                .print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "@variable [ [ AS ] table_alias ]");
    }


    @Test
    public void testJoinType() throws Exception {
        BlockMeta b = FromConverter.JoinTypeConverter.meta;

        StringWriter writer = new MetaContextBlockPrinter()
                .print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<join_type> ::=\n" +
                        "[ { INNER | { { LEFT | RIGHT | FULL } [ OUTER ] } } [ <join_hint> ] ]\n" +
                        "JOIN");
    }


    @Test
    public void testSystemTime() throws Exception {
        BlockMeta b = FromConverter.SystemTimeConverter.meta;

        StringWriter writer = new MetaContextBlockPrinter()
                .print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<system_time> ::=\n" +
                        "{\n" +
                        "AS OF <date_time>\n" +
                        "| FROM <start_date_time> TO <end_date_time>\n" +
                        "| BETWEEN <start_date_time> AND <end_date_time>\n" +
                        "| CONTAINED IN ( <start_date_time> , <end_date_time> )\n" +
                        "| ALL\n" +
                        "}");
    }


    @Test
    public void testDateTime() throws Exception {
        BlockMeta b = FromConverter.DateTimeConverter.meta;

        StringWriter writer = new MetaContextBlockPrinter()
                .print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<date_time> ::=\n" +
                        "<date_time_literal> | @date_time_variable");
    }


    private Map<From,String> model2StringMap;

    @Before
    public void init(){
        FromBuilderTest builderTest = new FromBuilderTest();
        model2StringMap = new LinkedHashMap<>();

        model2StringMap.put(
                builderTest.exampleA,
                "FROM Sales.SalesTerritory");

        model2StringMap.put(
                builderTest.exampleB,
                "FROM HumanResources.Employee WITH (TABLOCK, HOLDLOCK)");

        model2StringMap.put(
                builderTest.exampleC,
                "FROM HumanResources.Employee AS e\n" +
                        "     CROSS JOIN HumanResources.Department AS d");

        model2StringMap.put(
                builderTest.exampleD,
                "FROM Production.Product AS p\n" +
                        "     FULL OUTER JOIN Sales.SalesOrderDetail AS sod\n" +
                        "     ON p.ProductID = sod.ProductID");

        model2StringMap.put(
                builderTest.exampleE,
                "FROM Production.Product AS p\n" +
                        "     LEFT OUTER JOIN Sales.SalesOrderDetail AS sod\n" +
                        "     ON p.ProductID = sod.ProductID");

        model2StringMap.put(
                builderTest.exampleF,
                "FROM Production.Product AS p  \n" +
                        "     INNER JOIN Sales.SalesOrderDetail AS sod  \n" +
                        "     ON p.ProductID = sod.ProductID");

        model2StringMap.put(
                builderTest.exampleG,
                "FROM Sales.SalesTerritory AS st\n" +
                        "     RIGHT OUTER JOIN Sales.SalesPerson AS sp\n" +
                        "     ON st.TerritoryID = sp.TerritoryID");

        model2StringMap.put(
                builderTest.exampleH,
                "FROM Production.Product AS p\n" +
                        "     INNER MERGE JOIN Purchasing.ProductVendor AS pv\n" +
                        "     ON p.ProductID = pv.ProductID\n" +
                        "     INNER HASH JOIN Purchasing.Vendor AS v\n" +
                        "     ON pv.BusinessEntityID = v.BusinessEntityID");

        //TODO derived table ()
//        model2StringMap.put(
//                builderTest.exampleI,
//                "FROM Person.Person AS p\n" +
//                        "     INNER JOIN HumanResources.Employee e ON p.BusinessEntityID = e.BusinessEntityID\n" +
//                        "     INNER JOIN\n" +
//                        "     (SELECT bea.BusinessEntityID, a.City\n" +
//                        "     FROM Person.Address AS a\n" +
//                        "     INNER JOIN Person.BusinessEntityAddress AS bea\n" +
//                        "     ON a.AddressID = bea.AddressID) AS d\n" +
//                        "     ON p.BusinessEntityID = d.BusinessEntityID");

        model2StringMap.put(
                builderTest.exampleJ,
                "FROM Sales.Customer TABLESAMPLE SYSTEM (10 PERCENT)");

        //TODO rowset_function
//        model2StringMap.put(
//                builderTest.exampleK,
//                "FROM Departments d CROSS APPLY dbo.GetReports(d.DeptMgrID)");

//        model2StringMap.put(
//                builderTest.exampleL,
//                "FROM sys.dm_exec_cached_plans AS cp   \n" +
//                        "CROSS APPLY sys.dm_exec_query_plan(cp.plan_handle)");

        model2StringMap.put(
                builderTest.exampleM1,
                "FROM DEPARTMENT\n" +
                        "     FOR SYSTEM_TIME AS OF '2014-01-01'");

        model2StringMap.put(
                builderTest.exampleM2,
                "FROM DEPARTMENT\n" +
                        "     FOR SYSTEM_TIME FROM '2013-01-01' TO '2014-01-01'");

        model2StringMap.put(
                builderTest.exampleM3,
                "FROM DEPARTMENT\n" +
                        "     FOR SYSTEM_TIME BETWEEN '2013-01-01' AND '2014-01-01'");

        model2StringMap.put(
                builderTest.exampleM4,
                "FROM DEPARTMENT\n" +
                        "     FOR SYSTEM_TIME CONTAINED IN ( '2013-01-01', '2014-01-01' )");

        model2StringMap.put(
                builderTest.exampleM5,
                "FROM DEPARTMENT\n" +
                        "     FOR SYSTEM_TIME FROM @AsOfFrom TO @AsOfTo");

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