package com.xy.xsql.orm.core.sql.clause;

import com.xy.xsql.orm.core.sql.statements.SelectBuilder;
import com.xy.xsql.orm.data.sql.clause.With;
import com.xy.xsql.orm.data.sql.statements.dml.Select;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by xiaoyao9184 on 2017/3/11.
 */
public class WithBuilderTest {

    /**
     * @link https://msdn.microsoft.com/en-us/library/ms175972.aspx#Examples
     WITH Sales_CTE (SalesPersonID, SalesOrderID, SalesYear)
     AS
     -- Define the CTE query.
     (
     SELECT SalesPersonID, SalesOrderID, YEAR(OrderDate) AS SalesYear
     FROM Sales.SalesOrderHeader
     WHERE SalesPersonID IS NOT NULL
     )
     -- Define the outer query referencing the CTE name.
     SELECT SalesPersonID, COUNT(SalesOrderID) AS TotalSales, SalesYear
     FROM Sales_CTE
     GROUP BY SalesYear, SalesPersonID
     ORDER BY SalesPersonID, SalesYear;
     GO
     */
    @Test
    public void testWith(){
        Select select = new SelectBuilder()
                .withSelectList()
                    .withSelectItem().withColumnName("SalesPersonID").out()
                    .withSelectItem().withColumnName("SalesOrderID").out()
                    .withSelectItem().withColumnName("SalesPersonID").out()
                .out()
                .withFrom()
                    .withTableSource().withTable("Sales.SalesOrderHeader").out().out()
                .withWhere()
                    .withSearchCondition()
                        .withPredicate().IsNull().withNot(true)
                        .out().out().out()
                .build(null);




        With with = new WithBuilder<Void>()
                .withItem()
                    .withExpressionName("Sales_CTE")
                    .withColumnName("SalesPersonID")
                    .withColumnName("SalesOrderID")
                    .withColumnName("SalesYear")
                    .withCteQueryDefinition(select)
                    .and()
                .build();

        Assert.assertEquals(with.getCommonTableExpressionList().size(),1);
    }

}
