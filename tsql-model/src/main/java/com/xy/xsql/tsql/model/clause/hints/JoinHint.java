package com.xy.xsql.tsql.model.clause.hints;

/**
 * https://msdn.microsoft.com/en-us/library/ms173815.aspx
 *
 * <join_hint>
 *
 * Created by xiaoyao9184 on 2016/12/22.
 */
public enum JoinHint {
    LOOP,
    HASH,
    MERGE,
    REMOTE;

    @Override
    public String toString() {
        return this.name();
    }

}
