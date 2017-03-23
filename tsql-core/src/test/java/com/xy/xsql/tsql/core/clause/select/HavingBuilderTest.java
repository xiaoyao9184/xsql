package com.xy.xsql.tsql.core.clause.select;

import com.xy.xsql.tsql.core.MockParent;
import com.xy.xsql.tsql.core.MockParentBuilder;
import com.xy.xsql.tsql.model.clause.select.Having;
import com.xy.xsql.tsql.model.predicate.Comparison;
import org.junit.Assert;
import org.junit.Test;

import static com.xy.xsql.tsql.core.expression.Expressions.e;
import static com.xy.xsql.tsql.core.expression.Expressions.e_number;
import static com.xy.xsql.tsql.core.predicate.Predicates.*;
import static com.xy.xsql.tsql.model.operator.Operators.GREATER;

/**
 * Created by xiaoyao9184 on 2017/1/18.
 */
public class HavingBuilderTest {

    /**
     * HAVING SUM(LineTotal) > 100000.00
     */
    @Test
    public void testExample(){
        // @formatter:off
        Having having = new HavingBuilder<Void>()
                .withSearchCondition()
                    .withPredicate()._Comparison()
                        .withExpression(e("SUM(LineTotal)"))
                        .withOperator(GREATER)
                        .withExpression(e_number(100000.00))
                        .and()
                    .and()
                .build();

        //parent+quick
        MockParent<Having> parent = new MockParentBuilder<HavingBuilder<MockParent<Having>>,Having>
                (HavingBuilder.class,Having.class)
                .$child()
                    .$Predicate(
                        p_greater(
                                e("SUM(LineTotal)"),
                                e_number(100000.00))
                    )
                    .and();
        // @formatter:on

        Assert.assertEquals(having.getSearchCondition().getPredicate().getClass() ,Comparison.class);
        Comparison predicate = (Comparison) having.getSearchCondition().getPredicate();
        Assert.assertEquals(predicate.getExpression().toString(), "SUM(LineTotal)");
        Assert.assertEquals(predicate.getOperatorExpression().toString(), "100000.0");
    }

}
