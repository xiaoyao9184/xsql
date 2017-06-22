package com.xy.xsql.block.tsql.core.predicate;

import com.xy.xsql.block.core.ReferenceBlockPrinter;
import com.xy.xsql.block.model.ReferenceBlock;
import com.xy.xsql.block.tsql.core.expression.AtTimeConverter;
import org.junit.Assert;
import org.junit.Test;

import java.io.StringWriter;

import static org.junit.Assert.*;

/**
 * Created by xiaoyao9184 on 2017/6/15.
 */
public class BetweenPredicateConverterTest {

    @Test
    public void test() throws Exception {
        ReferenceBlock b = BetweenPredicateConverter.meta();

        StringWriter writer = new ReferenceBlockPrinter()
                .print(b);

        System.out.print(writer);
        Assert.assertEquals(writer.toString(),
                "<BETWEEN> ::=\n" +
                        "expression [ NOT ] BETWEEN { expression } AND expression");
    }
}