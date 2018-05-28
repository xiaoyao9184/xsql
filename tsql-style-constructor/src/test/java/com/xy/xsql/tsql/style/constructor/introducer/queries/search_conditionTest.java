package com.xy.xsql.tsql.style.constructor.introducer.queries;

import com.xy.xsql.tsql.builder.chain.elements.operators.Operators;
import com.xy.xsql.tsql.model.datatypes.constants.StringConstant;
import com.xy.xsql.tsql.model.queries.SearchCondition;
import com.xy.xsql.tsql.model.queries.Select;
import com.xy.xsql.tsql.model.queries.predicates.Comparison;
import com.xy.xsql.tsql.model.queries.predicates.ComparisonSubQuery;
import com.xy.xsql.tsql.model.queries.predicates.In;
import com.xy.xsql.tsql.model.queries.predicates.Like;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.datatypes.table.ColumnNameFactory.c;
import static com.xy.xsql.tsql.builder.chain.elements.expressions.Expressions.*;
import static com.xy.xsql.tsql.builder.chain.queries.predicates.Predicates.*;
import static com.xy.xsql.tsql.style.constructor.introducer.queries.predicates.contains.CONTAINS;
import static com.xy.xsql.tsql.style.constructor.introducer.queries.predicates.freetext.FREETEXT;
import static com.xy.xsql.tsql.style.constructor.introducer.queries.search_condition.$;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by xiaoyao9184 on 2018/5/9.
 */
public class search_conditionTest {

    // @formatter:off
    /**
     * WHERE
     ( Name > '1' AND Name > '2')
     ND ( Name = '3' $AND Name = '4' )
     */
    public SearchCondition useGroupTransform = $(
            $(
                    p_greater(
                            c("Name"),
                            e_string("1")
                    )
            ).AND(
                    p_greater(
                            c("Name"),
                            e_string("2")
                    )
            )
    ).AND(
            $(
                    p_equal(
                            c("Name"),
                            e_string("3")
                    )
            ).AND(
                    p_equal(
                            c("Name"),
                            e_string("4")
                    )
            )
    ).build();
    // @formatter:on

    // @formatter:off
    /**
     * WHERE
     CONTAINS ( A , 'aaa' )
     AND b_FREETEXT ( A , 'aaa' )
     */
    public SearchCondition useTransform = $(
            CONTAINS("A","aaa").build()
    ).AND(
            FREETEXT("A","aaa").build()
    ).build();
    // @formatter:on

    /**
     * WHERE CountryRegionCode == ALL ( subquery ) ;
     */
    @Test
    public void testALLSOMEANY(){
        Select select = null;
        // @formatter:off
        SearchCondition searchCondition = $(
                p_equal(
                        e("CountryRegionCode"),
                        all(),
                        select
                )
        ).build();
        // @formatter:on
        assertEquals(searchCondition.getPredicate().getClass(), ComparisonSubQuery.class);
        ComparisonSubQuery predicate = (ComparisonSubQuery) searchCondition.getPredicate();
        assertEquals(predicate.getExpression().toString(), "CountryRegionCode");
        assertEquals(predicate.getOperator(), Operators.$Equal);
    }

    /**
     * WHERE CountryRegionCode == ALL ( subquery ) AND CountryRegionCode = 20;
     */
    @Test
    public void testAnd(){
        Select select = null;
        // @formatter:off
        SearchCondition searchCondition = $(
                p_equal(
                        e("CountryRegionCode"),
                        all(),
                        select
                )
        ).AND(
                p_equal(
                        e("CountryRegionCode"),
                        e_number(20)
                )
        ).build();
        // @formatter:on
        assertEquals(searchCondition.getPredicate().getClass(), ComparisonSubQuery.class);
        ComparisonSubQuery predicate = (ComparisonSubQuery) searchCondition.getPredicate();
        assertEquals(predicate.getExpression().toString(), "CountryRegionCode");
        assertEquals(predicate.getOperator(), Operators.$Equal);

        assertEquals(searchCondition.getAndOrList().size(), 1);
        Comparison predicate1 = (Comparison) searchCondition.getAndOrList().get(0).getPredicate();
        assertEquals(predicate1.getExpression().toString(), "CountryRegionCode");
        assertEquals(predicate1.getOperator(), Operators.$Equal);
    }

    /**
     * WHERE CountryRegionCode == ALL ( subquery ) AND NOT ( CountryRegionCode > 20 AND CountryRegionCode < 50);
     */
    @Test
    public void testAndAnd(){
        Select select = null;
        // @formatter:off
        SearchCondition searchCondition = $(
                p_equal(
                        e("CountryRegionCode"),
                        all(),
                        select
                )
        ).AND_NOT(
                $(
                        p_greater(
                                e("CountryRegionCode"),
                                e_number(20)
                        )
                ).AND(
                        p_less(
                                e("CountryRegionCode"),
                                e_number(50)
                        )
                )
        ).build();
        // @formatter:on
        assertEquals(searchCondition.getPredicate().getClass(), ComparisonSubQuery.class);
        ComparisonSubQuery predicate = (ComparisonSubQuery) searchCondition.getPredicate();
        assertEquals(predicate.getExpression().toString(), "CountryRegionCode");
        assertEquals(predicate.getOperator(), Operators.$Equal);


        assertEquals(searchCondition.getAndOrList().size(), 1);
        Comparison predicate1 = (Comparison) searchCondition.getAndOrList().get(0).getSearchCondition().getPredicate();
        assertEquals(predicate1.getExpression().toString(), "CountryRegionCode");
        assertEquals(predicate1.getOperator(), Operators.$Greater);

        assertEquals(searchCondition.getAndOrList().get(0).getSearchCondition().getAndOrList().size(), 1);
        Comparison predicate2 = (Comparison) searchCondition.getAndOrList().get(0).getSearchCondition().getAndOrList().get(0).getPredicate();
        assertEquals(predicate2.getExpression().toString(), "CountryRegionCode");
        assertEquals(predicate2.getOperator(), Operators.$Less);

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
    public SearchCondition exampleA = $(
            p_like(
                    e("LargePhotoFileName"),
                    e_string("%greena_%"),
                    e_string("a")
            )
    ).build();
    // @formatter:on

    @Test
    public void testExampleA(){
        assertEquals(exampleA.getPredicate().getClass(), Like.class);
        Like predicate2 = (Like) exampleA.getPredicate();
        assertEquals(predicate2.getExpression().toString(), "LargePhotoFileName");
        assertTrue(predicate2.getLikeExpression() instanceof StringConstant);
        assertEquals(((StringConstant)predicate2.getLikeExpression()).getString(), "%greena_%");
        assertEquals(predicate2.getEscapeCharacter().getString(), "a");
    }


    // @formatter:off
    //parent+quick
    /**
     * CountryRegionCode NOT IN ('US')
     AND City LIKE N'Pa%' ;
     */
    public SearchCondition exampleB = $(
            p_not_in(
                    e("CountryRegionCode"),
                    e_string("US")
            )
    ).AND(
            p_like(
                    e("City"),
                    e_n_string("Pa%")
            )
    ).build();
    // @formatter:on

    @Test
    public void testExampleB(){
        assertEquals(exampleB.getPredicate().getClass(), In.class);
        In predicate = (In) exampleB.getPredicate();
        assertEquals(predicate.getExpression().toString(), "CountryRegionCode");
        assertTrue(predicate.getExpressionList().get(0) instanceof StringConstant);
        assertEquals(((StringConstant)predicate.getExpressionList().get(0)).getString(), "US");

        assertEquals(exampleB.getAndOrList().size(), 1);
        assertEquals(exampleB.getAndOrList().get(0).getPredicate().getClass(), Like.class);
        Like predicate1 = (Like) exampleB.getAndOrList().get(0).getPredicate();
        assertEquals(predicate1.getExpression().toString(), "City");
        assertTrue(predicate1.getLikeExpression() instanceof StringConstant);
        assertEquals(((StringConstant)predicate1.getLikeExpression()).getString(), "Pa%");
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
    public SearchCondition exampleC = $(
            p_like(
                    e("LastName"),
                    e_string("%and%")
            )
    ).build();
    // @formatter:on

    @Test
    public void testExampleC(){
        assertEquals(exampleC.getPredicate().getClass(), Like.class);
        Like predicate = (Like) exampleC.getPredicate();
        assertEquals(predicate.getExpression().toString(), "LastName");
        assertTrue(predicate.getLikeExpression() instanceof StringConstant);
        assertEquals(((StringConstant)predicate.getLikeExpression()).getString(), "%and%");
    }


    // @formatter:off
    //parent+quick
    /**
     * LastName LIKE N'%and%';
     */
    public SearchCondition exampleD = $(
            p_like(
                    e("LastName"),
                    e_n_string("%and%")
            )
    ).build();
    // @formatter:on

    @Test
    public void testExampleD(){
        assertEquals(exampleD.getPredicate().getClass(), Like.class);
        Like predicate = (Like) exampleD.getPredicate();
        assertEquals(predicate.getExpression().toString(), "LastName");
        assertTrue(predicate.getLikeExpression() instanceof StringConstant);
        assertEquals(((StringConstant)predicate.getLikeExpression()).getString(), "%and%");
    }

}