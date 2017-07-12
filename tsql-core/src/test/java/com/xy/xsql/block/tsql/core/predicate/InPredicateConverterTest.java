package com.xy.xsql.block.tsql.core.predicate;

import com.xy.xsql.block.core.MetaContextBlockPrinter;
import com.xy.xsql.block.model.BlockMeta;
import org.junit.Assert;
import org.junit.Test;

import java.io.StringWriter;

/**
 * Created by xiaoyao9184 on 2017/6/15.
 */
public class InPredicateConverterTest {

    @Test
    public void testMetaPrint() throws Exception {
        BlockMeta b = InPredicateConverter.meta;

        StringWriter writer = new MetaContextBlockPrinter()
                .printMeta(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<IN> ::=\n" +
                        "expression [ NOT ] IN ( subquery | { expression } [,...n] )");
    }

}