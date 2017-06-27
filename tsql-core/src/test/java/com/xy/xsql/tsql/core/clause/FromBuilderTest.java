package com.xy.xsql.tsql.core.clause;

import com.xy.xsql.tsql.core.MockParent;
import com.xy.xsql.tsql.core.MockParentBuilder;
import com.xy.xsql.tsql.model.clause.From;
import com.xy.xsql.tsql.model.clause.hints.JoinHint;
import com.xy.xsql.tsql.model.operator.Operators;
import com.xy.xsql.tsql.model.predicate.Comparison;
import com.xy.xsql.tsql.model.statement.dml.Select;
import org.junit.Assert;
import org.junit.Test;

import static com.xy.xsql.tsql.core.element.ColumnNameFactory.c;
import static com.xy.xsql.tsql.core.element.TableNameFactory.t;
import static com.xy.xsql.tsql.core.predicate.Predicates.*;

/**
 * Created by xiaoyao9184 on 2017/3/11.
 */
public class FromBuilderTest {


    // @formatter:off
    public From exampleA = new MockParentBuilder<FromBuilder<MockParent<From>>,From>
            (FromBuilder.class,From.class)
            .$child()
                .$(t("Sales","SalesTerritory"))
                .and()
            .get();
    // @formatter:on

    /**
     * FROM Sales.SalesTerritory
     */
    @Test
    public void testExampleA(){
        // @formatter:off
        From option = new FromBuilder<From>()
                .withItem()._Base()
                    .withTableName(t("Sales","SalesTerritory"))
                    .and()
                .build();

        //parent+quick
        MockParent<From> parent = new MockParentBuilder<FromBuilder<MockParent<From>>,From>
                (FromBuilder.class,From.class)
                .$child()
                    .$(t("Sales","SalesTerritory"))
                    .and();
        // @formatter:on

        Assert.assertEquals(option.getTableSourceList().size(),1);
        Assert.assertEquals(option.getTableSourceList().get(0).getClass(), From.BaseTable.class);
        From.BaseTable tableSource = (From.BaseTable) option.getTableSourceList().get(0);
        Assert.assertEquals(tableSource.getTableName().toString(),"Sales.SalesTerritory");
    }


    // @formatter:off
    public From exampleB = new MockParentBuilder<FromBuilder<MockParent<From>>,From>
            (FromBuilder.class,From.class)
            .$child()
                .$(t("HumanResources","Employee"))
                .and()
            .get();
    // @formatter:on

    /**
     * FROM HumanResources.Employee
     */
    @Test
    public void testExampleB(){
        // @formatter:off
        From option = new FromBuilder<From>()
                .withItem()._Base()
                    .withTableName(t("HumanResources","Employee"))
                    .and()
                .build();

        //parent+quick
        MockParent<From> parent = new MockParentBuilder<FromBuilder<MockParent<From>>,From>
                (FromBuilder.class,From.class)
                .$child()
                    .$(t("HumanResources","Employee"))
                    .and();
        // @formatter:on

        Assert.assertEquals(option.getTableSourceList().size(),1);
        Assert.assertEquals(option.getTableSourceList().get(0).getClass(), From.BaseTable.class);
        From.BaseTable tableSource = (From.BaseTable) option.getTableSourceList().get(0);
        Assert.assertEquals(tableSource.getTableName().toString(),"HumanResources.Employee");
    }


    // @formatter:off
    public From exampleC = new MockParentBuilder<FromBuilder<MockParent<From>>,From>
            (FromBuilder.class,From.class)
            .$child()
                .$()
                    .$(t("HumanResources","Employee"),"e")
                    .$Cross_Join()
                    .$(t("HumanResources","Department"),"d")
                    .and()
                .and()
            .get();
    // @formatter:on

    /**
     * FROM HumanResources.Employee AS e
     CROSS JOIN HumanResources.Department AS d
     */
    @Test
    public void testExampleC(){
        // @formatter:off
        From from = new FromBuilder<From>()
                .withItem()._Joined()
                    .withCrossJoin()
                    .withTableSource()._Base()
                        .withTableName(t("HumanResources","Employee"))
                        .withTableAlias("e")
                        .and()
                    .withTableSource2()._Base()
                        .withTableName(t("HumanResources","Department"))
                        .withTableAlias("d")
                        .and()
                    .and()
                .build();

        //parent+quick
        MockParent<From> parent = new MockParentBuilder<FromBuilder<MockParent<From>>,From>
                (FromBuilder.class,From.class)
                .$child()
                    .$()
                        .$(t("HumanResources","Employee"),"e")
                        .$Cross_Join()
                        .$(t("HumanResources","Department"),"d")
                        .and()
                    .and();
        // @formatter:on

        Assert.assertEquals(from.getTableSourceList().size(),1);

        Assert.assertEquals(from.getTableSourceList().get(0).getClass(), From.JoinedTable.class);
        From.JoinedTable tableSource = (From.JoinedTable) from.getTableSourceList().get(0);

        Assert.assertEquals(tableSource.getTableSource().getClass(),From.BaseTable.class);
        From.BaseTable tableSource1 = (From.BaseTable) tableSource.getTableSource();
        Assert.assertEquals(tableSource1.getTableName().toString(),"HumanResources.Employee");
        Assert.assertEquals(tableSource1.getTableAlias().toString(),"e");

        Assert.assertEquals(tableSource.isUseCrossJoin(),true);

        Assert.assertEquals(tableSource.getTableSource2().getClass(),From.BaseTable.class);
        From.BaseTable tableSource2 = (From.BaseTable) tableSource.getTableSource2();
        Assert.assertEquals(tableSource2.getTableName().toString(),"HumanResources.Department");
        Assert.assertEquals(tableSource2.getTableAlias().toString(),"d");
    }


    // @formatter:off
    public From exampleD =  new MockParentBuilder<FromBuilder<MockParent<From>>,From>
                (FromBuilder.class,From.class)
                .$child()
                    .$()
                        .$(t("Production","Product"))
                        .$As("p")
                        .$Full_Outer_Join()
                        .$(t("HumanResources","Department"))
                        .$As("p")
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

    /**
     * FROM Production.Product AS p
     FULL OUTER JOIN Sales.SalesOrderDetail AS sod
     ON p.ProductID = sod.ProductID
     */
    @Test
    public void testExampleD(){
        // @formatter:off
        From from = new FromBuilder<From>()
                .withItem()._Joined()
                    .withTableSource()._Base()
                        .withTableName(t("Production","Product"))
                        .withTableAlias("p")
                        .and()
                    .withJoinType(From.JoinTypeKeywords.FULL_OUTER_JOIN)
                    .withTableSource2()._Base()
                        .withTableName(t("Sales","SalesOrderDetail"))
                        .withTableAlias("sod")
                        .and()
                    .withSearchCondition()
                        .withPredicate()._Comparison()
                            .withExpression(c("p","ProductID"))
                            .withOperator(Operators.EQUAL)
                            .withExpression(c("sod","ProductID"))
                            .and()
                        .and()
                    .and()
                .build();

        //parent+quick
        MockParent<From> parent = new MockParentBuilder<FromBuilder<MockParent<From>>,From>
                (FromBuilder.class,From.class)
                .$child()
                    .$()
                        .$(t("Production","Product"))
                        .$As("p")
                        .$Full_Outer_Join()
                        .$(t("HumanResources","Department"))
                        .$As("p")
                //TODO i dont know wtf about this tag
//                        .and()
//                        .$(t("HumanResources","Department"),"p")
                        .$On()
                            .$(p_equal(
                                    c("p","ProductID"),
                                    c("sod","ProductID")
                            ))
                            .and()
                        .and()
                    .and();
        // @formatter:on

        Assert.assertEquals(from.getTableSourceList().size(),1);

        Assert.assertEquals(from.getTableSourceList().get(0).getClass(), From.JoinedTable.class);
        From.JoinedTable tableSource = (From.JoinedTable) from.getTableSourceList().get(0);

        Assert.assertEquals(tableSource.getTableSource().getClass(),From.BaseTable.class);
        From.BaseTable tableSource1 = (From.BaseTable) tableSource.getTableSource();
        Assert.assertEquals(tableSource1.getTableName().toString(),"Production.Product");
        Assert.assertEquals(tableSource1.getTableAlias().toString(),"p");

        Assert.assertEquals(tableSource.getJoinType().getKeywords(), From.JoinTypeKeywords.FULL_OUTER_JOIN);

        Assert.assertEquals(tableSource.getTableSource2().getClass(),From.BaseTable.class);
        From.BaseTable tableSource2 = (From.BaseTable) tableSource.getTableSource2();
        Assert.assertEquals(tableSource2.getTableName().toString(),"Sales.SalesOrderDetail");
        Assert.assertEquals(tableSource2.getTableAlias().toString(),"sod");

        Assert.assertEquals(tableSource.getSearchCondition().getPredicate().getClass(),Comparison.class);

    }


    // @formatter:off
    public From exampleE =  new MockParentBuilder<FromBuilder<MockParent<From>>,From>
                (FromBuilder.class,From.class)
                .$child()
                    .$()
                        .$(t("Production","Product"))
                        .$As("p")
                        .$Left_Outer_Join()
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

    /**
     * FROM Production.Product AS p
     LEFT OUTER JOIN Sales.SalesOrderDetail AS sod
     ON p.ProductID = sod.ProductID
     */
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
                            .withOperator(Operators.EQUAL)
                            .withExpression(c("sod","ProductID"))
                            .and()
                        .and()
                    .and()
                .build();

        // @formatter:on

        Assert.assertEquals(from.getTableSourceList().size(),1);

        Assert.assertEquals(from.getTableSourceList().get(0).getClass(), From.JoinedTable.class);
        From.JoinedTable tableSource = (From.JoinedTable) from.getTableSourceList().get(0);

        Assert.assertEquals(tableSource.getTableSource().getClass(),From.BaseTable.class);
        From.BaseTable tableSource1 = (From.BaseTable) tableSource.getTableSource();
        Assert.assertEquals(tableSource1.getTableName().toString(),"Production.Product");
        Assert.assertEquals(tableSource1.getTableAlias().toString(),"p");

        Assert.assertEquals(tableSource.getJoinType().getKeywords(), From.JoinTypeKeywords.LEFT_OUTER_JOIN);

        Assert.assertEquals(tableSource.getTableSource2().getClass(),From.BaseTable.class);
        From.BaseTable tableSource2 = (From.BaseTable) tableSource.getTableSource2();
        Assert.assertEquals(tableSource2.getTableName().toString(),"Sales.SalesOrderDetail");
        Assert.assertEquals(tableSource2.getTableAlias().toString(),"sod");

        Assert.assertEquals(tableSource.getSearchCondition().getPredicate().getClass(),Comparison.class);

    }


    // @formatter:off
    public From exampleF =  new MockParentBuilder<FromBuilder<MockParent<From>>,From>
                (FromBuilder.class,From.class)
                .$child()
                    .$()
                        .$(t("Production","Product"))
                        .$As("p")
                        .$Inner_Join()
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

    /**
     * FROM Production.Product AS p
     INNER JOIN Sales.SalesOrderDetail AS sod
     ON p.ProductID = sod.ProductID
     */
    @Test
    public void testExampleF(){
        // @formatter:off
        From from = new FromBuilder<From>()
                .withItem()._Joined()
                    .withTableSource()._Base()
                        .withTableName(t("Production","Product"))
                        .withTableAlias("p")
                        .and()
                    .withJoinType(From.JoinTypeKeywords.INNER_JOIN)
                    .withTableSource2()._Base()
                        .withTableName(t("Sales","SalesOrderDetail"))
                        .withTableAlias("sod")
                        .and()
                    .withSearchCondition()
                        .withPredicate()._Comparison()
                            .withExpression(c("p","ProductID"))
                            .withOperator(Operators.EQUAL)
                            .withExpression(c("sod","ProductID"))
                            .and()
                        .and()
                    .and()
                .build();

        // @formatter:on

        Assert.assertEquals(from.getTableSourceList().size(),1);

        Assert.assertEquals(from.getTableSourceList().get(0).getClass(), From.JoinedTable.class);
        From.JoinedTable tableSource = (From.JoinedTable) from.getTableSourceList().get(0);

        Assert.assertEquals(tableSource.getTableSource().getClass(),From.BaseTable.class);
        From.BaseTable tableSource1 = (From.BaseTable) tableSource.getTableSource();
        Assert.assertEquals(tableSource1.getTableName().toString(),"Production.Product");
        Assert.assertEquals(tableSource1.getTableAlias().toString(),"p");

        Assert.assertEquals(tableSource.getJoinType().getKeywords(), From.JoinTypeKeywords.INNER_JOIN);

        Assert.assertEquals(tableSource.getTableSource2().getClass(),From.BaseTable.class);
        From.BaseTable tableSource2 = (From.BaseTable) tableSource.getTableSource2();
        Assert.assertEquals(tableSource2.getTableName().toString(),"Sales.SalesOrderDetail");
        Assert.assertEquals(tableSource2.getTableAlias().toString(),"sod");

        Assert.assertEquals(tableSource.getSearchCondition().getPredicate().getClass(),Comparison.class);

    }


    // @formatter:off
    public From exampleG =  new MockParentBuilder<FromBuilder<MockParent<From>>,From>
                (FromBuilder.class,From.class)
                .$child()
                    .$()
                        .$(t("Sales","SalesTerritory"))
                        .$As("st")
                        .$Right_Outer_Join()
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

    /**
     * FROM Sales.SalesTerritory AS st
     RIGHT OUTER JOIN Sales.SalesPerson AS sp
     ON st.TerritoryID = sp.TerritoryID ;
     */
    @Test
    public void testExampleG(){
        // @formatter:off
        From from = new FromBuilder<From>()
                .withItem()._Joined()
                    .withTableSource()._Base()
                        .withTableName(t("Sales","SalesTerritory"))
                        .withTableAlias("st")
                        .and()
                    .withJoinType(From.JoinTypeKeywords.RIGHT_OUTER_JOIN)
                    .withTableSource2()._Base()
                        .withTableName(t("Sales","SalesPerson"))
                        .withTableAlias("sp")
                        .and()
                    .withSearchCondition()
                        .withPredicate()._Comparison()
                            .withExpression(c("st","TerritoryID"))
                            .withOperator(Operators.EQUAL)
                            .withExpression(c("sp","TerritoryID"))
                            .and()
                        .and()
                    .and()
                .build();

        // @formatter:on

        Assert.assertEquals(from.getTableSourceList().size(),1);

        Assert.assertEquals(from.getTableSourceList().get(0).getClass(), From.JoinedTable.class);
        From.JoinedTable tableSource = (From.JoinedTable) from.getTableSourceList().get(0);

        Assert.assertEquals(tableSource.getTableSource().getClass(),From.BaseTable.class);
        From.BaseTable tableSource1 = (From.BaseTable) tableSource.getTableSource();
        Assert.assertEquals(tableSource1.getTableName().toString(),"Sales.SalesTerritory");
        Assert.assertEquals(tableSource1.getTableAlias().toString(),"st");

        Assert.assertEquals(tableSource.getJoinType().getKeywords(), From.JoinTypeKeywords.RIGHT_OUTER_JOIN);

        Assert.assertEquals(tableSource.getTableSource2().getClass(),From.BaseTable.class);
        From.BaseTable tableSource2 = (From.BaseTable) tableSource.getTableSource2();
        Assert.assertEquals(tableSource2.getTableName().toString(),"Sales.SalesPerson");
        Assert.assertEquals(tableSource2.getTableAlias().toString(),"sp");

        Assert.assertEquals(tableSource.getSearchCondition().getPredicate().getClass(),Comparison.class);

    }


    // @formatter:off
    public From exampleH =  new MockParentBuilder<FromBuilder<MockParent<From>>,From>
                (FromBuilder.class,From.class)
                .$child()
                    .$()
                        .$(t("Production","Product"))
                        .$As("p")
                        .$Inner_Merge_Join()
                        .$(t("Purchasing","ProductVendor"))
                        .$As("pv")
                        .$On()
                            .$(p_equal(
                                    c("p","ProductID"),
                                    c("pv","ProductID")
                            ))
                            .and()
                        .$Inner_Hash_Join()
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

    /**
     * FROM Production.Product AS p
     INNER MERGE JOIN Purchasing.ProductVendor AS pv
     ON p.ProductID = pv.ProductID
     INNER HASH JOIN Purchasing.Vendor AS v
     ON pv.BusinessEntityID = v.BusinessEntityID
     */
    @Test
    public void testExampleH(){
        // @formatter:off
        From from = new FromBuilder<From>()
                .withItem()._Joined()
                    .withTableSource()._Joined()
                        .withTableSource()._Base()
                            .withTableName(t("Production","Product"))
                            .withTableAlias("p")
                            .and()
                        .withJoinType(From.JoinTypeKeywords.INNER_JOIN, JoinHint.MERGE)
                        .withTableSource2()._Base()
                            .withTableName(t("Purchasing","ProductVendor"))
                            .withTableAlias("pv")
                            .and()
                        .withSearchCondition()
                            .withPredicate()._Comparison()
                                .withExpression(c("p","ProductID"))
                                .withOperator(Operators.EQUAL)
                                .withExpression(c("pv","ProductID"))
                                .and()
                            .and()
                        .and()
                    .withJoinType(From.JoinTypeKeywords.INNER_JOIN, JoinHint.HASH)
                    .withTableSource2()._Base()
                        .withTableName(t("Purchasing","Vendor"))
                        .withTableAlias("v")
                        .and()
                    .withSearchCondition()
                        .withPredicate()._Comparison()
                            .withExpression(c("pv","BusinessEntityID"))
                            .withOperator(Operators.EQUAL)
                            .withExpression(c("v","BusinessEntityID"))
                            .and()
                        .and()
                    .and()
                .build();

        // @formatter:on

        Assert.assertEquals(from.getTableSourceList().size(),1);

        Assert.assertEquals(from.getTableSourceList().get(0).getClass(), From.JoinedTable.class);
        From.JoinedTable tableSource = (From.JoinedTable) from.getTableSourceList().get(0);

        Assert.assertEquals(tableSource.getTableSource().getClass(),From.JoinedTable.class);
        From.JoinedTable tableSource01 = (From.JoinedTable) tableSource.getTableSource();

            Assert.assertEquals(tableSource01.getTableSource().getClass(),From.BaseTable.class);
            From.BaseTable tableSource11 = (From.BaseTable) tableSource01.getTableSource();
            Assert.assertEquals(tableSource11.getTableName().toString(),"Production.Product");
            Assert.assertEquals(tableSource11.getTableAlias().toString(),"p");

            Assert.assertEquals(tableSource01.getJoinType().getKeywords(), From.JoinTypeKeywords.INNER_JOIN);
            Assert.assertEquals(tableSource01.getJoinType().getJoinHint(), JoinHint.MERGE);

            Assert.assertEquals(tableSource01.getTableSource2().getClass(),From.BaseTable.class);
            From.BaseTable tableSource12 = (From.BaseTable) tableSource01.getTableSource2();
            Assert.assertEquals(tableSource12.getTableName().toString(),"Purchasing.ProductVendor");
            Assert.assertEquals(tableSource12.getTableAlias().toString(),"pv");


        Assert.assertEquals(tableSource.getJoinType().getKeywords(), From.JoinTypeKeywords.INNER_JOIN);
        Assert.assertEquals(tableSource.getJoinType().getJoinHint(), JoinHint.HASH);

        Assert.assertEquals(tableSource.getTableSource2().getClass(),From.BaseTable.class);
        From.BaseTable tableSource02 = (From.BaseTable) tableSource.getTableSource2();
        Assert.assertEquals(tableSource02.getTableName().toString(),"Purchasing.Vendor");
        Assert.assertEquals(tableSource02.getTableAlias().toString(),"v");

        Assert.assertEquals(tableSource.getSearchCondition().getPredicate().getClass(),Comparison.class);

    }

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
    @Test
    public void testExampleI(){
        // @formatter:off
        From from = new FromBuilder<From>()
                .withItem()._Joined()
                    .withTableSource()._Joined()
                        .withTableSource()._Base()
                            .withTableName(t("Person","Person"))
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
                                .withOperator(Operators.EQUAL)
                                .withExpression(c("e","BusinessEntityID"))
                                .and()
                            .and()
                        .and()
                    .withJoinType(From.JoinTypeKeywords.INNER_JOIN)
                    .withTableSource2()._Derived()
                        .withSubQuery(new Select.QuerySpecification())
                        .withTableAlias("d")
                        .and()
                    .withSearchCondition()
                        .withPredicate()._Comparison()
                            .withExpression(c("p","BusinessEntityID"))
                            .withOperator(Operators.EQUAL)
                            .withExpression(c("d","BusinessEntityID"))
                            .and()
                        .and()
                    .and()
                .build();


        //parent+quick
        MockParent<From> parent = new MockParentBuilder<FromBuilder<MockParent<From>>,From>
                (FromBuilder.class,From.class)
                .$child()
                    .$()
                        .$(t("Person","Person"))
                        .$As("p")
                        .$Inner_Join()
                        .$(t("HumanResources","Employee"))
                        .$As("e")
                        .$On()
                            .$(p_equal(
                                    c("p","BusinessEntityID"),
                                    c("e","BusinessEntityID")
                            ))
                            .and()
                        .$Inner_Join()
                        .$(new Select.QuerySpecification())
                        .$As("d")
                        .$On()
                            .$(p_equal(
                                    c("p","BusinessEntityID"),
                                    c("d","BusinessEntityID")
                            ))
                            .and()
                        .and()
                    .and();
        // @formatter:on

        Assert.assertEquals(from.getTableSourceList().size(),1);

        Assert.assertEquals(from.getTableSourceList().get(0).getClass(), From.JoinedTable.class);
        From.JoinedTable tableSource = (From.JoinedTable) from.getTableSourceList().get(0);

        Assert.assertEquals(tableSource.getTableSource().getClass(),From.JoinedTable.class);
        From.JoinedTable tableSource0 = (From.JoinedTable) tableSource.getTableSource();

            Assert.assertEquals(tableSource0.getTableSource().getClass(),From.BaseTable.class);
            From.BaseTable tableSource1 = (From.BaseTable) tableSource0.getTableSource();
            Assert.assertNotNull(tableSource1.getTableName().toString(),"Person.Person");
            Assert.assertEquals(tableSource1.getTableAlias().toString(),"p");

            Assert.assertEquals(tableSource0.getJoinType().getKeywords(), From.JoinTypeKeywords.INNER_JOIN);

            Assert.assertEquals(tableSource0.getTableSource2().getClass(),From.BaseTable.class);
            From.BaseTable tableSource2 = (From.BaseTable) tableSource0.getTableSource2();
            Assert.assertNotNull(tableSource2.getTableName().toString(),"HumanResources.Employee");
            Assert.assertEquals(tableSource2.getTableAlias().toString(),"e");

        Assert.assertEquals(tableSource.getJoinType().getKeywords(), From.JoinTypeKeywords.INNER_JOIN);

        Assert.assertEquals(tableSource.getTableSource2().getClass(),From.DerivedTable.class);
        From.DerivedTable tableSource3 = (From.DerivedTable) tableSource.getTableSource2();
        Assert.assertNotNull(tableSource3.getSubQuery());
        Assert.assertEquals(tableSource3.getTableAlias().toString(),"d");

        Assert.assertEquals(tableSource.getSearchCondition().getPredicate().getClass(),Comparison.class);

    }

    /**
     * FROM Sales.Customer TABLESAMPLE SYSTEM (10 PERCENT) ;
     */
    @Test
    public void testExampleJ(){
        // @formatter:off
        From from = new FromBuilder<From>()
                .withItem()._Base()
                    .withTableName(t("Sales","Customer"))
//                    .withTABLESAMPLE
                    .and()
                .build();


        //parent+quick
        MockParent<From> parent = new MockParentBuilder<FromBuilder<MockParent<From>>,From>
                (FromBuilder.class,From.class)
                .$child()
                    .$()
                        .$(t("Sales","Customer"))
//                        .$TableSample()
//                            .$System()
//                            .$Percent(10)
//                            .$Row(10)
//                            .$RereaTable()
//                            .and()
                        .and()
                        .and()
                    .and();
        // @formatter:on

        Assert.assertEquals(from.getTableSourceList().size(),1);

        Assert.assertEquals(from.getTableSourceList().get(0).getClass(), From.BaseTable.class);
        From.BaseTable tableSource = (From.BaseTable) from.getTableSourceList().get(0);
        Assert.assertEquals(tableSource.getTableName().toString(),"Sales.Customer");
    }

    /**
     * FROM Departments d CROSS APPLY dbo.GetReports(d.DeptMgrID) ;
     */
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
//                    .withTableSource2()._Function()
//                        .withName("dbo.GetReports")
//                        .withArgs(c("d","DeptMgrID"))
//                        .and()
                    .and()
                .build();


        //parent+quick
        MockParent<From> parent = new MockParentBuilder<FromBuilder<MockParent<From>>,From>
                (FromBuilder.class,From.class)
                .$child()
                    .$()
                        .$(t("Departments"))
                        .$As("d")
                        .$Cross_Apply()
//                        .$(f("dbo.GetReports"),
//                                c("d","DeptMgrID"))
                        .and()
                    .and();
        // @formatter:on

        Assert.assertEquals(from.getTableSourceList().size(),1);

        Assert.assertEquals(from.getTableSourceList().get(0).getClass(), From.JoinedTable.class);
        From.JoinedTable tableSource = (From.JoinedTable) from.getTableSourceList().get(0);

        Assert.assertEquals(tableSource.getTableSource().getClass(),From.BaseTable.class);
        From.BaseTable tableSource1 = (From.BaseTable) tableSource.getTableSource();
        Assert.assertEquals(tableSource1.getTableName().toString(),"Departments");
        Assert.assertEquals(tableSource1.getTableAlias().toString(),"d");

        Assert.assertTrue(tableSource.isUseCrossApply());

//        Assert.assertEquals(tableSource.getTableSource2().getClass(),From.Function.class);
//        From.Function tableSource2 = (From.Function) tableSource.getTableSource2();
//        Assert.assertEquals(tableSource2.getName().toString(),"dbo.GetReports");
//        Assert.assertEquals(tableSource2.getArgs().toString(),"d.DeptMgrID");

    }
}
