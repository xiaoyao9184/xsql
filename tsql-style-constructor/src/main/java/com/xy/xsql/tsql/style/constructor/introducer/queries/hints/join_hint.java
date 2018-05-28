package com.xy.xsql.tsql.style.constructor.introducer.queries.hints;

import com.xy.xsql.tsql.model.queries.hints.JoinHint;
import com.xy.xsql.tsql.style.constructor.builder.queries.hints.b$join_hint;

/**
 * Created by xiaoyao9184 on 2018/5/9.
 */
@SuppressWarnings({"WeakerAccess", "unused"})
public interface join_hint {

    static b$join_hint LOOP(){
        return new b$join_hint(JoinHint.LOOP);
    }
    static b$join_hint HASH(){
        return new b$join_hint(JoinHint.HASH);
    }
    static b$join_hint MERGE(){
        return new b$join_hint(JoinHint.MERGE);
    }
    static b$join_hint REMOTE(){
        return new b$join_hint(JoinHint.REMOTE);
    }
}
