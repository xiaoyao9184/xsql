package com.xy.xsql.block.tsql.core.statement.ddl;

import com.xy.xsql.block.core.ReferenceBlockPrinter;
import com.xy.xsql.block.model.ReferenceBlock;
import com.xy.xsql.block.tsql.core.variable.DeclareVariableConverter;
import com.xy.xsql.tsql.core.statement.ddl.TruncateTableBuilderTest;
import com.xy.xsql.tsql.model.statement.ddl.TruncateTable;
import org.junit.Assert;
import org.junit.Before;
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

    private TruncateTableBuilderTest builderTest;

    @Before
    public void init(){
        builderTest = new TruncateTableBuilderTest();
    }

    @Test
    public void testPrintA() throws Exception {
        TruncateTable example = builderTest.exampleA;
        String ok = "TRUNCATE TABLE HumanResources.JobCandidate";

        StringWriter writer = ReferenceBlockPrinter.print(example);
        String check = writer.toString()
                .replaceAll(" ", "")
                .replaceAll("\n", "");

        ok = ok
                .replaceAll(" ", "")
                .replaceAll("\n", "");
        Assert.assertEquals(
                check,
                ok);
    }

    @Test
    public void testPrintB() throws Exception {
        TruncateTable example = builderTest.exampleB;
        String ok = "TRUNCATE TABLE PartitionTable1\n" +
                "     WITH (PARTITIONS (2, 4, 6 TO 8))";

        StringWriter writer = ReferenceBlockPrinter.print(example);
        String check = writer.toString()
                .replaceAll(" ", "")
                .replaceAll("\n", "");

        ok = ok
                .replaceAll(" ", "")
                .replaceAll("\n", "");
        Assert.assertEquals(
                check,
                ok);
    }

}