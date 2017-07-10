package com.xy.xsql.tsql.core.clause;

import com.xy.xsql.tsql.core.MockParent;
import com.xy.xsql.tsql.core.MockParentBuilder;
import com.xy.xsql.tsql.model.clause.SearchCondition;
import com.xy.xsql.tsql.model.predicate.In;
import com.xy.xsql.tsql.model.predicate.Like;
import com.xy.xsql.tsql.model.predicate.Comparison;
import com.xy.xsql.tsql.model.predicate.ComparisonSubQuery;
import com.xy.xsql.tsql.model.statement.dml.Select;
import org.junit.Assert;
import org.junit.Test;

import static com.xy.xsql.tsql.core.element.ColumnNameFactory.c;
import static com.xy.xsql.tsql.core.expression.Expressions.*;
import static com.xy.xsql.tsql.core.predicate.Predicates.*;

/**
 * Created by xiaoyao9184 on 2017/3/11.
 */
public class SearchConditionBuilderTest {

    // @formatter:off
    /**
     * WHERE
        ( Name > '1' AND Name > '2')
        AND ( Name = '3' AND Name = '4' )
     */
    public SearchCondition useGroupTransform = new MockParentBuilder<SearchConditionBuilder<MockParent<SearchCondition>>,SearchCondition>
                (SearchConditionBuilder.class,SearchCondition.class)
                .$child()
                    .$()
                        .$(e("Name")).$Greater(e_string("3"))
                        .$(p_equal(
                                e("Name"),
                                e_string("1")
                        ))
                        .$And(p_equal(
                                e("Name"),
                                e_string("2")
                        ))
                        .and()
                    .$And()
                        .$(e("Name")).$Equal(e_string("3"))
                        .$(p_equal(
                                e("Name"),
                                e_string("3")
                        ))
                        .$And(p_equal(
                                e("Name"),
                                e_string("4")
                        ))
                        .and()
                    .and()
                .get();
    // @formatter:on

    // @formatter:off
    /**
     * WHERE
        CONTAINS ( A , 'aaa' )
        AND FREETEXT ( A , 'aaa' )
     */
    public SearchCondition useTransform = new MockParentBuilder<SearchConditionBuilder<MockParent<SearchCondition>>,SearchCondition>
                (SearchConditionBuilder.class,SearchCondition.class)
                .$child()
                    .$Contains(c("A"))
                        .$("aaa")
                    .$And_FreeText(c("A"))
                        .$("aaa")
                    .and()
                .get();
    // @formatter:on

    /**
     * WHERE CountryRegionCode == ALL ( subquery ) ;
     */
    @Test
    public void testALLSOMEANY(){
        Select select = null;
        // @formatter:off
        SearchCondition searchCondition = new SearchConditionBuilder<Void>()
                .$(p_equal(
                        e("CountryRegionCode"),
                        all(),
                        select
                ))
                .build();
        // @formatter:on
        Assert.assertEquals(searchCondition.getPredicate().getClass(), ComparisonSubQuery.class);
        ComparisonSubQuery predicate = (ComparisonSubQuery) searchCondition.getPredicate();
        Assert.assertEquals(predicate.getExpression().toString(), "CountryRegionCode");
        Assert.assertEquals(predicate.getOperator(), com.xy.xsql.tsql.model.operator.Operators.EQUAL);
    }

    /**
     * WHERE CountryRegionCode == ALL ( subquery ) AND CountryRegionCode = 20;
     */
    @Test
    public void testAnd(){
        Select select = null;
        // @formatter:off
        SearchCondition searchCondition = new SearchConditionBuilder<Void>()
                .$(p_equal(
                        e("CountryRegionCode"),
                        all(),
                        select
                ))
                .$And(p_equal(
                        e("CountryRegionCode"),
                        e_number(20)
                ))
                .build();
        // @formatter:on
        Assert.assertEquals(searchCondition.getPredicate().getClass(), ComparisonSubQuery.class);
        ComparisonSubQuery predicate = (ComparisonSubQuery) searchCondition.getPredicate();
        Assert.assertEquals(predicate.getExpression().toString(), "CountryRegionCode");
        Assert.assertEquals(predicate.getOperator(), com.xy.xsql.tsql.model.operator.Operators.EQUAL);

        Assert.assertEquals(searchCondition.getAndOrList().size(), 1);
        Comparison predicate1 = (Comparison) searchCondition.getAndOrList().get(0).getPredicate();
        Assert.assertEquals(predicate1.getExpression().toString(), "CountryRegionCode");
        Assert.assertEquals(predicate1.getOperator(), com.xy.xsql.tsql.model.operator.Operators.EQUAL);
    }

    /**
     * WHERE CountryRegionCode == ALL ( subquery ) AND NOT ( CountryRegionCode > 20 AND CountryRegionCode < 50);
     */
    @Test
    public void testAndAnd(){
        Select select = null;
        // @formatter:off
        SearchCondition searchCondition = new SearchConditionBuilder<Void>()
                .withPredicate()._ComparisonSubQuery()
                    .withExpression(e("CountryRegionCode"))
                    .withOperator(com.xy.xsql.tsql.model.operator.Operators.EQUAL)
                    .withALL_SOME_ANY(ComparisonSubQuery.ALL_SOME_ANY.ALL)
                    .withSubQuery(select)
                    .and()
                .withAndOrNotItem()
                    .withNot(true)
                    .withSearchCondition()
                        .withPredicate()._Comparison()
                            .withExpression(e("CountryRegionCode"))
                            .withOperator(com.xy.xsql.tsql.model.operator.Operators.GREATER)
                            .withExpression(e_number(20))
                            .and()
                        .withAndOrNotItem()
                            .withAnd()
                            .withPredicate()._Comparison()
                                .withExpression(e("CountryRegionCode"))
                                .withOperator(com.xy.xsql.tsql.model.operator.Operators.LESS)
                                .withExpression(e_number(50))
                                .and()
                            .and()
                        .and()
                    .and()
//                .$Predicate(
//                        p_equal(e("CountryRegionCode"),
//                                all(),
//                                select)
//                )
//                .$_AndNotSearchCondition()
//                    .$Predicate(
//                            p_greater(e("CountryRegionCode"),
//                                e_number(20))
//                    )
//                    .$_AndPredicate(
//                            p_less(e("CountryRegionCode"),
//                                    e_number(50))
//                    )
//                    .and()
                .build();
        // @formatter:on
        Assert.assertEquals(searchCondition.getPredicate().getClass(), ComparisonSubQuery.class);
        ComparisonSubQuery predicate = (ComparisonSubQuery) searchCondition.getPredicate();
        Assert.assertEquals(predicate.getExpression().toString(), "CountryRegionCode");
        Assert.assertEquals(predicate.getOperator(), com.xy.xsql.tsql.model.operator.Operators.EQUAL);


        Assert.assertEquals(searchCondition.getAndOrList().size(), 1);
        Comparison predicate1 = (Comparison) searchCondition.getAndOrList().get(0).getSearchCondition().getPredicate();
        Assert.assertEquals(predicate1.getExpression().toString(), "CountryRegionCode");
        Assert.assertEquals(predicate1.getOperator(), com.xy.xsql.tsql.model.operator.Operators.GREATER);

        Assert.assertEquals(searchCondition.getAndOrList().get(0).getSearchCondition().getAndOrList().size(), 1);
        Comparison predicate2 = (Comparison) searchCondition.getAndOrList().get(0).getSearchCondition().getAndOrList().get(0).getPredicate();
        Assert.assertEquals(predicate2.getExpression().toString(), "CountryRegionCode");
        Assert.assertEquals(predicate2.getOperator(), com.xy.xsql.tsql.model.operator.Operators.LESS);

    }


    /*
    Examples
    See https://docs.microsoft.com/zh-cn/sql/t-sql/queries/search-condition-transact-sql#examples
     */

    // @formatter:off
    //parent+quick
    /**
     * LargePhotoFileName LIKE '%greena_%' ESCAPE 'a' ;
     */
    public SearchCondition exampleA = new MockParentBuilder<SearchConditionBuilder<MockParent<SearchCondition>>,SearchCondition>
                (SearchConditionBuilder.class,SearchCondition.class)
                .$child()
                    .$(p_like(
                            e("LargePhotoFileName"),
                            e_string("%greena_%"),
                            e_string("a")
                    ))
                    .and()
                .get();
    // @formatter:on

    @Test
    public void testExampleA(){
        // @formatter:off
        SearchCondition searchCondition = new SearchConditionBuilder<Void>()
                .withPredicate()._Like()
                        .withStringExpression(e("LargePhotoFileName"))
                        .withStringExpression(e_string("%greena_%"))
                        .withEscape(e_string("a"))
                        .and()
                .build();
        // @formatter:on

        Assert.assertEquals(searchCondition.getPredicate().getClass(), Like.class);
        Like predicate = (Like) searchCondition.getPredicate();
        Assert.assertEquals(predicate.getExpression().toString(), "LargePhotoFileName");
        Assert.assertEquals(predicate.getLikeExpression().toString(), "'%greena_%'");
        Assert.assertEquals(predicate.getEscapeCharacter().toString(), "'a'");

        Assert.assertEquals(exampleA.getPredicate().getClass(), Like.class);
        Like predicate2 = (Like) exampleA.getPredicate();
        Assert.assertEquals(predicate2.getExpression().toString(), "LargePhotoFileName");
        Assert.assertEquals(predicate2.getLikeExpression().toString(), "'%greena_%'");
        Assert.assertEquals(predicate2.getEscapeCharacter().toString(), "'a'");
    }


    // @formatter:off
    //parent+quick
    /**
     * CountryRegionCode NOT IN ('US')
     AND City LIKE N'Pa%' ;
     */
    public SearchCondition exampleB = new MockParentBuilder<SearchConditionBuilder<MockParent<SearchCondition>>,SearchCondition>
                (SearchConditionBuilder.class,SearchCondition.class)
                .$child()
                    .$(p_not_in(
                            e("CountryRegionCode"),
                            e_string("US")
                    ))
                    .$And(p_like(
                            e("City"),
                            e_n_string("Pa%")
                    ))
                    .and()
                .get();
    // @formatter:on

    @Test
    public void testExampleB(){
        // @formatter:off
        SearchCondition searchCondition = new SearchConditionBuilder<Void>()
                .withPredicate()._In()
                    .withNot()
                    .withExpression(e("CountryRegionCode"))
                    .withValueExpression(e_string("US"))
                    .and()
                .withAndOrNotItem()
                    .withPredicate()._Like()
                        .withStringExpression(e("City"))
                        .withStringExpression(e_n_string("Pa%"))
                        .and()
                    .and()
//                .$Predicate(
//                        p_in(e("CountryRegionCode"),
//                                e_string("US"))
//                )
//                .$_AndPredicate(
//                        p_like(e("City"),
//                                e_n_string("Pa%"))
//                )
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


    /*
    Examples: Azure SQL Data Warehouse and Parallel Data Warehouse
    See https://docs.microsoft.com/zh-cn/sql/t-sql/queries/search-condition-transact-sql#examples-includesssdwfullincludessssdwfull-mdmd-and-includesspdwincludessspdw-mdmd
     */

    // @formatter:off
    //parent+quick
    /**
     * LastName LIKE '%and%';
     */
    public SearchCondition exampleC = new MockParentBuilder<SearchConditionBuilder<MockParent<SearchCondition>>,SearchCondition>
                (SearchConditionBuilder.class,SearchCondition.class)
                .$child()
                    .$(p_like(
                            e("LastName"),
                            e_string("%and%")
                    ))
                    .and()
                .get();
    // @formatter:on

    @Test
    public void testExampleC(){
        // @formatter:off
        SearchCondition searchCondition = new SearchConditionBuilder<Void>()
                .$(p_like(
                        e("LastName"),
                        e_string("%and%")
                ))
                .build();
        // @formatter:on

        Assert.assertEquals(searchCondition.getPredicate().getClass(), Like.class);
        Like predicate = (Like) searchCondition.getPredicate();
        Assert.assertEquals(predicate.getExpression().toString(), "LastName");
        Assert.assertEquals(predicate.getLikeExpression().toString(), "'%and%'");
    }


    // @formatter:off
    //parent+quick
    /**
     * LastName LIKE N'%and%';
     */
    public SearchCondition exampleD = new MockParentBuilder<SearchConditionBuilder<MockParent<SearchCondition>>,SearchCondition>
                (SearchConditionBuilder.class,SearchCondition.class)
                .$child()
                    .$(p_like(
                            e("LastName"),
                            e_n_string("%and%")
                    ))
                    .and()
                .get();
    // @formatter:on

    @Test
    public void testExampleD(){
        // @formatter:off
        SearchCondition searchCondition = new SearchConditionBuilder<Void>()
                .$(p_like(
                        e("LastName"),
                        e_n_string("%and%")
                ))
                .build();
        // @formatter:on

        Assert.assertEquals(searchCondition.getPredicate().getClass(), Like.class);
        Like predicate = (Like) searchCondition.getPredicate();
        Assert.assertEquals(predicate.getExpression().toString(), "LastName");
        Assert.assertEquals(predicate.getLikeExpression().toString(), "N'%and%'");
    }

}
