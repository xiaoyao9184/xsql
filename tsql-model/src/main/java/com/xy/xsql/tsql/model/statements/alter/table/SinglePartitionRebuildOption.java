package com.xy.xsql.tsql.model.statements.alter.table;

import com.xy.xsql.tsql.model.datatypes.constants.NumberConstant;
import com.xy.xsql.tsql.model.datatypes.table.index.IndexOption;

/**
 * Created by xiaoyao9184 on 2017/9/18.
 */
public class SinglePartitionRebuildOption {
    private boolean useSortInTempDb;
    private NumberConstant maxDegreeOfParallelism;
    private IndexOption.DataCompression dataCompression;
    private boolean useOnline;
    private LowPriorityLockWait lowPriorityLockWait;

    public boolean isUseSortInTempDb() {
        return useSortInTempDb;
    }

    public void setUseSortInTempDb(boolean useSortInTempDb) {
        this.useSortInTempDb = useSortInTempDb;
    }

    public NumberConstant getMaxDegreeOfParallelism() {
        return maxDegreeOfParallelism;
    }

    public void setMaxDegreeOfParallelism(NumberConstant maxDegreeOfParallelism) {
        this.maxDegreeOfParallelism = maxDegreeOfParallelism;
    }

    public IndexOption.DataCompression getDataCompression() {
        return dataCompression;
    }

    public void setDataCompression(IndexOption.DataCompression dataCompression) {
        this.dataCompression = dataCompression;
    }

    public boolean isUseOnline() {
        return useOnline;
    }

    public void setUseOnline(boolean useOnline) {
        this.useOnline = useOnline;
    }

    public LowPriorityLockWait getLowPriorityLockWait() {
        return lowPriorityLockWait;
    }

    public void setLowPriorityLockWait(LowPriorityLockWait lowPriorityLockWait) {
        this.lowPriorityLockWait = lowPriorityLockWait;
    }
}
