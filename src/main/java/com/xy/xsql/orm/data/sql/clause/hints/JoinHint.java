package com.xy.xsql.orm.data.sql.clause.hints;

import com.xy.xsql.orm.data.sql.Element;

import java.util.List;

/**
 * https://msdn.microsoft.com/en-us/library/ms173815.aspx
 *
 * <join_hint>
 *
 * Created by xiaoyao9184 on 2016/12/22.
 */
public enum JoinHint implements Element {
    LOOP,
    HASH,
    MERGE,
    REMOTE;

    @Override
    public String toString() {
        return this.name();
    }

}
