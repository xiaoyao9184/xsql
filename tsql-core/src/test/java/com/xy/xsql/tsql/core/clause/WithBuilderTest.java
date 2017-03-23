package com.xy.xsql.tsql.core.clause;

import com.xy.xsql.tsql.core.MockParent;
import com.xy.xsql.tsql.core.MockParentBuilder;
import com.xy.xsql.tsql.core.statement.dml.SelectBuilder;
import com.xy.xsql.tsql.model.clause.With;
import com.xy.xsql.tsql.model.statement.dml.Select;
import org.junit.Assert;
import org.junit.Test;

import static com.xy.xsql.tsql.core.element.ColumnNameFactory.c;
import static com.xy.xsql.tsql.core.element.TableNameFactory.t;
import static com.xy.xsql.tsql.core.expression.Expressions.e;
import static com.xy.xsql.tsql.core.predicate.Predicates.*;

/**
 * TODO not done
 * Created by xiaoyao9184 on 2017/3/11.
 */
public class WithBuilderTest {



    /**
     * -- Define the CTE expression name and column list.
     WITH Sales_CTE (SalesPersonID, SalesOrderID, SalesYear)
     AS
     -- Define the CTE query.
     (
     SELECT SalesPersonID, SalesOrderID, YEAR(OrderDate) AS SalesYear
     FROM Sales.SalesOrderHeader
     WHERE SalesPersonID IS NOT NULL
     )
     -- Define the outer query referencing the CTE name.
     */
    @Test
    public void testExampleA(){
        // @formatter:off
        Select select = new SelectBuilder()
                .withQuery()
                    .withQuerySpecification()
                        .withSelectItem().withColumnName(c("SalesPersonID")).and()
                        .withSelectItem().withColumnName(c("SalesOrderID")).and()
                        .withSelectItem().withColumnName(c("SalesPersonID")).and()
                        .withFrom()
                            .withItem()._Base()
                                .withTableName(t("Sales","SalesOrderHeader"))
                                .and()
                            .and()
                        .withWhere()
                            .withSearchCondition()
                                .$Predicate(p_is_not_null(c("SalesPersonID")))
                                .and()
                            .and()
                        .and()
                    .and()
                .build();

        With with = new WithBuilder<Void>()
                .withItem()
                    .withExpressionName("Sales_CTE")
                    .withColumnName(c("SalesPersonID"))
                    .withColumnName(c("SalesOrderID"))
                    .withColumnName(c("SalesYear"))
                    .withCteQueryDefinition(select)
                    .and()
                .build();

        //parent+quick
        MockParent<With> parent = new MockParentBuilder<WithBuilder<MockParent<With>>,With>
                (WithBuilder.class,With.class)
                .$child()
                    .$(
                            "Sales_CTE",
                            select,
                            c("SalesPersonID"),
                            c("SalesOrderID"),
                            c("SalesYear")
                    )
                    .and();
        // @formatter:on

        Assert.assertEquals(with.getCommonTableExpressionList().size(), 1);
        With.CommonTableExpression cte = with.getCommonTableExpressionList().get(0);
        Assert.assertEquals(cte.getExpressionName(), "Sales_CTE");
        Assert.assertEquals(cte.getColumnName().size(),3);
        Assert.assertEquals(cte.getColumnName().get(0).toString(),"SalesPersonID");
        Assert.assertEquals(cte.getColumnName().get(1).toString(),"SalesOrderID");
        Assert.assertEquals(cte.getColumnName().get(2).toString(),"SalesYear");
    }

    /**
     * WITH Sales_CTE (SalesPersonID, NumberOfOrders)
     AS
     (
     SELECT SalesPersonID, COUNT(*)
     FROM Sales.SalesOrderHeader
     WHERE SalesPersonID IS NOT NULL
     GROUP BY SalesPersonID
     )
     */
    @Test
    public void testExampleB(){
        // @formatter:off
        Select select = new SelectBuilder()
                .build();

        With with = new WithBuilder<Void>()
                .withItem()
                    .withExpressionName("Sales_CTE")
                    .withColumnName(c("SalesPersonID"))
                    .withColumnName(c("NumberOfOrders"))
                    .withCteQueryDefinition(select)
                    .and()
                .build();

        //parent+quick
        MockParent<With> parent = new MockParentBuilder<WithBuilder<MockParent<With>>,With>
                (WithBuilder.class,With.class)
                .$child()
                    .$(
                            "Sales_CTE",
                            select,
                            c("SalesPersonID"),
                            c("NumberOfOrders")
                    )
                    .and();
        // @formatter:on

        Assert.assertEquals(with.getCommonTableExpressionList().size(), 1);
        With.CommonTableExpression cte = with.getCommonTableExpressionList().get(0);
        Assert.assertEquals(cte.getExpressionName(), "Sales_CTE");
        Assert.assertEquals(cte.getColumnName().size(),2);
        Assert.assertEquals(cte.getColumnName().get(0).toString(),"SalesPersonID");
        Assert.assertEquals(cte.getColumnName().get(1).toString(),"NumberOfOrders");
    }

    /**
     * WITH Sales_CTE (SalesPersonID, TotalSales, SalesYear)
     AS
     -- Define the first CTE query.
     (
     SELECT SalesPersonID, SUM(TotalDue) AS TotalSales, YEAR(OrderDate) AS SalesYear
     FROM Sales.SalesOrderHeader
     WHERE SalesPersonID IS NOT NULL
     GROUP BY SalesPersonID, YEAR(OrderDate)

     )
     ,   -- Use a comma to separate multiple CTE definitions.

     -- Define the second CTE query, which returns sales quota data by year for each sales person.
     Sales_Quota_CTE (BusinessEntityID, SalesQuota, SalesQuotaYear)
     AS
     (
     SELECT BusinessEntityID, SUM(SalesQuota)AS SalesQuota, YEAR(QuotaDate) AS SalesQuotaYear
     FROM Sales.SalesPersonQuotaHistory
     GROUP BY BusinessEntityID, YEAR(QuotaDate)
     )
     */
    @Test
    public void testExampleC(){
        // @formatter:off
        Select select = new SelectBuilder()
                .build();

        With with = new WithBuilder<Void>()
                .withItem()
                    .withExpressionName("Sales_CTE")
                    .withColumnName(c("SalesPersonID"))
                    .withColumnName(c("TotalSales"))
                    .withColumnName(c("SalesYear"))
                    .withCteQueryDefinition(select)
                    .and()
                .withItem()
                    .withExpressionName("Sales_Quota_CTE")
                    .withColumnName(c("BusinessEntityID"))
                    .withColumnName(c("SalesQuota"))
                    .withColumnName(c("SalesQuotaYear"))
                    .withCteQueryDefinition(select)
                    .and()
                .build();

        //parent+quick
        MockParent<With> parent = new MockParentBuilder<WithBuilder<MockParent<With>>,With>
                (WithBuilder.class,With.class)
                .$child()
                    .$(
                            "Sales_CTE",
                            select,
                            c("SalesPersonID"),
                            c("TotalSales"),
                            c("SalesYear")
                    )
                    .$(
                            "Sales_Quota_CTE",
                            select,
                            c("BusinessEntityID"),
                            c("SalesQuota"),
                            c("SalesQuotaYear")
                    )
                    .and();
        // @formatter:on

        Assert.assertEquals(with.getCommonTableExpressionList().size(), 2);

        With.CommonTableExpression cte = with.getCommonTableExpressionList().get(0);
        Assert.assertEquals(cte.getExpressionName(), "Sales_CTE");
        Assert.assertEquals(cte.getColumnName().size(),3);
        Assert.assertEquals(cte.getColumnName().get(0).toString(),"SalesPersonID");
        Assert.assertEquals(cte.getColumnName().get(1).toString(),"TotalSales");
        Assert.assertEquals(cte.getColumnName().get(2).toString(),"SalesYear");

        With.CommonTableExpression cte2 = with.getCommonTableExpressionList().get(1);
        Assert.assertEquals(cte2.getExpressionName(), "Sales_Quota_CTE");
        Assert.assertEquals(cte2.getColumnName().size(),3);
        Assert.assertEquals(cte2.getColumnName().get(0).toString(),"BusinessEntityID");
        Assert.assertEquals(cte2.getColumnName().get(1).toString(),"SalesQuota");
        Assert.assertEquals(cte2.getColumnName().get(2).toString(),"SalesQuotaYear");
    }

    /**
     * WITH DirectReports(ManagerID, EmployeeID, Title, EmployeeLevel) AS
     (
     SELECT ManagerID, EmployeeID, Title, 0 AS EmployeeLevel
     FROM dbo.MyEmployees
     WHERE ManagerID IS NULL
     UNION ALL
     SELECT e.ManagerID, e.EmployeeID, e.Title, EmployeeLevel + 1
     FROM dbo.MyEmployees AS e
     INNER JOIN DirectReports AS d
     ON e.ManagerID = d.EmployeeID
     )
     */
    @Test
    public void testExampleD(){
        // @formatter:off
        Select select = new SelectBuilder()
                .build();

        With with = new WithBuilder<Void>()
                .withItem()
                    .withExpressionName("DirectReports")
                    .withColumnName(c("ManagerID"))
                    .withColumnName(c("EmployeeID"))
                    .withColumnName(c("Title"))
                    .withColumnName(c("EmployeeLevel"))
                    .withCteQueryDefinition(select)
                    .and()
                .build();

        //parent+quick
        MockParent<With> parent = new MockParentBuilder<WithBuilder<MockParent<With>>,With>
                (WithBuilder.class,With.class)
                .$child()
                    .$(
                            "DirectReports",
                            select,
                            c("ManagerID"),
                            c("EmployeeID"),
                            c("Title"),
                            c("EmployeeLevel")
                    )
                    .and();
        // @formatter:on

        Assert.assertEquals(with.getCommonTableExpressionList().size(), 1);

        With.CommonTableExpression cte = with.getCommonTableExpressionList().get(0);
        Assert.assertEquals(cte.getExpressionName(), "DirectReports");
        Assert.assertEquals(cte.getColumnName().size(),4);
        Assert.assertEquals(cte.getColumnName().get(0).toString(),"ManagerID");
        Assert.assertEquals(cte.getColumnName().get(1).toString(),"EmployeeID");
        Assert.assertEquals(cte.getColumnName().get(2).toString(),"Title");
        Assert.assertEquals(cte.getColumnName().get(3).toString(),"EmployeeLevel");
    }

    /**
     * WITH DirectReports(ManagerID, EmployeeID, Title, EmployeeLevel) AS
     (
     SELECT ManagerID, EmployeeID, Title, 0 AS EmployeeLevel
     FROM dbo.MyEmployees
     WHERE ManagerID IS NULL
     UNION ALL
     SELECT e.ManagerID, e.EmployeeID, e.Title, EmployeeLevel + 1
     FROM dbo.MyEmployees AS e
     INNER JOIN DirectReports AS d
     ON e.ManagerID = d.EmployeeID
     )
     */
    @Test
    public void testExampleE(){
        // @formatter:off
        Select select = new SelectBuilder()
                .build();

        With with = new WithBuilder<Void>()
                .withItem()
                    .withExpressionName("DirectReports")
                    .withColumnName(c("ManagerID"))
                    .withColumnName(c("EmployeeID"))
                    .withColumnName(c("Title"))
                    .withColumnName(c("EmployeeLevel"))
                    .withCteQueryDefinition(select)
                    .and()
                .build();

        //parent+quick
        MockParent<With> parent = new MockParentBuilder<WithBuilder<MockParent<With>>,With>
                (WithBuilder.class,With.class)
                .$child()
                    .$(
                            "DirectReports",
                            select,
                            c("ManagerID"),
                            c("EmployeeID"),
                            c("Title"),
                            c("EmployeeLevel")
                    )
                    .and();
        // @formatter:on

        Assert.assertEquals(with.getCommonTableExpressionList().size(), 1);

        With.CommonTableExpression cte = with.getCommonTableExpressionList().get(0);
        Assert.assertEquals(cte.getExpressionName(), "DirectReports");
        Assert.assertEquals(cte.getColumnName().size(),4);
        Assert.assertEquals(cte.getColumnName().get(0).toString(),"ManagerID");
        Assert.assertEquals(cte.getColumnName().get(1).toString(),"EmployeeID");
        Assert.assertEquals(cte.getColumnName().get(2).toString(),"Title");
        Assert.assertEquals(cte.getColumnName().get(3).toString(),"EmployeeLevel");
    }


    /**
     * WITH DirectReports(Name, Title, EmployeeID, EmployeeLevel, Sort)
     AS (SELECT CONVERT(varchar(255), e.FirstName + ' ' + e.LastName),
     e.Title,
     e.EmployeeID,
     1,
     CONVERT(varchar(255), e.FirstName + ' ' + e.LastName)
     FROM dbo.MyEmployees AS e
     WHERE e.ManagerID IS NULL
     UNION ALL
     SELECT CONVERT(varchar(255), REPLICATE ('|    ' , EmployeeLevel) +
     e.FirstName + ' ' + e.LastName),
     e.Title,
     e.EmployeeID,
     EmployeeLevel + 1,
     CONVERT (varchar(255), RTRIM(Sort) + '|    ' + FirstName + ' ' +
     LastName)
     FROM dbo.MyEmployees AS e
     JOIN DirectReports AS d ON e.ManagerID = d.EmployeeID
     )
     */
    @Test
    public void testExampleF(){
        // @formatter:off
        Select select = new SelectBuilder()
                .build();

        With with = new WithBuilder<Void>()
                .withItem()
                    .withExpressionName("DirectReports")
                    .withColumnName(c("Name"))
                    .withColumnName(c("Title"))
                    .withColumnName(c("EmployeeID"))
                    .withColumnName(c("EmployeeLevel"))
                    .withColumnName(c("Sort"))
                    .withCteQueryDefinition(select)
                    .and()
                .build();

        //parent+quick
        MockParent<With> parent = new MockParentBuilder<WithBuilder<MockParent<With>>,With>
                (WithBuilder.class,With.class)
                .$child()
                    .$(
                            "DirectReports",
                            select,
                            c("Name"),
                            c("Title"),
                            c("EmployeeID"),
                            c("EmployeeLevel"),
                            c("Sort")
                    )
                    .and();
        // @formatter:on

        Assert.assertEquals(with.getCommonTableExpressionList().size(), 1);

        With.CommonTableExpression cte = with.getCommonTableExpressionList().get(0);
        Assert.assertEquals(cte.getExpressionName(), "DirectReports");
        Assert.assertEquals(cte.getColumnName().size(),5);
        Assert.assertEquals(cte.getColumnName().get(0).toString(),"Name");
        Assert.assertEquals(cte.getColumnName().get(1).toString(),"Title");
        Assert.assertEquals(cte.getColumnName().get(2).toString(),"EmployeeID");
        Assert.assertEquals(cte.getColumnName().get(3).toString(),"EmployeeLevel");
        Assert.assertEquals(cte.getColumnName().get(4).toString(),"Sort");
    }

    /**
     * WITH cte (EmployeeID, ManagerID, Title) as
     (
     SELECT EmployeeID, ManagerID, Title
     FROM dbo.MyEmployees
     WHERE ManagerID IS NOT NULL
     UNION ALL
     SELECT cte.EmployeeID, cte.ManagerID, cte.Title
     FROM cte
     JOIN  dbo.MyEmployees AS e
     ON cte.ManagerID = e.EmployeeID
     )
     --Uses MAXRECURSION to limit the recursive levels to 2
     */
    @Test
    public void testExampleG(){
        // @formatter:off
        Select select = new SelectBuilder()
                .build();

        With with = new WithBuilder<Void>()
                .withItem()
                    .withExpressionName("cte")
                    .withColumnName(c("EmployeeID"))
                    .withColumnName(c("ManagerID"))
                    .withColumnName(c("Title"))
                    .withCteQueryDefinition(select)
                    .and()
                .build();

        //parent+quick
        MockParent<With> parent = new MockParentBuilder<WithBuilder<MockParent<With>>,With>
                (WithBuilder.class,With.class)
                .$child()
                    .$(
                            "cte",
                            select,
                            c("EmployeeID"),
                            c("ManagerID"),
                            c("Title")
                    )
                    .and();
        // @formatter:on

        Assert.assertEquals(with.getCommonTableExpressionList().size(), 1);

        With.CommonTableExpression cte = with.getCommonTableExpressionList().get(0);
        Assert.assertEquals(cte.getExpressionName(), "cte");
        Assert.assertEquals(cte.getColumnName().size(),3);
        Assert.assertEquals(cte.getColumnName().get(0).toString(),"EmployeeID");
        Assert.assertEquals(cte.getColumnName().get(1).toString(),"ManagerID");
        Assert.assertEquals(cte.getColumnName().get(2).toString(),"Title");
    }
}
