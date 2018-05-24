package com.xy.xsql.tsql.builder.chain.statements;

import com.xy.xsql.tsql.model.queries.With;
import com.xy.xsql.tsql.model.statements.Insert;
import com.xy.xsql.tsql.model.statements.Merge;
import com.xy.xsql.tsql.model.queries.Select;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.datatypes.table.ColumnNameFactory.*;
import static com.xy.xsql.tsql.builder.chain.datatypes.table.TableNameFactory.*;
import static com.xy.xsql.tsql.builder.chain.elements.expressions.Expressions.*;
import static com.xy.xsql.tsql.builder.chain.queries.OutputBuilder.c_$action;
import static com.xy.xsql.tsql.builder.chain.queries.OutputBuilder.c_inserted;
import static com.xy.xsql.tsql.builder.chain.queries.Queries.$Select;
import static com.xy.xsql.tsql.builder.chain.queries.Queries.$Query;
import static com.xy.xsql.tsql.builder.chain.queries.Queries.$SubQuery;
import static com.xy.xsql.tsql.builder.chain.queries.UpdateBuilder.SetItemBuilder.s;
import static com.xy.xsql.tsql.builder.chain.queries.Queries.$With;
import static com.xy.xsql.tsql.builder.chain.queries.hints.QueryHintBuilder.$HashJoin;
import static com.xy.xsql.tsql.builder.chain.queries.hints.TableHintLimiteds.$IgnoreTriggers;
import static com.xy.xsql.tsql.builder.chain.queries.hints.TableHintLimiteds.$Tablock;
import static com.xy.xsql.tsql.builder.chain.queries.hints.TableHintLimiteds.$Xlock;
import static com.xy.xsql.tsql.builder.chain.queries.predicates.Predicates.*;
import static com.xy.xsql.tsql.builder.chain.statements.Statements.$Insert;
import static com.xy.xsql.tsql.builder.chain.statements.Statements.$Merge;
import static org.junit.Assert.*;

/**
 * Created by xiaoyao9184 on 2017/1/7.
 */
public class InsertBuilderTest {

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
    public Insert exampleA = $Insert()
                .$Into()
                .$(t("Production","UnitMeasure"))
                .$Values()
                    .$(e_n_string("FT"),e_n_string("Feet"),e_string("20080414"))
                    .and()
                .build();
    // @formatter:on

    @Test
    public void testExampleA(){
        // @formatter:off
        Insert insert = new InsertBuilder()
                .withInto()
                .withTableName(t("Production","UnitMeasure"))
                .withValues()
                  .withItem()
                        .withRowValueExpression(e_n_string("FT"))
                        .withRowValueExpression(e_n_string("Feet"))
                        .withRowValueExpression(e_string("20080414"))
                        .and()
                    .and()
                .build();
        // @formatter:on

        assertTrue(insert.isUseInto());
        assertEquals(insert.getTableName().toString(),"Production.UnitMeasure");
        assertEquals(insert.getValues().getRowValueExpressionListGroup().size(),1);
        assertEquals(insert.getValues().getRowValueExpressionListGroup().get(0).size(),3);
    }


    // @formatter:off
    //parent+quick
    /**
     * INSERT INTO Production.UnitMeasure
    VALUES (N'FT2', N'Square Feet ', '20080923'), (N'Y', N'Yards', '20080923')
        , (N'Y3', N'Cubic Yards', '20080923')
     */
    public Insert exampleB = $Insert()
                .$Into()
                .$(t("Production","UnitMeasure"))
                .$Values()
                    .$(e_n_string("FT2"),e_n_string("Square Feet "),e_string("20080923"))
                    .$(e_n_string("Y"),e_n_string("Yards"),e_string("20080923"))
                    .$(e_n_string("Y3"),e_n_string("Cubic Yards"),e_string("20080923"))
                    .and()
                .build();
    // @formatter:on

    @Test
    public void testExampleB(){
        // @formatter:off
        Insert insert = new InsertBuilder()
                .withInto()
                .withTableName(t("Production","UnitMeasure"))
                .withValues()
                    .withItem()
                        .withRowValueExpression(e_n_string("FT2"))
                        .withRowValueExpression(e_n_string("Square Feet "))
                        .withRowValueExpression(e_string("20080923"))
                        .and()
                    .withItem()
                        .withRowValueExpression(e_n_string("Y"))
                        .withRowValueExpression(e_n_string("Yards"))
                        .withRowValueExpression(e_string("20080923"))
                        .and()
                    .withItem()
                        .withRowValueExpression(e_n_string("Y3"))
                        .withRowValueExpression(e_n_string("Cubic Yards"))
                        .withRowValueExpression(e_string("20080923"))
                        .and()
                    .and()
                .build();
        // @formatter:on

        assertTrue(insert.isUseInto());
        assertEquals(insert.getTableName().toString(),"Production.UnitMeasure");
        assertEquals(insert.getValues().getRowValueExpressionListGroup().size(),3);
        assertEquals(insert.getValues().getRowValueExpressionListGroup().get(0).size(),3);
        assertEquals(insert.getValues().getRowValueExpressionListGroup().get(1).size(),3);
        assertEquals(insert.getValues().getRowValueExpressionListGroup().get(2).size(),3);
    }


    // @formatter:off
    //parent+quick
    /**
     * INSERT INTO Production.UnitMeasure (Name, UnitMeasureCode,
        ModifiedDate)
    VALUES (N'Square Yards', N'Y2', GETDATE())
     */
    public Insert exampleC = $Insert()
                .$Into()
                .$(t("Production","UnitMeasure"))
                .$(c("Name"),c("UnitMeasureCode"),c("ModifiedDate"))
                .$Values()
                    .$(e_n_string("Square Yards"),e_n_string("Y2"),e("GETDATE()"))
                    .and()
                .build();
    // @formatter:on

    /**
     * INSERT INTO Production.UnitMeasure (Name, UnitMeasureCode,
     ModifiedDate)
     VALUES (N'Square Yards', N'Y2', GETDATE());
     */
    @Test
    public void testExampleC(){
        // @formatter:off
        Insert insert = new InsertBuilder()
                .withInto()
                .withTableName(t("Production","UnitMeasure"))
                .withColumn(c("Name"))
                .withColumn(c("UnitMeasureCode"))
                .withColumn(c("ModifiedDate"))
                .withValues()
                    .withItem()
                        .withRowValueExpression(e_n_string("Square Yards"))
                        .withRowValueExpression(e_n_string("Y2"))
                        .withRowValueExpression(e("GETDATE()"))
                        .and()
                    .and()
                .build();
        // @formatter:on

        assertTrue(insert.isUseInto());
        assertEquals(insert.getTableName().toString(),"Production.UnitMeasure");
        assertEquals(insert.getColumns().size(),3);
        assertEquals(insert.getValues().getRowValueExpressionListGroup().size(),1);
        assertEquals(insert.getValues().getRowValueExpressionListGroup().get(0).size(),3);
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
    public Insert exampleD1 = $Insert()
            .$Into()
            .$(t("dbo","T1"))
            .$(c("column_4"))
            .$Values()
                .$(e_string("Explicit value"))
                .and()
            .build();
    // @formatter:on


    // @formatter:off
    /**
     * IINSERT INTO dbo.T1 (column_2, column_4)
        VALUES ('Explicit value', 'Explicit value')
     */
    public Insert exampleD2 = $Insert()
            .$Into()
            .$(t("dbo","T1"))
            .$(c("column_2"),c("column_4"))
            .$Values()
                .$(e_string("Explicit value"),e_string("Explicit value"))
                .and()
            .build();
    // @formatter:on


    // @formatter:off
    /**
     * INSERT INTO dbo.T1 (column_2)
        VALUES ('Explicit value')
     */
    public Insert exampleD3 = $Insert()
            .$Into()
            .$(t("dbo","T1"))
            .$(c("column_2"))
            .$Values()
                .$(e_string("Explicit value"))
                .and()
            .build();
    // @formatter:on


    // @formatter:off
    /**
     * INSERT INTO T1 DEFAULT VALUES
     */
    public Insert exampleD4 = $Insert()
            .$Into()
            .$(t("T1"))
            .$DefaultValues()
            .build();
    // @formatter:on

    @Test
    public void testExampleD(){
        // @formatter:off
        Insert insert = new InsertBuilder()
                .withInto()
                .withTableName(t("dbo","T1"))
                .withColumn(c("column_4"))
                .withValues()
                    .withItem()
                        .withRowValueExpression(e_string("Explicit value"))
                        .and()
                    .and()
                .build();

        Insert insert1 = new InsertBuilder()
                .withInto()
                .withTableName(t("dbo","T1"))
                .withColumn(c("column_2"))
                .withColumn(c("column_4"))
                .withValues()
                    .withItem()
                        .withRowValueExpression(e_string("Explicit value"))
                        .withRowValueExpression(e_string("Explicit value"))
                        .and()
                    .and()
                .build();

        Insert insert2 = new InsertBuilder()
                .withInto()
                .withTableName(t("dbo","T1"))
                .withColumn(c("column_2"))
                .withValues()
                    .withItem()
                        .withRowValueExpression(e_string("Explicit value"))
                        .and()
                    .and()
                .build();

        Insert insert3 = new InsertBuilder()
                .withInto()
                .withTableName(t("T1"))
                .withDefaultValues()
                .build();
        // @formatter:on

        assertTrue(insert.isUseInto());
        assertEquals(insert.getTableName().toString(),"dbo.T1");
        assertEquals(insert.getColumns().size(),1);
        assertEquals(insert.getValues().getRowValueExpressionListGroup().size(),1);
        assertEquals(insert.getValues().getRowValueExpressionListGroup().get(0).size(),1);

        assertTrue(insert1.isUseInto());
        assertEquals(insert1.getTableName().toString(),"dbo.T1");
        assertEquals(insert1.getColumns().size(),2);
        assertEquals(insert1.getValues().getRowValueExpressionListGroup().size(),1);
        assertEquals(insert1.getValues().getRowValueExpressionListGroup().get(0).size(),2);

        assertTrue(insert2.isUseInto());
        assertEquals(insert2.getTableName().toString(),"dbo.T1");
        assertEquals(insert2.getColumns().size(),1);
        assertEquals(insert2.getValues().getRowValueExpressionListGroup().size(),1);
        assertEquals(insert2.getValues().getRowValueExpressionListGroup().get(0).size(),1);

        assertTrue(insert3.isUseInto());
        assertEquals(insert3.getTableName().toString(),"T1");
        assertEquals(insert3.isUseDefaultValues(),true);
    }


    // @formatter:off
    //parent+quick
    /**
     * INSERT T1 VALUES ('Row #1')
     */
    public Insert exampleE1 = $Insert()
            .$(t("T1"))
            .$Values()
                .$(e_string("Row #1"))
                .and()
            .build();
    // @formatter:on


    // @formatter:off
    /**
     * INSERT T1 (column_2) VALUES ('Row #2')
     */
    public Insert exampleE2 = $Insert()
            .$(t("T1"))
            .$(c("column_2"))
            .$Values()
                .$(e_string("Row #2"))
                .and()
            .build();
    // @formatter:on


    // @formatter:off
    /**
     * INSERT INTO T1 (column_1,column_2)
    VALUES (-99, 'Explicit identity value')
     */
    public Insert exampleE3 = $Insert()
            .$Into()
            .$(t("T1"))
            .$(c("column_1"),c("column_2"))
            .$Values()
                .$(e_number(-99),e_string("Explicit identity value"))
                .and()
            .build();
    // @formatter:on


    // @formatter:off
    //parent+quick
    /**
     * INSERT INTO dbo.T1 (column_2)
        VALUES (NEWID())
     */
    public Insert exampleF1 = $Insert()
            .$Into()
            .$(t("dbo","T1"))
            .$(c("column_2"))
            .$Values()
                .$(e("NEWID()"))
                .and()
            .build();
    // @formatter:on


    // @formatter:off
    /**
     * INSERT INTO T1 DEFAULT VALUES
     */
    public Insert exampleF2 = $Insert()
            .$Into()
            .$(t("T1"))
            .$DefaultValues()
            .build();
    // @formatter:on


    // @formatter:off
    //parent+quick
    /**
     * INSERT INTO dbo.Points (PointValue) VALUES (CONVERT(Point, '3,4'))
     */
    public Insert exampleG1 = $Insert()
            .$Into()
            .$(t("dbo","Points"))
            .$(c("PointValue"))
            .$Values()
                .$(e("CONVERT(Point, '3,4')"))
                .and()
            .build();

    /**
     * INSERT INTO dbo.Points (PointValue) VALUES (CONVERT(Point, '1,5'))
     */
    public Insert exampleG2 = $Insert()
            .$Into()
            .$(t("dbo","Points"))
            .$(c("PointValue"))
            .$Values()
                .$(e("CONVERT(Point, '1,5')"))
                .and()
            .build();

    /**
     * INSERT INTO dbo.Points (PointValue) VALUES (CAST ('1,99' AS Point))
     */
    public Insert exampleG3 = $Insert()
            .$Into()
            .$(t("dbo","Points"))
            .$(c("PointValue"))
            .$Values()
                .$(e("CAST ('1,99' AS Point)"))
                .and()
            .build();
    // @formatter:on


    /*
    Inserting Data from Other Tables
    See https://docs.microsoft.com/zh-cn/sql/t-sql/statements/insert-transact-sql#OtherTables
     */


    // @formatter:off
    private Select.QuerySpecification derivedTableH1 = $Query()
            .$(e_string("SELECT"))
            .$(c("sp","BusinessEntityID"))
            .$(c("c","LastName"))
            .$(c("sp","SalesYTD"))
            .$From()
                .$()
                    .$(t("Sales","SalesPerson")).$As("sp")
                    .$InnerJoin()
                    .$(t("Person","Person")).$As("c")
                    .$On()
                        .$(p_equal(c("",""),c("","")))
                        .and()
                    .and()
                .and()
            .$Where()
                .$(p_equal(
                        c("sp","BusinessEntityID"),
                        c("c","LastName")
                ))
                .and()
            .build();
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
    public Insert exampleH1 = $Insert()
            .$Into()
            .$(t("dbo","EmployeeSales"))
            //TODO derived_table
//            .$Select(derivedTableH1)
            .build();

    /**
     * --INSERT...EXECUTE procedure example
     INSERT INTO dbo.EmployeeSales
     EXECUTE dbo.uspGetEmployeeSales
     */
    public Insert exampleH2 = $Insert()
            .$Into()
            .$(t("dbo","EmployeeSales"))
            //TODO execute_statement
//            .$Execute()
            .build();

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
    public Insert exampleH3 = $Insert()
            .$Into()
            .$(t("dbo","EmployeeSales"))
            //TODO execute_statement
//            .$Execute()
            .build();
    // @formatter:on


    // @formatter:off
    private Select.QuerySpecification querySpecificationI = $Query()
            .$(c("e","BusinessEntityID"))
            .$(c("c","LastName"))
            .$(c("c","FirstName"))
            .$(c("pp","PhoneNumber"))
            .$(c("a","AddressLine1"))
            .$(c("a","City"))
            .$(c("sp","StateProvinceCode"))
            .$(c("a","PostalCode"))
            .$(c("e","CurrentFlag"))
            .$From()
                .$()
                    .$(t("HumanResources","Employee")).$As("e")
                    .$InnerJoin()
                    .$(t("Person","BusinessEntityAddress")).$As("bea")
                    .$On()
                        .$(p_equal(
                                c("e","BusinessEntityID"),
                                c("bea","BusinessEntityID")))
                        .and()
                    .$InnerJoin()
                    .$(t("Person","Address")).$As("a")
                    .$On()
                        .$(p_equal(
                                c("bea","AddressID"),
                                c("a","AddressID")))
                        .and()
                    .$InnerJoin()
                    .$(t("Person","PersonPhone")).$As("pp")
                    .$On()
                        .$(p_equal(
                                c("e","BusinessEntityID"),
                                c("pp","BusinessEntityID")))
                        .and()
                    .$InnerJoin()
                    .$(t("Person","StateProvince")).$As("sp")
                    .$On()
                        .$(p_equal(
                                c("a","StateProvinceID"),
                                c("sp","StateProvinceID")))
                        .and()
                    .$InnerJoin()
                    .$(t("Person","Person")).$As("c")
                    .$On()
                        .$(p_equal(
                                c("e","BusinessEntityID"),
                                c("c","BusinessEntityID")))
                        .and()
                    .and()
                .and()
            .build();

    private With withI = $With()
            .$("EmployeeTemp")
                .$(c("EmpID"))
                .$(c("LastName"))
                .$(c("FirstName"))
                .$(c("Phone"))
                .$(c("Address"))
                .$(c("City"))
                .$(c("StateProvince"))
                .$As($SubQuery(querySpecificationI))
            .build();

    private Select.QuerySpecification derivedTableI = $Query()
            .$(c("EmpID"))
            .$(c("LastName"))
            .$(c("FirstName"))
            .$(c("Phone"))
            .$(c("Address"))
            .$(c("City"))
            .$(c("StateProvince"))
            .$(c("PostalCode"))
            .$(c("CurrentFlag"))
            .$From()
                .$(t("EmployeeTemp"))
                .and()
            .build();
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
    public Insert exampleI = $Insert()
            .$With(withI)
            .$Into()
            .$(t("HumanResources","NewEmployee"))
            //TODO derived_table
//            .$Select(derivedTableI1)
            .build();


    // @formatter:off
    private Select derivedTableJ1 = $Select()
            .$Select()
                .$(c("sp","BusinessEntityID"))
                .$(c("c","LastName"))
                .$(c("c","FirstName"))
                .$(c("sp","SalesYTD"))
                .$From()
                    .$()
                        .$(t("Sales","SalesPerson")).$As("sp")
                        .$InnerJoin()
                        .$(t("Person","Person")).$As("c")
                        .$On()
                            .$(p_equal(
                                    c("sp","BusinessEntityID"),
                                    c("c","BusinessEntityID")
                            ))
                            .and()
                        .and()
                    .and()
                .$Where()
                    .$(p_greater(
                            c("sp","SalesYTD"),
                            e_number(250000.00)
                    ))
                    .and()
                .and()
            .$OrderBy()
                .$(c("sp","SalesYTD"))
                .$Desc()
                .and()
            .build();

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
    public Insert exampleJ1 = $Insert()
            .$Top()
                .$(e_number(5))
                .and()
            .$(t("dbo","EmployeeSales"))
            .$Output()
                .$(c_inserted("EmployeeID"))
                .$(c_inserted("FirstName"))
                .$(c_inserted("LastName"))
                .$(c_inserted("YearlySales"))
                .and()
            //TODO derived_table
//            .$Select(derivedTableJ1)
            .build();
    // @formatter:on


    // @formatter:off
    private Select derivedTableJ2 = $Select()
            .$Select()
                .$Top()
                    .$(e_number(5))
                    .and()
                .$(c("sp","BusinessEntityID"))
                .$(c("c","LastName"))
                .$(c("c","FirstName"))
                .$(c("sp","SalesYTD"))
                .$From()
                    .$()
                        .$(t("Sales","SalesPerson")).$As("sp")
                        .$InnerJoin()
                        .$(t("Person","Person")).$As("c")
                        .$On()
                            .$(p_equal(
                                    c("sp","BusinessEntityID"),
                                    c("c","BusinessEntityID")
                            ))
                            .and()
                        .and()
                    .and()
                .$Where()
                    .$(p_greater(
                            c("sp","SalesYTD"),
                            e_number(250000.00)
                    ))
                    .and()
                .and()
            .$OrderBy()
                .$(c("sp","SalesYTD"))
                .$Desc()
                .and()
            .build();

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
    public Insert exampleJ2 = $Insert()
            .$(t("dbo","EmployeeSales"))
            .$Output()
                .$(c_inserted("EmployeeID"))
                .$(c_inserted("FirstName"))
                .$(c_inserted("LastName"))
                .$(c_inserted("YearlySales"))
                .and()
            //TODO derived_table
//            .$Select(derivedTableJ2)
            .build();
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
    public Insert exampleK = $Insert()
            .$Into()
            .$(t("V1"))
            .$Values()
                .$(e_string("Row 1"),e_number(1))
                .and()
            .build();
    // @formatter:on


    // @formatter:off
    private Select.QuerySpecification derivedTableL = $Query()
            .$(c("LocationID"))
            .$(c("CostRate"))
            .$(e("GETDATE()"))
            .$From()
                .$(t("Production","Location"))
                .and()
            .$Where()
                .$(p_greater(
                        c("CostRate"),
                        e_number(0)
                ))
                .and()
            .build();
    /**
     * -- Insert values into the table variable.
    INSERT INTO @MyTableVar (LocationID, CostRate, ModifiedDate)
    SELECT LocationID, CostRate, GETDATE()
    FROM Production.Location
    WHERE CostRate > 0
     */
    public Insert exampleL = $Insert()
            .$Into()
            //TODO ? table variable
//            .$(e_variable("MyTableVar"))
            .$(c("LocationID"))
            .$(c("CostRate"))
            .$(c("ModifiedDate"))
            //TODO derived_table
//            .$Select(derivedTableL)
            .build();
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
    public Insert exampleM = $Insert()
            .$Into()
            .$(t("MyLinkServer","AdventureWorks2012","HumanResources","Department"))
            .$(c("Name"))
            .$(c("GroupName"))
            .$Values()
                .$(e_n_string("Public Relations"),
                        e_n_string("Executive General and Administration"))
                .and()
            .build();
    // @formatter:on


    // @formatter:off
    /**
     * INSERT OPENQUERY (MyLinkServer,
        'SELECT Name, GroupName
         FROM AdventureWorks2012.HumanResources.Department')
    VALUES ('Environmental Impact', 'Engineering')
     */
    public Insert exampleN = $Insert()
            //TODO rowset_function_limited
//            .$Fun()
            .$Values()
                .$(e_string("Environmental Impact"),
                        e_string("Engineering"))
                .and()
            .build();
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
    public Insert exampleO = $Insert()
            .$Into()
            //TODO rowset_function_limited
//            .$Fun()
            .$Values()
                .$(e_string("Standards and Methods"),
                        e_string("Quality Assurance"))
                .and()
            .build();
    // @formatter:on


    // @formatter:off
    private Select.QuerySpecification derivedTableP = $Query()
            .$(t("T"))
            .$From()
                .$()
                    .$(t("Insured_Customers"),"T1")
                    .$Join()
                    .$(t("CarSensor_Data"),"T2")
                    .$On()
                        .$(p_equal(
                                c("T1","CustomerKey"),
                                c("T2","CustomerKey")
                        ))
                        .and()
                    .and()
                .and()
            .$Where()
                .$(p_equal(
                        c("T2","YearMeasured"),
                        e_number(2009)
                ))
                .$And(p_greater(
                        c("T2","Speed"),
                        e_number(40)
                ))
                .and()
            .build();

    /**
     * -- Export data: Move old data to Hadoop while keeping
    -- it query-able via external table.

    INSERT INTO dbo.FastCustomer2009
    SELECT T.* FROM Insured_Customers T1 JOIN CarSensor_Data T2
    ON (T1.CustomerKey = T2.CustomerKey)
    WHERE T2.YearMeasured = 2009 and T2.Speed > 40
     */
    public Insert exampleP = $Insert()
            .$Into()
            .$(t("dbo","FastCustomer2009"))
            //TODO derived_table
//            .$Select(derivedTableP)
            .build();
    // @formatter:on

    /*
    Bulk Loading Data from Tables or Data Files
    See https://docs.microsoft.com/zh-cn/sql/t-sql/statements/insert-transact-sql#BulkLoad
     */


    // @formatter:off
    private Select.QuerySpecification derivedTableQ = $Query()
            .$()
            .$From()
                .$(t("Sales","SalesOrderDetail"))
                .and()
            .build();

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
    public Insert exampleQ = $Insert()
            .$Into()
            .$(t("Sales","SalesHistory"))
            .$With($Tablock())
            .$(c("SalesOrderID"))
            .$(c("SalesOrderDetailID"))
            .$(c("CarrierTrackingNumber"))
            .$(c("OrderQty"))
            .$(c("ProductID"))
            .$(c("SpecialOfferID"))
            .$(c("UnitPrice"))
            .$(c("UnitPriceDiscount"))
            .$(c("LineTotal"))
            .$(c("rowguid"))
            .$(c("ModifiedDate"))
            //TODO derived_table
//            .$Select(derivedTableQ)
            .build();
    // @formatter:on


    // @formatter:off
    private Select.QuerySpecification derivedTableR = $Query()
            .$(c("b","Name"))
            .$(c("b","GroupName"))
            .$From()
                //TODO function
//                .$(t("Sales","SalesOrderDetail"))
                .and()
            .build();

    /**
     * INSERT INTO HumanResources.Department WITH (IGNORE_TRIGGERS) (Name, GroupName)
    SELECT b.Name, b.GroupName
    FROM OPENROWSET (
        BULK 'C:SQLFilesDepartmentData.txt',
        FORMATFILE = 'C:SQLFilesBulkloadFormatFile.xml',
        ROWS_PER_BATCH = 15000)AS b
     */
    public Insert exampleR = $Insert()
            .$Into()
            .$(t("HumanResources","Department"))
            .$With($IgnoreTriggers())
            .$(c("Name"))
            .$(c("GroupName"))
            //TODO derived_table
//            .$Select(derivedTableR)
            .build();
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
    public Insert exampleS = $Insert()
            .$Into()
            .$(t("Production","Location"))
            .$With($Xlock())
            .$(c("Name"))
            .$(c("CostRate"))
            .$(c("Availability"))
            .$Values()
                .$(e_n_string("Final Inventory"),
                        e_number(15.00),
                        e_number(80.00)
                )
                .and()
            .build();
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
    public Insert exampleT = $Insert()
            .$(t("Production","ScrapReason"))
            .$Output()
                .$(c_inserted("ScrapReasonID"))
                .$(c_inserted("Name"))
                .$(c_inserted("ModifiedDate"))
                .$Into("MyTableVar")
                .and()
            .$Values()
                .$(e_n_string("Operator error"),
                        e("GETDATE()")
                )
                .and()
            .build();
    // @formatter:on


    // @formatter:off
    private Select derivedTableU = $Select()
            .$Select()
                .$(c("c","FirstName"))
                .$(c("c","LastName"))
                .$(c("sp","SalesYTD"))
                .$From()
                    .$()
                        .$(t("Sales","SalesPerson")).$As("sp")
                        .$InnerJoin()
                        .$(t("Person","Person")).$As("c")
                        .$On()
                            .$(p_equal(
                                    c("sp","BusinessEntityID"),
                                    c("c","BusinessEntityID")
                            )).and()
                        .and()
                    .and()
                .$Where()
                    .$(p_like(
                            c("sp","BusinessEntityID"),
                            e_string("2%")
                    ))
                    .and()
                .and()
            .$OrderBy()
                .$(c("c","LastName"))
                .$(c("c","FirstName"))
                .and()
            .build();

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
    public Insert exampleU = $Insert()
            .$Into()
            .$(t("dbo","EmployeeSales"))
            .$(c("LastName"))
            .$(c("FirstName"))
            .$(c("CurrentSales"))
            .$Output()
                .$(c_inserted("LastName"))
                .$(c_inserted("FirstName"))
                .$(c_inserted("CurrentSales"))
                .$Into("MyTableVar")
                .and()
            //TODO derived_table
//            .$Select(derivedTableU)
            .build();
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
    private Merge derivedTableV0 = $Merge()
            .$(t("Production","ProductInventory"))
            .$As("pi")
            .$Using()
                .$($Query()
                        .$(c("ProductID"))
                        .$(e("SUM(OrderQty)"))
                        .$From()
                            .$()
                                .$(t("Sales","SalesOrderDetail")).$As("sod")
                                .$Join()
                                .$(t("Sales","SalesOrderHeader")).$As("soh")
                                .$On()
                                    .$(p_equal(
                                            c("sod","SalesOrderID"),
                                            c("soh","SalesOrderID")
                                    ))
                                    .$And(p_equal(
                                            c("soh","OrderDate"),
                                            e_string("20070401")
                                    ))
                                    .and()
                                .and()
                            .and()
                        .$GroupBy()
                            .$(c("ProductID"))
                            .and()
                        .build()
                )
                .$As("src","ProductID","OrderQty")
            .$WhenMatched()
                .$And()
                    .$(p_less_equal(e_subtraction(
                            c("pi","Quantity"),
                            c("src","OrderQty")),
                            e_number(0)
                    ))
                    .and()
                .$Then().$Delete().and()
            .$WhenMatched()
                .$Then().$UpdateSet(s(
                        c("pi","Quantity"),
                        e_subtraction(
                                c("pi","Quantity"),
                                c("src","OrderQty")
                        )
                )).and()
            .$OutPut()
                .$Output(c_$action())
                .$OutputDeleted("ProductID")
                .and()
            .build();
        //         WHEN MATCHED $AND pi.Quantity - src.OrderQty <= 0
//         THEN DELETE
//         WHEN MATCHED
//         THEN UPDATE SET pi.Quantity = pi.Quantity - src.OrderQty
//         OUTPUT $action, deleted.ProductID) AS Changes (Action, ProductID)

    private Select.QuerySpecification derivedTableV = $Query()
            .$(c("ProductID"))
            .$(e("GETDATE()"))
            .$From()
                //TODO derived_table
//                .$(derivedTableV0).$As("sp","Action","ProductID")
                .and()
            .$Where()
                .$(p_equal(
                        c("Action"),
                        e_string("DELETE")
                ))
                .and()
            .build();

    /**
     * INSERT INTO Production.ZeroInventory (DeletedProductID, RemovedOnDate)
     SELECT ProductID, GETDATE()
     FROM
     (   MERGE Production.ProductInventory AS pi
         USING (SELECT ProductID, SUM(OrderQty) FROM Sales.SalesOrderDetail AS sod
         JOIN Sales.SalesOrderHeader AS soh
         ON sod.SalesOrderID = soh.SalesOrderID
         $AND soh.OrderDate = '20070401'
         GROUP BY ProductID) AS src (ProductID, OrderQty)
         ON (pi.ProductID = src.ProductID)
         WHEN MATCHED $AND pi.Quantity - src.OrderQty <= 0
         THEN DELETE
         WHEN MATCHED
         THEN UPDATE SET pi.Quantity = pi.Quantity - src.OrderQty
         OUTPUT $action, deleted.ProductID) AS Changes (Action, ProductID)
     WHERE Action = 'DELETE'
     */
    public Insert exampleV = $Insert()
            .$Into()
            .$(t("Production","ZeroInventory"))
            .$(c("DeletedProductID"))
            .$(c("RemovedOnDate"))
            //TODO derived_table
//            .$Select(derivedTableV)
            .build();
    // @formatter:on


    // @formatter:off
    /**
     * INSERT INTO EmployeeTitles
    SELECT EmployeeKey, LastName, Title
    FROM ssawPDW.dbo.DimEmployee
    WHERE EndDate IS NULL
     */
    public Insert exampleW = $Insert()
            .$Into()
            .$(t("EmployeeTitles"))
            //TODO derived_table
//            .$Select(derivedTableW)
            .build();
    // @formatter:on


    // @formatter:off
    /**
     * -- Uses AdventureWorks

    INSERT INTO DimCurrency
    VALUES (500, N'C1', N'Currency1')
    OPTION ( LABEL = N'label1' )
     */
    public Insert exampleX = $Insert()
            .$Into()
            .$(t("DimCurrency"))
            .$Values()
                .$(e_number(500),
                        e_n_string("C1"),
                        e_n_string("Currency1"))
                .and()
            //TODO ? OPTION
//            .$Option()
//                .$(e_n_string("label1"))
//                .and()
            .build();
    // @formatter:on


    // @formatter:off
    private Select derivedTableY = $Select()
            .$Select()
                .$(c("ProspectiveBuyerKey"))
                .$(c("ProspectAlternateKey"))
                .$(c("FirstName"))
                .$(c("MiddleName"))
                .$(c("LastName"))
                .$From()
                    .$()
                        .$(t("ProspectiveBuyer"))
                            .$("p")
                        .$Join()
                        .$(t("DimGeography"))
                            .$("g")
                        .$On()
                            .$(p_equal(
                                    c("p","PostalCode"),
                                    c("g","PostalCode")
                            ))
                            .and()
                        .and()
                    .and()
                .$Where()
                    .$(p_equal(
                            c("g","CountryRegionCode"),
                            e_string("FR")
                    ))
                    .and()
                .and()
            .$Option()
                .$("Add French Prospects")
                .$($HashJoin())
                .and()
            .build();
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
    public Insert exampleY = $Insert()
            .$Into()
            .$(t("DimCustomer"))
            .$(c("CustomerKey"))
            .$(c("CustomerAlternateKey"))
            .$(c("FirstName"))
            .$(c("MiddleName"))
            .$(c("LastName"))
            //TODO derived_table
//            .$Select(derivedTableY)
            .build();
    // @formatter:on

}
