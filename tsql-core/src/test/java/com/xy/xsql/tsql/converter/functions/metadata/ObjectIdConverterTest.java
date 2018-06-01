package com.xy.xsql.tsql.converter.functions.metadata;

import com.xy.xsql.block.core.printer.MetaBlockPrinter;
import com.xy.xsql.block.model.BlockMeta;
import org.junit.Assert;
import org.junit.Test;

import java.io.StringWriter;

import static org.junit.Assert.*;

/**
 * Created by xiaoyao9184 on 2018/6/1.
 */
public class ObjectIdConverterTest {

    @Test
    public void testMetaPrint() throws Exception {
        BlockMeta b = ObjectIdConverter.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        //TODO support object_name
        Assert.assertEquals(writer.toString(),
                "<OBJECT_ID> ::=\n" +
                        "OBJECT_ID ( [ server_name . ] [ database_name . [ schema_name ] . | schema_name . ] table_name [ , 'object_type' ] )");
    }

}