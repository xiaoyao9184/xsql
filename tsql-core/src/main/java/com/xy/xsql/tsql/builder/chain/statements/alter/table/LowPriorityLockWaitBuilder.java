package com.xy.xsql.tsql.builder.chain.statements.alter.table;

import com.xy.xsql.core.builder.CodeTreeBuilder;
import com.xy.xsql.tsql.model.statements.alter.table.LowPriorityLockWait;

/**
 * LowPriorityLockWaitBuilder
 * Created by xiaoyao9184 on 2017/9/16.
 */
@SuppressWarnings({"WeakerAccess", "unused"})
public class LowPriorityLockWaitBuilder<ParentBuilder>
        extends CodeTreeBuilder<LowPriorityLockWaitBuilder<ParentBuilder>,ParentBuilder,LowPriorityLockWait> {

    public LowPriorityLockWaitBuilder(LowPriorityLockWait target) {
        super(target);
    }

    /**
     * set
     * @param time time
     * @return THIS
     */
    public LowPriorityLockWaitBuilder<ParentBuilder> withTime(String time){
        target.setTime(time);
        return this;
    }

    /**
     * set
     * @param useMinutes minutes
     * @return THIS
     */
    public LowPriorityLockWaitBuilder<ParentBuilder> withUseMinutes(boolean useMinutes){
        target.setUseMinutes(useMinutes);
        return this;
    }

    /**
     * set
     * @param abortAfterWait AbortAfterWait
     * @return THIS
     */
    public LowPriorityLockWaitBuilder<ParentBuilder> withAbortAfterWait(LowPriorityLockWait.AbortAfterWait abortAfterWait){
        target.setAbortAfterWait(abortAfterWait);
        return this;
    }




    /*
    Quick
     */

    /**
     * Quick set
     * @param time time
     * @return THIS
     */
    public LowPriorityLockWaitBuilder<ParentBuilder> $WaitAtLowPriority$MaxDuration(String time){
        return withTime(time);
    }

    /**
     * Quick set
     * @return THIS
     */
    public LowPriorityLockWaitBuilder<ParentBuilder> $Minutes(){
        return withUseMinutes(true);
    }

    /**
     * Quick set
     * @return THIS
     */
    public LowPriorityLockWaitBuilder<ParentBuilder> $AbortAfterWait$None(){
        return withAbortAfterWait(LowPriorityLockWait.AbortAfterWait.NONE);
    }

    /**
     * Quick set
     * @return THIS
     */
    public LowPriorityLockWaitBuilder<ParentBuilder> $AbortAfterWait$Self(){
        return withAbortAfterWait(LowPriorityLockWait.AbortAfterWait.SELF);
    }

    /**
     * Quick set
     * @return THIS
     */
    public LowPriorityLockWaitBuilder<ParentBuilder> $AbortAfterWait$Blockers(){
        return withAbortAfterWait(LowPriorityLockWait.AbortAfterWait.BLOCKERS);
    }

}
