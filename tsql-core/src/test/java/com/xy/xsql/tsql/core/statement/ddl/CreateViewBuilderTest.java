package com.xy.xsql.tsql.core.statement.ddl;

import com.xy.xsql.tsql.model.statement.ddl.create.CreateView;
import com.xy.xsql.tsql.model.queries.Select;
import org.junit.Assert;
import org.junit.Test;

import static com.xy.xsql.tsql.core.element.ColumnNameFactory.c;
import static com.xy.xsql.tsql.core.element.TableNameFactory.t;
import static com.xy.xsql.tsql.core.expression.Expressions.*;
import static com.xy.xsql.tsql.core.predicate.Predicates.*;
import static com.xy.xsql.tsql.core.statement.ddl.CreateViewBuilder.CREATE_VIEW;
import static com.xy.xsql.tsql.core.statement.ddl.CreateViewBuilder.ENCRYPTION;
import static com.xy.xsql.tsql.core.statement.ddl.CreateViewBuilder.SCHEMABINDING;
import static com.xy.xsql.tsql.core.statement.dml.SelectBuilder.SELECT;

/**
 * Created by xiaoyao9184 on 2017/8/4.
 */
public class CreateViewBuilderTest {

    /*
    Examples
    See https://docs.microsoft.com/en-us/sql/t-sql/statements/create-view-transact-sql#examples
     */

    // @formatter:off
    /**
     * CREATE VIEW hiredate_view
    AS
    SELECT p.FirstName, p.LastName, e.BusinessEntityID, e.HireDate
    FROM HumanResources.Employee e
    JOIN Person.Person AS p ON e.BusinessEntityID = p.BusinessEntityID
     */
    public Select selectA = SELECT()
            .$Select()
                .$(c("p","FirstName"))
                .$(c("p","LastName"))
                .$(c("e","BusinessEntityID"))
                .$(c("e","HireDate"))
                .$From()
                    .$()
                        .$(t("HumanResources","Employee"),"e")
                        .$Join()
                        .$(t("Person","Person")).$As("p")
                        .$On()
                            .$(p_equal(
                                    c("e","BusinessEntityID"),
                                    c("p","BusinessEntityID")))
                            .and()
                        .and()
                    .and()
                .and()
            .build();

    public CreateView exampleA = CREATE_VIEW(selectA,"hiredate_view");
    // @formatter:on

    @Test
    public void testExampleA(){
        Assert.assertEquals(exampleA.getViewName(),"hiredate_view");
        Assert.assertEquals(exampleA.getSelectStatement(),selectA);
    }

    // @formatter:off
    /**
     * CREATE VIEW Purchasing.PurchaseOrderReject
    WITH ENCRYPTION
    AS
    SELECT PurchaseOrderID, ReceivedQty, RejectedQty,
        RejectedQty / ReceivedQty AS RejectRatio, DueDate
    FROM Purchasing.PurchaseOrderDetail
    WHERE RejectedQty / ReceivedQty > 0
    AND DueDate > CONVERT(DATETIME,'20010630',101)
     */
    public Select selectB = SELECT()
            .$Select()
                .$(c("PurchaseOrderID"))
                .$(c("ReceivedQty"))
                .$(c("RejectedQty"))
                .$(e_division(c("RejectedQty"),c("ReceivedQty")),"RejectRatio")
                .$(c("DueDate"))
                .$From()
                    .$(t("Purchasing","PurchaseOrderDetail"))
                    .and()
                .$Where()
                    .$(p_greater(
                            e_division(c("RejectedQty"),c("ReceivedQty")),
                            e_number(0)
                    ))
                    .$And(p_greater(
                            c("DueDate"),
                            e("CONVERT(DATETIME,'20010630',101)")
                    ))
                    .and()
                .and()
            .build();

    public CreateView exampleB = new CreateViewBuilder()
            .$View("Purchasing","PurchaseOrderReject")
            .$As(selectB)
            .$With(ENCRYPTION())
            .build();
    // @formatter:on

    @Test
    public void testExampleB(){
        Assert.assertEquals(exampleB.getSchemaName(),"Purchasing");
        Assert.assertEquals(exampleB.getViewName(),"PurchaseOrderReject");
        Assert.assertEquals(exampleB.getSelectStatement(),selectB);
    }

    // @formatter:off
    /**
     * CREATE VIEW dbo.SeattleOnly
    AS
    SELECT p.LastName, p.FirstName, e.JobTitle, a.City, sp.StateProvinceCode
    FROM HumanResources.Employee e
    INNER JOIN Person.Person p
    ON p.BusinessEntityID = e.BusinessEntityID
        INNER JOIN Person.BusinessEntityAddress bea
        ON bea.BusinessEntityID = e.BusinessEntityID
        INNER JOIN Person.Address a
        ON a.AddressID = bea.AddressID
        INNER JOIN Person.StateProvince sp
        ON sp.StateProvinceID = a.StateProvinceID
    WHERE a.City = 'Seattle'
    WITH CHECK OPTION
     */
    public Select selectC = SELECT()
            .$Select()
                .$(c("p","LastName"))
                .$(c("p","FirstName"))
                .$(c("e","JobTitle"))
                .$(c("a","City"))
                .$(c("sp","StateProvinceCode"))
                .$From()
                    .$()
                        .$(t("HumanResources","Employee"),"e")
                        .$Inner_Join()
                        .$(t("Person","Person"),"p")
                        .$On()
                            .$(p_equal(
                                    c("p","BusinessEntityID"),
                                    c("e","BusinessEntityID")
                            ))
                            .and()
                        .$Inner_Join()
                        .$(t("Person","BusinessEntityAddress"),"bea")
                        .$On()
                            .$(p_equal(
                                    c("bea","BusinessEntityID"),
                                    c("e","BusinessEntityID")
                            ))
                            .and()
                        .$Inner_Join()
                        .$(t("Person","Address"),"a")
                        .$On()
                            .$(p_equal(
                                    c("a","AddressID"),
                                    c("bea","AddressID")
                            ))
                            .and()
                        .$Inner_Join()
                        .$(t("Person","StateProvince"),"sp")
                        .$On()
                            .$(p_equal(
                                    c("sp","StateProvinceID"),
                                    c("a","StateProvinceID")
                            ))
                            .and()
                        .and()
                    .and()
                .$Where()
                    .$(p_equal(
                            c("a","City"),
                            e_string("Seattle")
                    ))
                    .and()
                .and()
            .build();

    public CreateView exampleC = new CreateViewBuilder()
            .$View("dbo","SeattleOnly")
            .$As(selectC)
            .$With_Check_Option()
            .build();
    // @formatter:on

    @Test
    public void testExampleC(){
        Assert.assertEquals(exampleC.getSchemaName(),"dbo");
        Assert.assertEquals(exampleC.getViewName(),"SeattleOnly");
        Assert.assertEquals(exampleC.getSelectStatement(),selectC);
        Assert.assertEquals(exampleC.isUseCheckOption(),true);
    }

    // @formatter:off
    /**
     * CREATE VIEW Sales.SalesPersonPerform
    AS
    SELECT TOP (100) SalesPersonID, SUM(TotalDue) AS TotalSales
    FROM Sales.SalesOrderHeader
    WHERE OrderDate > CONVERT(DATETIME,'20001231',101)
    GROUP BY SalesPersonID
     */
    public Select selectD = SELECT()
            .$Select()
                .$Top()
                    .$(e_number(100))
                    .and()
                .$(c("SalesPersonID"))
                .$(c("SUM(TotalDue)"),"TotalSales")
                .$From()
                    .$(t("Sales","SalesOrderHeader"))
                    .and()
                .$Where()
                    .$(p_greater(
                            c("OrderDate"),
                            e("CONVERT(DATETIME,'20001231',101)  ")
                    ))
                    .and()
                .$GroupBy()
                    .$(c("SalesPersonID"))
                    .and()
                .and()
            .build();

    public CreateView exampleD = new CreateViewBuilder()
            .$View("Sales","SalesPersonPerform")
            .$As(selectD)
            .build();
    // @formatter:on

    @Test
    public void testExampleD(){
        Assert.assertEquals(exampleD.getSchemaName(),"Sales");
        Assert.assertEquals(exampleD.getViewName(),"SalesPersonPerform");
        Assert.assertEquals(exampleD.getSelectStatement(),selectD);
    }

    // @formatter:off
    /**
     * CREATE VIEW dbo.all_supplier_view
    WITH SCHEMABINDING
    AS
    SELECT supplyID, supplier
      FROM dbo.SUPPLY1
    UNION ALL
    SELECT supplyID, supplier
      FROM dbo.SUPPLY2
    UNION ALL
    SELECT supplyID, supplier
      FROM dbo.SUPPLY3
    UNION ALL
    SELECT supplyID, supplier
      FROM dbo.SUPPLY4
     */
    public Select selectE = SELECT()
            .$()
                .$Select()
                    .$(c("supplyID"))
                    .$(c("supplier"))
                    .$From()
                        .$(t("dbo","SUPPLY1"))
                        .and()
                    .and()
                .$Union_All_Select()
                    .$(c("supplyID"))
                    .$(c("supplier"))
                    .$From()
                        .$(t("dbo","SUPPLY2"))
                        .and()
                    .and()
                .$Union_All_Select()
                    .$(c("supplyID"))
                    .$(c("supplier"))
                    .$From()
                        .$(t("dbo","SUPPLY3"))
                        .and()
                    .and()
                .$Union_All_Select()
                    .$(c("supplyID"))
                    .$(c("supplier"))
                    .$From()
                        .$(t("dbo","SUPPLY4"))
                        .and()
                    .and()
                .and()
            .build();

    public CreateView exampleE = new CreateViewBuilder()
            .$View("dbo","all_supplier_view")
            .$With(SCHEMABINDING())
            .$As(selectE)
            .build();
    // @formatter:on

    @Test
    public void testExampleE(){
        Assert.assertEquals(exampleE.getSchemaName(),"dbo");
        Assert.assertEquals(exampleE.getViewName(),"all_supplier_view");
        Assert.assertEquals(exampleE.getSelectStatement(),selectE);
    }

}