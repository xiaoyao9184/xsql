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
import static com.xy.xsql.tsql.core.expression.BinaryExpressions.e_addition;
import static com.xy.xsql.tsql.core.expression.BinaryExpressions.e_assignment;
import static com.xy.xsql.tsql.core.expression.Expressions.e;
import static com.xy.xsql.tsql.core.expression.Expressions.e_number;
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
                    .$Predicate(p_is_not_null(c("SalesPersonID")))
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
                            selectA,
                            c("SalesPersonID"),
                            c("SalesOrderID"),
                            c("SalesYear")
                    )
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
                    .$Predicate(p_is_not_null(c("SalesPersonID")))
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
                            selectB,
                            c("SalesPersonID"),
                            c("NumberOfOrders")
                    )
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
                    .$Predicate(p_is_not_null(c("SalesPersonID")))
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
                            selectC1,
                            c("SalesPersonID"),
                            c("TotalSales"),
                            c("SalesYear")
                    )
                    .$(
                            "Sales_Quota_CTE",
                            selectC2,
                            c("BusinessEntityID"),
                            c("SalesQuota"),
                            c("SalesQuotaYear")
                    )
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
                        .$Predicate(p_is_null(e("ManagerID")))
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
                                .$Predicate(p_equal(c("e","ManagerID"),c("d","EmployeeID")))
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
                            selectD,
                            c("ManagerID"),
                            c("EmployeeID"),
                            c("Title"),
                            c("EmployeeLevel")
                    )
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
                        .$Predicate(p_is_null(e("ManagerID")))
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
                                .$Predicate(p_equal(c("e","ManagerID"),c("d","EmployeeID")))
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
                            selectE,
                            c("ManagerID"),
                            c("EmployeeID"),
                            c("Title"),
                            c("EmployeeLevel")
                    )
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
                        .$Predicate(p_is_null(c("e","ManagerID")))
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
                                .$Predicate(p_equal(
                                        c("e","ManagerID"),
                                        c("d","EmployeeID")))
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
                            selectF,
                            c("Name"),
                            c("Title"),
                            c("EmployeeID"),
                            c("EmployeeLevel"),
                            c("Sort")
                    )
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
                        .$Predicate(p_is_not_null(c("ManagerID")))
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
                                .$Predicate(p_equal(
                                        c("cte","ManagerID"),
                                        c("e","EmployeeID")))
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
                            selectG,
                            c("EmployeeID"),
                            c("ManagerID"),
                            c("Title")
                    )
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
                            .and()
                        .and()
                    .$Where()
                        .$Predicate(p_equal(c("b","ProductAssemblyID"),e_number(800)))
                        .$_AndPredicate(p_is_null(c("b","EndDate")))
                        .and()
                    .and()
                .$Union_All_Select()
                    .$(c("bom","ProductAssemblyID"))
                    .$(c("bom","ComponentID"))
                    .$(c("p","PerAssemblyQty"))
                    .$(c("bom","EndDate"))
                    .$(e_assignment(c("ComponentLevel"),e_number(1)))
                    .$From()
                        .$()
                            .$(t("Production","BillOfMaterials"),"bom")
                            .$Inner_Join()
                            .$(t("Parts"),"p")
                            .$On()
                                .$Predicate(p_equal(
                                        c("bom","ProductAssemblyID"),
                                        c("p","ComponentID")))
                                .and()
                            .and()
                        .and()
                    .$Where()
                        .$Predicate(p_is_null(c("bom","EndDate")))
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
                            selectH,
                            c("AssemblyID"),
                            c("ComponentID"),
                            c("PerAssemblyQty"),
                            c("EndDate"),
                            c("ComponentLevel")
                    )
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

    //TODO IJK


    /*
    Examples: SQL Data Warehouse and Parallel Data Warehouse
    See https://docs.microsoft.com/zh-cn/sql/t-sql/queries/with-common-table-expression-transact-sql#examples-includesssdwincludessssdw-mdmd-and-includesspdwincludessspdw-mdmd
     */

    //TODO LMNOP
}
