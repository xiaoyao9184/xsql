package com.xy.xsql.tsql.converter.functions.rowset;

import com.xy.xsql.block.core.printer.MetaBlockPrinter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.builder.chain.functions.rowset.OpenDataSourceFunctionTest;
import com.xy.xsql.tsql.converter.BaseTest;
import com.xy.xsql.tsql.model.functions.rowset.OpenDataSource;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.StringWriter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by xiaoyao9184 on 2018/5/31.
 */
public class OpenDataSourceConverterTest {

    @Test
    public void testMetaPrint() throws Exception {
        BlockMeta b = OpenDataSourceConverter.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<OPENDATASOURCE> ::=\n" +
                        "OPENDATASOURCE ( provider_name , init_string )");
    }

    private Map<OpenDataSource,String> model2StringMap;

    @Before
    public void init(){
        OpenDataSourceFunctionTest builderTest = new OpenDataSourceFunctionTest();
        model2StringMap = new LinkedHashMap<>();

        model2StringMap.put(
                builderTest.example1,
                "OPENDATASOURCE('SQLNCLI',\n" +
                        "     'Data Source=London\\Payroll;Integrated Security=SSPI')");
        model2StringMap.put(
                builderTest.example2,
                "OPENDATASOURCE('Microsoft.Jet.OLEDB.4.0',\n" +
                        "     'Data Source=C:\\DataFolder\\Documents\\TestExcel.xls;Extended Properties=EXCEL 5.0')");
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