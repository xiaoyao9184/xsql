package com.xy.xsql.tsql.core.clause;

import com.xy.xsql.tsql.core.statement.SelectBuilder;
import com.xy.xsql.tsql.model.clause.Where;
import com.xy.xsql.tsql.model.clause.With;
import com.xy.xsql.tsql.model.operator.Operators;
import com.xy.xsql.tsql.model.statement.dml.Select;
import org.junit.Assert;
import org.junit.Test;

import static com.xy.xsql.tsql.core.expression.ExpressionBuilder.e;
import static com.xy.xsql.tsql.core.expression.ExpressionBuilder.e_string;

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
                .withQuery()
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
                .out()
                .build(null);




        With with = new WithBuilder<Void>()
                .withItem()
                    .withExpressionName("Sales_CTE")
                    .withColumnName("SalesPersonID")
                    .withColumnName("SalesOrderID")
                    .withColumnName("SalesYear")
//                    .withCteQueryDefinition(select)
                    .and()
                .build();

        Assert.assertEquals(with.getCommonTableExpressionList().size(),1);
    }

    /**
     * WHERE Name = 'Blade' ;
     */
    @Test
    public void testExampleA(){
        // @formatter:off
        Where where = new WhereBuilder<Void>()
                .withSearchCondition()
                    .withPredicate()
                        .Operator()
                            .withExpression(e_string("Name"))
                            .withOperator(Operators.EQUAL)
                            .withExpression(e("Blade"))
                            .out()
                        .out()
                    .build();
        // @formatter:on
    }

    /**
     * WHERE Name LIKE ('%Frame%');
     */
    @Test
    public void testExampleB(){
        // @formatter:off
        Where where = new WhereBuilder<Void>()
                .withSearchCondition()
                    .withPredicate()
                        .Operator()
                            .withExpression(e("Name"))
                            .withOperator(Operators.LIKE)
                            .withExpression(e_string("%Frame%"))
                            .out()
                        .out()
                    .build();
        // @formatter:on
    }

    /**
     * WHERE ProductID <= 12 ;
     */
    @Test
    public void testExampleC(){
        // @formatter:off
        Where where = new WhereBuilder<Void>()
                .withSearchCondition()
                    .withPredicate()
                        .Operator()
                            .withExpression(e_string("ProductID"))
                            .withOperator(Operators.LESS_EQUAL)
                            .withExpression(e(12))
                            .out()
                        .out()
                    .build();
        // @formatter:on
    }

    /**
     * WHERE ProductID = 2
     OR ProductID = 4
     OR Name = 'c' ;
     */
    @Test
    public void testExampleD(){
        // @formatter:off
        Where where = new WhereBuilder<Void>()
                .withSearchCondition()
                    .withPredicate().Operator()
                            .withExpression(e("ProductID"))
                            .withOperator(Operators.EQUAL)
                            .withExpression(e(2))
                            .out()
                    .withAndOrNotItem()
                        .withOr()
                        .withPredicate()
                            .Operator()
                                .withExpression(e("ProductID"))
                                .withOperator(Operators.EQUAL)
                                .withExpression(e(4))
                                .out()
                        .out()
                    .withAndOrNotItem()
                        .withOr()
                        .withPredicate()
                            .Operator()
                                .withExpression(e("Name"))
                                .withOperator(Operators.EQUAL)
                                .withExpression(e_string("c"))
                                .out()
                        .out()
                    .out()
                .build();
        // @formatter:on
    }

}
