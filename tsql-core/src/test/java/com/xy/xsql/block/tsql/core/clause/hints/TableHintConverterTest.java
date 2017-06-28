package com.xy.xsql.block.tsql.core.clause.hints;

import com.xy.xsql.block.core.ReferenceBlockPrinter;
import com.xy.xsql.block.model.ReferenceBlock;
import org.junit.Assert;
import org.junit.Test;

import java.io.StringWriter;

import static com.xy.xsql.tsql.core.clause.hints.QueryHintBuilder.MERGE_JOIN;
import static com.xy.xsql.tsql.core.clause.hints.TableHintBuilder.FORCESCAN;
import static com.xy.xsql.tsql.core.clause.hints.TableHintBuilder.FORCESEEK;
import static com.xy.xsql.tsql.core.clause.hints.TableHintBuilder.TABLOCK;
import static org.junit.Assert.*;

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


    @Test
    public void testPrintA() throws Exception {
        StringWriter writer = ReferenceBlockPrinter.print(TABLOCK());

        String ok = "TABLOCK";
        ok = ok.replaceAll(" ","");
        Assert.assertEquals(writer.toString().replace(" ",""),
                ok);
    }


    @Test
    public void testPrintB1() throws Exception {
        StringWriter writer = ReferenceBlockPrinter.print(FORCESEEK());

        String ok = "FORCESEEK";
        ok = ok.replaceAll(" ","");
        Assert.assertEquals(writer.toString().replace(" ",""),
                ok);
    }


    @Test
    public void testPrintB2() throws Exception {
        StringWriter writer = ReferenceBlockPrinter.print(FORCESEEK("PK_SalesOrderDetail_SalesOrderID_SalesOrderDetailID","SalesOrderID"));

        String ok = "FORCESEEK (PK_SalesOrderDetail_SalesOrderID_SalesOrderDetailID (SalesOrderID))";
        ok = ok.replaceAll(" ","");
        Assert.assertEquals(writer.toString().replace(" ",""),
                ok);
    }


    @Test
    public void testPrintC() throws Exception {
        StringWriter writer = ReferenceBlockPrinter.print(FORCESCAN());

        String ok = "FORCESCAN";
        ok = ok.replaceAll(" ","");
        Assert.assertEquals(writer.toString().replace(" ",""),
                ok);
    }
}