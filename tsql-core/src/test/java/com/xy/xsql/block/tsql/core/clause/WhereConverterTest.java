package com.xy.xsql.block.tsql.core.clause;

import com.xy.xsql.block.core.ReferenceBlockPrinter;
import com.xy.xsql.block.model.ReferenceBlock;
import org.junit.Assert;
import org.junit.Test;

import java.io.StringWriter;

/**
 * Created by xiaoyao9184 on 2017/6/20.
 */
public class WhereConverterTest {

    @Test
    public void test() throws Exception {
        ReferenceBlock b = WhereConverter.meta();

        StringWriter writer = new ReferenceBlockPrinter()
                .print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<WHERE> ::=\n" +
                        "WHERE <search_condition>");
    }

}