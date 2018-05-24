package com.xy.xsql.tsql.builder.chain.statements;

import com.xy.xsql.tsql.model.statements.BulkInsert;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.datatypes.table.TableNameFactory.t;
import static com.xy.xsql.tsql.builder.chain.statements.Statements.$BulkInsert;
import static com.xy.xsql.tsql.builder.chain.statements.BulkInsertBuilder.WithSetter.*;
import static org.junit.Assert.*;

/**
 * Created by xiaoyao9184 on 2017/3/10.
 */
public class BulkInsertBuilderTest {

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
    public BulkInsert exampleA = $BulkInsert()
            .$(t("AdventureWorks2012","Sales","SalesOrderDetail"))
            .$From("f:\\orders\\lineitem.tbl")
            .$With(
                    FIELDTERMINATOR(" |"),
                    ROWTERMINATOR(" |\\n")
            )
            .build();
    // @formatter:on

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

        assertEquals(bulkInsert.getTableOrView().toString(),"AdventureWorks2012.Sales.SalesOrderDetail");
        assertEquals(bulkInsert.getFormDataFile().getString(),"f:\\orders\\lineitem.tbl");
        assertEquals(bulkInsert.getFieldTerminator().getString()," |");
        assertEquals(bulkInsert.getRowTerminator().getString()," |\\n");
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
    public BulkInsert exampleB = $BulkInsert()
            .$(t("AdventureWorks2012","Sales","SalesOrderDetail"))
            .$From("f:\\orders\\lineitem.tbl")
            .$With(
                    FIELDTERMINATOR(" |"),
                    ROWTERMINATOR(" :\\n"),
                    FIRE_TRIGGERS()
            )
            .build();
    // @formatter:on

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

        assertEquals(bulkInsert.getTableOrView().toString(),"AdventureWorks2012.Sales.SalesOrderDetail");
        assertEquals(bulkInsert.getFormDataFile().getString(),"f:\\orders\\lineitem.tbl");
        assertEquals(bulkInsert.getFieldTerminator().getString()," |");
        assertEquals(bulkInsert.getRowTerminator().getString()," :\\n");
        assertTrue(bulkInsert.isFireTriggers());
    }


    // @formatter:off
    //parent+quick
    /**
     * BULK INSERT AdventureWorks2012.Sales.SalesOrderDetail
    FROM ''<drive>:\<path>\<filename>''
    WITH (ROWTERMINATOR = '''+CHAR(10)+''')
     */
    public BulkInsert exampleC = $BulkInsert()
            .$(t("AdventureWorks2012","Sales","SalesOrderDetail"))
            .$From("'<drive>:\\<path>\\<filename>'")
            .$With(
                    ROWTERMINATOR("''+CHAR(10)+''")
            )
            .build();
    // @formatter:on

    @Test
    public void testExampleC(){
        // @formatter:off
        BulkInsert bulkInsert = new BulkInsertBuilder()
                .withTableViewName(t("AdventureWorks2012","Sales","SalesOrderDetail"))
                .withFrom("'<drive>:\\<path>\\<filename>'")
                .withRowTerminator("''+CHAR(10)+''")
                .build();
        // @formatter:on

        assertEquals(bulkInsert.getTableOrView().toString(),"AdventureWorks2012.Sales.SalesOrderDetail");
        assertEquals(bulkInsert.getFormDataFile().getString(),"'<drive>:\\<path>\\<filename>'");
        assertEquals(bulkInsert.getRowTerminator().getString(),"''+CHAR(10)+''");
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
    public BulkInsert exampleD = $BulkInsert()
            .$(t("MyTable"))
            .$From("D:\\data.csv")
            .$With(
                    $CODEPAGE("65001"),
                    $DATAFILETYPE(char_()),
                    FIELDTERMINATOR(",")
            )
            .build();
    // @formatter:on

    @Test
    public void testExampleD(){
        // @formatter:off
        BulkInsert bulkInsert = new BulkInsertBuilder()
                .withTableViewName(t("MyTable"))
                .withCodePage("65001")
                .withDataFileType(char_())
                .withFieldTerminator(",")
                .build();
        // @formatter:on

        assertEquals(bulkInsert.getTableOrView().toString(),"MyTable");
        assertEquals(bulkInsert.getCodePage().getString(),"65001");
        assertEquals(bulkInsert.getDataFileType().getString(),"char");
        assertEquals(bulkInsert.getFieldTerminator().getString(),",");
    }


    // @formatter:off
    //parent+quick
    /**
     * BULK INSERT Sales.Invoices
    FROM '\\share\invoices\inv-2016-07-25.csv'
    WITH (FORMAT = 'CSV')
     */
    public BulkInsert exampleE = $BulkInsert()
            .$(t("Sales","Invoices"))
            .$From("\\\\share\\invoices\\inv-2016-07-25.csv")
            .$With(
                    FORMAT("CSV")
            )
            .build();
    // @formatter:on

    @Test
    public void testExampleE(){
        // @formatter:off
        BulkInsert bulkInsert = new BulkInsertBuilder()
                .withTableViewName(t("Sales","Invoices"))
                .withFrom("\\\\share\\invoices\\inv-2016-07-25.csv")
                .withFormat("CSV")
                .build();
        // @formatter:on

        assertEquals(bulkInsert.getTableOrView().toString(),"Sales.Invoices");
        assertEquals(bulkInsert.getFormDataFile().getString(),"\\\\share\\invoices\\inv-2016-07-25.csv");
        assertEquals(bulkInsert.getFormat().getString(),"CSV");
    }


    // @formatter:off
    //parent+quick
    /**
     * BULK INSERT Sales.Invoices
    FROM 'inv-2017-01-19.csv'
    WITH (DATA_SOURCE = 'MyAzureInvoices',
         FORMAT = 'CSV')
     */
    public BulkInsert exampleF = $BulkInsert()
            .$(t("Sales","Invoices"))
            .$From("inv-2017-01-19.csv")
            .$With(
                    FORMAT("CSV"),
                    DATASOURCE("MyAzureInvoices")
            )
            .build();
    // @formatter:on

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

        assertEquals(bulkInsert.getTableOrView().toString(),"Sales.Invoices");
        assertEquals(bulkInsert.getFormDataFile().getString(),"inv-2017-01-19.csv");
        assertEquals(bulkInsert.getDataSource().getString(),"MyAzureInvoices");
        assertEquals(bulkInsert.getFormat().getString(),"CSV");
    }

}
