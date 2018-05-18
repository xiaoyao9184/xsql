package com.xy.xsql.tsql.core.clause;

import com.xy.xsql.tsql.core.MockParent;
import com.xy.xsql.tsql.core.MockParentBuilder;
import com.xy.xsql.tsql.model.clause.SearchCondition;
import com.xy.xsql.tsql.model.clause.Where;
import com.xy.xsql.tsql.model.elements.expressions.Expression;
import com.xy.xsql.tsql.model.operator.Operators;
import com.xy.xsql.tsql.model.predicate.Between;
import com.xy.xsql.tsql.model.predicate.Comparison;
import com.xy.xsql.tsql.model.predicate.In;
import com.xy.xsql.tsql.model.predicate.Like;
import org.junit.Assert;
import org.junit.Test;

import static com.xy.xsql.tsql.core.expression.Expressions.*;
import static com.xy.xsql.tsql.core.predicate.Predicates.*;

/**
 * Created by xiaoyao9184 on 2017/3/11.
 */
public class WhereBuilderTest {

    /*
    Examples
    See https://docs.microsoft.com/zh-cn/sql/t-sql/queries/where-transact-sql#examples
     */

    // @formatter:off
    //parent+quick
    /**
     * WHERE Name = 'Blade'
     */
    public Where exampleA = new MockParentBuilder<WhereBuilder<MockParent<Where>>,Where>
                (WhereBuilder.class,Where.class)
                .$child()
                    .$(p_equal(
                            e("Name"),
                            e_string("Blade")
                    ))
                    .and()
                .get();
    // @formatter:on

    @Test
    public void testExampleA(){
        // @formatter:off
        Where where = new WhereBuilder<Void>()
                .withPredicate()._Comparison()
                    .withExpression(e("Name"))
                    .withOperator(Operators.EQUAL)
                    .withExpression(e_string("Blade"))
                    .and()
                .build();
        // @formatter:on

        Assert.assertEquals(where.getSearchCondition().getPredicate().getClass(), Comparison.class);
        Comparison predicate = (Comparison) where.getSearchCondition().getPredicate();
        Assert.assertEquals(predicate.getExpression().toString(), "Name");
        Assert.assertEquals(predicate.getOperatorExpression().toString(), "'Blade'");
    }


    // @formatter:off
    //parent+quick
    /**
     * WHERE Name LIKE ('%Frame%')
     */
    public Where exampleB = new MockParentBuilder<WhereBuilder<MockParent<Where>>,Where>
                (WhereBuilder.class,Where.class)
                .$child()
                    .$(p_like(
                            e("Name"),
                            e_string("%Frame%")
                    ))
                    .and()
                .get();
    // @formatter:on

    @Test
    public void testExampleB(){
        // @formatter:off
        Where where = new WhereBuilder<Void>()
                .withPredicate()._Like()
                    .withStringExpression(e("Name"))
                    .withStringExpression(e_string("%Frame%"))
                    .and()
                .build();
        // @formatter:on

        Assert.assertEquals(where.getSearchCondition().getPredicate().getClass(), Like.class);
        Like predicate = (Like) where.getSearchCondition().getPredicate();
        Assert.assertEquals(predicate.getExpression().toString(), "Name");
        Assert.assertEquals(predicate.getLikeExpression().toString(), "'%Frame%'");
    }


    // @formatter:off
    //parent+quick
    /**
     * WHERE ProductID <= 12
     */
    public Where exampleC = new MockParentBuilder<WhereBuilder<MockParent<Where>>,Where>
                (WhereBuilder.class,Where.class)
                .$child()
                    .$(p_less_equal(
                            e("ProductID"),
                            e_number(12)
                    ))
                    .and()
                .get();
    // @formatter:on

    @Test
    public void testExampleC(){
        // @formatter:off
        Where where = new WhereBuilder<Void>()
                .withPredicate()._Comparison()
                    .withExpression(e("ProductID"))
                    .withOperator(Operators.LESS_EQUAL)
                    .withExpression(e_number(12))
                    .and()
                .build();
        // @formatter:on

        Assert.assertEquals(where.getSearchCondition().getPredicate().getClass(), Comparison.class);
        Comparison predicate = (Comparison) where.getSearchCondition().getPredicate();
        Assert.assertEquals(predicate.getExpression().toString(), "ProductID");
        Assert.assertEquals(predicate.getOperatorExpression().toString(), "12");
    }


    // @formatter:off
    //parent+quick
    /**
     * WHERE ProductID = 2
     OR ProductID = 4
     OR Name = 'Spokes'
     */
    public Where exampleD = new MockParentBuilder<WhereBuilder<MockParent<Where>>,Where>
                (WhereBuilder.class,Where.class)
                .$child()
                    .$(p_equal(
                            e("ProductID"),
                            e_number(2)
                    ))
                    .$Or(p_equal(
                            e("ProductID"),
                            e_number(4)
                    ))
                    .$Or(p_equal(
                            e("Name"),
                            e_string("Spokes")
                    ))
                    .and()
                .get();
    // @formatter:on

    @Test
    public void testExampleD(){
        // @formatter:off
        Where where = new WhereBuilder<Void>()
                .withPredicate()._Comparison()
                        .withExpression(e("ProductID"))
                        .withOperator(Operators.EQUAL)
                        .withExpression(e_number(2))
                        .and()
                .withAndOrNotItem()
                    .withOr()
                    .withPredicate()._Comparison()
                            .withExpression(e("ProductID"))
                            .withOperator(Operators.EQUAL)
                            .withExpression(e_number(4))
                            .and()
                    .and()
                .withAndOrNotItem()
                    .withOr()
                    .withPredicate()._Comparison()
                            .withExpression(e("Name"))
                            .withOperator(Operators.EQUAL)
                            .withExpression(e_string("Spokes"))
                            .and()
                    .and()
                .build();
        // @formatter:on

        SearchCondition searchCondition = where.getSearchCondition();
        Assert.assertEquals(searchCondition.getPredicate().getClass(), Comparison.class);
        Comparison predicate = (Comparison) searchCondition.getPredicate();
        Assert.assertEquals(predicate.getExpression().toString(), "ProductID");
        Assert.assertEquals(predicate.getOperator(), Operators.EQUAL);
        Assert.assertEquals(predicate.getOperatorExpression().toString(), "2");

        Assert.assertEquals(where.getSearchCondition().getAndOrList().size(),2);

        SearchCondition.AndOrNotItem andOrNotItem = where.getSearchCondition().getAndOrList().get(0);
        Assert.assertEquals(andOrNotItem.getPredicate().getClass(), Comparison.class);
        Comparison predicate1 = (Comparison) andOrNotItem.getPredicate();
        Assert.assertEquals(predicate1.getExpression().toString(), "ProductID");
        Assert.assertEquals(predicate1.getOperator(), Operators.EQUAL);
        Assert.assertEquals(predicate1.getOperatorExpression().toString(), "4");

        SearchCondition.AndOrNotItem andOrNotItem1 = where.getSearchCondition().getAndOrList().get(1);
        Assert.assertEquals(andOrNotItem1.getPredicate().getClass(), Comparison.class);
        Comparison predicate2 = (Comparison) andOrNotItem1.getPredicate();
        Assert.assertEquals(predicate2.getExpression().toString(), "Name");
        Assert.assertEquals(predicate2.getOperator(), Operators.EQUAL);
        Assert.assertEquals(predicate2.getOperatorExpression().toString(), "'Spokes'");
    }


    // @formatter:off
    //parent+quick
    /**
     * WHERE Name LIKE ('%Frame%')
     AND Name LIKE ('HL%')
     AND Color = 'Red'
     */
    public Where exampleE = new MockParentBuilder<WhereBuilder<MockParent<Where>>,Where>
                (WhereBuilder.class,Where.class)
                .$child()
                    .$(p_like(
                            e("Name"),
                            e_string("%Frame%")
                    ))
                    .$And(p_like(
                            e("Name"),
                            e_string("HL%")
                    ))
                    .$And(p_equal(
                            e("Color"),
                            e_string("Red")
                    ))
                    .and()
                .get();
    // @formatter:on

    @Test
    public void testExampleE(){
        // @formatter:off
        Where where = new WhereBuilder<Void>()
                .withPredicate()._Like()
                    .withStringExpression(e("Name"))
                    .withStringExpression(e_string("%Frame%"))
                    .and()
                .withAndOrNotItem()
                    .withAnd()
                    .withPredicate()._Like()
                        .withStringExpression(e("Name"))
                        .withStringExpression(e_string("HL%"))
                        .and()
                    .and()
                .withAndOrNotItem()
                    .withAnd()
                    .withPredicate()._Comparison()
                        .withExpression(e("Color"))
                        .withOperator(Operators.EQUAL)
                        .withExpression(e_string("Red"))
                        .and()
                    .and()
                .build();
        // @formatter:on

        SearchCondition searchCondition = where.getSearchCondition();
        Assert.assertEquals(searchCondition.getPredicate().getClass(), Like.class);
        Like predicate = (Like) searchCondition.getPredicate();
        Assert.assertEquals(predicate.getExpression().toString(), "Name");
        Assert.assertEquals(predicate.getLikeExpression().toString(), "'%Frame%'");

        Assert.assertEquals(where.getSearchCondition().getAndOrList().size(),2);

        SearchCondition.AndOrNotItem andOrNotItem = where.getSearchCondition().getAndOrList().get(0);
        Assert.assertEquals(andOrNotItem.getPredicate().getClass(), Like.class);
        Like predicate1 = (Like) andOrNotItem.getPredicate();
        Assert.assertEquals(predicate1.getExpression().toString(), "Name");
        Assert.assertEquals(predicate1.getLikeExpression().toString(), "'HL%'");

        SearchCondition.AndOrNotItem andOrNotItem1 = where.getSearchCondition().getAndOrList().get(1);
        Assert.assertEquals(andOrNotItem1.getPredicate().getClass(), Comparison.class);
        Comparison predicate2 = (Comparison) andOrNotItem1.getPredicate();
        Assert.assertEquals(predicate2.getExpression().toString(), "Color");
        Assert.assertEquals(predicate2.getOperator(), Operators.EQUAL);
        Assert.assertEquals(predicate2.getOperatorExpression().toString(), "'Red'");
    }


    // @formatter:off
    //parent+quick
    /**
     * WHERE Name IN ('Blade', 'Crown Race', 'Spokes')
     */
    public Where exampleF = new MockParentBuilder<WhereBuilder<MockParent<Where>>,Where>
                (WhereBuilder.class,Where.class)
                .$child()
                    .$(p_in(
                            e("Name"),
                            e_string("Blade"),
                            e_string("Crown Race"),
                            e_string("Spokes")
                    ))
                    .and()
                .get();
    // @formatter:on

    @Test
    public void testExampleF(){
        // @formatter:off
        Where where = new WhereBuilder<Void>()
                .withPredicate()._In()
                    .withExpression(e("Name"))
                    .withValueExpression(e_string("Blade"),
                            e_string("Crown Race"),
                            e_string("Spokes"))
                    .and()
                .build();
        // @formatter:on

        SearchCondition searchCondition = where.getSearchCondition();
        Assert.assertEquals(searchCondition.getPredicate().getClass(), In.class);
        In predicate = (In) searchCondition.getPredicate();
        Assert.assertEquals(predicate.getExpression().toString(), "Name");

        Assert.assertEquals(predicate.getExpressionList().size(), 3);

        Expression expression = predicate.getExpressionList().get(0);
        Assert.assertEquals(expression.toString(), "'Blade'");
        Expression expression1 = predicate.getExpressionList().get(1);
        Assert.assertEquals(expression1.toString(), "'Crown Race'");
        Expression expression2 = predicate.getExpressionList().get(2);
        Assert.assertEquals(expression2.toString(), "'Spokes'");
    }


    // @formatter:off
    //parent+quick
    /**
     * WHERE ProductID BETWEEN 725 AND 734
     */
    public Where exampleG = new MockParentBuilder<WhereBuilder<MockParent<Where>>,Where>
                (WhereBuilder.class,Where.class)
                .$child()
                    .$(p_between(
                            e("ProductID"),
                            e_number(725),
                            e_number(734)
                    ))
                    .and()
                .get();
    // @formatter:on

    @Test
    public void testExampleG(){
        // @formatter:off
        Where where = new WhereBuilder<Void>()
                .withPredicate()._Between()
                    .withExpression(e("ProductID"))
                    .withExpression(e_number(725))
                    .withExpression(e_number(734))
                    .and()
                .build();
        // @formatter:on

        SearchCondition searchCondition = where.getSearchCondition();
        Assert.assertEquals(searchCondition.getPredicate().getClass(), Between.class);
        Between predicate = (Between) searchCondition.getPredicate();
        Assert.assertEquals(predicate.getExpression().toString(), "ProductID");
        Assert.assertEquals(predicate.getStartExpression().toString(), "725");
        Assert.assertEquals(predicate.getEndExpression().toString(), "734");

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
    public Where exampleH = new MockParentBuilder<WhereBuilder<MockParent<Where>>,Where>
                (WhereBuilder.class,Where.class)
                .$child()
                    .$(p_equal(
                            e("LastName"),
                            e_string("Smith")
                    ))
                    .and()
                .get();
    // @formatter:on

    @Test
    public void testExampleH(){
        // @formatter:off
        Where where = new WhereBuilder<Void>()
                .withPredicate()._Comparison()
                    .withExpression(e("LastName"))
                    .withOperator(Operators.EQUAL)
                    .withExpression(e_string("Smith"))
                    .and()
                .build();
        // @formatter:on

        SearchCondition searchCondition = where.getSearchCondition();
        Assert.assertEquals(searchCondition.getPredicate().getClass(), Comparison.class);
        Comparison predicate = (Comparison) searchCondition.getPredicate();
        Assert.assertEquals(predicate.getExpression().toString(), "LastName");
        Assert.assertEquals(predicate.getOperator(),Operators.EQUAL);
        Assert.assertEquals(predicate.getOperatorExpression().toString(), "'Smith'");

    }


    // @formatter:off
    //parent+quick
    /**
     * WHERE LastName LIKE ('%Smi%')
     */
    public Where exampleI = new MockParentBuilder<WhereBuilder<MockParent<Where>>,Where>
                (WhereBuilder.class,Where.class)
                .$child()
                    .$(p_like(
                            e("LastName"),
                            e_string("%Smi%")
                    ))
                    .and()
                .get();
    // @formatter:on

    @Test
    public void testExampleI(){
        // @formatter:off
        Where where = new WhereBuilder<Void>()
                .withPredicate()._Like()
                    .withStringExpression(e("LastName"))
                    .withStringExpression(e_string("%Smi%"))
                    .and()
                .build();
        // @formatter:on

        SearchCondition searchCondition = where.getSearchCondition();
        Assert.assertEquals(searchCondition.getPredicate().getClass(), Like.class);
        Like predicate = (Like) searchCondition.getPredicate();
        Assert.assertEquals(predicate.getExpression().toString(), "LastName");
        Assert.assertEquals(predicate.getLikeExpression().toString(), "'%Smi%'");

    }


    // @formatter:off
    //parent+quick
    /**
     * WHERE EmployeeKey  <= 500
     */
    public Where exampleJ = new MockParentBuilder<WhereBuilder<MockParent<Where>>,Where>
                (WhereBuilder.class,Where.class)
                .$child()
                    .$(p_less_equal(
                            e("EmployeeKey"),
                            e_number(500)
                    ))
                    .and()
                .get();
    // @formatter:on

    @Test
    public void testExampleJ(){
        // @formatter:off
        Where where = new WhereBuilder<Void>()
                .withPredicate()._Comparison()
                    .withExpression(e("EmployeeKey"))
                    .withOperator(Operators.LESS_EQUAL)
                    .withExpression(e_number(500))
                    .and()
                .build();
        // @formatter:on

        SearchCondition searchCondition = where.getSearchCondition();
        Assert.assertEquals(searchCondition.getPredicate().getClass(), Comparison.class);
        Comparison predicate = (Comparison) searchCondition.getPredicate();
        Assert.assertEquals(predicate.getExpression().toString(), "EmployeeKey");
        Assert.assertEquals(predicate.getOperator(),Operators.LESS_EQUAL);
        Assert.assertEquals(predicate.getOperatorExpression().toString(), "500");

    }


    // @formatter:off
    //parent+quick
    /**
     * WHERE EmployeeKey = 1 OR EmployeeKey = 8 OR EmployeeKey = 12
     */
    public Where exampleK = new MockParentBuilder<WhereBuilder<MockParent<Where>>,Where>
                (WhereBuilder.class,Where.class)
                .$child()
                    .$(p_equal(
                            e("EmployeeKey"),
                            e_number(1)
                    ))
                    .$Or(p_equal(
                            e("EmployeeKey"),
                            e_number(8)
                    ))
                    .$Or(p_equal(
                            e("EmployeeKey"),
                            e_number(12)
                    ))
                    .and()
                .get();
    // @formatter:on

    @Test
    public void testExampleK(){
        // @formatter:off
        Where where = new WhereBuilder<Void>()
                .withPredicate()._Comparison()
                    .withExpression(e("EmployeeKey"))
                    .withOperator(Operators.EQUAL)
                    .withExpression(e_number(1))
                    .and()
                .withAndOrNotItem()
                    .withOr()
                    .withPredicate()._Comparison()
                        .withExpression(e("EmployeeKey"))
                        .withOperator(Operators.EQUAL)
                        .withExpression(e_number(8))
                        .and()
                    .and()
                .withAndOrNotItem()
                    .withOr()
                    .withPredicate()._Comparison()
                        .withExpression(e("EmployeeKey"))
                        .withOperator(Operators.EQUAL)
                        .withExpression(e_number(12))
                        .and()
                    .and()
                .build();
        // @formatter:on

        SearchCondition searchCondition = where.getSearchCondition();
        Assert.assertEquals(searchCondition.getPredicate().getClass(), Comparison.class);
        Comparison predicate = (Comparison) searchCondition.getPredicate();
        Assert.assertEquals(predicate.getExpression().toString(), "EmployeeKey");
        Assert.assertEquals(predicate.getOperator(),Operators.EQUAL);
        Assert.assertEquals(predicate.getOperatorExpression().toString(), "1");

        Assert.assertEquals(searchCondition.getAndOrList().size(),2);

        SearchCondition.AndOrNotItem andOrNotItem = searchCondition.getAndOrList().get(0);
        Assert.assertEquals(andOrNotItem.getPredicate().getClass(), Comparison.class);
        Comparison predicate1 = (Comparison) andOrNotItem.getPredicate();
        Assert.assertEquals(predicate1.getExpression().toString(), "EmployeeKey");
        Assert.assertEquals(predicate1.getOperator(),Operators.EQUAL);
        Assert.assertEquals(predicate1.getOperatorExpression().toString(), "8");

        SearchCondition.AndOrNotItem andOrNotItem1 = searchCondition.getAndOrList().get(1);
        Assert.assertEquals(andOrNotItem.getPredicate().getClass(), Comparison.class);
        Comparison predicate2 = (Comparison) andOrNotItem1.getPredicate();
        Assert.assertEquals(predicate2.getExpression().toString(), "EmployeeKey");
        Assert.assertEquals(predicate2.getOperator(),Operators.EQUAL);
        Assert.assertEquals(predicate2.getOperatorExpression().toString(), "12");


    }


    // @formatter:off
    //parent+quick
    /**
     * WHERE EmployeeKey <= 500 AND LastName LIKE '%Smi%' AND FirstName LIKE '%A%'
     */
    public Where exampleL = new MockParentBuilder<WhereBuilder<MockParent<Where>>,Where>
                (WhereBuilder.class,Where.class)
                .$child()
                    .$(p_less_equal(
                            e("EmployeeKey"),
                            e_number(500)
                    ))
                    .$And(p_like(
                            e("LastName"),
                            e_string("%Smi%")
                    ))
                    .$And(p_like(
                            e("FirstName"),
                            e_string("%A%")
                    ))
                    .and()
                .get();
    // @formatter:on

    @Test
    public void testExampleL(){
        // @formatter:off
        Where where = new WhereBuilder<Void>()
                .withPredicate()._Comparison()
                    .withExpression(e("EmployeeKey"))
                    .withOperator(Operators.LESS_EQUAL)
                    .withExpression(e_number(500))
                    .and()
                .withAndOrNotItem()
                    .withAnd()
                    .withPredicate()._Like()
                        .withStringExpression(e("LastName"))
                        .withStringExpression(e_string("%Smi%"))
                        .and()
                    .and()
                .withAndOrNotItem()
                    .withAnd()
                    .withPredicate()._Like()
                        .withStringExpression(e("FirstName"))
                        .withStringExpression(e_string("%A%"))
                        .and()
                    .and()
                .build();
        // @formatter:on

        SearchCondition searchCondition = where.getSearchCondition();
        Assert.assertEquals(searchCondition.getPredicate().getClass(), Comparison.class);
        Comparison predicate = (Comparison) searchCondition.getPredicate();
        Assert.assertEquals(predicate.getExpression().toString(), "EmployeeKey");
        Assert.assertEquals(predicate.getOperator(),Operators.LESS_EQUAL);
        Assert.assertEquals(predicate.getOperatorExpression().toString(), "500");


        Assert.assertEquals(searchCondition.getAndOrList().size(), 2);

        SearchCondition.AndOrNotItem andOrNotItem = searchCondition.getAndOrList().get(0);
        Assert.assertEquals(andOrNotItem.getPredicate().getClass(), Like.class);
        Like predicate1 = (Like) andOrNotItem.getPredicate();
        Assert.assertEquals(predicate1.getExpression().toString(), "LastName");
        Assert.assertEquals(predicate1.getLikeExpression().toString(),"'%Smi%'");

        SearchCondition.AndOrNotItem andOrNotItem1 = searchCondition.getAndOrList().get(1);
        Assert.assertEquals(andOrNotItem.getPredicate().getClass(), Like.class);
        Like predicate2 = (Like) andOrNotItem1.getPredicate();
        Assert.assertEquals(predicate2.getExpression().toString(), "FirstName");
        Assert.assertEquals(predicate2.getLikeExpression().toString(),"'%A%'");

    }


    // @formatter:off
    //parent+quick
    /**
     * WHERE LastName IN ('Smith', 'Godfrey', 'Johnson')
     */
    public Where exampleM = new MockParentBuilder<WhereBuilder<MockParent<Where>>,Where>
                (WhereBuilder.class,Where.class)
                .$child()
                    .$(p_in(
                            e("LastName"),
                            e_string("Smith"),
                            e_string("Godfrey"),
                            e_string("Johnson")
                    ))
                    .and()
                .get();
    // @formatter:on

    @Test
    public void testExampleM(){
        // @formatter:off
        Where where = new WhereBuilder<Void>()
                .withPredicate()._In()
                    .withExpression(e("LastName"))
                    .withValueExpression(e_string("Smith"))
                    .withValueExpression(e_string("Godfrey"))
                    .withValueExpression(e_string("Johnson"))
                    .and()
                .build();
        // @formatter:on

        SearchCondition searchCondition = where.getSearchCondition();
        Assert.assertEquals(searchCondition.getPredicate().getClass(), In.class);
        In predicate = (In) searchCondition.getPredicate();
        Assert.assertEquals(predicate.getExpression().toString(), "LastName");
        Assert.assertEquals(predicate.getExpressionList().get(0).toString(),"'Smith'");
        Assert.assertEquals(predicate.getExpressionList().get(1).toString(), "'Godfrey'");
        Assert.assertEquals(predicate.getExpressionList().get(2).toString(), "'Johnson'");

    }


    // @formatter:off
    //parent+quick
    /**
     * WHERE EmployeeKey Between 100 AND 200
     */
    public Where exampleN = new MockParentBuilder<WhereBuilder<MockParent<Where>>,Where>
                (WhereBuilder.class,Where.class)
                .$child()
                    .$(p_between(
                            e("EmployeeKey"),
                            e_number(100),
                            e_number(200)
                    ))
                    .and()
                .get();
    // @formatter:on

    @Test
    public void testExampleN(){
        // @formatter:off
        Where where = new WhereBuilder<Void>()
                .withPredicate()._Between()
                    .withExpression(e("EmployeeKey"))
                    .withExpression(e_number(100))
                    .withExpression(e_number(200))
                    .and()
                .build();
        // @formatter:on

        SearchCondition searchCondition = where.getSearchCondition();
        Assert.assertEquals(searchCondition.getPredicate().getClass(), Between.class);
        Between predicate = (Between) searchCondition.getPredicate();
        Assert.assertEquals(predicate.getExpression().toString(), "EmployeeKey");
        Assert.assertEquals(predicate.getStartExpression().toString(),"100");
        Assert.assertEquals(predicate.getEndExpression().toString(), "200");

    }

}
