package com.xy.xsql.tsql.builder.chain.queries.hints;

import com.xy.xsql.tsql.model.queries.hints.JoinHint;

/**
 * JoinHint Factory
 * Created by xiaoyao9184 on 2017/3/12.
 */
@SuppressWarnings("unused")
public class JoinHints {

    /*
    Quick build
     */

    /**
     * Quick build
     * @return JoinHint
     */
    public static JoinHint $Loop(){
        return JoinHint.LOOP;
    }

    /**
     * Quick build
     * @return JoinHint
     */
    public static JoinHint $Hash(){
        return JoinHint.HASH;
    }

    /**
     * Quick build
     * @return JoinHint
     */
    public static JoinHint $Merge(){
        return JoinHint.MERGE;
    }

    /**
     * Quick build
     * @return JoinHint
     */
    public static JoinHint $Remote(){
        return JoinHint.REMOTE;
    }

}
