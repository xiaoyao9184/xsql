package com.xy.xsql.tsql.builder.chain.statements.alter.table;

import com.xy.xsql.core.builder.CodeTreeBuilder;
import com.xy.xsql.tsql.model.statements.alter.table.AlterTableOption;

/**
 * Created by xiaoyao9184 on 2017/9/16.
 */
public class AlterTableOptionBuilder<ParentBuilder>
        extends CodeTreeBuilder<AlterTableOptionBuilder<ParentBuilder>,ParentBuilder,AlterTableOption> {

    public AlterTableOptionBuilder(AlterTableOption target) {
        super(target);
    }

    public AlterTableOptionBuilder<ParentBuilder> withLockEscalation(AlterTableOption.LockEscalation lockEscalation){
        target.setLockEscalation(lockEscalation);
        return this;
    }

    /*
    Quick
     */

    public AlterTableOptionBuilder<ParentBuilder> $SET_$LOCK_ESCALATION_$AUTO(){
        return withLockEscalation(AlterTableOption.LockEscalation.AUTO);
    }

    public AlterTableOptionBuilder<ParentBuilder> $SET_$LOCK_ESCALATION_$TABLE(){
        return withLockEscalation(AlterTableOption.LockEscalation.TABLE);
    }

    public AlterTableOptionBuilder<ParentBuilder> $SET_$LOCK_ESCALATION_$DISABLE(){
        return withLockEscalation(AlterTableOption.LockEscalation.DISABLE);
    }

}
