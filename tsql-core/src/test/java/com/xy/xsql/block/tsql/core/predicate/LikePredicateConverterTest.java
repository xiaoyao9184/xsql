package com.xy.xsql.block.tsql.core.predicate;

import com.xy.xsql.block.core.MetaContextBlockPrinter;
import com.xy.xsql.block.model.BlockMeta;
import org.junit.Assert;
import org.junit.Test;

import java.io.StringWriter;

/**
 * Created by xiaoyao9184 on 2017/6/15.
 */
public class LikePredicateConverterTest {

    @Test
    public void test() throws Exception {
        BlockMeta b = LikePredicateConverter.meta();

        StringWriter writer = new MetaContextBlockPrinter()
                .print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<LIKE> ::=\n" +
                        "string_expression [ NOT ] LIKE string_expression [ ESCAPE 'escape_character' ]");
    }

}