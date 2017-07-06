package com.xy.xsql.tsql.core.clause.select;

import com.xy.xsql.tsql.core.MockParent;
import com.xy.xsql.tsql.core.MockParentBuilder;
import com.xy.xsql.tsql.model.clause.select.Over;
import com.xy.xsql.tsql.model.clause.select.Select;
import org.junit.Assert;
import org.junit.Test;

import static com.xy.xsql.tsql.core.element.ColumnNameFactory.c;
import static com.xy.xsql.tsql.core.element.TableNameFactory.t;
import static com.xy.xsql.tsql.core.expression.Expressions.e;
import static com.xy.xsql.tsql.core.expression.Expressions.e_string;

/**
 * Created by xiaoyao9184 on 2017/3/22.
 */
public class SelectBuilderTest {

    /*
    Examples
    See https://docs.microsoft.com/zh-cn/sql/t-sql/queries/select-examples-transact-sql
     */

    // @formatter:off
    //parent+quick
    /**
     * SELECT *
     */
    public Select exampleA1 = new MockParentBuilder<SelectBuilder<MockParent<Select>>,Select>
                (SelectBuilder.class,Select.class)
                .$child()
                    .$()
                    .and()
                .get();
    /**
     * SELECT p.*
     */
    public Select exampleA2 = new MockParentBuilder<SelectBuilder<MockParent<Select>>,Select>
                (SelectBuilder.class,Select.class)
                .$child()
                    .$(t("p"))
                    .and()
                .get();
    // @formatter:on

    @Test
    public void testExampleA(){
        // @formatter:off
        Select select = new SelectBuilder<Void>()
                .withSelectItem()
                    .withAll()
                    .and()
                .build();

        Select select1 = new SelectBuilder<Void>()
                .withSelectItem()
                    .withTableAll(t("p"))
                    .and()
                .build();
        // @formatter:on

        Assert.assertEquals(select.getSelectList().size(),1);
        Assert.assertTrue(select.getSelectList().get(0).isUseAll());

        Assert.assertEquals(select1.getSelectList().size(),1);
        Assert.assertEquals(select1.getSelectList().get(0).getTableViewName().toString(),"p");
        Assert.assertTrue(select1.getSelectList().get(0).isUseTableAll());
    }

    // @formatter:off
    //parent+quick
    /**
     * SELECT Name, ProductNumber, ListPrice AS Price
     */
    public Select exampleA3 = new MockParentBuilder<SelectBuilder<MockParent<Select>>,Select>
                (SelectBuilder.class,Select.class)
                .$child()
                    .$(c("Name"))
                    .$(c("ProductNumber"))
                    .$(c("ListPrice"),"Price")
                    .and()
                .get();
    // @formatter:on

    @Test
    public void testExampleA3(){
        // @formatter:off
        Select select = new SelectBuilder<Void>()
                .withSelectItem()
                    .withColumnName(c("Name"))
                    .and()
                .withSelectItem()
                    .withColumnName(c("ProductNumber"))
                    .and()
                .withSelectItem()
                    .withColumnName(c("ListPrice"))
                    .withColumnAlias("Price")
                    .and()
                .build();
        // @formatter:on

        Assert.assertEquals(select.getSelectList().size(),3);
        Assert.assertEquals(select.getSelectList().get(0).getColumnName().toString(),"Name");
        Assert.assertEquals(select.getSelectList().get(1).getColumnName().toString(),"ProductNumber");
        Assert.assertEquals(select.getSelectList().get(2).getColumnName().toString(),"ListPrice");
        Assert.assertEquals(select.getSelectList().get(2).getColumnAlias().toString(),"Price");
    }


    // @formatter:off
    //parent+quick
    /**
     * SELECT p.Name AS ProductName,
     NonDiscountSales = (OrderQty * UnitPrice),
     Discounts = ((OrderQty * UnitPrice) * UnitPriceDiscount)
     */
    public Select exampleB = new MockParentBuilder<SelectBuilder<MockParent<Select>>,Select>
                (SelectBuilder.class,Select.class)
                .$child()
                    .$(c("p","Name"),"ProductName")
                    .$("NonDiscountSales",e("(OrderQty * UnitPrice)"))
                    .$("Discounts",e("((OrderQty * UnitPrice) * UnitPriceDiscount)"))
                    .and()
                .get();
    // @formatter:on

    @Test
    public void testExampleB(){
        // @formatter:off
        Select select = new SelectBuilder<Void>()
                .withSelectItem()
                    .withColumnName(c("p","Name"))
                    .withColumnAlias("ProductName")
                    .and()
                .withSelectItem()
                    .withColumnAlias("NonDiscountSales")
                    .withExpression(e("(OrderQty * UnitPrice)"))
                    .and()
                .withSelectItem()
                    .withColumnAlias("Discounts")
                    .withExpression(e("((OrderQty * UnitPrice) * UnitPriceDiscount)"))
                    .and()
                .build();
        // @formatter:on

        Assert.assertEquals(select.getSelectList().size(),3);
        Assert.assertEquals(select.getSelectList().get(0).getColumnName().toString(),"p.Name");
        Assert.assertEquals(select.getSelectList().get(1).getColumnName().toString(),"NonDiscountSales");
        Assert.assertEquals(select.getSelectList().get(2).getColumnName().toString(),"Discounts");
    }


    // @formatter:off
    //parent+quick
    /**
     * SELECT 'Total income is', ((OrderQty * UnitPrice) * (1.0 - UnitPriceDiscount)), ' for ',
     p.Name AS ProductName
     */
    public Select exampleB2 = new MockParentBuilder<SelectBuilder<MockParent<Select>>,Select>
                (SelectBuilder.class,Select.class)
                .$child()
                    .$(e_string("Total income is"))
                    .$(e("((OrderQty * UnitPrice) * (1.0 - UnitPriceDiscount))"))
                    .$(e_string(" for "))
                    .$(c("p","Name"),"ProductName")
                    .and()
                .get();
    // @formatter:on

    @Test
    public void testExampleB2(){
        // @formatter:off
        Select select = new SelectBuilder<Void>()
                .withSelectItem()
                    .withExpression(e_string("Total income is"))
                    .and()
                .withSelectItem()
                    .withExpression(e("((OrderQty * UnitPrice) * (1.0 - UnitPriceDiscount))"))
                    .and()
                .withSelectItem()
                    .withExpression(e_string(" for "))
                    .and()
                .withSelectItem()
                    .withColumnName(c("p","Name"))
                    .withColumnAlias("ProductName")
                    .and()
                .build();
        // @formatter:on

        Assert.assertEquals(select.getSelectList().size(),4);
        Assert.assertNotNull(select.getSelectList().get(0).getExpression());
        Assert.assertNotNull(select.getSelectList().get(1).getExpression());
        Assert.assertNotNull(select.getSelectList().get(2).getExpression());
        Assert.assertEquals(select.getSelectList().get(3).getColumnName().toString(),"p.Name");
    }


    // @formatter:off
    //parent+quick
    /**
     * SELECT DISTINCT JobTitle
     */
    public Select exampleC = new MockParentBuilder<SelectBuilder<MockParent<Select>>,Select>
                (SelectBuilder.class,Select.class)
                .$child()
                    .$Distinct()
                    .$(c("JobTitle"))
                    .and()
                .get();
    // @formatter:on

    @Test
    public void testExampleC(){
        // @formatter:off
        Select select = new SelectBuilder<Void>()
                .withDistinct()
                .withSelectItem()
                    .withColumnName(c("JobTitle"))
                    .and()
                .build();
        // @formatter:on

        Assert.assertEquals(select.getSelectList().size(),1);
        Assert.assertTrue(select.isUseDistinct());
        Assert.assertEquals(select.getSelectList().get(0).getColumnName().toString(),"JobTitle");
    }


    // @formatter:off
    //parent+quick
    /**
     * SELECT DISTINCT p.LastName, p.FirstName
     */
    public Select exampleE2 = new MockParentBuilder<SelectBuilder<MockParent<Select>>,Select>
                (SelectBuilder.class,Select.class)
                .$child()
                    .$Distinct()
                    .$(c("p","LastName"))
                    .$(c("p","FirstName"))
                    .and()
                .get();
    // @formatter:on

    @Test
    public void testExampleE2(){
        // @formatter:off
        Select select = new SelectBuilder<Void>()
                .withDistinct()
                .withSelectItem()
                    .withColumnName(c("p","LastName"))
                    .and()
                .withSelectItem()
                    .withColumnName(c("p","FirstName"))
                    .and()
                .build();
        // @formatter:on

        Assert.assertEquals(select.getSelectList().size(),2);
        Assert.assertTrue(select.isUseDistinct());
        Assert.assertEquals(select.getSelectList().get(0).getColumnName().toString(),"p.LastName");
        Assert.assertEquals(select.getSelectList().get(1).getColumnName().toString(),"p.FirstName");
    }


    // @formatter:off
    //parent+quick
    /**
     * SELECT SalesOrderID, SUM(LineTotal) AS SubTotal
     */
    public Select exampleF = new MockParentBuilder<SelectBuilder<MockParent<Select>>,Select>
                (SelectBuilder.class,Select.class)
                .$child()
                    .$(c("SalesOrderID"))
                    .$(e("SUM(LineTotal)"),"SubTotal")
                    .and()
                .get();
    // @formatter:on

    @Test
    public void testExampleF(){
        // @formatter:off
        Select select = new SelectBuilder<Void>()
                .withSelectItem()
                    .withColumnName(c("SalesOrderID"))
                    .and()
                .withSelectItem()
                    .withExpression(e("SUM(LineTotal)"))
                    .withColumnAlias("SubTotal")
                    .and()
                .build();
        // @formatter:on

        Assert.assertEquals(select.getSelectList().size(),2);
        Assert.assertEquals(select.getSelectList().get(0).getColumnName().toString(),"SalesOrderID");
        Assert.assertNotNull(select.getSelectList().get(1).getExpression());
        Assert.assertEquals(select.getSelectList().get(1).getColumnAlias().toString(),"SubTotal");
    }


    // @formatter:off
    //parent+quick
    /**
     * SELECT ProductID, SpecialOfferID, AVG(UnitPrice) AS [Average Price],
     SUM(LineTotal) AS SubTotal
     */
    public Select exampleG = new MockParentBuilder<SelectBuilder<MockParent<Select>>,Select>
                (SelectBuilder.class,Select.class)
                .$child()
                    .$(c("ProductID"))
                    .$(c("SpecialOfferID"))
                    .$(e("AVG(UnitPrice)"),"[Average Price]")
                    .$(e("SUM(LineTotal)"),"SubTotal")
                    .and()
                .get();
    // @formatter:on

    @Test
    public void testExampleG(){
        // @formatter:off
        Select select = new SelectBuilder<Void>()
                .withSelectItem()
                    .withColumnName(c("ProductID"))
                    .and()
                .withSelectItem()
                    .withColumnName(c("SpecialOfferID"))
                    .and()
                .withSelectItem()
                    .withExpression(e("AVG(UnitPrice)"))
                    .withColumnAlias("[Average Price]")
                    .and()
                .withSelectItem()
                    .withExpression(e("SUM(LineTotal)"))
                    .withColumnAlias("SubTotal")
                    .and()
                .build();
        // @formatter:on

        Assert.assertEquals(select.getSelectList().size(),4);
        Assert.assertEquals(select.getSelectList().get(0).getColumnName().toString(),"ProductID");
        Assert.assertEquals(select.getSelectList().get(1).getColumnName().toString(),"SpecialOfferID");
        Assert.assertNotNull(select.getSelectList().get(2).getExpression());
        Assert.assertEquals(select.getSelectList().get(2).getColumnAlias().toString(),"[Average Price]");
        Assert.assertNotNull(select.getSelectList().get(3).getExpression());
        Assert.assertEquals(select.getSelectList().get(3).getColumnAlias().toString(),"SubTotal");
    }


    // @formatter:off
    //parent+quick
    /**
     * SELECT ProductModelID, AVG(ListPrice) AS [Average List Price]
     */
    public Select exampleH = new MockParentBuilder<SelectBuilder<MockParent<Select>>,Select>
                (SelectBuilder.class,Select.class)
                .$child()
                    .$(c("ProductModelID"))
                    .$(e(" AVG(ListPrice)"),"[Average List Price]")
                    .and()
                .get();
    // @formatter:on

    @Test
    public void testExampleH(){
        // @formatter:off
        Select select = new SelectBuilder<Void>()
                .withSelectItem()
                    .withColumnName(c("ProductModelID"))
                    .and()
                .withSelectItem()
                    .withExpression(e(" AVG(ListPrice)"))
                    .withColumnAlias("[Average List Price]")
                    .and()
                .build();
        // @formatter:on

        Assert.assertEquals(select.getSelectList().size(),2);
        Assert.assertEquals(select.getSelectList().get(0).getColumnName().toString(),"ProductModelID");
        Assert.assertNotNull(select.getSelectList().get(1).getExpression());
        Assert.assertEquals(select.getSelectList().get(1).getColumnAlias().toString(),"[Average List Price]");
    }


    // @formatter:off
    //parent+quick
    /**
     * SELECT AVG(OrderQty) AS [Average Quantity],
     NonDiscountSales = (OrderQty * UnitPrice)
     */
    public Select exampleI = new MockParentBuilder<SelectBuilder<MockParent<Select>>,Select>
                (SelectBuilder.class,Select.class)
                .$child()
                    .$(e("AVG(OrderQty)"),"[Average Quantity]")
                    .$("NonDiscountSales",e("(OrderQty * UnitPrice)"))
                    .and()
                .get();
    // @formatter:on

    @Test
    public void testExampleI(){
        // @formatter:off
        Select select = new SelectBuilder<Void>()
                .withSelectItem()
                    .withExpression(e("AVG(OrderQty)"))
                    .withColumnAlias("[Average Quantity]")
                    .and()
                .withSelectItem()
                    .withColumnAlias("NonDiscountSales")
                    .withEQ()
                    .withExpression(e("(OrderQty * UnitPrice)"))
                    .and()
                .build();
        // @formatter:on

        Assert.assertEquals(select.getSelectList().size(),2);
        Assert.assertNotNull(select.getSelectList().get(0).getExpression());
        Assert.assertEquals(select.getSelectList().get(0).getColumnAlias().toString(),"[Average Quantity]");
        Assert.assertEquals(select.getSelectList().get(1).getColumnName().toString(),"NonDiscountSales");
        Assert.assertNotNull(select.getSelectList().get(1).getExpression());
    }

}
