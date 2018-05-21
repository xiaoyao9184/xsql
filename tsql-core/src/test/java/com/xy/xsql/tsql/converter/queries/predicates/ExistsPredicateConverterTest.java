package com.xy.xsql.tsql.converter.queries.predicates;

import com.xy.xsql.block.core.printer.MetaBlockPrinter;
import com.xy.xsql.block.model.BlockMeta;
import org.junit.Assert;
import org.junit.Test;

import java.io.StringWriter;

/**
 * Created by xiaoyao9184 on 2017/6/15.
 */
public class ExistsPredicateConverterTest {

    @Test
    public void testMetaPrint() throws Exception {
        BlockMeta b = ExistsPredicateConverter.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<EXISTS> ::=\n" +
                        "EXISTS ( subquery )");
    }

}