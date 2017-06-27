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
public class ReNameDatabaseConverterTest {

    @Test
    public void test() throws Exception {
        ReferenceBlock b = ReNameDatabaseConverter.meta();

        StringWriter writer = new ReferenceBlockPrinter()
                .print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<RENAME> ::=\n" +
                        "RENAME DATABASE [ :: ] database_name TO new_database_name");
    }

}