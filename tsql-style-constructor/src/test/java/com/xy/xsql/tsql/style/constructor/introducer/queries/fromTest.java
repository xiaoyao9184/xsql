package com.xy.xsql.tsql.style.constructor.introducer.queries;

import com.xy.xsql.tsql.builder.chain.queries.SelectBuilder;
import com.xy.xsql.tsql.model.queries.From;
import com.xy.xsql.tsql.model.queries.Select;
import com.xy.xsql.tsql.model.queries.hints.JoinHint;
import com.xy.xsql.tsql.model.queries.hints.TableHint;
import com.xy.xsql.tsql.model.queries.predicates.Comparison;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.datatypes.table.ColumnNameFactory.c;
import static com.xy.xsql.tsql.builder.chain.datatypes.table.TableNameFactory.t;
import static com.xy.xsql.tsql.builder.chain.elements.expressions.Expressions.*;
import static com.xy.xsql.tsql.builder.chain.queries.predicates.Predicates.*;
import static com.xy.xsql.tsql.style.constructor.introducer.queries.from.*;
import static com.xy.xsql.tsql.style.constructor.introducer.queries.hints.join_hint.*;
import static com.xy.xsql.tsql.style.constructor.introducer.queries.hints.table_hint.*;
import static com.xy.xsql.tsql.style.constructor.introducer.queries.select.select.AS;
import static org.junit.Assert.*;

/**
 * Created by xiaoyao9184 on 2018/5/11.
 */
public class fromTest {

    /*
    Examples
    See https://docs.microsoft.com/zh-cn/sql/t-sql/queries/from-transact-sql#examples
     */

    // @formatter:off
    /**
     * FROM Sales.SalesTerritory
     */
    public From exampleA = FROM(
            t("Sales","SalesTerritory")
    ).build();
    // @formatter:on

    @Test
    public void testExampleA(){
        assertEquals(exampleA.getTableSourceList().size(),1);
        assertEquals(exampleA.getTableSourceList().get(0).getClass(), From.BaseTable.class);
        From.BaseTable tableSource = (From.BaseTable) exampleA.getTableSourceList().get(0);
        assertEquals(tableSource.getTableName().toString(),"Sales.SalesTerritory");
    }


    // @formatter:off
    /**
     * FROM HumanResources.Employee WITH (TABLOCK, HOLDLOCK)
     */
    public From exampleB = FROM(
            t("HumanResources","Employee"),
            WITH(TABLOCK(),HOLDLOCK())
    ).build();
    // @formatter:on

    @Test
    public void testExampleB(){
        assertEquals(exampleB.getTableSourceList().size(),1);
        assertEquals(exampleB.getTableSourceList().get(0).getClass(), From.BaseTable.class);
        From.BaseTable tableSource = (From.BaseTable) exampleB.getTableSourceList().get(0);
        assertEquals(tableSource.getTableName().toString(),"HumanResources.Employee");

        assertEquals(tableSource.getTableHintList().size(),2);
        assertEquals(tableSource.getTableHintList().get(0).getType(), TableHint.Type.TABLOCK);
        assertEquals(tableSource.getTableHintList().get(1).getType(), TableHint.Type.HOLDLOCK);

    }


    // @formatter:off
    /**
     * FROM HumanResources.Employee AS e
     CROSS JOIN HumanResources.Department AS d
     */
    public From exampleC = FROM(
            t("HumanResources","Employee"),AS("e")
    ).CROSS(
            JOIN(t("HumanResources","Department"),AS("d"))
    ).build();
    // @formatter:on

    @Test
    public void testExampleC(){
        assertEquals(exampleC.getTableSourceList().size(),1);

        assertEquals(exampleC.getTableSourceList().get(0).getClass(), From.JoinedTable.class);
        From.JoinedTable tableSource = (From.JoinedTable) exampleC.getTableSourceList().get(0);

        assertEquals(tableSource.getTableSource().getClass(),From.BaseTable.class);
        From.BaseTable tableSource1 = (From.BaseTable) tableSource.getTableSource();
        assertEquals(tableSource1.getTableName().toString(),"HumanResources.Employee");
        assertTrue(tableSource1.isUseAs());
        assertEquals(tableSource1.getTableAlias().toString(),"e");

        assertEquals(tableSource.isUseCrossJoin(),true);

        assertEquals(tableSource.getTableSource2().getClass(),From.BaseTable.class);
        From.BaseTable tableSource2 = (From.BaseTable) tableSource.getTableSource2();
        assertEquals(tableSource2.getTableName().toString(),"HumanResources.Department");
        assertTrue(tableSource2.isUseAs());
        assertEquals(tableSource2.getTableAlias().toString(),"d");
    }


    // @formatter:off
    /**
     * FROM Production.Product AS p
     FULL OUTER JOIN Sales.SalesOrderDetail AS sod
     ON p.ProductID = sod.ProductID
     */
    public From exampleD = FROM(
            t("Production","Product"),AS("p")
    ).FULL_OUTER(
            JOIN(t("Sales","SalesOrderDetail"),AS("sod")),
            ON(
                    p_equal(
                            c("p","ProductID"),
                            c("sod","ProductID")
                    )
            )
    ).build();
    // @formatter:on

    @Test
    public void testExampleD(){
        assertEquals(exampleD.getTableSourceList().size(),1);

        assertEquals(exampleD.getTableSourceList().get(0).getClass(), From.JoinedTable.class);
        From.JoinedTable tableSource = (From.JoinedTable) exampleD.getTableSourceList().get(0);

        assertEquals(tableSource.getTableSource().getClass(),From.BaseTable.class);
        From.BaseTable tableSource1 = (From.BaseTable) tableSource.getTableSource();
        assertEquals(tableSource1.getTableName().toString(),"Production.Product");
        assertTrue(tableSource1.isUseAs());
        assertEquals(tableSource1.getTableAlias().toString(),"p");

        assertEquals(tableSource.getJoinType().getKeywords(), From.JoinTypeKeywords.FULL_OUTER_JOIN);

        assertEquals(tableSource.getTableSource2().getClass(),From.BaseTable.class);
        From.BaseTable tableSource2 = (From.BaseTable) tableSource.getTableSource2();
        assertEquals(tableSource2.getTableName().toString(),"Sales.SalesOrderDetail");
        assertTrue(tableSource2.isUseAs());
        assertEquals(tableSource2.getTableAlias().toString(),"sod");

        assertEquals(tableSource.getSearchCondition().getPredicate().getClass(),Comparison.class);

    }


    // @formatter:off
    /**
     * FROM Production.Product AS p
     LEFT OUTER JOIN Sales.SalesOrderDetail AS sod
     ON p.ProductID = sod.ProductID
     */
    public From exampleE = FROM(
            t("Production","Product"),AS("p")
    ).LEFT_OUTER(
            JOIN(
                    t("Sales","SalesOrderDetail"),AS("sod")
            ),
            ON(
                    p_equal(
                            c("p","ProductID"),
                            c("sod","ProductID")
                    )
            )
    ).build();
    // @formatter:on

    @Test
    public void testExampleE(){
        assertEquals(exampleE.getTableSourceList().size(),1);

        assertEquals(exampleE.getTableSourceList().get(0).getClass(), From.JoinedTable.class);
        From.JoinedTable tableSource = (From.JoinedTable) exampleE.getTableSourceList().get(0);

        assertEquals(tableSource.getTableSource().getClass(),From.BaseTable.class);
        From.BaseTable tableSource1 = (From.BaseTable) tableSource.getTableSource();
        assertEquals(tableSource1.getTableName().toString(),"Production.Product");
        assertEquals(tableSource1.getTableAlias().toString(),"p");

        assertEquals(tableSource.getJoinType().getKeywords(), From.JoinTypeKeywords.LEFT_OUTER_JOIN);

        assertEquals(tableSource.getTableSource2().getClass(),From.BaseTable.class);
        From.BaseTable tableSource2 = (From.BaseTable) tableSource.getTableSource2();
        assertEquals(tableSource2.getTableName().toString(),"Sales.SalesOrderDetail");
        assertEquals(tableSource2.getTableAlias().toString(),"sod");

        assertEquals(tableSource.getSearchCondition().getPredicate().getClass(),Comparison.class);

    }


    // @formatter:off
    /**
     * FROM Production.Product AS p
     INNER JOIN Sales.SalesOrderDetail AS sod
     ON p.ProductID = sod.ProductID
     */
    public From exampleF = FROM(
            t("Production","Product"),AS("p")
    ).INNER(JOIN(
            t("Sales","SalesOrderDetail"),AS("sod"))
            ,ON(
                 p_equal(
                         c("p","ProductID"),
                         c("sod","ProductID")
                 )
            )
    ).build();
    // @formatter:on

    @Test
    public void testExampleF(){
        // @formatter:on

        assertEquals(exampleF.getTableSourceList().size(),1);

        assertEquals(exampleF.getTableSourceList().get(0).getClass(), From.JoinedTable.class);
        From.JoinedTable tableSource = (From.JoinedTable) exampleF.getTableSourceList().get(0);

        assertEquals(tableSource.getTableSource().getClass(),From.BaseTable.class);
        From.BaseTable tableSource1 = (From.BaseTable) tableSource.getTableSource();
        assertEquals(tableSource1.getTableName().toString(),"Production.Product");
        assertTrue(tableSource1.isUseAs());
        assertEquals(tableSource1.getTableAlias().toString(),"p");

        assertEquals(tableSource.getJoinType().getKeywords(), From.JoinTypeKeywords.INNER_JOIN);

        assertEquals(tableSource.getTableSource2().getClass(),From.BaseTable.class);
        From.BaseTable tableSource2 = (From.BaseTable) tableSource.getTableSource2();
        assertEquals(tableSource2.getTableName().toString(),"Sales.SalesOrderDetail");
        assertTrue(tableSource2.isUseAs());
        assertEquals(tableSource2.getTableAlias().toString(),"sod");

        assertEquals(tableSource.getSearchCondition().getPredicate().getClass(),Comparison.class);

    }


    // @formatter:off
    /**
     * FROM Sales.SalesTerritory AS st
     RIGHT OUTER JOIN Sales.SalesPerson AS sp
     ON st.TerritoryID = sp.TerritoryID
     */
    public From exampleG = FROM(
            t("Sales","SalesTerritory"),AS("st")
    ).RIGHT_OUTER(
        JOIN(
                t("Sales","SalesPerson"),AS("sp")
        ),
        ON(p_equal(
                c("st","TerritoryID"),
                c("sp","TerritoryID")
        ))
    ).build();
    // @formatter:on

    @Test
    public void testExampleG(){
        assertEquals(exampleG.getTableSourceList().size(),1);

        assertEquals(exampleG.getTableSourceList().get(0).getClass(), From.JoinedTable.class);
        From.JoinedTable tableSource = (From.JoinedTable) exampleG.getTableSourceList().get(0);

        assertEquals(tableSource.getTableSource().getClass(),From.BaseTable.class);
        From.BaseTable tableSource1 = (From.BaseTable) tableSource.getTableSource();
        assertEquals(tableSource1.getTableName().toString(),"Sales.SalesTerritory");
        assertTrue(tableSource1.isUseAs());
        assertEquals(tableSource1.getTableAlias().toString(),"st");

        assertEquals(tableSource.getJoinType().getKeywords(), From.JoinTypeKeywords.RIGHT_OUTER_JOIN);

        assertEquals(tableSource.getTableSource2().getClass(),From.BaseTable.class);
        From.BaseTable tableSource2 = (From.BaseTable) tableSource.getTableSource2();
        assertEquals(tableSource2.getTableName().toString(),"Sales.SalesPerson");
        assertTrue(tableSource2.isUseAs());
        assertEquals(tableSource2.getTableAlias().toString(),"sp");

        assertEquals(tableSource.getSearchCondition().getPredicate().getClass(),Comparison.class);

    }


    // @formatter:off
    /**
     * FROM Production.Product AS p
     INNER MERGE JOIN Purchasing.ProductVendor AS pv
     ON p.ProductID = pv.ProductID
     INNER HASH JOIN Purchasing.Vendor AS v
     ON pv.BusinessEntityID = v.BusinessEntityID
     */
    public From exampleH = FROM(
            t("Production","Product"),AS("p")
    ).INNER(MERGE(),JOIN(
            t("Purchasing","ProductVendor"),AS("pv")
    ),ON(
            p_equal(
                    c("p","ProductID"),
                    c("pv","ProductID")
            )
    )).INNER(HASH(),JOIN(
            t("Purchasing","Vendor"),AS("v")
    ),ON(
           p_equal(
                    c("pV","BusinessEntityID"),
                    c("v","BusinessEntityID")
            )
    )).build();
    // @formatter:on

    @Test
    public void testExampleH(){
        assertEquals(exampleH.getTableSourceList().size(),1);

        assertEquals(exampleH.getTableSourceList().get(0).getClass(), From.JoinedTable.class);
        From.JoinedTable tableSource = (From.JoinedTable) exampleH.getTableSourceList().get(0);

        assertEquals(tableSource.getTableSource().getClass(),From.JoinedTable.class);
        From.JoinedTable tableSource01 = (From.JoinedTable) tableSource.getTableSource();

        assertEquals(tableSource01.getTableSource().getClass(),From.BaseTable.class);
        From.BaseTable tableSource11 = (From.BaseTable) tableSource01.getTableSource();
        assertEquals(tableSource11.getTableName().toString(),"Production.Product");
        assertTrue(tableSource11.isUseAs());
        assertEquals(tableSource11.getTableAlias().toString(),"p");

        assertEquals(tableSource01.getJoinType().getKeywords(), From.JoinTypeKeywords.INNER_JOIN);
        assertEquals(tableSource01.getJoinType().getJoinHint(), JoinHint.MERGE);

        assertEquals(tableSource01.getTableSource2().getClass(),From.BaseTable.class);
        From.BaseTable tableSource12 = (From.BaseTable) tableSource01.getTableSource2();
        assertEquals(tableSource12.getTableName().toString(),"Purchasing.ProductVendor");
        assertTrue(tableSource12.isUseAs());
        assertEquals(tableSource12.getTableAlias().toString(),"pv");


        assertEquals(tableSource.getJoinType().getKeywords(), From.JoinTypeKeywords.INNER_JOIN);
        assertEquals(tableSource.getJoinType().getJoinHint(), JoinHint.HASH);

        assertEquals(tableSource.getTableSource2().getClass(),From.BaseTable.class);
        From.BaseTable tableSource02 = (From.BaseTable) tableSource.getTableSource2();
        assertEquals(tableSource02.getTableName().toString(),"Purchasing.Vendor");
        assertTrue(tableSource02.isUseAs());
        assertEquals(tableSource02.getTableAlias().toString(),"v");

        assertEquals(tableSource.getSearchCondition().getPredicate().getClass(),Comparison.class);

    }


    // @formatter:off
    private Select.QuerySpecification querySpecification = new SelectBuilder.QuerySpecificationBuilder<Void>()
            .$(c("bea", "BusinessEntityID"))
            .$(c("a", "City"))
            .$From()
                .$()
                    .$(t("Person","Address"))
                    .$As("a")
                    .$InnerJoin()
                    .$(t("Person","BusinessEntityAddress"))
                    .$As("bea")
                    .$On()
                        .$(
                                p_equal(c("a","AddressID"),
                                        c("bea","AddressID"))
                        )
                        .and()
                    .and()
                .and()
                .build();

    /**
     * FROM Person.Person AS p
     INNER JOIN HumanResources.Employee e ON p.BusinessEntityID = e.BusinessEntityID
     INNER JOIN
     (SELECT bea.BusinessEntityID, a.City
     FROM Person.Address AS a
     INNER JOIN Person.BusinessEntityAddress AS bea
     ON a.AddressID = bea.AddressID) AS d
     ON p.BusinessEntityID = d.BusinessEntityID
     */
    public From exampleI = FROM(
            t("Person","Person"),AS("p")
    ).INNER(JOIN(
            t("HumanResources","Employee"),AS("e")
    ),ON(
            p_equal(
                    c("p","BusinessEntityID"),
                    c("e","BusinessEntityID")
            )
    )).INNER(JOIN(
            querySpecification,AS("d")
    ),ON(
            p_equal(
                    c("p","BusinessEntityID"),
                    c("d","BusinessEntityID")
            )
    )).build();
    // @formatter:on

    @Test
    public void testExampleI(){
        assertEquals(exampleI.getTableSourceList().size(),1);

        assertEquals(exampleI.getTableSourceList().get(0).getClass(), From.JoinedTable.class);
        From.JoinedTable tableSource = (From.JoinedTable) exampleI.getTableSourceList().get(0);

        assertEquals(tableSource.getTableSource().getClass(),From.JoinedTable.class);
        From.JoinedTable tableSource0 = (From.JoinedTable) tableSource.getTableSource();

        assertEquals(tableSource0.getTableSource().getClass(),From.BaseTable.class);
        From.BaseTable tableSource1 = (From.BaseTable) tableSource0.getTableSource();
        assertNotNull(tableSource1.getTableName().toString(),"Person.Person");
        assertTrue(tableSource1.isUseAs());
        assertEquals(tableSource1.getTableAlias().toString(),"p");

        assertEquals(tableSource0.getJoinType().getKeywords(), From.JoinTypeKeywords.INNER_JOIN);

        assertEquals(tableSource0.getTableSource2().getClass(),From.BaseTable.class);
        From.BaseTable tableSource2 = (From.BaseTable) tableSource0.getTableSource2();
        assertNotNull(tableSource2.getTableName().toString(),"HumanResources.Employee");
        assertEquals(tableSource2.getTableAlias().toString(),"e");

        assertEquals(tableSource.getJoinType().getKeywords(), From.JoinTypeKeywords.INNER_JOIN);

        assertEquals(tableSource.getTableSource2().getClass(),From.DerivedTable.class);
        From.DerivedTable tableSource3 = (From.DerivedTable) tableSource.getTableSource2();
        assertNotNull(tableSource3.getSubQuery());
        assertTrue(tableSource3.isUseAs());
        assertEquals(tableSource3.getTableAlias().toString(),"d");

        assertEquals(tableSource.getSearchCondition().getPredicate().getClass(),Comparison.class);

    }


    // @formatter:off
    /**
     * FROM Sales.Customer TABLESAMPLE SYSTEM (10 WILDCARD_PERCENT)
     */
    public From exampleJ = FROM(
            t("Sales","Customer"),
            TABLESAMPLE(SYSTEM(),10,PERCENT())
    ).build();
    // @formatter:on

    @Test
    public void testExampleJ(){
        assertEquals(exampleJ.getTableSourceList().size(),1);

        assertEquals(exampleJ.getTableSourceList().get(0).getClass(), From.BaseTable.class);
        From.BaseTable tableSource = (From.BaseTable) exampleJ.getTableSourceList().get(0);
        assertEquals(tableSource.getTableName().toString(),"Sales.Customer");

        assertTrue(tableSource.getTableSample().isUseSystem());
        assertEquals(tableSource.getTableSample().getSampleNumber().getNumber(),10);
        assertTrue(tableSource.getTableSample().isUsePercent());
    }


    // @formatter:off
    /**
     * FROM Departments d CROSS APPLY dbo.GetReports(d.DeptMgrID)
     */
    public From exampleK = FROM(
            t("Departments"),"d"
    ).CROSS(APPLY(
            //TODO rowset_function
            t("dbo.GetReports(d.DeptMgrID)")
    )).build();
    // @formatter:on

    @Test
    public void testExampleK(){
        assertEquals(exampleK.getTableSourceList().size(),1);

        assertEquals(exampleK.getTableSourceList().get(0).getClass(), From.JoinedTable.class);
        From.JoinedTable tableSource = (From.JoinedTable) exampleK.getTableSourceList().get(0);

        assertEquals(tableSource.getTableSource().getClass(),From.BaseTable.class);
        From.BaseTable tableSource1 = (From.BaseTable) tableSource.getTableSource();
        assertEquals(tableSource1.getTableName().toString(),"Departments");
        assertEquals(tableSource1.getTableAlias().toString(),"d");

        assertTrue(tableSource.isUseCrossApply());

//        assertEquals(tableSource.getTableSource2().getClass(),From.Function.class);
//        From.Function tableSource2 = (From.Function) tableSource.getTableSource2();
//        assertEquals(tableSource2.getName().toString(),"dbo.GetReports");
//        assertEquals(tableSource2.getArgs().toString(),"d.DeptMgrID");

    }


    // @formatter:off
    /**
     * FROM sys.dm_exec_cached_plans AS cp
     CROSS APPLY sys.dm_exec_query_plan(cp.plan_handle)
     */
    public From exampleL = FROM(
            t("sys","dm_exec_cached_plans"),AS("cp")
    ).CROSS(APPLY(
            //TODO rowset_function
            t("sys","dm_exec_query_plan(cp.plan_handle)")
    )).build();
    // @formatter:on

    @Test
    public void testExampleL(){
        assertEquals(exampleL.getTableSourceList().size(),1);

        assertEquals(exampleL.getTableSourceList().get(0).getClass(), From.JoinedTable.class);
        From.JoinedTable tableSource = (From.JoinedTable) exampleL.getTableSourceList().get(0);

        assertEquals(tableSource.getTableSource().getClass(),From.BaseTable.class);
        From.BaseTable tableSource1 = (From.BaseTable) tableSource.getTableSource();
        assertEquals(tableSource1.getTableName().toString(),"sys.dm_exec_cached_plans");
        assertEquals(tableSource1.getTableAlias().toString(),"cp");

        assertTrue(tableSource.isUseCrossApply());

//        assertEquals(tableSource.getTableSource2().getClass(),From.Function.class);
//        From.Function tableSource2 = (From.Function) tableSource.getTableSource2();
//        assertEquals(tableSource2.getName().toString(),"dbo.GetReports");
//        assertEquals(tableSource2.getArgs().toString(),"d.DeptMgrID");

    }


    // @formatter:off
    /**
     * FROM DEPARTMENT
     FOR SYSTEM_TIME AS OF '2014-01-01'
     */
    public From exampleM1 = FROM(
            t("DEPARTMENT"),
            FOR_SYSTEM_TIME(AS_OF("2014-01-01"))
    ).build();
    // @formatter:on

    @Test
    public void testExampleM1(){
        assertEquals(exampleM1.getTableSourceList().size(),1);

        assertEquals(exampleM1.getTableSourceList().get(0).getClass(),From.BaseWithTimeTable.class);
        From.BaseWithTimeTable tableSource = (From.BaseWithTimeTable) exampleM1.getTableSourceList().get(0);
        assertEquals(tableSource.getTableName().toString(),"DEPARTMENT");

        assertEquals(tableSource.getSystemTime().getDateTime().getDateTimeLiteral().getString(),"2014-01-01");
    }


    // @formatter:off
    /**
     * FROM DEPARTMENT
     FOR SYSTEM_TIME FROM '2013-01-01' TO '2014-01-01'
     */
    public From exampleM2 = FROM(
            t("DEPARTMENT"),
            FOR_SYSTEM_TIME(FROM_("2013-01-01"),TO("2014-01-01"))
    ).build();
    // @formatter:on

    @Test
    public void testExampleM2(){
        assertEquals(exampleM2.getTableSourceList().size(),1);

        assertEquals(exampleM2.getTableSourceList().get(0).getClass(),From.BaseWithTimeTable.class);
        From.BaseWithTimeTable tableSource = (From.BaseWithTimeTable) exampleM2.getTableSourceList().get(0);
        assertEquals(tableSource.getTableName().toString(),"DEPARTMENT");

        assertTrue(tableSource.getSystemTime().isUseFrom());
        assertEquals(tableSource.getSystemTime().getStartDateTime().getDateTimeLiteral().getString(),"2013-01-01");
        assertEquals(tableSource.getSystemTime().getEndDateTime().getDateTimeLiteral().getString(),"2014-01-01");
    }


    // @formatter:off
    /**
     * FROM DEPARTMENT
     FOR SYSTEM_TIME BETWEEN '2013-01-01' AND '2014-01-01'
     */
    public From exampleM3 = FROM(
            t("DEPARTMENT"),
            FOR_SYSTEM_TIME(BETWEEN("2013-01-01"),AND("2014-01-01"))
    ).build();
    // @formatter:on

    @Test
    public void testExampleM3(){
        assertEquals(exampleM3.getTableSourceList().size(),1);

        assertEquals(exampleM3.getTableSourceList().get(0).getClass(),From.BaseWithTimeTable.class);
        From.BaseWithTimeTable tableSource = (From.BaseWithTimeTable) exampleM3.getTableSourceList().get(0);
        assertEquals(tableSource.getTableName().toString(),"DEPARTMENT");

        assertTrue(tableSource.getSystemTime().isUseBetween());
        assertEquals(tableSource.getSystemTime().getStartDateTime().getDateTimeLiteral().getString(),"2013-01-01");
        assertEquals(tableSource.getSystemTime().getEndDateTime().getDateTimeLiteral().getString(),"2014-01-01");
    }


    // @formatter:off
    /**
     * FROM DEPARTMENT
     FOR SYSTEM_TIME CONTAINED IN ( '2013-01-01', '2014-01-01' )
     */
    public From exampleM4 = FROM(
            t("DEPARTMENT"),
            FOR_SYSTEM_TIME(CONTAINED_IN("2013-01-01","2014-01-01"))
    ).build();
    // @formatter:on

    @Test
    public void testExampleM4(){
        assertEquals(exampleM4.getTableSourceList().size(),1);

        assertEquals(exampleM4.getTableSourceList().get(0).getClass(),From.BaseWithTimeTable.class);
        From.BaseWithTimeTable tableSource = (From.BaseWithTimeTable) exampleM4.getTableSourceList().get(0);
        assertEquals(tableSource.getTableName().toString(),"DEPARTMENT");

        assertTrue(tableSource.getSystemTime().isUseContained());
        assertEquals(tableSource.getSystemTime().getStartDateTime().getDateTimeLiteral().getString(),"2013-01-01");
        assertEquals(tableSource.getSystemTime().getEndDateTime().getDateTimeLiteral().getString(),"2014-01-01");
    }


    // @formatter:off
    /**
     * FROM DEPARTMENT
     FOR SYSTEM_TIME FROM @AsOfFrom TO @AsOfTo
     */
    public From exampleM5 = FROM(
            t("DEPARTMENT"),
            FOR_SYSTEM_TIME(FROM_(e_variable("AsOfFrom")),TO(e_variable("AsOfTo")))
    ).build();
    // @formatter:on

    @Test
    public void testExampleM5(){
        assertEquals(exampleM5.getTableSourceList().size(),1);

        assertEquals(exampleM5.getTableSourceList().get(0).getClass(),From.BaseWithTimeTable.class);
        From.BaseWithTimeTable tableSource = (From.BaseWithTimeTable) exampleM5.getTableSourceList().get(0);
        assertEquals(tableSource.getTableName().toString(),"DEPARTMENT");

        assertTrue(tableSource.getSystemTime().isUseFrom());
        assertEquals(tableSource.getSystemTime().getStartDateTime().getDateTimeVariable().toString(),"@AsOfFrom");
        assertEquals(tableSource.getSystemTime().getEndDateTime().getDateTimeVariable().toString(),"@AsOfTo");

    }


    /*
    Examples: Azure SQL Data Warehouse and Parallel Data Warehouse
    See https://docs.microsoft.com/en-us/sql/t-sql/queries/from-transact-sql#examples-includesssdwfullincludessssdwfull-mdmd-and-includesspdwincludessspdw-mdmd
     */

    // @formatter:off
    /**
     * FROM FactInternetSales AS fis
    INNER JOIN DimProduct AS dp
    ON dp.ProductKey = fis.ProductKey;
     */
    public From exampleN = FROM(
            t("FactInternetSales"),
            AS("fis")
    ).INNER(JOIN(
            t("DimProduct"),
            AS("dp")),
            ON(p_equal(
                    c("dp","ProductKey"),
                    c("fis","ProductKey")))
    ).build();
    // @formatter:on

    @Test
    public void testExampleN(){
        assertEquals(exampleN.getTableSourceList().get(0).getClass(),From.JoinedTable.class);
        From.JoinedTable tableSource = (From.JoinedTable) exampleN.getTableSourceList().get(0);

        assertEquals(tableSource.getTableSource().getClass(),From.BaseTable.class);
        From.BaseTable tableSource1 = (From.BaseTable) tableSource.getTableSource();

        assertEquals(tableSource1.getTableName().getFullName(), "FactInternetSales");
        assertTrue(tableSource1.isUseAs());
        assertEquals(tableSource1.getTableAlias().toString(),"fis");

        assertEquals(tableSource.getJoinType().getKeywords(),From.JoinTypeKeywords.INNER_JOIN);

        assertEquals(tableSource.getTableSource2().getClass(),From.BaseTable.class);
        From.BaseTable tableSource2 = (From.BaseTable) tableSource.getTableSource2();

        assertEquals(tableSource2.getTableName().getFullName(), "DimProduct");
        assertTrue(tableSource2.isUseAs());
        assertEquals(tableSource2.getTableAlias().toString(),"dp");

        assertEquals(tableSource.getSearchCondition().getPredicate().getClass(), Comparison.class);
    }

    // @formatter:off
    /**
     * FROM FactInternetSales AS fis
     INNER JOIN DimProduct AS dp
     ON dp.ProductKey = fis.ProductKey
     */
    public From exampleO = FROM(
            t("FactInternetSales"),AS("fis")
    ).INNER(JOIN(t("DimProduct"),AS("dp")),
            ON(
                    p_equal(
                            c("dp","ProductKey"),
                            c("fis","ProductKey")
                    )
            )
    ).build();
    // @formatter:on

    @Test
    public void testExampleO(){
        assertEquals(exampleO.getTableSourceList().size(),1);

        assertEquals(exampleO.getTableSourceList().get(0).getClass(),From.JoinedTable.class);
        From.JoinedTable tableSource = (From.JoinedTable) exampleO.getTableSourceList().get(0);

        assertEquals(tableSource.getTableSource().getClass(),From.BaseTable.class);
        From.BaseTable tableSource1 = (From.BaseTable) tableSource.getTableSource();

        assertEquals(tableSource1.getTableName().toString(),"FactInternetSales");
        assertEquals(tableSource1.getTableAlias().toString(),"fis");

        assertEquals(tableSource.getTableSource2().getClass(),From.BaseTable.class);
        From.BaseTable tableSource2 = (From.BaseTable) tableSource.getTableSource2();

        assertEquals(tableSource2.getTableName().toString(),"DimProduct");
        assertEquals(tableSource2.getTableAlias().toString(),"dp");

        assertEquals(tableSource.getSearchCondition().getPredicate().getClass(),Comparison.class);

    }


    // @formatter:off
    /**
     * FROM FactInternetSales AS fis
     LEFT OUTER JOIN DimProduct AS dp
     ON dp.ProductKey = fis.ProductKey
     */
    public From exampleP = FROM(
            t("FactInternetSales"),AS("fis")
    ).LEFT_OUTER(JOIN(
            t("DimProduct"),AS("dp")
    ),ON(
            p_equal(
                    c("dp","ProductKey"),
                    c("fis","ProductKey")
            )
    )).build();
    // @formatter:on

    @Test
    public void testExampleP(){
        assertEquals(exampleP.getTableSourceList().size(),1);

        assertEquals(exampleP.getTableSourceList().get(0).getClass(),From.JoinedTable.class);
        From.JoinedTable tableSource = (From.JoinedTable) exampleP.getTableSourceList().get(0);

        assertEquals(tableSource.getTableSource().getClass(),From.BaseTable.class);
        From.BaseTable tableSource1 = (From.BaseTable) tableSource.getTableSource();

        assertEquals(tableSource1.getTableName().toString(),"FactInternetSales");
        assertEquals(tableSource1.getTableAlias().toString(),"fis");

        assertEquals(tableSource.getTableSource2().getClass(),From.BaseTable.class);
        From.BaseTable tableSource2 = (From.BaseTable) tableSource.getTableSource2();

        assertEquals(tableSource2.getTableName().toString(),"DimProduct");
        assertEquals(tableSource2.getTableAlias().toString(),"dp");

        assertEquals(tableSource.getSearchCondition().getPredicate().getClass(),Comparison.class);

    }


    //TODO NOPQRSTUV



}