package com.xy.xsql.tsql.converter.functions.collation;

import com.xy.xsql.block.core.printer.MetaBlockPrinter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.converter.functions.analytic.PercentRankConverter;
import org.junit.Assert;
import org.junit.Test;

import java.io.StringWriter;

import static org.junit.Assert.*;

/**
 * Created by xiaoyao9184 on 2018/5/31.
 */
public class CollationPropertyConverterTest {

    @Test
    public void testMetaPrint() throws Exception {
        BlockMeta b = CollationPropertyConverter.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<COLLATIONPROPERTY> ::=\n" +
                        "COLLATIONPROPERTY ( collation_name , property )");
    }

}