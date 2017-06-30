package com.xy.xsql.block.tsql.core.clause.hints;

import com.xy.xsql.block.core.ReferenceBlockPrinter;
import com.xy.xsql.block.model.ReferenceBlock;
import com.xy.xsql.block.tsql.core.clause.OptionConverter;
import com.xy.xsql.tsql.model.clause.hints.QueryHint;
import org.junit.Assert;
import org.junit.Test;

import java.io.StringWriter;

import static com.xy.xsql.tsql.core.clause.hints.JoinHintBuilder.HASH;
import static com.xy.xsql.tsql.core.clause.hints.QueryHintBuilder.*;
import static com.xy.xsql.tsql.core.clause.hints.QueryHintBuilder.OptimizeForBuilder.OPTIMIZE_FOR_Item;
import static com.xy.xsql.tsql.core.clause.hints.TableHintBuilder.FORCESEEK;
import static com.xy.xsql.tsql.core.clause.hints.TableHintBuilder.INDEX;
import static com.xy.xsql.tsql.core.clause.hints.TableHintBuilder.NOLOCK;
import static org.junit.Assert.*;

/**
 * Created by xiaoyao9184 on 2017/6/20.
 */
public class QueryHintConverterTest {

    @Test
    public void test() throws Exception {
        ReferenceBlock b = QueryHintConverter.meta();

        StringWriter writer = new ReferenceBlockPrinter()
                .print(b);

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

    @Test
    public void testPrintA() throws Exception {
        StringWriter writer = ReferenceBlockPrinter.print(MERGE_JOIN());

        String ok = "MERGE JOIN";
        ok = ok.replaceAll(" ","");
        Assert.assertEquals(writer.toString().replace(" ",""),
                ok);
    }

    @Test
    public void testPrintB() throws Exception {
        QueryHint queryHint = OPTIMIZE_FOR(
                OPTIMIZE_FOR_Item("city_name",false,"Seattle"),
                OPTIMIZE_FOR_Item("postal_code",true,null));

        StringWriter writer = ReferenceBlockPrinter.print(queryHint);
        String check = writer.toString()
                .replace(" ","")
                .replace("\n","");

        String ok = "OPTIMIZE FOR (@city_name = 'Seattle', @postal_code UNKNOWN)";
        ok = ok.replaceAll(" ","");
        Assert.assertEquals(
                check,
                ok);
    }

    @Test
    public void testPrintC() throws Exception {
        QueryHint queryHint = MAXRECURSION(2);

        StringWriter writer = ReferenceBlockPrinter.print(queryHint);
        String check = writer.toString()
                .replace(" ","")
                .replace("\n","");

        String ok = "MAXRECURSION 2";
        ok = ok.replaceAll(" ","");
        Assert.assertEquals(
                check,
                ok);
    }

    @Test
    public void testPrintD() throws Exception {
        QueryHint queryHint = MERGE_UNION();

        StringWriter writer = ReferenceBlockPrinter.print(queryHint);
        String check = writer.toString()
                .replace(" ","")
                .replace("\n","");

        String ok = "MERGE UNION";
        ok = ok.replaceAll(" ","");
        Assert.assertEquals(
                check,
                ok);
    }

    @Test
    public void testPrintE1() throws Exception {
        QueryHint queryHint = HASH_GROUP();

        StringWriter writer = ReferenceBlockPrinter.print(queryHint);
        String check = writer.toString()
                .replace(" ","")
                .replace("\n","");

        String ok = "HASH GROUP";
        ok = ok.replaceAll(" ","");
        Assert.assertEquals(
                check,
                ok);
    }

    @Test
    public void testPrintE2() throws Exception {
        QueryHint queryHint = FAST(10);

        StringWriter writer = ReferenceBlockPrinter.print(queryHint);
        String check = writer.toString()
                .replace(" ","")
                .replace("\n","");

        String ok = "FAST 10";
        ok = ok.replaceAll(" ","");
        Assert.assertEquals(
                check,
                ok);
    }

    @Test
    public void testPrintF() throws Exception {
        QueryHint queryHint = MAXDOP(2);

        StringWriter writer = ReferenceBlockPrinter.print(queryHint);
        String check = writer.toString()
                .replace(" ","")
                .replace("\n","");

        String ok = "MAXDOP 2";
        ok = ok.replaceAll(" ","");
        Assert.assertEquals(
                check,
                ok);
    }

    @Test
    public void testPrintG1() throws Exception {
        QueryHint queryHint = TABLE_HINT(
                "e",
                INDEX("IX_Employee_ManagerID"));

        StringWriter writer = ReferenceBlockPrinter.print(queryHint);
        String check = writer.toString()
                .replace(" ", "")
                .replace("\n", "");

        //TODO make sure INDEX '=' omitted
        String ok = "TABLE HINT(e, INDEX = (IX_Employee_ManagerID))";
        ok = ok.replaceAll(" ", "");
        Assert.assertEquals(
                check,
                ok);
    }

    @Test
    public void testPrintG2() throws Exception {
        QueryHint queryHint = TABLE_HINT(
                "e",
                INDEX("PK_Employee_EmployeeID","IX_Employee_ManagerID"));

        StringWriter writer = ReferenceBlockPrinter.print(queryHint);
        String check = writer.toString()
                .replace(" ","")
                .replace("\n","");

        String ok = "TABLE HINT(e, INDEX(PK_Employee_EmployeeID, IX_Employee_ManagerID))";
        ok = ok.replaceAll(" ","");
        Assert.assertEquals(
                check,
                ok);
    }

    @Test
    public void testPrintH() throws Exception {
        QueryHint queryHint = TABLE_HINT(
                "HumanResources.Employee",
                FORCESEEK());

        StringWriter writer = ReferenceBlockPrinter.print(queryHint);
        String check = writer.toString()
                .replace(" ","")
                .replace("\n","");

        String ok = "TABLE HINT( HumanResources.Employee, FORCESEEK)";
        ok = ok.replaceAll(" ","");
        Assert.assertEquals(
                check,
                ok);
    }

    @Test
    public void testPrintI1() throws Exception {
        QueryHint queryHint = TABLE_HINT(
                "e",
                INDEX("IX_Employee_ManagerID"));

        StringWriter writer = ReferenceBlockPrinter.print(queryHint);
        String check = writer.toString()
                .replace(" ","")
                .replace("\n","");

        String ok = "TABLE HINT (e, INDEX = ( IX_Employee_ManagerID))";
        ok = ok.replaceAll(" ","");
        Assert.assertEquals(
                check,
                ok);
    }

    @Test
    public void testPrintI2() throws Exception {
        QueryHint queryHint = TABLE_HINT(
                "c",
                FORCESEEK());

        StringWriter writer = ReferenceBlockPrinter.print(queryHint);
        String check = writer.toString()
                .replace(" ","")
                .replace("\n","");

        String ok = "TABLE HINT (c, FORCESEEK)";
        ok = ok.replaceAll(" ","");
        Assert.assertEquals(
                check,
                ok);
    }

    @Test
    public void testPrintJ() throws Exception {
        QueryHint queryHint = TABLE_HINT(
                "e");

        StringWriter writer = ReferenceBlockPrinter.print(queryHint);
        String check = writer.toString()
                .replace(" ","")
                .replace("\n","");

        String ok = "TABLE HINT(e)";
        ok = ok.replaceAll(" ","");
        Assert.assertEquals(
                check,
                ok);
    }

    @Test
    public void testPrintK1() throws Exception {
        QueryHint queryHint = TABLE_HINT(
                "e",
                INDEX("IX_Employee_ManagerID"),
                NOLOCK(),
                FORCESEEK());

        StringWriter writer = ReferenceBlockPrinter.print(queryHint);
        String check = writer.toString()
                .replace(" ","")
                .replace("\n","");

        String ok = "TABLE HINT (e, INDEX = (IX_Employee_ManagerID), NOLOCK, FORCESEEK)";
        ok = ok.replaceAll(" ","");
        Assert.assertEquals(
                check,
                ok);
    }

    @Test
    public void testPrintK2() throws Exception {
        QueryHint queryHint = TABLE_HINT(
                "e",
                NOLOCK());

        StringWriter writer = ReferenceBlockPrinter.print(queryHint);
        String check = writer.toString()
                .replace(" ","")
                .replace("\n","");

        String ok = "TABLE HINT (e, NOLOCK)";
        ok = ok.replaceAll(" ","");
        Assert.assertEquals(
                check,
                ok);
    }

    @Test
    public void testPrintL1() throws Exception {
        QueryHint queryHint = RECOMPILE();

        StringWriter writer = ReferenceBlockPrinter.print(queryHint);
        String check = writer.toString()
                .replace(" ","")
                .replace("\n","");

        String ok = "RECOMPILE";
        ok = ok.replaceAll(" ","");
        Assert.assertEquals(
                check,
                ok);
    }

    @Test
    public void testPrintL2() throws Exception {
        QueryHint queryHint = USE_HINT("ASSUME_MIN_SELECTIVITY_FOR_FILTER_ESTIMATES", "DISABLE_PARAMETER_SNIFFING");

        StringWriter writer = ReferenceBlockPrinter.print(queryHint);
        String check = writer.toString()
                .replace(" ","")
                .replace("\n","");

        String ok = "USE HINT ('ASSUME_MIN_SELECTIVITY_FOR_FILTER_ESTIMATES', 'DISABLE_PARAMETER_SNIFFING')";
        ok = ok.replaceAll(" ","");
        Assert.assertEquals(
                check,
                ok);
    }
}