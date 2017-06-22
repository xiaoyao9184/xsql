package com.xy.xsql.block.tsql.core.predicate;

import com.xy.xsql.block.core.ReferenceBlockPrinter;
import com.xy.xsql.block.model.ReferenceBlock;
import org.junit.Assert;
import org.junit.Test;

import java.io.StringWriter;

import static org.junit.Assert.*;

/**
 * Created by xiaoyao9184 on 2017/6/15.
 */
public class LikePredicateConverterTest {

    @Test
    public void test() throws Exception {
        ReferenceBlock b = LikePredicateConverter.meta();

        StringWriter writer = new ReferenceBlockPrinter()
                .print(b);

        System.out.print(writer);
        Assert.assertEquals(writer.toString(),
                "<LIKE> ::=\n" +
                        "string_expression [ NOT ] LIKE string_expression [ ESCAPE 'escape_character' ]");
    }

}