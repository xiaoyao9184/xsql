package com.xy.xsql.block.tsql.core.clause.hints;

import com.xy.xsql.block.core.printer.ModelMetaBlockPrinter;
import com.xy.xsql.block.model.BlockMeta;
import org.junit.Assert;
import org.junit.Test;

import java.io.StringWriter;

/**
 * Created by xiaoyao9184 on 2017/6/21.
 */
public class TableHintLimitedConverterTest {

    @Test
    public void testMetaPrint() throws Exception {
        BlockMeta b = TableHintLimitedConverter.meta;

        StringWriter writer = new ModelMetaBlockPrinter()
                .printMeta(b);

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