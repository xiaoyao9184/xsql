package com.xy.xsql.tsql.style.constructor.introducer.queries.select;

import com.xy.xsql.tsql.model.queries.select.Select;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.datatypes.table.ColumnNameFactory.c;
import static com.xy.xsql.tsql.builder.chain.datatypes.table.TableNameFactory.t;
import static com.xy.xsql.tsql.builder.chain.elements.expressions.Expressions.e;
import static com.xy.xsql.tsql.builder.chain.elements.expressions.Expressions.e_string;
import static com.xy.xsql.tsql.style.constructor.introducer.queries.select.select.AS;
import static com.xy.xsql.tsql.style.constructor.introducer.queries.select.select.DISTINCT;
import static com.xy.xsql.tsql.style.constructor.introducer.queries.select.select.SELECT;
import static org.junit.Assert.*;

/**
 * Created by xiaoyao9184 on 2018/5/10.
 */
public class selectTest {

    /*
    Examples
    See https://docs.microsoft.com/zh-cn/sql/t-sql/queries/select-examples-transact-sql
     */

    // @formatter:off
    //parent+quick
    /**
     * SELECT *
     */
    public Select exampleA1 = SELECT(

    ).build();
    /**
     * SELECT p.*
     */
    public Select exampleA2 = SELECT(
            t("p")
    ).build();
    // @formatter:on

    @Test
    public void testExampleA(){
        assertEquals(exampleA1.getSelectList().size(),1);
        assertTrue(exampleA1.getSelectList().get(0).isUseAll());

        assertEquals(exampleA2.getSelectList().size(),1);
        assertEquals(exampleA2.getSelectList().get(0).getTableViewName().toString(),"p");
        assertTrue(exampleA2.getSelectList().get(0).isUseTableAll());
    }

    // @formatter:off
    //parent+quick
    /**
     * SELECT Name, ProductNumber, ListPrice AS Price
     */
    public Select exampleA3 = SELECT(
            c("Name")
    ).$$(
            c("ProductNumber")
    )
    .$$(
            c("ListPrice"),AS("Price")
    ).build();
    // @formatter:on

    @Test
    public void testExampleA3(){
        assertEquals(exampleA3.getSelectList().size(),3);
        assertEquals(exampleA3.getSelectList().get(0).getColumnName().toString(),"Name");
        assertEquals(exampleA3.getSelectList().get(1).getColumnName().toString(),"ProductNumber");
        assertEquals(exampleA3.getSelectList().get(2).getColumnName().toString(),"ListPrice");
        assertEquals(exampleA3.getSelectList().get(2).getColumnAlias().toString(),"Price");
    }


    // @formatter:off
    //parent+quick
    /**
     * SELECT p.Name AS ProductName,
     NonDiscountSales = (OrderQty * UnitPrice),
     Discounts = ((OrderQty * UnitPrice) * UnitPriceDiscount)
     */
    public Select exampleB = SELECT(
            c("p","Name"),AS("ProductName")
    ).$$(
            "NonDiscountSales",e("(OrderQty * UnitPrice)")
    ).$$(
            "Discounts",e("((OrderQty * UnitPrice) * UnitPriceDiscount)")
    ).build();
    // @formatter:on

    @Test
    public void testExampleB(){
        assertEquals(exampleB.getSelectList().size(),3);
        assertEquals(exampleB.getSelectList().get(0).getColumnName().toString(),"p.Name");
        assertEquals(exampleB.getSelectList().get(1).getColumnAlias().toString(),"NonDiscountSales");
        assertEquals(exampleB.getSelectList().get(2).getColumnAlias().toString(),"Discounts");
    }


    // @formatter:off
    //parent+quick
    /**
     * SELECT 'Total income is', ((OrderQty * UnitPrice) * (1.0 - UnitPriceDiscount)), ' for ',
     p.Name AS ProductName
     */
    public Select exampleB2 = SELECT(
            e_string("Total income is")
    ).$$(
            e("((OrderQty * UnitPrice) * (1.0 - UnitPriceDiscount))")
    ).$$(
            e_string(" for ")
    ).$$(
            c("p","Name"),AS("ProductName")
    ).build();
    // @formatter:on

    @Test
    public void testExampleB2(){
        assertEquals(exampleB2.getSelectList().size(),4);
        assertNotNull(exampleB2.getSelectList().get(0).getExpression());
        assertNotNull(exampleB2.getSelectList().get(1).getExpression());
        assertNotNull(exampleB2.getSelectList().get(2).getExpression());
        assertEquals(exampleB2.getSelectList().get(3).getColumnName().toString(),"p.Name");
    }


    // @formatter:off
    //parent+quick
    /**
     * SELECT DISTINCT JobTitle
     */
    public Select exampleC = SELECT(
            DISTINCT(),
            c("JobTitle")
    ).build();
    // @formatter:on

    @Test
    public void testExampleC(){
        assertEquals(exampleC.getSelectList().size(),1);
        assertTrue(exampleC.isUseDistinct());
        assertEquals(exampleC.getSelectList().get(0).getColumnName().toString(),"JobTitle");
    }


    // @formatter:off
    //parent+quick
    /**
     * SELECT DISTINCT p.LastName, p.FirstName
     */
    public Select exampleE2 = SELECT(
            DISTINCT(),
            c("p","LastName")
    ).$$(
            c("p","FirstName")
    ).build();
    // @formatter:on

    @Test
    public void testExampleE2(){
        assertEquals(exampleE2.getSelectList().size(),2);
        assertTrue(exampleE2.isUseDistinct());
        assertEquals(exampleE2.getSelectList().get(0).getColumnName().toString(),"p.LastName");
        assertEquals(exampleE2.getSelectList().get(1).getColumnName().toString(),"p.FirstName");
    }


    // @formatter:off
    //parent+quick
    /**
     * SELECT SalesOrderID, SUM(LineTotal) AS SubTotal
     */
    public Select exampleF = SELECT(
            c("SalesOrderID")
    ).$$(
            e("SUM(LineTotal)"),AS("SubTotal")
    ).build();
    // @formatter:on

    @Test
    public void testExampleF(){
        assertEquals(exampleF.getSelectList().size(),2);
        assertEquals(exampleF.getSelectList().get(0).getColumnName().toString(),"SalesOrderID");
        assertNotNull(exampleF.getSelectList().get(1).getExpression());
        assertEquals(exampleF.getSelectList().get(1).getColumnAlias().toString(),"SubTotal");
    }


    // @formatter:off
    //parent+quick
    /**
     * SELECT ProductID, SpecialOfferID, AVG(UnitPrice) AS [Average Price],
     SUM(LineTotal) AS SubTotal
     */
    public Select exampleG = SELECT(
            c("ProductID")
    ).$$(
            c("SpecialOfferID")
    ).$$(
            e("AVG(UnitPrice)"),AS("[Average Price]")
    ).$$(
            e("SUM(LineTotal)"),AS("SubTotal")
    ).build();
    // @formatter:on

    @Test
    public void testExampleG(){
        assertEquals(exampleG.getSelectList().size(),4);
        assertEquals(exampleG.getSelectList().get(0).getColumnName().toString(),"ProductID");
        assertEquals(exampleG.getSelectList().get(1).getColumnName().toString(),"SpecialOfferID");
        assertNotNull(exampleG.getSelectList().get(2).getExpression());
        assertEquals(exampleG.getSelectList().get(2).getColumnAlias().toString(),"[Average Price]");
        assertNotNull(exampleG.getSelectList().get(3).getExpression());
        assertEquals(exampleG.getSelectList().get(3).getColumnAlias().toString(),"SubTotal");
    }


    // @formatter:off
    //parent+quick
    /**
     * SELECT ProductModelID, AVG(ListPrice) AS [Average List Price]
     */
    public Select exampleH = SELECT(
            c("ProductModelID")
    ).$$(
            e(" AVG(ListPrice)"),AS("[Average List Price]")
    ).build();
    // @formatter:on

    @Test
    public void testExampleH(){
        assertEquals(exampleH.getSelectList().size(),2);
        assertEquals(exampleH.getSelectList().get(0).getColumnName().toString(),"ProductModelID");
        assertNotNull(exampleH.getSelectList().get(1).getExpression());
        assertEquals(exampleH.getSelectList().get(1).getColumnAlias().toString(),"[Average List Price]");
    }


    // @formatter:off
    //parent+quick
    /**
     * SELECT AVG(OrderQty) AS [Average Quantity],
     NonDiscountSales = (OrderQty * UnitPrice)
     */
    public Select exampleI = SELECT(
            e("AVG(OrderQty)"),AS("[Average Quantity]")
    ).$$(
            "NonDiscountSales",e("(OrderQty * UnitPrice)")
    ).build();
    // @formatter:on

    @Test
    public void testExampleI(){
        assertEquals(exampleI.getSelectList().size(),2);
        assertNotNull(exampleI.getSelectList().get(0).getExpression());
        assertEquals(exampleI.getSelectList().get(0).getColumnAlias().toString(),"[Average Quantity]");
        assertEquals(exampleI.getSelectList().get(1).getColumnAlias().toString(),"NonDiscountSales");
        assertNotNull(exampleI.getSelectList().get(1).getExpression());
    }

}