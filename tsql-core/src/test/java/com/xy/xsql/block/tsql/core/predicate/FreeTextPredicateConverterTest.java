package com.xy.xsql.block.tsql.core.predicate;

import com.xy.xsql.block.core.ModelMetaBlockPrinter;
import com.xy.xsql.block.model.BlockMeta;
import org.junit.Assert;
import org.junit.Test;

import java.io.StringWriter;

/**
 * Created by xiaoyao9184 on 2017/6/15.
 */
public class FreeTextPredicateConverterTest {

    @Test
    public void testMetaPrint() throws Exception {
        BlockMeta b = FreeTextPredicateConverter.meta;

        StringWriter writer = new ModelMetaBlockPrinter()
                .printMeta(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<FREETEXT> ::=\n" +
                        "FREETEXT ( { column_name | ( column_list ) | * } , 'freetext_string' , [ LANGUAGE language_term ] )");
    }

}