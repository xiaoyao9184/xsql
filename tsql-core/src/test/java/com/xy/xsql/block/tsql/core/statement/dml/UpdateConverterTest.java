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
public class UpdateConverterTest {

    @Test
    public void test() throws Exception {
        ReferenceBlock b = UpdateConverter.meta();

        StringWriter writer = new ReferenceBlockPrinter()
                .print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<UPDATE> ::=\n" +
                        "[ WITH <common_table_expression> [ ,...n ] ]\n" +
                        "UPDATE\n" +
                        "[ TOP ( expression ) [ PERCENT ] ]\n" +
                        "{\n" +
                        "table_alias\n" +
                        "| <object>\n" +
                        "}\n" +
                        "SET\n" +
                        "{\n" +
                        "column_name = expression | DEFAULT | NULL\n" +
                        "| @variable = expression\n" +
                        "| @variable = column = expression\n" +
                        "| column_name += | -= | *= | /= | %= | &= | ^= | |= expression\n" +
                        "| @variable { += | -= | *= | /= | %= | &= | ^= | |= } expression\n" +
                        "| @variable = column { += | -= | *= | /= | %= | &= | ^= | |= } expression\n" +
                        "} [,...n]\n" +
                        "[ <OUTPUT Clause> ]\n" +
                        "[ FROM table_source [ ,...n ]  ]\n" +
                        "[ WHERE <search_condition> ]\n" +
                        "[ OPTION ( <Query Hint> [ ,...n ] ) ]");
    }


}