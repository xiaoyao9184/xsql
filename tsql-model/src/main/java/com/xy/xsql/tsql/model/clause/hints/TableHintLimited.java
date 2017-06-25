package com.xy.xsql.tsql.model.clause.hints;

/**
 * https://msdn.microsoft.com/en-us/library/ms187373.aspx
 *
 * <table_hint_limited>
 *
 * Created by xiaoyao9184 on 2016/12/22.
 */
public enum TableHintLimited {
    KEEPIDENTITY,
    KEEPDEFAULTS,
    HOLDLOCK,
    IGNORE_CONSTRAINTS,
    IGNORE_TRIGGERS,
    NOLOCK,
    NOWAIT,
    PAGLOCK,
    READCOMMITTED,
    READCOMMITTEDLOCK,
    READPAST,
    REPEATABLEREAD,
    ROWLOCK,
    SERIALIZABLE,
    SNAPSHOT,
    TABLOCK,
    TABLOCKX,
    UPDLOCK,
    XLOCK;

    @Override
    public String toString() {
        return this.name();
    }

}
