package com.xy.xsql.tsql.converter.functions.metadata;

import com.xy.xsql.block.core.printer.MetaBlockPrinter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.converter.functions.mathematical.AbsConverter;
import org.junit.Assert;
import org.junit.Test;

import java.io.StringWriter;

import static org.junit.Assert.*;

/**
 * Created by xiaoyao9184 on 2018/6/1.
 */
public class AppLockModeConverterTest {

    @Test
    public void testMetaPrint() throws Exception {
        BlockMeta b = AppLockModeConverter.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<APPLOCK_MODE> ::=\n" +
                        "APPLOCK_MODE ( 'database_principal' , 'resource_name' , 'lock_owner' )");
    }

}