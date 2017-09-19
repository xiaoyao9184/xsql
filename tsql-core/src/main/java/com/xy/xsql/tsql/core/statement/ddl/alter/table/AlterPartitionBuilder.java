package com.xy.xsql.tsql.core.statement.ddl.alter.table;

import com.xy.xsql.core.builder.CodeTreeBuilder;
import com.xy.xsql.tsql.model.expression.Expression;
import com.xy.xsql.tsql.model.statement.ddl.alter.table.AlterPartition;
import com.xy.xsql.tsql.model.statement.ddl.alter.table.LowPriorityLockWait;

import static com.xy.xsql.core.FiledBuilder.initSet;

/**
 * Created by xiaoyao9184 on 2017/9/16.
 */
public class AlterPartitionBuilder<ParentBuilder>
        extends CodeTreeBuilder<AlterPartitionBuilder<ParentBuilder>,ParentBuilder,AlterPartition> {

    public AlterPartitionBuilder(AlterPartition target) {
        super(target);
    }

    public AlterPartitionBuilder<ParentBuilder> withSourcePartitionNumberExpression(Expression sourcePartitionNumberExpression){
        target.setSourcePartitionNumberExpression(sourcePartitionNumberExpression);
        return this;
    }

    public AlterPartitionBuilder<ParentBuilder> withTargetPartitionNumberExpression(Expression targetPartitionNumberExpression){
        target.setTargetPartitionNumberExpression(targetPartitionNumberExpression);
        return this;
    }

    public AlterPartitionBuilder<ParentBuilder> withTargetTable(String targetTable){
        target.setTargetTable(targetTable);
        return this;
    }

    public AlterPartitionBuilder<ParentBuilder> withLowPriorityLockWait(LowPriorityLockWait lowPriorityLockWait){
        target.setLowPriorityLockWait(lowPriorityLockWait);
        return this;
    }

    /*
    Quick
     */

    private boolean tempFlagSourceTarget;

    public AlterPartitionBuilder<ParentBuilder> $PARTITION(Expression partitionNumberExpression){
        if(tempFlagSourceTarget){
            return withSourcePartitionNumberExpression(partitionNumberExpression);
        }else{
            return withTargetPartitionNumberExpression(partitionNumberExpression);
        }
    }

    public AlterPartitionBuilder<ParentBuilder> $TO(String targetTable){
        tempFlagSourceTarget = false;
        return withTargetTable(targetTable);
    }

    public LowPriorityLockWaitBuilder<AlterPartitionBuilder<ParentBuilder>> $WITH(){
        return new LowPriorityLockWaitBuilder<AlterPartitionBuilder<ParentBuilder>>
                (initSet(LowPriorityLockWait::new,
                        target::getLowPriorityLockWait,
                        target::setLowPriorityLockWait))
                .in(this);
    }


}
