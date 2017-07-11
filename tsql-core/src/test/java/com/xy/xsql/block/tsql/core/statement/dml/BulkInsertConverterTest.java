package com.xy.xsql.block.tsql.core.statement.dml;

import com.xy.xsql.block.core.MetaContextBlockPrinter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.core.statement.dml.BulkInsertBuilderTest;
import com.xy.xsql.tsql.model.statement.dml.BulkInsert;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.StringWriter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by xiaoyao9184 on 2017/6/17.
 */
public class BulkInsertConverterTest {

    @Test
    public void test() throws Exception {
        BlockMeta b = BulkInsertConverter.meta;

        StringWriter writer = new MetaContextBlockPrinter()
                .print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<BULK INSERT> ::=\n" +
                        "BULK INSERT \n" +
                        "[ database_name . [ schema_name ] . | schema_name . ][ table_name | view_name ]  \n" +
                        "FROM 'data_file' \n" +
                        "[ WITH\n" +
                        "(\n" +
                        "[ BATCHSIZE = batch_size ]\n" +
                        "[ [ , ] CHECK_CONSTRAINTS ]\n" +
                        "[ [ , ] CODEPAGE = { 'ACP' | 'OEM' | 'RAW' | 'code_page' } ]\n" +
                        "[ [ , ] DATAFILETYPE = { 'char' | 'native' | 'widechar' | 'widenative' } ]\n" +
                        "[ [ , ] DATASOURCE = 'data_source_name' ]\n" +
                        "[ [ , ] ERRORFILE = 'file_name' ]\n" +
                        "[ [ , ] ERRORFILE_DATASOURCE = 'data_source_name' ]\n" +
                        "[ [ , ] FIRSTROW = first_row ]\n" +
                        "[ [ , ] FIRE_TRIGGERS ]\n" +
                        "[ [ , ] FORMATFILE_DATASOURCE = data_source_name ]\n" +
                        "[ [ , ] KEEPIDENTITY ]\n" +
                        "[ [ , ] KEEPNULLS ]\n" +
                        "[ [ , ] KILOBYTES_PER_BATCH = kilobytes_per_batch ]\n" +
                        "[ [ , ] LASTROW = last_row ]\n" +
                        "[ [ , ] MAXERRORS = max_errors ]\n" +
                        "[ [ , ] ORDER ( { column [ ASC | DESC ] } [,...n] ) ]\n" +
                        "[ [ , ] ROWS_PER_BATCH = rows_per_batch ]\n" +
                        "[ [ , ] ROWTERMINATOR = 'row_terminator' ]\n" +
                        "[ [ , ] TABLOCK ]\n" +
                        "[ [ , ] FORMAT = 'CSV' ]\n" +
                        "[ [ , ] FIELDQUOTE = 'quote_characters' ]\n" +
                        "[ [ , ] FORMATFILE = 'format_file_path' ]\n" +
                        "[ [ , ] FIELDTERMINATOR = 'field_terminator' ]\n" +
                        ") ]");
    }

    private Map<BulkInsert,String> model2StringMap;

    @Before
    public void init(){
        BulkInsertBuilderTest builderTest = new BulkInsertBuilderTest();
        model2StringMap = new LinkedHashMap<>();

        /*
        order
         */
        model2StringMap.put(
                builderTest.exampleA,
                "BULK INSERT AdventureWorks2012.Sales.SalesOrderDetail\n" +
                        "       FROM 'f:\\orders\\lineitem.tbl'\n" +
                        "       WITH\n" +
                        "          (\n" +
                        "             ROWTERMINATOR =' |\\n',\n" +
                        "             FIELDTERMINATOR =' |'\n" +
                        "          )");
//                "BULK INSERT AdventureWorks2012.Sales.SalesOrderDetail\n" +
//                        "       FROM 'f:\\orders\\lineitem.tbl'\n" +
//                        "       WITH\n" +
//                        "          (\n" +
//                        "             FIELDTERMINATOR =' |',\n" +
//                        "             ROWTERMINATOR =' |\\n'\n" +
//                        "          )");

        /*
        order
         */
        model2StringMap.put(
                builderTest.exampleB,
                "BULK INSERT AdventureWorks2012.Sales.SalesOrderDetail\n" +
                        "       FROM 'f:\\orders\\lineitem.tbl'\n" +
                        "       WITH\n" +
                        "         (\n" +
                        "            FIRE_TRIGGERS\n," +
                        "            ROWTERMINATOR = ':\\n',\n" +
                        "            FIELDTERMINATOR =' |'\n" +
                        "          )");
//                "BULK INSERT AdventureWorks2012.Sales.SalesOrderDetail\n" +
//                        "       FROM 'f:\\orders\\lineitem.tbl'\n" +
//                        "       WITH\n" +
//                        "         (\n" +
//                        "            FIELDTERMINATOR =' |',\n" +
//                        "            ROWTERMINATOR = ':\\n',\n" +
//                        "            FIRE_TRIGGERS\n" +
//                        "          )");

        model2StringMap.put(
                builderTest.exampleC,
                "BULK INSERT AdventureWorks2012.Sales.SalesOrderDetail\n" +
                        "    FROM ''<drive>:\\<path>\\<filename>''\n" +
                        "    WITH (ROWTERMINATOR = '''+CHAR(10)+''')");

        model2StringMap.put(
                builderTest.exampleD,
                "BULK INSERT MyTable\n" +
                        "    FROM 'D:\\data.csv'\n" +
                        "    WITH\n" +
                        "    ( CODEPAGE = '65001',\n" +
                        "        DATAFILETYPE = 'char',\n" +
                        "        FIELDTERMINATOR = ','\n" +
                        "    )");

        model2StringMap.put(
                builderTest.exampleE,
                "BULK INSERT Sales.Invoices\n" +
                        "    FROM '\\\\share\\invoices\\inv-2016-07-25.csv'\n" +
                        "    WITH (FORMAT = 'CSV')");

        /*
        DATA_SOURCE to DATASOURCE
         */
        model2StringMap.put(
                builderTest.exampleF,
                "BULK INSERT Sales.Invoices\n" +
                        "    FROM 'inv-2017-01-19.csv'\n" +
                        "    WITH (DATASOURCE = 'MyAzureInvoices',\n" +
                        "         FORMAT = 'CSV')");
//                "BULK INSERT Sales.Invoices\n" +
//                        "    FROM 'inv-2017-01-19.csv'\n" +
//                        "    WITH (DATA_SOURCE = 'MyAzureInvoices',\n" +
//                        "         FORMAT = 'CSV')");

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