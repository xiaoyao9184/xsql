package com.xy.xsql.tsql.core.clause;

import com.xy.xsql.tsql.core.MockParent;
import com.xy.xsql.tsql.core.MockParentBuilder;
import com.xy.xsql.tsql.model.clause.With;
import com.xy.xsql.tsql.model.statement.dml.Select;
import org.junit.Assert;
import org.junit.Test;

import static com.xy.xsql.tsql.core.clause.subquery.SubQueryBuilder.QUERYS;
import static com.xy.xsql.tsql.core.element.ColumnNameFactory.c;
import static com.xy.xsql.tsql.core.element.TableNameFactory.t;
import static com.xy.xsql.tsql.core.expression.Expressions.*;
import static com.xy.xsql.tsql.core.predicate.Predicates.*;
import static com.xy.xsql.tsql.core.statement.StatementBuilderFactory.SELECT;

/**
 * Created by xiaoyao9184 on 2017/3/11.
 */
public class WithBuilderTest {

    /*
    Examples
    See https://docs.microsoft.com/zh-cn/sql/t-sql/queries/with-common-table-expression-transact-sql#examples
     */

    // @formatter:off
    private Select selectA = SELECT()
                .$Select()
                    .$(c("SalesPersonID"))
                    .$(c("SalesOrderID"))
                    .$(e("YEAR(OrderDate)"),"SalesYear")
                .$From()
                    .$(t("Sales","SalesOrderHeader"))
                    .and()
                .$Where()
                    .$(p_is_not_null(
                            c("SalesPersonID")
                    ))
                    .and()
                .and()
                .build();

    //parent+quick

    /**
     * WITH Sales_CTE (SalesPersonID, SalesOrderID, SalesYear)
     AS
     -- Define the CTE query.
     (
     SELECT SalesPersonID, SalesOrderID, YEAR(OrderDate) AS SalesYear
     FROM Sales.SalesOrderHeader
     WHERE SalesPersonID IS NOT NULL
     )
     */
    public With exampleA = new MockParentBuilder<WithBuilder<MockParent<With>>,With>
                (WithBuilder.class,With.class)
                .$child()
                    .$(
                            "Sales_CTE",
                            c("SalesPersonID"),
                            c("SalesOrderID"),
                            c("SalesYear")
                    ).$As(selectA)
                    .and()
                .get();
    // @formatter:on

    @Test
    public void testExampleA(){
        // @formatter:off
        With with = new WithBuilder<Void>()
                .withItem()
                    .withExpressionName("Sales_CTE")
                    .withColumnName(c("SalesPersonID"))
                    .withColumnName(c("SalesOrderID"))
                    .withColumnName(c("SalesYear"))
                    .withCteQueryDefinition(selectA)
                    .and()
                .build();
        // @formatter:on

        Assert.assertEquals(with.getCommonTableExpressionList().size(), 1);
        With.CommonTableExpression cte = with.getCommonTableExpressionList().get(0);
        Assert.assertEquals(cte.getExpressionName(), "Sales_CTE");
        Assert.assertEquals(cte.getColumnName().size(),3);
        Assert.assertEquals(cte.getColumnName().get(0).toString(),"SalesPersonID");
        Assert.assertEquals(cte.getColumnName().get(1).toString(),"SalesOrderID");
        Assert.assertEquals(cte.getColumnName().get(2).toString(),"SalesYear");
    }


    // @formatter:off
    private Select selectB = SELECT()
            .$Select()
                .$(c("SalesPersonID"))
                .$(e("COUNT(*)"))
                .$From()
                    .$(t("Sales","SalesOrderHeader"))
                    .and()
                .$Where()
                    .$(p_is_not_null(
                            c("SalesPersonID")
                    ))
                    .and()
                .$GroupBy()
                    .$(c("SalesPersonID"))
                .and()
            .and()
            .build();

    //parent+quick
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
    public With exampleB = new MockParentBuilder<WithBuilder<MockParent<With>>,With>
                (WithBuilder.class,With.class)
                .$child()
                    .$(
                            "Sales_CTE",
                            c("SalesPersonID"),
                            c("NumberOfOrders")
                    ).$As(selectB)
                    .and()
                .get();
    // @formatter:on

    @Test
    public void testExampleB(){
        // @formatter:off
        With with = new WithBuilder<Void>()
                .withItem()
                    .withExpressionName("Sales_CTE")
                    .withColumnName(c("SalesPersonID"))
                    .withColumnName(c("NumberOfOrders"))
                    .withCteQueryDefinition(selectB)
                    .and()
                .build();
        // @formatter:on

        Assert.assertEquals(with.getCommonTableExpressionList().size(), 1);
        With.CommonTableExpression cte = with.getCommonTableExpressionList().get(0);
        Assert.assertEquals(cte.getExpressionName(), "Sales_CTE");
        Assert.assertEquals(cte.getColumnName().size(),2);
        Assert.assertEquals(cte.getColumnName().get(0).toString(),"SalesPersonID");
        Assert.assertEquals(cte.getColumnName().get(1).toString(),"NumberOfOrders");
    }


    // @formatter:off
    private Select selectC1 = SELECT()
            .$Select()
                .$(c("SalesPersonID"))
                .$(e("SUM(TotalDue)"),"TotalSales")
                .$(e("YEAR(OrderDate)"),"SalesYear")
                .$From()
                    .$(t("Sales","SalesOrderHeader"))
                    .and()
                .$Where()
                    .$(p_is_not_null(
                            c("SalesPersonID")
                    ))
                    .and()
                .$GroupBy()
                    .$(c("SalesPersonID"))
                    .$(e("YEAR(OrderDate)"))
                .and()
            .and()
            .build();

    private Select selectC2 = SELECT()
            .$Select()
                .$(c("BusinessEntityID"))
                .$(e("SUM(SalesQuota)"),"SalesQuota")
                .$(e("YEAR(QuotaDate)"),"SalesQuotaYear")
                .$From()
                    .$(t("Sales","SalesPersonQuotaHistory"))
                    .and()
                .$GroupBy()
                    .$(c("BusinessEntityID"))
                    .$(e("YEAR(QuotaDate)"))
                .and()
            .and()
            .build();

    //parent+quick
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
    public With exampleC = new MockParentBuilder<WithBuilder<MockParent<With>>,With>
                (WithBuilder.class,With.class)
                .$child()
                    .$(
                            "Sales_CTE",
                            c("SalesPersonID"),
                            c("TotalSales"),
                            c("SalesYear")
                    ).$As(selectC1)
                    .$(
                            "Sales_Quota_CTE",
                            c("BusinessEntityID"),
                            c("SalesQuota"),
                            c("SalesQuotaYear")
                    ).$As(selectC2)
                    .and()
                .get();
    // @formatter:on

    @Test
    public void testExampleC(){
        // @formatter:off
        With with = new WithBuilder<Void>()
                .withItem()
                    .withExpressionName("Sales_CTE")
                    .withColumnName(c("SalesPersonID"))
                    .withColumnName(c("TotalSales"))
                    .withColumnName(c("SalesYear"))
                    .withCteQueryDefinition(selectC1)
                    .and()
                .withItem()
                    .withExpressionName("Sales_Quota_CTE")
                    .withColumnName(c("BusinessEntityID"))
                    .withColumnName(c("SalesQuota"))
                    .withColumnName(c("SalesQuotaYear"))
                    .withCteQueryDefinition(selectC2)
                    .and()
                .build();
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


    // @formatter:off
    private Select selectD = SELECT()
            .withQuery()
                .$Select()
                    .$(c("ManagerID"))
                    .$(c("EmployeeID"))
                    .$(c("Title"))
                    .$(e_number(0),"EmployeeLevel")
                    .$From()
                        .$(t("dbo","MyEmployees"))
                        .and()
                    .$Where()
                        .$(p_is_null(
                                e("ManagerID")
                        ))
                        .and()
                    .and()
                .$Union_All_Select()
                    .$(c("e","ManagerID"))
                    .$(c("e","EmployeeID"))
                    .$(c("e","Title"))
                    .$(e_addition(c("EmployeeLevel"),e_number(1)))
                    .$From()
                        .$()
                            .$(t("dbo","MyEmployees"),"e")
                            .$Inner_Join()
                            .$(t("DirectReports"),"d")
                            .$On()
                                .$(p_equal(
                                        c("e","ManagerID"),
                                        c("d","EmployeeID")
                                ))
                                .and()
                            .and()
                        .and()
                    .and()
                .and()
            .build();

    //parent+quick
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
    public With exampleD = new MockParentBuilder<WithBuilder<MockParent<With>>,With>
                (WithBuilder.class,With.class)
                .$child()
                    .$(
                            "DirectReports",
                            c("ManagerID"),
                            c("EmployeeID"),
                            c("Title"),
                            c("EmployeeLevel")
                    ).$As(selectD)
                    .and()
                .get();
    // @formatter:on

    @SuppressWarnings("Duplicates")
    @Test
    public void testExampleD(){
        // @formatter:off
        With with = new WithBuilder<Void>()
                .withItem()
                    .withExpressionName("DirectReports")
                    .withColumnName(c("ManagerID"))
                    .withColumnName(c("EmployeeID"))
                    .withColumnName(c("Title"))
                    .withColumnName(c("EmployeeLevel"))
                    .withCteQueryDefinition(selectD)
                    .and()
                .build();
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


    // @formatter:off
    private Select selectE = SELECT()
            .withQuery()
                .$Select()
                    .$(c("ManagerID"))
                    .$(c("EmployeeID"))
                    .$(c("Title"))
                    .$(e_number(0),"EmployeeLevel")
                    .$From()
                        .$(t("dbo","MyEmployees"))
                        .and()
                    .$Where()
                        .$(p_is_null(
                                e("ManagerID")
                        ))
                        .and()
                    .and()
                .$Union_All_Select()
                    .$(c("e","ManagerID"))
                    .$(c("e","EmployeeID"))
                    .$(c("e","Title"))
                    .$(e_addition(c("EmployeeLevel"),e_number(1)))
                    .$From()
                        .$()
                            .$(t("dbo","MyEmployees"),"e")
                            .$Inner_Join()
                            .$(t("DirectReports"),"d")
                            .$On()
                                .$(p_equal(
                                        c("e","ManagerID"),
                                        c("d","EmployeeID")
                                ))
                                .and()
                            .and()
                        .and()
                    .and()
                .and()
            .build();

    //parent+quick
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
    public With exampleE = new MockParentBuilder<WithBuilder<MockParent<With>>,With>
                (WithBuilder.class,With.class)
                .$child()
                    .$(
                            "DirectReports",
                            c("ManagerID"),
                            c("EmployeeID"),
                            c("Title"),
                            c("EmployeeLevel")
                    ).$As(selectE)
                    .and()
                .get();
    // @formatter:on

    @SuppressWarnings("Duplicates")
    @Test
    public void testExampleE(){
        // @formatter:off
        With with = new WithBuilder<Void>()
                .withItem()
                    .withExpressionName("DirectReports")
                    .withColumnName(c("ManagerID"))
                    .withColumnName(c("EmployeeID"))
                    .withColumnName(c("Title"))
                    .withColumnName(c("EmployeeLevel"))
                    .withCteQueryDefinition(selectE)
                    .and()
                .build();
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


    // @formatter:off
    private Select selectF = SELECT()
            .withQuery()
                .$Select()
                    .$(e("CONVERT(varchar(255), e.FirstName + ' ' + e.LastName)"))
                    .$(c("e","Title"))
                    .$(c("e","EmployeeID"))
                    .$(e_number(1))
                    .$(e("CONVERT(varchar(255), e.FirstName + ' ' + e.LastName)"))
                    .$From()
                        .$(t("dbo","MyEmployees"),"e")
                            .and()
                        .and()
                    .$Where()
                        .$(p_is_null(
                                c("e","ManagerID")
                        ))
                        .and()
                    .and()
                .$Union_All_()
                .$Select()
                    .$(e("CONVERT(varchar(255), REPLICATE ('|    ' , EmployeeLevel) + e.FirstName + ' ' + e.LastName)"))
                    .$(c("e","Title"))
                    .$(c("e","EmployeeID"))
                    .$(e_addition(c("EmployeeLevel"),e_number(1)))
                    .$(e("CONVERT (varchar(255), RTRIM(Sort) + '|    ' + FirstName + ' ' + LastName)"))
                    .$From()
                        .$()
                            .$(t("dbo","MyEmployees"),"e")
                            .$Join()
                            .$(t("DirectReports"),"d")
                            .$On()
                                .$(p_equal(
                                        c("e","ManagerID"),
                                        c("d","EmployeeID")
                                ))
                                .and()
                            .and()
                        .and()
                    .and()
                .and()
            .and()
            .build();

    //parent+quick
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
    public With exampleF = new MockParentBuilder<WithBuilder<MockParent<With>>,With>
                (WithBuilder.class,With.class)
                .$child()
                    .$(
                            "DirectReports",
                            c("Name"),
                            c("Title"),
                            c("EmployeeID"),
                            c("EmployeeLevel"),
                            c("Sort")
                    ).$As(selectF)
                    .and()
                .get();
    // @formatter:on

    @Test
    public void testExampleF(){
        // @formatter:off
        With with = new WithBuilder<Void>()
                .withItem()
                    .withExpressionName("DirectReports")
                    .withColumnName(c("Name"))
                    .withColumnName(c("Title"))
                    .withColumnName(c("EmployeeID"))
                    .withColumnName(c("EmployeeLevel"))
                    .withColumnName(c("Sort"))
                    .withCteQueryDefinition(selectF)
                    .and()
                .build();
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


    // @formatter:off
    private Select selectG = SELECT()
            .withQuery()
                .$Select()
                    .$(c("EmployeeID"))
                    .$(c("ManagerID"))
                    .$(c("Title"))
                    .$From()
                        .$(t("dbo","MyEmployees"))
                        .and()
                    .$Where()
                        .$(p_is_not_null(
                                c("ManagerID")
                        ))
                        .and()
                    .and()
                .$Union_All_()
                .$Select()
                    .$(c("cte","EmployeeID"))
                    .$(c("cte","ManagerID"))
                    .$(c("cte","Title"))
                    .$From()
                        .$()
                            .$(t("cte"),null)
                            .$Join()
                            .$(t("dbo","MyEmployees"),"e")
                            .$On()
                                .$(p_equal(
                                        c("cte","ManagerID"),
                                        c("e","EmployeeID")
                                ))
                                .and()
                            .and()
                        .and()
                    .and()
                .and()
            .and()
            .build();

    //parent+quick
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
    public With exampleG = new MockParentBuilder<WithBuilder<MockParent<With>>,With>
                (WithBuilder.class,With.class)
                .$child()
                    .$(
                            "cte",
                            c("EmployeeID"),
                            c("ManagerID"),
                            c("Title")
                    ).$As(selectG)
                    .and()
                .get();
    // @formatter:on

    @Test
    public void testExampleG(){
        // @formatter:off
        With with = new WithBuilder<Void>()
                .withItem()
                    .withExpressionName("cte")
                    .withColumnName(c("EmployeeID"))
                    .withColumnName(c("ManagerID"))
                    .withColumnName(c("Title"))
                    .withCteQueryDefinition(selectG)
                    .and()
                .build();
        // @formatter:on

        Assert.assertEquals(with.getCommonTableExpressionList().size(), 1);

        With.CommonTableExpression cte = with.getCommonTableExpressionList().get(0);
        Assert.assertEquals(cte.getExpressionName(), "cte");
        Assert.assertEquals(cte.getColumnName().size(),3);
        Assert.assertEquals(cte.getColumnName().get(0).toString(),"EmployeeID");
        Assert.assertEquals(cte.getColumnName().get(1).toString(),"ManagerID");
        Assert.assertEquals(cte.getColumnName().get(2).toString(),"Title");
    }


    // @formatter:off
    private Select selectH = SELECT()
            .withQuery()
                .$Select()
                    .$(c("b","ProductAssemblyID"))
                    .$(c("b","ComponentID"))
                    .$(c("b","PerAssemblyQty"))
                    .$(c("b","EndDate"))
                    .$(e_number(0),"ComponentLevel")
                    .$From()
                        .$(t("Production","BillOfMaterials"),"b")
                            .$As("b")
                        .and()
                    .$Where()
                        .$(p_equal(
                                c("b","ProductAssemblyID"),
                                e_number(800)
                        ))
                        .$And(p_is_null(
                                c("b","EndDate")
                        ))
                        .and()
                    .and()
                .$Union_All_Select()
                    .$(c("bom","ProductAssemblyID"))
                    .$(c("bom","ComponentID"))
                    .$(c("p","PerAssemblyQty"))
                    .$(c("bom","EndDate"))
                    .$(e_addition(c("ComponentLevel"),e_number(1)))
                    .$From()
                        .$()
                            .$(t("Production","BillOfMaterials")).$As("bom")
                            .$Inner_Join()
                            .$(t("Parts")).$As("p")
                            .$On()
                                .$(p_equal(
                                        c("bom","ProductAssemblyID"),
                                        c("p","ComponentID")
                                ))
                                .$And(p_is_null(
                                        c("bom","EndDate")
                                ))
                                .and()
                            .and()
                        .and()
                    .and()
                .and()
            .build();

    //parent+quick
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
     */
    public With exampleH = new MockParentBuilder<WithBuilder<MockParent<With>>,With>
                (WithBuilder.class,With.class)
                .$child()
                    .$(
                            "Parts",
                            c("AssemblyID"),
                            c("ComponentID"),
                            c("PerAssemblyQty"),
                            c("EndDate"),
                            c("ComponentLevel")
                    ).$As(selectH)
                    .and()
                .get();
    // @formatter:on

    @Test
    public void testExampleH(){
        // @formatter:off
        With with = new WithBuilder<Void>()
                .withItem()
                    .withExpressionName("Parts")
                    .withColumnName(c("AssemblyID"))
                    .withColumnName(c("ComponentID"))
                    .withColumnName(c("PerAssemblyQty"))
                    .withColumnName(c("EndDate"))
                    .withColumnName(c("ComponentLevel"))
                    .withCteQueryDefinition(selectG)
                    .and()
                .build();
        // @formatter:on

        Assert.assertEquals(with.getCommonTableExpressionList().size(), 1);

        With.CommonTableExpression cte = with.getCommonTableExpressionList().get(0);
        Assert.assertEquals(cte.getExpressionName(), "Parts");
        Assert.assertEquals(cte.getColumnName().size(),5);
        Assert.assertEquals(cte.getColumnName().get(0).toString(),"AssemblyID");
        Assert.assertEquals(cte.getColumnName().get(1).toString(),"ComponentID");
        Assert.assertEquals(cte.getColumnName().get(2).toString(),"PerAssemblyQty");
        Assert.assertEquals(cte.getColumnName().get(3).toString(),"EndDate");
        Assert.assertEquals(cte.getColumnName().get(4).toString(),"ComponentLevel");
    }


    // @formatter:off
    private Select selectJ = SELECT()
            .$()
                .$Select()
                    .$(c("Mother"))
                    .$From()
                        .$(t("dbo","Person"))
                        .and()
                    .$Where()
                        .$(p_equal(
                                c("Name"),
                                e_string("Bonnie")
                        ))
                        .and()
                    .and()
                .$Union_Select()
                    .$(c("Father"))
                    .$From()
                        .$(t("dbo","Person"))
                        .and()
                    .$Where()
                        .$(p_equal(
                                c("Name"),
                                e_string("Bonnie")
                        ))
                        .and()
                    .and()
                .$Union_All_Select()
                    .$(c("Person","Father"))
                    .$From()
                        .$(t("Generation"))
                        .$(t("Person"))
                        .and()
                    .$Where()
                        .$(p_equal(
                                c("Generation","ID"),
                                c("Person","ID")
                        ))
                        .and()
                    .and()
                .$Union_All_Select()
                    .$(c("Person","Mother"))
                    .$From()
                        .$(t("Generation"))
                        .$(t("dbo","Person"))
                        .and()
                    .$Where()
                        .$(p_equal(
                                c("Generation","ID"),
                                c("Person","ID")
                        ))
                        .and()
                    .and()
                .and()
            .build();

    //parent+quick
    /**
     * WITH Generation (ID) AS
     (
     -- First anchor member returns Bonnie's mother.
     SELECT Mother
     FROM dbo.Person
     WHERE Name = 'Bonnie'
     UNION
     -- Second anchor member returns Bonnie's father.
     SELECT Father
     FROM dbo.Person
     WHERE Name = 'Bonnie'
     UNION ALL
     -- First recursive member returns male ancestors of the previous generation.
     SELECT Person.Father
     FROM Generation, Person
     WHERE Generation.ID=Person.ID
     UNION ALL
     -- Second recursive member returns female ancestors of the previous generation.
     SELECT Person.Mother
     FROM Generation, dbo.Person
     WHERE Generation.ID=Person.ID
     )
     */
    public With exampleJ = new MockParentBuilder<WithBuilder<MockParent<With>>,With>
                (WithBuilder.class,With.class)
                .$child()
                    .$(
                            "Generation",
                            c("ID")
                    ).$As(selectJ)
                    .and()
                .get();
    // @formatter:on

    @Test
    public void testExampleJ(){
        // @formatter:off
        With with = new WithBuilder<Void>()
                .withItem()
                    .withExpressionName("Generation")
                    .withColumnName(c("ID"))
                    .withCteQueryDefinition(selectJ)
                    .and()
                .build();
        // @formatter:on

        Assert.assertEquals(with.getCommonTableExpressionList().size(), 1);

        With.CommonTableExpression cte = with.getCommonTableExpressionList().get(0);
        Assert.assertEquals(cte.getExpressionName(), "Generation");
        Assert.assertEquals(cte.getColumnName().size(),1);
        Assert.assertEquals(cte.getColumnName().get(0).toString(),"ID");
    }


    // @formatter:off
    private Select selectK1 = SELECT()
            .$()
                .$Select()
                    .$(c("itmIDComp"))
                    .$(c("itmID"))
                    .$From()
                        .$(e_variable("t1"))
                        .and()
                    .and()
                .$Union_All_Select()
                    .$(c("itmIDComp"))
                    .$(c("itmID"))
                    .$From()
                        .$(e_variable("t2"))
                        .and()
                    .and()
                .and()
            .build();

    private Select selectK2 = SELECT()
            .$()
                .$Select()
                    .$(c("t","itmID"),"itmIDComp")
                    .$(e_null(),"itmID")
                    .$(e("CAST(0 AS bigint)"),"N")
                    .$(e_number(1),"Lvl")
                    .$From()
                        .$(QUERYS()
                                .$Select()
                                    .$(e_number(1))
                                    .and()
                                .$Union_All_Select()
                                    .$(e_number(2))
                                    .and()
                                .$Union_All_Select()
                                    .$(e_number(3))
                                    .and()
                                .$Union_All_Select()
                                    .$(e_number(4))
                                    .and()
                            .build(),"t")
                            .$As("t","itmID")
                        .and()
                    .and()
                .$Union_All_Select()
                    .$(c("t","itmIDComp"))
                    .$(c("t","itmID"))
                    .$(e("ROW_NUMBER() OVER(PARTITION BY t.itmIDComp ORDER BY t.itmIDComp, t.itmID)"),"N")
                    .$(e_addition(c("Lvl"),e_number(1)))
                    .$From()
                        .$()
                            .$(t("r"))
                                .and()
                            .$Join()
                            .$(t("vw")).$As("t")
                            .$On()
                                .$(p_equal(
                                        c("t","itmID"),
                                        c("r","itmIDComp")
                                ))
                                .and()
                            .and()
                        .and()
                    .and()
                .and()
            .build();

    //parent+quick
    /**
     * WITH vw AS
     (
     SELECT itmIDComp, itmID
     FROM @t1

     UNION ALL

     SELECT itmIDComp, itmID
     FROM @t2
     )
     ,r AS
     (
     SELECT t.itmID AS itmIDComp
     , NULL AS itmID
     ,CAST(0 AS bigint) AS N
     ,1 AS Lvl
     FROM (SELECT 1 UNION ALL SELECT 2 UNION ALL SELECT 3 UNION ALL SELECT 4) AS t (itmID)

     UNION ALL

     SELECT t.itmIDComp
     , t.itmID
     , ROW_NUMBER() OVER(PARTITION BY t.itmIDComp ORDER BY t.itmIDComp, t.itmID) AS N
     , Lvl + 1
     FROM r
     JOIN vw AS t ON t.itmID = r.itmIDComp
     )
     */
    public With exampleK = new MockParentBuilder<WithBuilder<MockParent<With>>,With>
                (WithBuilder.class,With.class)
                .$child()
                    .$("vw").$As(selectK1)
                    .$("r").$As(selectK2)
                    .and()
                .get();
    // @formatter:on

    @Test
    public void testExampleK(){
        // @formatter:off
        With with = new WithBuilder<Void>()
                .withItem()
                    .withExpressionName("vw")
                    .withCteQueryDefinition(selectK1)
                    .and()
                .withItem()
                    .withExpressionName("r")
                    .withCteQueryDefinition(selectK2)
                    .and()
                .build();
        // @formatter:on

        Assert.assertEquals(with.getCommonTableExpressionList().size(), 2);

        With.CommonTableExpression cte = with.getCommonTableExpressionList().get(0);
        Assert.assertEquals(cte.getExpressionName(), "vw");
        Assert.assertNull(cte.getColumnName());

        With.CommonTableExpression cte1 = with.getCommonTableExpressionList().get(1);
        Assert.assertEquals(cte1.getExpressionName(), "r");
        Assert.assertNull(cte1.getColumnName());
    }


    /*
    Examples: SQL Data Warehouse and Parallel Data Warehouse
    See https://docs.microsoft.com/zh-cn/sql/t-sql/queries/with-common-table-expression-transact-sql#examples-includesssdwincludessssdw-mdmd-and-includesspdwincludessspdw-mdmd
     */

    // @formatter:off
    private Select selectL = SELECT()
            .$Select()
                .$(c("SalesPersonID"))
                .$(c("SalesOrderID"))
                .$(e("YEAR(OrderDate)"),"SalesYear")
                .$From()
                    .$(t("Sales","SalesOrderHeader"))
                    .and()
                .$Where()
                    .$(p_is_not_null(
                            c("SalesPersonID")
                    ))
                    .and()
                .and()
            .build();
    //parent+quick
    /**
     * WITH Sales_CTE (SalesPersonID, SalesOrderID, SalesYear)
     AS
     -- Define the CTE query.
     (
     SELECT SalesPersonID, SalesOrderID, YEAR(OrderDate) AS SalesYear
     FROM Sales.SalesOrderHeader
     WHERE SalesPersonID IS NOT NULL
     )
     */
    public With exampleL = new MockParentBuilder<WithBuilder<MockParent<With>>,With>
            (WithBuilder.class,With.class)
            .$child()
                .$(
                        "Sales_CTE",
                        c("SalesPersonID"),
                        c("SalesOrderID"),
                        c("SalesYear")
                ).$As(selectL)
                .and()
            .get();
    // @formatter:on

    @SuppressWarnings("Duplicates")
    @Test
    public void testExampleL(){
        // @formatter:off
        With with = new WithBuilder<Void>()
                .withItem()
                    .withExpressionName("Sales_CTE")
                    .withColumnName(c("SalesPersonID"))
                    .withColumnName(c("SalesOrderID"))
                    .withColumnName(c("SalesYear"))
                    .withCteQueryDefinition(selectL)
                    .and()
                .build();
        // @formatter:on

        Assert.assertEquals(with.getCommonTableExpressionList().size(), 1);

        With.CommonTableExpression cte = with.getCommonTableExpressionList().get(0);
        Assert.assertEquals(cte.getExpressionName(), "Sales_CTE");
        Assert.assertEquals(cte.getColumnName().size(),3);

    }

    // @formatter:off
    private Select selectM = SELECT()
            .$Select()
                .$(c("SalesPersonID"))
                .$(e("COUNT(*)"))
                .$From()
                    .$(t("Sales","SalesOrderHeader"))
                    .and()
                .$Where()
                    .$(p_is_not_null(
                            c("SalesPersonID")
                    ))
                    .and()
                .$GroupBy()
                    .$(c("SalesPersonID"))
                    .and()
                .and()
            .build();
    //parent+quick
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
    public With exampleM = new MockParentBuilder<WithBuilder<MockParent<With>>,With>
            (WithBuilder.class,With.class)
            .$child()
                .$(
                        "Sales_CTE",
                        c("SalesPersonID"),
                        c("NumberOfOrders")
                ).$As(selectM)
                .and()
            .get();
    // @formatter:on

    @Test
    public void testExampleM(){
        // @formatter:off
        With with = new WithBuilder<Void>()
                .withItem()
                    .withExpressionName("Sales_CTE")
                    .withColumnName(c("SalesPersonID"))
                    .withColumnName(c("NumberOfOrders"))
                    .withCteQueryDefinition(selectM)
                    .and()
                .build();
        // @formatter:on

        Assert.assertEquals(with.getCommonTableExpressionList().size(), 1);

        With.CommonTableExpression cte = with.getCommonTableExpressionList().get(0);
        Assert.assertEquals(cte.getExpressionName(), "Sales_CTE");
        Assert.assertEquals(cte.getColumnName().size(),2);

    }


    // @formatter:off
    private Select selectN = SELECT()
            .$Select()
                .$(c("SalesPersonID"))
                .$(c("SalesOrderID"))
                .$(e("YEAR(OrderDate)"),"SalesYear")
                .$From()
                    .$(t("Sales","SalesOrderHeader"))
                    .and()
                .$Where()
                    .$(p_is_not_null(
                            c("SalesPersonID")
                    ))
                    .and()
                .and()
            .build();
    //parent+quick
    /**
     * WITH Sales_CTE (SalesPersonID, SalesOrderID, SalesYear)
     AS
     -- Define the CTE query.
     (
     SELECT SalesPersonID, SalesOrderID, YEAR(OrderDate) AS SalesYear
     FROM Sales.SalesOrderHeader
     WHERE SalesPersonID IS NOT NULL
     )
     */
    public With exampleN = new MockParentBuilder<WithBuilder<MockParent<With>>,With>
            (WithBuilder.class,With.class)
            .$child()
                .$(
                        "Sales_CTE",
                        selectN,
                        c("SalesPersonID"),
                        c("SalesOrderID"),
                        c("SalesYear")
                )
                .and()
            .get();
    // @formatter:on

    @SuppressWarnings("Duplicates")
    @Test
    public void testExampleN(){
        // @formatter:off
        With with = new WithBuilder<Void>()
                .withItem()
                    .withExpressionName("Sales_CTE")
                    .withColumnName(c("SalesPersonID"))
                    .withColumnName(c("SalesOrderID"))
                    .withColumnName(c("SalesYear"))
                    .withCteQueryDefinition(selectN)
                    .and()
                .build();
        // @formatter:on

        Assert.assertEquals(with.getCommonTableExpressionList().size(), 1);

        With.CommonTableExpression cte = with.getCommonTableExpressionList().get(0);
        Assert.assertEquals(cte.getExpressionName(), "Sales_CTE");
        Assert.assertEquals(cte.getColumnName().size(),3);

    }


    // @formatter:off
    private Select selectO = SELECT()
            .$Select()
                .$(c("SalesPersonID"))
                .$(c("SalesOrderID"))
                .$(e("YEAR(OrderDate)"),"SalesYear")
                .$From()
                    .$(t("Sales","SalesOrderHeader"))
                    .and()
                .$Where()
                    .$(p_is_not_null(
                            c("SalesPersonID")
                    ))
                    .and()
                .and()
            .build();
    //parent+quick
    /**
     * WITH Sales_CTE (SalesPersonID, SalesOrderID, SalesYear)
     AS
     -- Define the CTE query.
     (
     SELECT SalesPersonID, SalesOrderID, YEAR(OrderDate) AS SalesYear
     FROM Sales.SalesOrderHeader
     WHERE SalesPersonID IS NOT NULL
     )
     */
    public With exampleO = new MockParentBuilder<WithBuilder<MockParent<With>>,With>
            (WithBuilder.class,With.class)
            .$child()
                .$(
                        "Sales_CTE",
                        selectO,
                        c("SalesPersonID"),
                        c("SalesOrderID"),
                        c("SalesYear")
                )
                .and()
            .get();
    // @formatter:on

    @SuppressWarnings("Duplicates")
    @Test
    public void testExampleO(){
        // @formatter:off
        With with = new WithBuilder<Void>()
                .withItem()
                    .withExpressionName("Sales_CTE")
                    .withColumnName(c("SalesPersonID"))
                    .withColumnName(c("SalesOrderID"))
                    .withColumnName(c("SalesYear"))
                    .withCteQueryDefinition(selectO)
                    .and()
                .build();
        // @formatter:on

        Assert.assertEquals(with.getCommonTableExpressionList().size(), 1);

        With.CommonTableExpression cte = with.getCommonTableExpressionList().get(0);
        Assert.assertEquals(cte.getExpressionName(), "Sales_CTE");
        Assert.assertEquals(cte.getColumnName().size(),3);

    }


    // @formatter:off
    private Select selectP1 = SELECT()
            .$Select()
                .$(e("COUNT(datekey)"))
                .$(e_string("DimDate"))
                .$From()
                    .$(t("DimDate"))
                    .and()
                .and()
            .build();

    private Select selectP2 = SELECT()
            .$Select()
                .$(e("COUNT(CustomerKey)"))
                .$(e_string("DimCustomer"))
                .$From()
                    .$(t("DimCustomer"))
                    .and()
                .and()
            .build();
    //parent+quick
    /**
     * WITH
     CountDate (TotalCount, TableName) AS
     (
     SELECT COUNT(datekey), 'DimDate' FROM DimDate
     ) ,
     CountCustomer (TotalAvg, TableName) AS
     (
     SELECT COUNT(CustomerKey), 'DimCustomer' FROM DimCustomer
     )
     */
    public With exampleP = new MockParentBuilder<WithBuilder<MockParent<With>>,With>
            (WithBuilder.class,With.class)
            .$child()
                .$(
                        "CountDate",
                        selectP1,
                        c("TotalCount"),
                        c("TableName")
                )
                .$(
                        "CountCustomer",
                        selectP2,
                        c("TotalAvg"),
                        c("TableName")
                )
                .and()
            .get();
    // @formatter:on

    @Test
    public void testExampleP(){
        // @formatter:off
        With with = new WithBuilder<Void>()
                .withItem()
                    .withExpressionName("CountDate")
                    .withColumnName(c("TotalCount"))
                    .withColumnName(c("TableName"))
                    .withCteQueryDefinition(selectP1)
                    .and()
                .withItem()
                    .withExpressionName("CountCustomer")
                    .withColumnName(c("TotalAvg"))
                    .withColumnName(c("TableName"))
                    .withCteQueryDefinition(selectP2)
                    .and()
                .build();
        // @formatter:on

        Assert.assertEquals(with.getCommonTableExpressionList().size(), 2);

        With.CommonTableExpression cte = with.getCommonTableExpressionList().get(0);
        Assert.assertEquals(cte.getExpressionName(), "CountDate");
        Assert.assertEquals(cte.getColumnName().size(),2);

        With.CommonTableExpression cte1 = with.getCommonTableExpressionList().get(1);
        Assert.assertEquals(cte1.getExpressionName(), "CountCustomer");
        Assert.assertEquals(cte1.getColumnName().size(),2);

    }
}
