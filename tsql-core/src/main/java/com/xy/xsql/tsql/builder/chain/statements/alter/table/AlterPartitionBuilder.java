package com.xy.xsql.tsql.builder.chain.statements.alter.table;

import com.xy.xsql.core.builder.parent.ParentHoldBuilder;
import com.xy.xsql.tsql.model.elements.expressions.Expression;
import com.xy.xsql.tsql.model.statements.alter.table.AlterPartition;
import com.xy.xsql.tsql.model.statements.alter.table.LowPriorityLockWait;

import static com.xy.xsql.core.handler.object.GetterSetterObjectHandler.object;

/**
 * AlterPartitionBuilder
 * Created by xiaoyao9184 on 2017/9/16.
 */
@SuppressWarnings({"WeakerAccess", "unused"})
public class AlterPartitionBuilder<ParentBuilder>
        extends ParentHoldBuilder<AlterPartitionBuilder<ParentBuilder>,ParentBuilder,AlterPartition> {

    public AlterPartitionBuilder() {
        super(new AlterPartition());
    }

    public AlterPartitionBuilder(AlterPartition target) {
        super(target);
    }

    /**
     * set
     * @param sourcePartitionNumberExpression Expression
     * @return THIS
     */
    public AlterPartitionBuilder<ParentBuilder> withSourcePartitionNumberExpression(Expression sourcePartitionNumberExpression){
        target.setSourcePartitionNumberExpression(sourcePartitionNumberExpression);
        return this;
    }

    /**
     * set
     * @param targetPartitionNumberExpression Expression
     * @return THIS
     */
    public AlterPartitionBuilder<ParentBuilder> withTargetPartitionNumberExpression(Expression targetPartitionNumberExpression){
        target.setTargetPartitionNumberExpression(targetPartitionNumberExpression);
        return this;
    }

    /**
     * set
     * @param targetTable target table
     * @return THIS
     */
    public AlterPartitionBuilder<ParentBuilder> withTargetTable(String targetTable){
        target.setTargetTable(targetTable);
        return this;
    }

    /**
     * set
     * @param lowPriorityLockWait LowPriorityLockWait
     * @return THIS
     */
    public AlterPartitionBuilder<ParentBuilder> withLowPriorityLockWait(LowPriorityLockWait lowPriorityLockWait){
        target.setLowPriorityLockWait(lowPriorityLockWait);
        return this;
    }




    /*
    Quick
     */

    private boolean tempFlagSourceTarget;

    /**
     * Quick set
     * @param partitionNumberExpression Expression
     * @return THIS
     */
    public AlterPartitionBuilder<ParentBuilder> $Partition(Expression partitionNumberExpression){
        if(tempFlagSourceTarget){
            return withSourcePartitionNumberExpression(partitionNumberExpression);
        }else{
            return withTargetPartitionNumberExpression(partitionNumberExpression);
        }
    }

    /**
     * set
     * @param targetTable target table
     * @return THIS
     */
    public AlterPartitionBuilder<ParentBuilder> $To(String targetTable){
        tempFlagSourceTarget = false;
        return withTargetTable(targetTable);
    }

    /**
     * Quick in
     * @return LowPriorityLockWaitBuilder
     */
    public LowPriorityLockWaitBuilder<AlterPartitionBuilder<ParentBuilder>> $With(){
        return new LowPriorityLockWaitBuilder<AlterPartitionBuilder<ParentBuilder>>
                (object(target::getLowPriorityLockWait, target::setLowPriorityLockWait)
                        .init(LowPriorityLockWait::new))
                .in(this);
    }

}
