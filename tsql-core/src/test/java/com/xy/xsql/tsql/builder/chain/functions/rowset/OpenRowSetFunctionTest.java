package com.xy.xsql.tsql.builder.chain.functions.rowset;

import com.xy.xsql.tsql.model.datatypes.constants.StringConstant;
import com.xy.xsql.tsql.model.functions.rowset.OpenRowSet;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.datatypes.Constants.c_string;
import static com.xy.xsql.tsql.builder.chain.datatypes.table.TableNameFactory.t;
import static com.xy.xsql.tsql.builder.chain.functions.RowsetFunctions.f_openrowset;
import static com.xy.xsql.tsql.builder.chain.statements.BulkInsertBuilder.WithSetter.*;
import static com.xy.xsql.tsql.builder.chain.statements.Statements.$BulkInsert;
import static com.xy.xsql.tsql.model.functions.rowset.OpenRowSet.Single.SINGLE_BLOB;
import static com.xy.xsql.tsql.model.functions.rowset.OpenRowSet.Single.SINGLE_CLOB;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by xiaoyao9184 on 2018/6/5.
 */
public class OpenRowSetFunctionTest {


    /**
     * OPENROWSET('SQLNCLI', 'Server=Seattle1;Trusted_Connection=yes;',
     'SELECT GroupName, Name, DepartmentID
     FROM AdventureWorks2012.HumanResources.Department
     ORDER BY GroupName, Name')
     */
    public OpenRowSet exampleA = f_openrowset(
            c_string("SQLNCLI"),
            c_string("Server=Seattle1;Trusted_Connection=yes;"),
            c_string("SELECT GroupName, Name, DepartmentID\n" +
                    "     FROM AdventureWorks2012.HumanResources.Department\n" +
                    "     ORDER BY GroupName, Name")
    );

    @Test
    public void testExampleA(){
        assertEquals(exampleA.getProviderName().getClass(),StringConstant.class);
        assertEquals(exampleA.getProviderString().getString(),"Server=Seattle1;Trusted_Connection=yes;");
        assertEquals(exampleA.getQuery().getString(),"SELECT GroupName, Name, DepartmentID\n" +
                "     FROM AdventureWorks2012.HumanResources.Department\n" +
                "     ORDER BY GroupName, Name");
    }

    /**
     * OPENROWSET('Microsoft.Jet.OLEDB.4.0',
     'C:\Program Files\Microsoft Office\OFFICE11\SAMPLES\Northwind.mdb';
     'admin';'',Customers)
     */
    public OpenRowSet exampleB = f_openrowset(
            c_string("Microsoft.Jet.OLEDB.4.0"),
            "C:\\Program Files\\Microsoft Office\\OFFICE11\\SAMPLES\\Northwind.mdb",
            "admin",
            "",
            "Customers"
    );

    @Test
    public void testExampleB(){
        assertEquals(exampleB.getProviderName().getClass(),StringConstant.class);
        assertEquals(exampleB.getDatasource().getString(),"C:\\Program Files\\Microsoft Office\\OFFICE11\\SAMPLES\\Northwind.mdb");
        assertEquals(exampleB.getUserId().getString(),"admin");
        assertEquals(exampleB.getPassword().getString(),"");
        assertEquals(exampleB.getObject().size(),1);
    }

    //ExampleC is like B

    /**
     * TODO support N string in BulkInsert data_file
     * OPENROWSET(BULK N'C:\Text1.txt', SINGLE_BLOB)
     */
    public OpenRowSet exampleD = f_openrowset(
            $BulkInsert()
                .$From("C:\\Text1.txt")
                .build(),
            SINGLE_BLOB
    );

    @Test
    public void testExampleD(){
        assertNotNull(exampleD.getBulkInsert());
        assertEquals(exampleD.getSingle(),SINGLE_BLOB);
    }

    /**
     * OPENROWSET( BULK 'c:\test\values.txt',
     FORMATFILE = 'c:\test\values.fmt')
     */
    public OpenRowSet exampleE = f_openrowset(
            $BulkInsert()
                    .$From("c:\\test\\values.txt")
                    .$With($Formatfile("c:\\test\\values.fmt"))
                    .build()
    );

    @Test
    public void testExampleE(){
        assertNotNull(exampleE.getBulkInsert());
    }

    /**
     * TODO support N string in BulkInsert data_file
     * OPENROWSET (BULK N'D:\data.csv', FORMATFILE =
     'D:\format_no_collation.txt', CODEPAGE = '65001')
     */
    public OpenRowSet exampleF = f_openrowset(
            $BulkInsert()
                    .$From("D:\\data.csv")
                    .$With($Formatfile("D:\\format_no_collation.txt"),$Codepage("65001"))
                    .build()
    );

    @Test
    public void testExampleF(){
        assertNotNull(exampleF.getBulkInsert());
    }

    /**
     * TODO support N string in BulkInsert data_file
     * TODO support N string in BulkInsert FORMATFILE
     * OPENROWSET(BULK N'D:\XChange\test-csv.csv',
     FORMATFILE = N'D:\XChange\test-csv.fmt',
     FIRSTROW=2,
     FORMAT='CSV')
     */
    public OpenRowSet exampleG = f_openrowset(
            $BulkInsert()
                    .$From("D:\\XChange\\test-csv.csv")
                    .$With($Formatfile("D:\\XChange\\test-csv.fmt"),
                            $Firstrow(2),
                            $Format("CSV"))
                    .build()
    );

    @Test
    public void testExampleG(){
        assertNotNull(exampleG.getBulkInsert());
    }

    /**
     * OPENROWSET(
     BULK 'C:\Program Files\Microsoft SQL Server\MSSQL14.CTP1_1\MSSQL\DATA\inv-2017-01-19.csv',
     SINGLE_CLOB)
     */
    public OpenRowSet exampleH = f_openrowset(
            $BulkInsert()
                    .$From("C:\\Program Files\\Microsoft SQL Server\\MSSQL14.CTP1_1\\MSSQL\\DATA\\inv-2017-01-19.csv")
                    .build(),
            SINGLE_CLOB
    );

    @Test
    public void testExampleH(){
        assertNotNull(exampleH.getBulkInsert());
        assertEquals(exampleH.getSingle(),SINGLE_CLOB);
    }

    /**
     * OPENROWSET(
     BULK  'inv-2017-01-19.csv',
     DATA_SOURCE = 'MyAzureInvoices',
     SINGLE_CLOB)
     */
    public OpenRowSet exampleI = f_openrowset(
            $BulkInsert()
                    .$From("inv-2017-01-19.csv")
                    .$With($Datasource("MyAzureInvoices"))
                    .build(),
            SINGLE_CLOB
    );

    @Test
    public void testExampleI(){
        assertNotNull(exampleI.getBulkInsert());
        assertEquals(exampleH.getSingle(),SINGLE_CLOB);
    }

}