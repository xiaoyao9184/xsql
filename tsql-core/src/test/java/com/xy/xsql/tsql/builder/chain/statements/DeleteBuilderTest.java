package com.xy.xsql.tsql.builder.chain.statements;

import com.xy.xsql.tsql.model.datatypes.constants.NumberConstant;
import com.xy.xsql.tsql.model.queries.From;
import com.xy.xsql.tsql.model.queries.predicates.Between;
import com.xy.xsql.tsql.model.queries.predicates.Comparison;
import com.xy.xsql.tsql.model.queries.predicates.In;
import com.xy.xsql.tsql.model.statements.Delete;
import com.xy.xsql.tsql.model.queries.Select;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.datatypes.table.ColumnNameFactory.c;
import static com.xy.xsql.tsql.builder.chain.datatypes.table.TableNameFactory.t;
import static com.xy.xsql.tsql.builder.chain.elements.expressions.Expressions.e_number;
import static com.xy.xsql.tsql.builder.chain.elements.expressions.Expressions.e_string;
import static com.xy.xsql.tsql.builder.chain.queries.OutputBuilder.c_deleted;
import static com.xy.xsql.tsql.builder.chain.queries.Queries.$Select;
import static com.xy.xsql.tsql.builder.chain.queries.predicates.Predicates.*;
import static com.xy.xsql.tsql.builder.chain.statements.Statements.$Delete;
import static org.junit.Assert.*;

/**
 * Created by xiaoyao9184 on 2017/1/9.
 */
public class DeleteBuilderTest {

    /*
    Basic Syntax
    See https://docs.microsoft.com/zh-cn/sql/t-sql/statements/delete-transact-sql#BasicSyntax
     */

    // @formatter:off
    //parent+quick
    /**
     * DELETE FROM Sales.SalesPersonQuotaHistory
     */
    public Delete exampleA = $Delete()
            .$From(t("Sales","SalesPersonQuotaHistory"))
            .build();
    // @formatter:on

    @Test
    public void testExampleA(){
        // @formatter:off
        Delete delete = new DeleteBuilder()
                .withFrom(true)
                .withTableName(t("Sales","SalesPersonQuotaHistory"))
                .build();
        // @formatter:on

        assertTrue(delete.isUseForm());
        assertEquals(delete.getTableName().toString(),"Sales.SalesPersonQuotaHistory");
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
    public Delete exampleB1 = $Delete()
            .$From(t("Production","ProductCostHistory"))
            .$Where()
                .$(p_greater(
                        c("StandardCost"),
                        e_number(1000.00)
                ))
                .and()
            .build();
    // @formatter:on

    @Test
    public void testExampleB1(){
        // @formatter:off
        Delete delete = new DeleteBuilder()
                .withFrom(true)
                .withTableName(t("Production","ProductCostHistory"))
                .withWhere()
                    .withPredicate()._Comparison()
                        .withExpression(c("StandardCost"))
                        .withOperator(com.xy.xsql.tsql.model.elements.operators.Comparison.GREATER)
                        .withExpression(e_number(1000.00))
                        .and()
                    .and()
                .build();
        // @formatter:on

        assertTrue(delete.isUseForm());
        assertEquals(delete.getTableName().toString(),"Production.ProductCostHistory");
        assertEquals(delete.getWhere().getSearchCondition().getPredicate().getClass(), Comparison.class);
    }


    // @formatter:off
    //parent+quick
    /**
     * DELETE Production.ProductCostHistory
    WHERE StandardCost $BETWEEN 12.00 $AND 14.00
          $AND EndDate IS NULL
     */
    public Delete exampleB2 = $Delete()
            .$(t("Production","ProductCostHistory"))
            .$Where()
                .$(p_between(
                        c("StandardCost"),
                        e_number(12.00),
                        e_number(14.00)
                ))
                .$And(p_is_null(
                        c("EndDate")
                ))
                .and()
            .build();
    // @formatter:on

    @Test
    public void testExampleB2(){
        // @formatter:off
        Delete delete = new DeleteBuilder()
                .withTableName(t("Production","ProductCostHistory"))
                .withWhere()
                    .withPredicate()._Between()
                        .withExpression(c("StandardCost"))
                        .withExpression(e_number(12.00))
                        .withExpression(e_number(14.00))
                        .and()
                    .withAndOrNotItem()
                        .withAnd()
                            .withPredicate()._IsNull()
                                .withExpression(c("EndDate"))
                            .and()
                        .and()
                    .and()
                .build();
        // @formatter:on

        assertEquals(delete.getTableName().toString(),"Production.ProductCostHistory");
        assertEquals(delete.getWhere().getSearchCondition().getPredicate().getClass(), Between.class);
        assertEquals(delete.getWhere().getSearchCondition().getAndOrList().size(), 1);
    }


    // @formatter:off
    //parent+quick
    /**
     * DELETE FROM HumanResources.EmployeePayHistory
    WHERE CURRENT OF complex_cursor
     */
    public Delete exampleC = $Delete()
            .$From(t("HumanResources","EmployeePayHistory"))
            .$Where()
                //TODO support CURRENT OF
                .and()
            .build();
    // @formatter:on

    /**
     * DELETE FROM HumanResources.EmployeePayHistory
     WHERE CURRENT OF complex_cursor;
     */
    @Test
    public void testExampleC(){
        // @formatter:off
        Delete delete = new DeleteBuilder()
                .withFrom(true)
                .withTableName(t("HumanResources","EmployeePayHistory"))
                .withWhere()
                    //TODO support CURRENT OF
                    .and()
                .build();
        // @formatter:on

        assertTrue(delete.isUseForm());
        assertEquals(delete.getTableName().getFullName(),"HumanResources.EmployeePayHistory");
    }


    // @formatter:off
    Select subQuery = $Select()
            .$Select()
                .$(c("BusinessEntityID"))
                .$From()
                    .$(t("Sales","SalesPerson"))
                    .and()
                .$Where()
                    .$(p_greater(
                            c("SalesYTD"),
                            e_number(2500000.00)
                    ))
                    .and()
                .and()
            .build();
    //parent+quick
    /**
     * -- SQL-2003 Standard subquery

     DELETE FROM Sales.SalesPersonQuotaHistory
     WHERE BusinessEntityID IN
     (SELECT BusinessEntityID
     FROM Sales.SalesPerson
     WHERE SalesYTD > 2500000.00)
     */
    public Delete exampleD1 = $Delete()
            .$From(t("Sales","SalesPersonQuotaHistory"))
            .$Where()
                .$(p_in(
                        c("BusinessEntityID"),
                        subQuery
                ))
                .and()
            .build();
    // @formatter:on

    @Test
    public void testExampleD1(){
        // @formatter:off
        Delete delete = new DeleteBuilder()
                .withFrom(true)
                .withTableName(t("Sales","SalesPersonQuotaHistory"))
                .withWhere()
                    .withPredicate()._In()
                        .withExpression(c("SalesYTD"))
                        .withSubQuery(subQuery)
                        .and()
                    .and()
                .build();
        // @formatter:on

        assertTrue(delete.isUseForm());
        assertEquals(delete.getTableName().toString(),"Sales.SalesPersonQuotaHistory");
        assertEquals(delete.getWhere().getSearchCondition().getPredicate().getClass(), In.class);
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
    public Delete exampleD2 = $Delete()
            .$From(t("Sales","SalesPersonQuotaHistory"))
            .$From()
                .$()
                    .$(t("Sales","SalesPersonQuotaHistory"))
                    .$As("spqh")
                    .$InnerJoin()
                    .$(t("Sales","SalesPerson"))
                    .$As("sp")
                    .$On()
                        .$(p_equal(
                            c("spqh","BusinessEntityID"),
                            c("sp","BusinessEntityID")))
                        .and()
                    .and()
                .and()
            .$Where()
                .$(p_greater(
                        c("sp","SalesYTD"),
                        e_number(2500000.00)
                ))
                .and()
            .build();
    // @formatter:on

    @Test
    public void testExampleD2(){
        // @formatter:off
        Delete delete = new DeleteBuilder()
                .withFrom(true)
                .withTableName(t("Sales","SalesPersonQuotaHistory"))
                .withFrom()
                    .withItem()._Joined()
                        .withTableSource()._Base()
                            .withTableName(t("Sales","SalesPersonQuotaHistory"))
                            .withTableAlias("spqh")
                            .and()
                        .withJoinType(From.JoinTypeKeywords.INNER_JOIN)
                        .withTableSource2()._Base()
                            .withTableName(t("Sales","SalesPerson"))
                            .withTableAlias("sp")
                            .and()
                        .and()
                    .and()
                .withWhere()
                    .withPredicate()._Comparison()
                        .withExpression(c("sp","SalesYTD"))
                        .withOperator(com.xy.xsql.tsql.model.elements.operators.Comparison.GREATER)
                        .withExpression(e_number(2500000.00))
                        .and()
                    .and()
                .build();
        // @formatter:on

        assertTrue(delete.isUseForm());
        assertEquals(delete.getTableName().toString(),"Sales.SalesPersonQuotaHistory");
        assertEquals(delete.getFrom().getTableSourceList().size(), 1);
        assertEquals(delete.getWhere().getSearchCondition().getPredicate().getClass(), Comparison.class);
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
    public Delete exampleD3 = $Delete()
                .$("spqh")
                .$From()
                    .$()
                        .$(t("Sales","SalesPersonQuotaHistory"))
                        .$As("spqh")
                        .$InnerJoin()
                        .$(t("Sales","SalesPerson"))
                        .$As("sp")
                        .$On()
                            .$(p_equal(
                                    c("spqh","BusinessEntityID"),
                                    c("sp","BusinessEntityID")))
                            .and()
                        .and()
                    .and()
                .$Where()
                    .$(p_greater(
                            c("sp","SalesYTD"),
                            e_number(2500000.00)
                    ))
                    .and()
                .build();
    // @formatter:on

    @Test
    public void testExampleD3(){
        // @formatter:off
        Delete delete = new DeleteBuilder()
                .withTableAlias("spqh")
                .withFrom()
                    .withItem()._Joined()
                        .withTableSource()._Base()
                            .withTableName(t("Sales","SalesPersonQuotaHistory"))
                            .withTableAlias("spqh")
                            .and()
                        .withJoinType(From.JoinTypeKeywords.INNER_JOIN)
                        .withTableSource2()._Base()
                            .withTableName(t("Sales","SalesPerson"))
                            .withTableAlias("sp")
                            .and()
                        .withSearchCondition()
                            .withPredicate()._Comparison()
                                .withExpression(c("spqh","BusinessEntityID"))
                                .withOperator(com.xy.xsql.tsql.model.elements.operators.Comparison.EQUAL)
                                .withExpression(c("sp","BusinessEntityID"))
                                .and()
                            .and()
                        .and()
                    .and()
                .withWhere()
                    .withPredicate()._Comparison()
                        .withExpression(c("sp","SalesYTD"))
                        .withOperator(com.xy.xsql.tsql.model.elements.operators.Comparison.GREATER)
                        .withExpression(e_number(2500000.00))
                        .and()
                    .and()
                .build();
        // @formatter:on

        assertEquals(delete.getTableAlias().toString(),"spqh");
        assertEquals(delete.getFrom().getTableSourceList().size(), 1);
        assertEquals(delete.getWhere().getSearchCondition().getPredicate().getClass(), Comparison.class);
    }


    // @formatter:off
    //parent+quick
    /**
     * DELETE TOP (20)
    FROM Purchasing.PurchaseOrderDetail
    WHERE DueDate < '20020701'
     */
    public Delete exampleE1 = $Delete()
                .$Top()
                    .$_(e_number(20))
                .$From(t("Purchasing","PurchaseOrderDetail"))
                .$Where()
                    .$(p_less(
                            c("DueDate"),
                            e_string("20020701")
                    ))
                    .and()
                .build();
    // @formatter:on

    @Test
    public void testExampleE1(){
        // @formatter:off
        Delete delete = new DeleteBuilder()
                .withTop()
                    .withExpression(e_number(20))
                    .and()
                .withFrom(true)
                .withTableName(t("Purchasing","PurchaseOrderDetail"))
                .withWhere()
                    .withPredicate()._Comparison()
                        .withExpression(c("DueDate"))
                        .withOperator(com.xy.xsql.tsql.model.elements.operators.Comparison.LESS)
                        .withExpression(e_string("20020701"))
                        .and()
                    .and()
                .build();
        // @formatter:on

        assertTrue(delete.isUseForm());
        assertTrue(delete.getTop().getExpression() instanceof NumberConstant);
        assertEquals(((NumberConstant)delete.getTop().getExpression()).getNumber().toString(),"20");
        assertEquals(delete.getTableName().toString(),"Purchasing.PurchaseOrderDetail");
        assertEquals(delete.getWhere().getSearchCondition().getPredicate().getClass(), Comparison.class);
    }


    // @formatter:off
    Select subQueryE2 = $Select()
            .$Select()
                .$Top()
                    .$_(e_number(10))
                .$(c("PurchaseOrderDetailID"))
                .$From()
                    .$(t("Purchasing","PurchaseOrderDetail"))
                    .and()
                .and()
            .$OrderBy()
                .$(c("DueDate")).$Asc()
                .and()
            .build();
    //parent+quick
    /**
     * DELETE FROM Purchasing.PurchaseOrderDetail
     WHERE PurchaseOrderDetailID IN
         (SELECT TOP 10 PurchaseOrderDetailID
         FROM Purchasing.PurchaseOrderDetail
         ORDER BY DueDate ASC)
     */
    public Delete exampleE2 = $Delete()
                .$From(t("Purchasing","PurchaseOrderDetail"))
                .$Where()
                    .$(p_in(
                            c("PurchaseOrderDetailID"),
                            subQueryE2
                    ))
                    .and()
                .build();
    // @formatter:on

    @Test
    public void testExampleE2(){
        // @formatter:off
        Delete delete = new DeleteBuilder()
                .withFrom(true)
                .withTableName(t("Purchasing","PurchaseOrderDetail"))
                .withWhere()
                    .withPredicate()._In()
                        .withExpression(c("PurchaseOrderDetailID"))
                        .withSubQuery(subQueryE2)
                        .and()
                    .and()
                .build();
        // @formatter:on

        assertTrue(delete.isUseForm());
        assertEquals(delete.getTableName().toString(),"Purchasing.PurchaseOrderDetail");
        assertEquals(delete.getWhere().getSearchCondition().getPredicate().getClass(), In.class);
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
    public Delete exampleF = $Delete()
                .$(t("MyLinkServer","AdventureWorks2012","HumanResources","Department"))
                .$Where()
                    .$(p_greater(
                            c("DepartmentID"),
                            e_number(16)
                    ))
                    .and()
                .build();
    // @formatter:on

    @Test
    public void testExampleF(){
        // @formatter:off
        Delete delete = new DeleteBuilder()
                .withTableName(t("MyLinkServer","AdventureWorks2012","HumanResources","Department"))
                .withWhere()
                    .withPredicate()._Comparison()
                        .withExpression(c("DepartmentID"))
                        .withOperator(com.xy.xsql.tsql.model.elements.operators.Comparison.GREATER)
                        .withExpression(e_number(16))
                        .and()
                    .and()
                .build();
        // @formatter:on

        assertEquals(delete.getTableName().toString(),"MyLinkServer.AdventureWorks2012.HumanResources.Department");
        assertEquals(delete.getWhere().getSearchCondition().getPredicate().getClass(), Comparison.class);
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
    public Delete exampleG = $Delete()
                //TODO function

                .build();
    // @formatter:on

    @Test
    public void testExampleG(){
        // @formatter:off
        Delete delete = new DeleteBuilder()
//                .withFunction()
                .build();
        // @formatter:on

//        assertEquals(delete.getTableName().toString(),"Sales.ShoppingCartItem");
//        assertEquals(delete.getOutput().getDmlSelectList().size(),1);
//        assertEquals(delete.getWhere().getSearchCondition().getPredicate().getClass(), Comparison.class);
    }


    // @formatter:off
    //parent+quick
    /**
     * DELETE FROM OPENDATASOURCE('SQLNCLI',
        'Data Source= <server_name>; Integrated Security=SSPI')
        .AdventureWorks2012.HumanResources.Department
    WHERE DepartmentID = 17;'
     */
    public Delete exampleH = $Delete()
                //TODO function

                .build();
    // @formatter:on

    @Test
    public void testExampleH(){
        // @formatter:off
        Delete delete = new DeleteBuilder()
//                .withFunction()
                .build();
        // @formatter:on

//        assertEquals(delete.getTableName().toString(),"Sales.ShoppingCartItem");
//        assertEquals(delete.getOutput().getDmlSelectList().size(),1);
//        assertEquals(delete.getWhere().getSearchCondition().getPredicate().getClass(), Comparison.class);
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
    public Delete exampleI = $Delete()
                .$(t("Sales","ShoppingCartItem"))
                .$Output()
                    .$Output(c_deleted())
                    .and()
                .$Where()
                    .$(p_equal(
                            c("ShoppingCartID"),
                            e_number(20621)
                    ))
                    .and()
                .build();
    // @formatter:on

    @Test
    public void testExampleI(){
        // @formatter:off
        Delete delete = new DeleteBuilder()
                .withTableName(t("Sales","ShoppingCartItem"))
                .withOutput()
                    .withOutputDmlSelect()
                        .withColumnName()
                            .withDeleted()
                            .and()
                        .and()
                    .and()
                .withWhere()
                    .withPredicate()._Comparison()
                        .withExpression(c("ShoppingCartID"))
                        .withOperator(com.xy.xsql.tsql.model.elements.operators.Comparison.EQUAL)
                        .withExpression(e_number(20621))
                        .and()
                    .and()
                .build();
        // @formatter:on

        assertEquals(delete.getTableName().toString(),"Sales.ShoppingCartItem");
        assertEquals(delete.getOutput().getOutputDmlSelectList().size(),1);
        assertEquals(delete.getWhere().getSearchCondition().getPredicate().getClass(), Comparison.class);
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
        WHERE p.ProductModelID $BETWEEN 120 and 130
     */
    public Delete exampleJ = $Delete()
                .$(t("Production","ProductProductPhoto"))
                .$Output()
                    .$(c_deleted("ProductID"))
                    .$(c("p","Name"))
                    .$(c("p","ProductModelID"))
                    .$(c_deleted("ProductPhotoID"))
                    .$Into("MyTableVar")
                    .and()
                .$From()
                    .$()
                        .$(t("Production","ProductProductPhoto"))
                        .$As("ph")
                        .$Join()
                        .$(t("Production","Product"))
                        .$As("p")
                        .$On()
                            .$(p_equal(
                                    c("ph","ProductID"),
                                    c("p","ProductID")
                            ))
                            .and()
                        .and()
                    .and()
                .$Where()
                    .$(p_between(
                            c("p","ProductModelID"),
                            e_number(120),
                            e_number(130)
                    ))
                    .and()
                .build();
    // @formatter:on

    @Test
    public void testExampleJ(){
        // @formatter:off
        Delete delete = new DeleteBuilder()
                .withTableName(t("Production","ProductProductPhoto"))
                .withOutput()
                    .withDmlSelect()
                        .withColumnName()
                            .withDeleted()
                            .withColumnName("ProductID")
                            .and()
                        .and()
                    .withDmlSelect()
                        .withColumnName()
                            .withFromTableName("p")
                            .withColumnName("Name")
                            .and()
                        .and()
                    .withDmlSelect()
                        .withColumnName()
                            .withFromTableName("p")
                            .withColumnName("ProductModelID")
                            .and()
                        .and()
                    .withDmlSelect()
                        .withColumnName()
                            .withDeleted()
                            .withColumnName("ProductPhotoID")
                            .and()
                        .and()
                    .withTableVariable("MyTableVar")
                    .and()
                .withFrom()
                    .withItem()._Joined()
                        .withTableSource()._Base()
                            .withTableName(t("Production","ProductProductPhoto"))
                            .withTableAlias("ph")
                            .and()
                        .withJoinType(From.JoinTypeKeywords.JOIN)
                        .withTableSource()._Base()
                            .withTableName(t("Production","Product"))
                            .withTableAlias("p")
                            .and()
                        .withSearchCondition()
                            .withPredicate()._Comparison()
                                .withExpression(c("ph","ProductID"))
                                .withOperator(com.xy.xsql.tsql.model.elements.operators.Comparison.EQUAL)
                                .withExpression(c("p","ProductID"))
                                .and()
                            .and()
                        .and()
                    .and()
                .withWhere()
                    .withPredicate()._Between()
                        .withExpression(c("p","ProductModelID"))
                        .withExpression(e_number(120))
                        .withExpression(e_number(130))
                        .and()
                    .and()
                .build();
        // @formatter:on

        assertEquals(delete.getTableName().toString(),"Production.ProductProductPhoto");
        assertEquals(delete.getOutput().getDmlSelectList().size(),4);
        assertEquals(delete.getOutput().getTableVariable().toString(),"@MyTableVar");
        assertEquals(delete.getFrom().getTableSourceList().size(),1);
        assertEquals(delete.getWhere().getSearchCondition().getPredicate().getClass(), Between.class);
    }


    /*
    Examples: Azure SQL Data Warehouse and Parallel Data Warehouse
    See https://docs.microsoft.com/zh-cn/sql/t-sql/statements/delete-transact-sql#examples-includesssdwfullincludessssdwfull-mdmd-and-includesspdwincludessspdw-mdmd
     */

}
