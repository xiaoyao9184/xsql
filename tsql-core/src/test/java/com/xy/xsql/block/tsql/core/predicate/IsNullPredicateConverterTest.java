package com.xy.xsql.block.tsql.core.predicate;

import com.xy.xsql.block.core.ModelMetaBlockPrinter;
import com.xy.xsql.block.model.BlockMeta;
import org.junit.Assert;
import org.junit.Test;

import java.io.StringWriter;

/**
 * Created by xiaoyao9184 on 2017/6/15.
 */
public class IsNullPredicateConverterTest {

    @Test
    public void testMetaPrint() throws Exception {
        BlockMeta b = IsNullPredicateConverter.meta;

        StringWriter writer = new ModelMetaBlockPrinter()
                .printMeta(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<IS NULL> ::=\n" +
                        "expression IS [ NOT ] NULL");
    }

}