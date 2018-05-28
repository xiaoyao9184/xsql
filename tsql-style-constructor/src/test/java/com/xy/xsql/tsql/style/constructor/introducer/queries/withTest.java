package com.xy.xsql.tsql.style.constructor.introducer.queries;

import com.xy.xsql.tsql.model.queries.Select;
import com.xy.xsql.tsql.model.queries.With;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.queries.Queries.$Select;
import static com.xy.xsql.tsql.builder.chain.datatypes.table.ColumnNameFactory.c;
import static com.xy.xsql.tsql.builder.chain.datatypes.table.TableNameFactory.t;
import static com.xy.xsql.tsql.builder.chain.elements.expressions.Expressions.*;
import static com.xy.xsql.tsql.builder.chain.queries.Queries.$Querys;
import static com.xy.xsql.tsql.builder.chain.queries.predicates.Predicates.*;
import static com.xy.xsql.tsql.style.constructor.introducer.queries.with.*;
import static org.junit.Assert.*;

/**
 * Created by xiaoyao9184 on 2018/5/11.
 */
public class withTest {

    /*
    Examples
    See https://docs.microsoft.com/zh-cn/sql/t-sql/queries/with-common-table-expression-transact-sql#examples
     */

    // @formatter:off
    private Select selectA = $Select()
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
    public With exampleA = WITH(
            "Sales_CTE",
            $(c("SalesPersonID"),
                    c("SalesOrderID"),
                    c("SalesYear")),
            AS(selectA)
    ).build();
    // @formatter:on

    @Test
    public void testExampleA(){
        assertEquals(exampleA.getCommonTableExpressionList().size(), 1);
        With.CommonTableExpression cte = exampleA.getCommonTableExpressionList().get(0);
        assertEquals(cte.getExpressionName(), "Sales_CTE");
        assertEquals(cte.getColumnName().size(),3);
        assertEquals(cte.getColumnName().get(0).toString(),"SalesPersonID");
        assertEquals(cte.getColumnName().get(1).toString(),"SalesOrderID");
        assertEquals(cte.getColumnName().get(2).toString(),"SalesYear");
    }


    // @formatter:off
    private Select selectB = $Select()
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
    public With exampleB = WITH(
            "Sales_CTE",
            $(c("SalesPersonID"),
                    c("NumberOfOrders")),
            AS(selectB)
    ).build();
    // @formatter:on

    @Test
    public void testExampleB(){
        assertEquals(exampleB.getCommonTableExpressionList().size(), 1);
        With.CommonTableExpression cte = exampleB.getCommonTableExpressionList().get(0);
        assertEquals(cte.getExpressionName(), "Sales_CTE");
        assertEquals(cte.getColumnName().size(),2);
        assertEquals(cte.getColumnName().get(0).toString(),"SalesPersonID");
        assertEquals(cte.getColumnName().get(1).toString(),"NumberOfOrders");
    }


    // @formatter:off
    private Select selectC1 = $Select()
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

    private Select selectC2 = $Select()
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
    public With exampleC = WITH(
            "Sales_CTE",
            $(
                    c("SalesPersonID"),
                    c("TotalSales"),
                    c("SalesYear")
            ),
            AS(selectC1)
    ).$$(
            "Sales_Quota_CTE",
            $(
                    c("BusinessEntityID"),
                    c("SalesQuota"),
                    c("SalesQuotaYear")
            ),
            AS(selectC2)
    ).build();
    // @formatter:on

    @Test
    public void testExampleC(){
        assertEquals(exampleC.getCommonTableExpressionList().size(), 2);

        With.CommonTableExpression cte = exampleC.getCommonTableExpressionList().get(0);
        assertEquals(cte.getExpressionName(), "Sales_CTE");
        assertEquals(cte.getColumnName().size(),3);
        assertEquals(cte.getColumnName().get(0).toString(),"SalesPersonID");
        assertEquals(cte.getColumnName().get(1).toString(),"TotalSales");
        assertEquals(cte.getColumnName().get(2).toString(),"SalesYear");

        With.CommonTableExpression cte2 = exampleC.getCommonTableExpressionList().get(1);
        assertEquals(cte2.getExpressionName(), "Sales_Quota_CTE");
        assertEquals(cte2.getColumnName().size(),3);
        assertEquals(cte2.getColumnName().get(0).toString(),"BusinessEntityID");
        assertEquals(cte2.getColumnName().get(1).toString(),"SalesQuota");
        assertEquals(cte2.getColumnName().get(2).toString(),"SalesQuotaYear");
    }


    // @formatter:off
    private Select selectD = $Select()
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
                .$UnionAllSelect()
                    .$(c("e","ManagerID"))
                    .$(c("e","EmployeeID"))
                    .$(c("e","Title"))
                    .$(e_addition(c("EmployeeLevel"),e_number(1)))
                    .$From()
                        .$()
                            .$(t("dbo","MyEmployees"),"e")
                            .$InnerJoin()
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
    public With exampleD = WITH(
            "DirectReports",
            $(
                    c("ManagerID"),
                    c("EmployeeID"),
                    c("Title"),
                    c("EmployeeLevel")
            ),
            AS(selectD)
    ).build();
    // @formatter:on

    @SuppressWarnings("Duplicates")
    @Test
    public void testExampleD(){
        assertEquals(exampleD.getCommonTableExpressionList().size(), 1);

        With.CommonTableExpression cte = exampleD.getCommonTableExpressionList().get(0);
        assertEquals(cte.getExpressionName(), "DirectReports");
        assertEquals(cte.getColumnName().size(),4);
        assertEquals(cte.getColumnName().get(0).toString(),"ManagerID");
        assertEquals(cte.getColumnName().get(1).toString(),"EmployeeID");
        assertEquals(cte.getColumnName().get(2).toString(),"Title");
        assertEquals(cte.getColumnName().get(3).toString(),"EmployeeLevel");
    }


    // @formatter:off
    private Select selectE = $Select()
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
                .$UnionAllSelect()
                    .$(c("e","ManagerID"))
                    .$(c("e","EmployeeID"))
                    .$(c("e","Title"))
                    .$(e_addition(c("EmployeeLevel"),e_number(1)))
                    .$From()
                        .$()
                            .$(t("dbo","MyEmployees"),"e")
                            .$InnerJoin()
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
    public With exampleE = WITH(
            "DirectReports",
            $(
                    c("ManagerID")
                    ,c("EmployeeID")
                    ,c("Title")
                    ,c("EmployeeLevel")
            ),
            AS(selectE)
    ).build();
    // @formatter:on

    @SuppressWarnings("Duplicates")
    @Test
    public void testExampleE(){
        assertEquals(exampleE.getCommonTableExpressionList().size(), 1);

        With.CommonTableExpression cte = exampleE.getCommonTableExpressionList().get(0);
        assertEquals(cte.getExpressionName(), "DirectReports");
        assertEquals(cte.getColumnName().size(),4);
        assertEquals(cte.getColumnName().get(0).toString(),"ManagerID");
        assertEquals(cte.getColumnName().get(1).toString(),"EmployeeID");
        assertEquals(cte.getColumnName().get(2).toString(),"Title");
        assertEquals(cte.getColumnName().get(3).toString(),"EmployeeLevel");
    }


    // @formatter:off
    private Select selectF = $Select()
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
                .$UnionAll_()
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
    public With exampleF = WITH(
            "DirectReports",
            $(
                    c("Name"),
                    c("Title"),
                    c("EmployeeID"),
                    c("EmployeeLevel"),
                    c("Sort")
            ),
            AS(selectF)
    ).build();
    // @formatter:on

    @Test
    public void testExampleF(){
        assertEquals(exampleF.getCommonTableExpressionList().size(), 1);

        With.CommonTableExpression cte = exampleF.getCommonTableExpressionList().get(0);
        assertEquals(cte.getExpressionName(), "DirectReports");
        assertEquals(cte.getColumnName().size(),5);
        assertEquals(cte.getColumnName().get(0).toString(),"Name");
        assertEquals(cte.getColumnName().get(1).toString(),"Title");
        assertEquals(cte.getColumnName().get(2).toString(),"EmployeeID");
        assertEquals(cte.getColumnName().get(3).toString(),"EmployeeLevel");
        assertEquals(cte.getColumnName().get(4).toString(),"Sort");
    }


    // @formatter:off
    private Select selectG = $Select()
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
                .$UnionAll_()
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
    public With exampleG = WITH(
            "cte",
            $(
                    c("EmployeeID"),
                    c("ManagerID"),
                    c("Title")
            ),
            AS(selectG)
    ).build();
    // @formatter:on

    @Test
    public void testExampleG(){
        assertEquals(exampleG.getCommonTableExpressionList().size(), 1);

        With.CommonTableExpression cte = exampleG.getCommonTableExpressionList().get(0);
        assertEquals(cte.getExpressionName(), "cte");
        assertEquals(cte.getColumnName().size(),3);
        assertEquals(cte.getColumnName().get(0).toString(),"EmployeeID");
        assertEquals(cte.getColumnName().get(1).toString(),"ManagerID");
        assertEquals(cte.getColumnName().get(2).toString(),"Title");
    }


    // @formatter:off
    private Select selectH = $Select()
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
                .$UnionAllSelect()
                    .$(c("bom","ProductAssemblyID"))
                    .$(c("bom","ComponentID"))
                    .$(c("p","PerAssemblyQty"))
                    .$(c("bom","EndDate"))
                    .$(e_addition(c("ComponentLevel"),e_number(1)))
                    .$From()
                        .$()
                            .$(t("Production","BillOfMaterials")).$As("bom")
                            .$InnerJoin()
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
    public With exampleH = WITH(
            "Parts",
            $(
                    c("AssemblyID")
                    ,c("ComponentID")
                    ,c("PerAssemblyQty")
                    ,c("EndDate")
                    ,c("ComponentLevel")
            ),
            AS(selectH)
    ).build();
    // @formatter:on

    @Test
    public void testExampleH(){
        assertEquals(exampleH.getCommonTableExpressionList().size(), 1);

        With.CommonTableExpression cte = exampleH.getCommonTableExpressionList().get(0);
        assertEquals(cte.getExpressionName(), "Parts");
        assertEquals(cte.getColumnName().size(),5);
        assertEquals(cte.getColumnName().get(0).toString(),"AssemblyID");
        assertEquals(cte.getColumnName().get(1).toString(),"ComponentID");
        assertEquals(cte.getColumnName().get(2).toString(),"PerAssemblyQty");
        assertEquals(cte.getColumnName().get(3).toString(),"EndDate");
        assertEquals(cte.getColumnName().get(4).toString(),"ComponentLevel");
    }


    // @formatter:off
    private Select selectJ = $Select()
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
                .$UnionSelect()
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
                .$UnionAllSelect()
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
                .$UnionAllSelect()
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
    public With exampleJ = WITH(
            "Generation",
            $(c("ID")),
            AS(selectJ)
    ).build();
    // @formatter:on

    @Test
    public void testExampleJ(){
        assertEquals(exampleJ.getCommonTableExpressionList().size(), 1);

        With.CommonTableExpression cte = exampleJ.getCommonTableExpressionList().get(0);
        assertEquals(cte.getExpressionName(), "Generation");
        assertEquals(cte.getColumnName().size(),1);
        assertEquals(cte.getColumnName().get(0).toString(),"ID");
    }


    // @formatter:off
    private Select selectK1 = $Select()
            .$()
                .$Select()
                    .$(c("itmIDComp"))
                    .$(c("itmID"))
                    .$From()
                        .$(e_variable("t1"))
                        .and()
                    .and()
                .$UnionAllSelect()
                    .$(c("itmIDComp"))
                    .$(c("itmID"))
                    .$From()
                        .$(e_variable("t2"))
                        .and()
                    .and()
                .and()
            .build();

    private Select selectK2 = $Select()
            .$()
                .$Select()
                    .$(c("t","itmID"),"itmIDComp")
                    .$(e_null(),"itmID")
                    .$(e("CAST(0 AS bigint)"),"N")
                    .$(e_number(1),"Lvl")
                    .$From()
                        .$($Querys()
                                .$Select()
                                    .$(e_number(1))
                                    .and()
                                .$UnionAllSelect()
                                    .$(e_number(2))
                                    .and()
                                .$UnionAllSelect()
                                    .$(e_number(3))
                                    .and()
                                .$UnionAllSelect()
                                    .$(e_number(4))
                                    .and()
                            .build(),"t")
                            .$As("t","itmID")
                        .and()
                    .and()
                .$UnionAllSelect()
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
    public With exampleK = WITH(
            "vw",
            AS(selectK1)
    ).$$(
            "r",
            AS(selectK2)
    ).build();
    // @formatter:on

    @Test
    public void testExampleK(){
        assertEquals(exampleK.getCommonTableExpressionList().size(), 2);

        With.CommonTableExpression cte = exampleK.getCommonTableExpressionList().get(0);
        assertEquals(cte.getExpressionName(), "vw");
        assertNull(cte.getColumnName());

        With.CommonTableExpression cte1 = exampleK.getCommonTableExpressionList().get(1);
        assertEquals(cte1.getExpressionName(), "r");
        assertNull(cte1.getColumnName());
    }


    /*
    Examples: SQL Data Warehouse and Parallel Data Warehouse
    See https://docs.microsoft.com/zh-cn/sql/t-sql/queries/with-common-table-expression-transact-sql#examples-includesssdwincludessssdw-mdmd-and-includesspdwincludessspdw-mdmd
     */

    // @formatter:off
    private Select selectL = $Select()
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
    public With exampleL = WITH(
            "Sales_CTE",
            $(
                    c("SalesPersonID"),
                    c("SalesOrderID"),
                    c("SalesYear")
            ),
            AS(selectL)
    ).build();
    // @formatter:on

    @SuppressWarnings("Duplicates")
    @Test
    public void testExampleL(){
        assertEquals(exampleL.getCommonTableExpressionList().size(), 1);

        With.CommonTableExpression cte = exampleL.getCommonTableExpressionList().get(0);
        assertEquals(cte.getExpressionName(), "Sales_CTE");
        assertEquals(cte.getColumnName().size(),3);

    }

    // @formatter:off
    private Select selectM = $Select()
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
    public With exampleM = WITH(
            "Sales_CTE",
            $(
                    c("SalesPersonID"),
                    c("NumberOfOrders")
            ),
            AS(selectM)
    ).build();
    // @formatter:on

    @Test
    public void testExampleM(){
        assertEquals(exampleM.getCommonTableExpressionList().size(), 1);

        With.CommonTableExpression cte = exampleM.getCommonTableExpressionList().get(0);
        assertEquals(cte.getExpressionName(), "Sales_CTE");
        assertEquals(cte.getColumnName().size(),2);

    }


    // @formatter:off
    private Select selectN = $Select()
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
    public With exampleN = WITH(
            "Sales_CTE",
            $(
                    c("SalesPersonID"),
                    c("SalesOrderID"),
                    c("SalesYear")
            ),
            AS(selectN)
    ).build();
    // @formatter:on

    @SuppressWarnings("Duplicates")
    @Test
    public void testExampleN(){
        assertEquals(exampleN.getCommonTableExpressionList().size(), 1);

        With.CommonTableExpression cte = exampleN.getCommonTableExpressionList().get(0);
        assertEquals(cte.getExpressionName(), "Sales_CTE");
        assertEquals(cte.getColumnName().size(),3);

    }


    // @formatter:off
    private Select selectO = $Select()
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
    public With exampleO = WITH(
            "Sales_CTE",
            $(
                    c("SalesPersonID"),
                    c("SalesOrderID"),
                    c("SalesYear")
            ),
            AS(selectO)
    ).build();
    // @formatter:on

    @SuppressWarnings("Duplicates")
    @Test
    public void testExampleO(){
        assertEquals(exampleO.getCommonTableExpressionList().size(), 1);

        With.CommonTableExpression cte = exampleO.getCommonTableExpressionList().get(0);
        assertEquals(cte.getExpressionName(), "Sales_CTE");
        assertEquals(cte.getColumnName().size(),3);

    }


    // @formatter:off
    private Select selectP1 = $Select()
            .$Select()
                .$(e("COUNT(datekey)"))
                .$(e_string("DimDate"))
                .$From()
                    .$(t("DimDate"))
                    .and()
                .and()
            .build();

    private Select selectP2 = $Select()
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
    public With exampleP = WITH(
            "CountDate",
            $(
                    c("TotalCount"),
                    c("TableName")
            ),
            AS(selectP1)
    ).$$(
            "CountCustomer",
            $(
                    c("TotalAvg"),
                    c("TableName")
            ),
            AS(selectP2)
    ).build();
    // @formatter:on

    @Test
    public void testExampleP(){
        assertEquals(exampleP.getCommonTableExpressionList().size(), 2);

        With.CommonTableExpression cte = exampleP.getCommonTableExpressionList().get(0);
        assertEquals(cte.getExpressionName(), "CountDate");
        assertEquals(cte.getColumnName().size(),2);

        With.CommonTableExpression cte1 = exampleP.getCommonTableExpressionList().get(1);
        assertEquals(cte1.getExpressionName(), "CountCustomer");
        assertEquals(cte1.getColumnName().size(),2);

    }
}