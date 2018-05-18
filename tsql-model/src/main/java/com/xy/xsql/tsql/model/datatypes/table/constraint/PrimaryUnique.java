package com.xy.xsql.tsql.model.datatypes.table.constraint;

import com.xy.xsql.tsql.model.datatypes.table.index.IndexOption;
import com.xy.xsql.tsql.model.datatypes.table.index.Partition;

import java.util.List;

/**
 * Created by xiaoyao9184 on 2017/8/7.
 */
public class PrimaryUnique implements Constraint {
    //PRIMARY KEY | UNIQUE
    private boolean usePrimaryKey;
    //[ CLUSTERED | NONCLUSTERED ]
    private Boolean useClustered;
    //[
    //    WITH FILLFACTOR = fillfactor
    //    |WITH ( <index_option> [ , ...n ] )
    //]
    private String fillfactor;
    private List<IndexOption> indexOptions;
    //[ ON { partition_scheme_name (partition_column_name)
    //| filegroup | "default" } ]
    private Partition on;


    //Table

    //(column [ ASC | DESC ] [ ,...n ] )
    private List<Column> columns;

    //Memory

    //NONCLUSTERED HASH
    private boolean useHash;
    //WITH (BUCKET_COUNT = bucket_count)
    private Integer bucketCount;

    public PrimaryUnique() {
    }

    public PrimaryUnique(boolean usePrimaryKey) {
        this.usePrimaryKey = usePrimaryKey;
    }


    public boolean isUsePrimaryKey() {
        return usePrimaryKey;
    }

    public void setUsePrimaryKey(boolean usePrimaryKey) {
        this.usePrimaryKey = usePrimaryKey;
    }

    public Boolean getUseClustered() {
        return useClustered;
    }

    public void setUseClustered(Boolean useClustered) {
        this.useClustered = useClustered;
    }

    public List<Column> getColumns() {
        return columns;
    }

    public void setColumns(List<Column> columns) {
        this.columns = columns;
    }

    public String getFillfactor() {
        return fillfactor;
    }

    public void setFillfactor(String fillfactor) {
        this.fillfactor = fillfactor;
    }

    public List<IndexOption> getIndexOptions() {
        return indexOptions;
    }

    public void setIndexOptions(List<IndexOption> indexOptions) {
        this.indexOptions = indexOptions;
    }

    public Partition getOn() {
        return on;
    }

    public void setOn(Partition on) {
        this.on = on;
    }

    public boolean isUseHash() {
        return useHash;
    }

    public void setUseHash(boolean useHash) {
        this.useHash = useHash;
    }

    public Integer getBucketCount() {
        return bucketCount;
    }

    public void setBucketCount(Integer bucketCount) {
        this.bucketCount = bucketCount;
    }

    public static class Column {
        private String column;

        //[ ASC | DESC ]
        private Boolean useAsc;

        public Column(){}

        public Column(String column){
            this.column = column;
        }

        public String getColumn() {
            return column;
        }

        public void setColumn(String column) {
            this.column = column;
        }

        public Boolean isUseAsc() {
            return useAsc;
        }

        public void setUseAsc(Boolean useAsc) {
            this.useAsc = useAsc;
        }

    }

}
