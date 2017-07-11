package com.xy.xsql.block.tsql.core.element;

import com.xy.xsql.block.core.MetaContextBlockPrinter;
import com.xy.xsql.block.model.BlockMeta;
import org.junit.Assert;
import org.junit.Test;

import java.io.StringWriter;

/**
 * Created by xiaoyao9184 on 2017/6/21.
 */
public class MultipartNamesConverterTest {

    /*
    Multipart Names
    See https://docs.microsoft.com/zh-cn/sql/t-sql/language-elements/transact-sql-syntax-conventions-transact-sql#multipart-names
     */

    @Test
    public void testTableName() throws Exception {
        BlockMeta b = MultipartNamesConverter.TableNameConverter.meta;

        StringWriter writer = new MetaContextBlockPrinter()
                .printMeta(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "[ server_name . ] [ database_name . [ schema_name ] . | schema_name . ] table_name");
    }

    @Test
    public void testColumnName() throws Exception {
        BlockMeta b = MultipartNamesConverter.ColumnNameConverter.meta;

        StringWriter writer = new MetaContextBlockPrinter()
                .printMeta(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "[ [ server_name . ] [ database_name . [ schema_name ] . | schema_name . ] table_name . ] column_name");
    }


}