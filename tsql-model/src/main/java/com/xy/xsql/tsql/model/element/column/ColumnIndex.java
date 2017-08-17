package com.xy.xsql.tsql.model.element.column;

import com.xy.xsql.tsql.model.element.index.IndexOption;
import com.xy.xsql.tsql.model.element.index.Partition;

import java.util.List;

/**
 * Created by xiaoyao9184 on 2017/8/16.
 */
public class ColumnIndex {
    //INDEX index_name [ CLUSTERED | NONCLUSTERED ]
    private String indexName;
    private Boolean useClustered;

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
}
