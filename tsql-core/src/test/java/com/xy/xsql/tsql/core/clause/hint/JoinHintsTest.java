package com.xy.xsql.tsql.core.clause.hint;

import com.xy.xsql.tsql.model.clause.hints.JoinHint;
import org.junit.Assert;
import org.junit.Test;

import static com.xy.xsql.tsql.core.clause.hints.JoinHints.*;

/**
 * Created by xiaoyao9184 on 2017/3/12.
 */
public class JoinHintsTest {

    /**
     * HASH
     */
    @Test
    public void testExampleA() {
        JoinHint joinHint = HASH();

        Assert.assertEquals(joinHint,JoinHint.HASH);
    }


    /**
     * LOOP
     */
    @Test
    public void testExampleB() {
        JoinHint joinHint = LOOP();

        Assert.assertEquals(joinHint,JoinHint.LOOP);
    }

    /**
     * MERGE
     */
    @Test
    public void testExampleC() {
        JoinHint joinHint = MERGE();

        Assert.assertEquals(joinHint,JoinHint.MERGE);
    }

}
