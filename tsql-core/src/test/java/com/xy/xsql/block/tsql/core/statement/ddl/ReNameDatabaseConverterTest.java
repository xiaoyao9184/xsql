package com.xy.xsql.block.tsql.core.statement.ddl;

import com.xy.xsql.block.core.ReferenceBlockPrinter;
import com.xy.xsql.block.model.ReferenceBlock;
import com.xy.xsql.tsql.core.statement.ddl.RenameBuilderTest;
import com.xy.xsql.tsql.model.statement.ddl.rename.ReName;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.StringWriter;

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

    private RenameBuilderTest builderTest;

    @Before
    public void init(){
        builderTest = new RenameBuilderTest();
    }

    @Test
    public void testPrintA() throws Exception {
        ReName reName = builderTest.exampleA;

        StringWriter writer = ReferenceBlockPrinter.print(reName);

        String ok = "RENAME DATABASE AdWorks TO AdWorks2";
        ok = ok.replaceAll(" ","");
        Assert.assertEquals(writer.toString().replace(" ",""),
                ok);
    }

}