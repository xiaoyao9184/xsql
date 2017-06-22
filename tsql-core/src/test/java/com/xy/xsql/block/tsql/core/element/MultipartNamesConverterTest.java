package com.xy.xsql.block.tsql.core.element;

import com.xy.xsql.block.core.ReferenceBlockPrinter;
import com.xy.xsql.block.model.ReferenceBlock;
import com.xy.xsql.block.tsql.core.clause.FromConverter;
import org.junit.Assert;
import org.junit.Test;

import java.io.StringWriter;

import static org.junit.Assert.*;

/**
 * Created by xiaoyao9184 on 2017/6/21.
 */
public class MultipartNamesConverterTest {

    @Test
    public void testTableName() throws Exception {
        ReferenceBlock b = MultipartNamesConverter.TableNameConverter.meta();

        StringWriter writer = new ReferenceBlockPrinter()
                .print(b);

        System.out.print(writer);
        Assert.assertEquals(writer.toString(),
                "[ database_name . [ schema_name ] . | schema_name . ] table_name");
    }

    @Test
    public void testColumnName() throws Exception {
        ReferenceBlock b = MultipartNamesConverter.ColumnNameConverter.meta();

        StringWriter writer = new ReferenceBlockPrinter()
                .print(b);

        System.out.print(writer);
        Assert.assertEquals(writer.toString(),
                "[ [ database_name . [ schema_name ] . | schema_name . ] table_name . ] column_name");
    }


}