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
public class TableHintLimitedConverterTest {

    @Test
    public void test() throws Exception {
        ReferenceBlock b = TableHintLimitedConverter.meta();

        StringWriter writer = new ReferenceBlockPrinter()
                .print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<table_hint_limited> ::=\n" +
                        "KEEPIDENTITY\n" +
                        "| KEEPDEFAULTS\n" +
                        "| HOLDLOCK\n" +
                        "| IGNORE_CONSTRAINTS\n" +
                        "| IGNORE_TRIGGERS\n" +
                        "| NOLOCK\n" +
                        "| NOWAIT\n" +
                        "| PAGLOCK\n" +
                        "| READPAST\n" +
                        "| READCOMMITTED\n" +
                        "| READCOMMITTEDLOCK\n" +
                        "| READPAST\n" +
                        "| REPEATABLEREAD\n" +
                        "| ROWLOCK\n" +
                        "| SERIALIZABLE\n" +
                        "| SNAPSHOT\n" +
                        "| TABLOCK\n" +
                        "| TABLOCKX\n" +
                        "| UPDLOCK\n" +
                        "| XLOCK");
    }

}