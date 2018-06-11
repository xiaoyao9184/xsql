package com.xy.xsql.tsql.converter.functions.security;

import com.xy.xsql.block.core.printer.MetaBlockPrinter;
import com.xy.xsql.block.model.BlockMeta;
import org.junit.Assert;
import org.junit.Test;

import java.io.StringWriter;

import static org.junit.Assert.*;

/**
 * Created by xiaoyao9184 on 2018/6/11.
 */
public class HasPermsByNameConverterTest {

    @Test
    public void testMetaPrint() throws Exception {
        BlockMeta b = HasPermsByNameConverter.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<HAS_PERMS_BY_NAME> ::=\n" +
                        "HAS_PERMS_BY_NAME ( securable , securable_class , permission [ , sub-securable ] [ , sub-securable_class ] )");
    }

}