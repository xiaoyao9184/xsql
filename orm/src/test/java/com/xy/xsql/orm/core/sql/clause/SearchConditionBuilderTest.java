package com.xy.xsql.orm.core.sql.clause;

import com.xy.xsql.orm.data.sql.clause.SearchCondition;
import com.xy.xsql.orm.data.sql.element.OperatorEnum;
import com.xy.xsql.orm.data.sql.statements.dml.Select;
import org.junit.Assert;
import org.junit.Test;

import static com.xy.xsql.orm.core.sql.ExpressionBuilder.*;
import static com.xy.xsql.orm.core.sql.clause.SearchConditionBuilder.OperatorPredicateBuilder.EQUAL;
import static com.xy.xsql.orm.core.sql.clause.SearchConditionBuilder.ASAPredicateBuilder.ALL;
import static com.xy.xsql.orm.core.sql.clause.SearchConditionBuilder.ASAPredicateBuilder.EQUAL;
import static com.xy.xsql.orm.core.sql.clause.SearchConditionBuilder.InPredicateBuilder.IN;
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
        Assert.assertEquals(searchCondition.getPredicate().getType(), SearchCondition.PredicateType.All);
        Assert.assertEquals(searchCondition.getPredicate().getExpression().toString(), "CountryRegionCode");
        Assert.assertEquals(searchCondition.getPredicate().getOperatorEnum(), OperatorEnum.EQUAL);
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
                .withAnd()
                    .withPredicate(
                            EQUAL(e("CountryRegionCode"),
                                    e_number(20))
                    )
                    .and()
                .build();
        // @formatter:on
        Assert.assertEquals(searchCondition.getPredicate().getType(), SearchCondition.PredicateType.All);
        Assert.assertEquals(searchCondition.getPredicate().getExpression().toString(), "CountryRegionCode");
        Assert.assertEquals(searchCondition.getPredicate().getOperatorEnum(), OperatorEnum.EQUAL);


        Assert.assertEquals(searchCondition.getAndOrList().size(), 1);
        Assert.assertEquals(searchCondition.getAndOrList().get(0).getPredicate().getType(), SearchCondition.PredicateType.Operator);
        Assert.assertEquals(searchCondition.getAndOrList().get(0).getPredicate().getExpression().toString(), "CountryRegionCode");
        Assert.assertEquals(searchCondition.getAndOrList().get(0).getPredicate().getOperatorEnum(), OperatorEnum.EQUAL);
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
        Assert.assertEquals(searchCondition.getPredicate().getType(), SearchCondition.PredicateType.All);
        Assert.assertEquals(searchCondition.getPredicate().getExpression().toString(), "CountryRegionCode");
        Assert.assertEquals(searchCondition.getPredicate().getOperatorEnum(), OperatorEnum.EQUAL);


        Assert.assertEquals(searchCondition.getAndOrList().size(), 1);
        Assert.assertEquals(searchCondition.getAndOrList().get(0).getSearchCondition().getPredicate().getType(), SearchCondition.PredicateType.Operator);
        Assert.assertEquals(searchCondition.getAndOrList().get(0).getSearchCondition().getPredicate().getExpression().toString(), "CountryRegionCode");
        Assert.assertEquals(searchCondition.getAndOrList().get(0).getSearchCondition().getPredicate().getOperatorEnum(), OperatorEnum.GREATER);
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
        Assert.assertEquals(searchCondition.getPredicate().getType(), SearchCondition.PredicateType.Like);
        Assert.assertEquals(searchCondition.getPredicate().getExpression().toString(), "LargePhotoFileName");
        Assert.assertEquals(searchCondition.getPredicate().getOperatorExpression().toString(), "'%greena_%'");
        Assert.assertEquals(searchCondition.getPredicate().getEscapeCharacter().toString(), "'a'");
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
        Assert.assertEquals(searchCondition.getPredicate().getType(), SearchCondition.PredicateType.In);
        Assert.assertEquals(searchCondition.getPredicate().getExpression().toString(), "CountryRegionCode");
        Assert.assertEquals(searchCondition.getPredicate().getExpressionList().get(0).toString(), "'US'");

        Assert.assertEquals(searchCondition.getAndOrList().size(), 1);
        Assert.assertEquals(searchCondition.getAndOrList().get(0).getPredicate().getType(), SearchCondition.PredicateType.Like);
        Assert.assertEquals(searchCondition.getAndOrList().get(0).getPredicate().getExpression().toString(), "City");
        Assert.assertEquals(searchCondition.getAndOrList().get(0).getPredicate().getOperatorExpression().toString(), "N'Pa%'");
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
        Assert.assertEquals(searchCondition.getPredicate().getType(), SearchCondition.PredicateType.Like);
        Assert.assertEquals(searchCondition.getPredicate().getExpression().toString(), "LastName");
        Assert.assertEquals(searchCondition.getPredicate().getOperatorExpression().toString(), "'%and%'");
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
        Assert.assertEquals(searchCondition.getPredicate().getType(), SearchCondition.PredicateType.Like);
        Assert.assertEquals(searchCondition.getPredicate().getExpression().toString(), "LastName");
        Assert.assertEquals(searchCondition.getPredicate().getOperatorExpression().toString(), "N'%and%'");
    }

}
