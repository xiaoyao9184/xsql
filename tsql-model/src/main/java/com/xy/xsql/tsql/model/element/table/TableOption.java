package com.xy.xsql.tsql.model.element.table;

import com.xy.xsql.tsql.model.element.collation.Collate;
import com.xy.xsql.tsql.model.statement.ddl.TruncateTable;

import java.util.List;
import java.util.Optional;

/**
 * https://docs.microsoft.com/en-us/sql/t-sql/statements/create-table-transact-sql
 * Created by xiaoyao9184 on 2017/8/16.
 */
public interface TableOption {



    enum  DATA_COMPRESSION_Type {
        NONE,
        ROW,
        PAGE
    }

    class DATA_COMPRESSION implements TableOption {
        private DATA_COMPRESSION_Type type;

        //[ WITH ( PARTITIONS ( { <partition_number_expression> | <range> }
        //[ , ...n ] ) ) ]
        private List<TruncateTable.Partitions> partitionsList;

        public DATA_COMPRESSION_Type getType() {
            return type;
        }

        public void setType(DATA_COMPRESSION_Type type) {
            this.type = type;
        }

        public List<TruncateTable.Partitions> getPartitionsList() {
            return partitionsList;
        }

        public void setPartitionsList(List<TruncateTable.Partitions> partitionsList) {
            this.partitionsList = partitionsList;
        }
    }

    class FILETABLE_DIRECTORY implements TableOption {
        private String directoryName;

        public String getDirectoryName() {
            return directoryName;
        }

        public void setDirectoryName(String directoryName) {
            this.directoryName = directoryName;
        }
    }

    class FILETABLE_COLLATE_FILENAME implements TableOption {

        private Collate collation_name;

        public Collate getCollation_name() {
            return collation_name;
        }

        public void setCollation_name(Collate collation_name) {
            this.collation_name = collation_name;
        }
    }

    class FILETABLE_PRIMARY_KEY_CONSTRAINT_NAME implements TableOption {

        private Collate collation_name;

        public Collate getCollation_name() {
            return collation_name;
        }

        public void setCollation_name(Collate collation_name) {
            this.collation_name = collation_name;
        }
    }

    class FILETABLE_STREAMID_UNIQUE_CONSTRAINT_NAME extends FILETABLE_PRIMARY_KEY_CONSTRAINT_NAME {

    }

    class FILETABLE_FULLPATH_UNIQUE_CONSTRAINT_NAME extends FILETABLE_PRIMARY_KEY_CONSTRAINT_NAME {

    }

    class SYSTEM_VERSIONING implements TableOption {
        private String schemaName;
        private String historyTableName;
        private String useDataConsistencyCheck;

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

        public String getUseDataConsistencyCheck() {
            return useDataConsistencyCheck;
        }

        public void setUseDataConsistencyCheck(String useDataConsistencyCheck) {
            this.useDataConsistencyCheck = useDataConsistencyCheck;
        }
    }

    class REMOTE_DATA_ARCHIVE implements TableOption {

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

    class MEMORY_OPTIMIZED implements TableOption {}

    class DURABILITY implements TableOption {
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
}
