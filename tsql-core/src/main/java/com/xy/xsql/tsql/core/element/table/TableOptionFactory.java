package com.xy.xsql.tsql.core.element.table;

import com.xy.xsql.core.builder.CodeTreeBuilder;
import com.xy.xsql.tsql.model.datatypes.table.collation.Collate;
import com.xy.xsql.tsql.model.datatypes.table.table.TableOption;
import com.xy.xsql.tsql.model.statements.TruncateTable;

import java.util.List;

import static com.xy.xsql.core.ListBuilder.initAdd;
import static com.xy.xsql.tsql.core.statement.ddl.TruncateTableBuilder.e_pn;
import static com.xy.xsql.tsql.core.statement.ddl.TruncateTableBuilder.e_range;

/**
 * Created by xiaoyao9184 on 2017/9/1.
 */
public class TableOptionFactory {

    public TableOption DATA_COMPRESSION(TableOption.DataCompressionType type) {
        TableOption.DataCompression DataCompression = new TableOption.DataCompression();
        DataCompression.setType(type);
        return DataCompression;
    }

    public DataCompressionBuilder<Void> DATA_COMPRESSION() {
        return new DataCompressionBuilder<Void>
                ( new TableOption.DataCompression());
    }

    public TableOption FILETABLE_DIRECTORY(String directoryName) {
        TableOption.FileTableDirectory fileTableDirectory = new TableOption.FileTableDirectory();
        fileTableDirectory.setDirectoryName(directoryName);
        return fileTableDirectory;
    }

    public TableOption FILETABLE_COLLATE_FILENAME(Collate collationName) {
        TableOption.FileTableCollateFilename fileTableCollateFilename = new TableOption.FileTableCollateFilename();
        fileTableCollateFilename.setCollationName(collationName);
        return fileTableCollateFilename;
    }

    public TableOption FILETABLE_PRIMARY_KEY_CONSTRAINT_NAME(Collate collationName) {
        TableOption.FileTablePrimaryKeyConstraintName fileTablePrimaryKeyConstraintName = new TableOption.FileTablePrimaryKeyConstraintName();
        fileTablePrimaryKeyConstraintName.setCollationName(collationName);
        return fileTablePrimaryKeyConstraintName;
    }

    public TableOption FILETABLE_STREAMID_UNIQUE_CONSTRAINT_NAME(Collate collationName) {
        TableOption.FileTableStreamIdUniqueConstraintName fileTableStreamIdUniqueConstraintName = new TableOption.FileTableStreamIdUniqueConstraintName();
        fileTableStreamIdUniqueConstraintName.setCollationName(collationName);
        return fileTableStreamIdUniqueConstraintName;
    }

    public TableOption FILETABLE_FULLPATH_UNIQUE_CONSTRAINT_NAME(Collate collationName) {
        TableOption.FileTableFullPathUniqueConstraintName fileTableFullPathUniqueConstraintName = new TableOption.FileTableFullPathUniqueConstraintName();
        fileTableFullPathUniqueConstraintName.setCollationName(collationName);
        return fileTableFullPathUniqueConstraintName;
    }

    public TableOption SYSTEM_VERSIONING(String schemaName, String historyTableName, Boolean useDataConsistencyCheck) {
        TableOption.SystemVersioning systemVersioning = new TableOption.SystemVersioning();
        systemVersioning.setSchemaName(schemaName);
        systemVersioning.setHistoryTableName(historyTableName);
        systemVersioning.setUseDataConsistencyCheck(useDataConsistencyCheck);
        return systemVersioning;
    }

//    public TableOption REMOTE_DATA_ARCHIVE(String schemaName, String historyTableName, Boolean useOff) {
//        TableOption.RemoteDataArchive remoteDataArchive = new TableOption.RemoteDataArchive();
//        remoteDataArchive.setUseOff(useOff);
//        remoteDataArchive.setTableStretchOptionsList(historyTableName);
//        return remoteDataArchive;
//    }

    public TableOption REMOTE_DATA_ARCHIVE_OFF() {
        TableOption.RemoteDataArchive remoteDataArchive = new TableOption.RemoteDataArchive();
        remoteDataArchive.setUseOff(true);
        return remoteDataArchive;
    }


    public static class DataCompressionBuilder<ParentBuilder>
            extends CodeTreeBuilder<DataCompressionBuilder<ParentBuilder>,ParentBuilder,TableOption.DataCompression> {

        public DataCompressionBuilder(TableOption.DataCompression dataCompression) {
            super(dataCompression);
        }

        public DataCompressionBuilder<ParentBuilder> NONE(){
            target.setType(TableOption.DataCompressionType.NONE);
            return this;
        }

        public DataCompressionBuilder<ParentBuilder> ROW(){
            target.setType(TableOption.DataCompressionType.ROW);
            return this;
        }

        public DataCompressionBuilder<ParentBuilder> PAGE(){
            target.setType(TableOption.DataCompressionType.PAGE);
            return this;
        }

        public DataCompressionBuilder<ParentBuilder> ON_PARTITIONS(Number... partitionNumbers){
            if(partitionNumbers.length == 1){
                initAdd(e_pn(partitionNumbers[0]),
                        target::getPartitionsList,
                        target::setPartitionsList);
            }else if(partitionNumbers.length >= 2){
                initAdd(e_range(partitionNumbers[0],partitionNumbers[1]),
                        target::getPartitionsList,
                        target::setPartitionsList);
            }
            return this;
        }

        public PartitionsListBuilder<DataCompressionBuilder<ParentBuilder>> ON_PARTITIONS(){
            return new PartitionsListBuilder<DataCompressionBuilder<ParentBuilder>>
                    ()
                    .enter(this,partitionsList -> target.setPartitionsList(partitionsList));
        }
    }

    public static class PartitionsListBuilder<ParentBuilder>
            extends CodeTreeBuilder<PartitionsListBuilder<ParentBuilder>,ParentBuilder,List<TruncateTable.Partitions>> {

        public PartitionsListBuilder(List<TruncateTable.Partitions> partitionsList) {
            super(partitionsList);
        }

        public PartitionsListBuilder() {
            super(null);
        }

        private TruncateTable.PartitionNumberExpression current;

//        public PartitionsListBuilder<ParentBuilder> $(Number... partitionNumbers){
//            if(partitionNumbers.length == 1){
//                current = e_pn(partitionNumbers[0]);
//            }else if(partitionNumbers.length >= 2){
//                current = e_range(partitionNumbers[0],partitionNumbers[1]);
//            }
//            target.add(current);
//            return this;
//        }

        public PartitionsListBuilder<ParentBuilder> $(Number partitionNumber){
            current = e_pn(partitionNumber);
            target.add(current);
            return this;
        }

        public PartitionsListBuilder<ParentBuilder> $To(Number partitionNumber){
            target.remove(current);
            target.add(e_range(current.getNumber(),partitionNumber));
            return this;
        }

    }



    public TableOption MEMORY_OPTIMIZED() {
        return new TableOption.MemoryOptimized();
    }

    public TableOption DURABILITY_SCHEMA_ONLY() {
        TableOption.Durability durability = new TableOption.Durability();
        durability.setSchemaAndData(false);
        return durability;
    }

    public TableOption DURABILITY_SCHEMA_AND_DATA() {
        TableOption.Durability durability = new TableOption.Durability();
        durability.setSchemaAndData(true);
        return durability;
    }

}
