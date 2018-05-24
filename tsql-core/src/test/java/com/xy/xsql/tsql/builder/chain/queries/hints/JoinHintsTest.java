package com.xy.xsql.tsql.builder.chain.queries.hints;

import com.xy.xsql.tsql.model.queries.hints.JoinHint;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.queries.hints.JoinHints.$Hash;
import static com.xy.xsql.tsql.builder.chain.queries.hints.JoinHints.$Loop;
import static com.xy.xsql.tsql.builder.chain.queries.hints.JoinHints.$Merge;
import static org.junit.Assert.*;

/**
 * Created by xiaoyao9184 on 2017/3/12.
 */
public class JoinHintsTest {

    /**
     * HASH
     */
    @Test
    public void testExampleA() {
        JoinHint joinHint = $Hash();

        assertEquals(joinHint,JoinHint.HASH);
    }


    /**
     * LOOP
     */
    @Test
    public void testExampleB() {
        JoinHint joinHint = $Loop();

        assertEquals(joinHint,JoinHint.LOOP);
    }

    /**
     * MERGE
     */
    @Test
    public void testExampleC() {
        JoinHint joinHint = $Merge();

        assertEquals(joinHint,JoinHint.MERGE);
    }

}
