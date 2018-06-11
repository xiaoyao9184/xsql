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
public class PWDcompareConverterTest {

    @Test
    public void testMetaPrint() throws Exception {
        BlockMeta b = PWDcompareConverter.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<PWDCOMPARE> ::=\n" +
                        "PWDCOMPARE ( 'clear_text_password' , password_hash [ , version ] )");
    }

}