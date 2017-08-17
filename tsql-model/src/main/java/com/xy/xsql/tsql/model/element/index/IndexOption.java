package com.xy.xsql.tsql.model.element.index;

import com.xy.xsql.tsql.model.statement.ddl.TruncateTable;

import java.util.List;

/**
 * https://docs.microsoft.com/en-us/sql/t-sql/statements/create-table-transact-sql
 * Created by xiaoyao9184 on 2017/8/7.
 */
public class IndexOption {

    private Type type;
    //ON | OFF
    private boolean useOn;
    //fillfactor
    private String fillfactor;
    //0 | delay [Minutes]
    private Integer delay;
    //NONE | ROW | PAGE | COLUMNSTORE | COLUMNSTORE_ARCHIVE
    private DataCompression dataCompression;
    //[ WITH ( PARTITIONS ( { <partition_number_expression> | <range> }
    //[ , ...n ] ) ) ]
    private List<TruncateTable.Partitions> partitionsList;


    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public boolean isUseOn() {
        return useOn;
    }

    public void setUseOn(boolean useOn) {
        this.useOn = useOn;
    }

    public String getFillfactor() {
        return fillfactor;
    }

    public void setFillfactor(String fillfactor) {
        this.fillfactor = fillfactor;
    }

    public Integer getDelay() {
        return delay;
    }

    public void setDelay(Integer delay) {
        this.delay = delay;
    }

    public DataCompression getDataCompression() {
        return dataCompression;
    }

    public void setDataCompression(DataCompression dataCompression) {
        this.dataCompression = dataCompression;
    }

    public List<TruncateTable.Partitions> getPartitionsList() {
        return partitionsList;
    }

    public void setPartitionsList(List<TruncateTable.Partitions> partitionsList) {
        this.partitionsList = partitionsList;
    }

    public enum Type{
        PAD_INDEX,
        FILLFACTOR,
        IGNORE_DUP_KEY,
        STATISTICS_NORECOMPUTE,
        ALLOW_ROW_LOCKS,
        ALLOW_PAGE_LOCKS,
        COMPRESSION_DELAY,
        DATA_COMPRESSION;
    }

    public enum DataCompression{
        NONE,
        ROW,
        PAGE,
        COLUMNSTORE,
        COLUMNSTORE_ARCHIVE;
    }


}
