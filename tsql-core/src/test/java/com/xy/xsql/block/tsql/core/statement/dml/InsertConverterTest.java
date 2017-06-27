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
public class InsertConverterTest {

    @Test
    public void test() throws Exception {
        ReferenceBlock b = InsertConverter.meta();

        StringWriter writer = new ReferenceBlockPrinter()
                .print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<INSERT> ::=\n" +
                        "[ WITH <common_table_expression> [ ,...n ] ]\n" +
                        "INSERT\n" +
                        "[ TOP ( expression ) [ PERCENT ] ]\n" +
                        "[ INTO ]\n" +
                        "{ <object> }\n" +
                        "[ ( column_list ) ]\n" +
                        "[ <OUTPUT Clause> ]\n" +
                        "{\n" +
                        "[ <VALUES ( { DEFAULT | NULL | expression } [ ,...n ] ) [ ,...n     ]> ]\n" +
                        "| [ DEFAULT VALUES ]\n" +
                        "}");
    }


}