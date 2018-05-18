package com.xy.xsql.tsql.model.statements.alter.table;

import com.xy.xsql.tsql.model.datatypes.table.index.IndexOption;

import java.util.List;

/**
 * Created by xiaoyao9184 on 2017/9/17
 */
public class Rebuild implements Item {

    private boolean useAll;
    private List<IndexOption> rebuildOptions;
    private Integer partitionNumber;
    private List<SinglePartitionRebuildOption> singlePartitionRebuildOptions;

    public boolean isUseAll() {
        return useAll;
    }

    public void setUseAll(boolean useAll) {
        this.useAll = useAll;
    }

    public Integer getPartitionNumber() {
        return partitionNumber;
    }

    public void setPartitionNumber(Integer partitionNumber) {
        this.partitionNumber = partitionNumber;
    }

    public List<IndexOption> getRebuildOptions() {
        return rebuildOptions;
    }

    public void setRebuildOptions(List<IndexOption> rebuildOptions) {
        this.rebuildOptions = rebuildOptions;
    }

    public List<SinglePartitionRebuildOption> getSinglePartitionRebuildOptions() {
        return singlePartitionRebuildOptions;
    }

    public void setSinglePartitionRebuildOptions(List<SinglePartitionRebuildOption> singlePartitionRebuildOptions) {
        this.singlePartitionRebuildOptions = singlePartitionRebuildOptions;
    }

}
