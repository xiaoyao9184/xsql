package com.xy.xsql.tsql.core.statement.dml;

import com.xy.xsql.tsql.model.clause.With;
import com.xy.xsql.tsql.model.statement.dml.Insert;
import com.xy.xsql.tsql.model.statement.dml.Merge;
import com.xy.xsql.tsql.model.statement.dml.Select;
import org.junit.Assert;
import org.junit.Test;

import static com.xy.xsql.tsql.core.clause.OutputBuilder.c_$action;
import static com.xy.xsql.tsql.core.clause.OutputBuilder.c_inserted;
import static com.xy.xsql.tsql.core.clause.WithBuilder.WITH;
import static com.xy.xsql.tsql.core.clause.hints.QueryHintBuilder.HASH_JOIN;
import static com.xy.xsql.tsql.core.clause.hints.TableHintLimiteds.IGNORE_TRIGGERS;
import static com.xy.xsql.tsql.core.clause.hints.TableHintLimiteds.TABLOCK;
import static com.xy.xsql.tsql.core.clause.hints.TableHintLimiteds.XLOCK;
import static com.xy.xsql.tsql.core.clause.subquery.SubQueryBuilder.QUERY;
import static com.xy.xsql.tsql.core.clause.subquery.SubQueryBuilder.SUB_QUERY;
import static com.xy.xsql.tsql.core.element.ColumnNameFactory.c;
import static com.xy.xsql.tsql.core.element.TableNameFactory.t;
import static com.xy.xsql.tsql.core.expression.BinaryExpressions.e_subtraction;
import static com.xy.xsql.tsql.core.expression.Expressions.*;
import static com.xy.xsql.tsql.core.predicate.Predicates.*;
import static com.xy.xsql.tsql.core.statement.StatementBuilderFactory.MERGE;
import static com.xy.xsql.tsql.core.statement.dml.InsertBuilder.INSERT;
import static com.xy.xsql.tsql.core.statement.dml.SelectBuilder.SELECT;
import static com.xy.xsql.tsql.core.statement.dml.UpdateBuilder.SetItemBuilder.s;

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
    public Insert exampleA = INSERT()
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

        Assert.assertTrue(insert.isUseInto());
        Assert.assertEquals(insert.getTableName().toString(),"Production.UnitMeasure");
        Assert.assertEquals(insert.getValues().getRowValueExpressionListGroup().size(),1);
        Assert.assertEquals(insert.getValues().getRowValueExpressionListGroup().get(0).size(),3);
    }


    // @formatter:off
    //parent+quick
    /**
     * INSERT INTO Production.UnitMeasure
    VALUES (N'FT2', N'Square Feet ', '20080923'), (N'Y', N'Yards', '20080923')
        , (N'Y3', N'Cubic Yards', '20080923')
     */
    public Insert exampleB = INSERT()
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

        Assert.assertTrue(insert.isUseInto());
        Assert.assertEquals(insert.getTableName().toString(),"Production.UnitMeasure");
        Assert.assertEquals(insert.getValues().getRowValueExpressionListGroup().size(),3);
        Assert.assertEquals(insert.getValues().getRowValueExpressionListGroup().get(0).size(),3);
        Assert.assertEquals(insert.getValues().getRowValueExpressionListGroup().get(1).size(),3);
        Assert.assertEquals(insert.getValues().getRowValueExpressionListGroup().get(2).size(),3);
    }


    // @formatter:off
    //parent+quick
    /**
     * INSERT INTO Production.UnitMeasure (Name, UnitMeasureCode,
        ModifiedDate)
    VALUES (N'Square Yards', N'Y2', GETDATE())
     */
    public Insert exampleC = INSERT()
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

        Assert.assertTrue(insert.isUseInto());
        Assert.assertEquals(insert.getTableName().toString(),"Production.UnitMeasure");
        Assert.assertEquals(insert.getColumns().size(),3);
        Assert.assertEquals(insert.getValues().getRowValueExpressionListGroup().size(),1);
        Assert.assertEquals(insert.getValues().getRowValueExpressionListGroup().get(0).size(),3);
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
    public Insert exampleD1 = INSERT()
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
    public Insert exampleD2 = INSERT()
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
    public Insert exampleD3 = INSERT()
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
    public Insert exampleD4 = INSERT()
            .$Into()
            .$(t("T1"))
            .$Default_Values()
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

        Assert.assertTrue(insert.isUseInto());
        Assert.assertEquals(insert.getTableName().toString(),"dbo.T1");
        Assert.assertEquals(insert.getColumns().size(),1);
        Assert.assertEquals(insert.getValues().getRowValueExpressionListGroup().size(),1);
        Assert.assertEquals(insert.getValues().getRowValueExpressionListGroup().get(0).size(),1);

        Assert.assertTrue(insert1.isUseInto());
        Assert.assertEquals(insert1.getTableName().toString(),"dbo.T1");
        Assert.assertEquals(insert1.getColumns().size(),2);
        Assert.assertEquals(insert1.getValues().getRowValueExpressionListGroup().size(),1);
        Assert.assertEquals(insert1.getValues().getRowValueExpressionListGroup().get(0).size(),2);

        Assert.assertTrue(insert2.isUseInto());
        Assert.assertEquals(insert2.getTableName().toString(),"dbo.T1");
        Assert.assertEquals(insert2.getColumns().size(),1);
        Assert.assertEquals(insert2.getValues().getRowValueExpressionListGroup().size(),1);
        Assert.assertEquals(insert2.getValues().getRowValueExpressionListGroup().get(0).size(),1);

        Assert.assertTrue(insert3.isUseInto());
        Assert.assertEquals(insert3.getTableName().toString(),"T1");
        Assert.assertEquals(insert3.isUseDefaultValues(),true);
    }


    // @formatter:off
    //parent+quick
    /**
     * INSERT T1 VALUES ('Row #1')
     */
    public Insert exampleE1 = INSERT()
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
    public Insert exampleE2 = INSERT()
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
    public Insert exampleE3 = INSERT()
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
    public Insert exampleF1 = INSERT()
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
    public Insert exampleF2 = INSERT()
            .$Into()
            .$(t("T1"))
            .$Default_Values()
            .build();
    // @formatter:on


    // @formatter:off
    //parent+quick
    /**
     * INSERT INTO dbo.Points (PointValue) VALUES (CONVERT(Point, '3,4'))
     */
    public Insert exampleG1 = INSERT()
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
    public Insert exampleG2 = INSERT()
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
    public Insert exampleG3 = INSERT()
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
    private Select.QuerySpecification derivedTableH1 = QUERY()
            .$(e_string("SELECT"))
            .$(c("sp","BusinessEntityID"))
            .$(c("c","LastName"))
            .$(c("sp","SalesYTD"))
            .$From()
                .$()
                    .$(t("Sales","SalesPerson")).$As("sp")
                    .$Inner_Join()
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
    public Insert exampleH1 = INSERT()
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
    public Insert exampleH2 = INSERT()
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
    public Insert exampleH3 = INSERT()
            .$Into()
            .$(t("dbo","EmployeeSales"))
            //TODO execute_statement
//            .$Execute()
            .build();
    // @formatter:on


    // @formatter:off
    private Select.QuerySpecification querySpecificationI = QUERY()
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
                    .$Inner_Join()
                    .$(t("Person","BusinessEntityAddress")).$As("bea")
                    .$On()
                        .$(p_equal(
                                c("e","BusinessEntityID"),
                                c("bea","BusinessEntityID")))
                        .and()
                    .$Inner_Join()
                    .$(t("Person","Address")).$As("a")
                    .$On()
                        .$(p_equal(
                                c("bea","AddressID"),
                                c("a","AddressID")))
                        .and()
                    .$Inner_Join()
                    .$(t("Person","PersonPhone")).$As("pp")
                    .$On()
                        .$(p_equal(
                                c("e","BusinessEntityID"),
                                c("pp","BusinessEntityID")))
                        .and()
                    .$Inner_Join()
                    .$(t("Person","StateProvince")).$As("sp")
                    .$On()
                        .$(p_equal(
                                c("a","StateProvinceID"),
                                c("sp","StateProvinceID")))
                        .and()
                    .$Inner_Join()
                    .$(t("Person","Person")).$As("c")
                    .$On()
                        .$(p_equal(
                                c("e","BusinessEntityID"),
                                c("c","BusinessEntityID")))
                        .and()
                    .and()
                .and()
            .build();

    private With withI = WITH()
            .$("EmployeeTemp")
                .$(c("EmpID"))
                .$(c("LastName"))
                .$(c("FirstName"))
                .$(c("Phone"))
                .$(c("Address"))
                .$(c("City"))
                .$(c("StateProvince"))
                .$As(SUB_QUERY(querySpecificationI))
            .build();

    private Select.QuerySpecification derivedTableI = QUERY()
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
    public Insert exampleI = INSERT()
            .$With(withI)
            .$Into()
            .$(t("HumanResources","NewEmployee"))
            //TODO derived_table
//            .$Select(derivedTableI1)
            .build();


    // @formatter:off
    private Select derivedTableJ1 = SELECT()
            .$Select()
                .$(c("sp","BusinessEntityID"))
                .$(c("c","LastName"))
                .$(c("c","FirstName"))
                .$(c("sp","SalesYTD"))
                .$From()
                    .$()
                        .$(t("Sales","SalesPerson")).$As("sp")
                        .$Inner_Join()
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
    public Insert exampleJ1 = INSERT()
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
    private Select derivedTableJ2 = SELECT()
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
                        .$Inner_Join()
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
    public Insert exampleJ2 = INSERT()
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
    public Insert exampleK = INSERT()
            .$Into()
            .$(t("V1"))
            .$Values()
                .$(e_string("Row 1"),e_number(1))
                .and()
            .build();
    // @formatter:on


    // @formatter:off
    private Select.QuerySpecification derivedTableL = QUERY()
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
    public Insert exampleL = INSERT()
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
    public Insert exampleM = INSERT()
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
    public Insert exampleN = INSERT()
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
    public Insert exampleO = INSERT()
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
    private Select.QuerySpecification derivedTableP = QUERY()
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
    public Insert exampleP = INSERT()
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
    private Select.QuerySpecification derivedTableQ = QUERY()
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
    public Insert exampleQ = INSERT()
            .$Into()
            .$(t("Sales","SalesHistory"))
            .$With(TABLOCK())
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
    private Select.QuerySpecification derivedTableR = QUERY()
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
    public Insert exampleR = INSERT()
            .$Into()
            .$(t("HumanResources","Department"))
            .$With(IGNORE_TRIGGERS())
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
    public Insert exampleS = INSERT()
            .$Into()
            .$(t("Production","Location"))
            .$With(XLOCK())
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
    public Insert exampleT = INSERT()
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
    private Select derivedTableU = SELECT()
            .$Select()
                .$(c("c","FirstName"))
                .$(c("c","LastName"))
                .$(c("sp","SalesYTD"))
                .$From()
                    .$()
                        .$(t("Sales","SalesPerson")).$As("sp")
                        .$Inner_Join()
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
    public Insert exampleU = INSERT()
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
//         AND soh.OrderDate = '20070401'
//         GROUP BY ProductID) AS src (ProductID, OrderQty)
//         ON (pi.ProductID = src.ProductID)

//     WHERE Action = 'DELETE'
    private Merge derivedTableV0 = MERGE()
            .$(t("Production","ProductInventory"))
            .$As("pi")
            .$Using()
                .$(QUERY()
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
            .$When_Matched()
                .$And()
                    .$(p_less_equal(e_subtraction(
                            c("pi","Quantity"),
                            c("src","OrderQty")),
                            e_number(0)
                    ))
                    .and()
                .$Then().$Delete().and()
            .$When_Matched()
                .$Then().$Update_Set(s(
                        c("pi","Quantity"),
                        e_subtraction(
                                c("pi","Quantity"),
                                c("src","OrderQty")
                        )
                )).and()
            .$OutPut()
                .$Output(c_$action())
                .$Output_Deleted("ProductID")
                .and()
            .build();
        //         WHEN MATCHED AND pi.Quantity - src.OrderQty <= 0
//         THEN DELETE
//         WHEN MATCHED
//         THEN UPDATE SET pi.Quantity = pi.Quantity - src.OrderQty
//         OUTPUT $action, deleted.ProductID) AS Changes (Action, ProductID)

    private Select.QuerySpecification derivedTableV = QUERY()
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
    public Insert exampleV = INSERT()
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
    public Insert exampleW = INSERT()
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
    public Insert exampleX = INSERT()
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
    private Select derivedTableY = SELECT()
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
                .$(HASH_JOIN())
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
    public Insert exampleY = INSERT()
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
