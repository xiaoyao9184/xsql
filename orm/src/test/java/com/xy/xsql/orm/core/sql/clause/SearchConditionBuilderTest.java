package com.xy.xsql.orm.core.sql.clause;

import com.xy.xsql.tsql.model.clause.SearchCondition;
import com.xy.xsql.orm.data.sql.element.OperatorEnum;
import com.xy.xsql.tsql.model.predicate.In;
import com.xy.xsql.tsql.model.predicate.Like;
import com.xy.xsql.tsql.model.predicate.Operator;
import com.xy.xsql.tsql.model.predicate.OperatorSubQuery;
import com.xy.xsql.tsql.model.statement.dml.Select;
import org.junit.Assert;
import org.junit.Test;

import static com.xy.xsql.orm.core.sql.ExpressionBuilder.*;
import static com.xy.xsql.orm.core.sql.clause.SearchConditionBuilder.OperatorPredicateBuilder.EQUAL;
import static com.xy.xsql.orm.core.sql.clause.SearchConditionBuilder.ASAPredicateBuilder.ALL;
import static com.xy.xsql.orm.core.sql.clause.SearchConditionBuilder.ASAPredicateBuilder.EQUAL;
import static com.xy.xsql.orm.core.sql.clause.SearchConditionBuilder.LikePredicateBuilder.LIKE;
import static com.xy.xsql.orm.core.sql.clause.SearchConditionBuilder.OperatorPredicateBuilder.GREATER;
import static com.xy.xsql.orm.core.sql.clause.SearchConditionBuilder.OperatorPredicateBuilder.LESS;

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
                        EQUAL(e("CountryRegionCode"),
                                ALL(),
                                select)
                )
                .build();
        // @formatter:on
        Assert.assertEquals(searchCondition.getPredicate().getClass(), OperatorSubQuery.class);
        OperatorSubQuery predicate = (OperatorSubQuery) searchCondition.getPredicate();
        Assert.assertEquals(predicate.getExpression().toString(), "CountryRegionCode");
        Assert.assertEquals(predicate.getOperatorEnum(), OperatorEnum.EQUAL);
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
                        EQUAL(e("CountryRegionCode"),
                                ALL(),
                                select)
                )
                .withAndPredicate(
                        EQUAL(e("CountryRegionCode"),
                                e_number(20)))
                .build();
        // @formatter:on
        Assert.assertEquals(searchCondition.getPredicate().getClass(), OperatorSubQuery.class);
        OperatorSubQuery predicate = (OperatorSubQuery) searchCondition.getPredicate();
        Assert.assertEquals(predicate.getExpression().toString(), "CountryRegionCode");
        Assert.assertEquals(predicate.getOperatorEnum(), OperatorEnum.EQUAL);

        Assert.assertEquals(searchCondition.getAndOrList().size(), 1);
        Operator predicate1 = (Operator) searchCondition.getAndOrList().get(0).getPredicate();
        Assert.assertEquals(predicate1.getExpression().toString(), "CountryRegionCode");
        Assert.assertEquals(predicate1.getOperatorEnum(), OperatorEnum.EQUAL);
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
                        EQUAL(e("CountryRegionCode"),
                                ALL(),
                                select)
                )
                .withAndNotSearchCondition()
                    .withPredicate(
                            GREATER(e("CountryRegionCode"),
                                e_number(20))
                    )
                    .withAndPredicate(
                            LESS(e("CountryRegionCode"),
                                    e_number(50))
                    )
                    .and()
                .build();
        // @formatter:on
        Assert.assertEquals(searchCondition.getPredicate().getClass(), OperatorSubQuery.class);
        OperatorSubQuery predicate = (OperatorSubQuery) searchCondition.getPredicate();
        Assert.assertEquals(predicate.getExpression().toString(), "CountryRegionCode");
        Assert.assertEquals(predicate.getOperatorEnum(), OperatorEnum.EQUAL);


        Assert.assertEquals(searchCondition.getAndOrList().size(), 1);
        Operator predicate1 = (Operator) searchCondition.getAndOrList().get(0).getSearchCondition().getPredicate();
        Assert.assertEquals(predicate1.getExpression().toString(), "CountryRegionCode");
        Assert.assertEquals(predicate1.getOperatorEnum(), OperatorEnum.GREATER);

        Assert.assertEquals(searchCondition.getAndOrList().get(0).getSearchCondition().getAndOrList().size(), 1);
        Operator predicate2 = (Operator) searchCondition.getAndOrList().get(0).getSearchCondition().getAndOrList().get(0).getPredicate();
        Assert.assertEquals(predicate2.getExpression().toString(), "CountryRegionCode");
        Assert.assertEquals(predicate2.getOperatorEnum(), OperatorEnum.LESS);

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
                        LIKE(
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
                        IN(e("CountryRegionCode"),
                                e_string("US"))
                )
                .withAndPredicate(
                        LIKE(e("City"),
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
                        LIKE(e("LastName"),
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
                        LIKE(e("LastName"),
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
