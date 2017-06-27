package com.xy.xsql.block.tsql.core.statement.ddl;

import com.xy.xsql.block.core.ReferenceBlockPrinter;
import com.xy.xsql.block.model.ReferenceBlock;
import com.xy.xsql.block.tsql.core.variable.DeclareVariableConverter;
import org.junit.Assert;
import org.junit.Test;

import java.io.StringWriter;

import static org.junit.Assert.*;

/**
 * Created by xiaoyao9184 on 2017/6/17.
 */
public class TruncateTableConverterTest {

    @Test
    public void test() throws Exception {
        ReferenceBlock b = TruncateTableConverter.meta();

        StringWriter writer = new ReferenceBlockPrinter()
                .print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<TRUNCATE TABLE> ::=\n" +
                        "TRUNCATE TABLE \n" +
                        "[ { database_name .[ schema_name ] . | schema_name . } ]  table_name \n" +
                        "[ WITH ( PARTITIONS ( { <partition_number_expression> | <range> } [,...n] ) ) ]");
    }

}