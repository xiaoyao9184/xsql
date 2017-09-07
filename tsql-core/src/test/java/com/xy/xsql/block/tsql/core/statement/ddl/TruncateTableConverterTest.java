package com.xy.xsql.block.tsql.core.statement.ddl;

import com.xy.xsql.block.core.converter.ModelKeywordBlockConverter;
import com.xy.xsql.block.core.printer.MetaBlockPrinter;
import com.xy.xsql.block.core.printer.ModelMetaBlockPrinter;
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
    public void testMetaPrint() throws Exception {
        BlockMeta b = TruncateTableConverter.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<TRUNCATE TABLE> ::=\n" +
                        "TRUNCATE TABLE\n" +
                        "[ { database_name .[ schema_name ] . | schema_name . } ]  table_name\n" +
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
            StringWriter writer = ModelMetaBlockPrinter.print(key);
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

    @SuppressWarnings("Duplicates")
    @Test
    public void testKeywordPrint() throws Exception {
        final int[] index = {1};
        model2StringMap.forEach((key, value) -> {
            String check = ModelKeywordBlockConverter
                    .convert(key)
                    .print();
            System.out.println(check);

            check = check
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