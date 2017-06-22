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
public class DeleteConverterTest {

    @Test
    public void test() throws Exception {
        ReferenceBlock b = DeleteConverter.meta();

        StringWriter writer = new ReferenceBlockPrinter()
                .print(b);

        System.out.print(writer);
        Assert.assertEquals(writer.toString(),
                "<DELETE> ::=\n" +
                        "[ WITH <common_table_expression> [ ,...n ] ]\n" +
                        "DELETE\n" +
                        "[ TOP ( expression ) [ PERCENT ] ]\n" +
                        "[ FROM ]\n" +
                        "{\n" +
                        "[ table_alias ]\n" +
                        "| <object>\n" +
                        "}\n" +
                        "[ <OUTPUT Clause> ]\n" +
                        "[ FROM table_source [ ,...n ] ]\n" +
                        "[ WHERE { <search_condition> ]\n" +
                        "[ OPTION ( <Query Hint> [ ,...n ] ) ]");
    }


}