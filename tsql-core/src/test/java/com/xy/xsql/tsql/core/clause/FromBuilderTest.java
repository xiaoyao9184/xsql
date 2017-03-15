package com.xy.xsql.tsql.core.clause;

import com.xy.xsql.tsql.model.clause.From;
import org.junit.Assert;
import org.junit.Test;

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
                .withTableSource()
                    .withTableName(t("Sales","SalesTerritory"))
                    .and()
                .build();
        // @formatter:on

        Assert.assertEquals(option.getTableSourceList().size(),1);
        Assert.assertEquals(option.getTableSourceList().get(0).getTableName().toString(),"Sales.SalesTerritory");

    }

    /**
     * FROM HumanResources.Employee WITH (TABLOCK, HOLDLOCK) ;
     */
    @Test
    public void testExampleB(){
        // @formatter:off
        From option = new FromBuilder<From>()
                .withTableSource()
                    .withTableName(t("HumanResources","Employee"))
                    .and()
                .build();
        // @formatter:on

        Assert.assertEquals(option.getTableSourceList().size(),1);
        Assert.assertEquals(option.getTableSourceList().get(0).getTableName().toString(),"HumanResources.Employee");

    }

    /**
     * FROM HumanResources.Employee AS e
     CROSS JOIN HumanResources.Department AS d
     */
    @Test
    public void testExampleC(){
        // @formatter:off
        From from = new FromBuilder<From>()
                .withTableSource()
                    .withJoinedTable()
                        .withCrossJoin()
                        .withTableSource()
                            .withTableName(t("HumanResources","Employee"))
                            .withTableAlias("e")
                            .and()
                        .withTableSource2()
                            .withTableName(t("HumanResources","Department"))
                            .withTableAlias("d")
                            .and()
                        .and()
                    .and()
                .build();
        // @formatter:on

        Assert.assertEquals(from.getTableSourceList().size(),1);

        From.TableSource ts = from.getTableSourceList().get(0);
        Assert.assertEquals(ts.getJoinedTable().isUseCrossJoin(),true);
        Assert.assertEquals(ts.getJoinedTable().getTableSource().getTableName().toString(),"HumanResources.Employee");
        Assert.assertEquals(ts.getJoinedTable().getTableSource().getTableAlias().toString(),"e");
        Assert.assertEquals(ts.getJoinedTable().getTableSource2().getTableName().toString(),"HumanResources.Department");
        Assert.assertEquals(ts.getJoinedTable().getTableSource2().getTableAlias().toString(),"d");

    }


    /**
     * FROM Production.Product AS p
     FULL OUTER JOIN Sales.SalesOrderDetail AS sod
     ON p.ProductID = sod.ProductID
     */
    @Test
    public void testExampleD(){
        // @formatter:off
        From option = new FromBuilder<From>()
                .withTableSource()
                    .withTableName(t("HumanResources","Employee"))
                    .withTableAlias("e")
                    .and()
                .build();
        // @formatter:on

        Assert.assertEquals(option.getTableSourceList().size(),1);
        Assert.assertEquals(option.getTableSourceList().get(0).getTableName().toString(),"HumanResources.Employee");
        Assert.assertEquals(option.getTableSourceList().get(0).getTableAlias().toString(),"e");

    }

}
