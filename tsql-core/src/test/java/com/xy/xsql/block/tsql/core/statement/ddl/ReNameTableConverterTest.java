package com.xy.xsql.block.tsql.core.statement.ddl;

import com.xy.xsql.block.core.ReferenceBlockPrinter;
import com.xy.xsql.block.model.ReferenceBlock;
import com.xy.xsql.block.tsql.core.variable.DeclareVariableConverter;
import com.xy.xsql.tsql.core.statement.ddl.RenameBuilderTest;
import com.xy.xsql.tsql.model.statement.ddl.rename.ReName;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.StringWriter;

import static org.junit.Assert.*;

/**
 * Created by xiaoyao9184 on 2017/6/17.
 */
public class ReNameTableConverterTest {

    @Test
    public void test() throws Exception {
        ReferenceBlock b = ReNameTableConverter.meta();

        StringWriter writer = new ReferenceBlockPrinter()
                .print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<RENAME> ::=\n" +
                        "RENAME OBJECT [ :: ] [ [ database_name .  [schema_name ] ] . ] | [schema_name . ] ] table_name TO new_table_name");
    }

    private RenameBuilderTest builderTest;

    @Before
    public void init(){
        builderTest = new RenameBuilderTest();
    }

    @Test
    public void testPrintB1() throws Exception {
        ReName reName = builderTest.exampleB1;

        //TODO same type different converter
        StringWriter writer = ReferenceBlockPrinter.print(reName);

        String ok = "RENAME OBJECT Customer TO Customer1";
        ok = ok.replaceAll(" ","");
        Assert.assertEquals(writer.toString().replace(" ",""),
                ok);
    }

    @Test
    public void testPrintB2() throws Exception {
        ReName reName = builderTest.exampleB2;

        StringWriter writer = ReferenceBlockPrinter.print(reName);

        String ok = "RENAME OBJECT mydb.dbo.Customer TO Customer1";
        ok = ok.replaceAll(" ","");
        Assert.assertEquals(writer.toString().replace(" ",""),
                ok);
    }

}