package com.xy.xsql.tsql.builder.chain.queries.hints;

import com.xy.xsql.tsql.model.queries.hints.JoinHint;

/**
 * JoinHintFactory
 * Created by xiaoyao9184 on 2017/3/12.
 */
public class JoinHints {

    /*
    Quick build
     */

    /**
     * Quick build
     * @return
     */
    public static JoinHint LOOP(){
        return JoinHint.LOOP;
    }

    /**
     * Quick build
     * @return
     */
    public static JoinHint HASH(){
        return JoinHint.HASH;
    }

    /**
     * Quick build
     * @return
     */
    public static JoinHint MERGE(){
        return JoinHint.MERGE;
    }

    /**
     * Quick build
     * @return
     */
    public static JoinHint REMOTE(){
        return JoinHint.REMOTE;
    }

}
