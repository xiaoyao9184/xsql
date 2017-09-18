package com.xy.xsql.tsql.model.element.table;

import com.xy.xsql.tsql.model.element.collation.Collate;
import com.xy.xsql.tsql.model.statement.ddl.TruncateTable;
import com.xy.xsql.tsql.model.statement.ddl.alter.table.Item;

import java.util.List;

/**
 * https://docs.microsoft.com/en-us/sql/t-sql/statements/create-table-transact-sql
 * Created by xiaoyao9184 on 2017/8/16.
 */
public interface TableOption {


    /**
     * TODO simple of
     * @see com.xy.xsql.tsql.model.element.index.IndexOption.DataCompression
     */
    enum DataCompressionType {
        NONE,
        ROW,
        PAGE
    }

    class DataCompression implements TableOption {
        private DataCompressionType type;

        //[ WITH ( PARTITIONS ( { <partition_number_expression> | <range> }
        //[ , ...n ] ) ) ]
        private List<TruncateTable.Partitions> partitionsList;

        public DataCompressionType getType() {
            return type;
        }

        public void setType(DataCompressionType type) {
            this.type = type;
        }

        public List<TruncateTable.Partitions> getPartitionsList() {
            return partitionsList;
        }

        public void setPartitionsList(List<TruncateTable.Partitions> partitionsList) {
            this.partitionsList = partitionsList;
        }
    }

    class FileTableDirectory implements TableOption {
        private String directoryName;

        public String getDirectoryName() {
            return directoryName;
        }

        public void setDirectoryName(String directoryName) {
            this.directoryName = directoryName;
        }
    }

    class FileTableCollateFilename implements TableOption {

        private Collate collationName;

        public Collate getCollationName() {
            return collationName;
        }

        public void setCollationName(Collate collationName) {
            this.collationName = collationName;
        }
    }

    class FileTablePrimaryKeyConstraintName implements TableOption {

        private Collate collationName;

        public Collate getCollationName() {
            return collationName;
        }

        public void setCollationName(Collate collationName) {
            this.collationName = collationName;
        }
    }

    class FileTableStreamIdUniqueConstraintName extends FileTablePrimaryKeyConstraintName {

    }

    class FileTableFullPathUniqueConstraintName extends FileTablePrimaryKeyConstraintName {

    }

    class SystemVersioning implements TableOption {
        private String schemaName;
        private String historyTableName;
        private Boolean useDataConsistencyCheck;

        public String getSchemaName() {
            return schemaName;
        }

        public void setSchemaName(String schemaName) {
            this.schemaName = schemaName;
        }

        public String getHistoryTableName() {
            return historyTableName;
        }

        public void setHistoryTableName(String historyTableName) {
            this.historyTableName = historyTableName;
        }

        public Boolean getUseDataConsistencyCheck() {
            return useDataConsistencyCheck;
        }

        public void setUseDataConsistencyCheck(Boolean useDataConsistencyCheck) {
            this.useDataConsistencyCheck = useDataConsistencyCheck;
        }
    }

    class RemoteDataArchive implements TableOption {

        private List<TableStretchOptions> tableStretchOptionsList;

        private boolean useOff;

        public List<TableStretchOptions> getTableStretchOptionsList() {
            return tableStretchOptionsList;
        }

        public void setTableStretchOptionsList(List<TableStretchOptions> tableStretchOptionsList) {
            this.tableStretchOptionsList = tableStretchOptionsList;
        }

        public boolean isUseOff() {
            return useOff;
        }

        public void setUseOff(boolean useOff) {
            this.useOff = useOff;
        }
    }

    class MemoryOptimized implements TableOption {}

    class Durability implements TableOption {
        private boolean schemaAndData;

        public boolean isSchemaAndData() {
            return schemaAndData;
        }

        public void setSchemaAndData(boolean schemaAndData) {
            this.schemaAndData = schemaAndData;
        }
    }

//    private Type type;
//    private boolean schemaAndData;
//    private String schemaName;
//    private String historyTableName;
//    private String useDataConsistencyCheck;
//
//    public Type getType() {
//        return type;
//    }
//
//    public void setType(Type type) {
//        this.type = type;
//    }
//
//    public boolean isSchemaAndData() {
//        return schemaAndData;
//    }
//
//    public void setSchemaAndData(boolean schemaAndData) {
//        this.schemaAndData = schemaAndData;
//    }
//
//    public String getSchemaName() {
//        return schemaName;
//    }
//
//    public void setSchemaName(String schemaName) {
//        this.schemaName = schemaName;
//    }
//
//    public String getHistoryTableName() {
//        return historyTableName;
//    }
//
//    public void setHistoryTableName(String historyTableName) {
//        this.historyTableName = historyTableName;
//    }
//
//    public String getDataConsistencyCheck() {
//        return useDataConsistencyCheck;
//    }
//
//    public void setDataConsistencyCheck(String useDataConsistencyCheck) {
//        this.useDataConsistencyCheck = useDataConsistencyCheck;
//    }
//
//    public enum Type {
//        MEMORY_OPTIMIZED,
//        DURABILITY,
//        SYSTEM_VERSIONING
//    }


    enum KeyWords {
        DATA_COMPRESSION,
        FILETABLE_DIRECTORY,
        FILETABLE_COLLATE_FILENAME,
        FILETABLE_PRIMARY_KEY_CONSTRAINT_NAME,
        FILETABLE_STREAMID_UNIQUE_CONSTRAINT_NAME,
        FILETABLE_FULLPATH_UNIQUE_CONSTRAINT_NAME,
        SYSTEM_VERSIONING,
        HISTORY_TABLE,
        DATA_CONSISTENCY_CHECK,
        REMOTE_DATA_ARCHIVE,
        MIGRATION_STATE,
        PAUSED, FILTER_PREDICATE, DURABILITY, SCHEMA_ONLY, SCHEMA_AND_DATA, MEMORY_OPTIMIZED;


        @Override
        public String toString() {
            return this.name();
        }
    }
}
