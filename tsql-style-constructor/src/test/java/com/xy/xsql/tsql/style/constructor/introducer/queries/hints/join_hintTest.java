package com.xy.xsql.tsql.style.constructor.introducer.queries.hints;

import com.xy.xsql.tsql.model.queries.hints.JoinHint;
import org.junit.Test;

import static com.xy.xsql.tsql.style.constructor.introducer.queries.hints.join_hint.*;
import static org.junit.Assert.*;

/**
 * Created by xiaoyao9184 on 2018/5/10.
 */
public class join_hintTest {

    /**
     * HASH
     */
    @Test
    public void testExampleA() {
        JoinHint joinHint = HASH().build();

        assertEquals(joinHint,JoinHint.HASH);
    }


    /**
     * LOOP
     */
    @Test
    public void testExampleB() {
        JoinHint joinHint = LOOP().build();

        assertEquals(joinHint,JoinHint.LOOP);
    }

    /**
     * MERGE
     */
    @Test
    public void testExampleC() {
        JoinHint joinHint = MERGE().build();

        assertEquals(joinHint,JoinHint.MERGE);
    }

}