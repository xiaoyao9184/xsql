package com.xy.xsql.tsql.converter.functions.rowset;

import com.xy.xsql.block.core.printer.MetaBlockPrinter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.builder.chain.functions.rowset.OpenRowSetFunctionTest;
import com.xy.xsql.tsql.converter.BaseTest;
import com.xy.xsql.tsql.model.functions.rowset.OpenRowSet;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.StringWriter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by xiaoyao9184 on 2018/5/31.
 */
public class OpenRowSetConverterTest {

    @Test
    public void testMetaPrint() throws Exception {
        BlockMeta b = OpenRowSetConverter.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<OPENROWSET> ::=\n" +
                        "OPENROWSET (\n" +
                        "{ 'provider_name' , { 'datasource' ; 'user_id' ; 'password'\n" +
                        "\t\t| 'provider_string' } }\n" +
                        "\t, { [ catalog. ] [ schema. ] object\n" +
                        "\t| 'query' }\n" +
                        "| BULK 'data_file'\n" +
                        "\tFORMATFILE = 'format_file_path' [ <bulk_options> ]\n" +
                        "\t\t[ , SINGLE_BLOB | SINGLE_CLOB | SINGLE_NCLOB ] )");
    }

    private Map<OpenRowSet,String> model2StringMap;

    @Before
    public void init(){
        OpenRowSetFunctionTest builderTest = new OpenRowSetFunctionTest();
        model2StringMap = new LinkedHashMap<>();

        model2StringMap.put(
                builderTest.exampleA,
                "OPENROWSET('SQLNCLI', 'Server=Seattle1;Trusted_Connection=yes;',\n" +
                        "     'SELECT GroupName, Name, DepartmentID\n" +
                        "     FROM AdventureWorks2012.HumanResources.Department\n" +
                        "     ORDER BY GroupName, Name')");
        model2StringMap.put(
                builderTest.exampleB,
                "OPENROWSET('Microsoft.Jet.OLEDB.4.0',\n" +
                        "     'C:\\Program Files\\Microsoft Office\\OFFICE11\\SAMPLES\\Northwind.mdb';\n" +
                        "     'admin';'',Customers)");
        model2StringMap.put(
                builderTest.exampleD,
                "OPENROWSET(BULK 'C:\\Text1.txt', SINGLE_BLOB)");
        model2StringMap.put(
                builderTest.exampleE,
                "OPENROWSET( BULK 'c:\\test\\values.txt',\n" +
                        "     FORMATFILE = 'c:\\test\\values.fmt')");
        model2StringMap.put(
                builderTest.exampleF,
                "OPENROWSET (BULK 'D:\\data.csv', FORMATFILE =\n" +
                        "     'D:\\format_no_collation.txt', CODEPAGE = '65001')");
        model2StringMap.put(
                builderTest.exampleG,
                "OPENROWSET(BULK 'D:\\XChange\\test-csv.csv',\n" +
                        "     FORMATFILE = 'D:\\XChange\\test-csv.fmt',\n" +
                        "     FIRSTROW=2,\n" +
                        "     FORMAT='CSV')");
        model2StringMap.put(
                builderTest.exampleH,
                "OPENROWSET(\n" +
                        "     BULK 'C:\\Program Files\\Microsoft SQL Server\\MSSQL14.CTP1_1\\MSSQL\\DATA\\inv-2017-01-19.csv',\n" +
                        "     SINGLE_CLOB)");
        model2StringMap.put(
                builderTest.exampleI,
                "OPENROWSET(\n" +
                        "     BULK  'inv-2017-01-19.csv',\n" +
                        "     DATA_SOURCE = 'MyAzureInvoices',\n" +
                        "     SINGLE_CLOB)");
    }

    @Test
    public void testPrint() throws Exception {
        BaseTest.testPrint(model2StringMap);
    }

    @Test
    public void testKeywordPrint() throws Exception {
        BaseTest.testKeywordPrint(model2StringMap);
    }

}