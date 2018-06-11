package com.xy.xsql.tsql.builder.chain.functions.rowset;

import com.xy.xsql.tsql.model.datatypes.constants.StringConstant;
import com.xy.xsql.tsql.model.functions.rowset.OpenDataSource;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.datatypes.Constants.c_string;
import static com.xy.xsql.tsql.builder.chain.functions.RowsetFunctions.f_opendatasource;
import static org.junit.Assert.assertEquals;

/**
 * Created by xiaoyao9184 on 2018/6/5.
 */
public class OpenDataSourceFunctionTest {


    /**
     * OPENDATASOURCE('SQLNCLI',
     'Data Source=London\Payroll;Integrated Security=SSPI')
     */
    public OpenDataSource example1 = f_opendatasource(
            c_string("SQLNCLI"),
            "Data Source=London\\Payroll;Integrated Security=SSPI"
    );

    /**
     * OPENDATASOURCE('Microsoft.Jet.OLEDB.4.0',
     'Data Source=C:\DataFolder\Documents\TestExcel.xls;Extended Properties=EXCEL 5.0')
     */
    public OpenDataSource example2 = f_opendatasource(
            c_string("Microsoft.Jet.OLEDB.4.0"),
            "Data Source=C:\\DataFolder\\Documents\\TestExcel.xls;Extended Properties=EXCEL 5.0"
    );

    @Test
    public void testExample(){
        assertEquals(example1.getProviderName().getClass(), StringConstant.class);
        assertEquals(example1.getInitString(),"Data Source=London\\Payroll;Integrated Security=SSPI");
        assertEquals(example2.getProviderName().getClass(), StringConstant.class);
        assertEquals(example2.getInitString(),"Data Source=C:\\DataFolder\\Documents\\TestExcel.xls;Extended Properties=EXCEL 5.0");
    }


}