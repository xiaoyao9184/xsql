package com.xy.xsql.tsql.model.statements.alter.table;

/**
 * Created by xiaoyao9184 on 2017/9/17
 */
public class AlterTableOption implements Item {

    private LockEscalation lockEscalation;

    public LockEscalation getLockEscalation() {
        return lockEscalation;
    }

    public void setLockEscalation(LockEscalation lockEscalation) {
        this.lockEscalation = lockEscalation;
    }

    public enum LockEscalation {
        AUTO,
        TABLE,
        DISABLE
    }
}
