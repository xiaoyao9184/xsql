package com.xy.xsql.block.tsql.core.statement.ddl;

import com.xy.xsql.block.core.MetaContextBlockPrinter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.core.statement.ddl.TruncateTableBuilderTest;
import com.xy.xsql.tsql.model.statement.ddl.TruncateTable;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.StringWriter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by xiaoyao9184 on 2017/6/17.
 */
public class TruncateTableConverterTest {

    @Test
    public void test() throws Exception {
        BlockMeta b = TruncateTableConverter.meta;

        StringWriter writer = new MetaContextBlockPrinter()
                .printMeta(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<TRUNCATE TABLE> ::=\n" +
                        "TRUNCATE TABLE \n" +
                        "[ { database_name .[ schema_name ] . | schema_name . } ]  table_name \n" +
                        "[ WITH ( PARTITIONS ( { <partition_number_expression> | <range> } [,...n] ) ) ]");
    }


    private Map<TruncateTable,String> model2StringMap;

    @Before
    public void init(){
        TruncateTableBuilderTest builderTest = new TruncateTableBuilderTest();
        model2StringMap = new LinkedHashMap<>();

        model2StringMap.put(
                builderTest.exampleA,
                "TRUNCATE TABLE HumanResources.JobCandidate");

        model2StringMap.put(
                builderTest.exampleB,
                "TRUNCATE TABLE PartitionTable1   \n" +
                        "    WITH (PARTITIONS (2, 4, 6 TO 8))");
    }

    @SuppressWarnings("Duplicates")
    @Test
    public void testPrint() throws Exception {
        final int[] index = {1};
        model2StringMap.forEach((key, value) -> {
            StringWriter writer = MetaContextBlockPrinter.print(key);
            String check = writer.toString()
                    .replaceAll(" ", "")
                    .replaceAll("\n", "");

            String ok = value
                    .replaceAll(" ", "")
                    .replaceAll("\n", "");
            Assert.assertEquals(
                    "Not Equal Index:" + index[0],
                    check,
                    ok);
            index[0]++;
        });
    }

}