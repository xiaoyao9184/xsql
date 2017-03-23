package com.xy.xsql.tsql.core.statement;

import com.xy.xsql.tsql.model.clause.From;
import com.xy.xsql.tsql.model.operator.Operators;
import com.xy.xsql.tsql.model.predicate.Between;
import com.xy.xsql.tsql.model.predicate.Comparison;
import com.xy.xsql.tsql.model.predicate.In;
import com.xy.xsql.tsql.model.statement.dml.Delete;
import com.xy.xsql.tsql.model.statement.dml.Select;
import org.junit.Assert;
import org.junit.Test;

import static com.xy.xsql.tsql.core.clause.OutputBuilder.c_deleted;
import static com.xy.xsql.tsql.core.element.ColumnNameBuilder.c;
import static com.xy.xsql.tsql.core.element.TableNameBuilder.t;
import static com.xy.xsql.tsql.core.expression.ExpressionBuilder.*;
import static com.xy.xsql.tsql.core.predicate.PredicateBuilder.*;
import static com.xy.xsql.tsql.core.statement.DeleteBuilder.DELETE;

/**
 * Created by xiaoyao9184 on 2017/1/9.
 */
public class DeleteBuilderTest {

    /**
     * DELETE FROM Sales.SalesPersonQuotaHistory;
     */
    @Test
    public void testExample1A(){
        // @formatter:off
        Delete delete = new DeleteBuilder()
                .withFrom(true)
                .withTableName(t("Sales","SalesPersonQuotaHistory"))
                .build();

        //quick
        Delete quick = DELETE()
                .$From(t("Sales","Invoices"))
                .done();
        // @formatter:on

        Assert.assertTrue(delete.isUseForm());
        Assert.assertEquals(delete.getTableName().toString(),"Sales.SalesPersonQuotaHistory");
    }

    /**
     * DELETE FROM Production.ProductCostHistory
     WHERE StandardCost > 1000.00;
     */
    @Test
    public void testExample2A(){
        // @formatter:off
        Delete delete = new DeleteBuilder()
                .withFrom(true)
                .withTableName(t("Production","ProductCostHistory"))
                .withWhere()
                    .withSearchCondition()
                        .withPredicate()._Comparison()
                            .withExpression(c("StandardCost"))
                            .withOperator(Operators.GREATER)
                            .withExpression(e_number(1000.00))
                            .and()
                        .and()
                    .and()
                .build();

        //quick
        Delete quick = DELETE()
                .$From(t("Production","ProductCostHistory"))
                .$Where()
                    .$Predicate(p_greater(
                            c("StandardCost"),
                            e_number(1000.00)
                    ))
                    .and()
                .done();
        // @formatter:on

        Assert.assertTrue(delete.isUseForm());
        Assert.assertEquals(delete.getTableName().toString(),"Production.ProductCostHistory");
        Assert.assertEquals(delete.getWhere().getSearchCondition().getPredicate().getClass(), Comparison.class);
    }

    /**
     * DELETE Production.ProductCostHistory
     WHERE StandardCost BETWEEN 12.00 AND 14.00
     AND EndDate IS NULL;
     */
    @Test
    public void testExample2A1(){
        // @formatter:off
        Delete delete = new DeleteBuilder()
                .withFrom(true)
                .withTableName(t("Production","ProductCostHistory"))
                .withWhere()
                    .withSearchCondition()
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
                    .and()
                .build();

        //quick
        Delete quick = DELETE()
                .$From(t("Production","ProductCostHistory"))
                .$Where()
                    .$Predicate(p_between(
                            c("StandardCost"),
                            e_number(12.00),
                            e_number(14.00)
                    ))
                    .$_AndPredicate(p_greater(
                            c("StandardCost"),
                            e_number(1000.00)
                    ))
                    .and()
                .done();
        // @formatter:on

        Assert.assertTrue(delete.isUseForm());
        Assert.assertEquals(delete.getTableName().toString(),"Production.ProductCostHistory");
        Assert.assertEquals(delete.getWhere().getSearchCondition().getPredicate().getClass(), Between.class);
        Assert.assertEquals(delete.getWhere().getSearchCondition().getAndOrList().size(), 1);
    }

    /**
     * DELETE FROM HumanResources.EmployeePayHistory
     WHERE CURRENT OF complex_cursor;
     */
    @Test
    public void testExample2B(){
        //TODO support CURRENT OF
        // @formatter:off
//        Delete delete = new DeleteBuilder()
//                .withFrom()
//                    .withItem()._Base()
//                        .withTableName(t("HumanResources","EmployeePayHistory"))
//                        .and()
//                    .and()
//                .withWhere()
//                    .withSearchCondition()
//                        .withPredicate()._Between()
//                            .withExpression(c("StandardCost"))
//                            .withExpression(e_number(1200))
//                            .withExpression(e_number(14.00))
//                            .and()
//                        .withAndOrNotItem()
//                            .withAnd()
//                                .withPredicate()._IsNull()
//                                    .withExpression(c("EndDate"))
//                                .and()
//                            .and()
//                        .and()
//                    .and()
//                .build();


        // @formatter:on
//        Assert.assertEquals(delete.getTableName().toString(),"Production.ProductCostHistory");
//        Assert.assertEquals(delete.getWhere().getSearchCondition().getPredicate().getClass(), Comparison.class);
//        Assert.assertEquals(delete.getWhere().getSearchCondition().getAndOrList().size(), 1);
    }

    /**
     * DELETE FROM Sales.SalesPersonQuotaHistory
     WHERE BusinessEntityID IN
     (SELECT BusinessEntityID
     FROM Sales.SalesPerson
     WHERE SalesYTD > 2500000.00);
     */
    @Test
    public void testExample2C(){
        // @formatter:off
        Select.QuerySpecification subQuery = new SelectBuilder.QuerySpecificationBuilder<Void>()
                .$(c("BusinessEntityID"))
                .$From()
                    .$(t("Sales","SalesPerson"))
                    .and()
                .$Where()
                    .$Predicate(p_greater(
                            c("SalesYTD"),
                            e_number(2500000.00)
                    ))
                    .and()
                .build();

        Delete delete = new DeleteBuilder()
                .withFrom(true)
                .withTableName(t("Sales","SalesPersonQuotaHistory"))
                .withWhere()
                    .withSearchCondition()
                        .withPredicate()._In()
                            .withExpression(c("SalesYTD"))
                            //TODO
//                            .withSubQuery(subQuery)
                            .and()
                        .and()
                    .and()
                .build();

        //quick
        Delete quick = DELETE()
                .$From(t("Sales","SalesPersonQuotaHistory"))
                .$Where()
                    .$Predicate(p_in(
                            c("BusinessEntityID"),
                            e_subquery(subQuery)
                    ))
                    .and()
                .done();
        // @formatter:on

        Assert.assertTrue(delete.isUseForm());
        Assert.assertEquals(delete.getTableName().toString(),"Sales.SalesPersonQuotaHistory");
        Assert.assertEquals(delete.getWhere().getSearchCondition().getPredicate().getClass(), In.class);
    }

    /**
     * DELETE FROM Sales.SalesPersonQuotaHistory
     FROM Sales.SalesPersonQuotaHistory AS spqh
     INNER JOIN Sales.SalesPerson AS sp
     ON spqh.BusinessEntityID = sp.BusinessEntityID
     WHERE sp.SalesYTD > 2500000.00;
     */
    @Test
    public void testExample2C1(){
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
                        .withJoinType(From.JoinType.INNER_JOIN)
                        .withTableSource2()._Base()
                            .withTableName(t("Sales","SalesPerson"))
                            .withTableAlias("sp")
                            .and()
                        .and()
                    .and()
                .withWhere()
                    .withSearchCondition()
                        .withPredicate()._Comparison()
                            .withExpression(c("sp","SalesYTD"))
                            .withOperator(Operators.GREATER)
                            .withExpression(e_number(2500000.00))
                            .and()
                        .and()
                    .and()
                .build();

        //quick
        Delete quick = DELETE()
                .$From(t("Sales","SalesPersonQuotaHistory"))
                .$From()
                    .$()
                        .$(t("Sales","SalesPersonQuotaHistory"))
                        .$As("spqh")
                        .$Inner_Join()
                        .$(t("Sales","SalesPerson"))
                        .$As("sp")
                        .$On()
                            .$Predicate(p_equal(
                                    c("spqh","BusinessEntityID"),
                                    c("sp","BusinessEntityID")))
                            .and()
                        .and()
                    .and()
                .$Where()
                    .$Predicate(p_greater(
                            c("sp","SalesYTD"),
                            e_number(2500000.00)
                    ))
                    .and()
                .done();
        // @formatter:on

        Assert.assertTrue(delete.isUseForm());
        Assert.assertEquals(delete.getTableName().toString(),"Sales.SalesPersonQuotaHistory");
        Assert.assertEquals(delete.getFrom().getTableSourceList().size(), 1);
        Assert.assertEquals(delete.getWhere().getSearchCondition().getPredicate().getClass(), Comparison.class);
    }

    /**
     * DELETE spqh
     FROM
     Sales.SalesPersonQuotaHistory AS spqh
     INNER JOIN Sales.SalesPerson             AS sp  ON spqh.BusinessEntityID = sp.BusinessEntityID
     WHERE
     sp.SalesYTD > 2500000.00;
     */
    @Test
    public void testExample2C2(){
        // @formatter:off
        Delete delete = new DeleteBuilder()
                .withTableAlias("spqh")
                .withFrom()
                    .withItem()._Joined()
                        .withTableSource()._Base()
                            .withTableName(t("Sales","SalesPersonQuotaHistory"))
                            .withTableAlias("spqh")
                            .and()
                        .withJoinType(From.JoinType.INNER_JOIN)
                        .withTableSource2()._Base()
                            .withTableName(t("Sales","SalesPerson"))
                            .withTableAlias("sp")
                            .and()
                        .withSearchCondition()
                            .withPredicate()._Comparison()
                                .withExpression(c("spqh","BusinessEntityID"))
                                .withOperator(Operators.EQUAL)
                                .withExpression(c("sp","BusinessEntityID"))
                                .and()
                            .and()
                        .and()
                    .and()
                .withWhere()
                    .withSearchCondition()
                        .withPredicate()._Comparison()
                            .withExpression(c("sp","SalesYTD"))
                            .withOperator(Operators.GREATER)
                            .withExpression(e_number(2500000.00))
                            .and()
                        .and()
                    .and()
                .build();

        //quick
        Delete quick = DELETE()
                .$From("spqh")
                .$From()
                    .$()
                        .$(t("Sales","SalesPersonQuotaHistory"))
                        .$As("spqh")
                        .$Inner_Join()
                        .$(t("Sales","SalesPerson"))
                        .$As("sp")
                        .$On()
                            .$Predicate(p_equal(
                                    c("spqh","BusinessEntityID"),
                                    c("sp","BusinessEntityID")))
                            .and()
                        .and()
                    .and()
                .$Where()
                    .$Predicate(p_greater(
                            c("sp","SalesYTD"),
                            e_number(2500000.00)
                    ))
                    .and()
                .done();
        // @formatter:on

        Assert.assertEquals(delete.getTableAlias().toString(),"spqh");
        Assert.assertEquals(delete.getFrom().getTableSourceList().size(), 1);
        Assert.assertEquals(delete.getWhere().getSearchCondition().getPredicate().getClass(), Comparison.class);
    }

    /**
     * DELETE TOP (20)
     FROM Purchasing.PurchaseOrderDetail
     WHERE DueDate < '20020701';
     */
    @Test
    public void testExample2D(){
        // @formatter:off
        Delete delete = new DeleteBuilder()
                .withTop()
                    .withExpression(e_number(20))
                    .and()
                .withFrom(true)
                .withTableName(t("Purchasing","PurchaseOrderDetail"))
                .withWhere()
                    .withSearchCondition()
                        .withPredicate()._Comparison()
                            .withExpression(c("DueDate"))
                            .withOperator(Operators.LESS)
                            .withExpression(e_string("20020701"))
                            .and()
                        .and()
                    .and()
                .build();

        //quick
        Delete quick = DELETE()
                .$Top()
                    .$_(e_number(20))
                .$From(t("Purchasing","PurchaseOrderDetail"))
                .$Where()
                    .$Predicate(p_less(
                            c("DueDate"),
                            e_string("20020701")
                    ))
                    .and()
                .done();
        // @formatter:on

        Assert.assertTrue(delete.isUseForm());
        Assert.assertEquals(delete.getTop().getExpression().toString(),"20");
        Assert.assertEquals(delete.getTableName().toString(),"Purchasing.PurchaseOrderDetail");
        Assert.assertEquals(delete.getWhere().getSearchCondition().getPredicate().getClass(), Comparison.class);
    }

    /**
     * DELETE FROM Purchasing.PurchaseOrderDetail
     WHERE PurchaseOrderDetailID IN
     (SELECT TOP 10 PurchaseOrderDetailID
     FROM Purchasing.PurchaseOrderDetail
     ORDER BY DueDate ASC);
     */
    @Test
    public void testExample2D1(){
        // @formatter:off
        Select.QuerySpecification subQuery = new SelectBuilder.QuerySpecificationBuilder<Void>()
                .$Top()
                    .$_(e_number(10))
                .$(c("PurchaseOrderDetailID"))
                .$From()
                    .$(t("Purchasing","PurchaseOrderDetail"))
                    .and()
                //TODO orderBy in subQuery
                .build();

        Delete delete = new DeleteBuilder()
                .withFrom(true)
                .withTableName(t("Purchasing","PurchaseOrderDetail"))
                .withWhere()
                    .withSearchCondition()
                        .withPredicate()._In()
                            .withExpression(c("PurchaseOrderDetailID"))
                            //TODO
//                            .withSubQuery(subQuery)
                            .and()
                        .and()
                    .and()
                .build();

        //quick
        Delete quick = DELETE()
                .$From(t("Purchasing","PurchaseOrderDetail"))
                .$Where()
                    .$Predicate(p_in(
                            c("PurchaseOrderDetailID"),
                            e_subquery(subQuery)
                    ))
                    .and()
                .done();
        // @formatter:on

        Assert.assertTrue(delete.isUseForm());
        Assert.assertEquals(delete.getTableName().toString(),"Purchasing.PurchaseOrderDetail");
        Assert.assertEquals(delete.getWhere().getSearchCondition().getPredicate().getClass(), In.class);
    }



    /**
     * DELETE Sales.ShoppingCartItem
     OUTPUT DELETED.*
     WHERE ShoppingCartID = 20621;
     */
    @Test
    public void testExample4A(){
        // @formatter:off
        Delete delete = new DeleteBuilder()
                .withTableName(t("Sales","ShoppingCartItem"))
                .withOutput()
                    .withDmlSelect()
                        .withColumnName()
                            .withDeleted()
                            .and()
                        .and()
                    .and()
                .withWhere()
                    .withSearchCondition()
                        .withPredicate()._Comparison()
                            .withExpression(c("ShoppingCartID"))
                            .withOperator(Operators.EQUAL)
                            .withExpression(e_number(20621))
                            .and()
                        .and()
                    .and()
                .build();

        //quick
        Delete quick = DELETE()
                .$(t("Sales","ShoppingCartItem"))
                .$Output()
                    .$(c_deleted())
                    .and()
                .$Where()
                    .$Predicate(p_equal(
                            c("ShoppingCartID"),
                            e_number(20621)
                    ))
                    .and()
                .done();
        // @formatter:on

        Assert.assertEquals(delete.getTableName().toString(),"Sales.ShoppingCartItem");
        Assert.assertEquals(delete.getOutput().getDmlSelectList().size(),1);
        Assert.assertEquals(delete.getWhere().getSearchCondition().getPredicate().getClass(), Comparison.class);
    }


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
     WHERE p.ProductModelID BETWEEN 120 and 130;
     */
    @Test
    public void testExample4B(){
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
                        .withJoinType(From.JoinType.JOIN)
                        .withTableSource()._Base()
                            .withTableName(t("Production","Product"))
                            .withTableAlias("p")
                            .and()
                        .withSearchCondition()
                            .withPredicate()._Comparison()
                                .withExpression(c("ph","ProductID"))
                                .withOperator(Operators.EQUAL)
                                .withExpression(c("p","ProductID"))
                                .and()
                            .and()
                        .and()
                    .and()
                .withWhere()
                    .withSearchCondition()
                        .withPredicate()._Between()
                            .withExpression(c("p","ProductModelID"))
                            .withExpression(e_number(120))
                            .withExpression(e_number(130))
                            .and()
                        .and()
                    .and()
                .build();

        //quick
        Delete quick = DELETE()
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
                            .$Predicate(p_equal(
                                    c("ph","ProductID"),
                                    c("p","ProductID")
                            ))
                            .and()
                        .and()
                    .and()
                .$Where()
                    .$Predicate(p_between(
                            c("p","ProductModelID"),
                            e_number(120),
                            e_number(130)
                    ))
                    .and()
                .done();
        // @formatter:on

        Assert.assertEquals(delete.getTableName().toString(),"Production.ProductProductPhoto");
        Assert.assertEquals(delete.getOutput().getDmlSelectList().size(),4);
        Assert.assertEquals(delete.getOutput().getTableVariable().toString(),"@MyTableVar");
        Assert.assertEquals(delete.getFrom().getTableSourceList().size(),1);
        Assert.assertEquals(delete.getWhere().getSearchCondition().getPredicate().getClass(), Between.class);
    }
}
