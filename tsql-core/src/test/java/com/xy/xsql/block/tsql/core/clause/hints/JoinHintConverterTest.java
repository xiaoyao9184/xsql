package com.xy.xsql.block.tsql.core.clause.hints;

import com.xy.xsql.block.core.ReferenceBlockPrinter;
import com.xy.xsql.block.model.ReferenceBlock;
import com.xy.xsql.tsql.core.clause.FromBuilderTest;
import com.xy.xsql.tsql.core.clause.hint.JoinHintBuilderTest;
import com.xy.xsql.tsql.model.clause.From;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.StringWriter;

import static com.xy.xsql.tsql.core.clause.hints.JoinHintBuilder.HASH;

/**
 * Created by xiaoyao9184 on 2017/6/21.
 */
public class JoinHintConverterTest {

    @Test
    public void test() throws Exception {
        ReferenceBlock b = JoinHintConverter.meta();

        StringWriter writer = new ReferenceBlockPrinter()
                .print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<join_hint> ::=\n" +
                        "{ LOOP | HASH | MERGE | REMOTE }");
    }


    @Test
    public void testPrintA() throws Exception {
        StringWriter writer = ReferenceBlockPrinter.print(HASH());

        String ok = "HASH";
        Assert.assertEquals(writer.toString().replace(" ",""),
                ok);
    }
}