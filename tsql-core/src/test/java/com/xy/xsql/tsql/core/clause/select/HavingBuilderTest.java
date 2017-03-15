package com.xy.xsql.tsql.core.clause.select;

import com.xy.xsql.tsql.model.clause.select.Having;
import com.xy.xsql.tsql.model.predicate.Comparison;
import org.junit.Assert;
import org.junit.Test;

import static com.xy.xsql.tsql.core.clause.SearchConditionBuilder.OperatorPredicateBuilder.GREATER;
import static com.xy.xsql.tsql.core.expression.ExpressionBuilder.e;
import static com.xy.xsql.tsql.core.expression.ExpressionBuilder.e_number;

/**
 * Created by xiaoyao9184 on 2017/1/18.
 */
public class HavingBuilderTest {

    /**
     * HAVING SUM(LineTotal) > 100000.00
     */
    @Test
    public void testExample(){
        Having having = new HavingBuilder<Void>()
                .withSearchCondition()
                    .withPredicate(
                            GREATER(e("SUM(LineTotal)"),
                                    e_number(100000.00))
                    )
                    .and()
                .build();

        Assert.assertEquals(having.getSearchCondition().getPredicate().getClass() ,Comparison.class);
        Comparison predicate = (Comparison) having.getSearchCondition().getPredicate();
        Assert.assertEquals(predicate.getExpression().toString(), "SUM(LineTotal)");
        Assert.assertEquals(predicate.getOperatorExpression().toString(), "100000.0");
    }

}
