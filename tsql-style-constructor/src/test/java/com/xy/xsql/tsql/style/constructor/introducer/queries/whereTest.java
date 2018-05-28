package com.xy.xsql.tsql.style.constructor.introducer.queries;

import com.xy.xsql.tsql.model.datatypes.constants.NumberConstant;
import com.xy.xsql.tsql.model.datatypes.constants.StringConstant;
import com.xy.xsql.tsql.model.elements.expressions.Expression;
import com.xy.xsql.tsql.model.queries.SearchCondition;
import com.xy.xsql.tsql.model.queries.Where;
import com.xy.xsql.tsql.model.queries.predicates.Between;
import com.xy.xsql.tsql.model.queries.predicates.Comparison;
import com.xy.xsql.tsql.model.queries.predicates.In;
import com.xy.xsql.tsql.model.queries.predicates.Like;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.elements.expressions.Expressions.*;
import static com.xy.xsql.tsql.builder.chain.queries.predicates.Predicates.*;
import static com.xy.xsql.tsql.style.constructor.introducer.queries.where.WHERE;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by xiaoyao9184 on 2018/5/9.
 */
public class whereTest {

    /*
    Examples
    See https://docs.microsoft.com/zh-cn/sql/t-sql/queries/where-transact-sql#examples
     */

    // @formatter:off
    //parent+quick
    /**
     * WHERE Name = 'Blade'
     */
    public Where exampleA = WHERE(
            p_equal(
                    e("Name"),
                    e_string("Blade")
            )
    ).build();
    // @formatter:on

    @Test
    public void testExampleA(){
        assertEquals(exampleA.getSearchCondition().getPredicate().getClass(), Comparison.class);
        Comparison predicate = (Comparison) exampleA.getSearchCondition().getPredicate();
        assertEquals(predicate.getExpression().toString(), "Name");
        assertTrue(predicate.getOperatorExpression() instanceof StringConstant);
        assertEquals(((StringConstant)predicate.getOperatorExpression()).getString(), "Blade");
    }


    // @formatter:off
    //parent+quick
    /**
     * WHERE Name LIKE ('%Frame%')
     */
    public Where exampleB = WHERE(
            p_like(
                    e("Name"),
                    e_string("%Frame%")
            )
    ).build();
    // @formatter:on

    @Test
    public void testExampleB(){
        assertEquals(exampleB.getSearchCondition().getPredicate().getClass(), Like.class);
        Like predicate = (Like) exampleB.getSearchCondition().getPredicate();
        assertEquals(predicate.getExpression().toString(), "Name");
        assertTrue(predicate.getLikeExpression() instanceof StringConstant);
        assertEquals(((StringConstant)predicate.getLikeExpression()).getString(), "%Frame%");
    }


    // @formatter:off
    //parent+quick
    /**
     * WHERE ProductID <= 12
     */
    public Where exampleC = WHERE(
            p_less_equal(
                    e("ProductID"),
                    e_number(12)
            )
    ).build();
    // @formatter:on

    @Test
    public void testExampleC(){
        assertEquals(exampleC.getSearchCondition().getPredicate().getClass(), Comparison.class);
        Comparison predicate = (Comparison) exampleC.getSearchCondition().getPredicate();
        assertEquals(predicate.getExpression().toString(), "ProductID");
        assertTrue(predicate.getOperatorExpression() instanceof NumberConstant);
        assertEquals(((NumberConstant)predicate.getOperatorExpression()).getNumber().toString(), "12");
    }


    // @formatter:off
    //parent+quick
    /**
     * WHERE ProductID = 2
     OR ProductID = 4
     OR Name = 'Spokes'
     */
    public Where exampleD = WHERE(
            p_equal(
                    e("ProductID"),
                    e_number(2)
            )
    ).OR(
            p_equal(
                    e("ProductID"),
                    e_number(4)
            )
    ).OR(
            p_equal(
                    e("Name"),
                    e_string("Spokes")
            )
    ).build();
    // @formatter:on

    @Test
    public void testExampleD(){
        SearchCondition searchCondition = exampleD.getSearchCondition();
        assertEquals(searchCondition.getPredicate().getClass(), Comparison.class);
        Comparison predicate = (Comparison) searchCondition.getPredicate();
        assertEquals(predicate.getExpression().toString(), "ProductID");
        assertEquals(predicate.getOperator(), com.xy.xsql.tsql.model.elements.operators.Comparison.EQUAL);
        assertTrue(predicate.getOperatorExpression() instanceof NumberConstant);
        assertEquals(((NumberConstant)predicate.getOperatorExpression()).getNumber().toString(), "2");

        assertEquals(exampleD.getSearchCondition().getAndOrList().size(),2);

        SearchCondition.AndOrNotItem andOrNotItem = exampleD.getSearchCondition().getAndOrList().get(0);
        assertEquals(andOrNotItem.getPredicate().getClass(), Comparison.class);
        Comparison predicate1 = (Comparison) andOrNotItem.getPredicate();
        assertEquals(predicate1.getExpression().toString(), "ProductID");
        assertEquals(predicate1.getOperator(), com.xy.xsql.tsql.model.elements.operators.Comparison.EQUAL);
        assertTrue(predicate1.getOperatorExpression() instanceof NumberConstant);
        assertEquals(((NumberConstant)predicate1.getOperatorExpression()).getNumber().toString(), "4");

        SearchCondition.AndOrNotItem andOrNotItem1 = exampleD.getSearchCondition().getAndOrList().get(1);
        assertEquals(andOrNotItem1.getPredicate().getClass(), Comparison.class);
        Comparison predicate2 = (Comparison) andOrNotItem1.getPredicate();
        assertEquals(predicate2.getExpression().toString(), "Name");
        assertEquals(predicate2.getOperator(), com.xy.xsql.tsql.model.elements.operators.Comparison.EQUAL);
        assertTrue(predicate2.getOperatorExpression() instanceof StringConstant);
        assertEquals(((StringConstant)predicate2.getOperatorExpression()).getString(), "Spokes");
    }


    // @formatter:off
    //parent+quick
    /**
     * WHERE Name LIKE ('%Frame%')
     AND Name LIKE ('HL%')
     AND Color = 'Red'
     */
    public Where exampleE = WHERE(
            p_like(
                    e("Name"),
                    e_string("%Frame%")
            )
    ).AND(
            p_like(
                    e("Name"),
                    e_string("HL%")
            )
    ).AND(
            p_equal(
                    e("Color"),
                    e_string("Red")
            )
    ).build();
    // @formatter:on

    @Test
    public void testExampleE(){
        SearchCondition searchCondition = exampleE.getSearchCondition();
        assertEquals(searchCondition.getPredicate().getClass(), Like.class);
        Like predicate = (Like) searchCondition.getPredicate();
        assertEquals(predicate.getExpression().toString(), "Name");
        assertTrue(predicate.getLikeExpression() instanceof StringConstant);
        assertEquals(((StringConstant)predicate.getLikeExpression()).getString(), "%Frame%");

        assertEquals(exampleE.getSearchCondition().getAndOrList().size(),2);

        SearchCondition.AndOrNotItem andOrNotItem = exampleE.getSearchCondition().getAndOrList().get(0);
        assertEquals(andOrNotItem.getPredicate().getClass(), Like.class);
        Like predicate1 = (Like) andOrNotItem.getPredicate();
        assertEquals(predicate1.getExpression().toString(), "Name");
        assertTrue(predicate1.getLikeExpression() instanceof StringConstant);
        assertEquals(((StringConstant)predicate1.getLikeExpression()).getString(), "HL%");

        SearchCondition.AndOrNotItem andOrNotItem1 = exampleE.getSearchCondition().getAndOrList().get(1);
        assertEquals(andOrNotItem1.getPredicate().getClass(), Comparison.class);
        Comparison predicate2 = (Comparison) andOrNotItem1.getPredicate();
        assertEquals(predicate2.getExpression().toString(), "Color");
        assertEquals(predicate2.getOperator(), com.xy.xsql.tsql.model.elements.operators.Comparison.EQUAL);
        assertTrue(predicate2.getOperatorExpression() instanceof StringConstant);
        assertEquals(((StringConstant)predicate2.getOperatorExpression()).getString(), "Red");
    }


    // @formatter:off
    //parent+quick
    /**
     * WHERE Name IN ('Blade', 'Crown Race', 'Spokes')
     */
    public Where exampleF = WHERE(
            p_in(
                    e("Name"),
                    e_string("Blade"),
                    e_string("Crown Race"),
                    e_string("Spokes")
            )
    ).build();
    // @formatter:on

    @Test
    public void testExampleF(){
        SearchCondition searchCondition = exampleF.getSearchCondition();
        assertEquals(searchCondition.getPredicate().getClass(), In.class);
        In predicate = (In) searchCondition.getPredicate();
        assertEquals(predicate.getExpression().toString(), "Name");

        assertEquals(predicate.getExpressionList().size(), 3);

        Expression expression = predicate.getExpressionList().get(0);
        assertTrue(expression instanceof StringConstant);
        assertEquals(((StringConstant)expression).getString(), "Blade");
        Expression expression1 = predicate.getExpressionList().get(1);
        assertTrue(expression1 instanceof StringConstant);
        assertEquals(((StringConstant)expression1).getString(), "Crown Race");
        Expression expression2 = predicate.getExpressionList().get(2);
        assertTrue(expression2 instanceof StringConstant);
        assertEquals(((StringConstant)expression2).getString(), "Spokes");
    }


    // @formatter:off
    //parent+quick
    /**
     * WHERE ProductID BETWEEN 725 AND 734
     */
    public Where exampleG = WHERE(
            p_between(
                    e("ProductID"),
                    e_number(725),
                    e_number(734)
            )
    ).build();
    // @formatter:on

    @Test
    public void testExampleG(){
        SearchCondition searchCondition = exampleG.getSearchCondition();
        assertEquals(searchCondition.getPredicate().getClass(), Between.class);
        Between predicate = (Between) searchCondition.getPredicate();
        assertEquals(predicate.getExpression().toString(), "ProductID");
        assertTrue(predicate.getStartExpression() instanceof NumberConstant);
        assertEquals(((NumberConstant)predicate.getStartExpression()).getNumber().toString(), "725");
        assertTrue(predicate.getEndExpression() instanceof NumberConstant);
        assertEquals(((NumberConstant)predicate.getEndExpression()).getNumber().toString(), "734");
    }


    /*
    Examples: Azure SQL Data Warehouse and Parallel Data Warehouse
    See https://docs.microsoft.com/zh-cn/sql/t-sql/queries/where-transact-sql#examples-includesssdwfullincludessssdwfull-mdmd-and-includesspdwincludessspdw-mdmd
     */

    // @formatter:off
    //parent+quick
    /**
     * WHERE LastName = 'Smith'
     */
    public Where exampleH = WHERE(
            p_equal(
                    e("LastName"),
                    e_string("Smith")
            )
    ).build();
    // @formatter:on

    @Test
    public void testExampleH(){
        SearchCondition searchCondition = exampleH.getSearchCondition();
        assertEquals(searchCondition.getPredicate().getClass(), Comparison.class);
        Comparison predicate = (Comparison) searchCondition.getPredicate();
        assertEquals(predicate.getExpression().toString(), "LastName");
        assertEquals(predicate.getOperator(),com.xy.xsql.tsql.model.elements.operators.Comparison.EQUAL);
        assertTrue(predicate.getOperatorExpression() instanceof StringConstant);
        assertEquals(((StringConstant)predicate.getOperatorExpression()).getString(), "Smith");
    }


    // @formatter:off
    //parent+quick
    /**
     * WHERE LastName LIKE ('%Smi%')
     */
    public Where exampleI = WHERE(
            p_like(
                    e("LastName"),
                    e_string("%Smi%")
            )
    ).build();
    // @formatter:on

    @Test
    public void testExampleI(){
        SearchCondition searchCondition = exampleI.getSearchCondition();
        assertEquals(searchCondition.getPredicate().getClass(), Like.class);
        Like predicate = (Like) searchCondition.getPredicate();
        assertEquals(predicate.getExpression().toString(), "LastName");
        assertTrue(predicate.getLikeExpression() instanceof StringConstant);
        assertEquals(((StringConstant)predicate.getLikeExpression()).getString(), "%Smi%");
    }


    // @formatter:off
    //parent+quick
    /**
     * WHERE EmployeeKey  <= 500
     */
    public Where exampleJ = WHERE(
            p_less_equal(
                    e("EmployeeKey"),
                    e_number(500)
            )
    ).build();
    // @formatter:on

    @Test
    public void testExampleJ(){
        SearchCondition searchCondition = exampleJ.getSearchCondition();
        assertEquals(searchCondition.getPredicate().getClass(), Comparison.class);
        Comparison predicate = (Comparison) searchCondition.getPredicate();
        assertEquals(predicate.getExpression().toString(), "EmployeeKey");
        assertEquals(predicate.getOperator(),com.xy.xsql.tsql.model.elements.operators.Comparison.LESS_EQUAL);
        assertTrue(predicate.getOperatorExpression() instanceof NumberConstant);
        assertEquals(((NumberConstant)predicate.getOperatorExpression()).getNumber().toString(), "500");
    }


    // @formatter:off
    //parent+quick
    /**
     * WHERE EmployeeKey = 1 OR EmployeeKey = 8 OR EmployeeKey = 12
     */
    public Where exampleK = WHERE(
            p_equal(
                    e("EmployeeKey"),
                    e_number(1)
            )
    ).OR(
            p_equal(
                    e("EmployeeKey"),
                    e_number(8)
            )
    ).OR(
            p_equal(
                    e("EmployeeKey"),
                    e_number(12)
            )
    ).build();
    // @formatter:on

    @Test
    public void testExampleK(){
        SearchCondition searchCondition = exampleK.getSearchCondition();
        assertEquals(searchCondition.getPredicate().getClass(), Comparison.class);
        Comparison predicate = (Comparison) searchCondition.getPredicate();
        assertEquals(predicate.getExpression().toString(), "EmployeeKey");
        assertEquals(predicate.getOperator(),com.xy.xsql.tsql.model.elements.operators.Comparison.EQUAL);
        assertTrue(predicate.getOperatorExpression() instanceof NumberConstant);
        assertEquals(((NumberConstant)predicate.getOperatorExpression()).getNumber().toString(), "1");

        assertEquals(searchCondition.getAndOrList().size(),2);

        SearchCondition.AndOrNotItem andOrNotItem = searchCondition.getAndOrList().get(0);
        assertEquals(andOrNotItem.getPredicate().getClass(), Comparison.class);
        Comparison predicate1 = (Comparison) andOrNotItem.getPredicate();
        assertEquals(predicate1.getExpression().toString(), "EmployeeKey");
        assertEquals(predicate1.getOperator(),com.xy.xsql.tsql.model.elements.operators.Comparison.EQUAL);
        assertTrue(predicate1.getOperatorExpression() instanceof NumberConstant);
        assertEquals(((NumberConstant)predicate1.getOperatorExpression()).getNumber().toString(), "8");

        SearchCondition.AndOrNotItem andOrNotItem1 = searchCondition.getAndOrList().get(1);
        assertEquals(andOrNotItem.getPredicate().getClass(), Comparison.class);
        Comparison predicate2 = (Comparison) andOrNotItem1.getPredicate();
        assertEquals(predicate2.getExpression().toString(), "EmployeeKey");
        assertEquals(predicate2.getOperator(),com.xy.xsql.tsql.model.elements.operators.Comparison.EQUAL);
        assertTrue(predicate2.getOperatorExpression() instanceof NumberConstant);
        assertEquals(((NumberConstant)predicate2.getOperatorExpression()).getNumber().toString(), "12");
    }


    // @formatter:off
    //parent+quick
    /**
     * WHERE EmployeeKey <= 500 AND LastName LIKE '%Smi%' AND FirstName LIKE '%A%'
     */
    public Where exampleL = WHERE(
            p_less_equal(
                    e("EmployeeKey"),
                    e_number(500)
            )
    ).AND(
            p_like(
                    e("LastName"),
                    e_string("%Smi%")
            )
    ).AND(
            p_like(
                    e("FirstName"),
                    e_string("%A%")
            )
    ).build();
    // @formatter:on

    @Test
    public void testExampleL(){
        SearchCondition searchCondition = exampleL.getSearchCondition();
        assertEquals(searchCondition.getPredicate().getClass(), Comparison.class);
        Comparison predicate = (Comparison) searchCondition.getPredicate();
        assertEquals(predicate.getExpression().toString(), "EmployeeKey");
        assertEquals(predicate.getOperator(),com.xy.xsql.tsql.model.elements.operators.Comparison.LESS_EQUAL);
        assertTrue(predicate.getOperatorExpression() instanceof NumberConstant);
        assertEquals(((NumberConstant)predicate.getOperatorExpression()).getNumber().toString(), "500");


        assertEquals(searchCondition.getAndOrList().size(), 2);

        SearchCondition.AndOrNotItem andOrNotItem = searchCondition.getAndOrList().get(0);
        assertEquals(andOrNotItem.getPredicate().getClass(), Like.class);
        Like predicate1 = (Like) andOrNotItem.getPredicate();
        assertEquals(predicate1.getExpression().toString(), "LastName");
        assertTrue(predicate1.getLikeExpression() instanceof StringConstant);
        assertEquals(((StringConstant)predicate1.getLikeExpression()).getString(), "%Smi%");

        SearchCondition.AndOrNotItem andOrNotItem1 = searchCondition.getAndOrList().get(1);
        assertEquals(andOrNotItem.getPredicate().getClass(), Like.class);
        Like predicate2 = (Like) andOrNotItem1.getPredicate();
        assertEquals(predicate2.getExpression().toString(), "FirstName");
        assertTrue(predicate2.getLikeExpression() instanceof StringConstant);
        assertEquals(((StringConstant)predicate2.getLikeExpression()).getString(), "%A%");
    }


    // @formatter:off
    //parent+quick
    /**
     * WHERE LastName IN ('Smith', 'Godfrey', 'Johnson')
     */
    public Where exampleM = WHERE(
            p_in(
                    e("LastName"),
                    e_string("Smith"),
                    e_string("Godfrey"),
                    e_string("Johnson")
            )
    ).build();
    // @formatter:on

    @Test
    public void testExampleM(){
        SearchCondition searchCondition = exampleM.getSearchCondition();
        assertEquals(searchCondition.getPredicate().getClass(), In.class);
        In predicate = (In) searchCondition.getPredicate();
        assertEquals(predicate.getExpression().toString(), "LastName");
        assertTrue(predicate.getExpressionList().get(0) instanceof StringConstant);
        assertEquals(((StringConstant)predicate.getExpressionList().get(0)).getString(), "Smith");
        assertTrue(predicate.getExpressionList().get(1) instanceof StringConstant);
        assertEquals(((StringConstant)predicate.getExpressionList().get(1)).getString(), "Godfrey");
        assertTrue(predicate.getExpressionList().get(2) instanceof StringConstant);
        assertEquals(((StringConstant)predicate.getExpressionList().get(2)).getString(), "Johnson");
    }


    // @formatter:off
    //parent+quick
    /**
     * WHERE EmployeeKey Between 100 AND 200
     */
    public Where exampleN = WHERE(
            p_between(
                    e("EmployeeKey"),
                    e_number(100),
                    e_number(200)
            )
    ).build();
    // @formatter:on

    @Test
    public void testExampleN(){
        SearchCondition searchCondition = exampleN.getSearchCondition();
        assertEquals(searchCondition.getPredicate().getClass(), Between.class);
        Between predicate = (Between) searchCondition.getPredicate();
        assertEquals(predicate.getExpression().toString(), "EmployeeKey");
        assertTrue(predicate.getStartExpression() instanceof NumberConstant);
        assertEquals(((NumberConstant)predicate.getStartExpression()).getNumber().toString(), "100");
        assertTrue(predicate.getEndExpression() instanceof NumberConstant);
        assertEquals(((NumberConstant)predicate.getEndExpression()).getNumber().toString(), "200");
    }

}