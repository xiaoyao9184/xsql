package com.xy.xsql.tsql.style.constructor.introducer.statements;

import com.xy.xsql.tsql.model.queries.Select;
import com.xy.xsql.tsql.model.statements.Insert;
import com.xy.xsql.tsql.model.statements.Merge;
import com.xy.xsql.tsql.style.constructor.introducer.queries.from;
import com.xy.xsql.tsql.style.constructor.introducer.queries.output;
import com.xy.xsql.tsql.style.constructor.introducer.queries.select_;
import com.xy.xsql.tsql.style.constructor.introducer.queries.with;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.datatypes.table.ColumnNameFactory.c;
import static com.xy.xsql.tsql.builder.chain.datatypes.table.TableNameFactory.t;
import static com.xy.xsql.tsql.builder.chain.elements.expressions.Expressions.*;
import static com.xy.xsql.tsql.builder.chain.queries.predicates.Predicates.*;
import static com.xy.xsql.tsql.style.constructor.introducer.queries.from.*;
import static com.xy.xsql.tsql.style.constructor.introducer.queries.hints.table_hint_limited.*;
import static com.xy.xsql.tsql.style.constructor.introducer.queries.option.*;
import static com.xy.xsql.tsql.style.constructor.introducer.queries.output.$action;
import static com.xy.xsql.tsql.style.constructor.introducer.queries.output.INSERTED;
import static com.xy.xsql.tsql.style.constructor.introducer.queries.output.OUTPUT;
import static com.xy.xsql.tsql.style.constructor.introducer.queries.select.group_by.*;
import static com.xy.xsql.tsql.style.constructor.introducer.queries.select.order_by.DESC;
import static com.xy.xsql.tsql.style.constructor.introducer.queries.select.order_by.ORDER_BY;
import static com.xy.xsql.tsql.style.constructor.introducer.queries.select.select.AS;
import static com.xy.xsql.tsql.style.constructor.introducer.queries.top.TOP;
import static com.xy.xsql.tsql.style.constructor.introducer.queries.values.VALUES;
import static com.xy.xsql.tsql.style.constructor.introducer.queries.where.WHERE;
import static com.xy.xsql.tsql.style.constructor.introducer.statements.insert.*;
import static com.xy.xsql.tsql.style.constructor.introducer.queries.update.SET;
import static com.xy.xsql.tsql.style.constructor.introducer.statements.merge.WHEN_MATCHED;
import static com.xy.xsql.tsql.style.constructor.introducer.statements.merge.MERGE;
import static com.xy.xsql.tsql.style.constructor.introducer.statements.merge.UPATE;
import static com.xy.xsql.tsql.style.constructor.introducer.statements.merge.DELETE;
import static com.xy.xsql.tsql.style.constructor.introducer.statements.merge.THEN;
import static com.xy.xsql.tsql.style.constructor.introducer.statements.merge.USING;
import static org.junit.Assert.*;

/**
 * Created by xiaoyao9184 on 2018/5/15.
 */
public class insertTest {

    /*
    Basic Syntax
    See https://docs.microsoft.com/zh-cn/sql/t-sql/statements/insert-transact-sql#BasicSyntax
     */

    // @formatter:off
    //parent+quick
    /**
     * INSERT INTO Production.UnitMeasure
    VALUES (N'FT', N'Feet', '20080414')
     */
    public Insert exampleA = INSERT(
            INTO()
            ,t("Production","UnitMeasure")
            ,VALUES(e_n_string("FT"),e_n_string("Feet"),e_string("20080414"))
    ).build();
    // @formatter:on

    @Test
    public void testExampleA(){
        assertTrue(exampleA.isUseInto());
        assertEquals(exampleA.getTableName().toString(),"Production.UnitMeasure");
        assertEquals(exampleA.getValues().getRowValueExpressionListGroup().size(),1);
        assertEquals(exampleA.getValues().getRowValueExpressionListGroup().get(0).size(),3);
    }


    // @formatter:off
    //parent+quick
    /**
     * INSERT INTO Production.UnitMeasure
    VALUES (N'FT2', N'Square Feet ', '20080923'), (N'Y', N'Yards', '20080923')
        , (N'Y3', N'Cubic Yards', '20080923')
     */
    public Insert exampleB = INSERT(
            INTO()
            ,t("Production","UnitMeasure")
            ,VALUES(e_n_string("FT2"),e_n_string("Square Feet "),e_string("20080923")
            ).$$(e_n_string("Y"),e_n_string("Yards"),e_string("20080923")
            ).$$(e_n_string("Y3"),e_n_string("Cubic Yards"),e_string("20080923"))
    ).build();
    // @formatter:on

    @Test
    public void testExampleB(){
        assertTrue(exampleB.isUseInto());
        assertEquals(exampleB.getTableName().toString(),"Production.UnitMeasure");
        assertEquals(exampleB.getValues().getRowValueExpressionListGroup().size(),3);
        assertEquals(exampleB.getValues().getRowValueExpressionListGroup().get(0).size(),3);
        assertEquals(exampleB.getValues().getRowValueExpressionListGroup().get(1).size(),3);
        assertEquals(exampleB.getValues().getRowValueExpressionListGroup().get(2).size(),3);
    }


    // @formatter:off
    //parent+quick
    /**
     * INSERT INTO Production.UnitMeasure (Name, UnitMeasureCode,
        ModifiedDate)
    VALUES (N'Square Yards', N'Y2', GETDATE())
     */
    public Insert exampleC = INSERT(
            INTO()
            ,t("Production","UnitMeasure")
            ,$(c("Name"),c("UnitMeasureCode"),c("ModifiedDate"))
            ,VALUES(e_n_string("Square Yards"),e_n_string("Y2"),e("GETDATE()"))
    ).build();
    // @formatter:on

    /**
     * INSERT INTO Production.UnitMeasure (Name, UnitMeasureCode,
     ModifiedDate)
     VALUES (N'Square Yards', N'Y2', GETDATE());
     */
    @Test
    public void testExampleC(){
        assertTrue(exampleC.isUseInto());
        assertEquals(exampleC.getTableName().toString(),"Production.UnitMeasure");
        assertEquals(exampleC.getColumns().size(),3);
        assertEquals(exampleC.getValues().getRowValueExpressionListGroup().size(),1);
        assertEquals(exampleC.getValues().getRowValueExpressionListGroup().get(0).size(),3);
    }

    /*
    Handling Column Values
    See https://docs.microsoft.com/zh-cn/sql/t-sql/statements/insert-transact-sql#ColumnValues
     */

    // @formatter:off
    //parent+quick
    /**
     * INSERT INTO dbo.T1 (column_4)
        VALUES ('Explicit value')
     */
    public Insert exampleD1 = INSERT(
            INTO()
            ,t("dbo","T1")
            ,$(c("column_4"))
            ,VALUES(e_string("Explicit value"))
    ).build();
    // @formatter:on


    // @formatter:off
    /**
     * IINSERT INTO dbo.T1 (column_2, column_4)
        VALUES ('Explicit value', 'Explicit value')
     */
    public Insert exampleD2 = INSERT(
            INTO()
            ,t("dbo","T1")
            ,$(c("column_2"),c("column_4"))
            ,VALUES(e_string("Explicit value"),e_string("Explicit value"))
    ).build();
    // @formatter:on


    // @formatter:off
    /**
     * INSERT INTO dbo.T1 (column_2)
        VALUES ('Explicit value')
     */
    public Insert exampleD3 = INSERT(
            INTO()
            ,t("dbo","T1")
            ,$(c("column_2"))
            ,VALUES(e_string("Explicit value"))
    ).build();
    // @formatter:on


    // @formatter:off
    /**
     * INSERT INTO T1 DEFAULT VALUES
     */
    public Insert exampleD4 = INSERT(
            INTO()
            ,t("T1")
            ,DEFAULT_VALUES()
    ).build();
    // @formatter:on

    @Test
    public void testExampleD(){
        assertTrue(exampleD1.isUseInto());
        assertEquals(exampleD1.getTableName().toString(),"dbo.T1");
        assertEquals(exampleD1.getColumns().size(),1);
        assertEquals(exampleD1.getValues().getRowValueExpressionListGroup().size(),1);
        assertEquals(exampleD1.getValues().getRowValueExpressionListGroup().get(0).size(),1);

        assertTrue(exampleD2.isUseInto());
        assertEquals(exampleD2.getTableName().toString(),"dbo.T1");
        assertEquals(exampleD2.getColumns().size(),2);
        assertEquals(exampleD2.getValues().getRowValueExpressionListGroup().size(),1);
        assertEquals(exampleD2.getValues().getRowValueExpressionListGroup().get(0).size(),2);

        assertTrue(exampleD3.isUseInto());
        assertEquals(exampleD3.getTableName().toString(),"dbo.T1");
        assertEquals(exampleD3.getColumns().size(),1);
        assertEquals(exampleD3.getValues().getRowValueExpressionListGroup().size(),1);
        assertEquals(exampleD3.getValues().getRowValueExpressionListGroup().get(0).size(),1);

        assertTrue(exampleD4.isUseInto());
        assertEquals(exampleD4.getTableName().toString(),"T1");
        assertEquals(exampleD4.isUseDefaultValues(),true);
    }


    // @formatter:off
    //parent+quick
    /**
     * INSERT T1 VALUES ('Row #1')
     */
    public Insert exampleE1 = INSERT(
            t("T1")
            ,VALUES(e_string("Row #1"))
    ).build();
    // @formatter:on


    // @formatter:off
    /**
     * INSERT T1 (column_2) VALUES ('Row #2')
     */
    public Insert exampleE2 = INSERT(
            t("T1")
            ,$(c("column_2"))
            ,VALUES(e_string("Row #2"))
    ).build();
    // @formatter:on


    // @formatter:off
    /**
     * INSERT INTO T1 (column_1,column_2)
    VALUES (-99, 'Explicit identity value')
     */
    public Insert exampleE3 = INSERT(
            INTO()
            ,t("T1")
            ,$(c("column_1"),c("column_2"))
            ,VALUES(e_number(-99),e_string("Explicit identity value"))
    ).build();
    // @formatter:on


    // @formatter:off
    //parent+quick
    /**
     * INSERT INTO dbo.T1 (column_2)
        VALUES (NEWID())
     */
    public Insert exampleF1 = INSERT(
            INTO()
            ,t("dbo","T1")
            ,$(c("column_2"))
            ,VALUES(e("NEWID()"))
    ).build();
    // @formatter:on


    // @formatter:off
    /**
     * INSERT INTO T1 DEFAULT VALUES
     */
    public Insert exampleF2 = INSERT(
            INTO()
            ,t("T1")
            ,DEFAULT_VALUES()
    ).build();
    // @formatter:on


    // @formatter:off
    //parent+quick
    /**
     * INSERT INTO dbo.Points (PointValue) VALUES (CONVERT(Point, '3,4'))
     */
    public Insert exampleG1 = INSERT(
            INTO()
            ,t("dbo","Points")
            ,$(c("PointValue"))
            ,VALUES(e("CONVERT(Point, '3,4')"))
    ).build();

    /**
     * INSERT INTO dbo.Points (PointValue) VALUES (CONVERT(Point, '1,5'))
     */
    public Insert exampleG2 = INSERT(
            INTO()
            ,t("dbo","Points")
            ,$(c("PointValue"))
            ,VALUES(e("CONVERT(Point, '1,5')"))
    ).build();

    /**
     * INSERT INTO dbo.Points (PointValue) VALUES (CAST ('1,99' AS Point))
     */
    public Insert exampleG3 = INSERT(
            INTO()
            ,t("dbo","Points")
            ,$(c("PointValue"))
            ,VALUES(e("CAST ('1,99' AS Point)"))
    ).build();
    // @formatter:on


    /*
    Inserting Data from Other Tables
    See https://docs.microsoft.com/zh-cn/sql/t-sql/statements/insert-transact-sql#OtherTables
     */


    // @formatter:off
    private Select derivedTableH1 = select_.$(
            select_.SELECT(
                    select_.$(e_string("SELECT"))
                    .$$(c("sp","BusinessEntityID"))
                    .$$(c("c","LastName"))
                    .$$(c("sp","SalesYTD"))
                    ,FROM(t("Sales","SalesPerson"),AS("sp"))
                            .INNER(JOIN(t("Person","Person"),AS("c"))
                                    ,ON(p_equal(c("",""),c("","")))
                            )
                    ,WHERE(p_equal(
                                c("sp","BusinessEntityID"),
                                c("c","LastName")
                        ))
            )
            ,ORDER_BY(c("sp.BusinessEntityID"))
                    .$$(c("c.LastName"))
    ).build();
    //parent+quick
    /**
     * INSERT INTO dbo.EmployeeSales
    SELECT 'SELECT', sp.BusinessEntityID, c.LastName, sp.SalesYTD
    FROM Sales.SalesPerson AS sp
    INNER JOIN Person.Person AS c
        ON sp.BusinessEntityID = c.BusinessEntityID
    WHERE sp.BusinessEntityID LIKE '2%'
    ORDER BY sp.BusinessEntityID, c.LastName
     */
    public Insert exampleH1 = INSERT(
            INTO()
            ,t("dbo","EmployeeSales")
            //TODO derived_table
            ,DEFAULT_VALUES()
//            .$Select(derivedTableH1)
    ).build();

    /**
     * --INSERT...EXECUTE procedure example
     INSERT INTO dbo.EmployeeSales
     EXECUTE dbo.uspGetEmployeeSales
     */
    public Insert exampleH2 = INSERT(
            INTO()
            ,t("dbo","EmployeeSales")
            //TODO execute_statement
            ,DEFAULT_VALUES()
//            .$Execute()
    ).build();

    /**
     * INSERT INTO dbo.EmployeeSales
     EXECUTE
     ('
     SELECT ''EXEC STRING'', sp.BusinessEntityID, c.LastName,
     sp.SalesYTD
     FROM Sales.SalesPerson AS sp
     INNER JOIN Person.Person AS c
     ON sp.BusinessEntityID = c.BusinessEntityID
     WHERE sp.BusinessEntityID LIKE ''2%''
     ORDER BY sp.BusinessEntityID, c.LastName
     ')
     */
    public Insert exampleH3 = INSERT(
            INTO()
            ,t("dbo","EmployeeSales")
            //TODO execute_statement
            ,DEFAULT_VALUES()
//            .$Execute()
    ).build();
    // @formatter:on


    // @formatter:off
    private Select.QuerySpecification querySpecificationI = select_.SELECT(
            select_.$(c("e","BusinessEntityID"))
            .$$(c("c","LastName"))
            .$$(c("c","FirstName"))
            .$$(c("pp","PhoneNumber"))
            .$$(c("a","AddressLine1"))
            .$$(c("a","City"))
            .$$(c("sp","StateProvinceCode"))
            .$$(c("a","PostalCode"))
            .$$(c("e","CurrentFlag"))
            ,FROM(t("HumanResources","Employee"),AS("e"))
                    .INNER(JOIN(t("Person","BusinessEntityAddress"),AS("bea"))
                            ,ON(p_equal(
                                c("e","BusinessEntityID"),
                                c("bea","BusinessEntityID")))
                    ).INNER(JOIN(t("Person","Address"),AS("a"))
                            ,ON(p_equal(
                                c("bea","AddressID"),
                                c("a","AddressID")))
                    ).INNER(JOIN(t("Person","PersonPhone"),AS("pp"))
                            ,ON(p_equal(
                                c("e","BusinessEntityID"),
                                c("pp","BusinessEntityID")))
                    ).INNER(JOIN(t("Person","StateProvince"),AS("sp"))
                            ,ON(p_equal(
                                c("a","StateProvinceID"),
                                c("sp","StateProvinceID")))
                    ).INNER(JOIN(t("Person","Person"),AS("c"))
                            ,ON(p_equal(
                                c("e","BusinessEntityID"),
                                c("c","BusinessEntityID")))
                    )
    ).build();

    private Select.QuerySpecification derivedTableI = select_.SELECT(
            select_.$(c("EmpID"))
            .$$(c("LastName"))
            .$$(c("FirstName"))
            .$$(c("Phone"))
            .$$(c("Address"))
            .$$(c("City"))
            .$$(c("StateProvince"))
            .$$(c("PostalCode"))
            .$$(c("CurrentFlag"))
            ,FROM(t("EmployeeTemp"))
    ).build();
    //parent+quick
    /**
     * WITH EmployeeTemp (EmpID, LastName, FirstName, Phone,
     Address, City, StateProvince,
     PostalCode, CurrentFlag)
     AS (SELECT
     e.BusinessEntityID, c.LastName, c.FirstName, pp.PhoneNumber,
     a.AddressLine1, a.City, sp.StateProvinceCode,
     a.PostalCode, e.CurrentFlag
     FROM HumanResources.Employee e
     INNER JOIN Person.BusinessEntityAddress AS bea
     ON e.BusinessEntityID = bea.BusinessEntityID
     INNER JOIN Person.Address AS a
     ON bea.AddressID = a.AddressID
     INNER JOIN Person.PersonPhone AS pp
     ON e.BusinessEntityID = pp.BusinessEntityID
     INNER JOIN Person.StateProvince AS sp
     ON a.StateProvinceID = sp.StateProvinceID
     INNER JOIN Person.Person as c
     ON e.BusinessEntityID = c.BusinessEntityID
     )
     INSERT INTO HumanResources.NewEmployee
     SELECT EmpID, LastName, FirstName, Phone,
     Address, City, StateProvince, PostalCode, CurrentFlag
     FROM EmployeeTemp
     */
    public Insert exampleI = $(
            with.WITH(
                    "EmployeeTemp"
                    ,with.$(c("EmpID"),c("LastName"),c("FirstName"),c("Phone"))
                    ,with.AS(querySpecificationI)
            )
            ,INSERT(
                    INTO()
                    ,t("HumanResources","NewEmployee")
            //TODO derived_table
                    ,DEFAULT_VALUES()
//            .$Select(derivedTableI1)
            )
    ).build();


    // @formatter:off
    private Select derivedTableJ1 = select_.$(
            select_.SELECT(
                select_.$(c("sp","BusinessEntityID"))
                .$$(c("c","LastName"))
                .$$(c("c","FirstName"))
                .$$(c("sp","SalesYTD"))
                ,FROM(t("Sales","SalesPerson"),AS("sp"))
                        .$(JOIN(t("Person","Person"),AS("c"))
                                ,ON(p_equal(
                                        c("sp","BusinessEntityID"),
                                        c("c","BusinessEntityID")
                                )))
                ,WHERE(p_greater(
                        c("sp","SalesYTD"),
                        e_number(250000.00)
                ))
            )
            ,ORDER_BY(c("sp","SalesYTD"),DESC())
    ).build();

    /**
     * INSERT TOP(5)INTO dbo.EmployeeSales
     OUTPUT inserted.EmployeeID, inserted.FirstName,
     inserted.LastName, inserted.YearlySales
     SELECT sp.BusinessEntityID, c.LastName, c.FirstName, sp.SalesYTD
     FROM Sales.SalesPerson AS sp
     INNER JOIN Person.Person AS c
     ON sp.BusinessEntityID = c.BusinessEntityID
     WHERE sp.SalesYTD > 250000.00
     ORDER BY sp.SalesYTD DESC
     */
    public Insert exampleJ1 = INSERT(
            TOP(5)
            ,t("dbo","EmployeeSales")
            ,OUTPUT(
                    INSERTED("EmployeeID")
                    .INSERTED("FirstName")
                    .INSERTED("LastName")
                    .INSERTED("YearlySales"))
            //TODO derived_table
            ,DEFAULT_VALUES()
//            .$Select(derivedTableJ1)
    ).build();
    // @formatter:on


    // @formatter:off
    private Select derivedTableJ2 = select_.$(
            select_.SELECT(
                TOP(5)
                ,select_.$(c("sp","BusinessEntityID"))
                .$$(c("c","LastName"))
                .$$(c("c","FirstName"))
                .$$(c("sp","SalesYTD"))
                ,FROM(t("Sales","SalesPerson"),AS("sp"))
                        .INNER(JOIN(t("Person","Person"),AS("c"))
                                ,ON(p_equal(
                                    c("sp","BusinessEntityID"),
                                    c("c","BusinessEntityID")
                            )))
                ,WHERE(p_greater(
                        c("sp","SalesYTD"),
                        e_number(250000.00)
                ))
            )
            ,ORDER_BY(c("sp","SalesYTD"),DESC())
    ).build();

    /**
     * INSERT INTO dbo.EmployeeSales
     OUTPUT inserted.EmployeeID, inserted.FirstName,
     inserted.LastName, inserted.YearlySales
     SELECT TOP (5) sp.BusinessEntityID, c.LastName, c.FirstName, sp.SalesYTD
     FROM Sales.SalesPerson AS sp
     INNER JOIN Person.Person AS c
     ON sp.BusinessEntityID = c.BusinessEntityID
     WHERE sp.SalesYTD > 250000.00
     ORDER BY sp.SalesYTD DESC
     */
    public Insert exampleJ2 = INSERT(
            t("dbo","EmployeeSales")
            ,OUTPUT(INSERTED("EmployeeID")
                    .INSERTED("FirstName")
                    .INSERTED("LastName")
                    .INSERTED("YearlySales"))
            //TODO derived_table
            ,DEFAULT_VALUES()
//            .$Select(derivedTableJ2)
    ).build();
    // @formatter:on

    /*
    Specifying Target Objects Other Than Standard Tables
    See https://docs.microsoft.com/zh-cn/sql/t-sql/statements/insert-transact-sql#TargetObjects
     */


    // @formatter:off
    /**
     * INSERT INTO V1
     VALUES ('Row 1',1)
     */
    public Insert exampleK = INSERT(
            INTO()
            ,t("V1")
            ,VALUES(e_string("Row 1"),e_number(1))
    ).build();
    // @formatter:on


    // @formatter:off
    private Select.QuerySpecification derivedTableL = select_.SELECT(
            select_.$(c("LocationID"))
            .$$(c("CostRate"))
            .$$(e("GETDATE()"))
            ,FROM(t("Production","Location"))
            ,WHERE(p_greater(
                    c("CostRate"),
                    e_number(0)
            ))
    ).build();
    /**
     * -- Insert values into the table variable.
    INSERT INTO @MyTableVar (LocationID, CostRate, ModifiedDate)
    SELECT LocationID, CostRate, GETDATE()
    FROM Production.Location
    WHERE CostRate > 0
     */
    public Insert exampleL = INSERT(
            INTO()
            //TODO ? table variable
            ,t("@MyTableVar")
//            .$(e_variable("MyTableVar"))
            ,$(c("LocationID")
                    ,c("CostRate")
                    ,c("ModifiedDate"))
            //TODO derived_table
            ,DEFAULT_VALUES()
//            .$Select(derivedTableL)
    ).build();
    // @formatter:on

    /*
    Inserting Rows into a Remote Table
    See https://docs.microsoft.com/zh-cn/sql/t-sql/statements/insert-transact-sql#RemoteTables
     */


    // @formatter:off
    /**
     * -- Specify the remote data source in the FROM clause using a four-part name
     -- in the form linked_server.catalog.schema.object.

     INSERT INTO MyLinkServer.AdventureWorks2012.HumanResources.Department (Name, GroupName)
     VALUES (N'Public Relations', N'Executive General and Administration')
     */
    public Insert exampleM = INSERT(
            INTO()
            ,t("MyLinkServer","AdventureWorks2012","HumanResources","Department")
            ,$(c("Name")
                    ,c("GroupName"))
            ,VALUES(e_n_string("Public Relations"),
                    e_n_string("Executive General and Administration"))
    ).build();
    // @formatter:on


    // @formatter:off
    /**
     * INSERT OPENQUERY (MyLinkServer,
        'SELECT Name, GroupName
         FROM AdventureWorks2012.HumanResources.Department')
    VALUES ('Environmental Impact', 'Engineering')
     */
    public Insert exampleN = INSERT(
            //TODO rowset_function_limited
            t("OPENDATASOURCE('SQLNCLI',\n" +
"        'Data Source= <server_name>; Integrated Security=SSPI')\n" +
"        .AdventureWorks2012.HumanResources.Department (Name, GroupName)")
//            .$Fun()
            ,VALUES(e_string("Environmental Impact"),
                    e_string("Engineering"))
    ).build();
    // @formatter:on


    // @formatter:off
    /**
     * -- Use the OPENDATASOURCE function to specify the remote data source.
    -- Specify a valid server name for Data Source using the format
    -- server_name or server_nameinstance_name.

    INSERT INTO OPENDATASOURCE('SQLNCLI',
        'Data Source= <server_name>; Integrated Security=SSPI')
        .AdventureWorks2012.HumanResources.Department (Name, GroupName)
        VALUES (N'Standards and Methods', 'Quality Assurance')
     */
    public Insert exampleO = INSERT(
            INTO()
            //TODO rowset_function_limited
            ,t("OPENDATASOURCE('SQLNCLI',\n" +
"        'Data Source= <server_name>; Integrated Security=SSPI')\n" +
"        .AdventureWorks2012.HumanResources.Department (Name, GroupName)")
//            .$Fun()
            ,VALUES(e_string("Standards and Methods"),
                    e_string("Quality Assurance"))
    ).build();
    // @formatter:on


    // @formatter:off
    private Select.QuerySpecification derivedTableP = select_.SELECT(
            select_.$(t("T"))
            ,FROM(t("Insured_Customers"),AS("T1"))
                    .$(JOIN(t("CarSensor_Data"),AS("T2"))
                            ,ON(p_equal(
                                    c("T1","CustomerKey"),
                                    c("T2","CustomerKey")
                            )))
            ,WHERE(p_equal(
                        c("T2","YearMeasured"),
                        e_number(2009)
                )).AND(p_greater(
                        c("T2","Speed"),
                        e_number(40)
                ))
    ).build();

    /**
     * -- Export data: Move old data to Hadoop while keeping
    -- it query-able via external table.

    INSERT INTO dbo.FastCustomer2009
    SELECT T.* FROM Insured_Customers T1 JOIN CarSensor_Data T2
    ON (T1.CustomerKey = T2.CustomerKey)
    WHERE T2.YearMeasured = 2009 and T2.Speed > 40
     */
    public Insert exampleP = INSERT(
            INTO()
            ,t("dbo","FastCustomer2009")
            //TODO derived_table
            ,DEFAULT_VALUES()
//            .$Select(derivedTableP)
    ).build();
    // @formatter:on

    /*
    Bulk Loading Data from Tables or Data Files
    See https://docs.microsoft.com/zh-cn/sql/t-sql/statements/insert-transact-sql#BulkLoad
     */


    // @formatter:off
    private Select.QuerySpecification derivedTableQ = select_.SELECT(
            select_.$()
            ,FROM(t("Sales","SalesOrderDetail"))
    ).build();

    /**
     * -- Transfer data from Sales.SalesOrderDetail to Sales.SalesHistory
     INSERT INTO Sales.SalesHistory WITH (TABLOCK)
         (SalesOrderID,
         SalesOrderDetailID,
         CarrierTrackingNumber,
         OrderQty,
         ProductID,
         SpecialOfferID,
         UnitPrice,
         UnitPriceDiscount,
         LineTotal,
         rowguid,
         ModifiedDate)
     SELECT * FROM Sales.SalesOrderDetail
     */
    public Insert exampleQ = INSERT(
            INTO()
            ,t("Sales","SalesHistory")
            ,WITH(TABLOCK())
            ,$(c("SalesOrderID")
                    ,c("SalesOrderDetailID")
                    ,c("CarrierTrackingNumber")
                    ,c("OrderQty")
                    ,c("ProductID")
                    ,c("SpecialOfferID")
                    ,c("UnitPrice")
                    ,c("UnitPriceDiscount")
                    ,c("LineTotal")
                    ,c("rowguid")
                    ,c("ModifiedDate"))
            //TODO derived_table
            ,DEFAULT_VALUES()
//            .$Select(derivedTableQ)
    ).build();
    // @formatter:on


    // @formatter:off
    private Select.QuerySpecification derivedTableR = select_.SELECT(
            select_.$(c("b","Name"))
            .$$(c("b","GroupName"))
            ,FROM(
                    //TODO function
                    t("OPENROWSET (\n" +
"        BULK 'C:SQLFilesDepartmentData.txt',\n" +
"        FORMATFILE = 'C:SQLFilesBulkloadFormatFile.xml',\n" +
"        ROWS_PER_BATCH = 15000"),AS("b"))
    ).build();

    /**
     * INSERT INTO HumanResources.Department WITH (IGNORE_TRIGGERS) (Name, GroupName)
    SELECT b.Name, b.GroupName
    FROM OPENROWSET (
        BULK 'C:SQLFilesDepartmentData.txt',
        FORMATFILE = 'C:SQLFilesBulkloadFormatFile.xml',
        ROWS_PER_BATCH = 15000)AS b
     */
    public Insert exampleR = INSERT(
            INTO()
            ,t("HumanResources","Department")
            ,WITH(IGNORE_TRIGGERS())
            ,$(c("Name")
                    ,c("GroupName"))
            //TODO derived_table
            ,DEFAULT_VALUES()
//            .$Select(derivedTableR)
    ).build();
    // @formatter:on

    /*
    Overriding the Default Behavior of the Query Optimizer by Using Hints
    See https://docs.microsoft.com/zh-cn/sql/t-sql/statements/insert-transact-sql#TableHints
     */


    // @formatter:off
    /**
     * INSERT INTO Production.Location WITH (XLOCK)
     (Name, CostRate, Availability)
     VALUES ( N'Final Inventory', 15.00, 80.00)
     */
    public Insert exampleS = INSERT(
            INTO()
            ,t("Production","Location")
            ,WITH(XLOCK())
            ,$(c("Name")
                    ,c("CostRate")
                    ,c("Availability"))
            ,VALUES(e_n_string("Final Inventory"),
                    e_number(15.00),
                    e_number(80.00)
            )
    ).build();
    // @formatter:on

    /*
    Capturing the Results of the INSERT Statement
    See https://docs.microsoft.com/zh-cn/sql/t-sql/statements/insert-transact-sql#CaptureResults
     */


    // @formatter:off
    /**
     * INSERT Production.ScrapReason
        OUTPUT INSERTED.ScrapReasonID, INSERTED.Name, INSERTED.ModifiedDate
            INTO @MyTableVar
    VALUES (N'Operator error', GETDATE())
     */
    public Insert exampleT = INSERT(
            t("Production","ScrapReason")
            ,OUTPUT(INSERTED("ScrapReasonID")
                    .INSERTED("Name")
                    .INSERTED("ModifiedDate")
                    ,output.INTO("MyTableVar")
            )
            ,VALUES(e_n_string("Operator error"),
                    e("GETDATE()")
            )
    ).build();
    // @formatter:on


    // @formatter:off
    private Select derivedTableU = select_.$(
            select_.SELECT(
                select_.$(c("c","FirstName"))
                .$$(c("c","LastName"))
                .$$(c("sp","SalesYTD"))
                ,FROM(t("Sales","SalesPerson"),AS("sp"))
                            .INNER(JOIN(t("Person","Person"),AS("c"))
                                    ,ON(p_equal(
                                            c("sp","BusinessEntityID"),
                                            c("c","BusinessEntityID")
                                    )))
                ,WHERE(p_like(
                            c("sp","BusinessEntityID"),
                            e_string("2%")
                    ))
            )
            ,ORDER_BY(c("c","LastName"))
                .$$(c("c","FirstName"))
    ).build();

    /**
     * INSERT INTO dbo.EmployeeSales (LastName, FirstName, CurrentSales)
      OUTPUT INSERTED.LastName,
             INSERTED.FirstName,
             INSERTED.CurrentSales
      INTO @MyTableVar
        SELECT c.LastName, c.FirstName, sp.SalesYTD
        FROM Sales.SalesPerson AS sp
        INNER JOIN Person.Person AS c
            ON sp.BusinessEntityID = c.BusinessEntityID
        WHERE sp.BusinessEntityID LIKE '2%'
        ORDER BY c.LastName, c.FirstName
     */
    public Insert exampleU = INSERT(
            INTO()
            ,t("dbo","EmployeeSales")
            ,$(c("LastName"),c("FirstName"),c("CurrentSales"))
            ,OUTPUT(INSERTED("LastName")
                    .INSERTED("FirstName")
                    .INSERTED("CurrentSales")
                    , output.INTO(t("MyTableVar")))
            //TODO derived_table
            ,DEFAULT_VALUES()
//            .$Select(derivedTableU)
    ).build();
    // @formatter:on


    // @formatter:off
//    SELECT ProductID, GETDATE()
//     FROM
//     (   MERGE Production.ProductInventory AS pi
//         USING (SELECT ProductID, SUM(OrderQty) FROM Sales.SalesOrderDetail AS sod
//         JOIN Sales.SalesOrderHeader AS soh
//         ON sod.SalesOrderID = soh.SalesOrderID
//         $AND soh.OrderDate = '20070401'
//         GROUP BY ProductID) AS src (ProductID, OrderQty)
//         ON (pi.ProductID = src.ProductID)
//     WHERE Action = 'DELETE'
//         WHEN MATCHED $AND pi.Quantity - src.OrderQty <= 0
//         THEN DELETE
//         WHEN MATCHED
//         THEN UPDATE SET pi.Quantity = pi.Quantity - src.OrderQty
//         OUTPUT $action, deleted.ProductID) AS Changes (Action, ProductID)
    private Merge derivedTableV0 = MERGE(
            t("Production","ProductInventory")
            ,AS("pi")
            ,USING(select_.$(select_.SELECT(
                        select_.$(c("ProductID"))
                        .$$(e("SUM(OrderQty)"))
                        ,FROM(t("Sales","SalesOrderDetail"),AS("sod"))
                                .$(JOIN(t("Sales","SalesOrderHeader"),AS("soh"))
                                        ,from.ON(p_equal(
                                            c("sod","SalesOrderID"),
                                            c("soh","SalesOrderID")
                                        )).AND(p_equal(
                                                c("soh","OrderDate"),
                                                e_string("20070401")
                                        ))
                                )
                        ,GROUP_BY(c("ProductID"))
                )).build()
                ,AS("src")
                ,"ProductID","OrderQty")
            ,merge.ON(p_equal(
                    c("pi.ProductID"),
                    c("src.ProductID")
            ))
            ,WHEN_MATCHED(merge.AND(p_less_equal(e_subtraction(
                            c("pi","Quantity"),
                            c("src","OrderQty")),
                            e_number(0)))
                ,THEN(DELETE()))
            .WHEN_MATCHED(THEN(
                    UPATE(SET(c("pi","Quantity"),
                        e_subtraction(
                                c("pi","Quantity"),
                                c("src","OrderQty")
                        )))
            ))
            ,OUTPUT(
                    $action()
                    .DELETED("ProductID")
            )
    ).build();


    private Select.QuerySpecification derivedTableV = select_.SELECT(
            select_.$(c("ProductID"))
            .$$(e("GETDATE()"))
            ,FROM(
                //TODO derived_table
//                .$(derivedTableV0).$As("sp","Action","ProductID")
                    t("")
            )
            ,WHERE(p_equal(
                    c("Action"),
                    e_string("DELETE")
            ))
    ).build();

    /**
     * INSERT INTO Production.ZeroInventory (DeletedProductID, RemovedOnDate)
     SELECT ProductID, GETDATE()
     FROM
     (   MERGE Production.ProductInventory AS pi
         USING (SELECT ProductID, SUM(OrderQty) FROM Sales.SalesOrderDetail AS sod
         JOIN Sales.SalesOrderHeader AS soh
         ON sod.SalesOrderID = soh.SalesOrderID
         AND soh.OrderDate = '20070401'
         GROUP BY ProductID) AS src (ProductID, OrderQty)
         ON (pi.ProductID = src.ProductID)
         WHEN MATCHED AND pi.Quantity - src.OrderQty <= 0
         THEN DELETE
         WHEN MATCHED
         THEN UPDATE SET pi.Quantity = pi.Quantity - src.OrderQty
         OUTPUT $action, deleted.ProductID) AS Changes (Action, ProductID)
     WHERE Action = 'DELETE'
     */
    public Insert exampleV = INSERT(
            INTO()
            ,t("Production","ZeroInventory")
            ,$(c("DeletedProductID"),c("RemovedOnDate"))
            //TODO derived_table
            ,DEFAULT_VALUES()
//            .$Select(derivedTableV)
    ).build();
    // @formatter:on


    // @formatter:off
    /**
     * INSERT INTO EmployeeTitles
    SELECT EmployeeKey, LastName, Title
    FROM ssawPDW.dbo.DimEmployee
    WHERE EndDate IS NULL
     */
    public Insert exampleW = INSERT(
            INTO()
            ,t("EmployeeTitles")
            //TODO derived_table
            ,DEFAULT_VALUES()
//            .$Select(derivedTableW)
    ).build();
    // @formatter:on


    // @formatter:off
    /**
     * -- Uses AdventureWorks

    INSERT INTO DimCurrency
    VALUES (500, N'C1', N'Currency1')
    OPTION ( LABEL = N'label1' )
     */
    public Insert exampleX = INSERT(
            INTO()
            ,t("DimCurrency")
            ,VALUES(e_number(500),
                    e_n_string("C1"),
                    e_n_string("Currency1"))
            //TODO ? OPTION
//            ,OPTION(
//                    LABEL(e_n_string("label1"))
//            )
    ).build();
    // @formatter:on


    // @formatter:off
    private Select derivedTableY = select_.$(
            select_.SELECT(
                select_.$(c("ProspectiveBuyerKey"))
                .$$(c("ProspectAlternateKey"))
                .$$(c("FirstName"))
                .$$(c("MiddleName"))
                .$$(c("LastName"))
                ,FROM(t("ProspectiveBuyer"),"p")
                        .$(JOIN(t("DimGeography"),"g")
                            ,ON(p_equal(
                                    c("p","PostalCode"),
                                    c("g","PostalCode")
                            ))
                        )
                ,WHERE(p_equal(
                        c("g","CountryRegionCode"),
                        e_string("FR")
                ))
            )
            ,OPTION(
                    LABEL("Add French Prospects")
                    ,HASH_JOIN())
    ).build();
    /**
     * -- Uses AdventureWorks

    INSERT INTO DimCustomer (CustomerKey, CustomerAlternateKey,
        FirstName, MiddleName, LastName )
    SELECT ProspectiveBuyerKey, ProspectAlternateKey,
        FirstName, MiddleName, LastName
    FROM ProspectiveBuyer p JOIN DimGeography g ON p.PostalCode = g.PostalCode
    WHERE g.CountryRegionCode = 'FR'
    OPTION ( LABEL = 'Add French Prospects', HASH JOIN)
     */
    public Insert exampleY = INSERT(
            INTO()
            ,t("DimCustomer")
            ,$(c("CustomerKey")
                ,c("CustomerAlternateKey")
                ,c("FirstName")
                ,c("MiddleName")
                ,c("LastName"))
            //TODO derived_table
//            ,$(derivedTableY)
            ,DEFAULT_VALUES()
    ).build();
    // @formatter:on

}