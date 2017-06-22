package com.xy.xsql.block.tsql.core.statement.dml;

import com.xy.xsql.block.core.ReferenceBlockPrinter;
import com.xy.xsql.block.model.ReferenceBlock;
import com.xy.xsql.block.tsql.core.statement.ddl.ReNameDatabaseConverter;
import org.junit.Assert;
import org.junit.Test;

import java.io.StringWriter;

import static org.junit.Assert.*;

/**
 * Created by xiaoyao9184 on 2017/6/17.
 */
public class BulkInsertConverterTest {

    @Test
    public void test() throws Exception {
        ReferenceBlock b = BulkInsertConverter.meta();

        StringWriter writer = new ReferenceBlockPrinter()
                .print(b);

        System.out.print(writer);
        Assert.assertEquals(writer.toString(),
                "BULK INSERT \n" +
                        "[ database_name . [ schema_name ] . | schema_name . ][ table_name | view_name ]  \n" +
                        "FROM 'data_file' \n" +
                        "[ WITH ( ) ]");
    }


}