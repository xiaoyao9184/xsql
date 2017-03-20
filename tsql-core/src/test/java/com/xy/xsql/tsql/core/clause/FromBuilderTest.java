package com.xy.xsql.tsql.core.clause;

import com.xy.xsql.tsql.core.MockParent;
import com.xy.xsql.tsql.core.MockParentBuilder;
import com.xy.xsql.tsql.model.clause.From;
import com.xy.xsql.tsql.model.operator.Operators;
import com.xy.xsql.tsql.model.predicate.Comparison;
import org.junit.Assert;
import org.junit.Test;

import static com.xy.xsql.tsql.core.element.ColumnNameBuilder.c;
import static com.xy.xsql.tsql.core.element.TableNameBuilder.t;

/**
 * Created by xiaoyao9184 on 2017/3/11.
 */
public class FromBuilderTest {


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
                    .$(t("HumanResources","Employee"),"e")
                        .$Cross_Join()
                //TODO
//                            .$(t("HumanResources","Department"),"e")
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
                    .withJoinType(From.JoinType.FULL_OUTER_JOIN)
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

        Assert.assertEquals(tableSource.getJoinType(), From.JoinType.FULL_OUTER_JOIN);

        Assert.assertEquals(tableSource.getTableSource2().getClass(),From.BaseTable.class);
        From.BaseTable tableSource2 = (From.BaseTable) tableSource.getTableSource2();
        Assert.assertEquals(tableSource2.getTableName().toString(),"Sales.SalesOrderDetail");
        Assert.assertEquals(tableSource2.getTableAlias().toString(),"sod");

        Assert.assertEquals(tableSource.getSearchCondition().getPredicate().getClass(),Comparison.class);

    }

}
