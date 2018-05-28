package com.xy.xsql.tsql.style.constructor.introducer.queries;

import com.xy.xsql.tsql.model.datatypes.constants.NumberConstant;
import com.xy.xsql.tsql.model.datatypes.constants.StringConstant;
import com.xy.xsql.tsql.model.elements.expressions.BinaryExpression;
import com.xy.xsql.tsql.model.elements.operators.Compound;
import com.xy.xsql.tsql.model.queries.Select;
import com.xy.xsql.tsql.model.queries.Update;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.datatypes.table.ColumnNameFactory.c;
import static com.xy.xsql.tsql.builder.chain.datatypes.table.TableNameFactory.t;
import static com.xy.xsql.tsql.builder.chain.elements.expressions.Expressions.*;
import static com.xy.xsql.tsql.builder.chain.queries.predicates.Predicates.*;
import static com.xy.xsql.tsql.style.constructor.introducer.queries.from.*;
import static com.xy.xsql.tsql.style.constructor.introducer.queries.hints.table_hint_limited.TABLOCK;
import static com.xy.xsql.tsql.style.constructor.introducer.queries.option.OPTIMIZE_FOR;
import static com.xy.xsql.tsql.style.constructor.introducer.queries.option.OPTION;
import static com.xy.xsql.tsql.style.constructor.introducer.queries.output.*;
import static com.xy.xsql.tsql.style.constructor.introducer.queries.select.group_by.GROUP_BY;
import static com.xy.xsql.tsql.style.constructor.introducer.queries.select.order_by.ASC;
import static com.xy.xsql.tsql.style.constructor.introducer.queries.select.order_by.ORDER_BY;
import static com.xy.xsql.tsql.style.constructor.introducer.queries.select.select.AS;
import static com.xy.xsql.tsql.style.constructor.introducer.queries.top.TOP;
import static com.xy.xsql.tsql.style.constructor.introducer.queries.where.WHERE;
import static com.xy.xsql.tsql.style.constructor.introducer.queries.update.$;
import static com.xy.xsql.tsql.style.constructor.introducer.queries.update.*;
import static com.xy.xsql.tsql.style.constructor.introducer.queries.update.WITH;
import static org.junit.Assert.*;

/**
 * Created by xiaoyao9184 on 2018/5/14.
 */
public class updateTest {

    /*
    Basic Syntax
    See https://docs.microsoft.com/zh-cn/sql/t-sql/queries/update-transact-sql#BasicSyntax
     */

    // @formatter:off
    /**
     * UPDATE Person.Address
    SET ModifiedDate = GETDATE()
     */
    public Update example1A = UPDATE(
            t("Person","Address")
            ,SET(c("ModifiedDate"),
                 e("GETDATE()"))
    ).build();
    // @formatter:on

    @Test
    public void testExample1A(){
        assertEquals(example1A.getTableName().toString(),"Person.Address");
        assertEquals(example1A.getSets().size(),1);

        assertEquals(example1A.getSets().get(0).getClass(), Update.ColumnAssignmentSet.class);
        Update.ColumnAssignmentSet setItem = (Update.ColumnAssignmentSet) example1A.getSets().get(0);
        assertEquals(setItem.getColumnName().toString(), "ModifiedDate");
        assertEquals(setItem.getExpression().toString(), "GETDATE()");
    }


    // @formatter:off
    /**
     * UPDATE Sales.SalesPerson
    SET Bonus = 6000, CommissionPct = .10, SalesQuota = NULL
     */
    public Update example1B = UPDATE(
            t("Sales","SalesPerson")
            ,SET(c("Bonus"),e_number(6000))
            .$$(c("CommissionPct"),e(".10"))
            .$$(c("SalesQuota"),NULL())
    ).build();
    // @formatter:on

    @Test
    public void testExample1B(){
        assertEquals(example1B.getTableName().toString(),"Sales.SalesPerson");
        assertEquals(example1B.getSets().size(),3);

        assertEquals(example1B.getSets().get(0).getClass(), Update.ColumnAssignmentSet.class);
        Update.ColumnAssignmentSet setItem = (Update.ColumnAssignmentSet) example1B.getSets().get(0);
        assertEquals(setItem.getColumnName().toString(), "Bonus");
        assertTrue(setItem.getExpression() instanceof NumberConstant);
        assertEquals(((NumberConstant)setItem.getExpression()).getNumber().toString(), "6000");

        assertEquals(example1B.getSets().get(1).getClass(), Update.ColumnAssignmentSet.class);
        Update.ColumnAssignmentSet setItem1 = (Update.ColumnAssignmentSet) example1B.getSets().get(1);
        assertEquals(setItem1.getColumnName().toString(), "CommissionPct");
        assertEquals(setItem1.getExpression().toString(), ".10");

        assertEquals(example1B.getSets().get(2).getClass(), Update.ColumnAssignmentSet.class);
        Update.ColumnAssignmentSet setItem2 = (Update.ColumnAssignmentSet) example1B.getSets().get(2);
        assertEquals(setItem2.getColumnName().toString(), "SalesQuota");
        assertTrue(setItem2.isUseNull());
    }


    /*
    Limiting the Rows that Are Updated
    See https://docs.microsoft.com/zh-cn/sql/t-sql/queries/update-transact-sql#LimitingValues
     */


    // @formatter:off
    /**
     * UPDATE Production.Product
    SET Color = N'Metallic Red'
    WHERE Name LIKE N'Road-250%' AND Color = N'Red'
     */
    public Update example2A = UPDATE(
            t("Production","Product")
            ,SET(c("Color"),e_n_string("Metallic Red"))
            ,WHERE(p_like(
                            c("Name"),
                            e_n_string("Road-250%")
                    )).AND(p_equal(
                            c("Color"),
                            e_n_string("Red")
                    ))
    ).build();
    // @formatter:on

    @Test
    public void testExample2A(){
        assertEquals(example2A.getTableName().toString(),"Production.Product");
        assertEquals(example2A.getSets().size(),1);

        assertEquals(example2A.getSets().get(0).getClass(), Update.ColumnAssignmentSet.class);
        Update.ColumnAssignmentSet setItem = (Update.ColumnAssignmentSet) example2A.getSets().get(0);
        assertEquals(setItem.getColumnName().toString(), "Color");
        assertTrue(setItem.getExpression() instanceof StringConstant);
        assertEquals(((StringConstant)setItem.getExpression()).getString(), "Metallic Red");
    }


    //2B1 is same as 2A

    // @formatter:off
    private Select select2B = select_.$(select_.SELECT(
            TOP(10)
            ,select_.$(c("BusinessEntityID"))
            ,FROM(t("HumanResources","Employee"))
    ),ORDER_BY(c("HireDate"),ASC())).build();

    /**
     * UPDATE HumanResources.Employee
     SET VacationHours = VacationHours + 8
     FROM (SELECT TOP 10 BusinessEntityID FROM HumanResources.Employee
     ORDER BY HireDate ASC) AS th
     WHERE HumanResources.Employee.BusinessEntityID = th.BusinessEntityID
     */
    public Update example2B = UPDATE(
            t("HumanResources","Employee")
            ,SET(c("VacationHours"),
                    e_addition(
                                    e("VacationHours"),
                                    e_number(8)))
            ,FROM(select2B, AS("th"))
            ,WHERE(p_equal(
                            c("HumanResources","Employee","BusinessEntityID"),
                            c("th","BusinessEntityID")
                    ))
    ).build();
    // @formatter:on

    @Test
    public void testExample2B(){
        assertEquals(example2B.getTableName().toString(),"HumanResources.Employee");
        assertEquals(example2B.getSets().size(),1);

        assertEquals(example2B.getSets().get(0).getClass(), Update.ColumnAssignmentSet.class);
        Update.ColumnAssignmentSet setItem = (Update.ColumnAssignmentSet) example2B.getSets().get(0);
        assertEquals(setItem.getColumnName().toString(), "VacationHours");
        assertEquals(setItem.getExpression().getClass(), BinaryExpression.class);

        assertNotNull(example2B.getFrom());
        assertNotNull(example2B.getWhere());

    }


    // @formatter:off
    private Select.QueryExpression queryExpression2C =
            select_.SELECT(
                select_.$(c("b","ProductAssemblyID"))
                .$$(c("b","ComponentID"))
                .$$(c("b","PerAssemblyQty"))
                .$$(c("b","EndDate"))
                .$$(e_number(0),"ComponentLevel")
                ,FROM(t("Production","BillOfMaterials"),AS("b"))
                ,WHERE(p_equal(
                        c("b","ProductAssemblyID"),
                        e_number(800)
                )).AND(p_is_null(
                        c("b","EndDate")
                ))
            ).UNION_ALL(select_.SELECT(
                    select_.$(c("bom","ProductAssemblyID"))
                    .$$(c("bom","ComponentID"))
                    .$$(c("p","PerAssemblyQty"))
                    .$$(c("bom","EndDate"))
                    .$$(e_addition(
                            c("ComponentLevel"),
                            e_number(1)
                    ))
                    ,FROM(t("Production","BillOfMaterials"),AS("bom"))
                            .INNER(JOIN(t("Parts"),AS("p"))
                                ,ON(p_equal(
                                        c("bom","ProductAssemblyID"),
                                        c("p","ComponentID")
                                )).AND(p_is_null(
                                        c("bom","EndDate")
                                ))
                            )
            )).build();
    /**
     * WITH Parts(AssemblyID, ComponentID, PerAssemblyQty, EndDate, ComponentLevel) AS
    (
        SELECT b.ProductAssemblyID, b.ComponentID, b.PerAssemblyQty,
            b.EndDate, 0 AS ComponentLevel
        FROM Production.BillOfMaterials AS b
        WHERE b.ProductAssemblyID = 800
              AND b.EndDate IS NULL
        UNION ALL
        SELECT bom.ProductAssemblyID, bom.ComponentID, p.PerAssemblyQty,
            bom.EndDate, ComponentLevel + 1
        FROM Production.BillOfMaterials AS bom
            INNER JOIN Parts AS p
            ON bom.ProductAssemblyID = p.ComponentID
            AND bom.EndDate IS NULL
    )
    UPDATE Production.BillOfMaterials
    SET PerAssemblyQty = c.PerAssemblyQty * 2
    FROM Production.BillOfMaterials AS c
    JOIN Parts AS d ON c.ProductAssemblyID = d.AssemblyID
    WHERE d.ComponentLevel = 0
     */
    public Update example2C = $(
            with.WITH(
                    "Parts",
                    with.$(c("AssemblyID"),
                            c("ComponentID"),
                            c("PerAssemblyQty"),
                            c("EndDate"),
                            c("ComponentLevel")),
                    with.AS(queryExpression2C)
            ),
            UPDATE(
                    t("Production","BillOfMaterials")
                    ,SET(c("PerAssemblyQty"),
                            e_multiplication(
                                    c("c","PerAssemblyQty"),
                                    e_number(2)
                            ))
                    ,FROM(t("Production","BillOfMaterials"),AS("c"))
                            .$(JOIN(t("Parts"),AS("d")),
                                    ON(p_equal(
                                        c("c","ProductAssemblyID"),
                                        c("d","AssemblyID")
                                    ))
                            )
                    ,WHERE(p_equal(
                            c("d","ComponentLevel"),
                            e_number(0)
                    ))
            )
    ).build();
    // @formatter:on

    @Test
    public void testExample2C(){
        assertEquals(example2C.getTableName().toString(),"Production.BillOfMaterials");
        assertEquals(example2C.getSets().size(),1);

        assertEquals(example2C.getSets().get(0).getClass(), Update.ColumnAssignmentSet.class);
        Update.ColumnAssignmentSet setItem = (Update.ColumnAssignmentSet) example2C.getSets().get(0);
        assertEquals(setItem.getColumnName().toString(), "PerAssemblyQty");
        assertEquals(setItem.getExpression().getClass(), BinaryExpression.class);

        assertNotNull(example2C.getFrom());
        assertNotNull(example2C.getWhere());
    }


    // @formatter:off
    /**
     * UPDATE HumanResources.EmployeePayHistory
    SET PayFrequency = 2
    WHERE CURRENT OF complex_cursor;
    CLOSE complex_cursor;
    DEALLOCATE complex_cursor
     */
    public Update example2D = UPDATE(
            t("HumanResources","EmployeePayHistory")
            ,SET(c("PayFrequency"),
                e_number(2)
            )
            //TODO CURRENT OF
//            ,WHERE()
    ).build();
    // @formatter:on

    @Test
    public void testExample2D(){
        assertEquals(example2D.getTableName().toString(),"HumanResources.EmployeePayHistory");
        assertEquals(example2D.getSets().size(),1);

        assertEquals(example2D.getSets().get(0).getClass(), Update.ColumnAssignmentSet.class);
        Update.ColumnAssignmentSet setItem = (Update.ColumnAssignmentSet) example2D.getSets().get(0);
        assertEquals(setItem.getColumnName().toString(), "PayFrequency");
        assertTrue(setItem.getExpression() instanceof NumberConstant);
        assertEquals(((NumberConstant)setItem.getExpression()).getNumber().toString(), "2");
    }


    /*
    Setting Column Values
    See https://docs.microsoft.com/zh-cn/sql/t-sql/queries/update-transact-sql#ColumnValues
     */


    // @formatter:off
    /**
     * UPDATE Production.Product
    SET ListPrice = ListPrice * 2
     */
    public Update example3A = UPDATE(
            t("Production","Product")
            ,SET(c("ListPrice"),
                    e_multiplication(
                            c("ListPrice"),
                            e_number(2)
                    )
            )
    ).build();
    // @formatter:on

    @Test
    public void testExample3A(){
        assertEquals(example3A.getTableName().toString(),"Production.Product");
        assertEquals(example3A.getSets().size(),1);

        assertEquals(example3A.getSets().get(0).getClass(), Update.ColumnAssignmentSet.class);
        Update.ColumnAssignmentSet setItem = (Update.ColumnAssignmentSet) example3A.getSets().get(0);
        assertEquals(setItem.getColumnName().toString(), "ListPrice");
        assertEquals(setItem.getExpression().getClass(), BinaryExpression.class);
    }


    // @formatter:off
    /**
     * UPDATE Production.Product
    SET ListPrice += @NewPrice
    WHERE Color = N'Red'
     */
    public Update example3B = UPDATE(
            t("Production","Product")
            ,SET(c("ListPrice"),
                    Compound.ADD_ASSIGNMENT,
                    e_variable("NewPrice")
            )
            ,WHERE(p_equal(
                    c("Color"),
                    e_n_string("Red")
            ))
    ).build();
    // @formatter:on

    @Test
    public void testExample3B(){
        assertEquals(example3B.getTableName().toString(),"Production.Product");
        assertEquals(example3B.getSets().size(),1);

        assertEquals(example3B.getSets().get(0).getClass(), Update.ColumnCompoundSet.class);
        Update.ColumnCompoundSet setItem = (Update.ColumnCompoundSet) example3B.getSets().get(0);
        assertEquals(setItem.getColumnName().toString(), "ListPrice");
        assertEquals(setItem.getCompound(),Compound.ADD_ASSIGNMENT);
        assertEquals(setItem.getExpression().toString(),"@NewPrice");

        assertNotNull(example3B.getWhere());
    }


    // @formatter:off
    private Select.QuerySpecification subQuery3C0 = select_.SELECT(
            select_.$(e("MAX(OrderDate)"))
            ,FROM(t("Sales","SalesOrderHeader"),AS("so2"))
            ,WHERE(p_equal(
                    c("so2","SalesPersonID"),
                    c("so","SalesPersonID")
            ))
    ).build();

    private Select.QuerySpecification subQuery3C = select_.SELECT(
            select_.$(e("SUM(so.SubTotal)"))
            ,FROM(t("Sales","SalesOrderHeader"),AS("so"))
            ,WHERE(p_equal(
                    c("so","OrderDate"),
                    e_subquery(subQuery3C0)
            )).AND(p_equal(
                    c("Sales","SalesPerson","BusinessEntityID"),
                    c("so","SalesPersonID")
            ))
            ,GROUP_BY(c("so","SalesPersonID"))
    ).build();

    /**
     * UPDATE Sales.SalesPerson
     SET SalesYTD = SalesYTD +
     (SELECT SUM(so.SubTotal)
     FROM Sales.SalesOrderHeader AS so
     WHERE so.OrderDate = (SELECT MAX(OrderDate)
     FROM Sales.SalesOrderHeader AS so2
     WHERE so2.SalesPersonID = so.SalesPersonID)
     AND Sales.SalesPerson.BusinessEntityID = so.SalesPersonID
     GROUP BY so.SalesPersonID)
     */
    public Update example3C = UPDATE(
            t("Sales","SalesPerson")
            ,SET(c("SalesYTD"),
                    e_addition(
                            c("SalesYTD"),
                            subQuery3C
                    )
            )
    ).build();
    // @formatter:on

    @Test
    public void testExample3C(){
        assertEquals(example3C.getTableName().toString(),"Sales.SalesPerson");
        assertEquals(example3C.getSets().size(),1);

        assertEquals(example3C.getSets().get(0).getClass(), Update.ColumnAssignmentSet.class);
        Update.ColumnAssignmentSet setItem = (Update.ColumnAssignmentSet) example3C.getSets().get(0);
        assertEquals(setItem.getColumnName().toString(), "SalesYTD");
        assertEquals(setItem.getExpression().getClass(), BinaryExpression.class);
    }


    // @formatter:off
    /**
     * UPDATE Production.Location
    SET CostRate = DEFAULT
    WHERE CostRate > 20.00
     */
    public Update example3D = UPDATE(
            t("Production","Location")
            ,SET(c("CostRate"),DEFAULT())
            ,WHERE(p_greater(
                    c("CostRate"),
                    e_number(20.00)
            ))
    ).build();
    // @formatter:on

    @Test
    public void testExample3D(){
        assertEquals(example3D.getTableName().toString(),"Production.Location");
        assertEquals(example3D.getSets().size(),1);

        assertEquals(example3D.getSets().get(0).getClass(), Update.ColumnAssignmentSet.class);
        Update.ColumnAssignmentSet setItem = (Update.ColumnAssignmentSet) example3D.getSets().get(0);
        assertEquals(setItem.getColumnName().toString(), "CostRate");
        assertTrue(setItem.isUseDefault());

        assertNotNull(example3D.getWhere());
    }


    /*
    Specifying Target Objects Other than Standard Tables
    See https://docs.microsoft.com/zh-cn/sql/t-sql/queries/update-transact-sql#TargetObjects
     */

    // @formatter:off
    /**
     * UPDATE Person.vStateProvinceCountryRegion
    SET CountryRegionName = 'United States of America'
    WHERE CountryRegionName = 'United States'
     */
    public Update example4A = UPDATE(
            t("Person","vStateProvinceCountryRegion")
            ,SET(c("CountryRegionName"),e_string("United States of America"))
            ,WHERE(p_equal(
                    c("CountryRegionName"),
                    e_string("United States")
            ))
    ).build();
    // @formatter:on

    @Test
    public void testExample4A(){
        assertEquals(example4A.getTableName().toString(),"Person.vStateProvinceCountryRegion");
        assertEquals(example4A.getSets().size(),1);

        assertEquals(example4A.getSets().get(0).getClass(), Update.ColumnAssignmentSet.class);
        Update.ColumnAssignmentSet setItem = (Update.ColumnAssignmentSet) example4A.getSets().get(0);
        assertEquals(setItem.getColumnName().toString(), "CountryRegionName");
        assertTrue(setItem.getExpression() instanceof StringConstant);
        assertEquals(((StringConstant)setItem.getExpression()).getString(), "United States of America");

        assertNotNull(example4A.getWhere());
    }


    // @formatter:off
    /**
     * UPDATE sr
    SET sr.Name += ' - tool malfunction'
    FROM Production.ScrapReason AS sr
    JOIN Production.WorkOrder AS wo
         ON sr.ScrapReasonID = wo.ScrapReasonID
         AND wo.ScrappedQty > 300
     */
    public Update example4B = UPDATE(
            t("sr")
            ,SET(c("sr","Name"),
                    Compound.ADD_ASSIGNMENT,
                    e_string(" - tool malfunction"))
            ,FROM(t("Production","ScrapReason"),AS("sr"))
                    .$(JOIN(t("Production","WorkOrder"),AS("wo"))
                            ,ON(p_equal(
                                    c("sr","ScrapReasonID"),
                                    c("wo","ScrapReasonID")
                            )).AND(p_greater(
                                    c("wo","ScrappedQty"),
                                    e_number(300)
                            )))
    ).build();
    // @formatter:on

    @Test
    public void testExample4B(){
        assertEquals(example4B.getTableName().toString(),"sr");
        assertEquals(example4B.getSets().size(),1);

        assertEquals(example4B.getSets().get(0).getClass(), Update.ColumnCompoundSet.class);
        Update.ColumnCompoundSet setItem = (Update.ColumnCompoundSet) example4B.getSets().get(0);
        assertEquals(setItem.getColumnName().toString(), "sr.Name");
        assertTrue(setItem.getExpression() instanceof StringConstant);
        assertEquals(((StringConstant)setItem.getExpression()).getString(), " - tool malfunction");

        assertNotNull(example4B.getFrom());
    }


    // @formatter:off
    /**
     * -- Update columns in the table variable.
    UPDATE @MyTableVar
    SET NewVacationHours = e.VacationHours + 20,
        ModifiedDate = GETDATE()
    FROM HumanResources.Employee AS e
    WHERE e.BusinessEntityID = EmpID
     */
    public Update example4C = UPDATE(
            //TODO support variable
            t("@MyTableVar")
//            e_variable("MyTableVar")
            ,SET(c("NewVacationHours"),
                    e_addition(
                            c("e","VacationHours"),
                            e_number(20)
                    )
            ).$$(c("ModifiedDate"),
                    e("GETDATE()")
            )
            ,FROM(t("HumanResources","Employee"),AS("e"))
    ).build();
    // @formatter:on

    /**
     * UPDATE @MyTableVar
     SET NewVacationHours = e.VacationHours + 20,
     ModifiedDate = GETDATE()
     FROM HumanResources.Employee AS e
     */
    @Test
    public void testExample4C(){
//        assertEquals(update.getTableName().toString(),"Person.vStateProvinceCountryRegion");
        assertEquals(example4C.getSets().size(),2);

        assertEquals(example4C.getSets().get(0).getClass(), Update.ColumnAssignmentSet.class);
        Update.ColumnAssignmentSet setItem = (Update.ColumnAssignmentSet) example4C.getSets().get(0);
        assertEquals(setItem.getColumnName().toString(), "NewVacationHours");
        //TODO
//        assertEquals(setItem.getExpression().toString(), "' - tool malfunction'");
    }

    /*
    Updating Data Based on Data From Other Tables
    See https://docs.microsoft.com/zh-cn/sql/t-sql/queries/update-transact-sql#OtherTables
     */


    // @formatter:off
    private Select.QuerySpecification query5A1 = select_.SELECT(
            select_.$(e("MAX(OrderDate)"))
            ,FROM(t("Sales","SalesOrderHeader"))
            ,WHERE(p_equal(
                        c("SalesPersonID"),
                        c("sp","BusinessEntityID")
                ))
    ).build();

    /**
     * UPDATE Sales.SalesPerson
     SET SalesYTD = SalesYTD + SubTotal
     FROM Sales.SalesPerson AS sp
     JOIN Sales.SalesOrderHeader AS so
     ON sp.BusinessEntityID = so.SalesPersonID
     AND so.OrderDate = (SELECT MAX(OrderDate)
     FROM Sales.SalesOrderHeader
     WHERE SalesPersonID = sp.BusinessEntityID)
     */
    public Update example5A1 = UPDATE(
            t("Sales","SalesPerson")
            ,SET(c("SalesYTD"),
                    e_addition(
                            c("SalesYTD"),
                            c("SubTotal"))
            )
            ,FROM(t("Sales","SalesPerson"),AS("sp"))
                    .$(JOIN(t("Sales","SalesOrderHeader"),AS("so")),
                        ON(p_equal(
                                c("sp","BusinessEntityID"),
                                c("so","SalesPersonID")
                        )).AND(p_equal(
                                c("so","OrderDate"),
                                e_subquery(query5A1)
                        )))
    ).build();
    // @formatter:on


    // @formatter:off
    private Select.QuerySpecification query5A2_0 = select_.SELECT(
            select_.$(e("MAX(OrderDate)"))
            ,FROM(t("Sales","SalesOrderHeader"),AS("so2"))
            ,WHERE(p_equal(
                    c("so2","SalesPersonID"),
                    c("so","SalesPersonID")
            ))
    ).build();

    private Select.QuerySpecification query5A2 = select_.SELECT(
            select_.$(e("SUM(so.SubTotal)"))
            ,FROM(t("Sales","SalesOrderHeader"),AS("so"))
            ,WHERE(p_equal(
                    c("so","OrderDate"),
                    e_subquery(query5A2_0)
            )).AND(p_equal(
                    c("Sales","SalesPerson","BusinessEntityID"),
                    c("so","SalesPersonID")
            ))
            ,GROUP_BY(c("so","SalesPersonID"))
    ).build();
    /**
     * UPDATE Sales.SalesPerson
     SET SalesYTD = SalesYTD +
     (SELECT SUM(so.SubTotal)
     FROM Sales.SalesOrderHeader AS so
     WHERE so.OrderDate = (SELECT MAX(OrderDate)
     FROM Sales.SalesOrderHeader AS so2
     WHERE so2.SalesPersonID = so.SalesPersonID)
     AND Sales.SalesPerson.BusinessEntityID = so.SalesPersonID
     GROUP BY so.SalesPersonID)
     */
    public Update example5A2 = UPDATE(
            t("Sales","SalesPerson")
            ,SET(c("SalesYTD"),
                    e_addition(
                            c("SalesYTD"),
                            e_subquery(query5A2))
            )
    ).build();
    // @formatter:on


    /*
    Updating Rows in a Remote Table
    See https://docs.microsoft.com/zh-cn/sql/t-sql/queries/update-transact-sql#RemoteTables
     */


    // @formatter:off
    /**
     * -- Specify the remote data source using a four-part name
     -- in the form linked_server.catalog.schema.object.

     UPDATE MyLinkServer.AdventureWorks2012.HumanResources.Department
     SET GroupName = N'Public Relations'
     WHERE DepartmentID = 4
     */
    public Update example6A = UPDATE(
            t("MyLinkServer","AdventureWorks2012","HumanResources","Department")
            ,SET(c("GroupName"),
                    e_n_string("Public Relations")
            )
            ,WHERE(p_equal(
                    c("DepartmentID"),
                    e_number(4)
            ))
    ).build();
    // @formatter:on


    // @formatter:off
    /**
     * UPDATE OPENQUERY (MyLinkServer, 'SELECT GroupName FROM HumanResources.Department WHERE DepartmentID = 4')
     SET GroupName = 'Sales and Marketing'
     */
    public Update example6B = UPDATE(
            t()
            //TODO funtion
//                .$(t("MyLinkServer","AdventureWorks2012","HumanResources","Department"))
            ,SET(c("GroupName"),
                    e_n_string("Sales and Marketing")
            )
    ).build();
    // @formatter:on


    // @formatter:off
    /**
     * UPDATE OPENQUERY (MyLinkServer, 'SELECT GroupName FROM HumanResources.Department WHERE DepartmentID = 4')
    SET GroupName = 'Sales and Marketing'
     */
    public Update example6C = UPDATE(
            t()
            //TODO funtion
//                .$(t("MyLinkServer","AdventureWorks2012","HumanResources","Department"))
                ,SET(c("GroupName"),
                    e_n_string("Sales and Marketing")
            )
    ).build();
    // @formatter:on

    /*
    Updating Large Object Data Types
    See https://docs.microsoft.com/zh-cn/sql/t-sql/queries/update-transact-sql#LOBValues
     */


    // @formatter:off
    /**
     * UPDATE Production.Document
    SET DocumentSummary .WRITE (N'features',28,10)
    OUTPUT deleted.DocumentSummary,
           inserted.DocumentSummary
        INTO @MyTableVar
    WHERE Title = N'Front Reflector Bracket Installation'
     */
    public Update example7A = UPDATE(
            t("Production","Document")
            //TODO method
            ,SET(c("DocumentSummary"), e_n_string("Sales and Marketing"))
            ,OUTPUT(DELETED("DocumentSummary")
                    .INSERTED("DocumentSummary"),
                    INTO("MyTableVar")
            )
            ,WHERE(p_equal(
                    c("Title"),
                    e_n_string("Front Reflector Bracket Installation")
            ))
    ).build();
    // @formatter:on

    // @formatter:off
    /**
     * -- Replacing NULL value with temporary data.
    UPDATE Production.Document
    SET DocumentSummary = N'Replacing NULL value'
    WHERE Title = N'Crank Arm and Tire Maintenance'
     */
    public Update example7B1 = UPDATE(
            t("Production","Document")
            ,SET(c("DocumentSummary"),
                    e_n_string("Replacing NULL value")
            )
            ,WHERE(p_equal(
                    c("Title"),
                    e_n_string("Crank Arm and Tire Maintenance")
            ))
    ).build();

    /**
     * -- Replacing temporary data with the correct data. Setting @Length to NULL
    -- truncates all existing data from the @Offset position.
    UPDATE Production.Document
    SET DocumentSummary .WRITE(N'Carefully inspect and maintain the tires and crank arms.',0,NULL)
    WHERE Title = N'Crank Arm and Tire Maintenance'
     */
    public Update example7B2 = UPDATE(
            t("Production","Document")
            //TODO method
            ,SET(c("DocumentSummary"),
                    e_n_string("Replacing NULL value")
            )
            ,WHERE(p_equal(
                    c("Title"),
                    e_n_string("Crank Arm and Tire Maintenance")
            ))
    ).build();

    /**
     * -- Appending additional data to the end of the column by setting
    -- @Offset to NULL.
    UPDATE Production.Document
    SET DocumentSummary .WRITE (N' Appending data to the end of the column.', NULL, 0)
    WHERE Title = N'Crank Arm and Tire Maintenance'
     */
    public Update example7B3 = UPDATE(
            t("Production","Document")
            //TODO method
            ,SET(c("DocumentSummary"),
                    e_n_string("Replacing NULL value")
            )
            ,WHERE(p_equal(
                    c("Title"),
                    e_n_string("Crank Arm and Tire Maintenance")
            ))
    ).build();

    /**
     * -- Removing all data from @Offset to the end of the existing value by
    -- setting expression to NULL.
    UPDATE Production.Document
    SET DocumentSummary .WRITE (NULL, 56, 0)
    WHERE Title = N'Crank Arm and Tire Maintenance'
     */
    public Update example7B4 = UPDATE(
            t("Production","Document")
            //TODO method
            ,SET(c("DocumentSummary"),
                    e_n_string("Replacing NULL value")
            )
            ,WHERE(p_equal(
                    c("Title"),
                    e_n_string("Crank Arm and Tire Maintenance")
            ))
    ).build();

    /**
     * -- Removing partial data beginning at position 9 and ending at
    -- position 21.
    UPDATE Production.Document
    SET DocumentSummary .WRITE ('',9, 12)
    WHERE Title = N'Crank Arm and Tire Maintenance'
     */
    public Update example7B5 = UPDATE(
            t("Production","Document")
            //TODO method
            ,SET(c("DocumentSummary"),
                    e_n_string("Replacing NULL value")
            )
            ,WHERE(p_equal(
                    c("Title"),
                    e_n_string("Crank Arm and Tire Maintenance")
            ))
    ).build();
    // @formatter:on


    // @formatter:off
    /**
     * UPDATE Production.ProductPhoto
    SET ThumbNailPhoto = (
        SELECT *
        FROM OPENROWSET(BULK 'c:Tires.jpg', SINGLE_BLOB) AS x )
    WHERE ProductPhotoID = 1
     */
    public Update example7C = UPDATE(
            t("Production","ProductPhoto")
            //TODO OPENROWSET
            ,SET(c("ThumbNailPhoto"),
                    select_.SELECT(
                            select_.$(),
                            FROM(t(""))
                    ).build()
            )
            ,WHERE(p_equal(
                    c("ProductPhotoID"),
                    e_number(1)
            ))
    ).build();
    // @formatter:on


    // @formatter:off
    /**
     * UPDATE Archive.dbo.Records
    SET [Chart] = CAST('Xray 1' as varbinary(max))
    WHERE [SerialNumber] = 2
     */
    public Update example7D = UPDATE(
            t("Archive","dbo","Records")
            ,SET(c("[Chart]"),
                    e("CAST('Xray 1' as varbinary(max)) ")
            )
            ,WHERE(p_equal(
                    c("[SerialNumber]"),
                    e_number(2)
            ))
    ).build();
    // @formatter:on

    /*
    Updating User-defined Types
    See https://docs.microsoft.com/zh-cn/sql/t-sql/queries/update-transact-sql#UDTs
     */


    // @formatter:off
    /**
     * UPDATE dbo.Cities
    SET Location = CONVERT(Point, '12.3:46.2')
    WHERE Name = 'Anchorage'
     */
    public Update example8A = UPDATE(
            t("dbo","Cities")
            ,SET(c("Location"),
                    e("CONVERT(Point, '12.3:46.2')")
            )
            ,WHERE(p_equal(
                    c("Name"),
                    e_string("Anchorage")
            ))
    ).build();
    // @formatter:on


    // @formatter:off
    /**
     * UPDATE dbo.Cities
    SET Location.SetXY(23.5, 23.5)
    WHERE Name = 'Anchorage'
     */
    public Update example8B = UPDATE(
            t("dbo","Cities")
            //TODO method
            ,SET(c("Location"),
                    e("CONVERT(Point, '12.3:46.2')")
            )
            ,WHERE(p_equal(
                    c("Name"),
                    e_string("Anchorage")
            ))
    ).build();
    // @formatter:on


    // @formatter:off
    /**
     * UPDATE dbo.Cities
    SET Location.X = 23.5
    WHERE Name = 'Anchorage'
     */
    public Update example8C = UPDATE(
            t("dbo","Cities")
            ,SET(c("Location","X"),
                    e_number(23.5)
            )
            ,WHERE(p_equal(
                    c("Name"),
                    e_string("Anchorage")
            ))
    ).build();
    // @formatter:on

    /*
    Overriding the Default Behavior of the Query Optimizer by Using Hints
    See https://docs.microsoft.com/zh-cn/sql/t-sql/queries/update-transact-sql#TableHints
     */


    // @formatter:off
    /**
     * UPDATE Production.Product
    WITH (TABLOCK)
    SET ListPrice = ListPrice * 1.10
    WHERE ProductNumber LIKE 'BK-%'
     */
    public Update example9A = UPDATE(
            t("Production","Product")
            ,WITH(TABLOCK())
            ,SET(c("ListPrice"),
                    e_multiplication(
                            c("ListPrice"),
                            e_number(1.10)
                    )
            )
            ,WHERE(p_like(
                    c("ProductNumber"),
                    e_string("BK-%")
            ))
    ).build();
    // @formatter:on


    // @formatter:off
    /**
     * UPDATE Production.Product
    SET ListPrice = ListPrice * 1.10
    WHERE ProductNumber LIKE @Product
    OPTION (OPTIMIZE FOR (@Product = 'BK-%') )
     */
    public Update example9B = UPDATE(
            t("Production","Product")
            ,SET(c("ListPrice"),
                    e_multiplication(
                            c("ListPrice"),
                            e_number(1.10)
                    )
            )
            ,WHERE(p_like(
                    c("ProductNumber"),
                    e_variable("Product")
            ))
            ,OPTION(OPTIMIZE_FOR("Product","BK-%"))
    ).build();
    // @formatter:on

    /*
    Capturing the Results of the UPDATE Statement
    See https://docs.microsoft.com/zh-cn/sql/t-sql/queries/update-transact-sql#CaptureResults
     */


    // @formatter:off
    /**
     * UPDATE TOP (10) HumanResources.Employee
    SET VacationHours = VacationHours * 1.25,
        ModifiedDate = GETDATE()
    OUTPUT inserted.BusinessEntityID,
           deleted.VacationHours,
           inserted.VacationHours,
           inserted.ModifiedDate
    INTO @MyTableVar
     */
    public Update example10A = UPDATE(
            TOP(10)
            ,t("HumanResources","Employee")
            ,SET(c("VacationHours"),
                    e_multiplication(
                            c("VacationHours"),
                            e_number(1.25)
                    )
            ).$$(c("ModifiedDate"),
                    e("GETDATE()")
            )
            ,OUTPUT(
                    INSERTED("BusinessEntityID")
                    .DELETED("VacationHours")
                    .INSERTED("VacationHours")
                    .INSERTED("ModifiedDate")
                    ,INTO("MyTableVar")
            )
    ).build();
    // @formatter:on

    /*
    Using UPDATE in other statements
    See https://docs.microsoft.com/zh-cn/sql/t-sql/queries/update-transact-sql#Other
     */


    // @formatter:off
    /**
     * UPDATE HumanResources.Employee
    SET VacationHours =
        ( CASE
             WHEN SalariedFlag = 0 THEN VacationHours + @NewHours
             ELSE @NewHours
           END
        )
    WHERE CurrentFlag = 1
     */
    public Update example11A = UPDATE(
            t("HumanResources","Employee")
            ,SET(c("VacationHours"),
                    e_case()
                            .$When(p_equal(
                                    c("SalariedFlag"),
                                    e_number(0))
                            ).$Then(e_addition(
                                    c("VacationHours"),
                                    e_variable("NewHours")
                            )).$Else(e_variable("NewHours"))
                            .build()
            )
            ,WHERE(p_equal(
                    c("CurrentFlag"),
                    e_number(1)
            ))
    ).build();
    // @formatter:on


    // @formatter:off
    /**
     * UPDATE HumanResources.Department
        SET Name = N'MyNewName'
        WHERE DepartmentID BETWEEN 1 AND 2
     */
    public Update example11B = UPDATE(
            t("HumanResources","Department")
            ,SET(c("Name"),
                    e_n_string("MyNewName")
            )
            ,WHERE(p_between(
                    c("DepartmentID"),
                    e_number(1),
                    e_number(2)
            ))
    ).build();
    // @formatter:on


    /*
    Examples: SQL Data Warehouse and Parallel Data Warehouse
    See https://docs.microsoft.com/zh-cn/sql/t-sql/queries/update-transact-sql#examples-includesssdwincludessssdw-mdmd-and-includesspdwincludessspdw-mdmd
     */
}