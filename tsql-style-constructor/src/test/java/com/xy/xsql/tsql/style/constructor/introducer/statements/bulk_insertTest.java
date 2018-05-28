package com.xy.xsql.tsql.style.constructor.introducer.statements;

import com.xy.xsql.tsql.model.statements.BulkInsert;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.datatypes.table.TableNameFactory.t;
import static com.xy.xsql.tsql.style.constructor.introducer.statements.bulk_insert.*;
import static org.junit.Assert.*;

/**
 * Created by xiaoyao9184 on 2018/5/15.
 */
public class bulk_insertTest {

    /*
    Examples
    See https://docs.microsoft.com/zh-cn/sql/t-sql/statements/bulk-insert-transact-sql#examples
     */

    // @formatter:off
    //parent+quick
    /**
     * BULK INSERT AdventureWorks2012.Sales.SalesOrderDetail
       FROM 'f:\orders\lineitem.tbl'
       WITH
          (
             FIELDTERMINATOR =' |',
             ROWTERMINATOR =' |\n'
          )
     */
    public BulkInsert exampleA = BULK_INSERT(
            t("AdventureWorks2012","Sales","SalesOrderDetail")
            ,FROM("f:\\orders\\lineitem.tbl")
            ,WITH(
                    FIELDTERMINATOR(" |"),
                    ROWTERMINATOR(" |\\n")
            )
    ).build();
    // @formatter:on

    @Test
    public void testExampleA(){
        assertEquals(exampleA.getTableOrView().toString(),"AdventureWorks2012.Sales.SalesOrderDetail");
        assertEquals(exampleA.getFormDataFile().getString(),"f:\\orders\\lineitem.tbl");
        assertEquals(exampleA.getFieldTerminator().getString()," |");
        assertEquals(exampleA.getRowTerminator().getString()," |\\n");
    }


    // @formatter:off
    //parent+quick
    /**
     * BULK INSERT AdventureWorks2012.Sales.SalesOrderDetail
       FROM 'f:\orders\lineitem.tbl'
       WITH
         (
            FIELDTERMINATOR =' |',
            ROWTERMINATOR = ':\n',
            FIRE_TRIGGERS
          )
     */
    public BulkInsert exampleB = BULK_INSERT(
            t("AdventureWorks2012","Sales","SalesOrderDetail")
            ,FROM("f:\\orders\\lineitem.tbl")
            ,WITH(
                    FIELDTERMINATOR(" |"),
                    ROWTERMINATOR(" :\\n"),
                    FIRE_TRIGGERS()
            )
    ).build();
    // @formatter:on

    @Test
    public void testExampleB(){
        assertEquals(exampleB.getTableOrView().toString(),"AdventureWorks2012.Sales.SalesOrderDetail");
        assertEquals(exampleB.getFormDataFile().getString(),"f:\\orders\\lineitem.tbl");
        assertEquals(exampleB.getFieldTerminator().getString()," |");
        assertEquals(exampleB.getRowTerminator().getString()," :\\n");
        assertTrue(exampleB.isFireTriggers());
    }


    // @formatter:off
    //parent+quick
    /**
     * BULK INSERT AdventureWorks2012.Sales.SalesOrderDetail
    FROM ''<drive>:\<path>\<filename>''
    WITH (ROWTERMINATOR = '''+CHAR(10)+''')
     */
    public BulkInsert exampleC = BULK_INSERT(
            t("AdventureWorks2012","Sales","SalesOrderDetail")
            ,FROM("'<drive>:\\<path>\\<filename>'")
            ,WITH(
                    ROWTERMINATOR("''+CHAR(10)+''")
            )
    ).build();
    // @formatter:on

    @Test
    public void testExampleC(){
        assertEquals(exampleC.getTableOrView().toString(),"AdventureWorks2012.Sales.SalesOrderDetail");
        assertEquals(exampleC.getFormDataFile().getString(),"'<drive>:\\<path>\\<filename>'");
        assertEquals(exampleC.getRowTerminator().getString(),"''+CHAR(10)+''");
    }


    // @formatter:off
    //parent+quick
    /**
     * BULK INSERT MyTable
    FROM 'D:\data.csv'
    WITH
    ( CODEPAGE = '65001',
        DATAFILETYPE = 'char',
        FIELDTERMINATOR = ','
    )
     */
    public BulkInsert exampleD = BULK_INSERT(
            t("MyTable")
            ,FROM("D:\\data.csv")
            ,WITH(
                    CODEPAGE("65001"),
                    DATAFILETYPE_char(),
                    FIELDTERMINATOR(",")
            )
    ).build();
    // @formatter:on

    @Test
    public void testExampleD(){
        assertEquals(exampleD.getTableOrView().toString(),"MyTable");
        assertEquals(exampleD.getCodePage().getString(),"65001");
        assertEquals(exampleD.getDataFileType().getString(),"char");
        assertEquals(exampleD.getFieldTerminator().getString(),",");
    }


    // @formatter:off
    //parent+quick
    /**
     * BULK INSERT Sales.Invoices
    FROM '\\share\invoices\inv-2016-07-25.csv'
    WITH (FORMAT = 'CSV')
     */
    public BulkInsert exampleE = BULK_INSERT(
            t("Sales","Invoices")
            ,FROM("\\\\share\\invoices\\inv-2016-07-25.csv")
            ,WITH(
                    FORMAT("CSV")
            )
    ).build();
    // @formatter:on

    @Test
    public void testExampleE(){
        assertEquals(exampleE.getTableOrView().toString(),"Sales.Invoices");
        assertEquals(exampleE.getFormDataFile().getString(),"\\\\share\\invoices\\inv-2016-07-25.csv");
        assertEquals(exampleE.getFormat().getString(),"CSV");
    }


    // @formatter:off
    //parent+quick
    /**
     * BULK INSERT Sales.Invoices
    FROM 'inv-2017-01-19.csv'
    WITH (DATA_SOURCE = 'MyAzureInvoices',
         FORMAT = 'CSV')
     */
    public BulkInsert exampleF = BULK_INSERT(
            t("Sales","Invoices")
            ,FROM("inv-2017-01-19.csv")
            ,WITH(
                    FORMAT("CSV"),
                    DATASOURCE("MyAzureInvoices")
            )
    ).build();
    // @formatter:on

    @Test
    public void testExampleF(){
        assertEquals(exampleF.getTableOrView().toString(),"Sales.Invoices");
        assertEquals(exampleF.getFormDataFile().getString(),"inv-2017-01-19.csv");
        assertEquals(exampleF.getDataSource().getString(),"MyAzureInvoices");
        assertEquals(exampleF.getFormat().getString(),"CSV");
    }

}