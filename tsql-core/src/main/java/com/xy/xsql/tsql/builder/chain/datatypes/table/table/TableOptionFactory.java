package com.xy.xsql.tsql.builder.chain.datatypes.table.table;

import com.xy.xsql.core.builder.CodeTreeBuilder;
import com.xy.xsql.tsql.model.datatypes.table.collation.Collate;
import com.xy.xsql.tsql.model.datatypes.table.table.TableOption;
import com.xy.xsql.tsql.model.statements.TruncateTable;

import java.util.List;

import static com.xy.xsql.core.ListBuilder.initAdd;
import static com.xy.xsql.tsql.builder.chain.statements.TruncateTableBuilder.e_pn;
import static com.xy.xsql.tsql.builder.chain.statements.TruncateTableBuilder.e_range;

/**
 * TableOptionFactory
 * Created by xiaoyao9184 on 2017/9/1.
 */
@SuppressWarnings({"unused","WeakerAccess"})
public class TableOptionFactory {

    /**
     * Quick build
     * @param type DataCompressionType
     * @return TableOption
     */
    public TableOption $DataCompression(TableOption.DataCompressionType type) {
        TableOption.DataCompression DataCompression = new TableOption.DataCompression();
        DataCompression.setType(type);
        return DataCompression;
    }

    /**
     * Quick in
     * @return TableOption
     */
    public DataCompressionBuilder<Void> $DataCompression() {
        return new DataCompressionBuilder<>
                (new TableOption.DataCompression());
    }

    /**
     * Quick build
     * @param directoryName directory name
     * @return TableOption
     */
    public TableOption $FiletableDirectory(String directoryName) {
        TableOption.FileTableDirectory fileTableDirectory = new TableOption.FileTableDirectory();
        fileTableDirectory.setDirectoryName(directoryName);
        return fileTableDirectory;
    }

    /**
     * Quick build
     * @param collationName collation name
     * @return TableOption
     */
    public TableOption $FiletableCollateFilename(Collate collationName) {
        TableOption.FileTableCollateFilename fileTableCollateFilename = new TableOption.FileTableCollateFilename();
        fileTableCollateFilename.setCollationName(collationName);
        return fileTableCollateFilename;
    }

    /**
     * Quick build
     * @param collationName collation name
     * @return TableOption
     */
    public TableOption $FiletablePrimaryKeyConstraintName(Collate collationName) {
        TableOption.FileTablePrimaryKeyConstraintName fileTablePrimaryKeyConstraintName = new TableOption.FileTablePrimaryKeyConstraintName();
        fileTablePrimaryKeyConstraintName.setCollationName(collationName);
        return fileTablePrimaryKeyConstraintName;
    }

    /**
     * Quick build
     * @param collationName collation name
     * @return TableOption
     */
    public TableOption $FiletableStreamidUniqueConstraintName(Collate collationName) {
        TableOption.FileTableStreamIdUniqueConstraintName fileTableStreamIdUniqueConstraintName = new TableOption.FileTableStreamIdUniqueConstraintName();
        fileTableStreamIdUniqueConstraintName.setCollationName(collationName);
        return fileTableStreamIdUniqueConstraintName;
    }

    /**
     * Quick build
     * @param collationName collation name
     * @return TableOption
     */
    public TableOption $FiletableFullpathUniqueConstraintName(Collate collationName) {
        TableOption.FileTableFullPathUniqueConstraintName fileTableFullPathUniqueConstraintName = new TableOption.FileTableFullPathUniqueConstraintName();
        fileTableFullPathUniqueConstraintName.setCollationName(collationName);
        return fileTableFullPathUniqueConstraintName;
    }

    /**
     * Quick build
     * @param schemaName schema name
     * @param historyTableName history table name
     * @param useDataConsistencyCheck  data consistency check
     * @return TableOption
     */
    public TableOption $SystemVersioning(String schemaName, String historyTableName, Boolean useDataConsistencyCheck) {
        TableOption.SystemVersioning systemVersioning = new TableOption.SystemVersioning();
        systemVersioning.setSchemaName(schemaName);
        systemVersioning.setHistoryTableName(historyTableName);
        systemVersioning.setUseDataConsistencyCheck(useDataConsistencyCheck);
        return systemVersioning;
    }

//    public TableOption $REMOTE_DATA_ARCHIVE(String schemaName, String historyTableName, Boolean useOff) {
//        TableOption.RemoteDataArchive remoteDataArchive = new TableOption.RemoteDataArchive();
//        remoteDataArchive.setUseOff(useOff);
//        remoteDataArchive.setTableStretchOptionsList(historyTableName);
//        return remoteDataArchive;
//    }

    /**
     * Quick build
     * @return TableOption
     */
    public TableOption $RemoteDataArchiveOff() {
        TableOption.RemoteDataArchive remoteDataArchive = new TableOption.RemoteDataArchive();
        remoteDataArchive.setUseOff(true);
        return remoteDataArchive;
    }

    /**
     * DataCompressionBuilder
     * @param <ParentBuilder>
     */
    public static class DataCompressionBuilder<ParentBuilder>
            extends CodeTreeBuilder<DataCompressionBuilder<ParentBuilder>,ParentBuilder,TableOption.DataCompression> {

        public DataCompressionBuilder(TableOption.DataCompression dataCompression) {
            super(dataCompression);
        }




        /*
        Quick
         */

        /**
         * Quick set
         * @return THIS
         */
        public DataCompressionBuilder<ParentBuilder> $None(){
            target.setType(TableOption.DataCompressionType.NONE);
            return this;
        }

        /**
         * Quick set
         * @return THIS
         */
        public DataCompressionBuilder<ParentBuilder> $Row(){
            target.setType(TableOption.DataCompressionType.ROW);
            return this;
        }

        /**
         * Quick set
         * @return THIS
         */
        public DataCompressionBuilder<ParentBuilder> $Page(){
            target.setType(TableOption.DataCompressionType.PAGE);
            return this;
        }

        /**
         * Quick set
         * @param partitionNumbers partition number
         * @return THIS
         */
        public DataCompressionBuilder<ParentBuilder> $OnPartitions(Number... partitionNumbers){
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

        /**
         * Quick in
         * @return PartitionsListBuilder
         */
        public PartitionsListBuilder<DataCompressionBuilder<ParentBuilder>> $OnPartitions(){
            return new PartitionsListBuilder<DataCompressionBuilder<ParentBuilder>>
                    ()
                    .enter(this,partitionsList -> target.setPartitionsList(partitionsList));
        }
    }

    /**
     * PartitionsListBuilder
     * @param <ParentBuilder>
     */
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

        /**
         * Quick set
         * @param partitionNumber partition number
         * @return THIS
         */
        public PartitionsListBuilder<ParentBuilder> $(Number partitionNumber){
            current = e_pn(partitionNumber);
            target.add(current);
            return this;
        }

        /**
         * Quick set
         * @param partitionNumber partition number
         * @return THIS
         */
        public PartitionsListBuilder<ParentBuilder> $To(Number partitionNumber){
            target.remove(current);
            target.add(e_range(current.getNumber(),partitionNumber));
            return this;
        }

    }



    /**
     * Quick build
     * @return TableOption
     */
    public TableOption $MemoryOptimized() {
        return new TableOption.MemoryOptimized();
    }

    /**
     * Quick build
     * @return TableOption
     */
    public TableOption $DurabilitySchemaOnly() {
        TableOption.Durability durability = new TableOption.Durability();
        durability.setSchemaAndData(false);
        return durability;
    }

    /**
     * Quick build
     * @return TableOption
     */
    public TableOption $DurabilitySchemaAndData() {
        TableOption.Durability durability = new TableOption.Durability();
        durability.setSchemaAndData(true);
        return durability;
    }

}
