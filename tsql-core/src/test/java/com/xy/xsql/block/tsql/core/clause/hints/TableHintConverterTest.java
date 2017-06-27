package com.xy.xsql.block.tsql.core.clause.hints;

import com.xy.xsql.block.core.ReferenceBlockPrinter;
import com.xy.xsql.block.model.ReferenceBlock;
import org.junit.Assert;
import org.junit.Test;

import java.io.StringWriter;

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

}