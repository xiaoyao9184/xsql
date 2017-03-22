package com.xy.xsql.tsql.core.clause.select;

import com.xy.xsql.tsql.core.MockParent;
import com.xy.xsql.tsql.core.MockParentBuilder;
import com.xy.xsql.tsql.model.clause.select.Select;
import org.junit.Assert;
import org.junit.Test;

import static com.xy.xsql.tsql.core.element.ColumnNameBuilder.c;
import static com.xy.xsql.tsql.core.element.TableNameBuilder.t;
import static com.xy.xsql.tsql.core.expression.ExpressionBuilder.e;
import static com.xy.xsql.tsql.core.expression.ExpressionBuilder.e_string;

/**
 * Created by xiaoyao9184 on 2017/3/22.
 */
public class SelectBuilderTest {


    /**
     * SELECT *
     SELECT p.*
     */
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

        //parent+quick
        MockParent<Select> parent = new MockParentBuilder<SelectBuilder<MockParent<Select>>,Select>
                (SelectBuilder.class,Select.class)
                .$child()
                    .$()
                    .and();
        
        MockParent<Select> parent1 = new MockParentBuilder<SelectBuilder<MockParent<Select>>,Select>
                (SelectBuilder.class,Select.class)
                .$child()
                    .$(t("p"))
                    .and();
        // @formatter:on

        Assert.assertEquals(select.getList().size(),1);
        Assert.assertTrue(select.getList().get(0).isUseAll());

        Assert.assertEquals(select1.getList().size(),1);
        Assert.assertEquals(select1.getList().get(0).getTableViewName().toString(),"p");
        Assert.assertTrue(select1.getList().get(0).isUseTableAll());
    }

    /**
     * SELECT Name, ProductNumber, ListPrice AS Price
     */
    @Test
    public void testExampleA1(){
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

        //parent+quick
        MockParent<Select> parent = new MockParentBuilder<SelectBuilder<MockParent<Select>>,Select>
                (SelectBuilder.class,Select.class)
                .$child()
                    .$(c("Name"))
                    .$(c("ProductNumber"))
                    .$(c("ListPrice"),"Price")
                    .and();
        // @formatter:on

        Assert.assertEquals(select.getList().size(),3);
        Assert.assertEquals(select.getList().get(0).getColumnName().toString(),"Name");
        Assert.assertEquals(select.getList().get(1).getColumnName().toString(),"ProductNumber");
        Assert.assertEquals(select.getList().get(2).getColumnName().toString(),"ListPrice");
        Assert.assertEquals(select.getList().get(2).getColumnAlias().toString(),"Price");
    }

    /**
     * SELECT p.Name AS ProductName,
     NonDiscountSales = (OrderQty * UnitPrice),
     Discounts = ((OrderQty * UnitPrice) * UnitPriceDiscount)
     */
    @Test
    public void testExampleB(){
        // @formatter:off
        Select select = new SelectBuilder<Void>()
                .withSelectItem()
                    .withColumnName(c("p","Name"))
                    .withColumnAlias("ProductName")
                    .and()
                .withSelectItem()
                    .withColumnName(c("NonDiscountSales"))
                    .withExpression(e("(OrderQty * UnitPrice)"))
                    .and()
                .withSelectItem()
                    .withColumnName(c("Discounts"))
                    .withExpression(e("((OrderQty * UnitPrice) * UnitPriceDiscount)"))
                    .and()
                .build();

        //parent+quick
        MockParent<Select> parent = new MockParentBuilder<SelectBuilder<MockParent<Select>>,Select>
                (SelectBuilder.class,Select.class)
                .$child()
                    .$(c("p","Name"),"ProductName")
                    .$(c("NonDiscountSales"),e("(OrderQty * UnitPrice)"))
                    .$(c("Discounts"),e("((OrderQty * UnitPrice) * UnitPriceDiscount)"))
                    .and();
        // @formatter:on

        Assert.assertEquals(select.getList().size(),3);
        Assert.assertEquals(select.getList().get(0).getColumnName().toString(),"p.Name");
        Assert.assertEquals(select.getList().get(1).getColumnName().toString(),"NonDiscountSales");
        Assert.assertEquals(select.getList().get(2).getColumnName().toString(),"Discounts");
    }

    /**
     * SELECT 'Total income is', ((OrderQty * UnitPrice) * (1.0 - UnitPriceDiscount)), ' for ',
     p.Name AS ProductName
     */
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

        //parent+quick
        MockParent<Select> parent = new MockParentBuilder<SelectBuilder<MockParent<Select>>,Select>
                (SelectBuilder.class,Select.class)
                .$child()
                    .$(e_string("Total income is"))
                    .$(e("((OrderQty * UnitPrice) * (1.0 - UnitPriceDiscount))"))
                    .$(e_string(" for "))
                    .$(c("p","Name"),"ProductName")
                    .and();
        // @formatter:on

        Assert.assertEquals(select.getList().size(),4);
        Assert.assertNotNull(select.getList().get(0).getExpression());
        Assert.assertNotNull(select.getList().get(1).getExpression());
        Assert.assertNotNull(select.getList().get(2).getExpression());
        Assert.assertEquals(select.getList().get(3).getColumnName().toString(),"p.Name");
    }

    /**
     * SELECT DISTINCT JobTitle
     */
    @Test
    public void testExampleC(){
        // @formatter:off
        Select select = new SelectBuilder<Void>()
                .withDistinct()
                .withSelectItem()
                    .withColumnName(c("JobTitle"))
                    .and()
                .build();

        //parent+quick
        MockParent<Select> parent = new MockParentBuilder<SelectBuilder<MockParent<Select>>,Select>
                (SelectBuilder.class,Select.class)
                .$child()
                    .$Distinct()
                    .$(c("JobTitle"))
                    .and();
        // @formatter:on

        Assert.assertEquals(select.getList().size(),1);
        Assert.assertTrue(select.isUseDistinct());
        Assert.assertEquals(select.getList().get(0).getColumnName().toString(),"JobTitle");
    }

    /**
     * SELECT DISTINCT p.LastName, p.FirstName
     */
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

        //parent+quick
        MockParent<Select> parent = new MockParentBuilder<SelectBuilder<MockParent<Select>>,Select>
                (SelectBuilder.class,Select.class)
                .$child()
                    .$Distinct()
                    .$(c("p","LastName"))
                    .$(c("p","FirstName"))
                    .and();
        // @formatter:on

        Assert.assertEquals(select.getList().size(),2);
        Assert.assertTrue(select.isUseDistinct());
        Assert.assertEquals(select.getList().get(0).getColumnName().toString(),"p.LastName");
        Assert.assertEquals(select.getList().get(1).getColumnName().toString(),"p.FirstName");
    }

    /**
     * SELECT SalesOrderID, SUM(LineTotal) AS SubTotal
     */
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

        //parent+quick
        MockParent<Select> parent = new MockParentBuilder<SelectBuilder<MockParent<Select>>,Select>
                (SelectBuilder.class,Select.class)
                .$child()
                    .$(c("SalesOrderID"))
                    .$(e("SUM(LineTotal)"),"SubTotal")
                    .and();
        // @formatter:on

        Assert.assertEquals(select.getList().size(),2);
        Assert.assertEquals(select.getList().get(0).getColumnName().toString(),"SalesOrderID");
        Assert.assertNotNull(select.getList().get(1).getExpression());
        Assert.assertEquals(select.getList().get(1).getColumnAlias().toString(),"SubTotal");
    }

    /**
     * SELECT ProductID, SpecialOfferID, AVG(UnitPrice) AS [Average Price],
     SUM(LineTotal) AS SubTotal
     */
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

        //parent+quick
        MockParent<Select> parent = new MockParentBuilder<SelectBuilder<MockParent<Select>>,Select>
                (SelectBuilder.class,Select.class)
                .$child()
                    .$(c("ProductID"))
                    .$(c("SpecialOfferID"))
                    .$(e("AVG(UnitPrice)"),"[Average Price]")
                    .$(e("SUM(LineTotal)"),"SubTotal")
                    .and();
        // @formatter:on

        Assert.assertEquals(select.getList().size(),4);
        Assert.assertEquals(select.getList().get(0).getColumnName().toString(),"ProductID");
        Assert.assertEquals(select.getList().get(1).getColumnName().toString(),"SpecialOfferID");
        Assert.assertNotNull(select.getList().get(2).getExpression());
        Assert.assertEquals(select.getList().get(2).getColumnAlias().toString(),"[Average Price]");
        Assert.assertNotNull(select.getList().get(3).getExpression());
        Assert.assertEquals(select.getList().get(3).getColumnAlias().toString(),"SubTotal");
    }

    /**
     * SELECT ProductModelID, AVG(ListPrice) AS [Average List Price]
     */
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

        //parent+quick
        MockParent<Select> parent = new MockParentBuilder<SelectBuilder<MockParent<Select>>,Select>
                (SelectBuilder.class,Select.class)
                .$child()
                    .$(c("ProductModelID"))
                    .$(e(" AVG(ListPrice)"),"[Average List Price]")
                    .and();
        // @formatter:on

        Assert.assertEquals(select.getList().size(),2);
        Assert.assertEquals(select.getList().get(0).getColumnName().toString(),"ProductModelID");
        Assert.assertNotNull(select.getList().get(1).getExpression());
        Assert.assertEquals(select.getList().get(1).getColumnAlias().toString(),"[Average List Price]");
    }

    /**
     * SELECT AVG(OrderQty) AS [Average Quantity],
     NonDiscountSales = (OrderQty * UnitPrice)
     */
    @Test
    public void testExampleI(){
        // @formatter:off
        Select select = new SelectBuilder<Void>()
                .withSelectItem()
                    .withExpression(e("AVG(OrderQty)"))
                    .withColumnAlias("[Average Quantity]")
                    .and()
                .withSelectItem()
                    .withColumnName(c("NonDiscountSales"))
                    .withExpression(e("(OrderQty * UnitPrice)"))
                    .and()
                .build();

        //parent+quick
        MockParent<Select> parent = new MockParentBuilder<SelectBuilder<MockParent<Select>>,Select>
                (SelectBuilder.class,Select.class)
                .$child()
                    .$(e("AVG(OrderQty)"),"[Average Quantity]")
                    .$(c("NonDiscountSales"),e("(OrderQty * UnitPrice)"))
                    .and();
        // @formatter:on

        Assert.assertEquals(select.getList().size(),2);
        Assert.assertNotNull(select.getList().get(0).getExpression());
        Assert.assertEquals(select.getList().get(0).getColumnAlias().toString(),"[Average Quantity]");
        Assert.assertEquals(select.getList().get(1).getColumnName().toString(),"NonDiscountSales");
        Assert.assertNotNull(select.getList().get(1).getExpression());
    }
}
