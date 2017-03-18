package com.xy.xsql.tsql.core.clause;

import com.xy.xsql.tsql.core.MockParent;
import com.xy.xsql.tsql.core.MockParentBuilder;
import com.xy.xsql.tsql.model.clause.SearchCondition;
import com.xy.xsql.tsql.model.clause.Where;
import com.xy.xsql.tsql.model.expression.Expression;
import com.xy.xsql.tsql.model.operator.Operators;
import com.xy.xsql.tsql.model.predicate.Between;
import com.xy.xsql.tsql.model.predicate.Comparison;
import com.xy.xsql.tsql.model.predicate.In;
import com.xy.xsql.tsql.model.predicate.Like;
import org.junit.Assert;
import org.junit.Test;

import static com.xy.xsql.tsql.core.expression.ExpressionBuilder.*;
import static com.xy.xsql.tsql.core.predicate.PredicateBuilder.*;

/**
 * Created by xiaoyao9184 on 2017/3/11.
 */
public class WhereBuilderTest {

    /**
     * WHERE Name = 'Blade' ;
     */
    @Test
    public void testExampleA(){
        // @formatter:off
        Where where = new WhereBuilder<Void>()
                .withSearchCondition()
                    .withPredicate()._Comparison()
                        .withExpression(e("Name"))
                        .withOperator(Operators.EQUAL)
                        .withExpression(e_string("Blade"))
                        .and()
                    .and()
                .build();

        //parent+quick
        MockParent<Where> parent = new MockParentBuilder<WhereBuilder<MockParent<Where>>,Where>
                (WhereBuilder.class,Where.class)
                .$child()
                    .$Predicate(
                            p_equal(
                                    e("Name"),
                                    e_string("Blade")
                            )
                    )
                    .and();
        // @formatter:on

        Assert.assertEquals(where.getSearchCondition().getPredicate().getClass(), Comparison.class);
        Comparison predicate = (Comparison) where.getSearchCondition().getPredicate();
        Assert.assertEquals(predicate.getExpression().toString(), "Name");
        Assert.assertEquals(predicate.getOperatorExpression().toString(), "'Blade'");
    }

    /**
     * WHERE Name LIKE ('%Frame%');
     */
    @Test
    public void testExampleB(){
        // @formatter:off
        Where where = new WhereBuilder.QuickBuilder<Void>()
                .withPredicate(
                        p_like(e("Name"),e_string("%Frame%"))
                )
                .build();

        //parent+quick
        MockParent<Where> parent = new MockParentBuilder<WhereBuilder<MockParent<Where>>,Where>
                (WhereBuilder.class,Where.class)
                .$child()
                    .$Predicate(
                            p_like(
                                    e("name"),
                                    e_string("%Frame%")
                            )
                    )
                    .and();
        // @formatter:on

        Assert.assertEquals(where.getSearchCondition().getPredicate().getClass(), Like.class);
        Like predicate = (Like) where.getSearchCondition().getPredicate();
        Assert.assertEquals(predicate.getExpression().toString(), "Name");
        Assert.assertEquals(predicate.getLikeExpression().toString(), "'%Frame%'");
    }

    /**
     * WHERE ProductID <= 12 ;
     */
    @Test
    public void testExampleC(){
        // @formatter:off
        Where where = new WhereBuilder.QuickBuilder<Void>()
                .withPredicate(
                       p_less_equal(e("ProductID"),e_number(12))
                )
                .build();

        //parent+quick
        MockParent<Where> parent = new MockParentBuilder<WhereBuilder<MockParent<Where>>,Where>
                (WhereBuilder.class,Where.class)
                .$child()
                    .$Predicate(
                            p_less_equal(
                                    e("ProductID"),
                                    e_number(12))
                    )
                    .and();
        // @formatter:on

        Assert.assertEquals(where.getSearchCondition().getPredicate().getClass(), Comparison.class);
        Comparison predicate = (Comparison) where.getSearchCondition().getPredicate();
        Assert.assertEquals(predicate.getExpression().toString(), "ProductID");
        Assert.assertEquals(predicate.getOperatorExpression().toString(), "12");
    }

    /**
     * WHERE ProductID = 2
     OR ProductID = 4
     OR Name = 'Spokes' ;
     */
    @Test
    public void testExampleD(){
        // @formatter:off
        Where where0 = new WhereBuilder<Void>()
                .withSearchCondition()
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
                    .and()
                .build();


        Where where = new WhereBuilder.QuickBuilder<Void>()
                .withPredicate(
                        p_equal(e("ProductID"),e_number(2))
                )
                .withOrPredicate(
                        p_equal(e("ProductID"),e_number(4))
                )
                .withOrPredicate(
                        p_equal(e("Name"),e_string("Spokes"))
                )
                .build();

        //parent+quick
        MockParent<Where> parent = new MockParentBuilder<WhereBuilder<MockParent<Where>>,Where>
                (WhereBuilder.class,Where.class)
                .$child()
                    .$Predicate(
                            p_equal(e("ProductID"),e_number(2))
                    )
                    .$_OrPredicate(
                            p_equal(e("ProductID"),e_number(4))
                    )
                    .$_OrPredicate(
                            p_equal(e("Name"),e_string("Spokes"))
                    )
                    .and();
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

    /**
     * WHERE Name LIKE ('%Frame%')
     AND Name LIKE ('HL%')
     AND Color = 'Red' ;
     */
    @Test
    public void testExampleE(){
        // @formatter:off
        Where where = new WhereBuilder.QuickBuilder<Void>()
                .withPredicate(
                        p_like(e("Name"),e_string("%Frame%"))
                )
                .withAndPredicate(
                        p_like(e("Name"),e_string("HL%"))
                )
                .withAndPredicate(
                        p_equal(e("Color"),e_string("Red"))
                )
                .build();

        //parent+quick
        MockParent<Where> parent = new MockParentBuilder<WhereBuilder<MockParent<Where>>,Where>
                (WhereBuilder.class,Where.class)
                .$child()
                    .$Predicate(
                            p_like(e("Name"),e_string("%Frame%"))
                    )
                    .$_AndPredicate(
                            p_like(e("Name"),e_string("HL%"))
                    )
                    .$_AndPredicate(
                            p_equal(e("Color"),e_string("Red"))
                    )
                    .and();
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

    /**
     * WHERE Name IN ('Blade', 'Crown Race', 'Spokes');
     */
    @Test
    public void testExampleF(){
        // @formatter:off
        Where where = new WhereBuilder.QuickBuilder<Void>()
                .withPredicate(
                        p_in(
                                e("Name"),
                                e_string("Blade"),
                                e_string("Crown Race"),
                                e_string("Spokes"))
                )
                .build();

        //parent+quick
        MockParent<Where> parent = new MockParentBuilder<WhereBuilder<MockParent<Where>>,Where>
                (WhereBuilder.class,Where.class)
                .$child()
                    .$Predicate(
                        p_in(
                                e("Name"),
                                e_string("Blade"),
                                e_string("Crown Race"),
                                e_string("Spokes"))
                    )
                    .and();
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

    /**
     * WHERE ProductID BETWEEN 725 AND 734;
     */
    @Test
    public void testExampleG(){
        // @formatter:off
        Where where = new WhereBuilder.QuickBuilder<Void>()
                .withPredicate(
                        p_between(
                                e("ProductID"),
                                e_number(725),
                                e_number(734))
                )
                .build();

        //parent+quick
        MockParent<Where> parent = new MockParentBuilder<WhereBuilder<MockParent<Where>>,Where>
                (WhereBuilder.class,Where.class)
                .$child()
                    .$Predicate(
                        p_between(
                                e("ProductID"),
                                e_number(725),
                                e_number(734))
                    )
                    .and();
        // @formatter:on

        SearchCondition searchCondition = where.getSearchCondition();
        Assert.assertEquals(searchCondition.getPredicate().getClass(), Between.class);
        Between predicate = (Between) searchCondition.getPredicate();
        Assert.assertEquals(predicate.getExpression().toString(), "ProductID");
        Assert.assertEquals(predicate.getStartExpression().toString(), "725");
        Assert.assertEquals(predicate.getEndExpression().toString(), "734");

    }

    /**
     * WHERE LastName = 'Smith' ;
     */
    @Test
    public void testExampleH(){
        // @formatter:off
        Where where = new WhereBuilder.QuickBuilder<Void>()
                .withPredicate(
                        p_equal(
                                e("LastName"),
                                e_string("Smith"))
                )
                .build();

        //parent+quick
        MockParent<Where> parent = new MockParentBuilder<WhereBuilder<MockParent<Where>>,Where>
                (WhereBuilder.class,Where.class)
                .$child()
                    .$Predicate(
                        p_equal(
                                e("LastName"),
                                e_string("Smith"))
                    )
                    .and();
        // @formatter:on

        SearchCondition searchCondition = where.getSearchCondition();
        Assert.assertEquals(searchCondition.getPredicate().getClass(), Comparison.class);
        Comparison predicate = (Comparison) searchCondition.getPredicate();
        Assert.assertEquals(predicate.getExpression().toString(), "LastName");
        Assert.assertEquals(predicate.getOperator(),Operators.EQUAL);
        Assert.assertEquals(predicate.getOperatorExpression().toString(), "'Smith'");

    }

    /**
     * WHERE LastName LIKE ('%Smi%');
     */
    @Test
    public void testExampleI(){
        // @formatter:off
        Where where = new WhereBuilder.QuickBuilder<Void>()
                .withPredicate(
                        p_like(
                                e("LastName"),
                                e_string("%Smi%"))
                )
                .build();

        //parent+quick
        MockParent<Where> parent = new MockParentBuilder<WhereBuilder<MockParent<Where>>,Where>
                (WhereBuilder.class,Where.class)
                .$child()
                    .$Predicate(
                        p_like(
                                e("LastName"),
                                e_string("%Smi%"))
                    )
                    .and();
        // @formatter:on

        SearchCondition searchCondition = where.getSearchCondition();
        Assert.assertEquals(searchCondition.getPredicate().getClass(), Like.class);
        Like predicate = (Like) searchCondition.getPredicate();
        Assert.assertEquals(predicate.getExpression().toString(), "LastName");
        Assert.assertEquals(predicate.getLikeExpression().toString(), "'%Smi%'");

    }

    /**
     * WHERE EmployeeKey  <= 500;
     */
    @Test
    public void testExampleJ(){
        // @formatter:off
        Where where = new WhereBuilder.QuickBuilder<Void>()
                .withPredicate(
                        p_less_equal(
                                e("EmployeeKey"),
                                e_number(500))
                )
                .build();

        //parent+quick
        MockParent<Where> parent = new MockParentBuilder<WhereBuilder<MockParent<Where>>,Where>
                (WhereBuilder.class,Where.class)
                .$child()
                    .$Predicate(
                        p_less_equal(
                                e("EmployeeKey"),
                                e_number(500))
                    )
                    .and();
        // @formatter:on

        SearchCondition searchCondition = where.getSearchCondition();
        Assert.assertEquals(searchCondition.getPredicate().getClass(), Comparison.class);
        Comparison predicate = (Comparison) searchCondition.getPredicate();
        Assert.assertEquals(predicate.getExpression().toString(), "EmployeeKey");
        Assert.assertEquals(predicate.getOperator(),Operators.LESS_EQUAL);
        Assert.assertEquals(predicate.getOperatorExpression().toString(), "500");

    }

    /**
     * EmployeeKey = 1 OR EmployeeKey = 8 OR EmployeeKey = 12;
     */
    @Test
    public void testExampleK(){
        // @formatter:off
        Where where = new WhereBuilder.QuickBuilder<Void>()
                .withPredicate(
                        p_equal(
                            e("EmployeeKey"),
                            e_number(1))
                )
                .withOrPredicate(
                        p_equal(
                            e("EmployeeKey"),
                            e_number(8)
                ))
                .withOrPredicate(
                        p_equal(
                            e("EmployeeKey"),
                            e_number(12)
                ))
                .build();

        //parent+quick
        MockParent<Where> parent = new MockParentBuilder<WhereBuilder<MockParent<Where>>,Where>
                (WhereBuilder.class,Where.class)
                .$child()
                    .$Predicate(
                        p_equal(
                            e("EmployeeKey"),
                            e_number(1))
                    )
                    .$_OrPredicate(
                        p_equal(
                            e("EmployeeKey"),
                            e_number(8))
                    )
                    .$_OrPredicate(
                        p_equal(
                            e("EmployeeKey"),
                            e_number(12))
                    )
                    .and();
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

    /**
     * WHERE EmployeeKey <= 500 AND LastName LIKE '%Smi%' AND FirstName LIKE '%A%';
     */
    @Test
    public void testExampleL(){
        // @formatter:off
        Where where = new WhereBuilder.QuickBuilder<Void>()
                .withPredicate(
                        p_less_equal(
                                e("EmployeeKey"),
                                e_number(500))
                )
                .withAndPredicate(
                        p_like(
                                e("LastName"),
                                e_string("%Smi%"))
                )
                .withAndPredicate(
                        p_like(
                                e("FirstName"),
                                e_string("%A%"))
                )
                .build();

        //parent+quick
        MockParent<Where> parent = new MockParentBuilder<WhereBuilder<MockParent<Where>>,Where>
                (WhereBuilder.class,Where.class)
                .$child()
                    .$Predicate(
                        p_less_equal(
                                e("EmployeeKey"),
                                e_number(500))
                    )
                    .$_AndPredicate(
                        p_like(
                                e("LastName"),
                                e_string("%Smi%"))
                    )
                    .$_AndPredicate(
                        p_like(
                                e("FirstName"),
                                e_string("%A%"))
                    )
                    .and();
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

    /**
     * WHERE LastName IN ('Smith', 'Godfrey', 'Johnson');
     */
    @Test
    public void testExampleM(){
        // @formatter:off
        Where where = new WhereBuilder.QuickBuilder<Void>()
                .withPredicate(
                        p_in(
                                e("LastName"),
                                e_string("Smith"),
                                e_string("Godfrey"),
                                e_string("Johnson"))
                )
                .build();

        //parent+quick
        MockParent<Where> parent = new MockParentBuilder<WhereBuilder<MockParent<Where>>,Where>
                (WhereBuilder.class,Where.class)
                .$child()
                    .$Predicate(
                        p_in(
                            e("LastName"),
                            e_string("Smith"),
                            e_string("Godfrey"),
                            e_string("Johnson"))
                    )
                    .and();
        // @formatter:on

        SearchCondition searchCondition = where.getSearchCondition();
        Assert.assertEquals(searchCondition.getPredicate().getClass(), In.class);
        In predicate = (In) searchCondition.getPredicate();
        Assert.assertEquals(predicate.getExpression().toString(), "LastName");
        Assert.assertEquals(predicate.getExpressionList().get(0).toString(),"'Smith'");
        Assert.assertEquals(predicate.getExpressionList().get(1).toString(), "'Godfrey'");
        Assert.assertEquals(predicate.getExpressionList().get(2).toString(), "'Johnson'");

    }

    /**
     * WHERE EmployeeKey Between 100 AND 200;
     */
    @Test
    public void testExampleN(){
        // @formatter:off
        Where where = new WhereBuilder.QuickBuilder<Void>()
                .withPredicate(
                        p_between(
                            e("EmployeeKey"),
                            e_number(100),
                            e_number(200))
                )
                .build();

        //parent+quick
        MockParent<Where> parent = new MockParentBuilder<WhereBuilder<MockParent<Where>>,Where>
                (WhereBuilder.class,Where.class)
                .$child()
                    .$Predicate(
                        p_between(
                            e("EmployeeKey"),
                            e_number(100),
                            e_number(200))
                    )
                    .and();
        // @formatter:on

        SearchCondition searchCondition = where.getSearchCondition();
        Assert.assertEquals(searchCondition.getPredicate().getClass(), Between.class);
        Between predicate = (Between) searchCondition.getPredicate();
        Assert.assertEquals(predicate.getExpression().toString(), "EmployeeKey");
        Assert.assertEquals(predicate.getStartExpression().toString(),"100");
        Assert.assertEquals(predicate.getEndExpression().toString(), "200");

    }

}
