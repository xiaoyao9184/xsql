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
public class BulkInsertConverterTest {

    @Test
    public void test() throws Exception {
        ReferenceBlock b = BulkInsertConverter.meta();

        StringWriter writer = new ReferenceBlockPrinter()
                .print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
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
                        "[ [ , ] , { column [ ASC | DESC ] } ] [,...n]\n" +
                        "[ [ , ] ROWS_PER_BATCH = rows_per_batch ]\n" +
                        "[ [ , ] ROWTERMINATOR = 'row_terminator' ]\n" +
                        "[ [ , ] TABLOCK ]\n" +
                        "[ [ , ] FORMAT = 'CSV' ]\n" +
                        "[ [ , ] FIELDQUOTE = 'quote_characters' ]\n" +
                        "[ [ , ] FORMATFILE = 'format_file_path' ]\n" +
                        "[ [ , ] FIELDTERMINATOR = 'field_terminator' ]\n" +
                        ") ]");
    }


}