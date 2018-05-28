package com.xy.xsql.tsql.style.constructor.introducer.statements;

import com.xy.xsql.tsql.model.datatypes.constants.NumberConstant;
import com.xy.xsql.tsql.model.queries.From;
import com.xy.xsql.tsql.model.queries.predicates.Between;
import com.xy.xsql.tsql.model.queries.predicates.Comparison;
import com.xy.xsql.tsql.model.queries.predicates.In;
import com.xy.xsql.tsql.model.statements.Delete;
import com.xy.xsql.tsql.model.queries.Select;
import com.xy.xsql.tsql.style.constructor.introducer.queries.select_;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.datatypes.table.ColumnNameFactory.c;
import static com.xy.xsql.tsql.builder.chain.datatypes.table.TableNameFactory.t;
import static com.xy.xsql.tsql.builder.chain.elements.expressions.Expressions.*;
import static com.xy.xsql.tsql.builder.chain.queries.predicates.Predicates.*;
import static com.xy.xsql.tsql.style.constructor.introducer.queries.from.*;
import static com.xy.xsql.tsql.style.constructor.introducer.queries.output.*;
import static com.xy.xsql.tsql.style.constructor.introducer.queries.select.order_by.ASC;
import static com.xy.xsql.tsql.style.constructor.introducer.queries.select.order_by.ORDER_BY;
import static com.xy.xsql.tsql.style.constructor.introducer.queries.select.select.AS;
import static com.xy.xsql.tsql.style.constructor.introducer.queries.top.TOP;
import static com.xy.xsql.tsql.style.constructor.introducer.queries.where.WHERE;
import static com.xy.xsql.tsql.style.constructor.introducer.statements.delete.DELETE;
import static com.xy.xsql.tsql.style.constructor.introducer.statements.delete.FROM_;
import static org.junit.Assert.*;

/**
 * Created by xiaoyao9184 on 2018/5/15.
 */
public class deleteTest {

    /*
    Basic Syntax
    See https://docs.microsoft.com/zh-cn/sql/t-sql/statements/delete-transact-sql#BasicSyntax
     */

    // @formatter:off
    //parent+quick
    /**
     * DELETE FROM Sales.SalesPersonQuotaHistory
     */
    public Delete exampleA = DELETE(
            FROM_()
            ,t("Sales","SalesPersonQuotaHistory")
    ).build();
    // @formatter:on

    @Test
    public void testExampleA(){
        assertTrue(exampleA.isUseForm());
        assertEquals(exampleA.getTableName().toString(),"Sales.SalesPersonQuotaHistory");
    }

    /*
    Limiting the Rows Deleted
    See https://docs.microsoft.com/zh-cn/sql/t-sql/statements/delete-transact-sql#LimitRows
     */


    // @formatter:off
    //parent+quick
    /**
     * DELETE FROM Production.ProductCostHistory
    WHERE StandardCost > 1000.00
     */
    public Delete exampleB1 = DELETE(
            FROM_()
            ,t("Production","ProductCostHistory")
            ,WHERE(p_greater(
                    c("StandardCost"),
                    e_number(1000.00)
            ))
    ).build();
    // @formatter:on

    @Test
    public void testExampleB1(){
        assertTrue(exampleB1.isUseForm());
        assertEquals(exampleB1.getTableName().toString(),"Production.ProductCostHistory");
        assertEquals(exampleB1.getWhere().getSearchCondition().getPredicate().getClass(), Comparison.class);
    }


    // @formatter:off
    //parent+quick
    /**
     * DELETE Production.ProductCostHistory
    WHERE StandardCost BETWEEN 12.00 AND 14.00
          $AND EndDate IS NULL
     */
    public Delete exampleB2 = DELETE(
            t("Production","ProductCostHistory")
            ,WHERE(p_between(
                    c("StandardCost"),
                    e_number(12.00),
                    e_number(14.00)
            )).AND(p_is_null(
                    c("EndDate")
            ))
    ).build();
    // @formatter:on

    @Test
    public void testExampleB2(){
        assertEquals(exampleB2.getTableName().toString(),"Production.ProductCostHistory");
        assertEquals(exampleB2.getWhere().getSearchCondition().getPredicate().getClass(), Between.class);
        assertEquals(exampleB2.getWhere().getSearchCondition().getAndOrList().size(), 1);
    }


    // @formatter:off
    //parent+quick
    /**
     * DELETE FROM HumanResources.EmployeePayHistory
    WHERE CURRENT OF complex_cursor
     */
    public Delete exampleC = DELETE(
            FROM_()
            ,t("HumanResources","EmployeePayHistory")
            ,WHERE(p_equal(null,null))
    ).build();
    // @formatter:on

    /**
     * DELETE FROM HumanResources.EmployeePayHistory
     WHERE CURRENT OF complex_cursor;
     */
    @Test
    public void testExampleC(){
        assertTrue(exampleC.isUseForm());
        assertEquals(exampleC.getTableName().getFullName(),"HumanResources.EmployeePayHistory");
    }


    // @formatter:off
    Select.QuerySpecification subQuery = select_.SELECT(
            select_.$(c("BusinessEntityID"))
            ,FROM(t("Sales","SalesPerson"))
            ,WHERE(p_greater(
                    c("SalesYTD"),
                    e_number(2500000.00)
            ))
    ).build();
    //parent+quick
    /**
     * -- SQL-2003 Standard subquery

     DELETE FROM Sales.SalesPersonQuotaHistory
     WHERE BusinessEntityID IN
     (SELECT BusinessEntityID
     FROM Sales.SalesPerson
     WHERE SalesYTD > 2500000.00)
     */
    public Delete exampleD1 = DELETE(
            FROM_()
            ,t("Sales","SalesPersonQuotaHistory")
            ,WHERE(p_in(
                    c("BusinessEntityID"),
                    subQuery
            ))
    ).build();
    // @formatter:on

    @Test
    public void testExampleD1(){
        assertTrue(exampleD1.isUseForm());
        assertEquals(exampleD1.getTableName().toString(),"Sales.SalesPersonQuotaHistory");
        assertEquals(exampleD1.getWhere().getSearchCondition().getPredicate().getClass(), In.class);
    }


    // @formatter:off
    //parent+quick
    /**
     * -- Transact-SQL extension

    DELETE FROM Sales.SalesPersonQuotaHistory
    FROM Sales.SalesPersonQuotaHistory AS spqh
    INNER JOIN Sales.SalesPerson AS sp
    ON spqh.BusinessEntityID = sp.BusinessEntityID
    WHERE sp.SalesYTD > 2500000.00
     */
    public Delete exampleD2 = DELETE(
            FROM_()
            ,t("Sales","SalesPersonQuotaHistory")
            ,FROM(t("Sales","SalesPersonQuotaHistory"),AS("spqh"))
                    .INNER(JOIN(t("Sales","SalesPerson"),AS("sp"))
                            ,ON(p_equal(
                                    c("spqh","BusinessEntityID"),
                                    c("sp","BusinessEntityID"))
                            ))
            ,WHERE(p_greater(
                    c("sp","SalesYTD"),
                    e_number(2500000.00)
            ))
    ).build();
    // @formatter:on

    @Test
    public void testExampleD2(){
        assertTrue(exampleD2.isUseForm());
        assertEquals(exampleD2.getTableName().toString(),"Sales.SalesPersonQuotaHistory");
        assertEquals(exampleD2.getFrom().getTableSourceList().size(), 1);
        assertEquals(exampleD2.getWhere().getSearchCondition().getPredicate().getClass(), Comparison.class);
    }


    // @formatter:off
    //parent+quick
    /**
     * -- No need to mention target table more than once.

    DELETE spqh
      FROM
            Sales.SalesPersonQuotaHistory AS spqh
        INNER JOIN Sales.SalesPerson AS sp
            ON spqh.BusinessEntityID = sp.BusinessEntityID
      WHERE  sp.SalesYTD > 2500000.00
     */
    public Delete exampleD3 = DELETE(
                "spqh"
                ,FROM(t("Sales","SalesPersonQuotaHistory"),AS("spqh"))
                    .INNER(JOIN(t("Sales","SalesPerson"),AS("sp"))
                            ,ON(p_equal(
                                    c("spqh","BusinessEntityID"),
                                    c("sp","BusinessEntityID")))
                    )
                ,WHERE(p_greater(
                        c("sp","SalesYTD"),
                        e_number(2500000.00)
                ))
    ).build();
    // @formatter:on

    @Test
    public void testExampleD3(){
        assertEquals(exampleD3.getTableAlias().toString(),"spqh");
        assertEquals(exampleD3.getFrom().getTableSourceList().size(), 1);
        assertEquals(exampleD3.getWhere().getSearchCondition().getPredicate().getClass(), Comparison.class);
    }


    // @formatter:off
    //parent+quick
    /**
     * DELETE TOP (20)
    FROM Purchasing.PurchaseOrderDetail
    WHERE DueDate < '20020701'
     */
    public Delete exampleE1 = DELETE(
                TOP(20)
                ,FROM_()
                ,t("Purchasing","PurchaseOrderDetail")
                ,WHERE(p_less(
                        c("DueDate"),
                        e_string("20020701")
                ))
    ).build();
    // @formatter:on

    @Test
    public void testExampleE1(){
        assertTrue(exampleE1.isUseForm());
        assertTrue(exampleE1.getTop().getExpression() instanceof NumberConstant);
        assertEquals(((NumberConstant)exampleE1.getTop().getExpression()).getNumber().toString(),"20");
        assertEquals(exampleE1.getTableName().toString(),"Purchasing.PurchaseOrderDetail");
        assertEquals(exampleE1.getWhere().getSearchCondition().getPredicate().getClass(), Comparison.class);
    }


    // @formatter:off
    Select subQueryE2 = select_.$(select_.SELECT(
            TOP(10)
            ,select_.$(c("PurchaseOrderDetailID"))
            ,FROM(t("Purchasing","PurchaseOrderDetail"))
    ),ORDER_BY(c("DueDate"),ASC())
    ).build();
    //parent+quick
    /**
     * DELETE FROM Purchasing.PurchaseOrderDetail
     WHERE PurchaseOrderDetailID IN
         (SELECT TOP 10 PurchaseOrderDetailID
         FROM Purchasing.PurchaseOrderDetail
         ORDER BY DueDate ASC)
     */
    public Delete exampleE2 = DELETE(
                FROM_()
                ,t("Purchasing","PurchaseOrderDetail")
                ,WHERE(p_in(
                        c("PurchaseOrderDetailID"),
                        subQueryE2
                ))
    ).build();
    // @formatter:on

    @Test
    public void testExampleE2(){
        assertTrue(exampleE2.isUseForm());
        assertEquals(exampleE2.getTableName().toString(),"Purchasing.PurchaseOrderDetail");
        assertEquals(exampleE2.getWhere().getSearchCondition().getPredicate().getClass(), In.class);
    }


    /*
    Deleting Rows From a Remote Table
    See https://docs.microsoft.com/zh-cn/sql/t-sql/statements/delete-transact-sql#RemoteTables
     */


    // @formatter:off
    //parent+quick
    /**
     * -- Specify the remote data source using a four-part name
    -- in the form linked_server.catalog.schema.object.

    DELETE MyLinkServer.AdventureWorks2012.HumanResources.Department
    WHERE DepartmentID > 16
     */
    public Delete exampleF = DELETE(
                t("MyLinkServer","AdventureWorks2012","HumanResources","Department")
                ,WHERE(p_greater(
                        c("DepartmentID"),
                        e_number(16)
                ))
    ).build();
    // @formatter:on

    @Test
    public void testExampleF(){
        assertEquals(exampleF.getTableName().toString(),"MyLinkServer.AdventureWorks2012.HumanResources.Department");
        assertEquals(exampleF.getWhere().getSearchCondition().getPredicate().getClass(), Comparison.class);
    }

    /*
    Capturing the results of the DELETE statement
    See https://docs.microsoft.com/zh-cn/sql/t-sql/statements/delete-transact-sql#CaptureResults
     */


    // @formatter:off
    //parent+quick
    /**
     * DELETE OPENQUERY (MyLinkServer, 'SELECT Name, GroupName
    FROM AdventureWorks2012.HumanResources.Department
    WHERE DepartmentID = 18')
     */
    public Delete exampleG = DELETE(
            //TODO function
            "OPENQUERY (MyLinkServer, 'SELECT Name, GroupName\n" +
"    FROM AdventureWorks2012.HumanResources.Department\n" +
"    WHERE DepartmentID = 18')"
    ).build();
    // @formatter:on

    @Test
    public void testExampleG(){
//        assertEquals(exampleG.getTableName().toString(),"Sales.ShoppingCartItem");
//        assertEquals(exampleG.getOutput().getDmlSelectList().size(),1);
//        assertEquals(exampleG.getWhere().getSearchCondition().getPredicate().getClass(), Comparison.class);
    }


    // @formatter:off
    //parent+quick
    /**
     * DELETE FROM OPENDATASOURCE('SQLNCLI',
        'Data Source= <server_name>; Integrated Security=SSPI')
        .AdventureWorks2012.HumanResources.Department
    WHERE DepartmentID = 17;'
     */
    public Delete exampleH = DELETE(
            FROM_()
                //TODO function
            ,"OPENDATASOURCE('SQLNCLI',\n" +
"        'Data Source= <server_name>; Integrated Security=SSPI')\n" +
"        .AdventureWorks2012.HumanResources.Department"
            ,WHERE(p_equal(
                    c("DepartmentID"),
                    e("17")
            ))
    ).build();
    // @formatter:on

    @Test
    public void testExampleH(){
//        assertEquals(exampleH.getTableName().toString(),"Sales.ShoppingCartItem");
//        assertEquals(exampleH.getOutput().getDmlSelectList().size(),1);
//        assertEquals(exampleH.getWhere().getSearchCondition().getPredicate().getClass(), Comparison.class);
    }

    /*
    Capturing the results of the DELETE statement
    See https://docs.microsoft.com/zh-cn/sql/t-sql/statements/delete-transact-sql#CaptureResults
     */

    // @formatter:off
    //parent+quick
    /**
     * DELETE Sales.ShoppingCartItem
    OUTPUT DELETED.*
    WHERE ShoppingCartID = 20621
     */
    public Delete exampleI = DELETE(
                t("Sales","ShoppingCartItem")
                ,OUTPUT(DELETED())
                ,WHERE(p_equal(
                        c("ShoppingCartID"),
                        e_number(20621)
                ))
    ).build();
    // @formatter:on

    @Test
    public void testExampleI(){
        assertEquals(exampleI.getTableName().toString(),"Sales.ShoppingCartItem");
        assertEquals(exampleI.getOutput().getOutputDmlSelectList().size(),1);
        assertEquals(exampleI.getWhere().getSearchCondition().getPredicate().getClass(), Comparison.class);
    }


    // @formatter:off
    //parent+quick
    /**
     * DELETE Production.ProductProductPhoto
    OUTPUT DELETED.ProductID,
           p.Name,
           p.ProductModelID,
           DELETED.ProductPhotoID
        INTO @MyTableVar
    FROM Production.ProductProductPhoto AS ph
    JOIN Production.Product as p
        ON ph.ProductID = p.ProductID
        WHERE p.ProductModelID BETWEEN 120 and 130
     */
    public Delete exampleJ = DELETE(
            t("Production","ProductProductPhoto")
            ,OUTPUT(
                    DELETED("ProductID")
                    .$$(c("p","Name"))
                    .$$(c("p","ProductModelID"))
                    .DELETED("ProductPhotoID")
                    ,INTO("MyTableVar")
            )
            ,FROM(t("Production","ProductProductPhoto"),AS("ph"))
                    .$(JOIN(t("Production","Product"),AS("p"))
                            ,ON(p_equal(
                                    c("ph","ProductID"),
                                    c("p","ProductID")
                            ))
                    )
            ,WHERE(p_between(
                    c("p","ProductModelID"),
                    e_number(120),
                    e_number(130)
            ))
    ).build();
    // @formatter:on

    @Test
    public void testExampleJ(){
        assertEquals(exampleJ.getTableName().toString(),"Production.ProductProductPhoto");
        assertEquals(exampleJ.getOutput().getDmlSelectList().size(),4);
        assertEquals(exampleJ.getOutput().getTableVariable().toString(),"@MyTableVar");
        assertEquals(exampleJ.getFrom().getTableSourceList().size(),1);
        assertEquals(exampleJ.getWhere().getSearchCondition().getPredicate().getClass(), Between.class);
    }


    /*
    Examples: Azure SQL Data Warehouse and Parallel Data Warehouse
    See https://docs.microsoft.com/zh-cn/sql/t-sql/statements/delete-transact-sql#examples-includesssdwfullincludessssdwfull-mdmd-and-includesspdwincludessspdw-mdmd
     */

}