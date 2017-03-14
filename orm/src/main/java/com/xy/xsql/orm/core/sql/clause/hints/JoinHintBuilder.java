package com.xy.xsql.orm.core.sql.clause.hints;

import com.xy.xsql.tsql.model.clause.hints.JoinHint;

/**
 * Created by xiaoyao9184 on 2017/3/12.
 */
public class JoinHintBuilder {

    public static JoinHint LOOP(){
        return JoinHint.LOOP;
    }
    public static JoinHint HASH(){
        return JoinHint.HASH;
    }
    public static JoinHint MERGE(){
        return JoinHint.MERGE;
    }
    public static JoinHint REMOTE(){
        return JoinHint.REMOTE;
    }

}
