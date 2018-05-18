package com.xy.xsql.tsql.core.clause.select;

import com.xy.xsql.tsql.core.MockParent;
import com.xy.xsql.tsql.core.MockParentBuilder;
import com.xy.xsql.tsql.model.queries.select.Having;
import com.xy.xsql.tsql.model.queries.predicates.Comparison;
import org.junit.Assert;
import org.junit.Test;

import static com.xy.xsql.tsql.core.expression.Expressions.e;
import static com.xy.xsql.tsql.core.expression.Expressions.e_number;
import static com.xy.xsql.tsql.core.predicate.Predicates.p_greater;
import static com.xy.xsql.tsql.model.elements.operators.Operators.GREATER;

/**
 * Created by xiaoyao9184 on 2017/1/18.
 */
public class HavingBuilderTest {

    /*
    Examples
    See https://docs.microsoft.com/en-us/sql/t-sql/queries/select-having-transact-sql#examples
     */

    // @formatter:off
    //parent+quick
    /**
     * HAVING SUM(LineTotal) > 100000.00
     */
    public Having example = new MockParentBuilder<HavingBuilder<MockParent<Having>>,Having>
                (HavingBuilder.class,Having.class)
                .$child()
                    .$(p_greater(
                            e("SUM(LineTotal)"),
                            e_number(100000.00)
                    ))
                    .and()
                .get();
    // @formatter:on

    /**
     * HAVING SUM(LineTotal) > 100000.00
     */
    @Test
    public void testExample(){
        // @formatter:off
        Having having = new HavingBuilder<Void>()
                .withPredicate()._Comparison()
                    .withExpression(e("SUM(LineTotal)"))
                    .withOperator(GREATER)
                    .withExpression(e_number(100000.00))
                    .and()
                .build();
        // @formatter:on

        Assert.assertEquals(having.getSearchCondition().getPredicate().getClass() ,Comparison.class);
        Comparison predicate = (Comparison) having.getSearchCondition().getPredicate();
        Assert.assertEquals(predicate.getExpression().toString(), "SUM(LineTotal)");
        Assert.assertEquals(predicate.getOperatorExpression().toString(), "100000.0");
    }

}
