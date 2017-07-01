package com.xy.xsql.tsql.core.statement;

import com.xy.xsql.tsql.core.statement.dml.BulkInsertBuilder;
import com.xy.xsql.tsql.model.statement.dml.BulkInsert;
import org.junit.Assert;
import org.junit.Test;

import static com.xy.xsql.tsql.core.element.TableNameFactory.t;
import static com.xy.xsql.tsql.core.statement.dml.BulkInsertBuilder.*;

/**
 * Created by xiaoyao9184 on 2017/3/10.
 */
public class BulkInsertBuilderTest {


    // @formatter:off
    //parent+quick
    public BulkInsert exampleA = BULK_INSERT()
            .$(t("AdventureWorks2012","Sales","SalesOrderDetail"))
            .$From("f:\\orders\\lineitem.tbl")
            .$With(
                    FIELDTERMINATOR(" |"),
                    ROWTERMINATOR(" |\\n")
            )
            .done();
    // @formatter:on

    /**
     * BULK INSERT AdventureWorks2012.Sales.SalesOrderDetail
     FROM 'f:\orders\lineitem.tbl'
     WITH
     (
     FIELDTERMINATOR =' |',
     ROWTERMINATOR =' |\n'
     );
     */
    @Test
    public void testExampleA(){
        // @formatter:off
        BulkInsert bulkInsert = new BulkInsertBuilder()
                .withTableViewName(t("AdventureWorks2012","Sales","SalesOrderDetail"))
                .withFrom("f:\\orders\\lineitem.tbl")
                .withFieldTerminator(" |")
                .withRowTerminator(" |\\n")
                .build();
        // @formatter:on

        Assert.assertEquals(bulkInsert.getTableOrView().toString(),"AdventureWorks2012.Sales.SalesOrderDetail");
        Assert.assertEquals(bulkInsert.getFormDataFile().toString(),"'f:\\orders\\lineitem.tbl'");
        Assert.assertEquals(bulkInsert.getFieldTerminator().toString(),"' |'");
        Assert.assertEquals(bulkInsert.getRowTerminator().toString(),"' |\\n'");
    }


    // @formatter:off
    //parent+quick
    public BulkInsert exampleB = BULK_INSERT()
            .$(t("AdventureWorks2012","Sales","SalesOrderDetail"))
            .$From("f:\\orders\\lineitem.tbl")
            .$With(
                    FIELDTERMINATOR(" |"),
                    ROWTERMINATOR(" :\\n"),
                    FIRE_TRIGGERS()
            )
            .done();
    // @formatter:on

    /**
     * BULK INSERT AdventureWorks2012.Sales.SalesOrderDetail
     FROM 'f:\orders\lineitem.tbl'
     WITH
     (
     FIELDTERMINATOR =' |',
     ROWTERMINATOR = ':\n',
     FIRE_TRIGGERS
     );
     */
    @Test
    public void testExampleB(){
        // @formatter:off
        BulkInsert bulkInsert = new BulkInsertBuilder()
                .withTableViewName(t("AdventureWorks2012","Sales","SalesOrderDetail"))
                .withFrom("f:\\orders\\lineitem.tbl")
                .withFieldTerminator(" |")
                .withRowTerminator(" :\\n")
                .withFireTriggers()
                .build();
        // @formatter:on

        Assert.assertEquals(bulkInsert.getTableOrView().toString(),"AdventureWorks2012.Sales.SalesOrderDetail");
        Assert.assertEquals(bulkInsert.getFormDataFile().toString(),"'f:\\orders\\lineitem.tbl'");
        Assert.assertEquals(bulkInsert.getFieldTerminator().toString(),"' |'");
        Assert.assertEquals(bulkInsert.getRowTerminator().toString(),"' |\\n'");
        Assert.assertTrue(bulkInsert.isFireTriggers());
    }


    // @formatter:off
    //parent+quick
    public BulkInsert exampleC = BULK_INSERT()
            .$(t("AdventureWorks2012","Sales","SalesOrderDetail"))
            .$From("'<drive>:\\<path>\\<filename>'")
            .$With(
                    ROWTERMINATOR("''+CHAR(10)+''")
            )
            .done();
    // @formatter:on

    /**
     * BULK INSERT AdventureWorks2012.Sales.SalesOrderDetail
     FROM ''<drive>:\<path>\<filename>''
     WITH (ROWTERMINATOR = '''+CHAR(10)+''')
     */
    @Test
    public void testExampleC(){
        // @formatter:off
        BulkInsert bulkInsert = new BulkInsertBuilder()
                .withTableViewName(t("AdventureWorks2012","Sales","SalesOrderDetail"))
                .withFrom("'<drive>:\\<path>\\<filename>'")
                .withRowTerminator("''+CHAR(10)+''")
                .build();
        // @formatter:on

        Assert.assertEquals(bulkInsert.getTableOrView().toString(),"AdventureWorks2012.Sales.SalesOrderDetail");
        Assert.assertEquals(bulkInsert.getFormDataFile().toString(),"''<drive>:\\<path>\\<filename>''");
        Assert.assertEquals(bulkInsert.getRowTerminator().toString(),"'''+CHAR(10)+'''");
    }


    // @formatter:off
    //parent+quick
    public BulkInsert exampleD = BULK_INSERT()
            .$(t("MyTable"))
            .$From("D:\\data.csv")
            .$With(
                    CODEPAGE("65001"),
                    DATAFILETYPE(_char()),
                    FIELDTERMINATOR(",")
            )
            .done();
    // @formatter:on

    /**
     * BULK INSERT MyTable
     FROM 'D:\data.csv'
     WITH
     ( CODEPAGE = '65001',
     DATAFILETYPE = 'char',
     FIELDTERMINATOR = ','
     );
     */
    @Test
    public void testExampleD(){
        // @formatter:off
        BulkInsert bulkInsert = new BulkInsertBuilder()
                .withTableViewName(t("MyTable"))
                .withCodePage("56001")
                .withDataFileType(_char())
                .withFieldTerminator(",")
                .build();
        // @formatter:on

        Assert.assertEquals(bulkInsert.getTableOrView().toString(),"MyTable");
        Assert.assertEquals(bulkInsert.getCodePage().toString(),"'56001'");
        Assert.assertEquals(bulkInsert.getDataFileType().toString(),"'char'");
        Assert.assertEquals(bulkInsert.getFieldTerminator().toString(),"','");
    }


    // @formatter:off
    //parent+quick
    public BulkInsert exampleE = BULK_INSERT()
            .$(t("Sales","Invoices"))
            .$From("\\\\share\\invoices\\inv-2016-07-25.csv")
            .$With(
                    FORMAT("CSV")
            )
            .done();
    // @formatter:on

    /**
     * BULK INSERT Sales.Invoices
     FROM '\\share\invoices\inv-2016-07-25.csv'
     WITH (FORMAT = 'CSV');
     );
     */
    @Test
    public void testExampleE(){
        // @formatter:off
        BulkInsert bulkInsert = new BulkInsertBuilder()
                .withTableViewName(t("Sales","Invoices"))
                .withFrom("\\\\share\\invoices\\inv-2016-07-25.csv")
                .withFormat("CSV")
                .build();
        // @formatter:on

        Assert.assertEquals(bulkInsert.getTableOrView().toString(),"Sales.Invoices");
        Assert.assertEquals(bulkInsert.getFormDataFile().toString(),"'\\\\share\\invoices\\inv-2016-07-25.csv'");
        Assert.assertEquals(bulkInsert.getFormat().toString(),"'CSV'");
    }


    // @formatter:off
    //parent+quick
    public BulkInsert exampleF = BULK_INSERT()
            .$(t("Sales","Invoices"))
            .$From("inv-2017-01-19.csv")
            .$With(
                    FORMAT("CSV"),
                    DATASOURCE("MyAzureInvoices")
            )
            .done();
    // @formatter:on

    /**
     * BULK INSERT Sales.Invoices
     FROM 'inv-2017-01-19.csv'
     WITH (DATA_SOURCE = 'MyAzureInvoices',
     FORMAT = 'CSV');
     */
    @Test
    public void testExampleF(){
        // @formatter:off
        BulkInsert bulkInsert = new BulkInsertBuilder()
                .withTableViewName(t("Sales","Invoices"))
                .withFrom("inv-2017-01-19.csv")
                .withDataSource("MyAzureInvoices")
                .withFormat("CSV")
                .build();
        // @formatter:on

        Assert.assertEquals(bulkInsert.getTableOrView().toString(),"Sales.Invoices");
        Assert.assertEquals(bulkInsert.getFormDataFile().toString(),"'inv-2017-01-19.csv'");
        Assert.assertEquals(bulkInsert.getDataSource().toString(),"'MyAzureInvoices'");
        Assert.assertEquals(bulkInsert.getFormat().toString(),"'CSV'");
    }

}
