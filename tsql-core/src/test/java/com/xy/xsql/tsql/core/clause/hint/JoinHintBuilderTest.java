package com.xy.xsql.tsql.core.clause.hint;

import com.xy.xsql.tsql.model.clause.hints.JoinHint;
import org.junit.Assert;
import org.junit.Test;

import static com.xy.xsql.tsql.core.clause.hints.JoinHintBuilder.*;

/**
 * Created by xiaoyao9184 on 2017/3/12.
 */
public class JoinHintBuilderTest {

    /**
     * LEFT OUTER HASH JOIN Production.ProductReview AS pr
     */
    @Test
    public void testExampleA() {
        JoinHint joinHint = HASH();

        Assert.assertEquals(joinHint,JoinHint.HASH);
    }


    /**
     * INNER LOOP JOIN Sales.SalesPerson AS sp
     */
    @Test
    public void testExampleB() {
        JoinHint joinHint = LOOP();

        Assert.assertEquals(joinHint,JoinHint.LOOP);
    }

    /**
     * INNER MERGE JOIN Purchasing.PurchaseOrderDetail AS pod
     */
    @Test
    public void testExampleC() {
        JoinHint joinHint = MERGE();

        Assert.assertEquals(joinHint,JoinHint.MERGE);
    }

}
