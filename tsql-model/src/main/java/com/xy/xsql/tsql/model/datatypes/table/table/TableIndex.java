package com.xy.xsql.tsql.model.datatypes.table.table;

import com.xy.xsql.tsql.model.datatypes.table.index.IndexOption;
import com.xy.xsql.tsql.model.datatypes.table.constraint.PrimaryUnique;
import com.xy.xsql.tsql.model.datatypes.table.index.Partition;
import com.xy.xsql.tsql.model.statements.create.table.DiskBasedCreateTable;
import com.xy.xsql.tsql.model.statements.create.table.MemoryOptimizedCreateTable;

import java.util.List;

/**
 * https://docs.microsoft.com/en-us/sql/t-sql/statements/create-table-transact-sql
 * Created by xiaoyao9184 on 2017/8/16.
 */
public class TableIndex
        implements DiskBasedCreateTable.Item, MemoryOptimizedCreateTable.Item  {
    //
    private String indexName;
    private Boolean useClustered;
    private Boolean useNonClustered;
    private boolean useColumnStore;
    private boolean useHash;
    private List<PrimaryUnique.Column> columnNameList;

    //[ WITH ( <index_option> [ ,... n ] ) ]
    private List<IndexOption> indexOptionList;
    //[ ON { partition_scheme_name (column_name )
    //        | filegroup_name
    //        | default
    //    }
    //]
    private Partition partition;
    //[ FILESTREAM_ON { filestream_filegroup_name | partition_scheme_name | "NULL" } ]
    private Partition fileStreamPartition;

    //Memory

    //WITH (BUCKET_COUNT = bucket_count)
    private Integer bucketCount;
    //[WITH ( COMPRESSION_DELAY = {0 | delay [Minutes]})]
    private Integer delay;

    public String getIndexName() {
        return indexName;
    }

    public void setIndexName(String indexName) {
        this.indexName = indexName;
    }

    public Boolean getUseClustered() {
        return useClustered;
    }

    public void setUseClustered(Boolean useClustered) {
        this.useClustered = useClustered;
    }

    public Boolean getUseNonClustered() {
        return useNonClustered;
    }

    public void setUseNonClustered(Boolean useNonClustered) {
        this.useNonClustered = useNonClustered;
    }

    public boolean isUseColumnStore() {
        return useColumnStore;
    }

    public void setUseColumnStore(boolean useColumnStore) {
        this.useColumnStore = useColumnStore;
    }

    public List<PrimaryUnique.Column> getColumnNameList() {
        return columnNameList;
    }

    public void setColumnNameList(List<PrimaryUnique.Column> columnNameList) {
        this.columnNameList = columnNameList;
    }

    public List<IndexOption> getIndexOptionList() {
        return indexOptionList;
    }

    public void setIndexOptionList(List<IndexOption> indexOptionList) {
        this.indexOptionList = indexOptionList;
    }

    public Partition getPartition() {
        return partition;
    }

    public void setPartition(Partition partition) {
        this.partition = partition;
    }

    public Partition getFileStreamPartition() {
        return fileStreamPartition;
    }

    public void setFileStreamPartition(Partition fileStreamPartition) {
        this.fileStreamPartition = fileStreamPartition;
    }

    public Integer getBucketCount() {
        return bucketCount;
    }

    public void setBucketCount(Integer bucketCount) {
        this.bucketCount = bucketCount;
    }

    public Integer getDelay() {
        return delay;
    }

    public void setDelay(Integer delay) {
        this.delay = delay;
    }

    public boolean isUseHash() {
        return useHash;
    }

    public void setUseHash(boolean useHash) {
        this.useHash = useHash;
    }
}
