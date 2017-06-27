package com.xy.xsql.block.tsql.core.clause.hints;

import com.xy.xsql.block.core.ReferenceBlockPrinter;
import com.xy.xsql.block.model.ReferenceBlock;
import com.xy.xsql.block.tsql.core.clause.OptionConverter;
import com.xy.xsql.tsql.model.clause.hints.QueryHint;
import org.junit.Assert;
import org.junit.Test;

import java.io.StringWriter;

import static com.xy.xsql.tsql.core.clause.hints.JoinHintBuilder.HASH;
import static com.xy.xsql.tsql.core.clause.hints.QueryHintBuilder.MERGE_JOIN;
import static com.xy.xsql.tsql.core.clause.hints.QueryHintBuilder.OPTIMIZE_FOR;
import static com.xy.xsql.tsql.core.clause.hints.QueryHintBuilder.OptimizeForBuilder.OPTIMIZE_FOR_Item;
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
                        "| EXPAND_VIEWS\n" +
                        "| FAST number_rows\n" +
                        "| FORCE_ORDER\n" +
                        "| { FORCE | DISABLE } EXTERNALPUSHDOWN\n" +
                        "| IGNORE_NONCLUSTERED_COLUMNSTORE_INDEX\n" +
                        "| KEEP_PLAN\n" +
                        "| KEEPFIXED_PLAN\n" +
                        "| MAX_GRANT_PERCENT = percent\n" +
                        "| MIN_GRANT_PERCENT = percent\n" +
                        "| MAXDOP number_of_processors\n" +
                        "| MAXRECURSION number\n" +
                        "| NO_PERFORMANCE_SPOOL\n" +
                        "| OPTIMIZE_FOR ( @variable_name { UNKNOWN | = literal_constant } [,...n] )\n" +
                        "| OPTIMIZE_FOR_UNKNOWN\n" +
                        "| PARAMETERIZATION { SIMPLE | SIMPLE }\n" +
                        "| RECOMPILE\n" +
                        "| ROBUST_PLAN\n" +
                        "| USE_HINT ( '<hint_name>' [,...n] )\n" +
                        "| USE_PLAN N'xml_plan'\n" +
                        "| TABLE_HINT ( exposed_object_name [ , <table_hint> [ [, ]...n ] ] )");
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
}