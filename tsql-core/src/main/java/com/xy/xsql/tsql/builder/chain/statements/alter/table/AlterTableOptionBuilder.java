package com.xy.xsql.tsql.builder.chain.statements.alter.table;

import com.xy.xsql.core.builder.parent.ParentHoldBuilder;
import com.xy.xsql.tsql.model.statements.alter.table.AlterTableOption;

/**
 * AlterTableOptionBuilder
 * Created by xiaoyao9184 on 2017/9/16.
 */
@SuppressWarnings({"WeakerAccess", "unused"})
public class AlterTableOptionBuilder<ParentBuilder>
        extends ParentHoldBuilder<AlterTableOptionBuilder<ParentBuilder>,ParentBuilder,AlterTableOption> {

    public AlterTableOptionBuilder() {
        super(new AlterTableOption());
    }

    public AlterTableOptionBuilder(AlterTableOption target) {
        super(target);
    }

    /**
     * set
     * @param lockEscalation LockEscalation
     * @return THIS
     */
    public AlterTableOptionBuilder<ParentBuilder> withLockEscalation(AlterTableOption.LockEscalation lockEscalation){
        target.setLockEscalation(lockEscalation);
        return this;
    }




    /*
    Quick
     */

    /**
     * set
     * @return THIS
     */
    public AlterTableOptionBuilder<ParentBuilder> $Set$LockEscalation$Auto(){
        return withLockEscalation(AlterTableOption.LockEscalation.AUTO);
    }

    /**
     * set
     * @return THIS
     */
    public AlterTableOptionBuilder<ParentBuilder> $Set$LockEscalation$Table(){
        return withLockEscalation(AlterTableOption.LockEscalation.TABLE);
    }

    /**
     * set
     * @return THIS
     */
    public AlterTableOptionBuilder<ParentBuilder> $Set$LockEscalation$Disable(){
        return withLockEscalation(AlterTableOption.LockEscalation.DISABLE);
    }

}
