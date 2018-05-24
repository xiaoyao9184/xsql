package com.xy.xsql.tsql.builder.chain.queries;

import com.xy.xsql.tsql.builder.chain.MockParent;
import com.xy.xsql.tsql.builder.chain.MockParentBuilder;
import com.xy.xsql.tsql.model.queries.From;
import com.xy.xsql.tsql.model.queries.hints.JoinHint;
import com.xy.xsql.tsql.model.queries.hints.TableHint;
import com.xy.xsql.tsql.model.queries.predicates.Comparison;
import com.xy.xsql.tsql.model.queries.Select;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.datatypes.table.ColumnNameFactory.c;
import static com.xy.xsql.tsql.builder.chain.datatypes.table.TableNameFactory.t;
import static com.xy.xsql.tsql.builder.chain.elements.expressions.Expressions.e_string;
import static com.xy.xsql.tsql.builder.chain.elements.expressions.Expressions.e_variable;
import static com.xy.xsql.tsql.builder.chain.queries.Queries.$SubQuery;
import static com.xy.xsql.tsql.builder.chain.queries.hints.TableHintBuilder.$Holdlock;
import static com.xy.xsql.tsql.builder.chain.queries.hints.TableHintBuilder.$Tablock;
import static com.xy.xsql.tsql.builder.chain.queries.predicates.Predicates.p_equal;
import static org.junit.Assert.*;

/**
 * Created by xiaoyao9184 on 2017/3/11.
 */
public class FromBuilderTest {

    /*
    Examples
    See https://docs.microsoft.com/zh-cn/sql/t-sql/queries/from-transact-sql#examples
     */

    // @formatter:off
    /**
     * FROM Sales.SalesTerritory
     */
    public From exampleA = new MockParentBuilder<FromBuilder<MockParent<From>>,From>
            (FromBuilder.class,From.class)
            .$child()
                .$(t("Sales","SalesTerritory"))
                .and()
            .get();
    // @formatter:on

    @Test
    public void testExampleA(){
        // @formatter:off
        From option = new FromBuilder<From>()
                .withItem()._Base()
                    .withTableName(t("Sales","SalesTerritory"))
                    .and()
                .build();
        // @formatter:on

        assertEquals(option.getTableSourceList().size(),1);
        assertEquals(option.getTableSourceList().get(0).getClass(), From.BaseTable.class);
        From.BaseTable tableSource = (From.BaseTable) option.getTableSourceList().get(0);
        assertEquals(tableSource.getTableName().toString(),"Sales.SalesTerritory");
    }


    // @formatter:off
    /**
     * FROM HumanResources.Employee WITH (TABLOCK, HOLDLOCK)
     */
    public From exampleB = new MockParentBuilder<FromBuilder<MockParent<From>>,From>
            (FromBuilder.class,From.class)
            .$child()
                .$(t("HumanResources","Employee"),null)
                    .$With($Tablock(),$Holdlock())
                    .and()
                .and()
            .get();
    // @formatter:on

    @Test
    public void testExampleB(){
        // @formatter:off
        From option = new FromBuilder<From>()
                .withItem()._Base()
                    .withTableName(t("HumanResources","Employee"))
                    .withTableHint($Tablock(),$Holdlock())
                    .and()
                .build();
        // @formatter:on

        assertEquals(option.getTableSourceList().size(),1);
        assertEquals(option.getTableSourceList().get(0).getClass(), From.BaseTable.class);
        From.BaseTable tableSource = (From.BaseTable) option.getTableSourceList().get(0);
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
    public From exampleC = new MockParentBuilder<FromBuilder<MockParent<From>>,From>
            (FromBuilder.class,From.class)
            .$child()
                .$()
                    .$(t("HumanResources","Employee"))
                        .$As("e")
                    .$Cross_Join()
                    .$(t("HumanResources","Department"))
                        .$As("d")
                    .and()
                .and()
            .get();
    // @formatter:on

    @Test
    public void testExampleC(){
        // @formatter:off
        From from = new FromBuilder<From>()
                .withItem()._Joined()
                    .withCrossJoin()
                    .withTableSource()._Base()
                        .withTableName(t("HumanResources","Employee"))
                        .withAs()
                        .withTableAlias("e")
                        .and()
                    .withTableSource2()._Base()
                        .withTableName(t("HumanResources","Department"))
                        .withAs()
                        .withTableAlias("d")
                        .and()
                    .and()
                .build();
        // @formatter:on

        assertEquals(from.getTableSourceList().size(),1);

        assertEquals(from.getTableSourceList().get(0).getClass(), From.JoinedTable.class);
        From.JoinedTable tableSource = (From.JoinedTable) from.getTableSourceList().get(0);

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
    public From exampleD = new MockParentBuilder<FromBuilder<MockParent<From>>,From>
                (FromBuilder.class,From.class)
                .$child()
                    .$()
                        .$(t("Production","Product"))
                            .$As("p")
                        .$FullOuterJoin()
                        .$(t("Sales","SalesOrderDetail"))
                            .$As("sod")
                        .$On()
                            .$(p_equal(
                                    c("p","ProductID"),
                                    c("sod","ProductID")
                            ))
                            .and()
                        .and()
                    .and()
                .get();
    // @formatter:on

    @Test
    public void testExampleD(){
        // @formatter:off
        From from = new FromBuilder<From>()
                .withItem()._Joined()
                    .withTableSource()._Base()
                        .withTableName(t("Production","Product"))
                        .withAs()
                        .withTableAlias("p")
                        .and()
                    .withJoinType(From.JoinTypeKeywords.FULL_OUTER_JOIN)
                    .withTableSource2()._Base()
                        .withTableName(t("Sales","SalesOrderDetail"))
                        .withAs()
                        .withTableAlias("sod")
                        .and()
                    .withSearchCondition()
                        .withPredicate()._Comparison()
                            .withExpression(c("p","ProductID"))
                            .withOperator(com.xy.xsql.tsql.model.elements.operators.Comparison.EQUAL)
                            .withExpression(c("sod","ProductID"))
                            .and()
                        .and()
                    .and()
                .build();
        // @formatter:on

        assertEquals(from.getTableSourceList().size(),1);

        assertEquals(from.getTableSourceList().get(0).getClass(), From.JoinedTable.class);
        From.JoinedTable tableSource = (From.JoinedTable) from.getTableSourceList().get(0);

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
    public From exampleE = new MockParentBuilder<FromBuilder<MockParent<From>>,From>
                (FromBuilder.class,From.class)
                .$child()
                    .$()
                        .$(t("Production","Product"))
                        .$As("p")
                        .$LeftOuterJoin()
                        .$(t("Sales","SalesOrderDetail"))
                        .$As("sod")
                        .$On()
                            .$(p_equal(
                                    c("p","ProductID"),
                                    c("sod","ProductID")
                            ))
                            .and()
                        .and()
                    .and()
                .get();
    // @formatter:on

    @Test
    public void testExampleE(){
        // @formatter:off
        From from = new FromBuilder<From>()
                .withItem()._Joined()
                    .withTableSource()._Base()
                        .withTableName(t("Production","Product"))
                        .withTableAlias("p")
                        .and()
                    .withJoinType(From.JoinTypeKeywords.LEFT_OUTER_JOIN)
                    .withTableSource2()._Base()
                        .withTableName(t("Sales","SalesOrderDetail"))
                        .withTableAlias("sod")
                        .and()
                    .withSearchCondition()
                        .withPredicate()._Comparison()
                            .withExpression(c("p","ProductID"))
                            .withOperator(com.xy.xsql.tsql.model.elements.operators.Comparison.EQUAL)
                            .withExpression(c("sod","ProductID"))
                            .and()
                        .and()
                    .and()
                .build();

        // @formatter:on

        assertEquals(from.getTableSourceList().size(),1);

        assertEquals(from.getTableSourceList().get(0).getClass(), From.JoinedTable.class);
        From.JoinedTable tableSource = (From.JoinedTable) from.getTableSourceList().get(0);

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
    public From exampleF = new MockParentBuilder<FromBuilder<MockParent<From>>,From>
                (FromBuilder.class,From.class)
                .$child()
                    .$()
                        .$(t("Production","Product"))
                        .$As("p")
                        .$InnerJoin()
                        .$(t("Sales","SalesOrderDetail"))
                        .$As("sod")
                        .$On()
                            .$(p_equal(
                                    c("p","ProductID"),
                                    c("sod","ProductID")
                            ))
                            .and()
                        .and()
                    .and()
                .get();
    // @formatter:on

    @Test
    public void testExampleF(){
        // @formatter:off
        From from = new FromBuilder<From>()
                .withItem()._Joined()
                    .withTableSource()._Base()
                        .withTableName(t("Production","Product"))
                        .withAs()
                        .withTableAlias("p")
                        .and()
                    .withJoinType(From.JoinTypeKeywords.INNER_JOIN)
                    .withTableSource2()._Base()
                        .withTableName(t("Sales","SalesOrderDetail"))
                        .withAs()
                        .withTableAlias("sod")
                        .and()
                    .withSearchCondition()
                        .withPredicate()._Comparison()
                            .withExpression(c("p","ProductID"))
                            .withOperator(com.xy.xsql.tsql.model.elements.operators.Comparison.EQUAL)
                            .withExpression(c("sod","ProductID"))
                            .and()
                        .and()
                    .and()
                .build();

        // @formatter:on

        assertEquals(from.getTableSourceList().size(),1);

        assertEquals(from.getTableSourceList().get(0).getClass(), From.JoinedTable.class);
        From.JoinedTable tableSource = (From.JoinedTable) from.getTableSourceList().get(0);

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
    public From exampleG = new MockParentBuilder<FromBuilder<MockParent<From>>,From>
                (FromBuilder.class,From.class)
                .$child()
                    .$()
                        .$(t("Sales","SalesTerritory"))
                        .$As("st")
                        .$RightOuterJoin()
                        .$(t("Sales","SalesPerson"))
                        .$As("sp")
                        .$On()
                            .$(p_equal(
                                    c("st","TerritoryID"),
                                    c("sp","TerritoryID")
                            ))
                            .and()
                        .and()
                    .and()
                .get();
    // @formatter:on

    @Test
    public void testExampleG(){
        // @formatter:off
        From from = new FromBuilder<From>()
                .withItem()._Joined()
                    .withTableSource()._Base()
                        .withTableName(t("Sales","SalesTerritory"))
                        .withAs()
                        .withTableAlias("st")
                        .and()
                    .withJoinType(From.JoinTypeKeywords.RIGHT_OUTER_JOIN)
                    .withTableSource2()._Base()
                        .withTableName(t("Sales","SalesPerson"))
                        .withAs()
                        .withTableAlias("sp")
                        .and()
                    .withSearchCondition()
                        .withPredicate()._Comparison()
                            .withExpression(c("st","TerritoryID"))
                            .withOperator(com.xy.xsql.tsql.model.elements.operators.Comparison.EQUAL)
                            .withExpression(c("sp","TerritoryID"))
                            .and()
                        .and()
                    .and()
                .build();

        // @formatter:on

        assertEquals(from.getTableSourceList().size(),1);

        assertEquals(from.getTableSourceList().get(0).getClass(), From.JoinedTable.class);
        From.JoinedTable tableSource = (From.JoinedTable) from.getTableSourceList().get(0);

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
    public From exampleH = new MockParentBuilder<FromBuilder<MockParent<From>>,From>
                (FromBuilder.class,From.class)
                .$child()
                    .$()
                        .$(t("Production","Product"))
                        .$As("p")
                        .$InnerMergeJoin()
                        .$(t("Purchasing","ProductVendor"))
                        .$As("pv")
                        .$On()
                            .$(p_equal(
                                    c("p","ProductID"),
                                    c("pv","ProductID")
                            ))
                            .and()
                        .$InnerHashJoin()
                        .$(t("Purchasing","Vendor"))
                        .$As("v")
                        .$On()
                            .$(p_equal(
                                    c("pv","BusinessEntityID"),
                                    c("v","BusinessEntityID")
                            ))
                            .and()
                        .and()
                    .and()
                .get();
    // @formatter:on

    @Test
    public void testExampleH(){
        // @formatter:off
        From from = new FromBuilder<From>()
                .withItem()._Joined()
                    .withTableSource()._Joined()
                        .withTableSource()._Base()
                            .withTableName(t("Production","Product"))
                            .withAs()
                            .withTableAlias("p")
                            .and()
                        .withJoinType(From.JoinTypeKeywords.INNER_JOIN, JoinHint.MERGE)
                        .withTableSource2()._Base()
                            .withTableName(t("Purchasing","ProductVendor"))
                            .withAs()
                            .withTableAlias("pv")
                            .and()
                        .withSearchCondition()
                            .withPredicate()._Comparison()
                                .withExpression(c("p","ProductID"))
                                .withOperator(com.xy.xsql.tsql.model.elements.operators.Comparison.EQUAL)
                                .withExpression(c("pv","ProductID"))
                                .and()
                            .and()
                        .and()
                    .withJoinType(From.JoinTypeKeywords.INNER_JOIN, JoinHint.HASH)
                    .withTableSource2()._Base()
                        .withTableName(t("Purchasing","Vendor"))
                        .withAs()
                        .withTableAlias("v")
                        .and()
                    .withSearchCondition()
                        .withPredicate()._Comparison()
                            .withExpression(c("pv","BusinessEntityID"))
                            .withOperator(com.xy.xsql.tsql.model.elements.operators.Comparison.EQUAL)
                            .withExpression(c("v","BusinessEntityID"))
                            .and()
                        .and()
                    .and()
                .build();

        // @formatter:on

        assertEquals(from.getTableSourceList().size(),1);

        assertEquals(from.getTableSourceList().get(0).getClass(), From.JoinedTable.class);
        From.JoinedTable tableSource = (From.JoinedTable) from.getTableSourceList().get(0);

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
    public From exampleI = new MockParentBuilder<FromBuilder<MockParent<From>>,From>
                (FromBuilder.class,From.class)
                .$child()
                    .$()
                        .$(t("Person","Person"))
                        .$As("p")
                        .$InnerJoin()
                        .$(t("HumanResources","Employee"))
                        .$As("e")
                        .$On()
                            .$(p_equal(
                                    c("p","BusinessEntityID"),
                                    c("e","BusinessEntityID")
                            ))
                            .and()
                        .$InnerJoin()
                        .$(querySpecification)
                        .$As("d")
                        .$On()
                            .$(p_equal(
                                    c("p","BusinessEntityID"),
                                    c("d","BusinessEntityID")
                            ))
                            .and()
                        .and()
                    .and()
                .get();
    // @formatter:on

    @Test
    public void testExampleI(){
        // @formatter:off
        From from = new FromBuilder<From>()
                .withItem()._Joined()
                    .withTableSource()._Joined()
                        .withTableSource()._Base()
                            .withTableName(t("Person","Person"))
                            .withAs()
                            .withTableAlias("p")
                            .and()
                        .withJoinType(From.JoinTypeKeywords.INNER_JOIN)
                        .withTableSource2()._Base()
                            .withTableName(t("HumanResources","Employee"))
                            .withTableAlias("e")
                            .and()
                        .withSearchCondition()
                            .withPredicate()._Comparison()
                                .withExpression(c("p","BusinessEntityID"))
                                .withOperator(com.xy.xsql.tsql.model.elements.operators.Comparison.EQUAL)
                                .withExpression(c("e","BusinessEntityID"))
                                .and()
                            .and()
                        .and()
                    .withJoinType(From.JoinTypeKeywords.INNER_JOIN)
                    .withTableSource2()._Derived()
                        .withSubQuery($SubQuery(querySpecification))
                        .withAs()
                        .withTableAlias("d")
                        .and()
                    .withSearchCondition()
                        .withPredicate()._Comparison()
                            .withExpression(c("p","BusinessEntityID"))
                            .withOperator(com.xy.xsql.tsql.model.elements.operators.Comparison.EQUAL)
                            .withExpression(c("d","BusinessEntityID"))
                            .and()
                        .and()
                    .and()
                .build();
        // @formatter:on

        assertEquals(from.getTableSourceList().size(),1);

        assertEquals(from.getTableSourceList().get(0).getClass(), From.JoinedTable.class);
        From.JoinedTable tableSource = (From.JoinedTable) from.getTableSourceList().get(0);

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
     * FROM Sales.Customer TABLESAMPLE SYSTEM (10 PERCENT)
     */
    public From exampleJ = new MockParentBuilder<FromBuilder<MockParent<From>>,From>
                (FromBuilder.class,From.class)
                .$child()
                    .$()
                        .$(t("Sales","Customer"))
                            .$TableSample()
                                .$System()
                                .$Percent(10)
                                .and()
                            .and()
                        .and()
                    .and()
                .get();
    // @formatter:on

    @Test
    public void testExampleJ(){
        // @formatter:off
        From from = new FromBuilder<From>()
                .withItem()._Base()
                    .withTableName(t("Sales","Customer"))
                    .withTableSample()
                        .withSystem()
                        .withSampleNumber(10)
                        .withPercent()
                        .and()
                    .and()
                .build();
        // @formatter:on

        assertEquals(from.getTableSourceList().size(),1);

        assertEquals(from.getTableSourceList().get(0).getClass(), From.BaseTable.class);
        From.BaseTable tableSource = (From.BaseTable) from.getTableSourceList().get(0);
        assertEquals(tableSource.getTableName().toString(),"Sales.Customer");

        assertTrue(tableSource.getTableSample().isUseSystem());
        assertEquals(tableSource.getTableSample().getSampleNumber().getNumber(),10);
        assertTrue(tableSource.getTableSample().isUsePercent());
    }


    // @formatter:off
    /**
     * FROM Departments d CROSS APPLY dbo.GetReports(d.DeptMgrID)
     */
    public From exampleK = new MockParentBuilder<FromBuilder<MockParent<From>>,From>
                (FromBuilder.class,From.class)
                .$child()
                    .$()
                        .$(t("Departments"))
                        .$As("d")
                        .$Cross_Apply()
            //TODO rowset_function
//                        .$(f("dbo.GetReports"),
//                                c("d","DeptMgrID"))
                        .and()
                    .and()
                .get();
    // @formatter:on

    @Test
    public void testExampleK(){
        // @formatter:off
        From from = new FromBuilder<From>()
                .withItem()._Joined()
                    .withTableSource()._Base()
                        .withTableName(t("Departments"))
                        .withTableAlias("d")
                        .and()
                    .withCrossApply()
            //TODO rowset_function
//                    .withTableSource2()._Function()
//                        .withName("dbo.GetReports")
//                        .withArgs(c("d","DeptMgrID"))
//                        .and()
                    .and()
                .build();
        // @formatter:on

        assertEquals(from.getTableSourceList().size(),1);

        assertEquals(from.getTableSourceList().get(0).getClass(), From.JoinedTable.class);
        From.JoinedTable tableSource = (From.JoinedTable) from.getTableSourceList().get(0);

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
    public From exampleL = new MockParentBuilder<FromBuilder<MockParent<From>>,From>
                (FromBuilder.class,From.class)
                .$child()
                    .$()
                        .$(t("sys","dm_exec_cached_plans"))
                        .$As("cp")
                        .$Cross_Apply()
            //TODO rowset_function
//                        .$(f("sys.dm_exec_query_plan"),
//                                c("cp","plan_handle"))
                        .and()
                    .and()
                .get();
    // @formatter:on

    @Test
    public void testExampleL(){
        // @formatter:off
        From from = new FromBuilder<From>()
                .withItem()._Joined()
                    .withTableSource()._Base()
                        .withTableName(t("sys","dm_exec_cached_plans"))
                        .withTableAlias("cp")
                        .and()
                    .withCrossApply()
            //TODO rowset_function
//                    .withTableSource2()._Function()
//                        .withName("sys.dm_exec_query_plan")
//                        .withArgs(c("cp","plan_handle"))
//                        .and()
                    .and()
                .build();
        // @formatter:on

        assertEquals(from.getTableSourceList().size(),1);

        assertEquals(from.getTableSourceList().get(0).getClass(), From.JoinedTable.class);
        From.JoinedTable tableSource = (From.JoinedTable) from.getTableSourceList().get(0);

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
    public From exampleM1 = new MockParentBuilder<FromBuilder<MockParent<From>>,From>
                (FromBuilder.class,From.class)
                .$child()
                    .$(t("DEPARTMENT"),null)
                    .$ForSystemTime()
                    .$AsOf("2014-01-01")
//                    .$(t("DEPARTMENT"),null).$ForSystemTime()
//                        .$AsOf("2014-01-01")
//                        .and()
                    .and()
                .get();
    // @formatter:on

    @Test
    public void testExampleM1(){
        // @formatter:off
        From from = new FromBuilder<From>()
                .withItem()._BaseTime()
                    .withTableName(t("DEPARTMENT"))
                    .withSystemTime()._AsOf(e_string("2014-01-01"))
                    .and()
                .build();
        // @formatter:on

        assertEquals(from.getTableSourceList().size(),1);

        assertEquals(from.getTableSourceList().get(0).getClass(),From.BaseWithTimeTable.class);
        From.BaseWithTimeTable tableSource = (From.BaseWithTimeTable) from.getTableSourceList().get(0);
        assertEquals(tableSource.getTableName().toString(),"DEPARTMENT");

        assertEquals(tableSource.getSystemTime().getDateTime().getDateTimeLiteral().getString(),"2014-01-01");

    }


    // @formatter:off
    /**
     * FROM DEPARTMENT
     FOR SYSTEM_TIME FROM '2013-01-01' TO '2014-01-01'
     */
    public From exampleM2 = new MockParentBuilder<FromBuilder<MockParent<From>>,From>
                (FromBuilder.class,From.class)
                .$child()
                    .$(t("DEPARTMENT"),null)
                        .$ForSystemTime().$FromTo("2013-01-01","2014-01-01")
//                        .and()
                    .and()
                .get();
    // @formatter:on

    @Test
    public void testExampleM2(){
        // @formatter:off
        From from = new FromBuilder<From>()
                .withItem()._BaseTime()
                    .withTableName(t("DEPARTMENT"))
                    .withSystemTime()._From()
                        .withFrom(e_string("2013-01-01"))
                        .withTo(e_string("2014-01-01"))
                        .and()
                    .and()
                .build();
        // @formatter:on

        assertEquals(from.getTableSourceList().size(),1);

        assertEquals(from.getTableSourceList().get(0).getClass(),From.BaseWithTimeTable.class);
        From.BaseWithTimeTable tableSource = (From.BaseWithTimeTable) from.getTableSourceList().get(0);
        assertEquals(tableSource.getTableName().toString(),"DEPARTMENT");

        assertTrue(tableSource.getSystemTime().isUseFrom());
        assertEquals(tableSource.getSystemTime().getStartDateTime().getDateTimeLiteral().getString(),"2013-01-01");
        assertEquals(tableSource.getSystemTime().getEndDateTime().getDateTimeLiteral().getString(),"2014-01-01");

    }


    // @formatter:off
    /**
     * FROM DEPARTMENT
     FOR SYSTEM_TIME $BETWEEN '2013-01-01' $AND '2014-01-01'
     */
    public From exampleM3 = new MockParentBuilder<FromBuilder<MockParent<From>>,From>
                (FromBuilder.class,From.class)
                .$child()
                    .$(t("DEPARTMENT"),null)
                        .$ForSystemTime().$BetweenAnd("2013-01-01","2014-01-01")
//                        .and()
                    .and()
                .get();
    // @formatter:on

    @Test
    public void testExampleM3(){
        // @formatter:off
        From from = new FromBuilder<From>()
                .withItem()._BaseTime()
                    .withTableName(t("DEPARTMENT"))
                    .withSystemTime()._Between()
                        .withBetween(e_string("2013-01-01"))
                        .withAnd(e_string("2014-01-01"))
                        .and()
                    .and()
                .build();
        // @formatter:on

        assertEquals(from.getTableSourceList().size(),1);

        assertEquals(from.getTableSourceList().get(0).getClass(),From.BaseWithTimeTable.class);
        From.BaseWithTimeTable tableSource = (From.BaseWithTimeTable) from.getTableSourceList().get(0);
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
    public From exampleM4 = new MockParentBuilder<FromBuilder<MockParent<From>>,From>
                (FromBuilder.class,From.class)
                .$child()
                    .$(t("DEPARTMENT"),null)
                        .$ForSystemTime().$ContainedIn("2013-01-01","2014-01-01")
//                        .and()
                    .and()
                .get();
    // @formatter:on

    @Test
    public void testExampleM4(){
        // @formatter:off
        From from = new FromBuilder<From>()
                .withItem()._BaseTime()
                    .withTableName(t("DEPARTMENT"))
                    .withSystemTime()._ContainedIn()
                        .withStart(e_string("2013-01-01"))
                        .withEnd(e_string("2014-01-01"))
                        .and()
                    .and()
                .build();
        // @formatter:on

        assertEquals(from.getTableSourceList().size(),1);

        assertEquals(from.getTableSourceList().get(0).getClass(),From.BaseWithTimeTable.class);
        From.BaseWithTimeTable tableSource = (From.BaseWithTimeTable) from.getTableSourceList().get(0);
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
    public From exampleM5 = new MockParentBuilder<FromBuilder<MockParent<From>>,From>
                (FromBuilder.class,From.class)
                .$child()
                    .$(t("DEPARTMENT"),null)
                        .$ForSystemTime()
                            .$From(e_variable("AsOfFrom"))
                            .$To(e_variable("AsOfTo"))
//                        .and()
                    .and()
                .get();
    // @formatter:on

    @Test
    public void testExampleM5(){
        // @formatter:off
        From from = new FromBuilder<From>()
                .withItem()._BaseTime()
                    .withTableName(t("DEPARTMENT"))
                    .withSystemTime()._From()
                        .withFrom(e_variable("AsOfFrom"))
                        .withTo(e_variable("AsOfTo"))
                        .and()
                    .and()
                .build();
        // @formatter:on

        assertEquals(from.getTableSourceList().size(),1);

        assertEquals(from.getTableSourceList().get(0).getClass(),From.BaseWithTimeTable.class);
        From.BaseWithTimeTable tableSource = (From.BaseWithTimeTable) from.getTableSourceList().get(0);
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
     * FROM DimSalesTerritory
     */
    public From exampleN = new MockParentBuilder<FromBuilder<MockParent<From>>,From>
                (FromBuilder.class,From.class)
                .$child()
                    .$(t("DimSalesTerritory"))
                    .and()
                .get();
    // @formatter:on

    @Test
    public void testExampleN(){
        // @formatter:off
        From from = new FromBuilder<From>()
                .withItem()._BaseTime()
                    .withTableName(t("DimSalesTerritory"))
                    .and()
                .build();
        // @formatter:on

        assertEquals(from.getTableSourceList().size(),1);

        assertEquals(from.getTableSourceList().get(0).getClass(),From.BaseWithTimeTable.class);
        From.BaseWithTimeTable tableSource = (From.BaseWithTimeTable) from.getTableSourceList().get(0);
        assertEquals(tableSource.getTableName().toString(),"DimSalesTerritory");

    }

    // @formatter:off
    /**
     * FROM FactInternetSales AS fis
     INNER JOIN DimProduct AS dp
     ON dp.ProductKey = fis.ProductKey
     */
    public From exampleO = new MockParentBuilder<FromBuilder<MockParent<From>>,From>
                (FromBuilder.class,From.class)
                .$child()
                    .$()
                        .$(t("FactInternetSales")).$As("fis")
                        .$InnerJoin()
                        .$(t("DimProduct")).$As("dp")
                        .$On()
                            .$(p_equal(
                                    c("dp","ProductKey"),
                                    c("fis","ProductKey")
                            ))
                            .and()
                        .and()
                    .and()
                .get();
    // @formatter:on

    @Test
    public void testExampleO(){
        // @formatter:off
        From from = new FromBuilder<From>()
                .withItem()._Joined()
                    .withTableSource()._Base()
                        .withTableName(t("FactInternetSales"))
                        .withAs()
                        .withTableAlias("fis")
                        .and()
                    .withJoinType(From.JoinTypeKeywords.INNER_JOIN)
                    .withTableSource2()._Base()
                        .withTableName(t("DimProduct"))
                        .withAs()
                        .withTableAlias("dp")
                        .and()
                    .withSearchCondition()
                        .withPredicate()._Comparison()
                            .withExpression(c("dp","ProductKey"))
                            .withOperator(com.xy.xsql.tsql.model.elements.operators.Comparison.EQUAL)
                            .withExpression(c("fis","ProductKey"))
                            .and()
                        .and()
                    .and()
                .build();
        // @formatter:on

        assertEquals(from.getTableSourceList().size(),1);

        assertEquals(from.getTableSourceList().get(0).getClass(),From.JoinedTable.class);
        From.JoinedTable tableSource = (From.JoinedTable) from.getTableSourceList().get(0);

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
    public From exampleP = new MockParentBuilder<FromBuilder<MockParent<From>>,From>
                (FromBuilder.class,From.class)
                .$child()
                    .$()
                        .$(t("FactInternetSales")).$As("fis")
                        .$LeftOuterJoin()
                        .$(t("DimProduct")).$As("dp")
                        .$On()
                            .$(p_equal(
                                    c("dp","ProductKey"),
                                    c("fis","ProductKey")
                            ))
                            .and()
                        .and()
                    .and()
                .get();
    // @formatter:on

    @Test
    public void testExampleP(){
        // @formatter:off
        From from = new FromBuilder<From>()
                .withItem()._Joined()
                    .withTableSource()._Base()
                        .withTableName(t("FactInternetSales"))
                        .withAs()
                        .withTableAlias("fis")
                        .and()
                    .withJoinType(From.JoinTypeKeywords.LEFT_OUTER_JOIN)
                    .withTableSource2()._Base()
                        .withTableName(t("DimProduct"))
                        .withAs()
                        .withTableAlias("dp")
                        .and()
                    .withSearchCondition()
                        .withPredicate()._Comparison()
                            .withExpression(c("dp","ProductKey"))
                            .withOperator(com.xy.xsql.tsql.model.elements.operators.Comparison.EQUAL)
                            .withExpression(c("fis","ProductKey"))
                            .and()
                        .and()
                    .and()
                .build();
        // @formatter:on

        assertEquals(from.getTableSourceList().size(),1);

        assertEquals(from.getTableSourceList().get(0).getClass(),From.JoinedTable.class);
        From.JoinedTable tableSource = (From.JoinedTable) from.getTableSourceList().get(0);

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
