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
     */
    @Test
    public void testExampleC(){
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
