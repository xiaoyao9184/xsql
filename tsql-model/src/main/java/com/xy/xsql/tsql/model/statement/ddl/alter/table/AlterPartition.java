package com.xy.xsql.tsql.model.statement.ddl.alter.table;

import com.xy.xsql.tsql.model.expression.Expression;

import java.util.List;

/**
 * Created by xiaoyao9184 on 2017/9/17
 */
public class AlterPartition implements Item {

    private Expression sourcePartitionNumberExpression;
    private String targetTable;
    private Expression targetPartitionNumberExpression;
    private LowPriorityLockWait lowPriorityLockWait;

    public Expression getSourcePartitionNumberExpression() {
        return sourcePartitionNumberExpression;
    }

    public void setSourcePartitionNumberExpression(Expression sourcePartitionNumberExpression) {
        this.sourcePartitionNumberExpression = sourcePartitionNumberExpression;
    }

    public String getTargetTable() {
        return targetTable;
    }

    public void setTargetTable(String targetTable) {
        this.targetTable = targetTable;
    }

    public Expression getTargetPartitionNumberExpression() {
        return targetPartitionNumberExpression;
    }

    public void setTargetPartitionNumberExpression(Expression targetPartitionNumberExpression) {
        this.targetPartitionNumberExpression = targetPartitionNumberExpression;
    }

    public LowPriorityLockWait getLowPriorityLockWait() {
        return lowPriorityLockWait;
    }

    public void setLowPriorityLockWait(LowPriorityLockWait lowPriorityLockWait) {
        this.lowPriorityLockWait = lowPriorityLockWait;
    }

}
