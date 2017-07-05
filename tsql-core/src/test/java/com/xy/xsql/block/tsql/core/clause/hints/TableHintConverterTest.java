package com.xy.xsql.block.tsql.core.clause.hints;

import com.xy.xsql.block.core.ReferenceBlockPrinter;
import com.xy.xsql.block.model.ReferenceBlock;
import com.xy.xsql.tsql.core.clause.hint.TableHintBuilderTest;
import com.xy.xsql.tsql.model.clause.hints.TableHint;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.StringWriter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by xiaoyao9184 on 2017/6/21.
 */
public class TableHintConverterTest {

    @Test
    public void test() throws Exception {
        ReferenceBlock b = TableHintConverter.meta();

        StringWriter writer = new ReferenceBlockPrinter()
                .print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<table_hint> ::=\n" +
                        "[ NOEXPAND ]\n" +
                        "{\n" +
                        "INDEX ( index_value [,...n] )\n" +
                        "| INDEX = ( index_value )\n" +
                        "| FORCESEEK [ ( index_value ( index_column_name [,...n] ) ) ]\n" +
                        "| FORCESCAN\n" +
                        "| FORCESEEK\n" +
                        "| HOLDLOCK\n" +
                        "| NOLOCK\n" +
                        "| NOWAIT\n" +
                        "| PAGLOCK\n" +
                        "| READCOMMITTED\n" +
                        "| READCOMMITTEDLOCK\n" +
                        "| READPAST\n" +
                        "| READUNCOMMITTED\n" +
                        "| REPEATABLEREAD\n" +
                        "| ROWLOCK\n" +
                        "| SERIALIZABLE\n" +
                        "| SNAPSHOT\n" +
                        "| SNAPSHOT\n" +
                        "| SPATIAL_WINDOW_MAX_CELLS = integer\n" +
                        "| TABLOCK\n" +
                        "| TABLOCKX\n" +
                        "| UPDLOCK\n" +
                        "| XLOCK\n" +
                        "}");
    }

    private Map<TableHint,String> model2StringMap;

    @Before
    public void init(){
        TableHintBuilderTest builderTest = new TableHintBuilderTest();
        model2StringMap = new LinkedHashMap<>();

        model2StringMap.put(
                builderTest.exampleA,
                "TABLOCK");

        model2StringMap.put(
                builderTest.exampleB1,
                "FORCESEEK");

        model2StringMap.put(
                builderTest.exampleB2,
                "FORCESEEK (PK_SalesOrderDetail_SalesOrderID_SalesOrderDetailID (SalesOrderID))");

        model2StringMap.put(
                builderTest.exampleC,
                "FORCESCAN");

    }

    @SuppressWarnings("Duplicates")
    @Test
    public void testPrint() throws Exception {
        final int[] index = {1};
        model2StringMap.forEach((key, value) -> {
            StringWriter writer = ReferenceBlockPrinter.print(key);
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