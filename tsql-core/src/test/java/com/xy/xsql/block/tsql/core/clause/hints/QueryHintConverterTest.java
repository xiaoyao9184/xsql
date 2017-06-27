package com.xy.xsql.block.tsql.core.clause.hints;

import com.xy.xsql.block.core.ReferenceBlockPrinter;
import com.xy.xsql.block.model.ReferenceBlock;
import com.xy.xsql.block.tsql.core.clause.OptionConverter;
import org.junit.Assert;
import org.junit.Test;

import java.io.StringWriter;

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
}