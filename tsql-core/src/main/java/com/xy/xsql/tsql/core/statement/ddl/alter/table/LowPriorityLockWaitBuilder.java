package com.xy.xsql.tsql.core.statement.ddl.alter.table;

import com.xy.xsql.core.builder.CodeTreeBuilder;
import com.xy.xsql.tsql.model.statements.alter.table.LowPriorityLockWait;

/**
 * Created by xiaoyao9184 on 2017/9/16.
 */
public class LowPriorityLockWaitBuilder<ParentBuilder>
        extends CodeTreeBuilder<LowPriorityLockWaitBuilder<ParentBuilder>,ParentBuilder,LowPriorityLockWait> {

    public LowPriorityLockWaitBuilder(LowPriorityLockWait target) {
        super(target);
    }

    public LowPriorityLockWaitBuilder<ParentBuilder> withTime(String time){
        target.setTime(time);
        return this;
    }

    public LowPriorityLockWaitBuilder<ParentBuilder> withUseMinutes(boolean useMinutes){
        target.setUseMinutes(useMinutes);
        return this;
    }

    public LowPriorityLockWaitBuilder<ParentBuilder> withAbortAfterWait(LowPriorityLockWait.AbortAfterWait abortAfterWait){
        target.setAbortAfterWait(abortAfterWait);
        return this;
    }

    /*
    Quick
     */

    public LowPriorityLockWaitBuilder<ParentBuilder> $WAIT_AT_LOW_PRIORITY_$MAX_DURATION(String time){
        return withTime(time);
    }

    public LowPriorityLockWaitBuilder<ParentBuilder> $MINUTES(){
        return withUseMinutes(true);
    }

    public LowPriorityLockWaitBuilder<ParentBuilder> $ABORT_AFTER_WAIT_$NONE(){
        return withAbortAfterWait(LowPriorityLockWait.AbortAfterWait.NONE);
    }

    public LowPriorityLockWaitBuilder<ParentBuilder> $ABORT_AFTER_WAIT_$SELF(){
        return withAbortAfterWait(LowPriorityLockWait.AbortAfterWait.SELF);
    }

    public LowPriorityLockWaitBuilder<ParentBuilder> $ABORT_AFTER_WAIT_$BLOCKERS(){
        return withAbortAfterWait(LowPriorityLockWait.AbortAfterWait.BLOCKERS);
    }

}
