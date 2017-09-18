package com.xy.xsql.tsql.model.statement.ddl.alter.table;

/**
 * Created by xiaoyao9184 on 2017/9/17
 */
public class LowPriorityLockWait {

    private String time;
    private boolean useMinutes;
    private AbortAfterWait abortAfterWait;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public boolean isUseMinutes() {
        return useMinutes;
    }

    public void setUseMinutes(boolean useMinutes) {
        this.useMinutes = useMinutes;
    }

    public AbortAfterWait getAbortAfterWait() {
        return abortAfterWait;
    }

    public void setAbortAfterWait(AbortAfterWait abortAfterWait) {
        this.abortAfterWait = abortAfterWait;
    }

    public enum AbortAfterWait {
        NONE,
        SELF,
        BLOCKERS
    }
}
