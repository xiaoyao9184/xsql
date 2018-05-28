package com.xy.xsql.tsql.style.constructor.introducer.queries.select;

import com.xy.xsql.tsql.model.datatypes.constants.NumberConstant;
import com.xy.xsql.tsql.model.queries.predicates.Comparison;
import com.xy.xsql.tsql.model.queries.select.Having;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.elements.expressions.Expressions.e;
import static com.xy.xsql.tsql.builder.chain.elements.expressions.Expressions.e_number;
import static com.xy.xsql.tsql.builder.chain.queries.predicates.Predicates.p_greater;
import static com.xy.xsql.tsql.style.constructor.introducer.queries.select.having.HAVING;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by xiaoyao9184 on 2018/5/9.
 */
public class havingTest {

    /*
    Examples
    See https://docs.microsoft.com/en-us/sql/t-sql/queries/select-having-transact-sql#examples
     */

    // @formatter:off
    //parent+quick
    /**
     * HAVING SUM(LineTotal) > 100000.00
     */
    public Having example = HAVING(
            p_greater(
                    e("SUM(LineTotal)"),
                    e_number(100000.00)
            )
    ).build();
    // @formatter:on

    /**
     * HAVING SUM(LineTotal) > 100000.00
     */
    @Test
    public void testExample(){
        assertEquals(example.getSearchCondition().getPredicate().getClass() ,Comparison.class);
        Comparison predicate = (Comparison) example.getSearchCondition().getPredicate();
        assertEquals(predicate.getExpression().toString(), "SUM(LineTotal)");
        assertTrue(predicate.getOperatorExpression() instanceof NumberConstant);
        assertEquals(((NumberConstant)predicate.getOperatorExpression()).getNumber().toString(), "100000.0");
    }

}