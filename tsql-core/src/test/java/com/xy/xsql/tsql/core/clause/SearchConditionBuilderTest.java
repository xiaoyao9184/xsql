package com.xy.xsql.tsql.core.clause;

import com.xy.xsql.tsql.core.predicate.PredicateBuilder;
import com.xy.xsql.tsql.model.clause.SearchCondition;
import com.xy.xsql.tsql.model.operator.Operators;
import com.xy.xsql.tsql.model.predicate.In;
import com.xy.xsql.tsql.model.predicate.Like;
import com.xy.xsql.tsql.model.predicate.Comparison;
import com.xy.xsql.tsql.model.predicate.ComparisonSubQuery;
import com.xy.xsql.tsql.model.statement.dml.Select;
import org.junit.Assert;
import org.junit.Test;

import static com.xy.xsql.tsql.core.expression.ExpressionBuilder.*;
import static com.xy.xsql.tsql.core.predicate.PredicateBuilder.*;

/**
 * Created by xiaoyao9184 on 2017/3/11.
 */
public class SearchConditionBuilderTest {


    /**
     * WHERE CountryRegionCode == ALL ( subquery ) ;
     */
    @Test
    public void testALLSOMEANY(){
        Select select = null;
        // @formatter:off
        SearchCondition searchCondition = new SearchConditionBuilder<Void>()
                .withPredicate(
                        p_equal(e("CountryRegionCode"),
                                all(),
                                select)
                )
                .build();
        // @formatter:on
        Assert.assertEquals(searchCondition.getPredicate().getClass(), ComparisonSubQuery.class);
        ComparisonSubQuery predicate = (ComparisonSubQuery) searchCondition.getPredicate();
        Assert.assertEquals(predicate.getExpression().toString(), "CountryRegionCode");
        Assert.assertEquals(predicate.getOperator(), Operators.EQUAL);
    }

    /**
     * WHERE CountryRegionCode == ALL ( subquery ) AND CountryRegionCode = 20;
     */
    @Test
    public void testAnd(){
        Select select = null;
        // @formatter:off
        SearchCondition searchCondition = new SearchConditionBuilder<Void>()
                .withPredicate(
                        p_equal(e("CountryRegionCode"),
                                all(),
                                select)
                )
                .withAndPredicate(
                        PredicateBuilder.p_equal(e("CountryRegionCode"),
                                e_number(20)))
                .build();
        // @formatter:on
        Assert.assertEquals(searchCondition.getPredicate().getClass(), ComparisonSubQuery.class);
        ComparisonSubQuery predicate = (ComparisonSubQuery) searchCondition.getPredicate();
        Assert.assertEquals(predicate.getExpression().toString(), "CountryRegionCode");
        Assert.assertEquals(predicate.getOperator(), Operators.EQUAL);

        Assert.assertEquals(searchCondition.getAndOrList().size(), 1);
        Comparison predicate1 = (Comparison) searchCondition.getAndOrList().get(0).getPredicate();
        Assert.assertEquals(predicate1.getExpression().toString(), "CountryRegionCode");
        Assert.assertEquals(predicate1.getOperator(), Operators.EQUAL);
    }

    /**
     * WHERE CountryRegionCode == ALL ( subquery ) AND NOT ( CountryRegionCode > 20 AND CountryRegionCode < 50);
     */
    @Test
    public void testAndAnd(){
        Select select = null;
        // @formatter:off
        SearchCondition searchCondition = new SearchConditionBuilder<Void>()
                .withPredicate(
                        p_equal(e("CountryRegionCode"),
                                all(),
                                select)
                )
                .withAndNotSearchCondition()
                    .withPredicate(
                            p_greater(e("CountryRegionCode"),
                                e_number(20))
                    )
                    .withAndPredicate(
                            p_less(e("CountryRegionCode"),
                                    e_number(50))
                    )
                    .and()
                .build();
        // @formatter:on
        Assert.assertEquals(searchCondition.getPredicate().getClass(), ComparisonSubQuery.class);
        ComparisonSubQuery predicate = (ComparisonSubQuery) searchCondition.getPredicate();
        Assert.assertEquals(predicate.getExpression().toString(), "CountryRegionCode");
        Assert.assertEquals(predicate.getOperator(), Operators.EQUAL);


        Assert.assertEquals(searchCondition.getAndOrList().size(), 1);
        Comparison predicate1 = (Comparison) searchCondition.getAndOrList().get(0).getSearchCondition().getPredicate();
        Assert.assertEquals(predicate1.getExpression().toString(), "CountryRegionCode");
        Assert.assertEquals(predicate1.getOperator(), Operators.GREATER);

        Assert.assertEquals(searchCondition.getAndOrList().get(0).getSearchCondition().getAndOrList().size(), 1);
        Comparison predicate2 = (Comparison) searchCondition.getAndOrList().get(0).getSearchCondition().getAndOrList().get(0).getPredicate();
        Assert.assertEquals(predicate2.getExpression().toString(), "CountryRegionCode");
        Assert.assertEquals(predicate2.getOperator(), Operators.LESS);

    }



    /**
     * WHERE LargePhotoFileName LIKE '%greena_%' ESCAPE 'a' ;
     */
    @Test
    public void testExampleA(){
        // @formatter:off
        SearchCondition searchCondition0 = new SearchConditionBuilder<Void>()
                .withPredicate().Like()
                        .withStringExpression(e("LargePhotoFileName"))
                        .withStringExpression(e_string("%greena_%"))
                        .withEscape(e_string("a"))
                        .out()
                .build();

        //SIMPLE
        SearchCondition searchCondition = new SearchConditionBuilder<Void>()
                .withPredicate(
                        p_like(
                                e("LargePhotoFileName"),
                                e_string("%greena_%"),
                                e_string("a")))
                .build();
        // @formatter:on
        Assert.assertEquals(searchCondition.getPredicate().getClass(), Like.class);
        Like predicate = (Like) searchCondition.getPredicate();
        Assert.assertEquals(predicate.getExpression().toString(), "LargePhotoFileName");
        Assert.assertEquals(predicate.getLikeExpression().toString(), "'%greena_%'");
        Assert.assertEquals(predicate.getEscapeCharacter().toString(), "'a'");
    }

    /**
     * WHERE CountryRegionCode NOT IN ('US')
     AND City LIKE N'Pa%' ;
     */
    @Test
    public void testExampleB(){
        // @formatter:off
        SearchCondition searchCondition = new SearchConditionBuilder<Void>()
                .withPredicate(
                        p_in(e("CountryRegionCode"),
                                e_string("US"))
                )
                .withAndPredicate(
                        p_like(e("City"),
                                e_n_string("Pa%"))
                )
                .build();
        // @formatter:on
        Assert.assertEquals(searchCondition.getPredicate().getClass(), In.class);
        In predicate = (In) searchCondition.getPredicate();
        Assert.assertEquals(predicate.getExpression().toString(), "CountryRegionCode");
        Assert.assertEquals(predicate.getExpressionList().get(0).toString(), "'US'");

        Assert.assertEquals(searchCondition.getAndOrList().size(), 1);
        Assert.assertEquals(searchCondition.getAndOrList().get(0).getPredicate().getClass(), Like.class);
        Like predicate1 = (Like) searchCondition.getAndOrList().get(0).getPredicate();
        Assert.assertEquals(predicate1.getExpression().toString(), "City");
        Assert.assertEquals(predicate1.getLikeExpression().toString(), "N'Pa%'");
    }

    /**
     * WHERE LastName LIKE '%and%';
     */
    @Test
    public void testExampleC(){
        // @formatter:off
        SearchCondition searchCondition = new SearchConditionBuilder<Void>()
                .withPredicate(
                        p_like(e("LastName"),
                                e_string("%and%"))
                )
                .build();
        // @formatter:on
        Assert.assertEquals(searchCondition.getPredicate().getClass(), Like.class);
        Like predicate = (Like) searchCondition.getPredicate();
        Assert.assertEquals(predicate.getExpression().toString(), "LastName");
        Assert.assertEquals(predicate.getLikeExpression().toString(), "'%and%'");
    }

    /**
     * WHERE LastName LIKE N'%and%';
     */
    @Test
    public void testExampleD(){
        // @formatter:off
        SearchCondition searchCondition = new SearchConditionBuilder<Void>()
                .withPredicate(
                        p_like(e("LastName"),
                                e_n_string("%and%"))
                )
                .build();
        // @formatter:on
        Assert.assertEquals(searchCondition.getPredicate().getClass(), Like.class);
        Like predicate = (Like) searchCondition.getPredicate();
        Assert.assertEquals(predicate.getExpression().toString(), "LastName");
        Assert.assertEquals(predicate.getLikeExpression().toString(), "N'%and%'");
    }

}
