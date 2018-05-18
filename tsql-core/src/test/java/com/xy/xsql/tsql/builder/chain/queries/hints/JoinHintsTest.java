package com.xy.xsql.tsql.builder.chain.queries.hints;

import com.xy.xsql.tsql.model.queries.hints.JoinHint;
import org.junit.Assert;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.queries.hints.JoinHints.HASH;
import static com.xy.xsql.tsql.builder.chain.queries.hints.JoinHints.LOOP;
import static com.xy.xsql.tsql.builder.chain.queries.hints.JoinHints.MERGE;

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
